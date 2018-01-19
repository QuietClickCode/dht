package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.SysUserService;
import com.retailers.auth.vo.SysUserVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台人员管理
 * @author zhongp
 * @version 1.0.1
 */
@Controller
@RequestMapping("sysUser")
public class SysUserController extends BaseController {
    Logger logger= LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户退出
     * @return
     * @throws Exception
     */
    @RequestMapping("/exitLogin")
    public String exitLogin(HttpServletRequest request,HttpServletResponse response){
        logger.debug("LoginAction exitLogin()");
        // 登陆用户信息
        SysUser sysUser = (SysUser)request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        // 清空在线列表
        if (ObjectUtils.isNotEmpty(request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY))) {
            request.getSession().removeAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        }
        return "sys_user/login";
    }

    /**
     * 修改密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/editSysUserPwd")
    public String editSysUserPwd(HttpServletRequest request,HttpServletResponse response){
        /*logger.debug("LoginAction exitLogin()");
        // 登陆用户信息
        SysUser sysUser = (SysUser)request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        // 清空在线列表
        if (ObjectUtils.isNotEmpty(request.getSession().getAttribute(SystemConstant.LOG_USER_SESSION_KEY))) {
            request.getSession().removeAttribute(SystemConstant.LOG_USER_SESSION_KEY);
        }*/
        return "sys_user/reset_pwd";
    }

    /**
     * 打开后台人员管理页面
     * @return
     */
    @RequestMapping("openSysUserPage")
    @Menu(parentRes = "sys.user.manager",resourse = "sysUser.openSysUserPage",description = "职员管理",sort = 1,label = "职员管理")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    public String openSysUserPage(){
        return "sys_user/sys_user";
    }

    /**
     * 根据账号查找用户
     * @return
     */
    @RequestMapping("querySyUserByAccount")
//    @Function(label="根据账号查找用户", description = "根据账号查找用户", resourse = "sysUser.querySyUserByAccount",sort=1,parentRes="sysUser.openSysUserPage")
//    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp querySyUserByAccount(HttpServletRequest request, String account,String sysUserPwd){
        try {
            SysUser sysUser=sysUserService.querySyUserByAccount(account,sysUserPwd);
            setCurLoginUser(request,sysUser);
        } catch (AppException e) {
            return errorForSystem(e.getMessage());
        }
        return success("/index");
    }

    /*
    * 修改密码
    * */
    @RequestMapping("editSysUserPassword")
    @ResponseBody
    public BaseResp editSysUserPassword(HttpServletRequest request, String account,String sysUserPwd,String newPwd){
        try {
            boolean flag=sysUserService.editSysUserPassword(account,sysUserPwd,newPwd);
        } catch (AppException e) {
            return errorForSystem(e.getMessage());
        }
        return success(true);
    }

    /**
     * 取得职员列表
     * @param userName 用户名
     * @param orgs 所在部门（可以多个）
     * @param pageForm 显示行数
     * @return
     */
    @RequestMapping("querySysUserLists")
    @Function(label="取得职员列表", description = "取得职员列表", resourse = "sysUser.querySysUserLists",sort=1,parentRes="sysUser.openSysUserPage")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
//    public  Map<String,Object> querySysUserLists(String userName, String orgs,@RequestBody PageUtils pageForm){
    public  Map<String,Object> querySysUserLists(String userName, String orgs,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("uname",userName);
        params.put("orgIds",orgs);
        Pagination<SysUserVo> pages= sysUserService.querySysUserList(params,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",pages.getTotalCount());
        rtn.put("rows",pages.getData());
        return rtn;
    }

    /**
     * 添加职工
     * @param sysUserVo
     * @return
     */
    @RequestMapping("addSysUser")
    @Function(label="添加职员", description = "添加职员", resourse = "sysUser.addSysUser",sort=2,parentRes="sysUser.openSysUserPage")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp addSysUser(SysUserVo sysUserVo){
        try{
            validateForm(sysUserVo);
        }catch(AppException e){
            return errorForSystem(e.getMessage());
        }
        try{
            sysUserService.addSysUser(sysUserVo);
            return success("添加职工成功");
        }catch(Exception e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 编辑职工信息
     * @param sysUserVo
     * @return
     */
    @RequestMapping("editorSysUser")
    @Function(label="编辑职员", description = "编辑职员", resourse = "sysUser.editorSysUser",sort=3,parentRes="sysUser.openSysUserPage")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public Object editorSysUser(SysUserVo sysUserVo){
        try{
            validateForm(sysUserVo);
            boolean flag =sysUserService.editorSysUser(sysUserVo);
            return success(flag);
        }catch(AppException e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 删除选中的职员
     * @param uid 职员id
     * @return
     */
    @RequestMapping("delSysUser")
    @Function(label="删除职员", description = "删除职员", resourse = "sysUser.delSysUser",sort=4,parentRes="sysUser.openSysUserPage")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirectUrl = "http://www.baidu.com")
    @ResponseBody
    public BaseResp delSysUser(Long uid){
        if(ObjectUtils.isEmpty(uid)){
            return errorForParam("用户id不能为空");
        }
        boolean flag = sysUserService.delSysUser(uid);
        return success(flag);
    }
}
