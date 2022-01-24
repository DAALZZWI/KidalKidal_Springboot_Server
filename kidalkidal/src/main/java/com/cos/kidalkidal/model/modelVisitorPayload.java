package com.cos.kidalkidal.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class modelVisitorPayload implements Serializable {

	private static final long serialVersionUID = 7895938791376277199L;
	
	private Map< String , String > visitorImage;
    private String visitorRoomType;

    modelVisitorPayload() {

        this.visitorImage = new HashMap< String , String >();
        this.visitorRoomType = "";
    }

    public Map< String , String > getVisitorImage() { return visitorImage; }

    public void setVisitorImage( Map< String , String > visitorImage ) { this.visitorImage = visitorImage; }

    public String getVisitorRoomType() { return visitorRoomType; }

    public void setVisitorRoomType( String visitorRoomType ) { this.visitorRoomType = visitorRoomType; }
}
