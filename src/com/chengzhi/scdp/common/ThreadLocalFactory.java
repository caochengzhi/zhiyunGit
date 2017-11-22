package com.chengzhi.scdp.common;

import io.jsonwebtoken.Claims;

import java.io.Serializable;

/**
 * 为每个请求，分配一个本地线程变量，用来存放当前线程的用户
 * @author beisi
 *
 */
public class ThreadLocalFactory implements Serializable{
	private static final long serialVersionUID = -9195740682090109513L;
	
	static ThreadLocal<Claims> threadLocal = null;
	
	private static ThreadLocal<Claims> getCurrentThread(){
		if(threadLocal == null)
			threadLocal = new ThreadLocal<Claims>();
		return threadLocal;
	}
	
	public static void setUserToken(Claims token){
		getCurrentThread().set(token);
	}
	
	public static Claims getCurrentUserToken(){
		return getCurrentThread().get();
	}
	
	public static void removeUserToken(){
		getCurrentThread().remove();
	}

}
