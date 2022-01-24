package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelQr implements Serializable {

	private static final long serialVersionUID = 5595192381926558344L;
	
	private int qrNumber;
	private String qrCompany;
	private String qrOwner;
	
	public modelQr() {
		
		this.qrNumber = 0;
		this.qrCompany = "";
		this.qrOwner = "";
	}

	public int getQrNumber() { 	return qrNumber; }

	public void setQrNumber( int qrNumber ) { this.qrNumber = qrNumber; }

	public String getQrCompany() { return qrCompany; }

	public void setQrCompany( String qrCompany ) { this.qrCompany = qrCompany; }

	public String getQrOwner() { return qrOwner; }

	public void setQrOwner( String qrOwner ) { this.qrOwner = qrOwner; }
}
