package com.retailers.razz.manage.controller;


import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.SysUserService;
import com.retailers.razz.manage.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 主页
 * @author zhongp
 *
 */
@Controller
@SessionAttributes
public class MainController extends BaseController {
	@Autowired
	SysUserService sysUserService;

	@RequestMapping(value={"/index","/"})
	public String index(HttpServletRequest request){
//		System.out.println(11111);
		SysUser sysUser=getCurLoginUser(request);
//		if(ObjectUtils.isEmpty(sysUser)){
//			return "redirect:/login";
//		}
//		System.out.println(sysUser.getUid());
		if(ObjectUtils.isEmpty(sysUser)){
			SysUser info = new SysUser();
			info.setUid(-1l);
			info.setUaccount("admin");
			info.setUname("测试");
			setCurLoginUser(request,info);
		}
		return "index";
	}
	@RequestMapping(value={"/main"})
	public String main(HttpServletRequest request){
		SysUser info = new SysUser();
		info.setUid(1l);
		info.setUname("测试");
		request.getSession().setAttribute(SystemConstant.LOG_USER_SESSION_KEY,info);
		return "main";
	}

	@RequestMapping(value={"/login"})
	public String login(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}

	@RequestMapping(value={"/setpasswordpage"})
	public String setpasswordpage(){
		return "reset_pwd";
	}

}
