package com.with.bai.web.dto;

import lombok.Data;

@Data
public class FundFinancialDTO {
    private Long fid;
    private String name;
    private Double interestRate;
    private Double totalassets;
    private Long baseline;
    private Double yearRate;
    private Integer investTime;
    private Long overallscope;
    private Double unitPrice;
    private Long positions;
    private String introduction;
    private Integer power;
}
