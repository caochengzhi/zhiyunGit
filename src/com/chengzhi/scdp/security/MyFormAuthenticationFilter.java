package com.chengzhi.scdp.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.security.beans.CaptchaUsernamePasswordToken;
import com.chengzhi.scdp.system.dao.SysUsers;

/**
 * 扩展 FormAuthenticationFilter类，首先覆盖 createToken方法，以便获取CaptchaUsernamePasswordToken实例；
 * 然后增加验证码校验方法doCaptchaValidate；
 * 最后覆盖Shiro的认证方法 xecuteLogin在原表单认证逻辑处理之前进行验证码校验。
 * @author beisi
 *
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{
	private static Logger logger = Logger.getLogger(MyFormAuthenticationFilter.class);
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
     * 每次被authc拦截的url都会到这里来，这里用来处理 不注销之前已登录用户下，再次登录
     */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,ServletResponse response, Object mappedValue) {
		logger.info("*************isAccessAllowed***************");
		if(isLoginRequest(request, response)){
			
			if(isLoginSubmission(request, response)){
				//本次用户登陆账号
                String account = this.getUsername(request);
                
                //之前登陆的用户
                Subject subject = this.getSubject(request, response);
                SysUsers user = (SysUsers) subject.getPrincipal();
                
                //如果两次登陆的用户一样，则先退出之前登陆的用户
                if (account != null && user != null && !account.equals(user.getLoginName())){
                    //注销之前登录用户
                    subject.logout();
                }
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

	/**
     * 用户自定义验证方法，这里用来做验证码及账套的验证
     * 此方法第一次登录会进来，执行executeLogin方法前执行，验证通过返回false，验证不通过返回true
     * 验证失败，不会去执行executeLogin方法
     */
	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
		logger.info("*************onAccessDenied***************");
		
		HttpServletRequest httpServletRequest=(HttpServletRequest) request;
		
		//从session获取验证码，正确的验证码
        HttpSession session=httpServletRequest.getSession();
        String validate =(String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        
        //获取输入的验证码,验证失败，设置错误信息
        String myValidate = getCaptcha(request); 
        if (validate != null && myValidate != null && !validate.equalsIgnoreCase(myValidate)) {
            httpServletRequest.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "IncorrectCaptchaException");
            //拒绝访问
            return true;
        }
        
        //组织号为空，验证失败，设置错误信息
        Long organizationId = getOrganizationId(request);
        if(organizationId == null){
        	httpServletRequest.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, "OrganizationException");
            //拒绝访问
            return true;
        }
        	
        return super.onAccessDenied(request, response);
	}

	/**
	 * 登录认证
	 */
	@Override
	protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception {
		logger.info("*************executeLogin***************");
		
		CaptchaUsernamePasswordToken token = createToken(request, response);
		try {
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
	
}
