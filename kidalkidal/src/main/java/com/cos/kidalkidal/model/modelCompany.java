package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelCompany implements Serializable {
	
	private static final long serialVersionUID = 403162008526742577L;
	
	private String companyId;
	private String companyName;

	public modelCompany() {
		
		this.companyId = "";
		this.companyName = "";
	}

	public String getCompanyId() { return companyId; }

	public void setCompanyId( String companyId ) { this.companyId = companyId; }

	public String getCompanyName() { return companyName; }

	public void setCompanyName( String companyName ) { this.companyName = companyName; }
}
