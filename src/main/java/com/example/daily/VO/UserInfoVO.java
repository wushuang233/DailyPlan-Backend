package com.example.daily.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wushuang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVO {
    private String username;
    private Integer sex;
    private Integer age;
    private Integer plan_number;
    private String introduction;
}
