package com.example.daily.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.daily.entity.Plan;
import com.example.daily.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wushuang
 */
@Mapper
public interface PlanMapper extends BaseMapper<Plan> {

}