package com.with.bai.domain;

import lombok.Data;

@Data
public class Loan {
    private Long lid;
    private String name;
    private Long amount;
    private Long cost;
    private Double ltvRatio;
}
