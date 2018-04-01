package com.chengzhi.scdp.system.service;

import java.util.Map;

import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.SysDictDatas;

public interface ISysDictionaryService extends IBaseService<SysDictDatas, Long>{

	abstract Map<Long, String> getSysDictDatasByType(String dictType);
	
}
