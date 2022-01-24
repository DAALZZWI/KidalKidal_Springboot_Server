package com.cos.kidalkidal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cos.kidalkidal.mapper.mapperCompany;
import com.cos.kidalkidal.model.modelCompany;

@Service
public class serviceCompany {

	@Autowired
	private mapperCompany mapperCompany;
	
	public String functionCompanySelect( String companyCode ) {
		
		String companyCodeResult = "";
		
		companyCodeResult = mapperCompany.companySelect( companyCode );
		
		if( companyCodeResult != null ) {
			
			return companyCodeResult;
		} else {
			
			companyCodeResult = "";
			return companyCodeResult;
		}
	}
	
	public List< modelCompany > functionCompanySelectAll() {
		
		List< modelCompany > companyList = new ArrayList<>();
		
		companyList = mapperCompany.companySelectAll();
		
		return companyList;
	}
	
}
