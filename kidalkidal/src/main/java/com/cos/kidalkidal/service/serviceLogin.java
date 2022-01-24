package com.cos.kidalkidal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.model.modelUserPayload;
import com.cos.kidalkidal.model.modelUser;

@Service
public class serviceLogin {
	
	@Autowired
	private serviceUser serviceUser;
	
	public modelUserPayload functionUserLogin ( modelUserPayload modelUserPayload ) {
		
		modelUserPayload = functionUserEmailPasswordLogin ( modelUserPayload.getModelUser() );
		
		return modelUserPayload;
	}
	
	public modelUserPayload functionUserEmailPasswordLogin ( modelUser modelUser ) {

		modelUserPayload modelUserPayload = new modelUserPayload();
		modelUser modelUserResult = new modelUser();
		
		if( modelUser.getUserEmail().isEmpty() || modelUser.getUserPassword().isEmpty() ) {
			
			modelUserPayload.setModelUserPayload( 100 , "fail login : Email and password are empty" , new modelUser() );
			return modelUserPayload;
		}
		
		modelUserResult = serviceUser.userSelect( modelUser.getUserEmail() );
		
		if( modelUserResult == null ) {
			
			modelUserPayload.setModelUserPayload( 101 , "fail login : Database user information cannot be called" , new modelUser() );
			return modelUserPayload;
		}
		
		if( !modelUser.getUserEmail().equals( modelUserResult.getUserEmail() ) || !modelUser.getUserPassword().equals ( modelUserResult.getUserPassword() ) ) {
			
			modelUserPayload.setModelUserPayload( 102 , "fail login : Email password doesn't match" , new modelUser() );
			return modelUserPayload;
		}
		
		modelUserPayload.setModelUserPayload( 200 , "success login" , modelUserResult );
		return modelUserPayload;
	}
}
