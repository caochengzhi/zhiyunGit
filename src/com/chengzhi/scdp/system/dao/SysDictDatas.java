package com.chengzhi.scdp.system.dao;

import java.util.Date;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 数据字典明细数据类型配置表
 */
public class SysDictDatas extends AbstractModel{

	private static final long serialVersionUID = -8515342632274874574L;
	private Long id;
	private Long dictId;
	private String dictType;
	private String dictName;
	private char status;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private String updateDate;
	private String remarks;
	private Long organizationId;

	public SysDictDatas() {
	}

	public SysDictDatas(String dictName, char status,
			String createBy, Date createDate, String updateBy) {
		this.dictName = dictName;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
	}

	public SysDictDatas(String dictName, char status,
			String createBy, Date createDate, String updateBy,
			String updateDate, String remarks) {
		this.dictName = dictName;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
