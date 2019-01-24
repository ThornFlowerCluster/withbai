package com.with.bai.web.dto;

import lombok.Data;

@Data
public class InformationDTO {

    //累计金额
    private Integer cumulative;
    //代收金额
    private Integer collection;
    //累计收益
    private Integer profit;
    //用户量
    private Integer users;

}
