package com.js.ens.hrolling3d_ver2.core.tableDatas;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver2.core.InitValue;
import com.js.parser.ParserDefault;

public class TableData_Variable {
	
	private String VAR1 = "";
	private String VAR2 = "";
	private String VAR3 = "";
	private String VAR4 = "";
	private String VAR5 = "";
	private String VAR6 = "";
	private String VAR7 = "";
	private String VAR8 = "";
	private String VAR9 = "";
	private String VAR10 = "";
	private String VAR11 = "";
	private String VAR12 = "";
	private String VAR13 = "";
	private String VAR14 = "";
	private String VAR15 = "";
	private String VAR16 = "";
	
	public TableData_Variable() {
		// TODO Auto-generated constructor stub
	}
	
	public void setAllData(String data){
		ArrayList<String> tempList = ParserDefault.splitLineData(data, ",");
		
		this.setVAR1((tempList.get(0).length()!=0)?tempList.get(0):"");
		this.setVAR2((tempList.get(1).length()!=0)?tempList.get(1):"");
		this.setVAR3((tempList.get(2).length()!=0)?tempList.get(2):"");
		this.setVAR4((tempList.get(3).length()!=0)?tempList.get(3):"");
		this.setVAR5((tempList.get(4).length()!=0)?tempList.get(4):"");
		this.setVAR6((tempList.get(5).length()!=0)?tempList.get(5):"");
		this.setVAR7((tempList.get(6).length()!=0)?tempList.get(6):"");
		this.setVAR8((tempList.get(7).length()!=0)?tempList.get(7):"");
		this.setVAR9((tempList.get(8).length()!=0)?tempList.get(8):"");
		this.setVAR10((tempList.get(9).length()!=0)?tempList.get(9):"");
		this.setVAR11((tempList.get(10).length()!=0)?tempList.get(10):"");
		this.setVAR12((tempList.get(11).length()!=0)?tempList.get(11):"");
		this.setVAR13((tempList.get(12).length()!=0)?tempList.get(12):"");
		this.setVAR14((tempList.get(13).length()!=0)?tempList.get(13):"");
		this.setVAR15((tempList.get(14).length()!=0)?tempList.get(14):"");
		this.setVAR16((tempList.get(15).length()!=0)?tempList.get(15):"");
	}
	
	public String getVAR1() {
		return VAR1;
	}

	public void setVAR1(String vAR1) {
		VAR1 = vAR1;
	}

	public String getVAR2() {
		return VAR2;
	}

	public void setVAR2(String vAR2) {
		VAR2 = vAR2;
	}

	public String getVAR3() {
		return VAR3;
	}

	public void setVAR3(String vAR3) {
		VAR3 = vAR3;
	}

	public String getVAR4() {
		return VAR4;
	}

	public void setVAR4(String vAR4) {
		VAR4 = vAR4;
	}

	public String getVAR5() {
		return VAR5;
	}

	public void setVAR5(String vAR5) {
		VAR5 = vAR5;
	}

	public String getVAR6() {
		return VAR6;
	}

	public void setVAR6(String vAR6) {
		VAR6 = vAR6;
	}

	public String getVAR7() {
		return VAR7;
	}

	public void setVAR7(String vAR7) {
		VAR7 = vAR7;
	}

	public String getVAR8() {
		return VAR8;
	}

	public void setVAR8(String vAR8) {
		VAR8 = vAR8;
	}

	public String getVAR9() {
		return VAR9;
	}

	public void setVAR9(String vAR9) {
		VAR9 = vAR9;
	}

	public String getVAR10() {
		return VAR10;
	}

	public void setVAR10(String vAR10) {
		VAR10 = vAR10;
	}

	public String getVAR11() {
		return VAR11;
	}

	public void setVAR11(String vAR11) {
		VAR11 = vAR11;
	}

	public String getVAR12() {
		return VAR12;
	}

	public void setVAR12(String vAR12) {
		VAR12 = vAR12;
	}

	public String getVAR13() {
		return VAR13;
	}

	public void setVAR13(String vAR13) {
		VAR13 = vAR13;
	}

	public String getVAR14() {
		return VAR14;
	}

	public void setVAR14(String vAR14) {
		VAR14 = vAR14;
	}

	public String getVAR15() {
		return VAR15;
	}

	public void setVAR15(String vAR15) {
		VAR15 = vAR15;
	}

	public String getVAR16() {
		return VAR16;
	}

	public void setVAR16(String vAR16) {
		VAR16 = vAR16;
	}
	
	public ArrayList<String> getDB(){
		ArrayList<String> DB = new ArrayList<String>();
		DB.add("#########################################");
		DB.add("#->VariableInfo");
		
		DB.add(InitValue.VAR1	 	+"="+	this.VAR1);
		DB.add(InitValue.VAR2	 	+"="+	this.VAR2);
		DB.add(InitValue.VAR3	 	+"="+	this.VAR3);
		DB.add(InitValue.VAR4	 	+"="+	this.VAR4);
		DB.add(InitValue.VAR5	 	+"="+	this.VAR5);
		DB.add(InitValue.VAR6	 	+"="+	this.VAR6);
		DB.add(InitValue.VAR7	 	+"="+	this.VAR7);
		DB.add(InitValue.VAR8	 	+"="+	this.VAR8);
		DB.add(InitValue.VAR9	 	+"="+	this.VAR9);
		DB.add(InitValue.VAR10	 	+"="+	this.VAR10);
		DB.add(InitValue.VAR11	 	+"="+	this.VAR11);
		DB.add(InitValue.VAR12	 	+"="+	this.VAR12);
		DB.add(InitValue.VAR13	 	+"="+	this.VAR13);
		DB.add(InitValue.VAR14	 	+"="+	this.VAR14);
		DB.add(InitValue.VAR15	 	+"="+	this.VAR15);
		DB.add(InitValue.VAR16	 	+"="+	this.VAR16);
		DB.add("#########################################");
		
		return DB;
	}
	
	public void printAllData(){
		//System.out.println("<<Table - P Log>>");
		for(String line : this.getDB()){
			System.out.println(line);
		}
	}
}
