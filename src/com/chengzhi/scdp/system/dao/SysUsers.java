package com.chengzhi.scdp.system.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chengzhi.scdp.database.dao.AbstractModel;

public class SysUsers extends AbstractModel {

	private static final long serialVersionUID = 1613113405175819652L;
	private Long userId;
	private String loginName;
	private String loginPassword;
	private String userName;
	private String sex;
	private String phoneNumber;
	private String telephone;
	private String isValid;
	private String email;
	private Date createDate;
	private String lastUpdateBy;
	private String lastUpdateDate;
	private String creater;
	private Long organizationId;
	private List<Roles> roles = new ArrayList<Roles>();
	
	public SysUsers() {
	}

	public SysUsers(String loginName, String loginPassword, String userName,
			Date createDate, String creater, long organizationId) {
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.userName = userName;
		this.createDate = createDate;
		this.creater = creater;
		this.organizationId = organizationId;
	}

	public SysUsers(String loginName, String loginPassword, long organizationId, String isValid) {
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.organizationId = organizationId;
		this.isValid = isValid;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIsValid() {
		return this.isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Long getOrganizationId() {
		return this.organizationId;
	}

}
