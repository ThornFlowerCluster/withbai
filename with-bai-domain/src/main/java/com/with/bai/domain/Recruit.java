package com.with.bai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Recruit {
    private Long rid;
    private String name;
    private String category;
    private Integer number;
    private String department;
    private String place;
    private String company;
    private Date time;
    private String duty;
    private String requirement;
}
