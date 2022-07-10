package com.tjexcel.proj.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//
//@Entity
//@Table(name="exceltb")
public class ExcelEntity {
	
	
//	@Column(name="TOTALCOST")
	private String TOTALCOST;
	
	
	private String CLAIMNUMBER;
	
	private String VENDOR;
	
	private String CHECKNO;
	
	private Date CHECKDATEPAID;
	
	private String INDICATOR;
	private String FILENAME;
	
	public String getTOTALCOST() {
		return TOTALCOST;
	}
	public void setTOTALCOST(String tOTALCOST) {
		TOTALCOST = tOTALCOST;
	}
	public String getCLAIMNUMBER() {
		return CLAIMNUMBER;
	}
	public void setCLAIMNUMBER(String cLAIMNUMBER) {
		CLAIMNUMBER = cLAIMNUMBER;
	}
	public String getVENDOR() {
		return VENDOR;
	}
	public void setVENDOR(String vENDOR) {
		VENDOR = vENDOR;
	}
	public String getCHECKNO() {
		return CHECKNO;
	}
	public void setCHECKNO(String cHECKNO) {
		CHECKNO = cHECKNO;
	}
	public Date getCHECKDATEPAID() {
		return CHECKDATEPAID;
	}
	public void setCHECKDATEPAID(Date cHECKDATEPAID) {
		 
		CHECKDATEPAID=cHECKDATEPAID;
			
	
	}
	public String getINDICATOR() {
		return INDICATOR;
	}
	public void setINDICATOR(String iNDICATOR) {
		INDICATOR = iNDICATOR;
	}
	public String getFILENAME() {
		return FILENAME;
	}
	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}
	
	public ExcelEntity() {
		
	}
	public ExcelEntity(String tOTALCOST, String cLAIMNUMBER, String vENDOR, String cHECKNO, Date cHECKDATEPAID,
			String iNDICATOR, String fILENAME) {
		
		TOTALCOST = tOTALCOST;
		CLAIMNUMBER = cLAIMNUMBER;
		VENDOR = vENDOR;
		CHECKNO = cHECKNO;
		CHECKDATEPAID = cHECKDATEPAID;
		INDICATOR = iNDICATOR;
		FILENAME = fILENAME;
	}
	
	
	
}
