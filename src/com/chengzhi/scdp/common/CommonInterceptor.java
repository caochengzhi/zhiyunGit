package com.chengzhi.scdp.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.chengzhi.scdp.Constants;
import com.chengzhi.scdp.tools.DateTimeUtil;

/**
 * 系统全局所有请求拦截器
 * @author beisi
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = Logger.getLogger(CommonInterceptor.class);
	private static Map<String , String> viewUrls = new HashMap<String , String>(); //需要进行token验证的页面的请求url 
    private static Map<String , String> actionUrls = new HashMap<String , String>();//需要进行token验证的表单请求url
    private Object lock = new Object();
    
    static{
    	/*校验页面必须是一一对应的，GET请求页面一定要有个POST的请求页面*/
    	viewUrls.put("/scdp/login/toLogin", "GET");  
    	actionUrls.put("/scdp/login/verify", "POST");
    }
	
	/**  
     * 在业务处理器处理请求之前被调用  
     * 如果返回false  
     *     不会进入postHandle()和afteCompletion()方法，一般直接跳转页面处理
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
		
		String urlRequest = request.getRequestURI();  
        String method = request.getMethod();  
        
        /*
         * 这部分是为每次请求线程分配用户信息的，包含组织账号为多租户准备，是全局的
         * 思路：用户过滤掉登录请求后(登录请求是产生token信息的，所以不用存储本地线程变量)，剩余其他请求都要
         * 将包含用户信息的token保存到当前线程变量中为本次请求服务
         * 每次请求前做token验证，验证不通过统一跳去异常页面
         * response.sendRedirect("/scdp");
         */
        if(!"/scdp/login/toLogin".equals(urlRequest) && !"/scdp/login/verify".equals(urlRequest)){
        	String userToken = (String)request.getSession().getAttribute(Constants.USER_TOKEN);
        	TokenCheckResult check = JavaWebToken.validateJWT(userToken);
        	if(check != null && check.getIsSucess()){
        		ThreadLocalFactory.setUserToken(check.getClaims());
        	}else{
        		String returnMsg = check == null?"":check.getErrorCode();
        		redirectUrl(request, response, Constants.ERROR_PAGE, returnMsg+",<a href=\"javascript:history.back(-1)\">返回地球🌎</a>");
        		return false;
        	}
        }
        
        /*
         * 拦截器的配置拦截两类请求，一类是到页面的，一类是提交表单的。
		 * 		1、当页面的请求时，生成token的名字和token值，一份放到服务器端缓存中，一份放传给页面表单的隐藏域。
		 * 		2、当表单请求提交时，拦截器得到参数中的tokenName和token，然后验证token值，如果能匹配上，请求就通过，不能匹配上就不通过。
		 * 		3、可以设计防止表单重复提交本，原理服务端缓存当次请求的token，第一次提交后将服务端的当前token值清空，这样如果用户重复提交表单，传到后台服务端已经没有token值了，验证失败请求被驳回，
		 * token值来源，只有GET请求到后台，生成一个新的token值给前台，就是每次表单提交，token值都会不同
		 * 		
         * 这部分是做定制化页面token校验的，开发人员可以自定义哪些请求页面或表单做token验证
         * 在一定程度上可以防止网络攻击(因为每次页面提交自带token信息，这个是动态的)，
         */
        if(viewUrls.containsKey(urlRequest) && method.equals(viewUrls.get(urlRequest))){//get请求页面设置token
        	Map<String, Object> claims = new HashMap<>(); 
        	claims.put("url", urlRequest);
        	claims.put("currentTime", new DateTimeUtil().toString("yyyy-mm-dd hh:mi hh24:mi:ss") );
        	TokenHelper.setToken(request,claims);
        }else if(actionUrls.containsKey(urlRequest) && method.equals(actionUrls.get(urlRequest))){//post提交页面获取token
        	if(!handleToken(request, response, handler))//如果验证不通过，跳转error页面并返回false，不往下走
        		return false;
        }
        return true;
	}
	
	/** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) 
			throws Exception {
//        if(modelAndView != null)  //加入当前时间    
//            modelAndView.addObject("currentTime", new DateTimeUtil().toString("yyyy-mm-dd hh:mi hh24:mi:ss"));    
	}
	
	/**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等，servlet部分已经结束所以不可以在这个方法做页面跳转的动作   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception{
		ThreadLocalFactory.removeUserToken();
	}
	
	protected boolean handleToken(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{  
        synchronized(lock){  
            if(!TokenHelper.validToken(request)){  
            	log.info("未通过验证.......");  
            	redirectUrl(request, response, Constants.ERROR_PAGE, "<a href=\"javascript:history.back(-1)\">返回地球🌎</a>");  
            	return false;
            }
            log.info("通过验证...");  
            return true;
        }
    }
	
	/** 
     * 当出现一个非法令牌时调用 
	 * @throws IOException 
	 * @throws ServletException 
     */  
	protected void redirectUrl(HttpServletRequest request, HttpServletResponse response, String url, String errorMsg) throws ServletException, IOException{  
        request.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher(url).forward(request, response);
    }
	
}
