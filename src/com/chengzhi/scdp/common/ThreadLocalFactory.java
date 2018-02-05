package com.chengzhi.scdp.common;

import java.io.Serializable;

import com.chengzhi.scdp.system.dao.SysUsers;

/**
 * 为每个请求，分配一个本地线程变量，用来存放当前线程的用户
 * @author beisi
 *
 */
public class ThreadLocalFactory implements Serializable{
	private static final long serialVersionUID = -9195740682090109513L;
	
	static ThreadLocal<SysUsers> threadLocal = null;
	
	private static ThreadLocal<SysUsers> getCurrentThread(){
		if(threadLocal == null)
			threadLocal = new ThreadLocal<SysUsers>();
		return threadLocal;
	}
	
	public static void setCurrentUser(SysUsers user){
		getCurrentThread().set(user);
	}
	
	public static SysUsers getCurrentUser(){
		return getCurrentThread().get();
	}
	
	public static void removeCurrenUser(){
		getCurrentThread().remove();
	}

}
