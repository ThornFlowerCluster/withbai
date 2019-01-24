package com.with.bai.web.dto;

import lombok.Data;

import java.util.Date;
@Data
public class FundFundDTO {
    private Long fid;
    private String name;
    private String fullname;
    private Long code;
    private String risklevel;
    private Double interestRate;
    private Integer investTime;
    private Double yearRate;
    private Integer state;
    private String company;
    private Date dateofestablishment;
    private String generalmanager;
    private Long overallscope;
    private Double totalassets;
    private Double dateUpLow;
    private Long baseline;
    private Double unitPrice;
    private String introduction;
    private Integer power;

}

