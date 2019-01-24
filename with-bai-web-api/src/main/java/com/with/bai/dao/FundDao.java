package com.with.bai.dao;

import com.with.bai.domain.Fund;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FundDao {
    /**
     *   分类查询
     */
    List<Fund> selectFundByPower(int power);

    /**
     *   获取产品信息
     */

    Fund selectFundByFid(Long fid);

    /**
     *   分页显示
     */
    List<Fund> selectFundByPages(Map<String, Object> map);

    /**
     *     获取分类所有条数
     * @param fund
     * @return
     */
    int selectFundCount(Fund fund);

    //购买产品后修改已出售的数量
    int payByFund(Fund fund);
}
