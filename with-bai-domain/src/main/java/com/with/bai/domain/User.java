package com.with.bai.domain;

import lombok.Data;

@Data
public class User {

    private Long uid;
    private String email;
    private String name;
    private String password;
    private String IDNumber;
    private String phone;
    private String sex;
    private String job;

}
