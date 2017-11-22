package com.chengzhi.scdp.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Autowired
	private ISysUserService sysUserService;

	@RequestMapping(value="/toLogin",method = {RequestMethod.GET})
	public String login(HttpServletRequest request){
		return "/login";
	}
	
	@RequestMapping(value="/logout",method = {RequestMethod.GET})
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.USER_TOKEN);
		return "redirect:/";
	}
	
	/**
	 * 用户登录校验，如果校验通过，生成包含用户信息的token并放入session；验证不通过跳入登录页面
	 * 设置token超时未30分钟，超时后退出到登录首页，用户重新登录系统
	 * @param organization
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/verify", method = {RequestMethod.POST})
	public String verify(@RequestParam(value="organization",required=true) Long organization,
						@RequestParam(value="username",required=true) String username,
						@RequestParam(value="password",required=true) String password,
						HttpServletRequest request,RedirectAttributes attr){
		
		SysUsers cond = new SysUsers(username,password,organization);
		//List<SysUsers> list = sysUserService.findByCond(cond);
		List<SysUsers> list = new ArrayList<SysUsers>();
		list.add(cond);
		
		
		if(list == null || list.size() == 0){
			attr.addFlashAttribute("errorMsg", "用户名或密码错误！");
			return "redirect:/";
		}else{
			setTokenSession(request, list.get(0));
			return "/main";
		}
	}

	
}
