package com.cos.kidalkidal.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.cos.kidalkidal.model.modelQr;

@Mapper
@Repository
public interface mapperQr {

	public int qrInsert( modelQr modelQr );
	
	public int qrDelete( String qrOwner );
	
	public modelQr qrSelectNumber( String qrOwner );
	
	public ArrayList<modelQr> qrSelectAll( String qrCompany );
}
