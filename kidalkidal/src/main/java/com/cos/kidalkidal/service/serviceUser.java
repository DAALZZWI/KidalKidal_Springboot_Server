package com.cos.kidalkidal.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.mapper.mapperUser;
import com.cos.kidalkidal.model.modelUser;
import com.cos.kidalkidal.model.modelUserImage;

@Service
public class serviceUser {
	
	@Autowired
	private mapperUser mapperUser;
	
	public modelUser userSelect( String userEmail ) {
		
		modelUser model = mapperUser.userSelect( userEmail );
		
		if( model != null ) {
			
			return model;
		} else {
			
			model = null;
			return model;
		}
	}
	
	public modelUser userSelectByToken( String userToken ) {
		
		modelUser model = mapperUser.userSelectByToken( userToken );
		
		if( model != null ) {
			
			return model;
		} else {
			
			model = null;
			return model;
		}
	}
	
	public modelUserImage userSelectImage( String userEmail ) {
		
		modelUserImage result = new modelUserImage();
		result = mapperUser.userSelectImage( userEmail );
		
		if( result == null ) {
			
			result = new modelUserImage();
		} 
		
		return result;
	}
	
	public int userInsert( modelUser modelUser ) {
		
		int result = 0;

		try {
			
			if( mapperUser.userInsert ( modelUser ) > 0 ) {
				
				result =  1;
			}
		} catch (Exception e) {
			
			System.out.println(e);
			result = 0;
		}
		
		return result;
	}
	
	public int userUpdate( modelUser modelUser , String userEmail ) {
		
		HashMap< String , Object > map = new HashMap< String , Object >();
		map.put( "modelUser" , modelUser );
		map.put( "targetEmail" , userEmail );
		
		if( mapperUser.userUpdate(map) > 0 ) {
			
			return 1;
		} else {
			
			return 0;
		}
	}
	
	public String userSelectFindEmail( modelUser modelUser ) {
		
		String email = mapperUser.userSelectFindEmail( modelUser.getUserName() );
		
		if( email != null ) {
			
			return email;
		} else {
			
			return "";
		}
	}
	
	public int userDelete( String userEmail ) {
		
		int result = 0;
		
		try {
			
			if( mapperUser.userDelete( userEmail ) > 0 ) {
				
				result = 1;
			}
			
		} catch( Exception e) {
			
			result = 0;
		}
		
		return result;
	}
}
