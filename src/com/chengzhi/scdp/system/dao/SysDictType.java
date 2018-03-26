package com.chengzhi.scdp.system.dao;
// default package
// Generated 2018-3-11 18:39:46 by Hibernate Tools 3.2.2.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 数据字典类型表
 */
public class SysDictType extends AbstractModel {

	private static final long serialVersionUID = 983911767634635140L;
	private Long dictId;
	private String dictName;
	private String dictType;
	private char status;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private String updateDate;
	private String remarks;
	private Set<SysDictDatas> sysDictDatases = new HashSet<SysDictDatas>(0);

	public SysDictType() {
	}

	public SysDictType(String dictName, String dictType, char status,
			String createBy, Date createDate, String updateBy) {
		this.dictName = dictName;
		this.dictType = dictType;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
	}

	public SysDictType(String dictName, String dictType, char status,
			String createBy, Date createDate, String updateBy,
			String updateDate, String remarks, Set<SysDictDatas> sysDictDatases) {
		this.dictName = dictName;
		this.dictType = dictType;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
		this.updateDate = updateDate;
		this.remarks = remarks;
		this.sysDictDatases = sysDictDatases;
	}

	public Long getDictId() {
		return this.dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictType() {
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
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

	public Set<SysDictDatas> getSysDictDatases() {
		return this.sysDictDatases;
	}

	public void setSysDictDatases(Set<SysDictDatas> sysDictDatases) {
		this.sysDictDatases = sysDictDatases;
	}

}
