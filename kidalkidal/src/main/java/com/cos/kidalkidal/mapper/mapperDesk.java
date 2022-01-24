package com.cos.kidalkidal.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.cos.kidalkidal.model.modelDesk;

@Mapper
@Repository
public interface mapperDesk {
	
	public modelDesk deskSelect( int deskId );
	
	public ArrayList< modelDesk > deskSelectAll();
	
	public int deskInsert( modelDesk modelDesk );
	
	public int deskUpdate( HashMap< String , Object > hashmapUser );
	
	public int deskUpdateIncrease( int deskId );
	
	public int deskDeleteById( int deskId );
	
	public int deskDeleteByWriter( String deskWriter );
}
