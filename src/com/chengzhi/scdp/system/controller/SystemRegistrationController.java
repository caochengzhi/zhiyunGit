package com.chengzhi.scdp.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/systemRegist")
@Controller
public class SystemRegistrationController {

	@RequestMapping(value = "/toSearch", method = {RequestMethod.POST})
	public String login(){
		System.out.println("pppppppppp");
		return "system/systemRegistSearch";
	}
}
