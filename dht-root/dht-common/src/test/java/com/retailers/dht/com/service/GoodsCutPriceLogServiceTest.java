package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.entity.CutPriceLog;
import com.retailers.dht.common.entity.CutPricePrice;
import com.retailers.dht.common.service.CutPriceLogService;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.CutPriceLogVo;
import com.retailers.dht.common.vo.CutPriceVo;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/12/9
 */
public class GoodsCutPriceLogServiceTest extends TestBaseJunit {

    @Autowired
    private CutPriceLogService cutPriceLogService;
    /**
     * 取得商品关联的优惠
     */
    @Test
    public void  queryCutPriceLog()throws Exception{
        List<CutPriceLogVo> list = cutPriceLogService.queryCutPriceLog(57L,11L);

    }

    @Test
    public void  queryCutpriceByGdId()throws Exception{
        Map list = cutPriceLogService.queryCutpriceByGdId(858L,11L);
        System.out.println(list.get("cpInventory"));
    }


}
