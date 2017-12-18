package com.chengzhi.scdp.security.beans;

import java.util.Set;

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

import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.service.AbstractBusinessManager;

/**
 * scdp 系统认证类
 * 在 Shiro 认证与授权处理过程中，Realm 可以理解为读取用户信息、角色及权限的 DAO
 * @author beisi
 *
 */
public class CustomRealm extends AuthorizingRealm{
	private Logger logger = Logger.getLogger(CustomRealm.class);
	// 用于获取用户信息及用户权限信息的业务接口
	@Autowired
	private AbstractBusinessManager businessManager; 


	/**
	 * 用户登陆认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("******用户登陆认证*******"+authcToken.toString());
		SysUsers sysUser = businessManager.queryPermissions((CaptchaUsernamePasswordToken) authcToken);
		
		if (sysUser != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser.getLoginName(), sysUser.getLoginPassword(), getName());
            return authenticationInfo;
        }
        return null;
		
	}
	
	/**
	 * 用户授权认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection princi) {
		logger.info("******用户授权认证*******"+princi.toString());
		String loginName = (String) princi.fromRealm(getName()).iterator().next(); 
		Set<String> names = princi.getRealmNames();
		for(String name : names){
			System.out.println("用户授权认证======"+name);
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(null);
		simpleAuthorizationInfo.addStringPermission(loginName);
		return simpleAuthorizationInfo;
	}

}
