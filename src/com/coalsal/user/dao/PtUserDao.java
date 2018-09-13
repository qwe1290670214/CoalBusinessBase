/**
 * 
 */
package com.coalsal.user.dao;

import com.coalsal.framework.dao.BaseDao;
import com.coalsal.user.entity.PtUserEntity;

/**   
 * @ClassName:  UserDao   
 * @author: zyg
 * @date: 2018年7月13日 下午3:26:41
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
public interface PtUserDao extends BaseDao<PtUserEntity>{
   public PtUserEntity selectByUnamePwd( String username,String pwd);
   
}
