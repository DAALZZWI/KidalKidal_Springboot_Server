package com.cos.kidalkidal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.mapper.mapperDesk;
import com.cos.kidalkidal.model.modelDesk;
import com.cos.kidalkidal.model.modelDeskPayload;
import com.cos.kidalkidal.model.modelUserImage;

@Service
public class serviceDesk {
	
	@Autowired
	private mapperDesk mapperDesk;

	@Autowired
	private serviceUser serviceUser;
	
	public modelDeskPayload functionUserDesk( modelDeskPayload modelDeskPayload ) {
		
		modelDeskPayload.getModelDesk().setDeskRegisterDate( functionDeskRegisterDate() );
		modelDeskPayload.getModelDesk().setDeskStatus( "y" );
		
		if( mapperDesk.deskInsert( modelDeskPayload.getModelDesk() ) == 0 ) {
			
			modelDeskPayload.setModelDeskPayload( 100 ,  "fail sending" ,  new modelDesk() );
		} 

		modelDeskPayload.setModelDeskPayload( 200 ,  "success sending" ,  new modelDesk() );
		return modelDeskPayload;
	}
	
	public modelDeskPayload functionUserDeskAll() {
		
		modelDeskPayload modelDeskPayload = new modelDeskPayload();
		ArrayList< modelDesk > model = mapperDesk.deskSelectAll();
		ArrayList< modelDesk > modelResult = new ArrayList< modelDesk >();
		
		if( model == null ) {
			
			modelDeskPayload.setModelDesksPayload( 100 , "fail" , new ArrayList< modelDesk >() );
			return modelDeskPayload;
		}
		
		for( modelDesk d : model ) {
			
			modelUserImage result = new modelUserImage();
			result = serviceUser.userSelectImage( d.getDeskWriter() );
			
			if( !result.getUserImage().isEmpty() ) {

				d.setDeskImage( result.getUserImage() );
				modelResult.add( d );
			}
		}

		modelDeskPayload.setModelDesksPayload( 200 , "success" , modelResult );
		return modelDeskPayload;
	}
	
	public modelDeskPayload functionUserDeskIncrease( modelDeskPayload modelDeskPayload ) {
		
		if( deskUpdateToIncrease( modelDeskPayload.getModelDesk().getDeskId() ) == 0 ) {
			
			modelDeskPayload.setModelDeskPayload( 100 ,  "fail adding" ,  new modelDesk() );
			return modelDeskPayload;
		}
		
		modelDeskPayload.setModelDeskPayload( 200 ,  "success adding" ,  new modelDesk() );
		return modelDeskPayload;
	}
	
	public String functionDeskRegisterDate() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		String day = dateFormat.format( new Date() );
		
		return day;
	}
	
	public modelDesk deskSelect( int deskId ) {
		
		modelDesk model = mapperDesk.deskSelect( deskId );
		if( model != null ) {
			
			return model;
		} else {
			
			model = null;
			return model;
		}
	}
	
	public modelDeskPayload functionUserDeskDelete( modelDeskPayload modelDeskPayload ) {
		
		if( deskDeleteById( modelDeskPayload.getModelDesk().getDeskId() ) > 0 ) {
			
			modelDeskPayload.setModelDeskPayload( 200 ,  "success delete" ,  new modelDesk() );
		} else {
			
			modelDeskPayload.setModelDeskPayload( 100 ,  "fail delete" ,  new modelDesk() );
		}
		return modelDeskPayload;
	}
	
	public List< modelDesk > deskSelectAll() {
		
		List<modelDesk> model = null;
		model = mapperDesk.deskSelectAll();
		
		if( model != null ) {
			
			return model;
		} else {
			
			model = null;
			return model;
		}
	}
	
	public int deskInsert( modelDesk modelDesk ) {
		
		int result = 0;

		try {
			
			if( mapperDesk.deskInsert( modelDesk ) > 0 ) {
				
				result =  1;
			}
		} catch (Exception e) {
			
			System.out.println(e);
			result = 0;
		}
		
		return result;
	}
	
	public int deskUpdate( modelDesk modelDesk , String deskWriter ) {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put( "modelDesk" , modelDesk );
		map.put( "targetwriter" , deskWriter );
		
		if( mapperDesk.deskUpdate( map ) > 0 ) {
			
			return 1;
		} else {
			
			return 0;
		}
	}
	
	public int deskUpdateToIncrease( int deskId ) {
		
		if( mapperDesk.deskUpdateIncrease( deskId ) > 0 ) {
			
			return 1;
		} else {
			
			return 0;
		}
	} 
	
	public int deskDeleteById( int deskId ) {
		
		int result = 0;
		
		try {
			
			if( mapperDesk.deskDeleteById( deskId ) > 0 ) {
				
				result = 1;
			}
			
		} catch( Exception e) {
			
			result = 0;
		}
		
		return result;
	}
	
	public int deskDeleteByEmail( String deskWriter ) {
		
		int result = 0;
		
		try {
			
			if( mapperDesk.deskDeleteByWriter( deskWriter ) > 0 ) {
				
				result = 1;
			}
			
		} catch( Exception e) {
			
			result = 0;
		}
		
		return result;
	}
}
