package com.cos.kidalkidal.model;

import java.io.Serializable;
import java.util.ArrayList;

public class modelQrPayload implements Serializable {

	private static final long serialVersionUID = -6721528616130224585L;
	
	private int code;
    private String msg;
    private modelQr modelQr;
    private ArrayList< modelQr > modelQrs;

    modelQrPayload() {

        this.code = 0;
        this.msg = "";
        this.modelQr = new modelQr();
        this.modelQrs = new ArrayList< modelQr >();
    }

    public void setModelQrPayload( int code , String msg , modelQr modelQr ) {

        this.code = code;
        this.msg = msg;
        this.modelQr = modelQr;
    }

    public void setModelQrsPayload( int code , String msg , ArrayList< modelQr > modelQrs ) {

        this.code = code;
        this.msg = msg;
        this.modelQrs = modelQrs;
    }

    public int getCode() { return code; }

    public void setCode( int code ) { this.code = code; }

    public String getMsg() { return msg; }

    public void setMsg( String msg ) { this.msg = msg; }

    public modelQr getModelQr() { return modelQr; }

    public void setModelQr( modelQr modelQr ) { this.modelQr = modelQr; }

    public ArrayList< modelQr > getModelQrs() { return modelQrs; }

    public void setModelQrs( ArrayList< modelQr > modelQrs ) { this.modelQrs = modelQrs; }
}

