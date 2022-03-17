package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private Integer testId;
    private String testName;
    private Integer isOnline;
    private String startTime;
    private String endTime;
    private String maker;
}
