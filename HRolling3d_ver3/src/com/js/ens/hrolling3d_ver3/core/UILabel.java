package com.js.ens.hrolling3d_ver3.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.io.Reader;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class UILabel {
	private static UILabel instance = new UILabel();
	public static UILabel getInstance(){
		return instance;
	}
	private Map<String,String> UILabelMap;
	private String UILabelPath;
	
	public UILabel(){
		UILabelMap = new HashMap<String,String>();
		//UILabelPath = myUtil.setPath(myUtil.setPath(System.getProperty("user.dir"), "userConfig"),"UILabel.txt");
		UILabelPath = FolderTree.filePath_UILabel;
		this.readUILabelFile();
	}
	
	public void readUILabelFile(){
		Reader obj = new Reader(UILabelPath);
		obj.running();
		ArrayList<String> fileData = new ArrayList<String>();
		fileData = obj.getFileDataList();
		for(String line : fileData){
			String data = line.trim();
			if(data.contains("=")){
				//System.out.println(line);
				ArrayList<String> splitDataTokens = new ArrayList<String>();
				splitDataTokens = ParserDefault.splitLineData(data,"=");
				UILabelMap.put(splitDataTokens.get(0).trim(), splitDataTokens.get(1).trim()); 
			}
		}
	}
	
	public String getUILabelValue(String key){
		if(UILabelMap.containsKey(key)){
			return UILabelMap.get(key);
		}else{
			return "no-UILabel";
		}
	}
	
	
	// program variable = map key => value(in UILabel.txt)
	//
	//Common Label
	//
	public static String Model_Name = "Model Name";
	public static String Workspace = "Workspace";
	//
	// button label
	//
	public static String Import_P_Log = "Import P Log";
	public static String Export_P_Log = "Export P Log";
	public static String Apply = "Apply";
	//
	// Tab title
	//
	public static String Tab1Label="Tab1Label";
	public static String Tab2Label="Tab2Label";
	//
	// Table
	//
	public static String Table1Label="Table1Label";
	public static String Table2Label="Table2Label";
	public static String Table3Label="Table3Label";
	//
	// STAND
	//
	public static String STAND="STAND";
	public static String F1="F1";
	public static String F2="F2";
	public static String F3="F3";
	public static String F4="F4";
	public static String F5="F5";
	public static String F6="F6";
	public static String F7="F7";
		
	//
	// -> Work Roll(WR) parameter
	// Title 
	public static String Work_Roll_WR_Parameters="Work Roll(WR) Parameters";
	// 1) WR_TDIA
	public static String Top_WR_Diameter="Top WR Diameter(mm)";
	// 2) WR_BDIA
	public static String Bottom_WR_Diameter="Bottom WR Diameter(mm)";
	// 3) WR_ICRN
	public static String WR_Crown="WR Crown(mm)";
	// 4) Default-wr_len1-3
	public static String WR_Length="WR Length(mm)";
	public static String wr_len = "wr_len";
	// 5) wr_div_angle
	public static String WR_Mesh_Angle="WR Mesh Angle(deg.)";
	public static String wr_div_angle="wr_div_angle";
	// 6) wr_chamferX
	public static String WR_Chamfer_X="WR Chamfer-X(mm)";
	//WR Chamfer-X(mm)=WR Chamfer-X(mm)
	// 7) wr_chmferY
	public static String WR_Chamfer_Y="WR Chamfer-Y(mm)";
	//WR Chamfer-Y(mm)=WR Chamfer-Y(mm)
	// 8) wr_round
	public static String WR_Round = "WR Round(mm)";
	//WR Round(mm)=WR Round(mm)

	//
	// -> Backup Roll(BUR) Parameters
	// Title
	public static String Backup_Roll_BUR_Parameters="Backup Roll(BUR) Parameters";
	// 1) BUR_TDIA
	public static String Top_BUR_Diameter="Top BUR Diameter(mm)";
	// 2) BUR_BDIA
	public static String Bottom_BUR_Diameter="Bottom BUR Diameter(mm)";
	// 3) bur_len1-3
	public static String BUR_Length="BUR Length(mm)";
	public static String bur_len="bur_len";
	// 4) bur_div_angle
	public static String BUR_Mesh_Angle="BUR Mesh Angle(deg.)";
	public static String bur_div_angle="bur_div_angle";
	// 5) bur_chamferX
	public static String BUR_Chamfer_X = "BUR Chamfer-X(mm)";
	//BUR Chamfer-X(mm)=BUR Chamfer-X(mm)
	// 6) bur_chamferY
	public static String BUR_Chamfer_Y = "BUR Chamfer-Y(mm)";
	//BUR Chamfer-Y(mm)=BUR Chamfer-Y(mm)
	//
	//-> Plate Parameters
	// Title
	public static String Plate_Parameters="Plate Parameters";
	// 1) ENTRY_THK
	public static String Thickness="Thickness(mm)";
	// 2) STP_WID
	public static String Width="Width(mm)";			
	// 3) STP_LEN
	public static String Length="Length(mm)";
	// 4) ENTRTY_TEMP
	public static String Entry_Temperature="Entry Temperature(C)";
	// 5) EXIT_TEMP
	public static String Exit_Temperature="Exit Temperature(C)";
	// 6) p_in
	public static String Initial_Position="Initial Position(mm)";	
	public static String p_in="p_in";
	// 7) pl_m
	public static String Mesh_Length="Mesh Length(mm)";	
	public static String pl_m="pl_m";
	// 8) t_div
	public static String Thickness_Mesh_Divisions="Thickness Mesh Divisions";
	public static String t_div="t_div";
	// 9) p_cr
	public static String Plate_Crown="Plate Crown";
	
	//
	//-> Plate Parameters
	// Title 
	public static String Process_Information="Process Information";
	// 1) SPEED
	public static String Velocity="Velocity(mpm)";
	// 2) ROL_GAP
	public static String Roll_Gap="Roll Gap(mm)";
	// 3) Pass_LINE
	public static String Pass_Line="Pass Line(mm)";
	// 4) P-CROSS
	public static String Pair_Cross_Angle="Pair Cross Angle(Deg.)";
	// 5) BEND
	public static String Bender_Force="Bender Force(Tone)";
	// 6) TORQ
	public static String Roll_Torque="Roll Torque(Tone.m)";
	// 7) TENS
	public static String Tension_Stress="Tension Stress(Kgf/mm2)";			
	// 8) f_r2p
	public static String Roll_to_Plate_Frict_Coef="Roll to Plate Frict. Coef.";			
	public static String f_r2p="f_r2p";
	// 9) f_r2r
	public static String Roll_to_Roll_Fric_Coef="Roll to Roll Fric. Coef.";
	public static String f_r2r="f_r2r";
	
	// 10) vel_rate_top
	public static String Speed_Different_Ratio_top_roll="Speed Different Ratio-top roll(%)";
	public static String vel_rate_top = "vel_rate_top";
	// 11) vel_rate_bottom
	public static String Speed_Different_Ratio_bottom_roll="Speed Different Ratio-bottom roll(%)";
	public static String vel_rate_bottom="vel_rate_bottom";
	// 12) wr_trot
	public static String Top_WR_Rot_Vel_RPM="Top WR Rot. Vel.(RPM)";
	public static String wr_trot="wr_trot";
	// 13) wr_brot
	public static String Bottom_WR_Rot_Vel_RPM="Bottom WR Rot. Vel.(RPM)";
	public static String wr_brot="wr_brot";
	// 14) bur_trot
	public static String Top_BUR_Rot_Vel_RPM="Top BUR Rot. Vel.(RPM)";
	public static String bur_trot="bur_trot";
	// 15) bur_brot
	public static String Bottom_BUR_Rot_Vel_RPM="Bottom BUR Rot. Vel.(RPM)";
	public static String bur_brot="bur_brot";
	
	//
	// -> Roll Material Parameter
	// Title 
	public static String Roll_Material_Parameter="Roll Material parameter";
	// 1) 
	public static String Roll_Youngs_Modulus="Roll Young's Modulus(MPa)";
	// 2) 
	public static String Roll_Poissons_Ratio="Roll Poisson's Ratio";
	
	//
	//-> Plate Material parameter
	// Title
	public static String Plate_Material_parameter="Plate Material parameter";
	// 1) 
	public static String Youngs_Modulus="Young's Modulus(MPa)";
	// 2)
	public static String Flow_Stress="Flow Stress (MPa)";
	public static String Yield_Strength="Yield Strength (N/mm^2)";
	public static String Tensile_Strength="Tensile Strength (N/mm^2)";
	public static String Elongation="Elongation (mm/mm)";
	// 3)
	public static String Thermal_Expansion_Coefficient="Thermal Expansion Coefficient(mm/mm)";
	// 3)
	public static String Poissons_Ratio="Poisson's Ratio";
	// 4)
	public static String Mass_Density="Mass Density(Mg/mm^3)";
	// 5) etc
	public static String YM_Constant="YM_Constant";
	public static String YM_Table="YM_Table";
	public static String YM_Value="YM_Value";
	public static String FS_Constant="FS_Constant";
	public static String FS_Table="FS_Table";
	public static String FS_Value="FS_Value";
	public static String YS_Value="YS_Value";
	public static String TS_Value="TS_Value";
	public static String E_Value ="E_Value";
	public static String TEC_Constant="TEC_Constant";
	
	public static String TEC_Table="TEC_Table";
	public static String TEC_Value="TEC_Value";
	public static String PR_Constant="PR_Constant";
	public static String PR_Table="PR_Table";
	public static String PR_Value="PR_Value";
	public static String MD_Value="MD_Value";
	
	
	//
	//-> Analysis Information
	// Title
	public static String Analysis_Information="Analysis Information";
	// 1) lcase_time
	public static String Analysis_Time="Analysis Time(sec.)";
	public static String lcase_time="lcase_time"; 
	// 2) lcase_inc
	public static String No_of_Inc="No. of Inc";			
	public static String lcase_inc="lcase_inc";
	// 3) post_inc
	public static String Post_Writing_frequency="Post Writing frequency";
	public static String post_inc="post_inc";
	// 4) increment_time
	public static String Time_Increment="Time Increment(sec.)";
	public static String lcase_dt="lcase_dt";
	// 4-2) ltime_scale 
	public static String ltime_scale = "ltime_scale";
	// 5) parallel_DDM
	public static String Parallel_DDM="Parallel-DDM";
	public static String ParallelDDM="ParallelDDM";
	// 6) domain
	public static String Domain="Domain";
	//public static String Domain="Domain";
	// 7) parallel_Multi_Thread
	public static String Parallel_Multi_Thread="Parallel-Multi Thread";			
	public static String ParallelMultiThread="ParallelMultiThread";
	// 8) thread
	public static String Thread="Thread";
	//public static String Thread="Thread";
	
	//
	// ETC
	//
	public static String Constatnt="Constatnt";
	public static String Table="Table";
	public static String use="use";
	
	public static String BUR_TDIA = "BUR TDIA";
	public static String BUR_BDIA = "BUR BDIA";
	public static String WR_TDIA = "WR TDIA";
	public static String WR_BDIA = "WR BDIA";
	public static String WR_ICRN = "WR ICRN";
	public static String ENTRY_THK = "ENTRY THK";
	public static String EXIT_THK = "EXIT THK";
	public static String PAS_LINE = "PAS LINE";
	public static String ROL_GAP = "ROL GAP";
	public static String STP_WID = "STP WID";
	public static String STP_LEN = "STP LEN";
	public static String ENTRY_TEMP = "ENTRY TEMP";
	public static String EXIT_TEMP = "EXIT TEMP";
	public static String FRCE = "FRCE";
	public static String TORQ = "TORQ";
	public static String SPEED_mpm = "SPEED(mpm)";
	public static String BEND = "BEND";
	public static String P_CROSS =  "P-CROSS";
	public static String TENS = "TENS";
	public static String ROL_TIM = "ROL TIM";
	public static String IDL_TIM = "IDL TIM";
	public static String BUR_WEAR = "BUR WEAR";
	public static String WR_WEAR = "WR WEAR";
	public static String WR_THRM = "WR THRM";
	
}
