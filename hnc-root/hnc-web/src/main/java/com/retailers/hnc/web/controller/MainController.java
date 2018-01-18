package com.retailers.hnc.web.controller;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.SysUserService;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes
public class MainController extends BaseController {
	@Autowired
	SysUserService sysUserService;

	@RequestMapping(value={"/index","/"})
	public String index(){
		return "login";
	}

}
