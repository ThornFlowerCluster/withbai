package com.with.bai.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Fund implements Serializable {

    private Long fid;
    private String name;
    private String title;
    private Double interestRate;
    private Date time;
    private Long baseline;
    private Double yearRate;
    private Integer investTime;
    private Double unitPrice;
    private Long number;
    private Long positions;
    private String introduction;
    private Integer power;

}
