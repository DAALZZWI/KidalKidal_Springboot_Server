package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelVisitor implements Serializable{

	private static final long serialVersionUID = -5628072164027641708L;
	
	private String visitorType;
    private String visitorName;
    private String visitorImage;
    private String visitorNumber;

    public modelVisitor() {

        visitorType = "";
        visitorName = "";
        visitorImage = null;
        visitorNumber = "";
    }

    public String getVisitorType() { return visitorType; }

    public void setVisitorType( String visitorType ) { this.visitorType = visitorType; }

    public String getVisitorName() { return visitorName; }

    public void setVisitorName( String visitorName ) { this.visitorName = visitorName; }

    public String getVisitorImage() { return visitorImage; }

    public void setVisitorImage( String visitorImage ) { this.visitorImage = visitorImage; }

    public String getVisitorNumber() { return visitorNumber; }

    public void setVisitorNumber( String visitorNumber ) { this.visitorNumber = visitorNumber; }
}
