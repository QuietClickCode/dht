package com.retailers.hnc.manage.controller;

import com.retailers.auth.dao.SysUserMapper;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.SysUserService;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
                setCurLoginUser(request,sysUser);
                map.put("flag",true);
            }else{
                map.put("flag",false);
            }
        }
        return map;
    }

    @RequestMapping("/resetUserPassword")
    @ResponseBody
    public Map<String,Object> resetUserPassword(String account,String oldPassword,String newPassword){
        HashMap<String,Object> map = new HashMap<String,Object>();
        Map params = new HashMap();
        params.put("uaccount",account);
        Pagination<SysUser> page = new Pagination<SysUser>();
        page.setParams(params);
        page.setPageNo(1);
        page.setPageSize(1);
        List<SysUser> sysUsers = userMapper.querySysUserList(page);
        if(ObjectUtils.isEmpty(sysUsers)){
            map.put("status",-1);
            map.put("msg","该账户不存在");
        }else{
            SysUser sysUser = sysUsers.get(0);
            if(!sysUser.getUpassword().equals(oldPassword)){
                map.put("status",-1);
                map.put("msg","账号或者密码错误");
            }else{
                sysUser.setUpassword(newPassword);
                userMapper.updateSysUser(sysUser);
                map.put("status",0);
                map.put("msg","修改成功");
            }
        }
        return map;
    }
}
