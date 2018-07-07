package com.chengzhi.scdp.system.service.imp;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.ISysUserDao;
import com.chengzhi.scdp.system.dao.IUserRoleDao;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.dao.UserRole;
import com.chengzhi.scdp.system.service.IRolesService;
import com.chengzhi.scdp.system.service.ISysUserService;
import com.chengzhi.scdp.tools.DateTimeUtil;
import com.chengzhi.scdp.tools.ObjectUtil;
import com.chengzhi.scdp.tools.StringUtil;

/**
 * 系统用户服务类
 * @author beisi
 *
 */
@Service
public class SysUserServiceImp extends BaseServiceImp<SysUsers, Long> implements ISysUserService {
	protected Logger logger = Logger.getLogger(this.getClass());

	private ISysUserDao sysUserDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IRolesService rolesService;
	
	@Autowired
	@Qualifier("sysUserDao")
	@Override
	public void setBaseDao(IBaseDao<SysUsers, Long> baseDao) {
		this.baseDao = baseDao;
		sysUserDao = (ISysUserDao)baseDao;
	}

	@Override
	public SysUsers findUserById(Long userId) {
		return sysUserDao.findUserById(userId);
	}

	@Override
	public void saveUserRoles(List<UserRole> userRoles) {
		if(userRoles != null && userRoles.size() > 0){
			for(UserRole ur : userRoles){
				userRoleDao.save(ur);
			}
		}
	}
	
	/**
	 * 保存或更新用户，包含用户对角色的保存
	 */
	@Override
	public void saveOrUpdateSysUser(SysUsers cond, Long[] roleIds)throws CustomException{
		SysUsers user = null;
		if(cond.getUserId() == null)
		{
			if(StringUtil.isNullOrEmpty(cond.getLoginPassword()))
				throw new CustomException("保存新用户密码不允许为空");
				
			user = new SysUsers();
			user.setCreater(Constants.getCurrentSysUser().getLoginName());
			user.setLoginPassword(StringUtil.encrypt(cond.getLoginPassword()));
			ObjectUtil.copyPropertiesIgnoreNull(cond, user);
		}
		else
		{
			user = findUserById(cond.getUserId());
			ObjectUtil.copyPropertiesIgnoreNull(cond, user);
		}
		user.setLastUpdateDate(DateTimeUtil.getLastUpdateDate());
		user.setLastUpdateBy(Constants.getCurrentSysUser().getLoginName());
		sysUserDao.saveOrUpdate(user);
		
		//保存用户对应的角色信息
		if(roleIds != null && roleIds.length > 0){
			Long userId = user.getUserId();
			rolesService.updateWithSql("delete from user_role where user_id = "+userId+" and organization_id = "+Constants.getCurrentSysUser().getOrganizationId());
			for(Long roleId : roleIds){
				UserRole ur = new UserRole(userId,roleId);
				userRoleDao.save(ur);
			}
		}
	}

	/**
	 * 获取当前用户下所包含的角色与用户关系
	 */
	@Override
	public List<UserRole> getUserRolesByUserId(Long userId, Long organizationId) {
		return userRoleDao.getUserRolesByUserId(userId, organizationId);
	}

	@Override
	public void updateUserPassword(SysUsers user,String newPassword) throws CustomException {
		String sql = "update sys_users set login_password ='"+StringUtil.encrypt(newPassword)+"' where user_id="+user.getUserId();
		sysUserDao.updateWithSql(sql);
		
	}

	/**
	 * 查询指定角色下包含的所有用户
	 */
	@Override
	public List<SysUsers> findSysUsersByRoleId(Long roleId) {
		return sysUserDao.findSysUsersByRoleId(roleId);
	}
	
}
