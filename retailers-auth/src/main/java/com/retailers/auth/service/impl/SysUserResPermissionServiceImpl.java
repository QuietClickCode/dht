package com.retailers.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.dao.MenuMapper;
import com.retailers.auth.service.SysUserResPermissionService;
import com.retailers.auth.utils.CheckUserPermissionUtils;
import com.retailers.auth.vo.MenuVo;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zpapj on 2017/9/26.
 */
@Service("sysUserResPermissionService")
public class SysUserResPermissionServiceImpl implements SysUserResPermissionService {
    Logger logger = LoggerFactory.getLogger(SysUserResPermissionServiceImpl.class);
    @Autowired
    private MenuMapper menuMapper;
    @Async
    public void loadUserResPermission(Long userId) {
        logger.info("开始加载用户权限,用户id：[{}]",userId);
        long time=System.currentTimeMillis();
        List<MenuVo> list = new ArrayList<MenuVo>();
        if(userId.intValue()==-1){
            list=menuMapper.queryAllMenu(true);
        }else{
            list= menuMapper.queryUserMenu(userId,true);
        }
        Map<String,String> allowMenuUrl= new HashMap<String, String>();

        for(MenuVo vo:list){
            if(ObjectUtils.isNotEmpty(vo.getUrl())){
                allowMenuUrl.put(StringUtils.replaceFirstChart(vo.getUrl(), CheckUserPermissionUtils.URL_START_CHART,CheckUserPermissionUtils.URL_START_REPLACE),vo.getUrl());
            }
        }
        logger.info("加载用户权限结束,用户id：[{}]，执行时间：[{}],用户权限列表:[{}]",userId,(System.currentTimeMillis()-time), JSON.toJSON(allowMenuUrl));
        CheckUserPermissionUtils.permUrl.put(userId,allowMenuUrl);
    }
}
