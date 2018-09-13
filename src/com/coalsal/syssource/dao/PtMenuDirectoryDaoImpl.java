/**
 * 
 */
package com.coalsal.syssource.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coalsal.common.database.DBConnection;
import com.coalsal.syssource.entity.PtMenuDirectoryEntity;
import com.coalsal.user.MenuVo;

/**   
 * @ClassName:  PtMenuDirectoryDao   
 * @author: zyg
 * @date: 2018年7月19日 下午3:17:50
 *@Description:TODO(系统菜单项DAO)       
 */
public class PtMenuDirectoryDaoImpl  implements  PtMenuDirectoryDao{
  private ResultSet res;
  private PreparedStatement pst;
  private Connection conn;
	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#insertEntity(com.coalsal.framework.entity.BaseEntity)
	 */
	@Override
	public int insertEntity(PtMenuDirectoryEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#deleteById(java.lang.String)
	 */
	@Override
	public int deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#updateEntity(com.coalsal.framework.entity.BaseEntity)
	 */
	@Override
	public int updateEntity(PtMenuDirectoryEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#selectById(java.lang.String)
	 */
	@Override
	public PtMenuDirectoryEntity selectById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#selectALL(java.lang.String)
	 */
	@Override
	public List<PtMenuDirectoryEntity> selectALL(String whereSQL)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.user.dao.PtMenuDirectoryDao#selectPtMenuList(com.coalsal.user.entity.PtMenuDirectoryEntity)
	 */
	@Override
	public List<Map<String, Object>> selectPtMenuList(
			PtMenuDirectoryEntity entity) {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		StringBuffer sql=new StringBuffer(" select b.id,b.dir_name,b.DIR_LEVEL_NUMBER,b.PARENT_ID,b.ISITEM,b.STATUS,b.FLAG,b.MENU_GROUP_ID,a.name,c.RES_ID,c.RES_URL "
		 		+ " from sys_menu_group a right join pt_menu_directory b on a.ID=b.MENU_GROUP_ID   left join  pt_res c on b.RES_UUID=c.ID where 1=1 ");
		 //未删除
		 sql.append(" and b.STATUS=0 ");
		if(entity!=null){
			//菜单级别
			if(entity.getDirLeveNumber()!=null&&!entity.getDirLeveNumber().equals("")){
				sql.append(" and  b.DIR_LEVEL_NUMBER='"+entity.getDirLeveNumber()+"' ");
			}
			//上级菜单id
			if(entity.getParentId()!=null&&!entity.getParentId().equals("")){
				sql.append(" and b.PARENT_ID='"+entity.getParentId()+"' ");
			}else{
				sql.append("  and b.parent_id ='root' ");
				 
			}
			
			//菜单组id
			if(entity.getMenuGroupdId()!=null&&!entity.getMenuGroupdId().equals("")){
				sql.append(" and  b.MENU_GROUP_ID='"+entity.getMenuGroupdId()+"' ");
			}
			//菜单类型
			if(entity.getDirType()!=null&&!entity.getDirType().equals("")){
				sql.append(" and b.DIR_TYPE='"+entity.getDirType()+"'");
			}
		}
		    sql.append(" order by b.DIR_ORDER asc  ");
			
		    System.out.println(sql.toString());
		    DBConnection db=null;
			try {
				db = new DBConnection();
				 conn=db.getConnection();
				 pst=conn.prepareStatement(sql.toString());
				 res=pst.executeQuery();
				 while(res.next()){
					// b.id,b.dir_name,b.DIR_LEVEL_NUMBER,b.PARENT_ID,b.ISITEM,b.STATUS,b.FLAG,b.MENU_GROUP_ID,a.name,c.RES_ID,c.RES_URL  
					 Map<String,Object> map=new HashMap<String,Object>();
					 map.put("menuId", res.getString(1));
					 map.put("dirName", res.getString(2));
					 map.put("dirLeve", res.getString(3));
					 map.put("parentId", res.getString(4));
					 map.put("isitem", res.getString(5));
					 map.put("status", res.getString(6));
					 map.put("flag", res.getString(7));
					 map.put("menuGroupId", res.getString(8));
					 map.put("groupName", res.getString(9));
					 map.put("resId", res.getString(10));
					 String url=res.getString(11);
					 if(url!=null&&!url.trim().equals("")){
						 if(url.equals("javascript:void(0)")||url.equals("#")){
							 map.put("resURL", "");
						 }else{
							 map.put("resURL", url);
						 }
					 }else{
						 map.put("resURL", "");
					 }
					
					 
					 list.add(map);
				 }
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					if(pst!=null){
						pst.close();
					}
					if(conn!=null&&!conn.isClosed()){
						conn.close();
					}
				}catch(Exception s){
					s.printStackTrace();
				}
				
			}
		   
			 
		return list;
	}

	/* 
	 * 查询一级以及二级列表组合
	 */
	@Override
	public List<MenuVo> selectFirstMenus(String groupId, String typeId) {
		//根节点
		List<MenuVo> result=new ArrayList<MenuVo>();
		//添加一个任意节点的根节点root节点
		MenuVo root=new MenuVo();
		root.setName("系统菜单>>");
		root.setId("root");
		root.setLeve("0"); 
		root.setOpen(true);
		result.add(root);
		
		//数据库中的一级节点，以及该一级节点的子节点
		List<MenuVo> resList=null;
		PtMenuDirectoryEntity entity=new PtMenuDirectoryEntity();
		entity.setMenuGroupdId(groupId);//菜单组id
		entity.setDirType(typeId);//菜单类型
		entity.setDirLeveNumber("1");//一级菜单
		entity.setParentId(null);//无父菜单id
		List<Map<String,Object>> list=this.selectPtMenuList(entity);
		if(list!=null&&list.size()>0){
			resList=new ArrayList<MenuVo>();
			
			for(int i=0;i<list.size();i++){
				Map<String,Object> map=list.get(i);
				MenuVo vo=new MenuVo();
				vo.setName(map.get("dirName").toString());
				vo.setFlag(map.get("flag").toString());
				vo.setId(map.get("menuId").toString());
				vo.setLeve(map.get("dirLeve").toString());
				vo.setUrl(map.get("resURL").toString());
				vo.setOpen(true);
				//查询子菜单列表 2级子菜单列表
				
				List<Map<String,Object>> secList=this.selectPtMenuList(entity);
				if(secList!=null&&secList.size()>0){
					
					List<MenuVo> secResList=this.selectLeveNMenus(groupId, typeId, "2", map.get("menuId").toString());
					vo.setChildren(secResList);//向一级中放二级菜单列表children
				}
				
				
				resList.add(vo);
				
				root.setChildren(resList);//将其他节点放入根节点
				
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.user.dao.PtMenuDirectoryDao#selectLeveNMenus(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<MenuVo> selectLeveNMenus(String groupId, String typeId,
			String leve, String parentId) {
		List<MenuVo> resList=null;
		PtMenuDirectoryEntity entity=new PtMenuDirectoryEntity();
		entity.setMenuGroupdId(groupId);//菜单组id
		entity.setDirType(typeId);//菜单类型
		entity.setDirLeveNumber(leve);//一级菜单
		entity.setParentId(parentId);//无父菜单id
		List<Map<String,Object>> list=this.selectPtMenuList(entity);
		
		if(list!=null&&list.size()>0){
			resList=new ArrayList<MenuVo>();
			for(int i=0;i<list.size();i++){
				Map<String,Object> map=list.get(i);
				MenuVo vo=new MenuVo();
				vo.setName(map.get("dirName").toString());
				vo.setFlag(map.get("flag").toString());
				vo.setId(map.get("menuId").toString());
				vo.setLeve(map.get("dirLeve").toString());
				vo.setUrl(map.get("resURL").toString());
				 
			    //递归找三四。。。N级别节点列表
				List<MenuVo> resNexList= 	selectLeveNMenus(groupId, typeId, String.valueOf(Integer.parseInt(leve)+1), map.get("menuId").toString());
			    if(resNexList!=null&&resNexList.size()>0){
			    	vo.setChildren(resNexList);
			    }
				
				resList.add(vo);
			}
		}
		return resList;
	}

}
