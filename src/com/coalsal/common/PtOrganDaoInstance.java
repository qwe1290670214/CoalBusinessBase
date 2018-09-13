/**
 * 
 */
package com.coalsal.common;

import com.coalsal.orgin.dao.PtOrganDao;
import com.coalsal.orgin.dao.PtOrganDaoImpl;

/**   
 * @ClassName:  DaoInstance   
 * @author: zyg
 * @date: 2018年7月11日 上午8:31:04
 *@Description:TODO(这里用一句话描述这个类的作用)    
 *
 *   演示了一下单例模式：懒汉和饿汉式
 */
public class PtOrganDaoInstance {
	//单例模式，即让某一个对象，永远实例化一次；
	 private static PtOrganDao dao;
	 public static PtOrganDao getPtOrganDao(){
		  if(dao!=null){
			 	
			 	return dao;
		  }else{
			  dao=new PtOrganDaoImpl();
			  return dao;
		  } 
	 }
}
