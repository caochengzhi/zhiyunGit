package com.chengzhi.scdp.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
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
	
	/**
	 * 角色查看、编辑操作
	 * @param request
	 * @param operatorType
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/toModify/{operatorType}", method = {RequestMethod.POST})
	public String toModify(HttpServletRequest request,@PathVariable String operatorType, @RequestParam Long roleId){
		if(roleId != null){
			Roles role = rolesService.findRoleById(roleId);
			request.setAttribute("role", role);
		}
		request.setAttribute("roleId", roleId);
		request.setAttribute("type", operatorType);
		return "system/roleModify";
	}
	
	/**
	 * 获取角色对应的权限树
	 * @param operatorType
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/getRoleAclTree", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String getBaseAclTree(Long roleId){
		return getRolesAclTree(rolesService, roleId);
	}
	
	/**
	 * 更新角色所包含的用户
	 * @param roleId 角色id
	 * @param userIds 用户id数组
	 * @throws CustomException 
	 */
	@RequestMapping(value = "/updateRoleOfUsers", method = RequestMethod.POST)
	public String updateRoleOfUsers(Long roleId, Long[] userIds) throws CustomException{
		if(roleId == null)
			throw new CustomException("请选择相应角色进行操作!");
		rolesService.updateRoleOfUsers(roleId, userIds);
		return "system/roleSearch";
	}
	
	/**
	 * 查询角色对应的权限
	 * @param roleId
	 * @return
	 * @throws CustomException 
	 */
	@RequestMapping(value = "/getRoleViews", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String getRoleViews(Long roleId) throws CustomException{
		return getRoleViews(rolesService, roleId);
	}
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody List<Roles> searchRoles(){
		List<Roles> result = rolesService.findByCond(new Roles());
		return result;
	}
	
	/**
	 * 新增或修改指定角色
	 * @param role 对角色对象前台传入参数的封装
	 * @param operatorType 操作描述
	 * @return
	 * @throws CustomException 
	 */
	@RequestMapping(value = "/saveRole",method = RequestMethod.POST)
	public String saveOrUpdateRole(Roles role, String operatorType) throws CustomException{
		rolesService.saveRoleWithPermission(role, operatorType);
		return "system/roleSearch";
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
	
	
}
