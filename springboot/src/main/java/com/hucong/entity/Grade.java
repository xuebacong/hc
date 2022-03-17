package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grade {
    private Integer testId;
    private Integer uid;
    private String testDay;
    private Integer grade;
}
