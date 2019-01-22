package com.with.bai.service.impl;

import com.with.bai.dao.FundDao;
import com.with.bai.dao.OrdersDao;
import com.with.bai.domain.Fund;
import com.with.bai.domain.Orders;
import com.with.bai.domain.User;
import com.with.bai.service.FundService;
import com.with.bai.utils.BaseResult;
import com.with.bai.web.dto.FundDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;

@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private FundDao dao;
    private OrdersDao ordersDao;


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
     *
     * @return
     */
    @Override
    public BaseResult selectFundByPages(int page, int limit, Fund fund) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pageinfo = new HashMap<>();
        int count = getCount(fund);
        int start = (page - 1) * limit;
        int pagesNo = (int) Math.ceil(count * 1.0 / limit);
        map.put("start", start);
        map.put("limit", limit);
        map.put("fund", fund);
        pageinfo.put("page", page);
        pageinfo.put("limit", limit);
        pageinfo.put("pagesNo", pagesNo);
        List<Fund> funds = dao.selectFundByPages(map);
        List<Object> fundDTOS = getFundDTOS(funds, pageinfo);
        if (fundDTOS != null) {
            result = BaseResult.success("ok", fundDTOS);
        } else {
            result = BaseResult.fail("error");
        }
        return result;
    }

    @Override
    public BaseResult payByFund(Fund fund, User user, Double money) {
        if (money < 1000.00) {
            result = BaseResult.fail("最低起為10000");
        } else {
            Fund fundItem = dao.selectFundByFid(fund.getFid());
            int positions = (int) Math.floor(money / fund.getUnitPrice());
            Date date = new Date();
            dao.payByFund(fund.getFid(), positions);
            Orders orders = new Orders();
            orders.setFid(fund.getFid());
            orders.setUid(user.getUid());
            orders.setLoanMoney(money);
            orders.setStartTime(date);
            orders.setEndTime(getMinute(date,getMonu(fund)));
            ordersDao.insertOrdersByFid(orders);
            result = BaseResult.success("ok");
        }
        return result;
    }

    private List<Object> getFundDTOS(List<Fund> funds, Map<String, Object> pageinfo) {
        List<Object> fundDTOS = new ArrayList<>();
        fundDTOS.add(pageinfo);
        for (Fund fundd : funds) {
            FundDTO fundDTO = new FundDTO();
            BeanUtils.copyProperties(fundd, fundDTO);
            fundDTOS.add(fundDTO);
        }
        return fundDTOS;
    }

    private int getCount(Fund fund) {
        return dao.selectFundCount(fund);
    }

    public static Date getMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        date = calendar.getTime();
        return date;
    }

    public static int getMonu(Fund fund) {
        int a = 0;
        switch (fund.getInvestTime()){
            case 1:
                a = 6;
                break;
            case 2:
                a = 12;
                break;
            case 3:
                a = 24;
                break;
        }
        return a;
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




