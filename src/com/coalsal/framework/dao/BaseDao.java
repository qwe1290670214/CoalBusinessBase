
package com.coalsal.framework.dao;

import java.util.List;

import com.coalsal.framework.entity.BaseEntity;

/**   
 * @ClassName:  BaseDao   
 * @author: zyg
 * @date:  
 *@Description:
 * dao层接口继承该BaseDao接口
 */
public interface BaseDao<T extends BaseEntity> {
  public int insertEntity(T entity) throws Exception;
  public int deleteById(String id) throws Exception;
  public int updateEntity(T entity) throws Exception;
  public T selectById(String id) throws Exception;
  public List<T> selectALL(String whereSQL) throws Exception;
}
