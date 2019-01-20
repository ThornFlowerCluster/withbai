package com.with.bai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Recruit {

    private Long rid;
    private String name;
    private String category;
    private int number;
    private String department;
    private String place;
    private String comparny;
    private Date time;
    private String duty;
    private String requirement;



}
