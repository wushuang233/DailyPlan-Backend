package com.example.daily.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.daily.DTO.PlanDTO;
import com.example.daily.VO.PlanVO;
import com.example.daily.entity.Plan;
import com.example.daily.entity.User;
import com.example.daily.mapper.PlanMapper;
import com.example.daily.mapper.UserMapper;
import com.example.daily.service.PlanService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wushuang
 */
@Service
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {

    @Resource
    private PlanMapper planMapper;
    @Resource
    private PlanService planService;
    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean addPlan(PlanDTO planDTO) {
        String uuid = planDTO.getUuid();
        Integer userId = getUserId(uuid);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Plan plan = new Plan();
        try {
            Date start = dateFormat.parse(planDTO.getStart());
            Date end = dateFormat.parse(planDTO.getEnd());
            plan = Plan.builder()
                    .planName(planDTO.getPlanName())
                    .planContent(planDTO.getPlanContent())
                    .end(end)
                    .clock(planDTO.getClock())
                    .userId(userId)
                    .status(0)
                    .start(start)
                    .build();
        }catch (ParseException e){
            e.printStackTrace();
        }
        return planService.save(plan);
    }

    @Override
    public Integer getUserId(String uuid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return userMapper.selectOne(queryWrapper).getId();
    }

    @Override
    public List<PlanVO> getPlanList(String uuid) {
        // 确保getUserId方法正确实现并返回对应的用户ID
        Integer userId = getUserId(uuid);

        // 创建QueryWrapper实例
        QueryWrapper<Plan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                    .eq("status", 0);

        // 执行查询
        List<Plan> planList = this.baseMapper.selectList(queryWrapper);

        // 直接在流处理中转换Plan实体为PlanVO，避免单独的转换方法
        List<PlanVO> planVOList = planList.stream()
                .map(plan -> {
                    PlanVO vo = new PlanVO();
                    vo.setPlanId(plan.getId());
                    vo.setPlanName(plan.getPlanName());
                    vo.setPlanContent(plan.getPlanContent());
                    vo.setStart(plan.getStart());
                    vo.setEnd(plan.getEnd());
                    return vo;
                })
                .collect(Collectors.toList());

        return planVOList;
    }

    @Override
    public List<PlanVO> getPlanFinished(String uuid) {
        // 确保getUserId方法正确实现并返回对应的用户ID
        Integer userId = getUserId(uuid);

        // 创建QueryWrapper实例
        QueryWrapper<Plan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("status", 1);

        // 执行查询
        List<Plan> planList = this.baseMapper.selectList(queryWrapper);

        // 直接在流处理中转换Plan实体为PlanVO，避免单独的转换方法
        List<PlanVO> planVOList = planList.stream()
                .map(plan -> {
                    PlanVO vo = new PlanVO();
                    vo.setPlanId(plan.getId());
                    vo.setPlanName(plan.getPlanName());
                    vo.setPlanContent(plan.getPlanContent());
                    vo.setStart(plan.getStart());
                    vo.setEnd(plan.getEnd());
                    return vo;
                })
                .collect(Collectors.toList());

        return planVOList;
    }

    @Override
    public Boolean deletePlanById(Integer planId) {
        if (planId == null) {
            return false;
        }

        // 创建QueryWrapper实例，用于根据ID删除记录
        QueryWrapper<Plan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", planId);

        // 执行删除操作
        int rowsAffected = this.baseMapper.delete(queryWrapper);

        if(rowsAffected > 0){
            return true;
        }
        return false;

    }

    @Override
    public Boolean finishPlanById(Integer planId) {
        if (planId == null) {
            return false;
        }

        // 创建UpdateWrapper实例，用于更新指定ID的计划状态为1（已完成）
        UpdateWrapper<Plan> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", planId)
                .set("status", 1);

        // 执行更新操作
        boolean success = this.baseMapper.update(null, updateWrapper) > 0;

        return success;
    }


    @Override
    public Integer getPlanCount(String uuid) {
        // 确保getUserId方法正确实现并返回对应的用户ID
        Integer userId = getUserId(uuid);

        // 创建QueryWrapper实例，用于统计该用户的所有计划数量
        QueryWrapper<Plan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .eq("status", 1);

        // 执行统计查询
        Integer count = Math.toIntExact(this.baseMapper.selectCount(queryWrapper));

        return count;
    }
}
