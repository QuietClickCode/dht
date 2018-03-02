package com.retailers.sbj.common.filter;

import com.retailers.mybatis.common.service.SysParameterConfigService;
import com.retailers.wx.common.service.WxAccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 系统启动监听
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/8
 */
public class SystemStartFilter {
    Logger logger = LoggerFactory.getLogger(SystemStartFilter.class);
    @Autowired
    private SysParameterConfigService sysParameterConfigService;
    @Autowired
    private WxAccessTokenService wxAccessTokenService;
    /**
     * 初始货系统统常量加载
     */
    public void initSysParamterConfig(){
        long time = System.currentTimeMillis();
        sysParameterConfigService.initSysParamter();
        //注册系统服务器
        sysParameterConfigService.registerService();
        //初始化微信配置常
        wxAccessTokenService.initWxConfig();
    }
}
