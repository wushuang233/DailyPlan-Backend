package com.example.daily.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wushuang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanVO {
    Integer planId;
    String planName;
    String planContent;
    Date start;
    Date end;
}
