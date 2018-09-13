/**
 * 
 */
package com.coalsal.user;

import java.util.List;

/**   
 * @ClassName:  MenuVo   
 * @author: zyg
 * @date: 2018年7月19日 下午3:37:46
 *@Description:TODO菜单项VO类
 */
public class MenuVo {
  private String id;//默认 菜单id
  private String name;//默认 菜单名字
  private String url;
  private String leve;//菜单级别
  private String flag;//是否禁用
  private boolean open;//true false 默认
  private List<MenuVo> children;//默认 紫菜单项列表
  //是否隐藏节点
  private boolean isHidden;//true则隐藏
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getLeve() {
	return leve;
}
public void setLeve(String leve) {
	this.leve = leve;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public boolean isOpen() {
	return open;
}
public void setOpen(boolean open) {
	this.open = open;
}
public List<MenuVo> getChildren() {
	return children;
}
public void setChildren(List<MenuVo> children) {
	this.children = children;
}
public boolean getIsHidden() {
	return isHidden;
}
public void setIsHidden(boolean isHidden) {
	this.isHidden = isHidden;
}
  
  
}
