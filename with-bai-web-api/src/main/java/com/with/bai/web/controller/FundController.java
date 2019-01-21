package com.with.bai.web.controller;

import com.with.bai.domain.Fund;
import com.with.bai.service.FundService;
import com.with.bai.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.path.v1}/funds")
public class FundController {

    @Autowired
    private FundService service;

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public BaseResult category(int power) {
        return service.selectFundByPower(power);
    }

    @RequestMapping(value = "fund", method = RequestMethod.GET)
    public BaseResult fund(Long fid) {
        return service.selectFundByFid(fid);
    }

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public BaseResult page(String page, String limit, Fund fund) {
        int spage = page == null ? 1 : Integer.parseInt(page);
        int slimit = limit == null ? 10 : Integer.parseInt(limit);
        BaseResult baseResult = service.selectFundByPages(spage, slimit, fund);
        return baseResult;
    }

}

