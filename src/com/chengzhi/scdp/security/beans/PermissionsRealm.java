package com.chengzhi.scdp.security.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.system.dao.Roles;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.service.AbstractBusinessManager;
import com.chengzhi.scdp.tools.StringUtil;

/**
 * scdp 系统认证类
 * 在 Shiro 认证与授权处理过程中，Realm 可以理解为读取用户信息、角色及权限的 DAO
 * @author beisi
 *
 */
public class PermissionsRealm extends AuthorizingRealm{
	private Logger logger = Logger.getLogger(PermissionsRealm.class);
	// 用于获取用户信息及用户权限信息的业务接口
	@Autowired
	private AbstractBusinessManager businessManager; 

	/**
	 * 用户登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("******doGetAuthenticationInfo*******");
		SysUsers sysUser = null;
		try {
			sysUser = businessManager.queryPermissions((CaptchaUsernamePasswordToken) authcToken);
		} catch (CustomException e) {
			StringUtil.writeStackTraceToLog(logger, e);
			logger.error("doGetAuthenticationInfo 出错，获取用户信息出错，"+e.getMessage());
		}
		
		if (sysUser != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser, sysUser.getLoginPassword(), getName());
            return authenticationInfo;
        }
        return null;
		
	}
	
	/**
	 * 用户授权认证
     * 对当前路径予权限和角色,配置文件里面已经配置了缓存管理器，因此每次页面打开，后台只会读取一次用户角色+权限，后续都会用缓存，重新登陆后缓存自动清空
     * 当用户调用Security.getSubject().isPermitted(permissions)，Security.getSubject().hasRole(roleIdentifier)等方法时，
     * 会触发doGetAuthorizationInfo此方法，以及jsp中调用role权限标签也会触发此方法
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection PrincipalCollection) {
		logger.info("******doGetAuthorizationInfo*******");
		SysUsers user = Constants.getCurrentSysUser();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        simpleAuthorInfo.addRoles(getRoles(user));
        simpleAuthorInfo.addStringPermissions(user.getButtonGroups());
        return simpleAuthorInfo;
	}
	
	/**
     * 获取角色集合，string存放的角色名称
     * @param user
     * @return
     */    
	private List<String> getRoles(SysUsers user) {
        List<String> roles = new ArrayList<String>();
        if(user.getRoles() != null){
        	for (Roles role : user.getRoles()) {
                roles.add(role.getRoleCode());
            }
        }
        return roles;
    }

}
