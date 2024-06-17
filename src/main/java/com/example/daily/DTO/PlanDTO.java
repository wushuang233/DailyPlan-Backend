package com.example.daily.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wushuang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {
    String uuid;
    String planName;
    String start;
    String end;
    String clock;
    String planContent;
}
