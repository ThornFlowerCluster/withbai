package com.with.bai.domain;

import lombok.Data;

@Data
public class Fund {

    private Long fid;
    private String name;
    private String title;
    private Double interestRate;
    private Long baseline;
    private Double yearRate;
    private Long investTime;
    private Double unitPrice;
    private Long number;
    private String introduction;

}
