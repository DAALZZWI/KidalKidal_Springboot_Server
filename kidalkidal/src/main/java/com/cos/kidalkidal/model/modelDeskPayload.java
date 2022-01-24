package com.cos.kidalkidal.model;

import java.io.Serializable;
import java.util.ArrayList;

public class modelDeskPayload implements Serializable {

	private static final long serialVersionUID = 6811685685205318896L;
	
	private int code;
    private String msg;
    private modelDesk modelDesk;
    private ArrayList<modelDesk> modelDesks;

    public modelDeskPayload() {

        this.code = 0;
        this.msg = "";
        this.modelDesk = new modelDesk();
        this.modelDesks = new ArrayList<modelDesk>();
    }

    public void setModelDeskPayload( int code , String msg , modelDesk modelDesk ) {
		
		this.code = code;
		this.msg = msg;
		this.modelDesk = modelDesk;
	}
    
    public void setModelDesksPayload( int code , String msg , ArrayList<modelDesk> modelDesks ) {
		
		this.code = code;
		this.msg = msg;
		this.modelDesks = modelDesks;
	}
    
    public int getCode() { return code; }

    public void setCode( int code ) { this.code = code; }

    public String getMsg() { return msg; }

    public void setMsg( String msg ) { this.msg = msg; }

    public modelDesk getModelDesk() { return modelDesk; }

    public void setModelDesk( modelDesk modelDesk ) { this.modelDesk = modelDesk; }

    public ArrayList<modelDesk> getModelDesks() { return modelDesks; }

    public void setModelDesks( ArrayList<modelDesk> modelDesks ) { this.modelDesks = modelDesks; }
}
