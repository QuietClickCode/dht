package com.retailers.dht.common.controller;

import com.retailers.mybatis.common.service.SysParameterConfigService;
import com.retailers.wx.common.service.WxAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 重新加载系统参数
 * @author zhongp
 */
@Controller
@RequestMapping("/reaload")
public class ReloadController {
    @Autowired
    SysParameterConfigService sysParameterConfigService;
    @Autowired
    private WxAccessTokenService accessTokenService;

    /**
     * 重新加载系统参数至内存
     * @return
     */
    @RequestMapping("/reloadParamsToMemory")
    @ResponseBody
    public Map<String,Object> reloadParamsToMemory(){
        Map<String,Object> rtn=new HashMap<String,Object>();
        sysParameterConfigService.initSysParamter();
        rtn.put("status",0);
        return rtn;
    }
    /**
     * 重新加载系统参数至内存
     * @return
     */
    @RequestMapping("/reloadWxConfigToMemory")
    @ResponseBody
    public Map<String,Object> reloadWxConfigToMemory(){
        Map<String,Object> rtn=new HashMap<String,Object>();
        accessTokenService.initWxConfig();
        rtn.put("status",0);
        return rtn;
    }
}
