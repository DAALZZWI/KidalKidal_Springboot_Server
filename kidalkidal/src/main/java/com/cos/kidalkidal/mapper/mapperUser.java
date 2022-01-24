package com.cos.kidalkidal.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.cos.kidalkidal.model.modelUser;
import com.cos.kidalkidal.model.modelUserImage;

@Mapper
@Repository
public interface mapperUser {
	
	public modelUser userSelect( String userEmail );
	
	public modelUser userSelectByToken( String userToken );
	
	public modelUserImage userSelectImage( String userEmail );
	
	public String userSelectFindEmail( String userName );
	
	public int userInsert( modelUser modelUser );
	
	public int userUpdate( HashMap< String , Object > hashmapUser );
	
	public int userDelete( String userEmail );
}
