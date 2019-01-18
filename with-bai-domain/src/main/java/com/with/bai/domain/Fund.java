package com.with.bai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Fund {

    private Long fid;
    private String name;
    private String title;
    private Date time;
    private Double interestRate;
    private Long baseline;
    private Double yearRate;
    private Long investTime;
    private Double unitPrice;
    private Long number;
    private String introduction;
    private String category;

}
