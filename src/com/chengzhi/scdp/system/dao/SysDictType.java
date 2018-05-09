package com.chengzhi.scdp.system.dao;
// default package
// Generated 2018-3-11 18:39:46 by Hibernate Tools 3.2.2.GA

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 数据字典主类型配置表
 */
public class SysDictType extends AbstractModel {

	private static final long serialVersionUID = 983911767634635140L;
	private Long dictId;
	private String dictName;
	private String dictType;
	private String status;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private String updateDate;
	private String remarks;
	private Long organizationId;
	private List<SysDictDatas> sysDictDatases = new ArrayList<SysDictDatas>(0);

	public SysDictType() {
	}

	public SysDictType(String createBy,Date createDate) {
		this.createBy = createBy;
		this.createDate = createDate;
	}
	
	public SysDictType(String dictName, String dictType, String status,
			String createBy, Date createDate, String updateBy) {
		this.dictName = dictName;
		this.dictType = dictType;
		this.status = status;
		this.createBy = createBy;
		this.createDate = createDate;
		this.updateBy = updateBy;
	}

	public SysDictType(String dictName, String dictType, String status,
			String createBy, Date createDate, String updateBy,
			String updateDate, String remarks, List<SysDictDatas> sysDictDatases) {
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getDictType() {
		return this.dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
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

	public List<SysDictDatas> getSysDictDatases() {
		return this.sysDictDatases;
	}

	public void setSysDictDatases(List<SysDictDatas> sysDictDatases) {
		this.sysDictDatases = sysDictDatases;
	}

}
