package com.retailers.sbj.web.controller;

import com.retailers.auth.service.SysUserService;
import com.retailers.sbj.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
