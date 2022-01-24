package com.cos.kidalkidal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.kidalkidal.model.modelQrPayload;
import com.cos.kidalkidal.service.serviceQr;

@RestController
public class controllerQr {

	@Autowired
	private serviceQr serviceQr;
		
	@PostMapping ( "/qr/insert" )
	public modelQrPayload functionQrInsert ( @RequestBody modelQrPayload modelQrPayload ) {
		
		return serviceQr.functionQrInsert( modelQrPayload );
	}
	
	@PostMapping ( "/qr/delete" )
	public modelQrPayload functionQrDelete ( @RequestBody modelQrPayload modelQrPayload ) {
		
		return serviceQr.functionQrDelete( modelQrPayload );
	}
}
