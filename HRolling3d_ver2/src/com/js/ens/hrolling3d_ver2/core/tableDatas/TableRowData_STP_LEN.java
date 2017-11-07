package com.js.ens.hrolling3d_ver2.core.tableDatas;

import java.util.ArrayList;

public class TableRowData_STP_LEN extends TableRowData{
	private String name = "STP LEN";
	public String getName(){
		return name;
	}
	private String F1Value = "";
	private String F2Value = "";
	private String F3Value = "";
	private String F4Value = "";
	private String F5Value = "";
	private String F6Value = "";
	private String F7Value = "";
	
	private ArrayList<TableData_PLog> tableDataPLogList = null;
	
	public TableRowData_STP_LEN() {
		// TODO Auto-generated constructor stub
		this.tableDataPLogList = new ArrayList<TableData_PLog>();
	}

	public void setAllData(ArrayList<TableData_PLog> obj){
		this.tableDataPLogList = obj;
		for(TableData_PLog o : obj){
			if(o.getSTAND().equals("F1")){
				this.setF1Value(o.getSTP_LEN());
			}else if(o.getSTAND().equals("F2")){
				this.setF2Value(o.getSTP_LEN());
			}else if(o.getSTAND().equals("F3")){
				this.setF3Value(o.getSTP_LEN());
			}else if(o.getSTAND().equals("F4")){
				this.setF4Value(o.getSTP_LEN());
			}else if(o.getSTAND().equals("F5")){
				this.setF5Value(o.getSTP_LEN());
			}else if(o.getSTAND().equals("F6")){
				this.setF6Value(o.getSTP_LEN());
			}else if(o.getSTAND().equals("F7")){
				this.setF7Value(o.getSTP_LEN());
			}
		}
		
	}

	public String getF1Value() {
		return F1Value;
	}

	public void setF1Value(String f1Value) {
		F1Value = f1Value;
	}

	public String getF2Value() {
		return F2Value;
	}

	public void setF2Value(String f2Value) {
		F2Value = f2Value;
	}

	public String getF3Value() {
		return F3Value;
	}

	public void setF3Value(String f3Value) {
		F3Value = f3Value;
	}

	public String getF4Value() {
		return F4Value;
	}

	public void setF4Value(String f4Value) {
		F4Value = f4Value;
	}

	public String getF5Value() {
		return F5Value;
	}

	public void setF5Value(String f5Value) {
		F5Value = f5Value;
	}

	public String getF6Value() {
		return F6Value;
	}

	public void setF6Value(String f6Value) {
		F6Value = f6Value;
	}

	public String getF7Value() {
		return F7Value;
	}

	public void setF7Value(String f7Value) {
		F7Value = f7Value;
	}

	public void setName(String name) {
		this.name = name;
	}

}
