package com.js.ens.hrolling3d_ver2.core.tableDatas;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver2.core.InitValue;
import com.js.parser.ParserDefault;

public class TableData_SlabPlateInfo {
	
	private String STRIP_NO = "";
	private String STHK = "";
	private String SWID = "";
	private String SLEN = "";
	private String SWET = "";
	private String PTHK = "";
	private String PWID = "";
	private String PLEN = "";
	private String PWET = "";
	
	public TableData_SlabPlateInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public void setAllData(String data){
		ArrayList<String> tempList = ParserDefault.splitLineData(data, ",");
		
		this.setSTRIP_NO((tempList.get(0).length()!=0)?tempList.get(0):"");
		this.setSTHK((tempList.get(1).length()!=0)?tempList.get(1):"");
		this.setSWID((tempList.get(2).length()!=0)?tempList.get(2):"");
		this.setSLEN((tempList.get(3).length()!=0)?tempList.get(3):"");
		this.setSWET((tempList.get(4).length()!=0)?tempList.get(4):"");
		this.setPTHK((tempList.get(5).length()!=0)?tempList.get(5):"");
		this.setPWID((tempList.get(6).length()!=0)?tempList.get(6):"");
		this.setPLEN((tempList.get(7).length()!=0)?tempList.get(7):"");
		this.setPWET((tempList.get(8).length()!=0)?tempList.get(8):"");
	}
	

	public String getSTRIP_NO() {
		return STRIP_NO;
	}

	public void setSTRIP_NO(String sTRIP_NO) {
		STRIP_NO = sTRIP_NO;
	}

	public String getSTHK() {
		return STHK;
	}

	public void setSTHK(String sTHK) {
		STHK = sTHK;
	}

	public String getSWID() {
		return SWID;
	}

	public void setSWID(String sWID) {
		SWID = sWID;
	}
	
	public String getSLEN() {
		return SLEN;
	}

	public void setSLEN(String sLEN) {
		SLEN = sLEN;
	}

	public String getSWET() {
		return SWET;
	}

	public void setSWET(String sWET) {
		SWET = sWET;
	}

	public String getPTHK() {
		return PTHK;
	}

	public void setPTHK(String pTHK) {
		PTHK = pTHK;
	}

	public String getPWID() {
		return PWID;
	}

	public void setPWID(String pWID) {
		PWID = pWID;
	}

	public String getPLEN() {
		return PLEN;
	}

	public void setPLEN(String pLEN) {
		PLEN = pLEN;
	}

	public String getPWET() {
		return PWET;
	}

	public void setPWET(String pWET) {
		PWET = pWET;
	}
	
	public void printAllData(){
		System.out.println("<<Table - SlabPlateInfo>>");
		for(String line : this.getDB()){
			System.out.println(line);
		}
	}
	
	public ArrayList<String> getDB(){
		ArrayList<String> DB = new ArrayList<String>();
		DB.add("#########################################");
		DB.add("#->SlabPlateInfo");
		
		DB.add(InitValue.STRIP_NO	+"="+	this.STRIP_NO);
		DB.add(InitValue.STHK	 	+"="+	this.STHK);
		DB.add(InitValue.SWID	 	+"="+	this.SWID);
		DB.add(InitValue.SLEN	 	+"="+	this.SLEN);
		DB.add(InitValue.SWET	 	+"="+	this.SWET);
		DB.add(InitValue.PTHK	 	+"="+	this.PTHK);
		DB.add(InitValue.PWID	 	+"="+	this.PWID);
		DB.add(InitValue.PLEN	 	+"="+	this.PLEN);
		DB.add(InitValue.PWET 		+"="+	this.PWET);

		return DB;
	}
	
	
	
}
