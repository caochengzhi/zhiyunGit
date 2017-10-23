package com.chengzhi.scdp.common;

import java.io.Serializable;

import com.chengzhi.scdp.system.dao.Users;

/**
 * 为每个请求，分配一个本地线程变量，用来存放当前线程的用户
 * @author beisi
 *
 */
public class ThreadLocalFactory implements Serializable{
	private static final long serialVersionUID = -9195740682090109513L;
	
	static ThreadLocal<Users> threadLocal = null;
	
	private static ThreadLocal<Users> getCurrentThread(){
		if(threadLocal == null)
			threadLocal = new ThreadLocal<Users>();
		return threadLocal;
	}
	 
	public static void setSysUser(Users user){
		getCurrentThread().set(user);
	}
	
	public static Users getCurrentSysUser(){
		return getCurrentThread().get();
	}
	
	public static void removeSysUser(){
		getCurrentThread().remove();
	}

}
