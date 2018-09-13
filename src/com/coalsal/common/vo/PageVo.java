/**
 * 
 */
package com.coalsal.common.vo;

/**   
 * @ClassName:  PageVo   
 * @author: zyg
 * @date: 2018年7月7日 下午4:03:51
 *@Description:TODO(这里用一句话描述这个类的作用)       
 */
public class PageVo {

private int pageSize=10;// 每页几天记录 10
private int pageTotal;// 总页数 total/pageSize   无法整除+1 
private int total;//总记录数  （查询）
private int pageNow=1;//当前页的页码（页面决定）
private int offset;// 当前页第一条记录在数据库是第几条下标（pageNow-1）*pageSize
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public int getPageTotal() {
	return pageTotal;
}
public void setPageTotal(int pageTotal) {
	this.pageTotal = pageTotal;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
	//封装total总记录数时，计算总页数
	if(total%this.pageSize>0){
		this.pageTotal=this.total/this.pageSize+1;
	}else{
		this.pageTotal=this.total/this.pageSize;	
	}
	
}
public int getPageNow() {
	return pageNow;
}
public void setPageNow(int pageNow) {
	this.pageNow = pageNow;
	//封装当前页面时，计算偏移量，即每页第一条数据在 数据库是第几条（下标）
	this.offset=(this.pageNow-1)*this.pageSize;
}
public int getOffset() {
	return offset;
}
public void setOffset(int offset) {
	this.offset = offset;
}



}
