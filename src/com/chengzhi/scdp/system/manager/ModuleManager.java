package com.chengzhi.scdp.system.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.chengzhi.scdp.common.ServletInit;
import com.chengzhi.scdp.tools.StringUtil;

public class ModuleManager {
	private Logger logger = Logger.getLogger(this.getClass());
	private Document doc;
	
	//总模块，包含主moduel和子module
	private HashMap<String,Element> modules;
	
	//操作，action对应的button
	private HashMap<String,HashMap<String,Node>> moduleAction;
	//modules集合
	private HashMap<String,List<Node>> childModules;
	//action url集合
	private HashMap<String,List<Node>> childActions;
	public static List<String[]> idAndTitle = new ArrayList<String[]>();
	
	public static ModuleManager instance;
	
	private ModuleManager(){
		modules = new HashMap<String,Element>();
		moduleAction = new HashMap<String,HashMap<String,Node>>();
		childModules = new HashMap<String,List<Node>>();
		childActions = new HashMap<String,List<Node>>();
	}

	public static synchronized ModuleManager getInstance(){
		if(instance == null)
			instance = new ModuleManager();
		return instance;
	}
	
	public void reload(File file)throws Exception{
		DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
		doc = df.newDocumentBuilder().parse(file);
		initModules();
	}
	
	/**
	 * 将权限信息映射到HashMap
	 */
	private void initModules(){
		modules.clear();
		moduleAction.clear();
		childModules.clear();
		childActions.clear();
		NodeList nodes = doc.getElementsByTagName("module");
		for(int i = 0,len = nodes.getLength(); i < len;i++){
			Element el = (Element)nodes.item(i);
			String id = el.getAttribute("id");
			modules.put(id, el);
			
			//模块中的action
			HashMap<String,Node> actions = new HashMap<String,Node>(10);
			moduleAction.put(id, actions);
			List<Node> listModules = childModules.get(id);
			List<Node> listActions = childActions.get(id);
			
			NodeList children = el.getChildNodes();
			for(int j = 0,jlen = children.getLength(); j < jlen;j++){
				Node node = children.item(j);
				if("module".equals(node.getNodeName())){
					if(listModules == null){
						listModules = new ArrayList<Node>();
						childModules.put(id, listModules);
					}
					listModules.add(node);
				}else if("action".equals(node.getNodeName())){
					if(listActions == null){
						listActions = new ArrayList<Node>();
						childActions.put(id, listActions);
					}
					listActions.add(node);
					actions.put(((Element)node).getAttribute("name"), node);
				}
			}
			
			//加载id="*/* title=*"到idAndTile
			try{
				String[] idTitleArray = new String[2];
				if(!StringUtil.isNullOrEmpty(id) && id.indexOf("/") != -1){
					idTitleArray[0] = id;
					idTitleArray[1] = el.getAttribute("title");
					idAndTitle.add(idTitleArray);
				}
				
			}catch(Exception e){
				e.printStackTrace();
				logger.error("*****initModules****"+e.getMessage());
			}
		}
	}
	
	public List<Node> getChildModules(String id){
		return childModules.get(id);
	}
	
	public List<Node> getChildActions(String id) {
		return childActions.get(id);
	}
	
	/**
	 * 获取父节点名称，如果是根目录root则无法没有父节点
	 * @param id
	 * @return
	 */
	public String getParentId(String id){
		if("root".equals(id))
			return null;
		
		Element el = modules.get(id);
		Element parent = (Element)el.getParentNode();
		return parent.getAttribute("id");
	}
	
	public boolean ifHasChildAction(String mid){
		return childActions.containsKey(mid);
	}
	
	public boolean ifHasChildModule(String mid){
		return childModules.containsKey(mid);
	}
	
	/**
	 * 判断module是否存在
	 * @return
	 */
	public boolean hasModule(String id){
		return modules.containsKey(id);
		
	}
	
	/**
	 * 判断module中是否存在指定name的action
	 * @param mid
	 * @param name
	 * @return
	 */
	public boolean hasActioin(String mid,String name){
		HashMap<String,Node> map = moduleAction.get(mid);
		if(map == null) return false;
		return map.containsKey(name);
	}
	
	/**
	 * 获取action title
	 * @param mid
	 * @param name
	 * @return
	 */
	public String getActionTitle(String mid,String name){
		Element e = getAction(mid,name);
		if(e == null) return null;
		String s = e.getAttribute("title");
		return s.length() == 0?e.getAttribute("button"):s;
	}
	
	public Element getAction(String mid,String name){
		HashMap<String,Node> map = moduleAction.get(mid);
		if(map == null) return null;
		return (Element)map.get(name);
	}
	
	public Element getModule(String id) {
		return modules.get(id);
	}

	/**
	 * 获取module的title
	 * @return
	 */
	public String getModuleTitle(String mid){
		Element e = getModule(mid);
		return e == null?null:e.getAttribute("title");
	}
	
	/**
	 * 获取所有的模块名称
	 * @return
	 */
	public String[] getModuleNames(){
		NodeList list = doc.getElementsByTagName("module");
		String[] names = new String[list.getLength()];
		for(int i = 0,len = list.getLength();i < len;i++){
			names[i] = ((Element)list.item(i)).getAttribute("title");
		}
		return names;
	}
	
	/**
	 * 获取所有模块id对应的模块名称
	 */
	public Map<String,String> getModuleIds(){
		NodeList list = doc.getElementsByTagName("module");
		Map<String,String> map = new LinkedHashMap<String, String>();
		for(int i = 0,len = list.getLength();i < len;i++){
			Element el = (Element)list.item(i);
			map.put(el.getAttribute("id"), el.getAttribute("title"));
		}
		return map;
	}
	
	
	public JSONArray getAllResource(){

		JSONArray array = new JSONArray();
		Map<String,String> map = getModuleIds();
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String subId = it.next();
			String parentId = getParentId(subId);
			if(parentId == null) continue;
			
			String subName = map.get(subId);
			
			JSONObject obj = new JSONObject();
			obj.put("id", subId);
			obj.put("pid", parentId);
			obj.put("title", subName);
			obj.put("module", subId);
			if(!"root".equals(parentId)){
				obj.put("url", subId+"/toSearch");
				obj.put("code", parentId+":"+subId);
			}else{
				obj.put("code", parentId);
			}
			obj.put("type", "menu");
			obj.put("status", 1);
			obj.put("open", true);
			
			array.add(obj);
			
			HashMap<String,Node> e = moduleAction.get(subId);
			for(Iterator<String> subIt = e.keySet().iterator();subIt.hasNext();){
				String subKey = subIt.next();
				String title = getActionTitle(subId, subKey);
				JSONObject subObj = new JSONObject();
				subObj.put("id", subKey);
				subObj.put("pid", subId);
				subObj.put("title", title);
				subObj.put("module", subId);
				subObj.put("type", "button");
				subObj.put("status", 1);
				subObj.put("code", parentId+":"+subId+":"+subKey);
				subObj.put("open", true);
				
				array.add(subObj);
			}
			
		}
		System.out.println(array.toString());
		return array;
	
	}
	
	private static void setChecked(JSONObject obj,List<String> resource,StringBuilder sBuilder){
		obj.put("code", sBuilder.toString());
		if(resource != null && resource.size() > 0){
			if(resource.contains(sBuilder.toString()))
				obj.put("checked", true);
		}
		sBuilder.setLength(0);
	}
	
	/**
	 * 查询角色对应的权限列表
	 * @param resource
	 * @return
	 */
	public String getRoleViews(List<String> resource){

		JSONArray array = new JSONArray();
		Map<String,String> map = getModuleIds();
		StringBuilder sBuilder = new StringBuilder();
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String subId = it.next();
			String parentId = getParentId(subId);
			if(parentId == null) continue;
			
			sBuilder.append(parentId).append(":").append(subId);
			if(resource.contains(sBuilder.toString())){
				String subName = map.get(subId);
				JSONObject obj = new JSONObject();
				obj.put("id", subId);
				obj.put("pId", parentId);
				obj.put("name", subName);
				obj.put("open", true);
				array.add(obj);
			}
			sBuilder.setLength(0);
			
			HashMap<String,Node> e = moduleAction.get(subId);
			for(Iterator<String> subIt = e.keySet().iterator();subIt.hasNext();){
				String subKey = subIt.next();
				String title = getActionTitle(subId, subKey);
				
				if(resource != null && resource.size() > 0){
					sBuilder.append(parentId).append(":").append(subId).append(":").append(subKey);
					if(resource.contains(sBuilder.toString())){
						JSONObject subObj = new JSONObject();
						subObj.put("id", subKey);
						subObj.put("pId", subId);
						subObj.put("name", title);
						
						array.add(subObj);
					}
						
				}
				sBuilder.setLength(0);
			}
			
		}
		return array.toString();
	
	}
	
	/**
	 * 获取对应角色的权限菜单树,供编辑角色权限列表使用
	 * @return
	 */
	public String getRolesAclTree(List<String> resource){
		JSONArray array = new JSONArray();
		Map<String,String> map = getModuleIds();
		StringBuilder sBuilder = new StringBuilder();
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			String subId = it.next();
			String parentId = getParentId(subId);
			if(parentId == null) continue;
			
			String subName = map.get(subId);
			
			JSONObject obj = new JSONObject();
			obj.put("id", subId);
			obj.put("pId", parentId);
			obj.put("name", subName);
			
			sBuilder.append(parentId).append(":").append(subId);
			setChecked(obj, resource, sBuilder);
			
			array.add(obj);
			
			HashMap<String,Node> e = moduleAction.get(subId);
			for(Iterator<String> subIt = e.keySet().iterator();subIt.hasNext();){
				String subKey = subIt.next();
				String title = getActionTitle(subId, subKey);
				
				JSONObject subObj = new JSONObject();
				subObj.put("id", subKey);
				subObj.put("pId", subId);
				subObj.put("name", title);
				
				sBuilder.append(parentId).append(":").append(subId).append(":").append(subKey);
				setChecked(subObj, resource, sBuilder);
				
				array.add(subObj);
			}
			
		}
		return array.toString();
	}
	
	public static void main(String[] args) throws Exception{
		String path = ServletInit.class.getResource("/").getPath();
		path = path.substring(0, path.indexOf("classes"))+"module.xml";
		File f = new File(path);
		ModuleManager manager = ModuleManager.getInstance();
		try {
			manager.reload(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		manager.getAllResource();
		
	}
	
}
