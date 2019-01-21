package com.with.bai.dao;

import com.with.bai.domain.Fund;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FundDao {

    List<Fund> selectFundByPower(int power);

    Fund selectFundByFid(Long fid);

    List<Fund> selectFundByPages(Map<String, Object> map);

    int selectFundCount(Fund fund);

}
