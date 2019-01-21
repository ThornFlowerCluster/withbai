package com.with.bai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Fund {

    private Long fid;
    private String name;
    private String title;
    private Double interestRate;
    private Date time;
    private Long baseline;
    private Double yearRate;
    private int investTime;
    private Double unitPrice;
    private Long number;
    private Long positions;
    private String introduction;
    private int power;

}
