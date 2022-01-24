package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelOrder implements Serializable {

	private static final long serialVersionUID = 8482078711210368098L;
	
	private String orderUserEmail;
	private String orderUserImage;
	private String orderCompanyId;
	
	public modelOrder() {
		
		orderUserEmail = "";
		orderUserImage = "";
		orderCompanyId = "";
	}

	public String getOrderUserEmail() { return orderUserEmail; }

	public void setOrderUserEmail( String orderUserEmail ) { this.orderUserEmail = orderUserEmail; }

	public String getOrderUserImage() { return orderUserImage; }

	public void setOrderUserImage( String orderUserImage ) { this.orderUserImage = orderUserImage; }

	public String getOrderCompanyId() { return orderCompanyId; }

	public void setOrderCompanyId( String orderCompanyId ) { this.orderCompanyId = orderCompanyId; }
	
	
}
