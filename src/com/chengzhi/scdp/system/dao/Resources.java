package com.chengzhi.scdp.system.dao;

import com.chengzhi.scdp.database.dao.AbstractModel;

/**
 * 资源权限表
 * @author beisi
 */
public class Resources extends AbstractModel {

	private static final long serialVersionUID = -1426910918354277002L;
	
	private Long resourceId;
	private String title;
	private String url;
	private Long parentId;
	private Integer level;
	private Long organizationId;

	public Resources() {
	}

	public Resources(String title, String url, Long organizationId) {
		this.title = title;
		this.url = url;
		this.organizationId = organizationId;
	}

	public Resources(String title, String url, Long parentId, Integer level,
			Long organizationId) {
		this.title = title;
		this.url = url;
		this.parentId = parentId;
		this.level = level;
		this.organizationId = organizationId;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
