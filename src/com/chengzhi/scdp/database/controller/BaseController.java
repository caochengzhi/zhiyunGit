package com.chengzhi.scdp.database.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.manager.ModuleManager;
import com.chengzhi.scdp.system.service.IRolesService;

public class BaseController {

	/**
	 * 获取当前操作的用户名
	 * @return
	 */
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
	
	/**
	 * 获取当前用户菜单
	 * @param rolesService
	 * @return
	 */
	public String getCurrentMenus(){
		List<String> menusGroup = Constants.getCurrentSysUser().getMenusGroups();
		if(menusGroup == null || menusGroup.size() == 0)
			return null;
		ModuleManager manager = ModuleManager.getInstance();
		return manager.getSystemMenus(menusGroup);
	}
	
	/**
	 * 查询角色对应的权限列表
	 * @param resource
	 * @return
	 */
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
	
	/**
	 * 对用户名和账套设置cookie
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	public void setCookie(HttpServletResponse response) throws UnsupportedEncodingException{
		Cookie cookie = new Cookie("username",URLEncoder.encode(getUserName(), "UTF-8"));
		addCookie(response, cookie);
		
		cookie = new Cookie("organizationId",Constants.getCurrentSysUser().getOrganizationId()+"");
		addCookie(response, cookie);
	}
	
	private void addCookie(HttpServletResponse response, Cookie cookie) throws UnsupportedEncodingException{
		//设置时间为1年
		cookie.setMaxAge(30*24*3600);   
		cookie.setPath("/");
		//把cookie给浏览器
		response.addCookie(cookie);
	}
}
