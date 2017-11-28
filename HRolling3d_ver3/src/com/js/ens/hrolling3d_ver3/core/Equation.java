package com.js.ens.hrolling3d_ver3.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class Equation {
	private Map<String,String> equationMap;
	private String equationPath;
	private static final double PI = Math.PI;
	
	public Equation() {
		equationMap = new HashMap<String,String>();
		equationPath = FolderTree.filePath_equation;
		// TODO Auto-generated constructor stub
		/*
			// Analysis Time(sec.) 
			lcase_time = #p_len# * #p_thk# /( #roll_gap# * #pl_vel_mpm# * 1000.0 / 60.0 ) * #ltime_scale#
			
			// Time increment(sec.) 
			lcase_dt = #lcase_time# / #lcase_inc# 
			
			// Top WR Rot. Vel.(RPM) 
			wr_trot = #pl_vel_mpm# * 1000 /60 / ( #wr_tdia# / 2 ) * ( #vel_rate_top# / 100 ) * 60 / ( 2 * @pi )
			
			// Bottom WR Rot. Vel.(RPM) 
			wr_brot = #pl_vel_mpm# * 1000 / 60 / ( #wr_bdia# / 2 ) * ( #vel_rate_bottom# /100 ) * 60 / ( 2 * @pi )
			
			// Top BUR Rot. Vel.(RPM) 
			bur_trot = #pl_vel_mpm# * 1000 / 60 / ( #bur_tdia# / 2 ) * ( #vel_rate_top# / 100 ) * 60 / ( 2 * @pi )
			
			// Bottom BUR Rot. Vel.(RPM) 
			bur_brot = #pl_vel_mpm# * 1000 / 60 / ( #bur_bdia# / 2 ) * ( #vel_rate_bottom# / 100 ) * 60 / ( 2 * @pi )
		*/
	}
	
	public void readEquationFile(){
		Reader obj = new Reader(equationPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,"=");
				String key = "";
				String value = "";
				if(splitDataTokens.size()==1){
					key = splitDataTokens.get(0).trim();
					value = "";
					equationMap.put(key,value);
				}else{
					key = splitDataTokens.get(0).trim();
					value = splitDataTokens.get(1).trim();
					equationMap.put(key,value);
				}
				 
			}
		}
	}
	
	public String getEquation(String key){
		if(equationMap.containsKey(key)){
			return equationMap.get(key);
		}else{
			return "no-EQValue";
		}
	}
	
	// key 
	public static String lcase_time = "lcase_time";
	public static String lcase_dt = "lcase_dt";
	public static String lcase_inc = "lcase_inc";
	public static String wr_trot = "wr_trot"; 
	public static String wr_brot = "wr_brot";
	public static String bur_trot = "bur_trot";
	public static String bur_brot= "bur_brot";
	public static String pi = "@pi";
	// mapping
	
	

}
