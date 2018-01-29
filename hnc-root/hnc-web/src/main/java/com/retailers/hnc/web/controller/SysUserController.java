package com.retailers.hnc.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.dao.SysUserMapper;
import com.retailers.auth.entity.SysUser;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2018/1/14.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController{
    @Autowired
    SysUserMapper userMapper;

    @RequestMapping("/sysUserLogin")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, SysUser user){
        SysUser sysUser = userMapper.querySyUserByAccount(user.getUaccount());
        HashMap<String,Object> map = new HashMap<String,Object>();
        if (ObjectUtils.isEmpty(sysUser)) {
            map.put("flag",false);
        }else{
            if (ObjectUtils.isEquals(sysUser.getUpassword(),user.getUpassword())) {
                System.out.println(JSON.toJSONString(sysUser));
                setCurLoginUser(request,sysUser);

                Object obj =request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
                System.out.println(JSON.toJSONString(obj));
                map.put("flag",true);
            }else{
                map.put("flag",false);
            }
        }
        return map;
    }
}
