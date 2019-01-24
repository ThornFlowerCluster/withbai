package com.with.bai.service;

import com.with.bai.domain.Fund;
import com.with.bai.domain.User;
import com.with.bai.utils.BaseResult;

public interface FundService {
    BaseResult selectFundByPower(int power);

    BaseResult selectFundByFid(Long fid);

    BaseResult selectFundByPages(int spage, int limit, Fund fund);

    BaseResult payByFund(Fund fund, User user, Double money);

    BaseResult getInformation();
}
