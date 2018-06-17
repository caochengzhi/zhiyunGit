package com.chengzhi.scdp.system.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.security.beans.CaptchaUsernamePasswordToken;
import com.chengzhi.scdp.system.dao.RoleResource;
import com.chengzhi.scdp.system.dao.Roles;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.service.AbstractBusinessManager;
import com.chengzhi.scdp.system.service.IPermissionService;
import com.chengzhi.scdp.system.service.IRolesService;
import com.chengzhi.scdp.system.service.ISysUserService;
import com.chengzhi.scdp.tools.StringUtil;

/**
 * 获取用户信息及用户权限管理类
 * @author beisi
 *
 */
@Service
public class BusinessManager extends AbstractBusinessManager{
	private Logger logger = Logger.getLogger(BusinessManager.class);
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private IPermissionService permissionService;
	
	/**
	 * 用户查询，并获取当前用户所拥有的所有权限
	 * @param loginName 登录名
	 * @param passWord 登录密码
	 * @param organizationId 组织号
	 */
	@Override
	public SysUsers queryPermissions(CaptchaUsernamePasswordToken authcToken)throws CustomException{
		SysUsers currentUser = null;
		try{
			String loginName = authcToken.getUsername();
			String passWord = new String(authcToken.getPassword());
			Long organizationId = authcToken.getOrganiaztionId();
			String isValid = "Y";
			SysUsers cond = new SysUsers(loginName, passWord, organizationId, isValid);
			List<SysUsers> list = sysUserService.findByCond(cond);
			
			currentUser = list.size() == 1?list.get(0):null;
			
			if(currentUser != null){
				Long userId = currentUser.getUserId();
				
				/*
				 * 通过用户id查询用户角色关系表，获得当前用户所拥有的全部角色
				 */
				List<Roles> roles = rolesService.findRolesByUserId(userId, organizationId);
				if(roles != null && roles.size() > 0){
					//保存用户对于的角色
					currentUser.setRoles(roles);
					
					Map<Long,Roles> roleMap = new HashMap<Long, Roles>();//查询用户所包含的角色
					Long[] roleIds = new Long[roles.size()];
					for(int i = 0,len = roles.size();i < len;i++){
						Roles role = roles.get(i);
						roleMap.put(role.getRoleId(), role);
						roleIds[i] = role.getRoleId();
					}
					
					//保存角色对应的权限
					List<RoleResource> roleResources = rolesService.findRoleResourcesByRoleIds(roleIds, organizationId);
					if(roleResources != null && roleResources.size() > 0){
						for(RoleResource rs : roleResources){
							Long roleId = rs.getRoleId();
							roleMap.get(roleId).getRoleResourceCodes().add(rs);
						}
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			StringUtil.writeStackTraceToLog(logger, e);
			throw new CustomException("queryPermissions 出错!"+e.getMessage());
		}
		
		return currentUser;
	}

}
