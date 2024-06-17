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
public class UserLoginVO {
    private String userId;
    private String token;
}

