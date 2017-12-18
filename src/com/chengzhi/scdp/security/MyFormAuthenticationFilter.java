package com.chengzhi.scdp.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.IncorrectCaptchaException;
import com.chengzhi.scdp.security.beans.CaptchaUsernamePasswordToken;

/**
 * 扩展 FormAuthenticationFilter类，首先覆盖 createToken方法，以便获取CaptchaUsernamePasswordToken实例；
 * 然后增加验证码校验方法doCaptchaValidate；
 * 最后覆盖Shiro的认证方法 xecuteLogin在原表单认证逻辑处理之前进行验证码校验。
 * @author beisi
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{
	
	/**
	 * 获取验证码值
	 * @param request
	 * @return
	 */
	protected String getCaptcha(ServletRequest request) { 
		return WebUtils.getCleanParam(request, Constants.DEFAULT_CAPTCHA_PARAM); 
	}
	
	/**
	 * 获取组织账套值
	 * @param request
	 * @return
	 */
	protected Long getOrganizationId(ServletRequest request) { 
		String organization =  WebUtils.getCleanParam(request, Constants.ORGANIZATIONID); 
		if(organization != null)
			return Long.parseLong(organization);
		else
			return null;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);
		try {
			doCaptchaValidate((HttpServletRequest) request, token);
			Subject subject = getSubject(request, response);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}
	
	@Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request,ServletResponse response) {
		String loginName = getUsername(request); 
		String password = getPassword(request); 
		String captcha = getCaptcha(request); 
		Long   organizationId = getOrganizationId(request);
		boolean rememberMe = isRememberMe(request); 
		return new CaptchaUsernamePasswordToken(loginName, password, organizationId, rememberMe, captcha);
	}
	
	/**
	 * 验证码和组织号校验
	 * @param request
	 * @param token
	 */
	protected void doCaptchaValidate( HttpServletRequest request,CaptchaUsernamePasswordToken token){ 
		Object captcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY); 
		if( captcha != null && !captcha.toString().equalsIgnoreCase(token.getCaptcha())){ 
			throw new IncorrectCaptchaException ("验证码错误！"); 
		}else if(token.getOrganiaztionId() == null){
			throw new IncorrectCaptchaException ("组织号为空！"); 
		}
	}

}
