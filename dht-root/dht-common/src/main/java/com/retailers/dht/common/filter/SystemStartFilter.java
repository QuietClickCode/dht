package com.retailers.dht.common.filter;

import com.retailers.dht.common.service.SysParameterConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

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
    /**
     * 初始货系统统常量加载
     */
    public void initSysParamterConfig(){
        long time = System.currentTimeMillis();
        sysParameterConfigService.initSysParamter();
        System.out.println("initSysParamterConfig------------------------------------------------>:");
    }
}
