/**
 * 
 */
package com.coalsal.user.control;

import javax.servlet.annotation.WebServlet;

import com.coalsal.framework.control.BaseControl;
import com.coalsal.user.dao.PtRoleDao;
import com.coalsal.user.dao.PtUserDao;
import com.coalsal.user.dao.PtUserDaoImpl;
import com.coalsal.user.entity.PtUserEntity;

/**   
 * @ClassName:  LoginControl   
 * @author: zyg
 * @date: 2018年7月13日 下午4:46:44
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
@WebServlet(name="user/loginContrl",urlPatterns="/user/loginControl")
public class LoginControl extends BaseControl{
	private static PtUserDao ptUserDao;
	static{
		ptUserDao=new PtUserDaoImpl();
	}
    //用户登录
	public void login(){
		 String username=this.getRequest().getParameter("username");
		 String pwd=this.getRequest().getParameter("pwd");
		 String code=this.getRequest().getParameter("validateCode");
		 if(code!=null&&!code.trim().equals("")){
			 //获取session中的code
			 String sessionValCode=this.getRequest().getSession().getAttribute("valiCode").toString();
			 System.out.println("验证码："+sessionValCode+":"+code);
			 if(code.equals(sessionValCode)){//页面与后台session中验证码匹配
				 //查询数据库用户信息，验证登录（根据用户名，密码查询是否存在该记录，有则ok，否则登录失败）
				PtUserEntity entity= ptUserDao.selectByUnamePwd(username, pwd);
				 if(entity!=null){//登录成功
					 //用户对象放入sesssion
					 this.getRequest().getSession().setAttribute("user", entity);
					 //设置session周期
					 //this.getRequest().getSession().setMaxInactiveInterval(30);
					 
					 //加载main.jsp
					 this.setForward("/main.jsp");
				 }else{//登录失败
					 //返回login.jsp
					 this.getRequest().setAttribute("errorLogin", "登录失败！");
					 this.setForward("/login.jsp");
				 }
			 }else{
				 //返回login.jsp
				 this.getRequest().setAttribute("errorLogin", "验证码错误，登录失败！");
				 this.setForward("/login.jsp");
				 
			 }
		 }else{
			 //返回login.jsp
			 this.getRequest().setAttribute("errorLogin", "验证码为空，登录失败！");
			 this.setForward("/login.jsp");
			 
		 }
		
	}
}
