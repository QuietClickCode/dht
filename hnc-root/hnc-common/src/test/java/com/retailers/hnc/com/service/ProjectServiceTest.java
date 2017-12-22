package com.retailers.hnc.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.vo.ProjectVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/9/27.
 */
public class ProjectServiceTest extends TestBaseJunit{
    @Autowired
    private ProjectService projectService;

    @Test
    public void queryFreightByAddressTest(){
        List<ProjectVo> list = projectService.queryProjectVo();
        System.out.println(JSON.toJSONString(list));
    }

}
