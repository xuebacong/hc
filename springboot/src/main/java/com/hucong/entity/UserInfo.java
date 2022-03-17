package com.hucong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer uid;
    private String userDivision;
    private String userClass;
    private String userSchool;
}
