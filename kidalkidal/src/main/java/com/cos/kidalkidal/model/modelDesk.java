package com.cos.kidalkidal.model;

import java.io.Serializable;

public class modelDesk implements Serializable {

	private static final long serialVersionUID = 2121372431481537862L;
	
	private int deskId;
    private String deskTitle;
    private String deskSubTitle;
    private String deskWriter;
    private String deskRegisterDate;
	private String deskVisible;
	private String deskStatus;
	private String deskImage;
	private int deskSee;

    public modelDesk() {

    	this.deskId = 0;
        this.deskTitle = "";
        this.deskSubTitle = "";
        this.deskWriter = "";
        this.deskRegisterDate = "";
        this.deskVisible = "";
        this.deskStatus = "";
        this.deskImage = "";
        this.deskSee = 0;
    }
    
    public String getDeskRegisterDate() { return deskRegisterDate; }

	public void setDeskRegisterDate( String deskRegisterDate ) { this.deskRegisterDate = deskRegisterDate; }

	public String getDeskVisible() { return deskVisible; }

    public void setDeskVisible( String deskVisible ) { this.deskVisible = deskVisible; }
	
	public String getDeskStatus() { return deskStatus; }

	public void setDeskStatus( String deskStatus ) { this.deskStatus = deskStatus; }

	public int getDeskId() { return deskId; }

	public void setDeskId( int deskId ) { this.deskId = deskId; }
	
	public String getDeskTitle() { return deskTitle; }

    public void setDeskTitle( String deskTitle ) { this.deskTitle = deskTitle; }

    public String getDeskSubTitle() { return deskSubTitle; }

    public void setDeskSubTitle( String deskSubTitle ) { this.deskSubTitle = deskSubTitle; }

    public String getDeskWriter() { return deskWriter; }

    public void setDeskWriter( String deskWriter ) { this.deskWriter = deskWriter; }
    
    public String getDeskImage() { return deskImage; }

    public void setDeskImage( String deskImage ) { this.deskImage = deskImage; }

	public int getDeskSee() { return deskSee; }

	public void setDeskSee( int deskSee ) { this.deskSee = deskSee; }
    
}
