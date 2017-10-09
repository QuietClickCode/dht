package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统常量管理
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/8
 */
@Controller
@RequestMapping("sysParams")
public class SysParameterConfigController {
    @RequestMapping("/openSysParams")
    @Menu(resourse = "sysParams.openSysParams",parentRes = "sys.manager.permissions",label = "系统参数")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    public String openSysParams(){
        return "sys_params/params";
    }
}
