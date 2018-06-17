package com.chengzhi.scdp.system.service.imp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chengzhi.scdp.database.dao.IBaseDao;
import com.chengzhi.scdp.database.service.imp.BaseServiceImp;
import com.chengzhi.scdp.system.dao.ISysDictTypeDao;
import com.chengzhi.scdp.system.dao.SysDictType;
import com.chengzhi.scdp.system.service.ISysDictTypeService;

/**
 * 数据字典服务类
 * @author beisi
 *
 */
@Service
public class SysDictTypeServiceImp extends BaseServiceImp<SysDictType, Long> implements ISysDictTypeService{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	private ISysDictTypeDao sysDictTypeDao;

	@Autowired
	@Qualifier("sysDictTypeDao")
	@Override
	public void setBaseDao(IBaseDao<SysDictType, Long> baseDao) {
		this.baseDao = baseDao;
		sysDictTypeDao = (ISysDictTypeDao)baseDao;
	}
	
	
}
