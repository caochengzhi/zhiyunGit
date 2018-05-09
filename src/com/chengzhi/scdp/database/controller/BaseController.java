package com.chengzhi.scdp.database.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.manager.EhCacheUtil;
import com.chengzhi.scdp.system.manager.ModuleManager;
import com.chengzhi.scdp.system.service.IRolesService;

public class BaseController {

	@Autowired
	private EhCacheUtil ehcache;
	
	public static String getUserName(){
		SysUsers user = Constants.getCurrentSysUser();
		String userName = user.getLoginName();
		return userName;
	}
	
	/**
	 * 获取总权限菜单
	 * @param resource 当前角色拥有的权限
	 * @return
	 */
	public String getRolesAclTree(IRolesService rolesService, Long roleId){
		List<String> resourceCodes = new ArrayList<String>();
		
		if(roleId != null)
			resourceCodes = rolesService.getRolesOfCodes(new Long[]{roleId});
		
		ModuleManager manager = ModuleManager.getInstance();
		return manager.getRolesAclTree(resourceCodes);
	}
	
	public String getRoleViews(IRolesService rolesService, Long roleId)throws CustomException{
		if(roleId == null)
			throw new CustomException("getRoleViews出错，roleId不允许为空！");
		
		List<String> resourceCodes = rolesService.getRolesOfCodes(new Long[]{roleId});
		if(resourceCodes != null && resourceCodes.size() > 0){
			ModuleManager manager = ModuleManager.getInstance();
			return manager.getRoleViews(resourceCodes);
		}else{
			return null;
		}
		
	}
	
	public JSONArray getResources(){
		ModuleManager manager = ModuleManager.getInstance();
		return manager.getAllResource();
	}
	
	public EhCacheUtil getCache(){
		return ehcache;
	}
}
