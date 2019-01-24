package com.with.bai.service.impl;

import com.with.bai.dao.FundDao;
import com.with.bai.dao.OrdersDao;
import com.with.bai.dao.UserDao;
import com.with.bai.domain.Fund;
import com.with.bai.domain.Orders;
import com.with.bai.domain.User;
import com.with.bai.service.FundService;
import com.with.bai.utils.BaseResult;
import com.with.bai.web.dto.FundDTO;
import com.with.bai.web.dto.FundFinancialDTO;
import com.with.bai.web.dto.FundFundDTO;
import com.with.bai.web.dto.InformationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class FundServiceImpl implements FundService, Serializable {

    @Autowired
    private FundDao fundDao;
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private UserDao userDao;

    BaseResult result = null;

    /**
     * 随机抽取4条数据封装
     *
     * @param power 分类 0,基金 1,理财
     * @return
     */
    @Override
    public BaseResult selectFundByPower(int power) {
        List<Fund> fundList = fundDao.selectFundByPower(power);
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
        Fund fund = fundDao.selectFundByFid(fid);
        if (fund != null) {
            if(fund.getPower() == 0){
                FundFundDTO fundFundDTO = new FundFundDTO();
                BeanUtils.copyProperties(fund, fundFundDTO);
                result = BaseResult.success("ok", fundFundDTO);
            }else if(fund.getPower() == 1) {
                FundFinancialDTO fundFinancialDTO = new FundFinancialDTO();
                BeanUtils.copyProperties(fund, fundFinancialDTO);
                result = BaseResult.success("ok", fundFinancialDTO);
            }
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
        pageinfo.put("counts",count);
        List<Fund> funds = fundDao.selectFundByPages(map);
        List<Object> fundDTOS = getFundDTOS(funds, pageinfo);
        if (fundDTOS != null) {
            result = BaseResult.success("ok", fundDTOS);
        } else {
            result = BaseResult.fail("error");
        }
        return result;
    }


    /**
     * 投资产品
     * @param fund
     * @param user
     * @param money
     * @return
     */
    @Override
    public BaseResult payByFund(Fund fund, User user, Double money) {
        int orderr;
        Fund fundItem = fundDao.selectFundByFid(fund.getFid());
        if (money == null || money < fundItem.getBaseline()) {
            result = BaseResult.fail("最低起为起购金额"+fundItem.getBaseline());
        } else {

            double positions = Math.floor(money / fundItem.getUnitPrice());
            Long aaa = (long) (positions + fundItem.getPositions());
            Date date = new Date();
            fund.setPositions(aaa);
            Orders orders = new Orders();
            orders.setFid(fund.getFid());
            orders.setUid(1L);
            orders.setLoanMoney(money);
            orders.setStartTime(date);
            orders.setEndTime(getMinute(date, getMonu(fundItem.getInvestTime())));
            Orders orders1 = ordersDao.selectOrdersByFid(orders);
            if (orders1 == null) {
                orderr = ordersDao.insertOrdersByFid(orders);
            } else {
                Double loanMoney = orders1.getLoanMoney();
                orders.setLoanMoney(loanMoney + money);
                orderr = ordersDao.updateOrdersByFid(orders);
            }
            if (orderr > 0) {
                fundDao.payByFund(fund);
            }
            result = BaseResult.success("ok");
        }
        return result;
    }

    @Override
    public BaseResult getInformation() {
        InformationDTO informationDTO = new InformationDTO();
        int userDaoCounts = userDao.getCounts();
        int ordersSum = ordersDao.selectOrdersSum();
        informationDTO.setUsers(userDaoCounts*10);
        informationDTO.setCumulative(ordersSum);
        informationDTO.setCollection(ordersSum);
        informationDTO.setProfit((int) (ordersSum*0.01));
        return BaseResult.success("ok", informationDTO);
    }

    /**
     * 减少不需要出现的数据
     *
     * @param funds
     * @param pageinfo
     * @return
     */
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

    /**
     * 设置理财的结束日期
     *
     * @param fund
     * @return
     */
    private int getCount(Fund fund) {
        return fundDao.selectFundCount(fund);
    }

    public static Date getMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取理财的投资周期
     *
     * @param fund
     * @return
     */
    public static int getMonu(int fund) {
        int a = 0;
        switch (fund) {
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
        System.out.println(a);
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




