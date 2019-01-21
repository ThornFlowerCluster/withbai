package com.with.bai.service.impl;

import com.with.bai.dao.FundDao;
import com.with.bai.domain.Fund;
import com.with.bai.service.FundService;
import com.with.bai.utils.BaseResult;
import com.with.bai.utils.PageInfo;
import com.with.bai.web.dto.FundDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private FundDao dao;
    BaseResult result = null;

    /**
     * 随机抽取4条数据封装
     *
     * @param power 分类 0,基金 1,理财
     * @return
     */
    @Override
    public BaseResult selectFundByPower(int power) {
        List<Fund> fundList = dao.selectFundByPower(power);
        List<FundDTO> fundDTOS = fourItems(fundList);
        if (fundDTOS != null) {
            result = BaseResult.success("ok", fundDTOS);
        } else {
            result = BaseResult.fail("error");
        }
        return result;
    }

    /**
     * 根据fid查找详细信息
     *
     * @param fid
     * @return 一条记录信息
     */
    @Override
    public BaseResult selectFundByFid(Long fid) {
        Fund fund = dao.selectFundByFid(fid);
        if (fund != null) {
            result = BaseResult.success("ok", fund);
        } else {
            result = BaseResult.fail("error");
        }
        return result;
    }

    /**
     * 分页
     * 分类 0基金 1理财
     * 投资时限 0 全部查询
     * 理财（1、六个月内，2、六到十二个月内，3 十二个月以上，4、活期）
     * 基金（1、债券型，2、混合型，3、股票型，4、封闭式，5、指数型)
     * @return
     */
    @Override
    public BaseResult selectFundByPages(int page, int limit, Fund fund) {
        PageInfo<Fund> pageInfo = new PageInfo<>();
        Map<String, Object> map = new HashMap<>();
        int count = getCount(fund);
        int start = (page - 1) * limit;
        int pagesNo = (int) Math.ceil(count * 1.0 / limit);
        map.put("start", start);
        map.put("limit", limit);
        map.put("fund", fund);
        pageInfo.setData(dao.selectFundByPages(map));
        List<Object> fundDTOS = getFundDTOS(pageInfo);
        if (pageInfo.getData() != null) {
            result = BaseResult.success("ok", fundDTOS, page, pagesNo, limit);
        } else {
            result = BaseResult.fail("error");
        }
        return result;
    }

    private List<Object> getFundDTOS(PageInfo<Fund> pageInfo) {
        List<Object> fundDTOS = new ArrayList<>();
        for (Fund fundd : pageInfo.getData()) {
            FundDTO fundDTO = new FundDTO();
            BeanUtils.copyProperties(fundd, fundDTO);
            fundDTOS.add(fundDTO);
        }
        return fundDTOS;
    }

    private int getCount(Fund fund) {
        return dao.selectFundCount(fund);
    }

    /**
     * 随机抽取4条数据
     *
     * @param fundList
     * @return FundDTO
     */
    private List<FundDTO> fourItems(List<Fund> fundList) {
        Map<Long, Fund> map = new HashMap<>();
        List<FundDTO> fundDTOS = new ArrayList<>();
        List<Fund> funds = new ArrayList<>();
        for (int i = 0; i < fundList.size(); i++) {
            if (map.size() < 4) {
                int nextInt = new Random().nextInt(fundList.size());
                if (!map.containsKey(nextInt)) {
                    map.put(fundList.get(nextInt).getFid(), fundList.get(nextInt));
                }
            }
        }

        for (Map.Entry<Long, Fund> entity : map.entrySet()) {
            funds.add(entity.getValue());
        }

        for (Fund fund : funds) {
            FundDTO fundDTO = new FundDTO();
            BeanUtils.copyProperties(fund, fundDTO);
            fundDTOS.add(fundDTO);
        }
        return fundDTOS;
    }
}




