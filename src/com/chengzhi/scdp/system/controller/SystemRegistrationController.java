package com.chengzhi.scdp.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chengzhi.scdp.database.controller.BaseController;

@RequestMapping("/systemRegist")
@Controller
public class SystemRegistrationController extends BaseController{

	@RequestMapping(value = "/toSearch", method = {RequestMethod.GET})
	public String login(){
		return "system/systemRegistSearch";
	}
	
}
