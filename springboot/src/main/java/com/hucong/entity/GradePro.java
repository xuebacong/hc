package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradePro {
    private Integer testId;
    private Integer uid;
    private String uname;
    private String testName;
    private String testDay;
    private Integer grade;
}
