package com.cos.kidalkidal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.kidalkidal.model.modelDeskPayload;
import com.cos.kidalkidal.service.serviceDesk;

@RestController
public class controllerDesk {
	
	@Autowired
	private serviceDesk serviceDesk;
	
	@PostMapping ( "/desk/insert" )
	public modelDeskPayload userDesk ( @RequestBody modelDeskPayload modelDeskPayload ) {
		
		return serviceDesk.functionUserDesk( modelDeskPayload );
	}
	
	@PostMapping ( "/desk/select" )
	public modelDeskPayload userDeskAll () {
		
		return serviceDesk.functionUserDeskAll();
	}
	
	@PostMapping ( "/desk/delete"  )
	public modelDeskPayload userDeskDelete ( @RequestBody modelDeskPayload modelDeskPayload ) {
		
		return serviceDesk.functionUserDeskDelete( modelDeskPayload );
	}
	
	@PostMapping ( "/desk/increase" )
	public modelDeskPayload userDeskIncrease ( @RequestBody modelDeskPayload modelDeskPayload ) {
		
		return serviceDesk.functionUserDeskIncrease( modelDeskPayload );
	}
}
