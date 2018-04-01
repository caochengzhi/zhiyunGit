package com.chengzhi.scdp.system.controller;

import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chengzhi.scdp.database.controller.BaseController;
import com.chengzhi.scdp.system.service.ISysDictionaryService;
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

	@RequestMapping(value = "/toSearch", method = {RequestMethod.GET})
	public String toSearch(){
		return "system/dictionarySearch";
	}
	
	@RequestMapping(value = "/getDictSelect", method = {RequestMethod.POST}, produces={"text/html;charset=UTF-8"})
	public @ResponseBody String getDictSelect(String dictType){
		System.out.println(this.getCache()+"**********"+dictType);
		
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
//		JSONArray array = new JSONArray();
//		JSONObject json = new JSONObject();
//		json.put("id", 1);
//		json.put("text", "ljiong.com(老囧博客)");
//		
//		JSONObject json1 = new JSONObject();
//		json1.put("id", 2);
//		json1.put("text", "Ants(蚂蚁)");
//		
//		array.add(json1);
//		array.add(json);
		return array.toString();
	}
	
	
	
}
