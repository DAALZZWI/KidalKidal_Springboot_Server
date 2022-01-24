package com.cos.kidalkidal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.model.modelEmail;
import com.cos.kidalkidal.model.modelUserPayload;
import com.cos.kidalkidal.model.modelUser;

@Service
public class serviceFindAccount {
	
	@Autowired
	private serviceUser serviceUser;
	
	@Autowired
	private serviceSendEmail serviceSendEmail;
	
	public modelUserPayload functionUserFindEmail ( modelUserPayload modelUserPayload ) {
		
		String email = serviceUser.userSelectFindEmail( modelUserPayload.getModelUser() );
		
		if( email != "" ) {
			
			modelUserPayload = new modelUserPayload();
			modelUser modelUser = new modelUser();
			modelUser.setUserEmail( email );
			
			modelUserPayload.setCode( 200 );
			modelUserPayload.setMsg( "success sending" );
			modelUserPayload.setModelUser( modelUser );
		} else {
			
			modelUserPayload = new modelUserPayload();
			modelUser modelUser = new modelUser();
			modelUser.setUserEmail( email );
			
			modelUserPayload.setCode( 100 );
			modelUserPayload.setMsg( "fail sending" );
			modelUserPayload.setModelUser( modelUser );
		}
		
		return modelUserPayload;
	}
	
	public modelUserPayload functionUserFindPassword ( modelUserPayload modelUserPayload ) {

		
		modelUser modelUserResult = serviceUser.userSelect( modelUserPayload.getModelUser().getUserEmail() );
		
		if( modelUserResult != null ) {
			
			if( functionSendEmail( modelUserResult ) == true ) {
				
				modelUserPayload.setModelUserPayload( 200 ,  "success sending" ,  new modelUser() );
			} else {
				
				modelUserPayload.setModelUserPayload( 100 ,  "fail sending" ,  new modelUser() );
			}
		} else {
			
			modelUserPayload.setModelUserPayload( 101 ,  "unregistered account" ,  new modelUser() );
		}
		return modelUserPayload;
	}
	
	public boolean functionSendEmail( modelUser modelUser ) {
		
		modelEmail modelEmailResult = new modelEmail();
		
		modelEmailResult.setEmailFrom( "kidalkidal.inc@gmail.com" );
		modelEmailResult.setEmailTo( modelUser.getUserEmail() );
		modelEmailResult.setEmailSubject( "[  기달기달 : 계정찾기  ] " + modelUser.getUserName() + "님! 비밀번호를 까먹으셨나요??" );
		modelEmailResult.setEmailText( modelUser.getUserName() + "님의 비밀번호는 " + modelUser.getUserPassword() + " 에요!!"  );
		
		if( serviceSendEmail.functionSendEmail( modelEmailResult ) == true ) {
			
			return true;
		} else {
			
			return false;
		}
	}
}
