package com.chengzhi.scdp.system.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengzhi.scdp.database.controller.BaseController;

@RequestMapping("/systemRegist")
@Controller
public class SystemRegistrationController extends BaseController{

	@RequestMapping(value = "/toSearch", method = {RequestMethod.GET})
	public String login(){
		return "system/systemRegistSearch";
	}
	
	@RequestMapping(value = "/getAclTree", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody JSONArray getBaseAclTree(){
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("id", 1);
		obj.put("pid", 0);
		obj.put("status", 1);
		obj.put("title", "管理系统");
		
		JSONObject obj2 = new JSONObject();
		obj2.put("id", 2);
		obj2.put("pid",0);
		obj2.put("status", 1);
		obj2.put("title", "字典系统");
		
		JSONObject obj22 = new JSONObject();
		obj22.put("id", 22);
		obj22.put("pid", 1);
		obj22.put("status", 1);
		obj22.put("title", "字典系统2");
		
		JSONObject obj222 = new JSONObject();
		obj222.put("id", 23);
		obj222.put("pid", 22);
		obj222.put("status", 1);
		obj222.put("title", "字典系统222");
		
		array.add(obj);
		array.add(obj2);
		array.add(obj22);
		array.add(obj222);
		System.out.println(array.toString());
		return array;
//		return getResources();
	}
}
