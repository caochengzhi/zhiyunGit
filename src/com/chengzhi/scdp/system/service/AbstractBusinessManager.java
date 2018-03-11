package com.chengzhi.scdp.system.service;

import com.chengzhi.scdp.common.Exceptions.CustomException;
import com.chengzhi.scdp.security.beans.CaptchaUsernamePasswordToken;
import com.chengzhi.scdp.system.dao.SysUsers;

public abstract class AbstractBusinessManager {
	
	/**
	 * 获取当前用户所有的权限
	 */
	public abstract SysUsers queryPermissions(CaptchaUsernamePasswordToken authcToken)throws CustomException;

}
