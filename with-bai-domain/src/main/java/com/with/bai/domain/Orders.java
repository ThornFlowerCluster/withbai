package com.with.bai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {

    private  Long oid;
    private  String loanMoney;
    private Date startTime;
    private  Date endTime;
    private String state;
    private Long uid;
    private Long lid;
    private Long fid;




}
