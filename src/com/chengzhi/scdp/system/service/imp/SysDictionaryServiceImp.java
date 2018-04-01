package com.chengzhi.scdp.system.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.ISysDictionaryDao;
import com.chengzhi.scdp.system.dao.SysDictDatas;
import com.chengzhi.scdp.system.manager.EhCacheUtil;
import com.chengzhi.scdp.system.service.ISysDictionaryService;

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

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, String> getSysDictDatasByType(String dictType) {
		Map<Long, String> dictMap = null;
		Object cacheObj = ehcacheUtil.get(Constants.EhcacheTypes.dict_cache, dictType);
		if(cacheObj != null){
			dictMap = (HashMap<Long, String>)cacheObj;
		}else{
			List<SysDictDatas> list = sysDictionaryDao.findByProperty(SysDictDatas.class, "dictType", dictType);
			if(list != null && list.size() > 0){
				dictMap = new HashMap<Long, String>();
				for(SysDictDatas datas : list){
					dictMap.put(datas.getId(), datas.getDictName());
				}
				ehcacheUtil.put(Constants.EhcacheTypes.dict_cache, dictType, dictMap);
			}
			
		}
		return dictMap;
	}

}
