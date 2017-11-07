package com.js.ens.hrolling3d_ver3.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;


public class TableColumnLabel {
	private static TableColumnLabel instance = new TableColumnLabel();
	public static TableColumnLabel getInstance(){
		return instance;
	}
	
	
	private Map<String,String> tableColumnLabelMap;
	private String tableColumnLabelPath;
	
	public TableColumnLabel(){
		tableColumnLabelMap = new HashMap<String,String>();
		//tableColumnLabelPath = myUtil.setPath(System.getProperty("user.dir"), "TableColumnLabel.txt");	
		//tableColumnLabelPath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "userConfig"),"TableColumnLabel.txt");
		tableColumnLabelPath = FolderTree.filePath_TableColumLabel;
		this.readTableColumnLabelFile();
	}
	
	public void readTableColumnLabelFile(){
		Reader obj = new Reader(tableColumnLabelPath);
		//System.out.println("path : "+tableColumnLabelPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,"=");
				tableColumnLabelMap.put(splitDataTokens.get(0).trim(), splitDataTokens.get(1).trim());
				//System.out.println("key : "+splitDataTokens.get(0).trim());
				//System.out.println("value : "+splitDataTokens.get(1).trim());
			}
		}
	}
	
	public String getTableColumnLabel(String key){
		//System.out.println("Map size : "+tableColumnLabelMap.size());
		//myUtil.printMap(tableColumnLabelMap);
		//System.out.println("In KEY : "+key);
		//System.out.println("Out value : "+tableColumnLabelMap.get(key));
		if(tableColumnLabelMap.containsKey(key.trim())){
			return tableColumnLabelMap.get(key.trim());
		}else{
			return "no-ColumnData";
		}
	}
	
	//
	// Table 1 
	//
	public static String CL1_0 = "CL1_0";
	public static String CL1_1 = "CL1_1";
	public static String CL1_2 = "CL1_2";
	public static String CL1_3 = "CL1_3";
	public static String CL1_4 = "CL1_4";
	public static String CL1_5 = "CL1_5";
	public static String CL1_6 = "CL1_6";
	public static String CL1_7 = "CL1_7";
	public static String CL1_8 = "CL1_8";
	//
	// Table 2 
	//
	public static String CL2_0 = "CL2_0";
	public static String CL2_1 = "CL2_1";
	public static String CL2_2 = "CL2_2";
	public static String CL2_3 = "CL2_3";
	public static String CL2_4 = "CL2_4";
	public static String CL2_5 = "CL2_5";
	public static String CL2_6 = "CL2_6";
	public static String CL2_7 = "CL2_7";
	public static String CL2_8 = "CL2_8";
	public static String CL2_9 = "CL2_9";
	public static String CL2_10 = "CL2_10";
	public static String CL2_11 = "CL2_11";
	public static String CL2_12 = "CL2_12";
	public static String CL2_13 = "CL2_13";
	public static String CL2_14 = "CL2_14";
	public static String CL2_15 = "CL2_15";
	//
	// Table 3
	//
	public static String CL3_0 = "CL3_0";
	public static String CL3_1 = "CL3_1";
	public static String CL3_2 = "CL3_2";
	public static String CL3_3 = "CL3_3";
	public static String CL3_4 = "CL3_4";
	public static String CL3_5 = "CL3_5";
	public static String CL3_6 = "CL3_6";
	public static String CL3_7 = "CL3_7";
	public static String CL3_8 = "CL3_8";
	public static String CL3_9 = "CL3_9";
	public static String CL3_10 = "CL3_10";
	public static String CL3_11 = "CL3_11";
	public static String CL3_12 = "CL3_12";
	public static String CL3_13 = "CL3_13";
	public static String CL3_14 = "CL3_14";
	public static String CL3_15 = "CL3_15";
	public static String CL3_16 = "CL3_16";
	public static String CL3_17 = "CL3_17";
	public static String CL3_18 = "CL3_18";
	public static String CL3_19 = "CL3_19";
	public static String CL3_20 = "CL3_20";
	public static String CL3_21 = "CL3_21";
	public static String CL3_22 ="CL3_22";
	public static String CL3_23 = "CL3_23";
	public static String CL3_24 = "CL3_24";
}
	