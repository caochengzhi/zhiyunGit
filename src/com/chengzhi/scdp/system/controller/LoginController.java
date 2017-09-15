package com.chengzhi.scdp.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chengzhi.scdp.system.service.ISysUserService;

/**
 * 系统登录
 * @author beisi
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private ISysUserService sysUserService;

	@RequestMapping(value = "/Verification", method = {RequestMethod.POST})
	public String login(HttpServletRequest request,HttpServletResponse response){
		return "/main";
	}
}
