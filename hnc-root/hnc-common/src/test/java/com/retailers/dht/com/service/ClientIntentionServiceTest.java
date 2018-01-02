package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.hnc.common.entity.ClientIntention;
import com.retailers.hnc.common.service.ClientIntentionService;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.hnc.common.vo.ProjectVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by admin on 2017/9/27.
 */
public class ClientIntentionServiceTest extends TestBaseJunit{
    @Autowired
    private ClientIntentionService clientIntentionService;

    @Test
    public void queryFreightByAddressTest(){
        List<ClientIntentionVo> list = clientIntentionService.queryClientIntentionVoListByCmId(1L);
        System.out.println(JSON.toJSONString(list));
    }

}
