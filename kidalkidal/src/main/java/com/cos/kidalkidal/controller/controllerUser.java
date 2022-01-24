package com.cos.kidalkidal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cos.kidalkidal.model.modelUser;
import com.cos.kidalkidal.model.modelUserPayload;
import com.cos.kidalkidal.service.serviceFindAccount;
import com.cos.kidalkidal.service.serviceLogin;
import com.cos.kidalkidal.service.serviceProfile;
import com.cos.kidalkidal.service.serviceRegister;
import com.cos.kidalkidal.service.serviceUser;

@RestController
public class controllerUser {
	
	@Autowired
	private serviceLogin serviceLogin;
	
	@Autowired
	private serviceRegister serviceRegister;
	
	@Autowired
	private serviceFindAccount serviceFindAccount;
	
	@Autowired
	private serviceProfile serviceProfile;
	
	@Autowired
	private serviceUser serviceUser;
	
	@PostMapping ( "/user/register" )
	public modelUserPayload userRegister ( @RequestBody modelUserPayload modelUserPayload ) {
		
		return serviceRegister.functionUserRegister( modelUserPayload );
	}
	
	@PostMapping ( "/user/unregister" )
	public modelUserPayload userUnRegister ( @RequestBody modelUserPayload modelUserPayload ) {
		
		return serviceRegister.functionUserUnRegister( modelUserPayload );
	}
	
	@PostMapping ( "/user/login" )
	public modelUserPayload userLogin ( @RequestBody modelUserPayload modelUserPayload ) {
		
		return serviceLogin.functionUserLogin( modelUserPayload );
	}
	
	@PostMapping ( "/user/find/email" )
	public modelUserPayload userFindEmail ( @RequestBody modelUserPayload modelUserPayload ) {
		
		return serviceFindAccount.functionUserFindEmail( modelUserPayload );
	}
	
	@PostMapping ( "/user/find/password" )
	public modelUserPayload userFindPassword ( @RequestBody modelUserPayload modelUserPayload ) {

		return serviceFindAccount.functionUserFindPassword( modelUserPayload );
	}
	
	@PostMapping ( "/user/profile" )
	public modelUserPayload userProfile ( @RequestBody modelUserPayload modelUserPayload ) {
		
		return serviceProfile.functionUserProfile( modelUserPayload );
	}
	
	@GetMapping("/userSelect")
	public modelUser userSelect(@RequestParam("email") String userEmail) {
		
		modelUser model = serviceUser.userSelect(userEmail);
		return model;
	}
}
