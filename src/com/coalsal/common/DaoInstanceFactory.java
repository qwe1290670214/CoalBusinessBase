/**
 * 
 */
package com.coalsal.common;

import com.coalsal.framework.dao.BaseDao;
import com.coalsal.orgin.dao.PtOrganDao;

/**   
 * @ClassName:  DaoInstanceFactory   
 * @author: zyg
 * @date: 2018年7月11日 上午8:37:14
 *@Description:TODO(这里用一句话描述这个类的作用)   
 *工厂模式：将某一层对象的创建，集中于一个类中，可认为是对象创建的工厂；    
 *例如：下面该工厂类，用于创建不同dao层实例
 */
public class DaoInstanceFactory{ 
     //获取组织机构Dao
	 public static PtOrganDao  getOrganDao(){
		 return PtOrganDaoInstance.getPtOrganDao();
	 }
	 //获取角色dao
	
	//。。。。
}
