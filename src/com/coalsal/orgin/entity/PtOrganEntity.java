/**
 * 
 */
package com.coalsal.orgin.entity;

import com.coalsal.framework.entity.BaseEntity;

/**   
 * @ClassName:  PtOrganEntity   
 * @author: zyg
 * @date: 2018年7月7日 下午4:09:21
 *@Description:组织机构实体【pt_organ】      
 */
public class PtOrganEntity extends BaseEntity {
	private String organUUID;//id
	private String organCode;//组织机构编号
	private String organName;//组织机构名称
	private String organType;//组织机构类型
	private String inUse;//是否被启用
	private String parentUUID;//上一级组织机构id
	private String status;//是否删除
	private String modifierId;//编辑人
	private String modTime;//编辑时间
	private String description;//描述
	private String  accountCode;//银行账户code
	
	//父组织机构属性列表（与表列无关）
	private String parentOrganName;//上一级组织机构名称
	
	//查询条件属性
	private String modDateMin;
	private String modDateMax;
	

	public String getOrganUUID() {
		return organUUID;
	}

	public void setOrganUUID(String organUUID) {
		this.organUUID = organUUID;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getOrganType() {
		return organType;
	}

	public void setOrganType(String organType) {
		this.organType = organType;
	}

	public String getInUse() {
		return inUse;
	}

	public void setInUse(String inUse) {
		this.inUse = inUse;
	}

	public String getParentUUID() {
		return parentUUID;
	}

	public void setParentUUID(String parentUUID) {
		this.parentUUID = parentUUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModifierId() {
		return modifierId;
	}

	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	public String getModTime() {
		return modTime;
	}

	public void setModTime(String modTime) {
		this.modTime = modTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getParentOrganName() {
		return parentOrganName;
	}

	public void setParentOrganName(String parentOrganName) {
		this.parentOrganName = parentOrganName;
	}

	public String getModDateMin() {
		return modDateMin;
	}

	public void setModDateMin(String modDateMin) {
		this.modDateMin = modDateMin;
	}

	public String getModDateMax() {
		return modDateMax;
	}

	public void setModDateMax(String modDateMax) {
		this.modDateMax = modDateMax;
	}
	
	
	 
	
	

}
