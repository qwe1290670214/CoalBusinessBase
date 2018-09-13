/**
 * 
 */
package com.coalsal.framework.control;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**   
 * @ClassName:  BaseControl   
 * @author: zyg
 * @date:  
 *@Description:控制层类必须继承该类     
 */
public class BaseControl extends HttpServlet{
	
    private HttpServletRequest request;
    private HttpServletResponse response;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  this.doPost(req, resp);
	}
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		  this.request=req;
		  this.response=resp;
		  this.request.setCharacterEncoding("utf-8");
		  this.response.setCharacterEncoding("utf-8");
	      try {
			this.execute();
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	
	/**
	 * 处理业务的方法
	 * 该方法需子类继承并重写
	 * */
	protected void execute()throws Exception {
		 String action=this.getRequest().getParameter("action");
		 //通过反射，调用上面test方法： organ/organControl?action=test
		Method method= this.getClass().getMethod(action);//获取某个方法，根据方法名获取该方法，返回Method
		method.invoke(this);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	} 
	
	//服务器端跳转
	public void setForward(String url){
		try {
			this.request.getRequestDispatcher(url).forward(this.request, this.response);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	//客户端跳转
	public void setRedirect(String url){
		try {
			this.response.sendRedirect(url);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
	
}
