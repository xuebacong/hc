package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Single {
    private int singleId;
    private String singleQuestion;
    private String singleA;
    private String singleB;
    private String singleC;
    private String singleD;
    private String singleAnswer;
}
