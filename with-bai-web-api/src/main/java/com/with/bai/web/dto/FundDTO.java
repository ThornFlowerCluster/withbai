package com.with.bai.web.dto;

import lombok.Data;

@Data
public class FundDTO {
    private Long fid;
    private String name;
    private Double interestRate;
    private Long baseline;
    private Double yearRate;
    private int investTime;
    private Long number;
    private Long positions;
}
