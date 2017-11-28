package com.js.ens.hrolling3d_ver3.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class Mapping {
	private Map<String,ArrayList<String>> MappingMap;
	private Map<String,String> dataSet;
	//private ArrayList<ArrayList<String>> valueList;
	//private ArrayList<String> values;
	
	

	private String MappingPath;
	public Mapping() {
		// TODO Auto-generated constructor stub
		MappingMap = new HashMap<String,ArrayList<String>>();
		dataSet = new HashMap<String,String>();
		//valueList = new ArrayList<ArrayList<String>>();
		//values = new ArrayList<String>();
		//MappingPath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "userConfig"),"mappingTable.csv");
		MappingPath = FolderTree.filePath_MappingTable;
	}

	public void readMappingTableFile(){
		Reader obj = new Reader(MappingPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains(",")){
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,",");
				String key = splitDataTokens.get(0).trim();
				
				ArrayList<String> valueList = new ArrayList<String>();
				valueList.add(splitDataTokens.get(1));
				valueList.add(splitDataTokens.get(2));
				valueList.add(splitDataTokens.get(3));
				valueList.add(splitDataTokens.get(4));
				
				MappingMap.put(key,valueList);
				dataSet.put("#"+key+"#", splitDataTokens.get(2));
				
			}
		}
	}
	
	public String getVar(String key){
		if(MappingMap.containsKey(key)){
			return MappingMap.get(key).get(0);
		}else{
			return "no-MappingValue";
		}
	}
	
	public String getEQVar(String key){
		if(MappingMap.containsKey(key)){
			return MappingMap.get(key).get(1);
		}else{
			return "no-MappingValue";
		}
	}
	
	public String getProgVar(String key){
		if(MappingMap.containsKey(key)){
			return MappingMap.get(key).get(2);
		}else{
			return "no-MappingValue";
		}
	}
	
	
	
	public String getUILabel(String key){
		if(MappingMap.containsKey(key)){
			return MappingMap.get(key).get(3);
		}else{
			return "no-MappingValue";
		}
	}
	
	public String getDataSet(String key) {
		String reKey = "#"+key+"#";
		if(dataSet.containsKey(reKey)){
			return dataSet.get(reKey);
		}else{
			return "no-MappingValue";
		}
	}
	
	public static String wr_tdia = "#wr_tdia#";
	public static String wr_bdia = "#wr_bdia#";
	public static String bur_tdia = "#bur_tdia#";
	public static String bur_bdia = "#bur_bdia#";
	public static String wr_crown = "#wr_crown#";

	public static String roll_gap = "#roll_gap#";

	public static String wr_div_angle = "#wr_div_angle#";
	public static String bur_div_angle = "#bur_div_angle#";

	public static String wr_len = "#wr_len#";
	public static String bur_len = "#bur_len#";

	public static String p_thk = "#p_thk#";
	public static String p_wid = "#p_wid#";
	public static String p_len = "#p_len#";
	public static String p_ent_temp = "#p_ent_temp#";
	public static String p_exit_temp = "#p_exit_temp#";
	public static String p_in = "#p_in#";
	public static String pl_m = "#pl_m#";
	public static String t_div = "#t_div#";

	public static String pl_vel_mpm = "#pl_vel_mpm#";

	public static String pass_line = "#pass_line#";
	public static String p_cross_ang = "#p_cross_ang#";
	public static String roll_torque = "#roll_torque#";
	public static String bend_f = "#bend_f#";
	public static String tens_f = "#tens_f#";

	public static String f_r2p = "#f_r2p#";
	public static String f_r2r = "#f_r2r#";

	public static String lcase_inc = "#lcase_inc#";
	public static String post_inc = "#post_inc#";

	public static String frce = "#frce#";
	public static String exit_thk = "#exit_thk#";
	public static String rol_tim = "#rol_tim#";
	public static String idl_tim = "#idl_tim#";
	public static String bur_wear = "#bur_wear#";
	public static String wr_wear = "#wr_wear#";
	public static String wr_thrm = "#wr_thrm#";

	public static String ym_value = "#ym_value#";
	public static String tec_value = "#tec_value#";
	public static String pr_value = "#pr_value#";
	public static String md_value = "#md_value#";

	public static String lcase_time = "#lcase_time#";
	public static String lcase_dt = "#lcase_dt#";
	public static String ltime_scale = "#ltime_scale#";
	public static String domain = "#domain#";
	public static String thread = "#thread#";

	public static String vel_rate_top = "#vel_rate_top#";
	public static String vel_rate_bottom = "#vel_rate_bottom#";
	public static String wr_trot = "#wr_trot#";
	public static String wr_brot = "#wr_brot#";
	public static String bur_trot = "#bur_trot#";
	public static String bur_brot = "#bur_brot#";

	public static String var1 = "#var1#";
	public static String var2 = "#var2#";
	public static String var3 = "#var3#";
	public static String var4 = "#var4#";
	public static String var5 = "#var5#";
	public static String var6 = "#var6#";
	public static String var7 = "#var7#";
	public static String var8 = "#var8#";
	public static String var9 = "#var9#";
	public static String var10 = "#var10#";
	public static String var11 = "#var11#";
	public static String var12 = "#var12#";
	public static String var13 = "#var13#";
	public static String var14 = "#var14#";
	public static String var15 = "#var15#";
	public static String var16 = "#var16#";

	public static String sthk = "#sthk#";
	public static String swid = "#swid#";
	public static String slen = "#slen#";
	public static String swet = "#swet#";
	public static String pthk = "#pthk#";
	public static String pwid = "#pwid#";
	public static String plen = "#plen#";
	public static String pwet = "#pwet#";
	
	public static String wr_chamferX = "#wr_chamferX#";
	public static String wr_chamferY = "#wr_chamferY#";
	public static String wr_round = "#wr_round#";
	
	public static String bur_chamferX = "#bur_chamferX#";
	public static String bur_chamferY = "#bur_chamferY#";
	
	public static String p_cr = "#p_cr#";
	
	public static String ym_roll_value = "#YM_ROLL_VALUE#";
	public static String pr_roll_value = "#PR_ROLL_VALUE#";
	
	public static String f1f4_wr_min_r = "#f1f4_wr_min_r#";
	public static String f5f7_wr_min_r = "#f5f7_wr_min_r#";
	
	public static String f1f4_wr_round_min_r = "#f1f4_wr_round_min_r#";
	public static String f5f7_wr_round_min_r = "#f5f7_wr_round_min_r#";
	public static String f1f7_bur_round_min_r = "#f1f7_bur_round_min_r#";

	public static String f1f4_wr_max_round = "#f1f4_wr_max_round#";
	public static String f5f7_wr_max_round = "#f5f7_wr_max_round#";
	
	public static String f1f7_bur_min_r = "#f1f7_bur_min_r#";
	public static String f1f4_wr_max_cx = "#f1f4_wr_max_cx#";
	public static String f5f7_wr_max_cx = "#f5f7_wr_max_cx#";
	public static String f1f7_bur_max_cx = "#f1f7_bur_max_cx#";
}
