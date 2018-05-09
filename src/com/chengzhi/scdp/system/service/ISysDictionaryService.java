package com.chengzhi.scdp.system.service;

import java.util.List;
import java.util.Map;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.database.service.IBaseService;
import com.chengzhi.scdp.system.dao.SysDictDatas;

public interface ISysDictionaryService extends IBaseService<SysDictDatas, Long>{

	abstract Map<Long, String> getSysDictDatasByType(String dictCode);
	
	abstract void saveOrUpdateSysDictDatas(Long dictId, List<SysDictDatas> list)throws CustomException;
}
