/**
 * 
 */
package com.coalsal.syssource.entity;

import com.coalsal.framework.entity.BaseEntity;

/**   
 * @ClassName:  SysMenuGroupEntity   
 * @author: zyg
 * @date: 2018年7月19日 下午3:14:11
 *@Description:TODO系统菜单项类别实体   
 */
public class SysMenuGroupEntity extends BaseEntity{
  private String id;
  private String code;
  private String name;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
