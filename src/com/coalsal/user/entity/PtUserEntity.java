/**
 * 
 */
package com.coalsal.user.entity;

import com.coalsal.framework.entity.BaseEntity;

/**   
 * @ClassName:  UserEntity   
 * @author: zyg
 * @date: 2018年7月13日 下午3:16:10
 *@Description:用户信息实体 
 */
public class PtUserEntity extends BaseEntity {
	private String  userUUID;//USER_UUID 
	private String id;//ID	varchar	 
	private String  username;//USERNAME 
	private String  password;//PASSWORD 
	private String  email;//EMAIL	 
	private String mobile;//MOBILE 
	private Integer enabled=0;// 禁用1 启用0  	0	0
	private String accountNonExpired;//ACCOUNT_NON_EXPIRED	 	账户是否失效		 
	private Integer credentialsNonExpired;//CREDENTIALS_NON_EXPIRED 证书是否过期	 
	private Integer accountNonLocked=0;//ACCOUNT_NON_LOCKED 	 账户是否被锁 1 0				 
	private String organUUId;//ORGAN_UUID 	组织机构id 
	private String niceName;//NICE_NAME	  真实姓名 
	private Integer isAdmin=0;//IS_ADMIN	 	是否管理员		 
	private Integer maxMumSessions;//MAXIMUMSESSION 	最大会话时间	 
	private String registerdate;//REGISTERDATE	 	 注册时间 
	private String lastLongTime;//LASTLOGINTIME 	最后一次登录时间	 
	private String pwdQue;//PWDQUE	 	密保问题	 
	private String pwdAns;//PWDANS	 	密保答案	  
	private String remark;//REMARK	 	备注	utf8 
	private String depId;//DEPID	  	部门id	utf8	 
	private  String status="0";//STATUS 	 	状态	utf8	 1删除 0 未删除 
	private String modTime;//MODTIME	 	修改时间	utf8	 
	private String modifierId;//MODIFIERID	 		 修改人id	 
	private String isSum;//IS_SUM	 	是否阳光用户（0否1是）	 
	public String getUserUUID() {
		return userUUID;
	}
	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(String accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public Integer getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(Integer credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public Integer getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(Integer accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public String getOrganUUId() {
		return organUUId;
	}
	public void setOrganUUId(String organUUId) {
		this.organUUId = organUUId;
	}
	public String getNiceName() {
		return niceName;
	}
	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Integer getMaxMumSessions() {
		return maxMumSessions;
	}
	public void setMaxMumSessions(Integer maxMumSessions) {
		this.maxMumSessions = maxMumSessions;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public String getLastLongTime() {
		return lastLongTime;
	}
	public void setLastLongTime(String lastLongTime) {
		this.lastLongTime = lastLongTime;
	}
	public String getPwdQue() {
		return pwdQue;
	}
	public void setPwdQue(String pwdQue) {
		this.pwdQue = pwdQue;
	}
	public String getPwdAns() {
		return pwdAns;
	}
	public void setPwdAns(String pwdAns) {
		this.pwdAns = pwdAns;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModTime() {
		return modTime;
	}
	public void setModTime(String modTime) {
		this.modTime = modTime;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public String getIsSum() {
		return isSum;
	}
	public void setIsSum(String isSum) {
		this.isSum = isSum;
	}

}
