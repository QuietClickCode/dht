package com.retailers.dht.manage.controller;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.SysUserService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		SysUser sysUser=getCurLoginUser(request);
		if(ObjectUtils.isEmpty(sysUser)){
			SysUser info = new SysUser();
			info.setUid(-1l);
			info.setUaccount("admin2");
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

	@RequestMapping("/simulateLogin")
	@ResponseBody
	public BaseResp simulateLogin(HttpServletRequest request, Long userId){
		SysUser info = sysUserService.querySysUserByUid(userId);
		setCurLoginUser(request,info);
		return success("模拟用户登陆成功");
	}
}
