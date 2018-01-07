package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.service.impl.OpeningEmpClientServiceImpl;
import com.retailers.hnc.common.vo.ProjectVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class OpeningEmpClientServiceTest extends TestBaseJunit{
    @Autowired
    private OpeningEmpClientService openingEmpClientService;

    @Test
    public void queryCheckingandpassandnotpassListWeb(){
        Map params = new HashMap();
        params.put("status",1);
        List list = openingEmpClientService.queryCheckingandpassandnotpassListWeb(params,1,10).getData();
        System.out.println(JSON.toJSONString(list));
    }

}
