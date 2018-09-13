/**
 * 
 */
package com.coalsal.syssource.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.coalsal.common.utils.JsonUtils;
import com.coalsal.framework.control.BaseControl;
import com.coalsal.syssource.dao.PtMenuDirectoryDao;
import com.coalsal.syssource.dao.PtMenuDirectoryDaoImpl;
import com.coalsal.syssource.entity.PtMenuDirectoryEntity;
import com.coalsal.user.MenuVo;

/**   
 * @ClassName:  PtMenuControl   
 * @author: zyg
 * @date: 2018年7月19日 下午4:46:55
 *@Description:TODO系统菜单设置Control      
 */
@WebServlet("/sources/ptMenuControl")
public class PtMenuControl  extends BaseControl{
	private static PtMenuDirectoryDao ptMenuDirectoryDao;
	static{
		ptMenuDirectoryDao=new PtMenuDirectoryDaoImpl();
		
	}
    //加载菜单管理界面
	public void loadPtMenuPage(){
		  
		this.setForward("/views/sources/PtMenus.jsp");
	}
	 
	//显示一级以及二级菜单项
   public void selectMainMenus(){
	  String menuGroupID=this.getRequest().getParameter("menuGroupID");
	  String menuType=this.getRequest().getParameter("menuType");
	  System.out.println(menuGroupID+":::"+menuType);
	  List<MenuVo> list= ptMenuDirectoryDao.selectFirstMenus(menuGroupID, menuType);
	  PrintWriter out;
	try {
		out = this.getResponse().getWriter();
		out.println(JsonUtils.listToJson(list));
		
		out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	   
	   
   }
}
