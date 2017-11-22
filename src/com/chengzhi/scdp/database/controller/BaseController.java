package com.chengzhi.scdp.database.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.JavaWebToken;
import com.chengzhi.scdp.common.ThreadLocalFactory;
import com.chengzhi.scdp.system.dao.SysUsers;

public class BaseController {

	public static String getUserName(){
		Claims claim = ThreadLocalFactory.getCurrentUserToken();
		String userName = (String)claim.get(Constants.USERNAME);
		return userName;
	}
	
	protected void setTokenSession(HttpServletRequest request, SysUsers user) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(Constants.USERNAME, user.getUserName());
		claims.put(Constants.ORGANIZATIONID, user.getOrganizationId());
		String userToken = JavaWebToken.generateToken(claims,null);
		request.getSession().setAttribute(Constants.USER_TOKEN, userToken);
	}
}
