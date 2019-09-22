package com.oven.vo;

import lombok.Data;

@Data
public class User {

    private Integer id;
    private String userName;
    private String password;
    private String phone;
    private String email;
    private Integer age;

}
