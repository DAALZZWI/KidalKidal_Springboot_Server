package com.cos.kidalkidal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cos.kidalkidal.model.modelCompany;
import com.cos.kidalkidal.model.modelVisitorPayload;
import com.cos.kidalkidal.service.serviceSocketOrder;

@RestController
public class controllerOrder {

	@Autowired
	private serviceSocketOrder serviceSocketOrder;
	
	@PostMapping ( "/company/init" )
	public int companyInit () {
		
		return serviceSocketOrder.functionCompanyInit();
	}
	
	@PostMapping ( "/company/select" )
	public List< modelCompany > companySelect () {
		
		return serviceSocketOrder.functionCompanySelect();
	}
	
	@PostMapping( "/company/image/select" )
	public modelVisitorPayload companyImageSelect ( @RequestBody modelVisitorPayload modelVisitorPayload ) {
		
		return serviceSocketOrder.functionVisitorImageSelect( modelVisitorPayload );
	}

	@PostMapping( "/company/image/insert" )
	public modelVisitorPayload companyImageInsert ( @RequestBody modelVisitorPayload modelVisitorPayload ) {
		
		return serviceSocketOrder.functionVisitorImageInsert( modelVisitorPayload );
	}
}
