package com.chengzhi.scdp.system.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.controller.BaseController;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.service.IRolesService;
import com.chengzhi.scdp.system.service.ISysUserService;
import com.chengzhi.scdp.tools.DateTimeUtil;
import com.chengzhi.scdp.tools.ObjectUtil;
import com.chengzhi.scdp.tools.StringUtil;

/**
 * 系统登录
 * @author beisi
 *
 */
@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IRolesService rolesService;
	
	/**
     * 登陆失败的处理
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request,HttpSession session) {
        //获取错误信息
        String exceptionClassName = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(exceptionClassName != null){
            if(exceptionClassName.equals("org.apache.shiro.authc.UnknownAccountException")){
                request.setAttribute("errorMsg", "用户名或密码错误!");
            }else if(exceptionClassName.equals("IncorrectCaptchaException")) {
                request.setAttribute("errorMsg", "验证码或组织账套错误！");
            }else if(exceptionClassName.equals("OrganizationException")){
            	request.setAttribute("errorMsg", "账套不允许为空!");
            }
        }
        return "/login";
    }
	
	/**
     * 登陆成功后进入这里，获取用户信息和菜单权限
     * @param model
     * @param session
     * @return
	 * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value="/index",method = {RequestMethod.GET})
    public String index(Model model,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        SysUsers user = Constants.getCurrentSysUser();
        if(user == null){
        	return "redirect:/";
        }else{
			model.addAttribute("menu", getCurrentMenus());
        	setCookie(response);
            return "/main";
        }
        
    }
	
	/**
	 * 安全退出
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout",method = {RequestMethod.GET})
	public String logout(HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:/";
	}
	
	@RequestMapping(value="/userInformation",method = {RequestMethod.POST})
	public String persionalConfig(HttpServletRequest request, String type){
		SysUsers currentUser = Constants.getCurrentSysUser();
		request.setAttribute("user", sysUserService.findUserById(currentUser.getUserId()));
		request.setAttribute("type", type);
		return "init/userInformation";
	}
	
	@RequestMapping(value="/updateSysUser", method = {RequestMethod.POST})
	public String updateSysUser(SysUsers cond){
		if(cond.getUserId() != null){
			SysUsers user = sysUserService.findUserById(cond.getUserId());
			ObjectUtil.copyPropertiesIgnoreNull(cond, user);
			user.setLastUpdateDate(DateTimeUtil.getLastUpdateDate());
			sysUserService.update(user);
		}
		return "forward:/userInformation?type=personal";
	}
	
	@RequestMapping(value = "/changePassword", method = {RequestMethod.POST}, produces = {"text/html;charset=UTF-8;"})
	public @ResponseBody String changePassword(HttpServletRequest request) throws CustomException{
		String oldPw = request.getParameter("loginPassword");
		String newPw = request.getParameter("confirmPassword");
		String reNewPw = request.getParameter("reConfirmPassword");
		if(StringUtil.isNullOrEmpty(oldPw) || StringUtil.isNullOrEmpty(newPw)
				|| StringUtil.isNullOrEmpty(reNewPw))
			return "输入密码不允许为空！";
		
		if(!newPw.equals(reNewPw))
			return "前后两次输入密码不一致，请检查！";
		
		if(oldPw.equals(newPw))
			return "新密码不允许与旧密码相同！";
		
		SysUsers user = Constants.getCurrentSysUser();
		if(!oldPw.equals(StringUtil.decrypt(user.getLoginPassword())))
			return "输入原有密码错误！";
		
		sysUserService.updateUserPassword(user, newPw);
		return "success";
	}
	
	/**
	 * 验证码校验
	 * @param verityCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkKaptcha",method = {RequestMethod.POST})
	public @ResponseBody String checkCode(String verityCode,HttpServletRequest request){
		String sessionCode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		JSONObject result = new JSONObject();
		result.put("msg", verityCode.equalsIgnoreCase(sessionCode)?"1":"-1");
		return result.toString();
	}
	
}
