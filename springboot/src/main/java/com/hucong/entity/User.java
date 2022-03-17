package com.hucong.entity;

import com.alibaba.druid.support.monitor.annotation.MTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int uid;
    private String uname;
    private String password;
    private Integer role;
}

