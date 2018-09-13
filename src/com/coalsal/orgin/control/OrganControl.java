/**
 * 
 */
package com.coalsal.orgin.control;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.coalsal.common.utils.DateUtils;
import com.coalsal.common.vo.PageVo;
import com.coalsal.framework.control.BaseControl;
import com.coalsal.orgin.dao.PtOrganDao;
import com.coalsal.orgin.dao.PtOrganDaoImpl;
import com.coalsal.orgin.entity.PtOrganEntity;

/**   
 * @ClassName:  OrganControl   
 * @author: zyg
 * @date: 2018年7月7日 下午4:35:33
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
@WebServlet(name="com.coalsal.orgin.control.organControl",urlPatterns="/orgin/organControl")
public class OrganControl extends BaseControl{
	//单例模式
    private static PtOrganDao dao;
    static{
    	dao=new PtOrganDaoImpl();
    }
    
    //反射：通过某些手段，获取类自身某些信息；属性，方法，访问修饰符，返回值，参数，全局属性
	
	 public void organPage() throws Exception{
		 String pageNow=this.getRequest().getParameter("pageNow");//接收页码
		 //查询条件
		 String organName=this.getRequest().getParameter("organName");
		 String inuse=this.getRequest().getParameter("inuse");
		 System.out.println("----"+inuse);
		 String modDateMax=this.getRequest().getParameter("modDateMax");
		 String modDateMin=this.getRequest().getParameter("modDateMin");
		 
		 PtOrganEntity entity=new PtOrganEntity();
		 entity.setOrganName(organName);
		 entity.setInUse(inuse);
		 entity.setModDateMax(modDateMax);
		 entity.setModDateMin(modDateMin);
		 
		 if(pageNow==null){
			 pageNow="1";
		 }
		 PageVo pageVo=new PageVo();
		 pageVo.setPageNow(Integer.parseInt(pageNow));
		 
		 int total=dao.selectPtOrganTotal(entity);
		 pageVo.setTotal(total);
		 List<PtOrganEntity> list=dao.selectPtOrganPaper(pageVo,entity);
		 
		 this.getRequest().setAttribute("rows", list);
		 this.getRequest().setAttribute("pageVo", pageVo);
		 
		 this.getRequest().setAttribute("organName",organName);
		 this.getRequest().setAttribute("inuse",inuse);
		 this.getRequest().setAttribute("modDateMax",modDateMax);
		 this.getRequest().setAttribute("modDateMin",modDateMin);
		 this.setForward("/views/orgin/orgin.jsp");
		 
		 
	 }
	 
	 public void loadAaddPtOrganPage() throws Exception{
		 //查询组织机构列表
		 
			List<PtOrganEntity> list= dao.selectALL(null);
			 this.getRequest().setAttribute("rows", list);
			 this.setForward("/views/orgin/addOrgin.jsp");
		 
	 }
	 
	 public void organAdd() throws Exception{
		 String organName= this.getRequest().getParameter("organName");
			String organType= this.getRequest().getParameter("organType");
			String organDesc= this.getRequest().getParameter("desc");
			String inuse= this.getRequest().getParameter("inuse");
			String shortName= this.getRequest().getParameter("organShortName");
			String accountCode=this.getRequest().getParameter("account");
			String parentOrganId=this.getRequest().getParameter("hideParentOrgan");
			PtOrganEntity entity=new PtOrganEntity();
			entity.setAccountCode(accountCode);
			entity.setDescription(organDesc);
			entity.setInUse(inuse);
			entity.setOrganCode(shortName);
			entity.setOrganType(organType);
			entity.setModifierId("111");
			entity.setOrganName(organName);
			entity.setModTime(DateUtils.getDateTimeStr(new Date()));
			entity.setParentUUID(parentOrganId);
			
			int i=dao.insertEntity(entity);
			if(i>0){
				//查询(客户端跳转走后台)
				this.setRedirect("organControl?action=organPage");
			}else{
				 this.getRequest().setAttribute("errorMsg", "组织机构信息保存失败！");
				 this.setForward("/error.jsp");
			}
		 
	 }
	 
	 public void deleteOrgan() throws Exception{
		 
		 String organId=this.getRequest().getParameter("organId");
		  //是否该组织机构作为上级组织机构出现，如果存在子部门则不允许删除
		 List<PtOrganEntity> list= dao.selectChildOrganTotal(organId);
		 if(list.size()>0){
			 this.getRequest().setAttribute("errorMsg", "该组织机构被其他组织机构使用，无法删除！");
			 this.setForward("/error.jsp");
		 }else{
			 PtOrganEntity entity= dao.selectById(organId);
			 if(entity.getInUse().equals("禁用")){
				 entity.setInUse("0");
			 }else{
				 entity.setInUse("1");
			 }
			 entity.setStatus("Y");
			 if(entity!=null){
				 int i= dao.updateEntity(entity);
							if(i>0){
								//查询(客户端跳转走后台)
								this.setRedirect("organControl?action=organPage");
							}else{
								 this.getRequest().setAttribute("errorMsg", "组织机构信息删除失败！");
								 this.setForward("/error.jsp");
							}
			 }
			    
		 }
	 }
	 
	 
	 
	 public void selectForUpdate() throws Exception{
		 String organId=this.getRequest().getParameter("organId");
		 PtOrganEntity entity= dao.selectById(organId);
		 if(entity.getParentUUID()!=null&&!entity.getParentUUID().equals("")){
			 PtOrganEntity parentOrgan=dao.selectById(entity.getParentUUID());
			 if(parentOrgan!=null){
				 this.getRequest().setAttribute("parentOrganName",parentOrgan.getOrganName() );
			 }
 
		 }
		
		 //查询组织机构列表
		 List<PtOrganEntity> list= dao.selectALL(null);
		 this.getRequest().setAttribute("rows", list);
		 this.getRequest().setAttribute("entity", entity);
		 this.setForward("/views/orgin/updateOrgin.jsp");
		 
	 }
    
	 
	 public void updateOrgan() throws Exception{
		 String organUUID=this.getRequest().getParameter("organUUID");
	     String organName= this.getRequest().getParameter("organName");
		String organType= this.getRequest().getParameter("organType");
		String organDesc= this.getRequest().getParameter("desc");
		String inuse= this.getRequest().getParameter("inuse");
	 
		String accountCode=this.getRequest().getParameter("account");
		String parentOrganId=this.getRequest().getParameter("hideParentOrgan");
		
		PtOrganEntity entity=dao.selectById(organUUID);
		
		entity.setAccountCode(accountCode);
		entity.setDescription(organDesc);
		entity.setInUse(inuse);
	 
		entity.setOrganType(organType);
		entity.setOrganName(organName);
		entity.setParentUUID(parentOrganId);
		
		int i=dao.updateEntity(entity);
		if(i>0){
			//查询(客户端跳转走后台)
			this.setRedirect("organControl?action=organPage");
		}else{
			 this.getRequest().setAttribute("errorMsg", "组织机构信息修改失败！");
			 this.setForward("/error.jsp");
		}
	 
	 }
     
}
