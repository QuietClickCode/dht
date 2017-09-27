package com.retailers.auth.test;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.dao.OrganizationMapper;
import com.retailers.auth.service.OrganizationService;
import com.retailers.auth.vo.OrganizationVo;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.auth.base.TestBaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by admin on 2017/9/19.
 */
public class OrganizationServiceTest extends TestBaseJunit {
    @Autowired
    OrganizationService organizationService;
    @Autowired
    OrganizationMapper organizationMapper;

    /**
     * 取得组织机构树
     */
    @Test
    public void queryOrgTree(){
        List<OrganizationVo> list =  organizationService.queryOrganizationLists(null);
        System.out.println(JSON.toJSON(list));
    }

    @Test
    public void reqRsOrgPermission(){
        List<ZTreeVo> list = organizationMapper.reqRsOrgPermission(null);
        System.out.println(JSON.toJSON(list));
    }
    @Test
    public void reqOrgTree(){
        List<ZTreeVo> list = organizationService.reqOrgTree("9,10,11");
        System.out.println(JSON.toJSON(list));
    }
}
