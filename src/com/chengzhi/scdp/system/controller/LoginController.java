package com.chengzhi.scdp.system.controller;

import javax.servlet.http.HttpServletRequest;
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
import com.chengzhi.scdp.database.controller.BaseController;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.service.ISysUserService;

/**
 * 系统登录
 * @author beisi
 *
 */
@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private ISysUserService sysUserService;
	
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
     */
    @RequestMapping(value="/index",method = {RequestMethod.GET})
    public String index(Model model,HttpServletRequest request, HttpSession session) {
        SysUsers user = Constants.getCurrentSysUser();
        System.out.println(user);
        if(user == null)
        	return "redirect:/";
        else{
        	/*session.setAttribute("userId",user.getUser_id());
            model.addAttribute("userActive", user); //将数据放置到域对象
            //菜单转化为自定义的json
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(MenuAndPower.class, "id","pId","text","url");     
            String json = JSON.toJSONString(user.getMenuAndPowers(), filter);
            model.addAttribute("menu", json);*/
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
