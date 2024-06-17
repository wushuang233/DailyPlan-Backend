package com.example.daily.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daily.DTO.LoginDTO;
import com.example.daily.DTO.UserDTO;
import com.example.daily.constant.MessageConstant;
import com.example.daily.entity.User;
import com.example.daily.exception.AccountNotExistsException;
import com.example.daily.exception.OccupiedException;
import com.example.daily.exception.WrongPasswordException;
import com.example.daily.mapper.UserMapper;
import com.example.daily.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户功能
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    private UserService userService;

    /**
     * 添加用户
     */
    @Override
    public String addUser(UserDTO userDTO) {
        // username 查重
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        if (userMapper.selectOne(queryWrapper) != null) {
            throw new OccupiedException(MessageConstant.NAME);
        }
        User newUser = User.builder()
                .username(userDTO.getUsername())
                // 使用更安全的密码加密方式
                .password(SecureUtil.sha1(userDTO.getPassword()))
                .sex(userDTO.getSex())
                .age(userDTO.getAge())
                .introduction("")
                .email(userDTO.getEmail())
                .uuid(IdUtil.randomUUID())
                .build();
        userService.save(newUser);
        return newUser.getUuid();
    }

    /**
     * 登录
     */
    @Override
    public String login(LoginDTO loginUser) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginUser.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            String encryptedPassword = SecureUtil.sha1(loginUser.getPassword());
            if (encryptedPassword.equals(user.getPassword())) {
                return user.getUuid();
            } else {
                throw new WrongPasswordException("密码错误");
            }
        }
        throw new AccountNotExistsException("账号不存在");
    }

    @Override
    public User getUser(String uuid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateUserInfo(String uuid, String introduction, Integer age, Integer sex) {
        User user = getUser(uuid);

        if (user != null) {
            if (introduction != null) {
                user.setIntroduction(introduction);
            }
            if (age != null) {
                user.setAge(age);
            }
            if (sex != null) {
                user.setSex(sex);
            }

            userMapper.updateUserInfoByUuid(uuid, introduction, age, sex);
        } else {
            throw new RuntimeException("User not found with UUID: " + uuid);
        }
    }

}
