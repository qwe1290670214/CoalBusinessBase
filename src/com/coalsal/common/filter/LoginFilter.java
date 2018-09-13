/**
 * 
 */
package com.coalsal.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coalsal.user.entity.PtUserEntity;

/**   
 * @ClassName:  LoginFilter   
 * @author: zyg
 * @date: 2018年7月14日 上午8:03:24
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
public class LoginFilter implements Filter{

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		  //获取uri
		  HttpServletRequest request=(HttpServletRequest) arg0;
		  HttpServletResponse resposne=(HttpServletResponse) arg1;
		  
		  String requestURI= request.getRequestURI();
		  String path= requestURI.substring(requestURI.indexOf("/",1));
		  if(path.equals("/")||path.equals("/login.jsp")||path.equals("/welcome.jsp")){		  
			  arg2.doFilter(request, resposne);
		  }else if(path.toLowerCase().endsWith(".js")||path.toLowerCase().endsWith(".css")){
			  arg2.doFilter(request, resposne);
		  }else if(path.toLowerCase().endsWith(".png")||path.toLowerCase().endsWith(".jpg")||path.toLowerCase().endsWith(".gif")){
			  arg2.doFilter(request, resposne);  
		  }else if(path.equals("/user/loginControl")){//登录验证放行
			  arg2.doFilter(request, resposne);  	  
		  }else if(path.equals("/user/validateCodeControl")){//验证码
			  arg2.doFilter(arg0, arg1);  	
			  
		  }else{
			  //验证用户是否登录成功过，成功过，则放行
		        PtUserEntity user=	  (PtUserEntity) request.getSession().getAttribute("user");
			  if(user!=null){//之前登录验证通过
				  arg2.doFilter(request, resposne);  	
			  }else{
				  request.getRequestDispatcher("/login.jsp").forward(arg0, arg1);
			  }
		  }
		  
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub 
		
	}
  
}
