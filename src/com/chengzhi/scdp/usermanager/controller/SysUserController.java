package com.chengzhi.scdp.usermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户管理
 * @author beisi
 *
 */
@Controller
@RequestMapping("/userManager")
public class SysUserController {

	@RequestMapping(value = "/toSearch", method = {RequestMethod.POST})
	public String login(HttpServletRequest request,HttpServletResponse response){
		return "system/userSearch";
	}
}
