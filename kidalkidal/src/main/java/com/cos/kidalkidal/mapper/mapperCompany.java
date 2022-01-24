package com.cos.kidalkidal.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cos.kidalkidal.model.modelCompany;

@Mapper
@Repository
public interface mapperCompany {
	
	public String companySelect( String companyCode );
	
	public ArrayList< modelCompany >companySelectAll();
}
