package com.chengzhi.scdp.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 角色管理
 * @author beisi
 *
 */
@Controller
@RequestMapping("/roleManager")
public class RoleController {

	@RequestMapping(value = "/toSearch", method = {RequestMethod.POST})
	public String login(){
		return "system/roleSearch";
	}
}
