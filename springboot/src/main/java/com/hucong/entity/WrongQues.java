package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrongQues {
    private String uname;
    private Integer testId;
    private String testName;
    private String testDay;
    private Integer grade;
}
