package com.chengzhi.scdp.system.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.security.beans.CaptchaUsernamePasswordToken;
import com.chengzhi.scdp.system.dao.Resources;
import com.chengzhi.scdp.system.dao.RoleResource;
import com.chengzhi.scdp.system.dao.Roles;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.dao.UserRole;
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
			SysUsers cond = new SysUsers(loginName, passWord, organizationId);
			List<SysUsers> list = sysUserService.findByCond(cond);
			
			currentUser = list.size() == 1?list.get(0):null;
			
			if(currentUser != null){
				Long userId = currentUser.getUserId();
				
				/*
				 * 通过用户id查询用户角色关系表，获得当前用户所拥有的全部角色
				 */
				List<UserRole> userRoles = sysUserService.getUserRolesByUserId(userId, organizationId);
				if(userRoles != null && userRoles.size() > 0){
					
					Long[] roleIds = new Long[userRoles.size()];
					for(int i = 0,len = userRoles.size();i < len;i++){
						roleIds[i] = userRoles.get(i).getRoleId();
					}
					
					//通过角色id获取角色对象
					if(roleIds.length > 0){
						Map<Long,Roles> rmap = new HashMap<Long, Roles>();//查询用户所包含的角色
						List<Roles> roles = rolesService.findRolesByIds(roleIds, organizationId);
						currentUser.setRoles(roles);
						for(Roles role : roles){
							rmap.put(role.getRoleId(), role);
						}
						
						/*
						 * 通过角色id查询角色所拥有的全部权限
						 * 获取角色与资源对应关系，为取资源权限做准备
						 */
						Map<Long,List<Long>> map = new HashMap<Long, List<Long>>();//角色与权限对应关系
						List<RoleResource> rrs = rolesService.findRoleResourcesByRoleIds(roleIds, organizationId);
						for(RoleResource rr : rrs){
							Long roleId = rr.getRoleId();
							Long resourceId = rr.getResourceId();
							
							List<Long> lt = null;
							if(map.containsKey(roleId))
								lt = map.get(roleId);
							else
								lt = new ArrayList<Long>();
							
							lt.add(resourceId);
							map.put(roleId, lt);
						}
						
						for(Iterator<Long> it = map.keySet().iterator();it.hasNext();){
							Long roleId = it.next();
							List<Long> l = map.get(roleId);
							
							Roles role = rmap.get(roleId);
							
							Long[] resourceIds = new Long[l.size()];
							l.toArray(resourceIds);
							List<Resources> lr = permissionService.getPermissionsByIds(resourceIds, organizationId);
							
							role.setPermissions(lr);//角色拥有的权限
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
