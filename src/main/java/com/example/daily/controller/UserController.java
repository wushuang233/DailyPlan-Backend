package com.example.daily.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.daily.DTO.LoginDTO;
import com.example.daily.DTO.UserDTO;
import com.example.daily.VO.UserInfoVO;
import com.example.daily.VO.UserLoginVO;
import com.example.daily.constant.EncryptionConstant;
import com.example.daily.constant.RedisConstant;
import com.example.daily.entity.User;
import com.example.daily.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author wushuang
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/add")
    public UserLoginVO addAdmin(@RequestBody @Validated UserDTO userDTO){
        String uuid = userService.addUser(userDTO);
        // 生成 token
        Long expireTime = System.currentTimeMillis() + EncryptionConstant.JWT_EXPIRE_TIME;
        redisTemplate.opsForValue().set(RedisConstant.ADMIN_TOKEN_PREFIX + uuid,StpUtil.getTokenValue(),1, TimeUnit.DAYS);
        return UserLoginVO.builder()
                .token(StpUtil.getTokenValue())
                .userId(uuid)
                .build();
    }

    @PostMapping("/login")
    public UserLoginVO login(@RequestBody LoginDTO loginDTO){
        System.out.println(loginDTO.toString());
        String uuid = userService.login(loginDTO);
        // 生成 token
        Long expireTime = System.currentTimeMillis() + EncryptionConstant.JWT_EXPIRE_TIME;
        StpUtil.login(uuid);
        String token = StpUtil.getTokenValue();
        redisTemplate.opsForValue().set(RedisConstant.USER_TOKEN_PREFIX + uuid,StpUtil.getTokenValue(),1, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(RedisConstant.TOKEN_USER_PREFIX + token, uuid, 1, TimeUnit.DAYS);
        return UserLoginVO.builder()
                .token(StpUtil.getTokenValue())
                .userId(uuid)
                .build();
    }

    @GetMapping("/info")
    public UserInfoVO getUserInfo(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        String uuid = (String) redisTemplate.opsForValue().get(RedisConstant.TOKEN_USER_PREFIX + token);
        if (uuid == null) {
            throw new RuntimeException("Invalid token or session expired");
        }
        User user = userService.getUser(uuid);
        return UserInfoVO.builder()
                .username(user.getUsername())
                .age(user.getAge())
                .introduction(user.getIntroduction())
                .sex(user.getSex())
                .plan_number(0)
                .build();
    }


    @PutMapping("/update")
    public boolean updateUserInfo(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(required = false) String introduction,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Integer sex) {

        String token = authorization.replace("Bearer ", "");
        String uuid = (String) redisTemplate.opsForValue().get(RedisConstant.TOKEN_USER_PREFIX + token);

        if (uuid == null) {
            throw new RuntimeException("无效的令牌或会话已过期");
        }

        try {
            userService.updateUserInfo(uuid, introduction, age, sex);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

