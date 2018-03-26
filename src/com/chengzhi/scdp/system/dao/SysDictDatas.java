package com.chengzhi.scdp.system.dao;

import java.util.Date;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 数据字典配置表
 */
public class SysDictDatas extends AbstractModel{

	private static final long serialVersionUID = -8515342632274874574L;
	private Long id;
	private SysDictType sysDictType;
	private String dictName;
	private char status;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private String updateDate;
	private String remarks;

	public SysDictDatas() {
	}

	public SysDictDatas(SysDictType sysDictType, String dictName, char status,
			String createBy, Date createDate, String updateBy) {
		this.sysDictType = sysDictType;
		this.dictName = dictName;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
	}

	public SysDictDatas(SysDictType sysDictType, String dictName, char status,
			String createBy, Date createDate, String updateBy,
			String updateDate, String remarks) {
		this.sysDictType = sysDictType;
		this.dictName = dictName;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysDictType getSysDictType() {
		return this.sysDictType;
	}

	public void setSysDictType(SysDictType sysDictType) {
		this.sysDictType = sysDictType;
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
