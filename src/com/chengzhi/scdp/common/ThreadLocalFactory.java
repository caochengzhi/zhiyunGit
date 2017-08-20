package com.chengzhi.scdp.common;

import java.io.Serializable;

import com.chengzhi.scdp.usermanager.dao.SysUser;

/**
 * 为每个请求，分配一个本地线程变量，用来存放当前线程的用户
 * @author beisi
 *
 */
public class ThreadLocalFactory implements Serializable{
	private static final long serialVersionUID = -9195740682090109513L;
	
	static ThreadLocal<SysUser> threadLocal = new ThreadLocal<SysUser>();;
	 
	public static void setSysUser(SysUser user){
		threadLocal.set(user);
	}
	
	public static SysUser getCurrentSysUser(){
		return threadLocal.get();
	}

}
