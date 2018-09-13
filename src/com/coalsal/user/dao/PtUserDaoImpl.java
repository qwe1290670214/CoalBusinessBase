/**
 * 
 */
package com.coalsal.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.coalsal.common.database.DBConnection;
import com.coalsal.user.entity.PtUserEntity;
 

/**   
 * @ClassName:  PtDaoImpl   
 * @author: zyg
 * @date: 2018年7月13日 下午3:27:34
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
public class PtUserDaoImpl implements PtUserDao{
   private Connection conn;
   private ResultSet res;
   private PreparedStatement pst;
	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#insertEntity(com.coalsal.framework.entity.BaseEntity)
	 */
	@Override
	public int insertEntity(PtUserEntity entity) throws Exception {
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
	public int updateEntity(PtUserEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#selectById(java.lang.String)
	 */
	@Override
	public PtUserEntity selectById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.framework.dao.BaseDao#selectALL(java.lang.String)
	 */
	@Override
	public List<PtUserEntity> selectALL(String whereSQL) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.coalsal.user.dao.PtUserDao#selectByUnamePwd(java.lang.String, java.lang.String)
	 */
	@Override
	public PtUserEntity selectByUnamePwd(String username, String pwd) {
		 String sql="select  USER_UUID,ID,USERNAME,PASSWORD,EMAIL,MOBILE,ENABLED,ACCOUNT_NON_EXPIRED,CREDENTIALS_NON_EXPIRED,"
		 		+ "ACCOUNT_NON_LOCKED,ORGAN_UUID,NICE_NAME,IS_ADMIN,MAXIMUMSESSIONS,REGISTERDATE,"
+"LASTLOGINTIME,PWDQUE,PWDANS,REMARK,DEPID,STATUS,MODTIME,MODIFIERID,IS_SUM "
+" from pt_user   where  USERNAME=? and  PASSWORD=? ";
		 DBConnection db;
		 PtUserEntity entity=null;
		try {
			db = new DBConnection();
			conn=db.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pwd);

			res=pst.executeQuery();
			while(res.next()){
				entity=new PtUserEntity();
				entity.setUserUUID(res.getString(1));
				entity.setId(res.getString(2));
				entity.setUsername(res.getString(3));
				entity.setPassword(res.getString(4));
				entity.setEmail(res.getString(5));
				entity.setMobile(res.getString(6));
				entity.setEnabled(res.getInt(7));
				entity.setAccountNonExpired(res.getString(8));
				entity.setCredentialsNonExpired(res.getInt(9));
				entity.setAccountNonLocked(res.getInt(10));
				entity.setOrganUUId(res.getString(11));
				 entity.setNiceName(res.getString(12));
				 entity.setIsAdmin(res.getInt(13));
				 entity.setMaxMumSessions(res.getInt(14));
				 entity.setRegisterdate(res.getString(15));
				 entity.setRemark(res.getString(16));
				 entity.setLastLongTime(res.getString(17));
				 entity.setPassword(res.getString(18));
				 entity.setPwdAns(res.getString(19));
				 
				 entity.setDepId(res.getString(20));
				 entity.setStatus(res.getString(21));
				 entity.setModTime(res.getString(22));
				 entity.setModifierId(res.getString(23));
				 entity.setIsSum(res.getString(24));
			}
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(pst!=null){
					pst.close();
					}
					if(conn!=null&&!conn.isClosed()){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				 
		return entity;
	}

}
