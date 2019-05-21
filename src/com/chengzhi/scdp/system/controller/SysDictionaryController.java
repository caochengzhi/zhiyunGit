package com.chengzhi.scdp.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.controller.BaseController;
import com.chengzhi.scdp.database.dao.PageList;
import com.chengzhi.scdp.system.dao.SysDictDatas;
import com.chengzhi.scdp.system.dao.SysDictType;
import com.chengzhi.scdp.system.service.ISysDictTypeService;
import com.chengzhi.scdp.system.service.ISysDictionaryService;
import com.chengzhi.scdp.tools.DateTimeUtil;
import com.chengzhi.scdp.tools.JsonUtil;
import com.chengzhi.scdp.tools.ObjectUtil;
import com.chengzhi.scdp.tools.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 数据字典服务类
 * @author beisi
 *
 */
@RequestMapping("/dictManager")
@Controller
public class SysDictionaryController extends BaseController{
	@Autowired
	private ISysDictionaryService sysDictionaryService;
	@Autowired
	private ISysDictTypeService sysDictTypeService;

	/**
	 * 数据字典类型页面
	 */
	@RequestMapping(value = "/toSearch", method = {RequestMethod.GET})
	public String toSearch(){
		return "system/dictionarySearch";
	}
	
	/**
	 * 数据字典类型明细页面
	 */
	@RequestMapping(value = "/toDictDatasSearch", method = {RequestMethod.GET})
	public String toDictDatasSearch(@RequestParam Long dictId,@RequestParam String dictName, HttpServletRequest request) throws UnsupportedEncodingException{
		request.setAttribute("dictId", dictId);
		request.setAttribute("dictName",  new String(dictName.getBytes("ISO-8859-1"), "UTF-8"));
		return "system/dictionaryDatasSearch";
	}
	
	/**
	 * 保存或更新数据字典
	 * @param cond
	 * @return
	 */
	@RequestMapping(value = "/saveSysDictType", method = {RequestMethod.POST})
	public String saveSysDictType(SysDictType cond)throws CustomException{
		
		if(StringUtil.isNullOrEmpty(cond.getDictName()) || StringUtil.isNullOrEmpty(cond.getDictCode()))
			throw new CustomException("字典名称或类型不允许为空!");
		
		SysDictType sdt = null;
		
		if(cond.getDictId() != null)
			sdt = sysDictTypeService.get(SysDictType.class, cond.getDictId());
		else
			sdt = new SysDictType(getUserName(), new Date());
		
		ObjectUtil.copyPropertiesIgnoreNull(cond, sdt);
		
		sdt.setStatus(cond.getStatus() == null?"N":"Y");
		sdt.setUpdateBy(getUserName());
		sdt.setUpdateDate(DateTimeUtil.getLastUpdateDate());
		sysDictTypeService.saveOrUpdate(sdt);
		return "system/dictionarySearch";
	}
	
	/**
	 * 分页查询数据字典
	 * @param cond
	 * @param pageNumber
	 * @param pageSize
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	@RequestMapping(value = "/searchSysDictTypes", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8;"})
	public @ResponseBody String searchSysDictTypes(SysDictType cond,int pageNumber,int pageSize,String sortName, String sortOrder){
		JSONObject result = new JSONObject();
		PageList<SysDictType> pageCond = sysDictTypeService.findByCond(cond, sortName, sortOrder, pageNumber, pageSize);
		int count = pageCond.getTotal();
		List<SysDictType> list = pageCond.getList();
		result.put("total", count);
		result.put("rows", JsonUtil.formatListWithDate(list));
		return result.toString();
	}
	
	/**
	 * 查询指定数据字典对应的明细类型
	 * @return
	 */
	@RequestMapping(value = "/searchSysDictDatas",method = RequestMethod.POST)
	public @ResponseBody List<SysDictDatas> searchSysDictDatas(Long dictId){
		List<SysDictDatas> result = sysDictionaryService.findByProperty(SysDictDatas.class, "dictId", dictId);
		return result;
	}
	
	/**
	 * 保存数据字典明细数据
	 * @param dictId
	 * @return
	 */
	@RequestMapping(value = "/saveSysDictDatas", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String saveSysDictDatas(Long dictId, @RequestBody List<SysDictDatas> result){
		String msg = "保存成功!";
		try{
			sysDictionaryService.saveOrUpdateSysDictDatas(dictId, result);
		}catch(Exception e){
			msg = "保存失败,"+e.getMessage();
		}
		return msg;
	}
	
	/**
	 * 获取数据字典数据，先从ehcache缓存获取，没有再查数据库
	 * @param dictType
	 * @return
	 */
	@RequestMapping(value = "/getDictSelect", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8"})
	public @ResponseBody String getDictSelect(String dictType){
		JSONArray array = new JSONArray();
		Map<Long, String> dictMap = sysDictionaryService.getSysDictDatasByType(dictType);
		if(dictMap != null){
			for(Entry<Long, String> entry: dictMap.entrySet()){
				JSONObject obj = new JSONObject();
				obj.put("id", entry.getKey());
				obj.put("text", entry.getValue());
				array.add(obj);
			}
		}
		return array.toString();
	}
	
}
