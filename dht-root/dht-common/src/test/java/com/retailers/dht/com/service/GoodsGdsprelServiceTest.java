package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsGdsprelServiceTest extends TestBaseJunit {
    @Autowired
    private GoodsGdsprelService goodsGdsprelService;

    @Test
    public void editGoodsInventorys(){
        Map map = new HashMap();
        map.put(326L,-1L);map.put(323L,-1L);
        try {
            boolean falg = goodsGdsprelService.editGoodsInventorys(map);
            System.out.println(falg);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void queryGoodsDetailVoByGdIds(){

        GoodsGdsprelVo falg = goodsGdsprelService.queryGoodsGdsprelVoByGdspId(326L);
        System.out.println(JSON.toJSONString(falg));
    }
}
