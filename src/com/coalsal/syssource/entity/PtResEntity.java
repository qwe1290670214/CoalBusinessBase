/**
 * 
 */
package com.coalsal.syssource.entity;

import com.coalsal.framework.entity.BaseEntity;

/**   
 * @ClassName:  PtResEntity   
 * @author: zyg
 * @date: 2018年7月19日 下午3:15:29
 *@Description:TODO菜单项资源列表实体       
 */
public class PtResEntity extends BaseEntity{
	 private String   resUUID   ;//          VARCHAR2(255 CHAR)             not null,
	 private String   id    ;//               VARCHAR2(32)                   null,
	 private String   resId    ;//              VARCHAR2(32 CHAR)              null,
	 private String   resName   ;//             VARCHAR2(32 CHAR)              null,
	 private String   resURL    ;//             VARCHAR2(256 CHAR)             null,
	 private String   resType   ;//             VARCHAR2(255 CHAR)             null,
	 private String    parentUUID ;//          VARCHAR2(255 CHAR)             null,
	 private String    resOrder ;//              VARCHAR2(255 CHAR)             null,
	public String getResUUID() {
		return resUUID;
	}
	public void setResUUID(String resUUID) {
		this.resUUID = resUUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResURL() {
		return resURL;
	}
	public void setResURL(String resURL) {
		this.resURL = resURL;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getParentUUID() {
		return parentUUID;
	}
	public void setParentUUID(String parentUUID) {
		this.parentUUID = parentUUID;
	}
	public String getResOrder() {
		return resOrder;
	}
	public void setResOrder(String resOrder) {
		this.resOrder = resOrder;
	}
	 
}
