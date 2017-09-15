package com.chengzhi.scdp.system.dao;

import java.util.Date;

import com.chengzhi.scdp.database.dao.AbstractModel;

public class SysUser extends AbstractModel{

	private static final long serialVersionUID = 8910322665979990638L;
	private Long userId;
	private String loginName;
	private String loginPassword;
	private String userName;
	private Character sex;
	private String phoneNumber;
	private Character isValid;
	private Date createDate;
	private String creater;
	private Long organizationId;

	public SysUser() {
	}

	public SysUser(String loginName, String loginPassword, String userName,
			Date createDate, String creater, Long organizationId) {
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.userName = userName;
		this.createDate = createDate;
		this.creater = creater;
		this.organizationId = organizationId;
	}

	public SysUser(String loginName, String loginPassword, String userName,
			Character sex, String phoneNumber, Character isValid,
			Date createDate, String creater, Long organizationId) {
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.userName = userName;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.isValid = isValid;
		this.createDate = createDate;
		this.creater = creater;
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

	public Character getSex() {
		return this.sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Character getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Character isValid) {
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

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
