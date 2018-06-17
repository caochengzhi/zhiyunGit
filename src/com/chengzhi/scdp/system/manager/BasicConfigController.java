package com.chengzhi.scdp.system.manager;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chengzhi.scdp.database.controller.BaseController;
import com.chengzhi.scdp.system.dao.Roles;
import com.chengzhi.scdp.system.dao.SysUsers;
import com.chengzhi.scdp.system.service.IRolesService;
import com.chengzhi.scdp.system.service.ISysUserService;

/**
 * 获取基础配置信息
 * @author beisi
 *
 */
@RestController
@RequestMapping("/baseConig")
public class BasicConfigController extends BaseController{
	@Autowired
	private IRolesService rolesService;
	@Autowired
	private ISysUserService sysUserServices;
	
	/**
	 * 获取角色名列表，每次从数据库取，不取缓存
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getRolesNameList", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8"})
	public @ResponseBody String getRolesNameList(){
		
		JSONArray array = new JSONArray();
		List<Roles> result = rolesService.findByCond(new Roles());
		if(result != null && result.size() > 0){
			for(Roles role : result){
				JSONObject obj = new JSONObject();
				obj.put("id", role.getRoleId());
				obj.put("text", role.getRoleName());
				array.add(obj);
			}
		}
		return array.toString();
	}
	
	/**
	 * 获取角色名列表，每次从数据库取，不取缓存
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getUsersNameList", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8"})
	public @ResponseBody String getUsersNameList(){
		JSONArray array = new JSONArray();
		List<SysUsers> result = sysUserServices.findByCond(new SysUsers());
		if(result != null && result.size() > 0){
			for(SysUsers user : result){
				JSONObject obj = new JSONObject();
				obj.put("id", user.getUserId());
				obj.put("text", user.getUserName());
				array.add(obj);
			}
		}
		return array.toString();
	}
	
}
