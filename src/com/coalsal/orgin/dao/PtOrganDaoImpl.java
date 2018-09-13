/**
 * 
 */
package com.coalsal.orgin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.coalsal.common.database.DBConnection;
import com.coalsal.common.utils.DateUtils;
import com.coalsal.common.vo.PageVo;
import com.coalsal.orgin.entity.PtOrganEntity;
 

/**   
 * @ClassName:  PtOrganDaoImpl   
 * @author: zyg
 * @date: 2018年7月7日 下午4:12:47
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
public class PtOrganDaoImpl implements PtOrganDao{
    private ResultSet res;
    private PreparedStatement pst;
    private Connection conn;
    
	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#insertEntity(com.coalsal.framework.entity.BaseEntity)
	 */
	@Override
	public int insertEntity(PtOrganEntity entity) throws Exception {
		 String sql="insert into  pt_organ (ORGAN_UUID , ORGAN_CODE	 , ORGAN_NAME	 ,ORGAN_TYPE,	 "+
		 "IN_USE,PARENT_UUID, STATUS,MODIFIERID	, MODTIME,DESCRIPTION,account_code) values(?,?,?,?,?,?,?,?,?,?,?)";	
		 DBConnection db=null; int i=0;
		 try{
		 db=new DBConnection ();
		 conn=db.getConnection();
		 pst=conn.prepareStatement(sql);
		 pst.setString(1, UUID.randomUUID().toString());//32位随机字符串，永远不重复
		 pst.setString(2, entity.getOrganCode()+"_"+DateUtils.getDateLong(new Date()));
		 pst.setString(3, entity.getOrganName());
		 pst.setString(4, entity.getOrganType());
		 pst.setString(5, entity.getInUse());
		 pst.setString(6, entity.getParentUUID());
		 pst.setString(7, entity.getStatus());
		 pst.setString(8, entity.getModifierId());
		 pst.setString(9, entity.getModTime());
		 pst.setString(10, entity.getDescription());
		 pst.setString(11, entity.getAccountCode());
		
		 i=pst.executeUpdate();
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 if(pst!=null){
				 pst.close();
			 }
			 if(conn!=null&&!conn.isClosed()){
				 conn.close();
			 }
		 } 

		return i;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#deleteById(java.lang.String)
	 */
	@Override
	public int deleteById(String id) throws Exception {
		 String sql=" delete from  pt_organ  where pt_oragn.ORGAN_UUID=?";	
				 DBConnection db=null; int i=0;
				 try{
				 db=new DBConnection ();
				 conn=db.getConnection();
				 pst=conn.prepareStatement(sql);
				 pst.setString(1, id);//32位随机字符串，永远不重复

				 i=pst.executeUpdate();
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 if(pst!=null){
						 pst.close();
					 }
					 if(conn!=null&&!conn.isClosed()){
						 conn.close();
					 }
				 } 

				return i;
		 
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#updateEntity(com.coalsal.framework.entity.BaseEntity)
	 */
	@Override
	public int updateEntity(PtOrganEntity entity) throws Exception {
		 String sql="update pt_organ set ORGAN_NAME=?,ORGAN_TYPE=?	, "+
				 "IN_USE=?,PARENT_UUID=?, STATUS=?,DESCRIPTION=?,account_code=? where pt_organ.ORGAN_UUID=?";	
				 DBConnection db=null; int i=0;
				 try{
				 db=new DBConnection ();
				 conn=db.getConnection();
				 pst=conn.prepareStatement(sql);
				 pst.setString(1, entity.getOrganName());
				 pst.setString(2, entity.getOrganType());
				 pst.setString(3, entity.getInUse());
				 pst.setString(4, entity.getParentUUID());
				 pst.setString(5, entity.getStatus());
				 pst.setString(6, entity.getDescription());
				 pst.setString(7, entity.getAccountCode());
				 pst.setString(8, entity.getOrganUUID()); 
				 i=pst.executeUpdate();
				 }catch(Exception e){
					 e.printStackTrace();
				 }finally{
					 if(pst!=null){
						 pst.close();
					 }
					 if(conn!=null&&!conn.isClosed()){
						 conn.close();
					 }
				 } 

				return i; 
		 
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#selectById(java.lang.String)
	 */
	@Override
	public PtOrganEntity selectById(String id) throws Exception {
		 DBConnection db=new DBConnection ();
		 PtOrganEntity e=null;
		 try{
			 conn=db.getConnection();
			 StringBuffer sql=new StringBuffer("select a.ORGAN_UUID,a.ORGAN_CODE,a.ORGAN_NAME,a.ORGAN_TYPE,"+
  " a.IN_USE,b.ORGAN_NAME,a.MODTIME,a.account_code,a.MODIFIERID, a.DESCRIPTION,a.parent_uuid from pt_organ a left join pt_organ b on a.PARENT_UUID=b.ORGAN_UUID where  (a.status='N' or a.status is null) and a.ORGAN_UUID=?");
					
              System.out.println(sql.toString());
			  pst=conn.prepareStatement(sql.toString());
			  pst.setString(1, id);
			  res=pst.executeQuery();
			 while(res.next()){
				 e=new  PtOrganEntity();
				 e.setOrganUUID(res.getString(1));
				 e.setOrganCode(res.getString(2));
				 e.setOrganName(res.getString(3));
				 e.setOrganType(res.getString(4));
				// e.setInUse(res.getString(5));
				 if(res.getString(5)!=null&&!res.getString(5).equals("")){
					 if(res.getString(5).equals("1")){
						 e.setInUse("启用");
					 }else{
						e.setInUse("禁用"); 
						 
					 } 
				 }else{
					 e.setInUse("禁用"); 
				 }
				 e.setParentOrganName(res.getString(6));
				
				 e.setModTime(res.getString(7)!=null&&!res.getString(7).equals("")?DateUtils.getDateTimeStrFmt(res.getString(7)):null);
				 e.setAccountCode(res.getString(8));
				 e.setModifierId(res.getString(9));
				 e.setDescription(res.getString(10));
				 e.setParentUUID(res.getString(11));
				 
				 
			 }
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }finally{
			 if(pst!=null){
				 pst.close();
			 }
			 if(conn!=null&&!conn.isClosed()){
				 conn.close();
			 }
		 }
		return e;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#selectALL(java.lang.String)
	 */
	@Override
	public List<PtOrganEntity> selectALL(String whereSQL) throws Exception {
		 DBConnection db=new DBConnection ();
		 List<PtOrganEntity> list=new ArrayList<PtOrganEntity>();
		 try{
			 conn=db.getConnection();
			 StringBuffer sql=new StringBuffer("select a.ORGAN_UUID,a.ORGAN_CODE,a.ORGAN_NAME,a.ORGAN_TYPE,"+
  " a.IN_USE,b.ORGAN_NAME,a.MODTIME,a.account_code,a.MODIFIERID, a.DESCRIPTION from pt_organ a left join pt_organ b on a.PARENT_UUID=b.ORGAN_UUID where  (a.status='N' or a.status is null) ");
			if(whereSQL!=null&&!whereSQL.trim().equals("")){
				sql.append(whereSQL);
			}	 
	 
  
               System.out.println(sql.toString());
			  pst=conn.prepareStatement(sql.toString());
			 
			 
			 res=pst.executeQuery();
			
			 while(res.next()){
				 PtOrganEntity e=new  PtOrganEntity();
				 e.setOrganUUID(res.getString(1));
				 e.setOrganCode(res.getString(2));
				 e.setOrganName(res.getString(3));
				 e.setOrganType(res.getString(4));
				// e.setInUse(res.getString(5));
				 if(res.getString(5)!=null&&!res.getString(5).equals("")){
					 if(res.getString(5).equals("1")){
						 e.setInUse("启用");
					 }else{
						e.setInUse("禁用"); 
						 
					 } 
				 }else{
					 e.setInUse("禁用"); 
				 }
				 e.setParentOrganName(res.getString(6));
				
				 e.setModTime(res.getString(7)!=null&&!res.getString(7).equals("")?DateUtils.getDateTimeStrFmt(res.getString(7)):null);
				 e.setAccountCode(res.getString(8));
				 e.setModifierId(res.getString(9));
				 e.setDescription(res.getString(10));
				 list.add(e);
				 
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 if(pst!=null){
				 pst.close();
			 }
			 if(conn!=null&&!conn.isClosed()){
				 conn.close();
			 }
		 }
		return list;
	}

	/* 
	 * 查询总记录数
	 */
	@Override
	public int selectPtOrganTotal(PtOrganEntity entity) throws Exception {
		 DBConnection db=new DBConnection ();
		 int total=0;
		 try{
			 conn=db.getConnection();
			 StringBuffer sql=new StringBuffer("select  count(a.organ_uuid) from pt_organ a left join  pt_organ b on  a.PARENT_UUID=b.ORGAN_UUID where 1=1 and (a.status='N' or a.status is null) ");//where 1=1
			 if(entity!=null){
				 if(entity.getOrganName()!=null&&!entity.getOrganName().trim().equals("")){
					 sql.append(" and a.organ_Name like '%"+entity.getOrganName()+"%' ");
					 
				 }
				 if(entity.getInUse()!=null&&!entity.getInUse().trim().equals("")){
					 if(entity.getInUse().equals("1")){
						 sql.append(" and a.in_use=1 ");
					 }else{
						 sql.append(" and (a.in_use=0 or a.in_use is null)");
					 }
					 
				 }
				 if(entity.getModDateMax()!=null&&!entity.getModDateMax().trim().equals("")){
					 
							 sql.append(" and  str_to_date(date_format(STR_TO_DATE(a.modtime,'%Y%m%d%H%i%s'),'%Y-%m-%d'),'%Y-%m-%d')<=STR_TO_DATE('"+entity.getModDateMax()+"','%Y-%m-%d') ");
				 }
				 if(entity.getModDateMin()!=null&&!entity.getModDateMin().trim().equals("")){
					 
					 sql.append(" and  str_to_date(date_format(STR_TO_DATE(a.modtime,'%Y%m%d%H%i%s'),'%Y-%m-%d'),'%Y-%m-%d')>=STR_TO_DATE('"+entity.getModDateMin()+"','%Y-%m-%d') ");
		         }
				 
			 }
			 
			 System.out.println(sql.toString());
			 pst=conn.prepareStatement(sql.toString());
			 res=pst.executeQuery();
			
			 while(res.next()){
				 total=res.getInt(1); 
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 if(pst!=null){
				 pst.close();
			 }
			 if(conn!=null&&!conn.isClosed()){
				 conn.close();
			 }
		 }
		 
		 
		return total;
	}

	/*
	 * 分页查询
	 */
	@Override
	public List<PtOrganEntity> selectPtOrganPaper(PageVo pageVo,PtOrganEntity entity) throws Exception {
		 DBConnection db=new DBConnection ();
		 List<PtOrganEntity> list=new ArrayList<PtOrganEntity>();
		 try{
			 conn=db.getConnection();
			 StringBuffer sql=new StringBuffer("select a.ORGAN_UUID,a.ORGAN_CODE,a.ORGAN_NAME,a.ORGAN_TYPE,"+
  " a.IN_USE,b.ORGAN_NAME,a.MODTIME,a.account_code  from pt_organ a left join pt_organ b on a.PARENT_UUID=b.ORGAN_UUID where 1=1  and (a.status='N' or a.status is null) ");
					 
	if(entity!=null){
		 if(entity.getOrganName()!=null&&!entity.getOrganName().trim().equals("")){
			 sql.append(" and a.organ_Name like '%"+entity.getOrganName()+"%' ");
			 
		 }
		 if(entity.getInUse()!=null&&!entity.getInUse().trim().equals("")){
			 if(entity.getInUse().equals("1")){
				 sql.append(" and a.in_use=1 ");
			 }else{
				 sql.append(" and (a.in_use=0 or a.in_use is null) ");
			 }
		 }
		 if(entity.getModDateMax()!=null&&!entity.getModDateMax().trim().equals("")){
			 
			 sql.append(" and  str_to_date(date_format(STR_TO_DATE(a.modtime,'%Y%m%d%H%i%s'),'%Y-%m-%d'),'%Y-%m-%d')<=STR_TO_DATE('"+entity.getModDateMax()+"','%Y-%m-%d') ");
		 }
		 if(entity.getModDateMin()!=null&&!entity.getModDateMin().trim().equals("")){
	 
			 sql.append(" and  str_to_date(date_format(STR_TO_DATE(a.modtime,'%Y%m%d%H%i%s'),'%Y-%m-%d'),'%Y-%m-%d')>=STR_TO_DATE('"+entity.getModDateMin()+"','%Y-%m-%d') ");
		 }
	}
  
  sql.append(" limit ?,?");
  System.out.println(sql.toString());
			 pst=conn.prepareStatement(sql.toString());
			 pst.setInt(1, pageVo.getOffset());
			 pst.setInt(2, pageVo.getPageSize());
			 
			 res=pst.executeQuery();
			
			 while(res.next()){
				 PtOrganEntity e=new  PtOrganEntity();
				 e.setOrganUUID(res.getString(1));
				 e.setOrganCode(res.getString(2));
				 e.setOrganName(res.getString(3));
				 e.setOrganType(res.getString(4));
				// e.setInUse(res.getString(5));
				 if(res.getString(5)!=null&&!res.getString(5).equals("")){
					 if(res.getString(5).equals("1")){
						 e.setInUse("启用");
					 }else{
						e.setInUse("禁用"); 
						 
					 } 
				 }else{
					 e.setInUse("禁用"); 
				 }
				 e.setParentOrganName(res.getString(6));
				
				 e.setModTime(res.getString(7)!=null&&!res.getString(7).equals("")?DateUtils.getDateTimeStrFmt(res.getString(7)):null);
				 e.setAccountCode(res.getString(8));
				 list.add(e);
				 
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 if(pst!=null){
				 pst.close();
			 }
			 if(conn!=null&&!conn.isClosed()){
				 conn.close();
			 }
		 }
		return list;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.orgin.dao.PtOrganDao#selectChildOrganTotal(java.lang.String)
	 */
	@Override
	public List<PtOrganEntity> selectChildOrganTotal(String id)
			throws Exception {
		 DBConnection db=new DBConnection ();
		 List<PtOrganEntity> list=new ArrayList<PtOrganEntity>();
		 try{
			 conn=db.getConnection();
			 StringBuffer sql=new StringBuffer("select a.ORGAN_UUID,a.ORGAN_CODE,a.ORGAN_NAME,a.ORGAN_TYPE,"+
  " a.IN_USE,a.MODTIME,a.account_code,a.MODIFIERID, a.DESCRIPTION from pt_organ a   where  (a.status='N' or a.status is null) and a.parent_uuid=? ");
			 
	 
  
               System.out.println(sql.toString());
			  pst=conn.prepareStatement(sql.toString());
			  pst.setString(1, id);
			 
			 res=pst.executeQuery();
			
			 while(res.next()){
				 PtOrganEntity e=new  PtOrganEntity();
				 e.setOrganUUID(res.getString(1));
				 e.setOrganCode(res.getString(2));
				 e.setOrganName(res.getString(3));
				 e.setOrganType(res.getString(4));
				// e.setInUse(res.getString(5));
				 if(res.getString(5)!=null&&!res.getString(5).equals("")){
					 if(res.getString(5).equals("1")){
						 e.setInUse("启用");
					 }else{
						e.setInUse("禁用"); 
						 
					 } 
				 }else{
					 e.setInUse("禁用"); 
				 }
				 
				
				 e.setModTime(res.getString(6)!=null&&!res.getString(6).equals("")?DateUtils.getDateTimeStrFmt(res.getString(6)):null);
				 e.setAccountCode(res.getString(7));
				 e.setModifierId(res.getString(8));
				 e.setDescription(res.getString(9));
				 list.add(e);
				 
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 if(pst!=null){
				 pst.close();
			 }
			 if(conn!=null&&!conn.isClosed()){
				 conn.close();
			 }
		 }
		return list;
		 
	}

}
