package com.with.bai.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Fund implements Serializable {


    private Long    fid;
    private String  name;
    private String  fullname;
    private Long    code;
    private String  risklevel;
    private Integer state;
    private Date    opentradingdate;
    private String  company;
    private Date    dateofestablishment;
    private Double  interestRate;
    private String  generalmanager;
    private Long    overallscope;
    private Double  totalassets;
    private Double  dateUpLow;
    private Long    baseline;
    private Double  yearRate;
    private Integer investTime;
    private Date    remainingtime;
    private Double  unitPrice;
    private Long    positions;
    private String  introduction;
    private Integer power;


}


























