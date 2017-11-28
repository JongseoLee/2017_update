package com.js.ens.hrolling3d_ver3.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class InitValue {
	private Map<String,String> initValueMap;
	private String initValuePath;
	private String YM_tableValuePath;
	private String FS_tableValuePath;
	private String TEC_tableValuePath;
	private String PR_tableValuePath;
	
	public InitValue(){
		this.initValueMap = new HashMap<String,String>();
		//initValuePath = myUtil.setPath(System.getProperty("user.dir"), "InitValue.txt");
		/*
		this.initValuePath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "userConfig"),"InitValue.txt");
		this.YM_tableValuePath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "materialData"), "elastic_modulus.txt");
		this.FS_tableValuePath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "materialData"), "flow_stress.txt");
		this.TEC_tableValuePath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "materialData"), "expansion.txt");
		this.PR_tableValuePath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "materialData"), "poisson.txt");
		//*/
		
		this.initValuePath = FolderTree.filePath_InitValue;
		this.YM_tableValuePath = FolderTree.filePath_YMTablePath;
		this.FS_tableValuePath = FolderTree.filePath_FSTablePath;
		this.TEC_tableValuePath = FolderTree.filePath_TECTablePath;
		this.PR_tableValuePath = FolderTree.filePath_PRTablePath;
		
	}
	
	public void readInitValueFile(){
		Reader obj = new Reader(initValuePath);
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
					initValueMap.put(key,value);
				}else{
					key = splitDataTokens.get(0).trim();
					value = splitDataTokens.get(1).trim();
					initValueMap.put(key,value);
				}
				 
			}
		}
	}
	
	public String getInitValue(String key){
		if(initValueMap.containsKey(key)){
			return initValueMap.get(key);
		}else{
			return "no-InitValue";
		}
	}
	

	//
	// basic
	//
	public static String STRIP_NO = "STRIP NO";
	public static String STHK = "STHK";
	public static String SWID = "SWID";
	public static String SLEN = "SLEN";
	public static String SWET = "SWET";
	public static String PTHK = "PTHK";
	public static String PWID = "PWID";
	public static String PLEN = "PLEN";
	public static String PWET = "PWET";
	//
	// var
	//
	public static String VAR1 = "VAR1";
	public static String VAR2 = "VAR2";
	public static String VAR3 = "VAR3";
	public static String VAR4 = "VAR4";
	public static String VAR5 = "VAR5";
	public static String VAR6 = "VAR6";
	public static String VAR7 = "VAR7";
	public static String VAR8 = "VAR8";
	public static String VAR9 = "VAR9";
	public static String VAR10 = "VAR10";
	public static String VAR11 = "VAR11";
	public static String VAR12 = "VAR12";
	public static String VAR13 = "VAR13";
	public static String VAR14 = "VAR14";
	public static String VAR15 = "VAR15";
	public static String VAR16 = "VAR16";
	//
	//Common
	//

	//group1 - F1
	public static String wr_len_F1 = "wr_len_F1";
	public static String wr_div_angle_F1 = "wr_div_angle_F1";
	public static String wr_chamferX_F1 = "wr_chamferX_F1";
	public static String wr_chamferY_F1 = "wr_chamferY_F1";
	public static String wr_round_F1 = "wr_round_F1";
	//group2
	public static String bur_len_F1 = "bur_len_F1";
	public static String bur_div_angle_F1 = "bur_div_angle_F1";
	public static String bur_chamferX_F1 = "bur_chamferX_F1";
	public static String bur_chamferY_F1 = "bur_chamferY_F1";

	//group3
	public static String p_in_F1 = "p_in_F1";
	public static String pl_m_F1 = "pl_m_F1";
	public static String t_div_F1 = "t_div_F1";
	public static String p_cr_F1 = "p_cr_F1";
	

	//group4
	public static String f_r2p_F1 = "f_r2p_F1";
	public static String f_r2r_F1 = "f_r2r_F1";
	public static String vel_rate_top_F1="vel_rate_top_F1";
	public static String vel_rate_bottom_F1="vel_rate_bottom_F1";
	public static String wr_trot_F1="wr_trot_F1";
	public static String wr_brot_F1="wr_brot_F1";
	public static String bur_trot_F1="bur_trot_F1";
	public static String bur_brot_F1="bur_brot_F1";
	
	//group Roll Material Parameter
	public static String YM_Roll_Constant_F1 ="YM_Roll_Constant_F1";
	public static String PR_Roll_Constant_F1 = "PR_Roll_Constant_F1";
	
	//group5
	public static String YM_Constant_F1 = "YM_Constant_F1";
	public static String YM_Table_F1 = "YM_Table_F1";
	public static String YM_Value_F1 = "YM_Value_F1";
	public static String FS_Constant_F1 = "FS_Constant_F1";
	public static String FS_Table_F1 = "FS_Table_F1";
	public static String FS_Value_F1 = "FS_Value_F1";
	public static String YS_Value_F1 = "YS_Value_F1";
	public static String TS_Value_F1 = "TS_Value_F1";
	public static String E_Value_F1 = "E_Value_F1";
	
	public static String TEC_Constant_F1 = "TEC_Constant_F1";
	public static String TEC_Table_F1 = "TEC_Table_F1";
	public static String TEC_Value_F1 = "TEC_Value_F1";
	public static String PR_Constant_F1 = "PR_Constant_F1";
	public static String PR_Table_F1 = "PR_Table_F1";
	public static String PR_Value_F1 = "PR_Value_F1";
	public static String MD_Value_F1 = "MD_Value_F1";

	//group6
	public static String lcase_time_F1 = "lcase_time_F1";
	public static String lcase_inc_F1 = "lcase_inc_F1";
	public static String post_inc_F1 = "post_inc_F1";
	public static String lcase_dt_F1 = "lcase_dt_F1";
	public static String ltime_scale_F1 = "ltime_scale_F1";
	public static String ParallelDDM_F1 = "ParallelDDM_F1";
	public static String Domain_F1 = "Domain_F1";
	public static String ParallelMultiThread_F1 = "ParallelMultiThread_F1";
	public static String Thread_F1 = "Thread_F1";

	// group1 - F2
	public static String wr_len_F2 = "wr_len_F2";
	public static String wr_div_angle_F2 = "wr_div_angle_F2";
	public static String wr_chamferX_F2 = "wr_chamferX_F2";
	public static String wr_chamferY_F2 = "wr_chamferY_F2";
	public static String wr_round_F2 = "wr_round_F2";

	//group2
	public static String bur_len_F2 = "bur_len_F2";
	public static String bur_div_angle_F2 = "bur_div_angle_F2";
	public static String bur_chamferX_F2 = "bur_chamferX_F2";
	public static String bur_chamferY_F2 = "bur_chamferY_F2";

	//group3
	public static String p_in_F2 = "p_in_F2";
	public static String pl_m_F2 = "pl_m_F2";
	public static String t_div_F2 = "t_div_F2";
	public static String p_cr_F2 = "p_cr_F2";

	//group4
	public static String f_r2p_F2 = "f_r2p_F2";
	public static String f_r2r_F2 = "f_r2r_F2";
	public static String vel_rate_top_F2="vel_rate_top_F2";
	public static String vel_rate_bottom_F2="vel_rate_bottom_F2";
	public static String wr_trot_F2="wr_trot_F2";
	public static String wr_brot_F2="wr_brot_F2";
	public static String bur_trot_F2="bur_trot_F2";
	public static String bur_brot_F2="bur_brot_F2";
	
	//group Roll Material Parameter
	public static String YM_Roll_Constant_F2 ="YM_Roll_Constant_F2";
	public static String PR_Roll_Constant_F2 = "PR_Roll_Constant_F2";
	
	//group5
	public static String YM_Constant_F2 = "YM_Constant_F2";
	public static String YM_Table_F2 = "YM_Table_F2";
	public static String YM_Value_F2 = "YM_Value_F2";
	public static String FS_Constant_F2 = "FS_Constant_F2";
	public static String FS_Table_F2 = "FS_Table_F2";
	public static String FS_Value_F2 = "FS_Value_F2";
	public static String YS_Value_F2 = "YS_Value_F2";
	public static String TS_Value_F2 = "TS_Value_F2";
	public static String E_Value_F2 = "E_Value_F2";
	
	public static String TEC_Constant_F2 = "TEC_Constant_F2";
	public static String TEC_Table_F2 = "TEC_Table_F2";
	public static String TEC_Value_F2 = "TEC_Value_F2";
	public static String PR_Constant_F2 = "PR_Constant_F2";
	public static String PR_Table_F2 = "PR_Table_F2";
	public static String PR_Value_F2 = "PR_Value_F2";
	public static String MD_Value_F2 = "MD_Value_F2";

	//group6
	public static String lcase_time_F2 = "lcase_time_F2";
	public static String lcase_inc_F2 = "lcase_inc_F2";
	public static String post_inc_F2 = "post_inc_F2";
	public static String lcase_dt_F2 = "lcase_dt_F2";
	public static String ltime_scale_F2 = "ltime_scale_F2";
	public static String ParallelDDM_F2 = "ParallelDDM_F2";
	public static String Domain_F2 = "Domain_F2";
	public static String ParallelMultiThread_F2 = "ParallelMultiThread_F2";
	public static String Thread_F2 = "Thread_F2";

	
	//group1 F3
	public static String wr_len_F3 = "wr_len_F3";
	public static String wr_div_angle_F3 = "wr_div_angle_F3";
	public static String wr_chamferX_F3 = "wr_chamferX_F3";
	public static String wr_chamferY_F3 = "wr_chamferY_F3";
	public static String wr_round_F3 = "wr_round_F3";

	//group2
	public static String bur_len_F3 = "bur_len_F3";
	public static String bur_div_angle_F3 = "bur_div_angle_F3";
	public static String bur_chamferX_F3 = "bur_chamferX_F3";
	public static String bur_chamferY_F3 = "bur_chamferY_F3";

	//group3
	public static String p_in_F3 = "p_in_F3";
	public static String pl_m_F3 = "pl_m_F3";
	public static String t_div_F3 = "t_div_F3";
	public static String p_cr_F3 = "p_cr_F3";

	//group4
	public static String f_r2p_F3 = "f_r2p_F3";
	public static String f_r2r_F3 = "f_r2r_F3";
	public static String vel_rate_top_F3="vel_rate_top_F3";
	public static String vel_rate_bottom_F3="vel_rate_bottom_F3";
	public static String wr_trot_F3="wr_trot_F3";
	public static String wr_brot_F3="wr_brot_F3";
	public static String bur_trot_F3="bur_trot_F3";
	public static String bur_brot_F3="bur_brot_F3";
	
	//group Roll Material Parameter
	public static String YM_Roll_Constant_F3 ="YM_Roll_Constant_F3";
	public static String PR_Roll_Constant_F3 = "PR_Roll_Constant_F3";
	
	//group5
	public static String YM_Constant_F3 = "YM_Constant_F3";
	public static String YM_Table_F3 = "YM_Table_F3";
	public static String YM_Value_F3 = "YM_Value_F3";
	public static String FS_Constant_F3 = "FS_Constant_F3";
	public static String FS_Table_F3 = "FS_Table_F3";
	public static String FS_Value_F3 = "FS_Value_F3";
	public static String YS_Value_F3 = "YS_Value_F3";
	public static String TS_Value_F3 = "TS_Value_F3";
	public static String E_Value_F3 = "E_Value_F3";
	
	public static String TEC_Constant_F3 = "TEC_Constant_F3";
	public static String TEC_Table_F3 = "TEC_Table_F3";
	public static String TEC_Value_F3 = "TEC_Value_F3";
	public static String PR_Constant_F3 = "PR_Constant_F3";
	public static String PR_Table_F3 = "PR_Table_F3";
	public static String PR_Value_F3 = "PR_Value_F3";
	public static String MD_Value_F3 = "MD_Value_F3";

	//group6
	public static String lcase_time_F3 = "lcase_time_F3";
	public static String lcase_inc_F3 = "lcase_inc_F3";
	public static String post_inc_F3 = "post_inc_F3";
	public static String lcase_dt_F3 = "lcase_dt_F3";
	public static String ltime_scale_F3 = "ltime_scale_F3";
	public static String ParallelDDM_F3 = "ParallelDDM_F3";
	public static String Domain_F3 = "Domain_F3";
	public static String ParallelMultiThread_F3 = "ParallelMultiThread_F3";
	public static String Thread_F3 = "Thread_F3";

	//group1 -F4
	public static String wr_len_F4 = "wr_len_F4";
	public static String wr_div_angle_F4 = "wr_div_angle_F4";
	public static String wr_chamferX_F4 = "wr_chamferX_F4";
	public static String wr_chamferY_F4 = "wr_chamferY_F4";
	public static String wr_round_F4 = "wr_round_F4";
	
	//group2
	public static String bur_len_F4 = "bur_len_F4";
	public static String bur_div_angle_F4 = "bur_div_angle_F4";
	public static String bur_chamferX_F4 = "bur_chamferX_F4";
	public static String bur_chamferY_F4 = "bur_chamferY_F4";

	//group3
	public static String p_in_F4 = "p_in_F4";
	public static String pl_m_F4 = "pl_m_F4";
	public static String t_div_F4 = "t_div_F4";
	public static String p_cr_F4 = "p_cr_F4";

	//group4
	public static String f_r2p_F4 = "f_r2p_F4";
	public static String f_r2r_F4 = "f_r2r_F4";
	public static String vel_rate_top_F4="vel_rate_top_F4";
	public static String vel_rate_bottom_F4="vel_rate_bottom_F4";
	public static String wr_trot_F4="wr_trot_F4";
	public static String wr_brot_F4="wr_brot_F4";
	public static String bur_trot_F4="bur_trot_F4";
	public static String bur_brot_F4="bur_brot_F4";
	
	//group Roll Material Parameter
	public static String YM_Roll_Constant_F4 ="YM_Roll_Constant_F4";
	public static String PR_Roll_Constant_F4 = "PR_Roll_Constant_F4";

	//group5
	public static String YM_Constant_F4 = "YM_Constant_F4";
	public static String YM_Table_F4 = "YM_Table_F4";
	public static String YM_Value_F4 = "YM_Value_F4";
	public static String FS_Constant_F4 = "FS_Constant_F4";
	public static String FS_Table_F4 = "FS_Table_F4";
	public static String FS_Value_F4 = "FS_Value_F4";
	public static String YS_Value_F4 = "YS_Value_F4";
	public static String TS_Value_F4 = "TS_Value_F4";
	public static String E_Value_F4 = "E_Value_F4";
	
	public static String TEC_Constant_F4 = "TEC_Constant_F4";
	public static String TEC_Table_F4 = "TEC_Table_F4";
	public static String TEC_Value_F4 = "TEC_Value_F4";
	public static String PR_Constant_F4 = "PR_Constant_F4";
	public static String PR_Table_F4 = "PR_Table_F4";
	public static String PR_Value_F4 = "PR_Value_F4";
	public static String MD_Value_F4 = "MD_Value_F4";

	//group6
	public static String lcase_time_F4 = "lcase_time_F4";
	public static String lcase_inc_F4 = "lcase_inc_F4";
	public static String post_inc_F4 = "post_inc_F4";
	public static String lcase_dt_F4 = "lcase_dt_F4";
	public static String ltime_scale_F4 = "ltime_scale_F4";
	public static String ParallelDDM_F4 = "ParallelDDM_F4";
	public static String Domain_F4 = "Domain_F4";
	public static String ParallelMultiThread_F4 = "ParallelMultiThread_F4";
	public static String Thread_F4 = "Thread_F4";

	//group1 - F5
	public static String wr_len_F5 = "wr_len_F5";
	public static String wr_div_angle_F5 = "wr_div_angle_F5";
	public static String wr_chamferX_F5 = "wr_chamferX_F5";
	public static String wr_chamferY_F5 = "wr_chamferY_F5";
	public static String wr_round_F5 = "wr_round_F5";

	//group2
	public static String bur_len_F5 = "bur_len_F5";
	public static String bur_div_angle_F5 = "bur_div_angle_F5";
	public static String bur_chamferX_F5 = "bur_chamferX_F5";
	public static String bur_chamferY_F5 = "bur_chamferY_F5";

	//group3
	public static String p_in_F5 = "p_in_F5";
	public static String pl_m_F5 = "pl_m_F5";
	public static String t_div_F5 = "t_div_F5";
	public static String p_cr_F5 = "p_cr_F5";

	//group4
	public static String f_r2p_F5 = "f_r2p_F5";
	public static String f_r2r_F5 = "f_r2r_F5";
	public static String vel_rate_top_F5="vel_rate_top_F5";
	public static String vel_rate_bottom_F5="vel_rate_bottom_F5";
	public static String wr_trot_F5="wr_trot_F5";
	public static String wr_brot_F5="wr_brot_F5";
	public static String bur_trot_F5="bur_trot_F5";
	public static String bur_brot_F5="bur_brot_F5";
	
	//group Roll Material Parameter
	public static String YM_Roll_Constant_F5 ="YM_Roll_Constant_F5";
	public static String PR_Roll_Constant_F5 = "PR_Roll_Constant_F5";

	//group5
	public static String YM_Constant_F5 = "YM_Constant_F5";
	public static String YM_Table_F5 = "YM_Table_F5";
	public static String YM_Value_F5 = "YM_Value_F5";
	public static String FS_Constant_F5 = "FS_Constant_F5";
	public static String FS_Table_F5 = "FS_Table_F5";
	public static String FS_Value_F5 = "FS_Value_F5";
	public static String YS_Value_F5 = "YS_Value_F5";
	public static String TS_Value_F5 = "TS_Value_F5";
	public static String E_Value_F5 = "E_Value_F5";
	
	public static String TEC_Constant_F5 = "TEC_Constant_F5";
	public static String TEC_Table_F5 = "TEC_Table_F5";
	public static String TEC_Value_F5 = "TEC_Value_F5";
	public static String PR_Constant_F5 = "PR_Constant_F5";
	public static String PR_Table_F5 = "PR_Table_F5";
	public static String PR_Value_F5 = "PR_Value_F5";
	public static String MD_Value_F5 = "MD_Value_F5";

	//group6
	public static String lcase_time_F5 = "lcase_time_F5";
	public static String lcase_inc_F5 = "lcase_inc_F5";
	public static String post_inc_F5 = "post_inc_F5";
	public static String lcase_dt_F5 = "lcase_dt_F5";
	public static String ltime_scale_F5 = "ltime_scale_F5";	
	public static String ParallelDDM_F5 = "ParallelDDM_F5";
	public static String Domain_F5 = "Domain_F5";
	public static String ParallelMultiThread_F5 = "ParallelMultiThread_F5";
	public static String Thread_F5 = "Thread_F5";

	
	//group1 - F6
	public static String wr_len_F6 = "wr_len_F6";
	public static String wr_div_angle_F6 = "wr_div_angle_F6";
	public static String wr_chamferX_F6 = "wr_chamferX_F6";
	public static String wr_chamferY_F6 = "wr_chamferY_F6";
	public static String wr_round_F6 = "wr_round_F6";

	//group2
	public static String bur_len_F6 = "bur_len_F6";
	public static String bur_div_angle_F6 = "bur_div_angle_F6";
	public static String bur_chamferX_F6 = "bur_chamferX_F6";
	public static String bur_chamferY_F6 = "bur_chamferY_F6";

	//group3
	public static String p_in_F6 = "p_in_F6";
	public static String pl_m_F6 = "pl_m_F6";
	public static String t_div_F6 = "t_div_F6";
	public static String p_cr_F6 = "p_cr_F6";

	//group4
	public static String f_r2p_F6 = "f_r2p_F6";
	public static String f_r2r_F6 = "f_r2r_F6";
	public static String vel_rate_top_F6="vel_rate_top_F6";
	public static String vel_rate_bottom_F6="vel_rate_bottom_F6";
	public static String wr_trot_F6="wr_trot_F6";
	public static String wr_brot_F6="wr_brot_F6";
	public static String bur_trot_F6="bur_trot_F6";
	public static String bur_brot_F6="bur_brot_F6";
	
	//group Roll Material Parameter
	public static String YM_Roll_Constant_F6 ="YM_Roll_Constant_F6";
	public static String PR_Roll_Constant_F6 = "PR_Roll_Constant_F6";

	//group5
	public static String YM_Constant_F6 = "YM_Constant_F6";
	public static String YM_Table_F6 = "YM_Table_F6";
	public static String YM_Value_F6 = "YM_Value_F6";
	public static String FS_Constant_F6 = "FS_Constant_F6";
	public static String FS_Table_F6 = "FS_Table_F6";
	public static String FS_Value_F6 = "FS_Value_F6";
	public static String YS_Value_F6 = "YS_Value_F6";
	public static String TS_Value_F6 = "TS_Value_F6";
	public static String E_Value_F6 = "E_Value_F6";
	
	public static String TEC_Constant_F6 = "TEC_Constant_F6";
	public static String TEC_Table_F6 = "TEC_Table_F6";
	public static String TEC_Value_F6 = "TEC_Value_F6";
	public static String PR_Constant_F6 = "PR_Constant_F6";
	public static String PR_Table_F6 = "PR_Table_F6";
	public static String PR_Value_F6 = "PR_Value_F6";
	public static String MD_Value_F6 = "MD_Value_F6";

	//group6
	public static String lcase_time_F6 = "lcase_time_F6";
	public static String lcase_inc_F6 = "lcase_inc_F6";
	public static String post_inc_F6 = "post_inc_F6";
	public static String lcase_dt_F6 = "lcase_dt_F6";
	public static String ltime_scale_F6 = "ltime_scale_F6";
	public static String ParallelDDM_F6 = "ParallelDDM_F6";
	public static String Domain_F6 = "Domain_F6";
	public static String ParallelMultiThread_F6 = "ParallelMultiThread_F6";
	public static String Thread_F6 = "Thread_F6";
	
	//group1 - F7
	public static String wr_len_F7 = "wr_len_F7";
	public static String wr_div_angle_F7 = "wr_div_angle_F7";
	public static String wr_chamferX_F7 = "wr_chamferX_F7";
	public static String wr_chamferY_F7 = "wr_chamferY_F7";
	public static String wr_round_F7 = "wr_round_F7";

	//group2
	public static String bur_len_F7 = "bur_len_F7";
	public static String bur_div_angle_F7 = "bur_div_angle_F7";
	public static String bur_chamferX_F7 = "bur_chamferX_F7";
	public static String bur_chamferY_F7 = "bur_chamferY_F7";

	//group3
	public static String p_in_F7 = "p_in_F7";
	public static String pl_m_F7 = "pl_m_F7";
	public static String t_div_F7 = "t_div_F7";
	public static String p_cr_F7 = "p_cr_F7";

	//group4
	public static String f_r2p_F7 = "f_r2p_F7";
	public static String f_r2r_F7 = "f_r2r_F7";
	public static String vel_rate_top_F7="vel_rate_top_F7";
	public static String vel_rate_bottom_F7="vel_rate_bottom_F7";
	public static String wr_trot_F7="wr_trot_F7";
	public static String wr_brot_F7="wr_brot_F7";
	public static String bur_trot_F7="bur_trot_F7";
	public static String bur_brot_F7="bur_brot_F7";

	//group Roll Material Parameter
	public static String YM_Roll_Constant_F7 ="YM_Roll_Constant_F7";
	public static String PR_Roll_Constant_F7 = "PR_Roll_Constant_F7";
	
	//group5
	public static String YM_Constant_F7 = "YM_Constant_F7";
	public static String YM_Table_F7 = "YM_Table_F7";
	public static String YM_Value_F7 = "YM_Value_F7";
	public static String FS_Constant_F7 = "FS_Constant_F7";
	public static String FS_Table_F7 = "FS_Table_F7";
	public static String FS_Value_F7 = "FS_Value_F7";
	public static String YS_Value_F7 = "YS_Value_F7";
	public static String TS_Value_F7 = "TS_Value_F7";
	public static String E_Value_F7 = "E_Value_F7";
	
	public static String TEC_Constant_F7 = "TEC_Constant_F7";
	public static String TEC_Table_F7 = "TEC_Table_F7";
	public static String TEC_Value_F7 = "TEC_Value_F7";
	public static String PR_Constant_F7 = "PR_Constant_F7";
	public static String PR_Table_F7 = "PR_Table_F7";
	public static String PR_Value_F7 = "PR_Value_F7";
	public static String MD_Value_F7 = "MD_Value_F7";

	//group6
	public static String lcase_time_F7 = "lcase_time_F7";
	public static String lcase_inc_F7 = "lcase_inc_F7";
	public static String post_inc_F7 = "post_inc_F7";
	public static String lcase_dt_F7 = "lcase_dt_F7";
	public static String ltime_scale_F7 = "ltime_scale_F7";
	public static String ParallelDDM_F7 = "ParallelDDM_F7";
	public static String Domain_F7 = "Domain_F7";
	public static String ParallelMultiThread_F7 = "ParallelMultiThread_F7";
	public static String Thread_F7 = "Thread_F7";

	
	//
	//F1
	//
	//STAND_F1
	public static String BUR_TDIA_F1 = "BUR TDIA_F1";
	public static String BUR_BDIA_F1 = "BUR BDIA_F1";
	public static String WR_TDIA_F1 = "WR TDIA_F1";
	public static String WR_BDIA_F1 = "WR BDIA_F1";
	public static String WR_ICRN_F1 = "WR ICRN_F1";
	public static String ENTRY_THK_F1 = "ENTRY THK_F1";
	public static String EXIT_THK_F1 = "EXIT THK_F1";
	public static String PAS_LINE_F1 = "PAS LINE_F1";
	public static String ROL_GAP_F1 = "ROL GAP_F1";
	public static String STP_WID_F1 = "STP WID_F1";
	public static String STP_LEN_F1 = "STP LEN_F1";
	public static String ENTRY_TEMP_F1 = "ENTRY TEMP_F1";
	public static String EXIT_TEMP_F1 = "EXIT TEMP_F1";
	public static String FRCE_F1 = "FRCE_F1";
	public static String TORQ_F1 = "TORQ_F1";
	public static String SPEED_mpm_F1 = "SPEED(mpm)_F1";
	public static String BEND_F1 = "BEND_F1";
	public static String P_CROSS_F1 =  "P-CROSS_F1";
	public static String TENS_F1 = "TENS_F1";
	public static String ROL_TIM_F1 = "ROL TIM_F1";
	public static String IDL_TIM_F1 = "IDL TIM_F1";
	public static String BUR_WEAR_F1 = "BUR WEAR_F1";
	public static String WR_WEAR_F1 = "WR WEAR_F1";
	public static String WR_THRM_F1 = "WR THRM_F1";
	//
	//F2
	//
	//STAND_F2
	public static String BUR_TDIA_F2 = "BUR TDIA_F2";
	public static String BUR_BDIA_F2 = "BUR BDIA_F2";
	public static String WR_TDIA_F2 = "WR TDIA_F2";
	public static String WR_BDIA_F2 = "WR BDIA_F2";
	public static String WR_ICRN_F2 = "WR ICRN_F2";
	public static String ENTRY_THK_F2 = "ENTRY THK_F2";
	public static String EXIT_THK_F2 = "EXIT THK_F2";
	public static String PAS_LINE_F2 = "PAS LINE_F2";
	public static String ROL_GAP_F2 = "ROL GAP_F2";
	public static String STP_WID_F2 = "STP WID_F2";
	public static String STP_LEN_F2 = "STP LEN_F2";
	public static String ENTRY_TEMP_F2 = "ENTRY TEMP_F2";
	public static String EXIT_TEMP_F2 = "EXIT TEMP_F2";
	public static String FRCE_F2 = "FRCE_F2";
	public static String TORQ_F2 = "TORQ_F2";
	public static String SPEED_mpm_F2 = "SPEED(mpm)_F2";
	public static String BEND_F2 = "BEND_F2";
	public static String P_CROSS_F2 =  "P-CROSS_F2";
	public static String TENS_F2 = "TENS_F2";
	public static String ROL_TIM_F2 = "ROL TIM_F2";
	public static String IDL_TIM_F2 = "IDL TIM_F2";
	public static String BUR_WEAR_F2 = "BUR WEAR_F2";
	public static String WR_WEAR_F2 = "WR WEAR_F2";
	public static String WR_THRM_F2 = "WR THRM_F2";
	//
	//F3
	//
	//STAND_F3
	public static String BUR_TDIA_F3 = "BUR TDIA_F3";
	public static String BUR_BDIA_F3 = "BUR BDIA_F3";
	public static String WR_TDIA_F3 = "WR TDIA_F3";
	public static String WR_BDIA_F3 = "WR BDIA_F3";
	public static String WR_ICRN_F3 = "WR ICRN_F3";
	public static String ENTRY_THK_F3 = "ENTRY THK_F3";
	public static String EXIT_THK_F3 = "EXIT THK_F3";
	public static String PAS_LINE_F3 = "PAS LINE_F3";
	public static String ROL_GAP_F3 = "ROL GAP_F3";
	public static String STP_WID_F3 = "STP WID_F3";
	public static String STP_LEN_F3 = "STP LEN_F3";
	public static String ENTRY_TEMP_F3 = "ENTRY TEMP_F3";
	public static String EXIT_TEMP_F3 = "EXIT TEMP_F3";
	public static String FRCE_F3 = "FRCE_F3";
	public static String TORQ_F3 = "TORQ_F3";
	public static String SPEED_mpm_F3 = "SPEED(mpm)_F3";
	public static String BEND_F3 = "BEND_F3";
	public static String P_CROSS_F3 =  "P-CROSS_F3";
	public static String TENS_F3 = "TENS_F3";
	public static String ROL_TIM_F3 = "ROL TIM_F3";
	public static String IDL_TIM_F3 = "IDL TIM_F3";
	public static String BUR_WEAR_F3 = "BUR WEAR_F3";
	public static String WR_WEAR_F3 = "WR WEAR_F3";
	public static String WR_THRM_F3 = "WR THRM_F3";
	//
	//F4
	//
	//STAND_F4
	public static String BUR_TDIA_F4 = "BUR TDIA_F4";
	public static String BUR_BDIA_F4 = "BUR BDIA_F4";
	public static String WR_TDIA_F4 = "WR TDIA_F4";
	public static String WR_BDIA_F4 = "WR BDIA_F4";
	public static String WR_ICRN_F4 = "WR ICRN_F4";
	public static String ENTRY_THK_F4 = "ENTRY THK_F4";
	public static String EXIT_THK_F4 = "EXIT THK_F4";
	public static String PAS_LINE_F4 = "PAS LINE_F4";
	public static String ROL_GAP_F4 = "ROL GAP_F4";
	public static String STP_WID_F4 = "STP WID_F4";
	public static String STP_LEN_F4 = "STP LEN_F4";
	public static String ENTRY_TEMP_F4 = "ENTRY TEMP_F4";
	public static String EXIT_TEMP_F4 = "EXIT TEMP_F4";
	public static String FRCE_F4 = "FRCE_F4";
	public static String TORQ_F4 = "TORQ_F4";
	public static String SPEED_mpm_F4 = "SPEED(mpm)_F4";
	public static String BEND_F4 = "BEND_F4";
	public static String P_CROSS_F4 =  "P-CROSS_F4";
	public static String TENS_F4 = "TENS_F4";
	public static String ROL_TIM_F4 = "ROL TIM_F4";
	public static String IDL_TIM_F4 = "IDL TIM_F4";
	public static String BUR_WEAR_F4 = "BUR WEAR_F4";
	public static String WR_WEAR_F4 = "WR WEAR_F4";
	public static String WR_THRM_F4 = "WR THRM_F4";
	//
	//F5
	//
	//STAND_F5
	public static String BUR_TDIA_F5 = "BUR TDIA_F5";
	public static String BUR_BDIA_F5 = "BUR BDIA_F5";
	public static String WR_TDIA_F5 = "WR TDIA_F5";
	public static String WR_BDIA_F5 = "WR BDIA_F5";
	public static String WR_ICRN_F5 = "WR ICRN_F5";
	public static String ENTRY_THK_F5 = "ENTRY THK_F5";
	public static String EXIT_THK_F5 = "EXIT THK_F5";
	public static String PAS_LINE_F5 = "PAS LINE_F5";
	public static String ROL_GAP_F5 = "ROL GAP_F5";
	public static String STP_WID_F5 = "STP WID_F5";
	public static String STP_LEN_F5 = "STP LEN_F5";
	public static String ENTRY_TEMP_F5 = "ENTRY TEMP_F5";
	public static String EXIT_TEMP_F5 = "EXIT TEMP_F5";
	public static String FRCE_F5 = "FRCE_F5";
	public static String TORQ_F5 = "TORQ_F5";
	public static String SPEED_mpm_F5 = "SPEED(mpm)_F5";
	public static String BEND_F5 = "BEND_F5";
	public static String P_CROSS_F5 =  "P-CROSS_F5";
	public static String TENS_F5 = "TENS_F5";
	public static String ROL_TIM_F5 = "ROL TIM_F5";
	public static String IDL_TIM_F5 = "IDL TIM_F5";
	public static String BUR_WEAR_F5 = "BUR WEAR_F5";
	public static String WR_WEAR_F5 = "WR WEAR_F5";
	public static String WR_THRM_F5 = "WR THRM_F5";
	//
	//F6
	//
	//STAND_F6
	public static String BUR_TDIA_F6 = "BUR TDIA_F6";
	public static String BUR_BDIA_F6 = "BUR BDIA_F6";
	public static String WR_TDIA_F6 = "WR TDIA_F6";
	public static String WR_BDIA_F6 = "WR BDIA_F6";
	public static String WR_ICRN_F6 = "WR ICRN_F6";
	public static String ENTRY_THK_F6 = "ENTRY THK_F6";
	public static String EXIT_THK_F6 = "EXIT THK_F6";
	public static String PAS_LINE_F6 = "PAS LINE_F6";
	public static String ROL_GAP_F6 = "ROL GAP_F6";
	public static String STP_WID_F6 = "STP WID_F6";
	public static String STP_LEN_F6 = "STP LEN_F6";
	public static String ENTRY_TEMP_F6 = "ENTRY TEMP_F6";
	public static String EXIT_TEMP_F6 = "EXIT TEMP_F6";
	public static String FRCE_F6 = "FRCE_F6";
	public static String TORQ_F6 = "TORQ_F6";
	public static String SPEED_mpm_F6 = "SPEED(mpm)_F6";
	public static String BEND_F6 = "BEND_F6";
	public static String P_CROSS_F6 =  "P-CROSS_F6";
	public static String TENS_F6 = "TENS_F6";
	public static String ROL_TIM_F6 = "ROL TIM_F6";
	public static String IDL_TIM_F6 = "IDL TIM_F6";
	public static String BUR_WEAR_F6 = "BUR WEAR_F6";
	public static String WR_WEAR_F6 = "WR WEAR_F6";
	public static String WR_THRM_F6 = "WR THRM_F6";
	//
	//F7
	//
	//STAND_F7
	public static String BUR_TDIA_F7 = "BUR TDIA_F7";
	public static String BUR_BDIA_F7 = "BUR BDIA_F7";
	public static String WR_TDIA_F7 = "WR TDIA_F7";
	public static String WR_BDIA_F7 = "WR BDIA_F7";
	public static String WR_ICRN_F7 = "WR ICRN_F7";
	public static String ENTRY_THK_F7 = "ENTRY THK_F7";
	public static String EXIT_THK_F7 = "EXIT THK_F7";
	public static String PAS_LINE_F7 = "PAS LINE_F7";
	public static String ROL_GAP_F7 = "ROL GAP_F7";
	public static String STP_WID_F7 = "STP WID_F7";
	public static String STP_LEN_F7 = "STP LEN_F7";
	public static String ENTRY_TEMP_F7 = "ENTRY TEMP_F7";
	public static String EXIT_TEMP_F7 = "EXIT TEMP_F7";
	public static String FRCE_F7 = "FRCE_F7";
	public static String TORQ_F7 = "TORQ_F7";
	public static String SPEED_mpm_F7 = "SPEED(mpm)_F7";
	public static String BEND_F7 = "BEND_F7";
	public static String P_CROSS_F7 =  "P-CROSS_F7";
	public static String TENS_F7 = "TENS_F7";
	public static String ROL_TIM_F7 = "ROL TIM_F7";
	public static String IDL_TIM_F7 = "IDL TIM_F7";
	public static String BUR_WEAR_F7 = "BUR WEAR_F7";
	public static String WR_WEAR_F7 = "WR WEAR_F7";
	public static String WR_THRM_F7 = "WR THRM_F7";
	
	public static String f1f4_wr_min_r = "f1f4_wr_min_r";
	public static String f5f7_wr_min_r = "f5f7_wr_min_r";
	
	public static String f1f4_wr_round_min_r = "f1f4_wr_round_min_r";
	public static String f5f7_wr_round_min_r = "f5f7_wr_round_min_r";
	public static String f1f7_bur_round_min_r = "f1f7_bur_round_min_r";

	public static String f1f4_wr_max_round = "f1f4_wr_max_round";
	public static String f5f7_wr_max_round = "f5f7_wr_max_round";

	public static String f1f7_bur_min_r = "f1f7_bur_min_r";
	public static String f1f4_wr_max_cx = "f1f4_wr_max_cx";
	public static String f5f7_wr_max_cx = "f5f7_wr_max_cx";
	public static String f1f7_bur_max_cx = "f1f7_bur_max_cx";
	

	public String getYM_tableValuePath() {
		return YM_tableValuePath;
	}

	public void setYM_tableValuePath(String yM_tableValuePath) {
		YM_tableValuePath = yM_tableValuePath;
	}

	public String getFS_tableValuePath() {
		return FS_tableValuePath;
	}

	public void setFS_tableValuePath(String fS_tableValuePath) {
		FS_tableValuePath = fS_tableValuePath;
	}

	public String getTEC_tableValuePath() {
		return TEC_tableValuePath;
	}

	public void setTEC_tableValuePath(String tEC_tableValuePath) {
		TEC_tableValuePath = tEC_tableValuePath;
	}

	public String getPR_tableValuePath() {
		return PR_tableValuePath;
	}

	public void setPR_tableValuePath(String pR_tableValuePath) {
		PR_tableValuePath = pR_tableValuePath;
	}

	public static String getF1f4_wr_min_r() {
		return f1f4_wr_min_r;
	}

	public static void setF1f4_wr_min_r(String f1f4_wr_min_r) {
		InitValue.f1f4_wr_min_r = f1f4_wr_min_r;
	}

	public static String getF5f7_wr_min_r() {
		return f5f7_wr_min_r;
	}

	public static void setF5f7_wr_min_r(String f5f7_wr_min_r) {
		InitValue.f5f7_wr_min_r = f5f7_wr_min_r;
	}

	public static String getF1f4_wr_round_min_r() {
		return f1f4_wr_round_min_r;
	}

	public static void setF1f4_wr_round_min_r(String f1f4_wr_round_min_r) {
		InitValue.f1f4_wr_round_min_r = f1f4_wr_round_min_r;
	}

	public static String getF5f7_wr_round_min_r() {
		return f5f7_wr_round_min_r;
	}

	public static void setF5f7_wr_round_min_r(String f5f7_wr_round_min_r) {
		InitValue.f5f7_wr_round_min_r = f5f7_wr_round_min_r;
	}

	public static String getF1f7_bur_round_min_r() {
		return f1f7_bur_round_min_r;
	}

	public static void setF1f7_bur_round_min_r(String f1f7_bur_round_min_r) {
		InitValue.f1f7_bur_round_min_r = f1f7_bur_round_min_r;
	}

	public static String getF1f4_wr_max_round() {
		return f1f4_wr_max_round;
	}

	public static void setF1f4_wr_max_round(String f1f4_wr_max_round) {
		InitValue.f1f4_wr_max_round = f1f4_wr_max_round;
	}

	public static String getF5f7_wr_max_round() {
		return f5f7_wr_max_round;
	}

	public static void setF5f7_wr_max_round(String f5f7_wr_max_round) {
		InitValue.f5f7_wr_max_round = f5f7_wr_max_round;
	}

	public static String getF1f7_bur_min_r() {
		return f1f7_bur_min_r;
	}

	public static void setF1f7_bur_min_r(String f1f7_bur_min_r) {
		InitValue.f1f7_bur_min_r = f1f7_bur_min_r;
	}

	public static String getF1f4_wr_max_cx() {
		return f1f4_wr_max_cx;
	}

	public static void setF1f4_wr_max_cx(String f1f4_wr_max_cx) {
		InitValue.f1f4_wr_max_cx = f1f4_wr_max_cx;
	}

	public static String getF5f7_wr_max_cx() {
		return f5f7_wr_max_cx;
	}

	public static void setF5f7_wr_max_cx(String f5f7_wr_max_cx) {
		InitValue.f5f7_wr_max_cx = f5f7_wr_max_cx;
	}

	public static String getF1f7_bur_max_cx() {
		return f1f7_bur_max_cx;
	}

	public static void setF1f7_bur_max_cx(String f1f7_bur_max_cx) {
		InitValue.f1f7_bur_max_cx = f1f7_bur_max_cx;
	}
	
}
