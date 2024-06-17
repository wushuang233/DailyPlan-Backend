package com.example.daily.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.daily.DTO.PlanDTO;
import com.example.daily.DTO.UserDTO;
import com.example.daily.VO.PlanVO;
import com.example.daily.VO.UserLoginVO;
import com.example.daily.constant.EncryptionConstant;
import com.example.daily.constant.RedisConstant;
import com.example.daily.service.impl.PlanServiceImpl;
import com.example.daily.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wushuang
 */
@RestController
@RequestMapping("/plan")
public class PlanController {
    @Resource
    private PlanServiceImpl planService;
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/add")
    public Boolean addPlan(@RequestHeader("Authorization") String authorization ,@RequestBody PlanDTO planDTO){
        String token = authorization.replace("Bearer ", "");
        String uuid = (String) redisTemplate.opsForValue().get(RedisConstant.TOKEN_USER_PREFIX + token);
        planDTO.setUuid(uuid);
        return planService.addPlan(planDTO);
    }

    /**
     * 获取计划列表
     * @param authorization
     * @return
     */
    @GetMapping("/list")
    public List<PlanVO> getPlanList(@RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer ", "");
        String uuid = (String) redisTemplate.opsForValue().get(RedisConstant.TOKEN_USER_PREFIX + token);
        return planService.getPlanList(uuid);
    }

    @GetMapping("/finish")
    public List<PlanVO> getPlanFinished(@RequestHeader("Authorization") String authorization){
        String token = authorization.replace("Bearer ", "");
        String uuid = (String) redisTemplate.opsForValue().get(RedisConstant.TOKEN_USER_PREFIX + token);
        return planService.getPlanFinished(uuid);
    }

    // 完成计划接口
    @PutMapping("/finish/{planId}")
    public Boolean finishPlanById(@PathVariable("planId") Integer planId,
                                  @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        // 同样，可以在此处添加验证逻辑
        return planService.finishPlanById(planId);
    }

    @GetMapping("/count")
    public Integer getPlanCount(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        String uuid = (String) redisTemplate.opsForValue().get(RedisConstant.TOKEN_USER_PREFIX + token);

        return Math.toIntExact(this.planService.getPlanCount(uuid));
    }

    // 删除计划接口
    @DeleteMapping("/delete/{planId}")
    public Boolean deletePlanById(@PathVariable("planId") Integer planId,
                                  @RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        // 这里可以添加验证用户权限的逻辑，比如确保删除的是当前登录用户自己的计划
        return planService.deletePlanById(planId);
    }
}
