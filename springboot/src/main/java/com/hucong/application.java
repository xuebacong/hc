package com.hucong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//这个注解里面有扫描组件注解，可以扫描和他同级的包和子包
@MapperScan("com.hucong.dao")
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
