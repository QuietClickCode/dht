package com.retailers.auth.test;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.service.SysUserService;
import com.retailers.auth.vo.SysUserVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.auth.base.TestBaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * Created by admin on 2017/9/25.
 */
public class SysUserServiceTest extends TestBaseJunit {
    @Autowired
    private SysUserService sysUserService;

    /**
     *
     */
    @Test
    public void querySysUserLists(){
        Pagination<SysUserVo> pages= sysUserService.querySysUserList(new HashMap<String,Object>(),1,10);
        System.out.println(JSON.toJSON(pages));
    }
}
