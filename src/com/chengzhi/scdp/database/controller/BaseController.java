package com.chengzhi.scdp.database.controller;

import net.sf.json.JSONArray;

import com.chengzhi.scdp.common.ThreadLocalFactory;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.manager.ModuleManager;

public class BaseController {

	public static String getUserName(){
		SysUsers user = ThreadLocalFactory.getCurrentUser();
		String userName = user.getLoginName();
		return userName;
	}
	
	/**
	 * 获取总权限菜单
	 * @return
	 */
	public String getAclTree(){
		ModuleManager manager = ModuleManager.getInstance();
		return manager.getActTree();
	}
	
	public JSONArray getResources(){
		ModuleManager manager = ModuleManager.getInstance();
		return manager.getAllResource();
	}
}
