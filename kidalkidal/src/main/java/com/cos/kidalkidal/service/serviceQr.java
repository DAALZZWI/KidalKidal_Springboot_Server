package com.cos.kidalkidal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.kidalkidal.mapper.mapperQr;
import com.cos.kidalkidal.model.modelQr;
import com.cos.kidalkidal.model.modelQrPayload;

@Service
public class serviceQr {

	@Autowired
	private mapperQr mapperQr;
	
	@Autowired
	private serviceCompany serviceCompany;
	
	public modelQrPayload functionQrInsert( modelQrPayload modelQrPayload ) {
		
		modelQr modelQr = new modelQr();
		
		if( serviceCompany.functionCompanySelect( modelQrPayload.getModelQr().getQrCompany() ) == null  ) {
			
			modelQrPayload.setModelQrPayload( 103 , "fail register : none company" , new modelQr() );
		}
		
		modelQr = qrSelect( modelQrPayload.getModelQr().getQrOwner() );
		
		if( modelQr != null ) {
			
			String result = serviceCompany.functionCompanySelect( modelQrPayload.getModelQr().getQrCompany() ); 
			modelQr.setQrCompany( result );
			modelQrPayload.setModelQrPayload( 102 , "fail register : already exist" , modelQr );
		}
			
		if( qrInsert( modelQrPayload.getModelQr() ) == 0 ) {
			
			modelQrPayload.setModelQrPayload( 100 , "fail register" , new modelQr() );
		}
			
		modelQr = qrSelect( modelQrPayload.getModelQr().getQrOwner() );
		
		if( modelQr == null ) {
			
			qrDelete( modelQrPayload.getModelQr().getQrOwner() );
			
			modelQrPayload.setModelQrPayload( 101 , "fail register" , new modelQr() );
		}
		
		String result = serviceCompany.functionCompanySelect( modelQrPayload.getModelQr().getQrCompany() ); 
		modelQr.setQrCompany( result );
		modelQrPayload.setModelQrPayload( 200 , "success register" , modelQr );
		
		return modelQrPayload;
	}
	
	public modelQrPayload functionQrDelete( modelQrPayload modelQrPayload ) {
		
		
		if( qrDelete( modelQrPayload.getModelQr().getQrOwner() ) == 0 ) {
			
			modelQrPayload.setModelQrPayload( 100 , "fail unregister" , new modelQr() );
		}
		
		modelQrPayload.setModelQrPayload( 200 , "success unregister" , new modelQr() );
		return modelQrPayload;
	}
	
	public modelQr qrSelect( String qrOwner ) {
		
		modelQr model = mapperQr.qrSelectNumber( qrOwner );
		
		if( model != null ) {
			
			return model;
		} else {
			
			model = null;
			return model;
		}
	}
	
	public List<modelQr> qrSelectAll( String qrCompany) {
		
		List<modelQr> model = null;
		model = mapperQr.qrSelectAll( qrCompany );
		
		if( model != null ) {
			
			return model;
		} else {
			
			model = null;
			return model;
		}
	}
	
	
	public int qrInsert( modelQr modelQr ) {
		
		int result = 0;

		try {
			
			result = mapperQr.qrInsert( modelQr );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int qrDelete( String tokenOwner ) {
		
		int result = 0;

		try {
			
			if( mapperQr.qrDelete( tokenOwner ) > 0 ) {
				
				result = 1;
			}
		} catch ( Exception e ) {
			
		}
		
		return result;
	}
}
