package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.CutPriceLog;
import com.retailers.dht.common.service.BuyCarService;
import com.retailers.dht.common.service.CutPriceLogService;
import com.retailers.dht.common.vo.CutPriceLogVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class CutPriceLogServiceServiceTest extends TestBaseJunit{
    @Autowired
    private CutPriceLogService cutPriceLogService;

    @Test
    public void queryCutPriceLongByGdIDTest(){
        Map map = cutPriceLogService.queryCutpriceByGdId(858L,11L);
        System.out.println(map.get(858L));
    }
}
