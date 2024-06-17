package com.example.daily.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

/**
 * @author wushuang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_plan")
public class Plan {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 计划名称
     */
    @TableField(value = "plan_name")
    private String planName;
    /**
     * 对应用户id
     */
    @TableField(value = "user_id")
    private Integer userId;
    /**
     * 计划内容
     */
    @TableField(value = "plan_content")
    private String planContent;
    /**
     * 状态码，0表示未完成，1表示已完成
     */
    @TableField(value = "status")
    private Integer status;
    /**
     * 开始时间
     */
    @TableField(value = "start")
    private Date start;
    /**
     * 结束时间
     */
    @TableField(value = "end")
    private Date end;
    /**
     * 周期
     */
    @TableField(value = "clock")
    private String clock;
}
