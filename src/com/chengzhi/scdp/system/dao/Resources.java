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
	private String code;//权限标识
	private Integer type;//'菜单级别:1模块；2菜单；3按钮'
	private Long organizationId;

	public Resources() {
	}

	public Resources(String title, String url, Long organizationId) {
		this.title = title;
		this.url = url;
		this.organizationId = organizationId;
	}

	public Resources(String title, String url, Long parentId, Integer type,
			Long organizationId) {
		this.title = title;
		this.url = url;
		this.parentId = parentId;
		this.type = type;
		this.organizationId = organizationId;
	}

	public Long getResourceId() {
		return this.resourceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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


	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
