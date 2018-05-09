package com.chengzhi.scdp.system.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.ISysDictionaryDao;
import com.chengzhi.scdp.system.dao.SysDictDatas;
import com.chengzhi.scdp.system.manager.EhCacheUtil;
import com.chengzhi.scdp.system.service.ISysDictionaryService;
import com.chengzhi.scdp.tools.ObjectUtil;
import com.chengzhi.scdp.tools.StringUtil;

/**
 * 系统用户服务类
 * @author beisi
 *
 */
@Service
public class SysDictionaryServiceImp extends BaseServiceImp<SysDictDatas, Long> implements ISysDictionaryService {
	protected Logger logger = Logger.getLogger(this.getClass());

	private ISysDictionaryDao sysDictionaryDao;
	@Autowired
	private EhCacheUtil ehcacheUtil;
	
	@Autowired
	@Qualifier("sysDictionaryDao")
	@Override
	public void setBaseDao(IBaseDao<SysDictDatas, Long> baseDao) {
		this.baseDao = baseDao;
		sysDictionaryDao = (ISysDictionaryDao)baseDao;
	}

	/**
	 * 保存或更新指定类型的数据字典
	 * 已存在的数据做更新动作，不存在的做保存动作
	 * @param dictId 数据字典类型ID
	 * @param list 数据字典类型对应的明细数据
	 */
	@Override
	public void saveOrUpdateSysDictDatas(Long dictId, List<SysDictDatas> list)throws CustomException{
		try{
			if(dictId != null && list.size() > 0){
				
				String dictType = null;
				//封装数据字典成map，方便后面调用
				List<SysDictDatas> result = sysDictionaryDao.findByProperty(SysDictDatas.class, "dictId", dictId);
				Map<Long, SysDictDatas> map = new HashMap<Long, SysDictDatas>(result.size());
				for(SysDictDatas obj : result){
					map.put(obj.getId(), obj);
					dictType = obj.getDictType();
				}
				
				for(SysDictDatas newObj : list){
					Long id = newObj.getId();
					String code = newObj.getDictDataCode();
					String codeName = newObj.getDictDataName();
					
					//字典编码和名称不允许为空，后端增加一层校验和判断
					if(StringUtil.isNullOrEmpty(codeName) || StringUtil.isNullOrEmpty(code))
						continue;
						
					if(id != null && map.get(id) != null){
						SysDictDatas dictData = map.get(id);
						ObjectUtil.copyPropertiesIgnoreNull(newObj, dictData);
						dictData.setUpdateBy(Constants.getCurrentSysUser().getLoginName());
						sysDictionaryDao.update(dictData);
					}
					else{
						newObj.setDictId(dictId);
						newObj.setCreateBy(Constants.getCurrentSysUser().getLoginName());
						newObj.setCreateDate(new Date());
						newObj.setStatus(newObj.getStatus() == null?"Y":newObj.getStatus());
						newObj.setUpdateBy(newObj.getCreateBy());
						sysDictionaryDao.save(newObj);
					}
				}
				
				//重新刷新缓存对应的字典数据
				ehcacheUtil.remove(Constants.EhcacheTypes.dict_cache, dictType);
				putSysDictsToCache(dictType, list);
			}
		}catch(Exception e){
			StringUtil.writeStackTraceToLog(logger, e);
			throw new CustomException("saveOrUpdateSysDictDatas 出错!"+e.getMessage());
		}
		
	}

	/**
	 * 获取指定字典类型对应的配置信息,先从缓存取，没有则从数据库取，然后再存入缓存
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, String> getSysDictDatasByType(String dictType) {
		Map<Long, String> dictMap = null;
		Object cacheObj = ehcacheUtil.get(Constants.EhcacheTypes.dict_cache, dictType);
		if(cacheObj != null){
			dictMap = (HashMap<Long, String>)cacheObj;
		}else{
			List<SysDictDatas> list = sysDictionaryDao.findByProperty(SysDictDatas.class, "dictType", dictType);
			dictMap = putSysDictsToCache(dictType, list);
			
		}
		return dictMap;
	}

	private Map<Long, String> putSysDictsToCache(String dictType, List<SysDictDatas> list) {
		Map<Long, String> dictMap = new HashMap<Long, String>();
		if(list != null && list.size() > 0){
			dictMap = new HashMap<Long, String>();
			for(SysDictDatas datas : list){
				String code = datas.getDictDataCode();
				String codeName = datas.getDictDataName();
				
				//字典编码或名称或无效不做缓存
				if(StringUtil.isNullOrEmpty(codeName) || StringUtil.isNullOrEmpty(code) 
						|| "N".equals(datas.getStatus())){
					continue;
				}
				dictMap.put(datas.getId(), codeName);
			}
			ehcacheUtil.put(Constants.EhcacheTypes.dict_cache, dictType, dictMap);
		}
		return dictMap;
	}

}
