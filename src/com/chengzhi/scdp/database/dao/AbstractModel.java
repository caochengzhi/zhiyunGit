package com.chengzhi.scdp.database.dao;


public class AbstractModel implements java.io.Serializable {
	
	private static final long serialVersionUID = 6437537708317703594L;
	
	private Long organizationId;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
