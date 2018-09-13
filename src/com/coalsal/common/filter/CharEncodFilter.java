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
public class CharEncodFilter implements Filter{
    private String charEncoding="UTF-8";
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
		  request.setCharacterEncoding(charEncoding);
		  resposne.setCharacterEncoding(charEncoding);
		  arg2.doFilter(arg0, arg1);
		  
		  
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		String encode= arg0.getInitParameter("encode");
		if(encode!=null&&!encode.trim().equals("")){
			this.charEncoding=encode;
		}
	}
  
}
