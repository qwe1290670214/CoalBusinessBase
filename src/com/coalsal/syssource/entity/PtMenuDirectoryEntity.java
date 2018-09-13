/**
 * 
 */
package com.coalsal.syssource.entity;

import com.coalsal.framework.entity.BaseEntity;

/**   
 * @ClassName:  PtMenuDirectoryEntity   
 * @author: zyg
 * @date: 2018年7月19日 下午3:09:30
 *@Description:TODO(这里用一句话描述这个类的作用)   菜单目录实体    
 */
public class PtMenuDirectoryEntity extends BaseEntity {
	   private String id;//                   
	   private String dirCode ;//           
	   private String   dirName  ;//            
	   private String  dirLeveNumber ;// 菜单项级别   
	   private String  dirType    ;//类型'菜单目录类型：0，业务；1，系统；2，实施；4，集成；5，Demo';    
	   private String  resourceId ;// 资源id          
	   private String  parentId;//             
	   private String  isitem   ;// '明细否：0 非明细；1 明细';            
	   private String   dirOrder ;//  '菜单目录顺序号';           
	   private String   status ;// 是否删除 0,1              
	   private String  flag    ;//   '启/停状态：0 停用；1 启用';           
	   private String  menuGroupdId ;//         
	   private String  local ;//  国际化标示             
	   private String   resUUId ;// 资源id            
	   private String   realId ;//               
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDirCode() {
		return dirCode;
	}
	public void setDirCode(String dirCode) {
		this.dirCode = dirCode;
	}
	public String getDirName() {
		return dirName;
	}
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	public String getDirLeveNumber() {
		return dirLeveNumber;
	}
	public void setDirLeveNumber(String dirLeveNumber) {
		this.dirLeveNumber = dirLeveNumber;
	}
	public String getDirType() {
		return dirType;
	}
	public void setDirType(String dirType) {
		this.dirType = dirType;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getIsitem() {
		return isitem;
	}
	public void setIsitem(String isitem) {
		this.isitem = isitem;
	}
	public String getDirOrder() {
		return dirOrder;
	}
	public void setDirOrder(String dirOrder) {
		this.dirOrder = dirOrder;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMenuGroupdId() {
		return menuGroupdId;
	}
	public void setMenuGroupdId(String menuGroupdId) {
		this.menuGroupdId = menuGroupdId;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getResUUId() {
		return resUUId;
	}
	public void setResUUId(String resUUId) {
		this.resUUId = resUUId;
	}
	public String getRealId() {
		return realId;
	}
	public void setRealId(String realId) {
		this.realId = realId;
	}
}
