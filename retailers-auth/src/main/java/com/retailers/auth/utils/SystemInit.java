package com.retailers.auth.utils;

import com.retailers.auth.dao.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 系统 初始化
 */
public class SystemInit {
    Logger logger = LoggerFactory.getLogger(SystemInit.class);
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 初始化需要校验的权限路径
     */
    public void initPermissionUrl(){
        logger.info("初始化需要权限的url请求路径");
        List<String> urls = menuMapper.queryNeedPermissUrl();
        CheckUserPermissionUtils.initCheckUrl(urls);
    }
}
