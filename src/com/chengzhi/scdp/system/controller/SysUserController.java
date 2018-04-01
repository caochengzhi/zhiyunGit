package com.chengzhi.scdp.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengzhi.scdp.database.controller.BaseController;
import com.chengzhi.scdp.database.dao.PageList;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.service.ISysUserService;
import com.chengzhi.scdp.tools.DateTimeUtil;
import com.chengzhi.scdp.tools.JsonUtil;
import com.chengzhi.scdp.tools.ObjectUtil;
import com.chengzhi.scdp.tools.StringUtil;

/**
 * 用户管理
 * @author beisi
 *
 */
@Controller
@RequestMapping("/userManager")
public class SysUserController extends BaseController{
	@Autowired
	private ISysUserService sysUserService;

	@RequestMapping(value = "/toSearch", method = {RequestMethod.GET})
	public String toSearch(){
		return "system/userSearch";
	}
	
	/**
	 * 用户查询
	 * @param user
	 * @param pageNumber
	 * @param pageSize
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	@RequestMapping(value = "/search",method = RequestMethod.POST)
	public @ResponseBody String searchUsers(SysUsers user,int pageNumber,int pageSize,String sortName, String sortOrder){
		JSONObject result = new JSONObject();
		PageList<SysUsers> pageCond = sysUserService.findByCond(user, sortName, sortOrder, pageNumber, pageSize);
		int count = pageCond.getTotal();
		List<SysUsers> list = pageCond.getList();
		result.put("total", count);
		result.put("rows", JsonUtil.formatListWithDate(list));
		return result.toString();
	}
	
	/**
	 * 用户新增或修改 
	 * @param request
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/toModify", method = {RequestMethod.POST})
	public String toModify(HttpServletRequest request,@RequestParam Long userId){
		if(userId != null){
			SysUsers user = sysUserService.findUserById(userId);
			request.setAttribute("user", user);
		}
		return "system/userModify";
	}
	
	@RequestMapping(value="/cancalUser",method={RequestMethod.POST},produces={"text/html;charset=UTF-8;","application/json;"})
	public @ResponseBody String cancalUser(@RequestParam(value="userId",required=true) Long userId){
		String msg = null;
		try{
			SysUsers user = sysUserService.findUserById(userId);
			user.setIsValid('N');
			sysUserService.update(user);
			msg = "用户注销成功";
		}catch(Exception e){
			msg = "用户注销失败,"+e.getMessage();
		}
		return msg;
	}
	
	/**
	 * 新增和更新用户
	 */
	@RequestMapping(value="/saveUser",method={RequestMethod.POST})
	public String saveUser(SysUsers cond,Long userId){
		if(cond.getUserId() != null){
			SysUsers user = sysUserService.findUserById(cond.getUserId());
			ObjectUtil.copyPropertiesIgnoreNull(user, cond);
			cond.setLastUpdateDate(DateTimeUtil.getLastUpdateDate());
			sysUserService.update(user);
		}else{
			cond.setLastUpdateDate(DateTimeUtil.getLastUpdateDate());
			cond.setCreater(getUserName());
			cond.setLoginPassword(StringUtil.encrypt(cond.getLoginPassword()));
			sysUserService.save(cond);
		}
		return "system/userSearch";
	}
}
