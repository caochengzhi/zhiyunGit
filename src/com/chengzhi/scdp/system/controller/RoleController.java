package com.chengzhi.scdp.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.database.controller.BaseController;
import com.chengzhi.scdp.system.dao.Roles;
import com.chengzhi.scdp.system.service.IRolesService;

/**
 * 角色管理
 * @author beisi
 *
 */
@Controller
@RequestMapping("/roleManager")
public class RoleController extends BaseController{

	@Autowired
	private IRolesService rolesService;
	
	@RequestMapping(value = "/toSearch", method = {RequestMethod.GET})
	public String toSearch(){
		return "system/roleSearch";
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody List<Roles> searchRoles(){
		List<Roles> result = rolesService.findByCond(new Roles());
		return result;
	}
	
	/**
	 * 查询或修改角色对应的权限列表
	 * @param roleId 角色id
	 * @return
	 */
	@RequestMapping(value="/permission/{operatorType}/{roleId}",method = RequestMethod.POST)
	public String searchPermission(@PathVariable Long roleId, @PathVariable Long operatorType){
		if(Constants.MODIFY == operatorType){
			System.out.println("moidfy,role"+roleId);
		}else if(Constants.VIEW == operatorType){
			System.out.println("view,role"+roleId);
		}
		return "system/loggerSearch";
	}
	
	/**
	 * 新增或修改指定角色
	 * @param roleId 角色id
	 * @param roleName 角色名称
	 * @param describe 角色描述
	 * @return
	 */
	@RequestMapping(value = "/saveRole",method = RequestMethod.POST)
	public String saveOrUpdateRole(Long roleId, 
			@RequestParam(value="roleName",required=true) String roleName,
			@RequestParam(value="describe",required=true) String describe){
		if(roleId == null){
			Roles role = new Roles(roleName,describe,360L);
			rolesService.save(role);
		}else{
			Roles role = rolesService.findRoleById(roleId);
			role.setRoleName(roleName);
			role.setDescription(describe);
			rolesService.update(role);
		}
			
		return "system/roleSearch";
	}
	
	
}
