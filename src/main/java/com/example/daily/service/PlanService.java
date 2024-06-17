package com.example.daily.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.daily.DTO.PlanDTO;
import com.example.daily.VO.PlanVO;
import com.example.daily.entity.Plan;

import java.util.List;

/**
 * @author wushuang
 */
public interface PlanService extends IService<Plan> {

    /**
     * 增加计划
     */
    Boolean addPlan(PlanDTO planDTO);

    /**
     * 查询当前用户的计划列表
     */
    public List<PlanVO> getPlanList(String uuid);

    /**
     * 查询当前用户的已完成计划列表
     */
    public List<PlanVO> getPlanFinished(String uuid);

    Boolean deletePlanById(Integer planId);

    Boolean finishPlanById(Integer planId);

    /**
     * 获取计划数
     */
    Integer getPlanCount(String uuid);

    Integer getUserId(String uuid);
}
