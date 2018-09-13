/**
 * 
 */
package com.coalsal.user.entity;

import com.coalsal.framework.entity.BaseEntity;

/**   
 * @ClassName:  PtRoleEntity   
 * @author: zyg
 * @date: 2018年7月13日 下午3:25:16
 *@Description:角色实体
 */
public class PtRoleEntity extends BaseEntity {
  private String roleUUID;
  private String roleId;
  private String roleName;
public String getRoleUUID() {
	return roleUUID;
}
public void setRoleUUID(String roleUUID) {
	this.roleUUID = roleUUID;
}
public String getRoleId() {
	return roleId;
}
public void setRoleId(String roleId) {
	this.roleId = roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
  
}
