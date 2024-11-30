package com.oven.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;
    private String user_name;
    private String name;
    private String code;
    private String phone;
    private Integer age;
    private Double score;
    private Boolean isLeader;

}
