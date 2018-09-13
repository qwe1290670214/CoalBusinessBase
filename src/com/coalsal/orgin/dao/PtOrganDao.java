/**
 * 
 */
package com.coalsal.orgin.dao;

import java.util.List;

import com.coalsal.common.vo.PageVo;
import com.coalsal.framework.dao.BaseDao;
import com.coalsal.orgin.entity.PtOrganEntity;

/**   
 * @ClassName:  PtOrganDao   
 * @author: zyg
 * @date: 2018年7月7日 下午4:12:06
 *@Description:组织机构DAO   
 */
public interface PtOrganDao extends BaseDao<PtOrganEntity> {
   public int selectPtOrganTotal(PtOrganEntity entity) throws Exception;
   public List<PtOrganEntity> selectPtOrganPaper(PageVo pageVo,PtOrganEntity entitys) throws Exception;
   //按照组织机构主键查询子组织机构列表
   public List<PtOrganEntity> selectChildOrganTotal(String id) throws Exception;
}
