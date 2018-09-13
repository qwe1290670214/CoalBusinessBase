/**
 * 
 */
package com.coalsal.test;

import java.util.List;

import com.coalsal.common.utils.JsonUtils;
import com.coalsal.common.vo.PageVo;
import com.coalsal.orgin.dao.PtOrganDao;
import com.coalsal.orgin.dao.PtOrganDaoImpl;
import com.coalsal.orgin.entity.PtOrganEntity;
import com.coalsal.syssource.dao.PtMenuDirectoryDao;
import com.coalsal.syssource.dao.PtMenuDirectoryDaoImpl;
import com.coalsal.user.MenuVo;

/**   
 * @ClassName:  TestUtils   
 * @author: zyg
 * @date: 2018年7月7日 下午4:27:54
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
public class TestUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		   //查询一级菜单
		   PtMenuDirectoryDao dao=new PtMenuDirectoryDaoImpl();
		List<MenuVo> list=   dao.selectFirstMenus(null,null);//("1231", "5");
		System.out.println(JsonUtils.listToJson(list));
	}

}
