package com.retailers.hnc.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by admin on 2017/9/27.
 */
public class GoodsClassificationServiceTest extends TestBaseJunit {

    @Autowired
    private GoodsClassificationService goodsClassificationService;

    @Test
    public void queryGoodsClassificationList(){
        List<GoodsClassificationVo> pagination1 = goodsClassificationService.queryGoodsClassificationListByParentId(1L);
        System.out.println(pagination1.get(0).getGgName());
        System.out.println(pagination1.get(1).getGgName());
        System.out.println(pagination1.size());
    }

    /**
     *取得所有父级
     */
    @Test
    public void  queryGoodsClassificationParents(){
        long curNode=46;
        List<Long> lists = goodsClassificationService.queryGoodsClassificationParents(curNode);
        System.out.println(JSON.toJSON(lists));
    }

    /**
     * 取得所有子集
     */
    @Test
    public void  queryGoodsClassificationChilds(){
        long curNode=30;
        List<Long> lists = goodsClassificationService.queryGoodsClassificationChilds(curNode);
        Collections.sort(lists);
        System.out.println(JSON.toJSON(lists));
    }

    @Test
    public void queryGoodsClassificationByGids(){
        List<Long> gids= Arrays.asList(57l,51l);
        Map<Long,Map<String,Long>> rtn=goodsClassificationService.queryGoodsClassificationByGids(gids);
        System.out.println(JSON.toJSON(rtn));
    }

}
