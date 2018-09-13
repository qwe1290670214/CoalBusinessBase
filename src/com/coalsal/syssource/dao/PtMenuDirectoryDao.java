/**
 * 
 */
package com.coalsal.syssource.dao;

import java.util.List;
import java.util.Map;

import com.coalsal.framework.dao.BaseDao;
import com.coalsal.syssource.entity.PtMenuDirectoryEntity;
import com.coalsal.user.MenuVo;

/**   
 * @ClassName:  PtMenuDirectoryDao   
 * @author: zyg
 * @date: 2018年7月19日 下午3:17:50
 *@Description:TODO(系统菜单项DAO)       
 */
public interface PtMenuDirectoryDao  extends BaseDao<PtMenuDirectoryEntity>{
	 // 查询系统菜单项列表(菜单级别，父菜单id， 菜单类别（groupid），未删除状态0，菜单目录类型dirType)
      public List<Map<String,Object>> selectPtMenuList(PtMenuDirectoryEntity entity) ;
	
      //（1）系统一级菜单项列表以及该列表下二级列表（菜单组id、类型）List<MenuVO>//'菜单目录类型：0，业务；1，系统；2，实施；4，集成；5，Demo';
      public List<MenuVo> selectFirstMenus(String groupId,String typeId) ;
      //（2）查询N级菜单列表(菜单组id、类型, 菜单级别=N，上级菜单id)List<MenuVO>
      public List<MenuVo> selectLeveNMenus(String groupId,String typeId,String leve,String parentId);
}
