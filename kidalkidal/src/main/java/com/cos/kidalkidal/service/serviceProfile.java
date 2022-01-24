package com.cos.kidalkidal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.model.modelUserPayload;
import com.cos.kidalkidal.model.modelUser;

@Service
public class serviceProfile {
	
	@Autowired
	private serviceUser serviceUser;

	public modelUserPayload functionUserProfile( modelUserPayload modelUserPayload ) {
		
		modelUser modelUserResult = serviceUser.userSelect( modelUserPayload.getModelUser().getUserEmail() );
		
		if( modelUserResult == null ) {
			
			modelUserPayload.setModelUserPayload( 100 ,  "unregistered account." ,  new modelUser() );
			return modelUserPayload;
		}
		
		if( modelUserResult.getUserEmail().equals( modelUserPayload.getModelUser().getUserEmail() ) == false ) {
		
			modelUserPayload.setModelUserPayload( 101 ,  "emails are different" ,  new modelUser() );			
			return modelUserPayload;
		}
		
		if( serviceUser.userUpdate( modelUserPayload.getModelUser() , modelUserPayload.getModelUser().getUserEmail() ) == 0 ) {
			
			modelUserPayload.setModelUserPayload( 104 ,  "fail update" ,  new modelUser() );
			return modelUserPayload;
		}		
	
		modelUserPayload.setModelUserPayload( 200 ,  "success update" ,  modelUserPayload.getModelUser() );
		return modelUserPayload;
	}
}
