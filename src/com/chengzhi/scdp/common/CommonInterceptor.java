package com.chengzhi.scdp.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chengzhi.scdp.tools.DateTimeUtil;

/**
 * 系统全局所有请求拦截器
 * @author beisi
 * 在springmvc下实现token。
 * 实现思路：
 * 在springmvc配置文件中加入拦截器的配置，拦截两类请求，一类是到页面的，一类是提交表单的。
 * 		1、当页面的请求时，生成token的名 字和token值，一份放到缓存中，一份放传给页面表单的隐藏域。
 * 		2、当表单请求提交时，拦截器得到参数中的tokenName和token，然后到缓存中去取token值，如果能匹配上，请求就通过，不能匹配上就不通过。
 * 这里的tokenName生成时也是随机的，每次请求都不一样。而从缓存中取token值时，会立即将其删除（删与读是原子的，无线程安全问题）。
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = Logger.getLogger(CommonInterceptor.class);
	private static Map<String , String> viewUrls = new HashMap<String , String>(); //需要进行token验证的页面的请求url 
    private static Map<String , String> actionUrls = new HashMap<String , String>();//需要进行token验证的表单请求url
    private Object lock = new Object();
    
    static{
    	viewUrls.put("/scdp/login/toLogin", "GET");  
    	actionUrls.put("/scdp/login/verify", "POST");
    }
	
	/**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
     * 如果返回true  
     *    执行下一个拦截器,直到所有的拦截器都执行完毕  
     *    再执行被拦截的Controller  
     *    然后进入拦截器链,  
     *    从最后一个拦截器往回执行所有的postHandle()  
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()  
     */ 
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) 
			throws Exception {
		log.info(request.getMethod()+"==============执行顺序: 1、preHandle================"+request.getRequestURI());
		//XSS攻击：跨站脚本攻击,设置https的cookie可以预防xss攻击
		response.addHeader("Set-Cookie", "uid=112; Path=/; Secure; HttpOnly");
		
		String url = request.getRequestURI();  
        String method = request.getMethod();  
        
        if(viewUrls.containsKey(url) && method.equals(viewUrls.get(url))){
        	Map<String, Object> claims = new HashMap<>(); 
        	claims.put("url", url);
        	claims.put("currentTime", new DateTimeUtil().toString("yyyy-mm-dd hh:mi hh24:mi:ss") );
        	TokenHelper.setToken(request,claims);
        	return true;
        }else if(actionUrls.containsKey(url) && method.equals(actionUrls.get(url))){
        	return handleToken(request, response, handler);
        }
        return true;
        /*ThreadLocalFactory.setSysUser(null);
        */
        
        /*SysUser sysUser =  (SysUser)request.getSession().getAttribute("sysUser");   
        if(sysUser == null){  
            log.info("Interceptor：跳转到login页面！");  
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);  
            return false;  
        }else{
          	ThreadLocalFactory.setSysUser(sysUser);
            return true; 
        }*/
        
	}
	
	/** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) 
			throws Exception {
		log.info("==============执行顺序: 2、postHandle================");  
        if(modelAndView != null)  //加入当前时间    
            modelAndView.addObject("currentTime", new DateTimeUtil().toString("yyyy-mm-dd hh:mi hh24:mi:ss"));    
	}
	
	/**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception{
		ThreadLocalFactory.removeSysUser();
		log.info("==============执行顺序: 3、afterCompletion================"); 
	}
	
	protected boolean handleToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{  
        synchronized(lock){  
            if(!TokenHelper.validToken(request)){  
            	log.info("未通过验证...");  
                return handleInvalidToken(request, response, handler);  
            }
        }
        log.info("通过验证...");  
        return handleValidToken(request, response, handler);  
    }
	
	/** 
     * 当出现一个非法令牌时调用 
     */  
	protected boolean handleInvalidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{  
        request.setAttribute("errorMsg", "请不要频繁操作！<a href=\"javascript:history.back(-1)\">返回上一页</a>");
        request.getRequestDispatcher("/jsp/error/error.jsp").forward(request, response);
        return false;  
    }
    
	/** 
     * 当发现一个合法令牌时调用. 
     */  
    protected boolean handleValidToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws CustomException{  
        return true;  
    }
	
}
