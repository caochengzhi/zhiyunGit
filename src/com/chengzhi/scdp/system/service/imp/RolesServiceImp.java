package com.chengzhi.scdp.system.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.IRoleResourceDao;
import com.chengzhi.scdp.system.dao.IRolesDao;
import com.chengzhi.scdp.system.dao.IUserRoleDao;
import com.chengzhi.scdp.system.dao.RoleResource;
import com.chengzhi.scdp.system.dao.Roles;
import com.chengzhi.scdp.system.dao.UserRole;
import com.chengzhi.scdp.system.service.IRolesService;
import com.chengzhi.scdp.tools.StringUtil;

/**
 * 系统用户服务类
 * @author beisi
 *
 */
@Service
public class RolesServiceImp extends BaseServiceImp<Roles, Long> implements IRolesService {
	protected Logger logger = Logger.getLogger(this.getClass());

	private IRolesDao rolesDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IRoleResourceDao userResourceDao;
	private Pattern SPLIT = Pattern.compile(":");
	
	@Autowired
	@Qualifier("rolesDao")
	@Override
	public void setBaseDao(IBaseDao<Roles, Long> baseDao) {
		this.baseDao = baseDao;
		rolesDao = (IRolesDao)baseDao;
	}

	/**
	 * 根据id查询角色
	 */
	@Override
	public Roles findRoleById(Long roleId){
		return rolesDao.findRoleById(roleId);
	}

	@Override
	public List<Roles> findRolesByIds(Long[] roleIds) {
		return rolesDao.findRolesByIds(roleIds, null);
	}

	@Override
	public List<Roles> findRolesByIds(Long[] roleIds, Long organizationId) {
		return rolesDao.findRolesByIds(roleIds, organizationId);
	}

	@Override
	public List<RoleResource> findRoleResourcesByRoleIds(Long[] roleIds, Long organizationId) {
		RoleResource cond = new RoleResource();
		cond.setRoleIdIn(roleIds);
		cond.setOrganizationId(organizationId);
		return userResourceDao.findByCond(cond);
	}

	/**
	 * 获取角色对应的权限编码
	 * @return
	 */
	@Override
	public List<String> getRolesOfCodes(Long[] roleIds) {
		List<String> sourceCodes = new ArrayList<String>();
		if(roleIds != null && roleIds.length > 0)
		{
			List<RoleResource> list = findRoleResourcesByRoleIds(roleIds, null);
			for(RoleResource obj : list){
				String resourceCode = obj.getResourceCode();
				//此处做重复判断，是因为一个用户可能包含多个不同的角色，而不同的角色对应的权限会有重复的
				if(!sourceCodes.contains(resourceCode))
					sourceCodes.add(resourceCode);
			}
		}
		return sourceCodes;
	}

	/**
	 * 查询用户所拥有的角色
	 */
	@Override
	public List<Roles> findRolesByUserId(Long userId, Long organizationId) {
		return rolesDao.findRolesByUserId(userId, organizationId);
	}
	
	@Override
	public List<Roles> findRolesByUserId(Long userId) {
		return this.findRolesByUserId(userId, null);
	}

	/**
	 * 操作角色及角色对应的权限
	 * 当新增或修改时，可以对角色进行操作
	 * 当新增或更新时，可以对权限进行操作
	 */
	@Override
	public void saveRoleWithPermission(Roles role, String operatorType)throws CustomException{
		//1、保存或修改角色
		if(Constants.Operator.ADD.equals(operatorType) || Constants.Operator.MODIFY.equals(operatorType))
		{
			saveOrUpdate(role);
		}
		
		//2、新增或更新角色权限
		if(Constants.Operator.ADD.equals(operatorType) || Constants.Operator.UPDATE.equals(operatorType))
		{
			Long roleId = role.getRoleId();
			//先删除角色对应的所有历史权限
			userResourceDao.updateWithSql("delete from role_resource where role_id = "+roleId+" and organization_id = "+Constants.getCurrentSysUser().getOrganizationId());
			//再保存新设置的权限
			if(!StringUtil.isNullOrEmpty(role.getResourceCodes()))
			{
				List<RoleResource> array = new ArrayList<RoleResource>();
				String[] codes = role.getResourceCodes().split(",");
				for(String code : codes){
					RoleResource obj = new RoleResource();
					obj.setRoleId(roleId);
					obj.setResourceCode(code);
					int length = SPLIT.split(code).length;
					if(length == 2)
					{
						if(code.startsWith("root"))
							obj.setActionType(Constants.MODEL);
						else
							obj.setActionType(Constants.MENU);
						
					}
					else if(length == 3)
					{
						obj.setActionType(Constants.BUTTON);
					}
					array.add(obj);
				}
				userResourceDao.saveList(array);
			}
		}
	}

	/**
	 * 更新指定角色所包含的用户列表
	 */
	@Override
	public void updateRoleOfUsers(Long roleId, Long[] userIds) throws CustomException {
		//先删除角色对应的所有历史权限
		userResourceDao.updateWithSql("delete from user_role where role_id = "+roleId+" and organization_id = "+Constants.getCurrentSysUser().getOrganizationId());
		
		//再保存新设置的权限
		if(userIds != null){
			for(Long userId : userIds){
				UserRole ur = new UserRole(userId,roleId);
				userRoleDao.save(ur);
			}
		}
	}

}
