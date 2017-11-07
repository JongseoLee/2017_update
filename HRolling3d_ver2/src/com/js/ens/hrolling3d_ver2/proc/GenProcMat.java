package com.js.ens.hrolling3d_ver2.proc;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver2.FolderTree;
import com.js.ens.hrolling3d_ver2.core.MainController;
import com.js.ens.hrolling3d_ver2.core.UILabel;
import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_PLog;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class GenProcMat {
	private MainController MC = MainController.getInstance();
	//private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	/*
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String userModuleFolder_con = myUtil.setPath(userModuleFolder, "consquent");
	private String userModuleFolder_ind = myUtil.setPath(userModuleFolder, "individual");
	//*/
	
	//private String configFolder_con = FolderTree.folderPath_Config_fileList_con;
	//private String configFolder_ind = FolderTree.folderPath_Config_fileList_ind;
	private String userModuleFolder_con = FolderTree.folderPath_consquent;
	private String userModuleFolder_ind = FolderTree.folderPath_individual;
	private String destFolder;
	
	
	//private String destFolder; 
	//private String newFileName;
	private ArrayList<String> ori_fileDataList = new ArrayList<String>();
	private ArrayList<String> procDataList = new ArrayList<String>();
	private ArrayList<TableData_PLog> plogObj = null;
	
	
	public String FileName_matDef = "a3_material_define.proc";
	public String FileName_mat01Con = "mat01_elastic_modulus_const.proc";
	public String FileName_mat01Tbl = "mat01_elastic_modulus.proc";
	public String FileName_mat02Con = "mat02_flow_stress_const.proc";
	public String FileName_mat02Tbl = "mat02_flow_stress.proc";
	public String FileName_mat03Con = "mat03_thermal_expansion_const.proc";
	public String FileName_mat03Tbl = "mat03_thermal_expansion.proc";
	public String FileName_mat04Con = "mat04_poisson_const.proc";
	public String FileName_mat04Tbl = "mat04_poisson.proc";
	public String FileName_mat05 = "mat05_mass_density.proc";
	public String CMD = "*exec_procedure ";
	
	
	public String FilePath_mat01Con = "";
	public String FilePath_mat01Tbl = "";
	public String FilePath_mat02Con = "";
	public String FilePath_mat02Tbl = "";
	public String FilePath_mat03Con = "";
	public String FilePath_mat03Tbl = "";
	public String FilePath_mat04Con = "";
	public String FilePath_mat04Tbl = "";
	public String FilePath_mat05 = "";
	
	
	public static String YM_Type = "%ElasticModulusType%";
	public static String FS_Type = "%FlowStressType%";
	public static String TEC_Type = "%Thermal_expansionType%";
	public static String PR_Type = "%PoissonType%";
	// mat01
	// constant
	public static String YM_value = "%value_YoungsModulus%";
	// table
	public static String YM_tableName = "%tableName%";
	public static String YM_tableData = "%tableDataList%";
	// mat02
	// constant
	public static String YS_value = "%value_YieldStrength%";
	public static String TS_value = "%value_TensileStrength%";
	public static String E_value = "%value_Elongation%";
	// table
	public static String FS_tableName = "%tableName%";
	public static String FS_PlasticDataSetNum = "%PlasticDataSetNum%";
	public static String FS_TemperatureDataSetNum = "%TemperatureDataSetNum%";
	public static String FS_PlaticStrainDataSetNum = "%PlaticStrainDataSetNum%";
	public static String FS_plasstic_Strain = "%plasstic_Strain%";
	public static String FS_Temperature = "%Temperature%";
	public static String FS_Plastic_Strain_Rate = "%Plastic_Strain_Rate%";
	public static String FS_curveDataTable = "%curveDataTable%";
	// constant
	public static String TEC_value = "%value_expansion%";
	// table
	public static String TEC_tableName = "%tableName%";
	public static String TEC_tableData = "%tableDataList%";
	// constant
	public static String PR_value = "%value_poisson%";
	// table
	public static String PR_tableName = "%tableName%";
	public static String PR_tableData = "%tableDataList%";
	// Mass Density 
	public static String MD_value = "%value_MassDensity%";
	public GenProcMat() {
		// TODO Auto-generated constructor stub
		plogObj = MC.getTableDataPLogList();
	}
	
	public void a3materialDefine(String Standtype){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, Standtype),this.FileName_matDef);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),Standtype),this.FileName_matDef);
		readSourceData(ori_filePath);
		if(Standtype.equals(UILabel.F1)){
			swapValue(0);
		}else if(Standtype.equals(UILabel.F2)){
			swapValue(1);
		}else if(Standtype.equals(UILabel.F3)){
			swapValue(2);
		}else if(Standtype.equals(UILabel.F4)){
			swapValue(3);
		}else if(Standtype.equals(UILabel.F5)){
			swapValue(4);
		}else if(Standtype.equals(UILabel.F6)){
			swapValue(5);
		}else if(Standtype.equals(UILabel.F7)){
			swapValue(6);
		}
		
		writeProc(new_filePath);
		
	}
	
	private void readSourceData(String ori_filePath){
		Reader reader = new Reader(ori_filePath);
		reader.running();
		for(String line : reader.getFileDataList()){
			this.ori_fileDataList.add(line.replace("\t", "  "));
		}
	}
	
	private void swapValue(int StandType){
		TableData_PLog obj = plogObj.get(StandType);
		for(String line : this.ori_fileDataList){
			if(line.contains(this.YM_Type)){
				String newLine = "";
				if(obj.getYM_Constant().equals("true")){
					newLine = line.replace(this.YM_Type, this.CMD+this.FileName_mat01Con);
				}else{
					newLine = line.replace(this.YM_Type, this.CMD+this.FileName_mat01Tbl);
				}
				this.procDataList.add(newLine);
			}else if(line.contains(this.FS_Type)){
				String newLine = "";
				if(obj.getFS_Constant().equals("true")){
					newLine = line.replace(this.FS_Type, this.CMD+this.FileName_mat02Con);
				}else{
					newLine = line.replace(this.FS_Type, this.CMD+this.FileName_mat02Tbl);
				}				
				this.procDataList.add(newLine);
			}else if(line.contains(this.TEC_Type)){
				String newLine = "";
				if(obj.getTEC_Constant().equals("true")){
					newLine = line.replace(this.TEC_Type, this.CMD+this.FileName_mat03Con);
				}else{
					newLine = line.replace(this.TEC_Type, this.CMD+this.FileName_mat03Tbl);
				}				
				this.procDataList.add(newLine);
			}else if(line.contains(this.PR_Type)){
				String newLine = "";
				if(obj.getPR_Constant().equals("true")){
					newLine = line.replace(this.PR_Type, this.CMD+this.FileName_mat04Con);
				}else{
					newLine = line.replace(this.PR_Type, this.CMD+this.FileName_mat04Tbl);
				}				
				this.procDataList.add(newLine);
			}else{
				this.procDataList.add(line);		
			}
		}
	}
	
	public void genProcMatF1(String destFolder){
		this.a3materialDefine(UILabel.F1);
		TableData_PLog obj = plogObj.get(0); 
		
		mat01_elasticModulus obj01 = new mat01_elasticModulus();
		if(obj.getYM_Constant().equals("true")){
			// Constant
			obj01.running("constant", UILabel.F1);
		}else{
			// Table
			obj01.running("table", UILabel.F1);
		}
		
		mat02_flowStress obj02 = new mat02_flowStress();
		if(obj.getFS_Constant().equals("true")){
			// Constant
			obj02.running("constant", UILabel.F1);
		}else{
			// Table
			obj02.running("table", UILabel.F1);
		}
		
		mat03_thermalExpansion obj03 = new mat03_thermalExpansion();
		if(obj.getTEC_Constant().equals("true")){
			// Constant
			obj03.running("constant", UILabel.F1);
		}else{
			// Table
			obj03.running("table", UILabel.F1);
		}
		
		mat04_poisson obj04 = new mat04_poisson();
		if(obj.getPR_Constant().equals("true")){
			// Constant
			obj04.running("constant", UILabel.F1);
		}else{
			// Table
			obj04.running("table", UILabel.F1);
		}
		
		mat05_massDensity obj05 = new mat05_massDensity();
		obj05.running(UILabel.F1);
	}
	
	public void genProcMatF2(String destFolder){
		this.a3materialDefine(UILabel.F2);
		TableData_PLog obj = plogObj.get(1); 
		
		mat01_elasticModulus obj01 = new mat01_elasticModulus();
		if(obj.getYM_Constant().equals("true")){
			// Constant
			obj01.running("constant", UILabel.F2);
		}else{
			// Table
			obj01.running("table", UILabel.F2);
		}
		
		mat02_flowStress obj02 = new mat02_flowStress();
		if(obj.getFS_Constant().equals("true")){
			// Constant
			obj02.running("constant", UILabel.F2);
		}else{
			// Table
			obj02.running("table", UILabel.F2);
		}
		
		mat03_thermalExpansion obj03 = new mat03_thermalExpansion();
		if(obj.getTEC_Constant().equals("true")){
			// Constant
			obj03.running("constant", UILabel.F2);
		}else{
			// Table
			obj03.running("table", UILabel.F2);
		}
		
		mat04_poisson obj04 = new mat04_poisson();
		if(obj.getPR_Constant().equals("true")){
			// Constant
			obj04.running("constant", UILabel.F2);
		}else{
			// Table
			obj04.running("table", UILabel.F2);
		}
		
		mat05_massDensity obj05 = new mat05_massDensity();
		obj05.running(UILabel.F2);
	}

	public void genProcMatF3(String destFolder){
		this.a3materialDefine(UILabel.F3);
		TableData_PLog obj = plogObj.get(2); 
		
		mat01_elasticModulus obj01 = new mat01_elasticModulus();
		if(obj.getYM_Constant().equals("true")){
			// Constant
			obj01.running("constant", UILabel.F3);
		}else{
			// Table
			obj01.running("table", UILabel.F3);
		}
		
		mat02_flowStress obj02 = new mat02_flowStress();
		if(obj.getFS_Constant().equals("true")){
			// Constant
			obj02.running("constant", UILabel.F3);
		}else{
			// Table
			obj02.running("table", UILabel.F3);
		}
		
		mat03_thermalExpansion obj03 = new mat03_thermalExpansion();
		if(obj.getTEC_Constant().equals("true")){
			// Constant
			obj03.running("constant", UILabel.F3);
		}else{
			// Table
			obj03.running("table", UILabel.F3);
		}
		
		mat04_poisson obj04 = new mat04_poisson();
		if(obj.getPR_Constant().equals("true")){
			// Constant
			obj04.running("constant", UILabel.F3);
		}else{
			// Table
			obj04.running("table", UILabel.F3);
		}
		
		mat05_massDensity obj05 = new mat05_massDensity();
		obj05.running(UILabel.F3);
	}

	public void genProcMatF4(String destFolder){
		this.a3materialDefine(UILabel.F4);
		TableData_PLog obj = plogObj.get(3); 
		
		mat01_elasticModulus obj01 = new mat01_elasticModulus();
		if(obj.getYM_Constant().equals("true")){
			// Constant
			obj01.running("constant", UILabel.F4);
		}else{
			// Table
			obj01.running("table", UILabel.F4);
		}
		
		mat02_flowStress obj02 = new mat02_flowStress();
		if(obj.getFS_Constant().equals("true")){
			// Constant
			obj02.running("constant", UILabel.F4);
		}else{
			// Table
			obj02.running("table", UILabel.F4);
		}
		
		mat03_thermalExpansion obj03 = new mat03_thermalExpansion();
		if(obj.getTEC_Constant().equals("true")){
			// Constant
			obj03.running("constant", UILabel.F4);
		}else{
			// Table
			obj03.running("table", UILabel.F4);
		}
		
		mat04_poisson obj04 = new mat04_poisson();
		if(obj.getPR_Constant().equals("true")){
			// Constant
			obj04.running("constant", UILabel.F4);
		}else{
			// Table
			obj04.running("table", UILabel.F4);
		}
		
		mat05_massDensity obj05 = new mat05_massDensity();
		obj05.running(UILabel.F4);
	}

	public void genProcMatF5(String destFolder){
		this.a3materialDefine(UILabel.F5);
		TableData_PLog obj = plogObj.get(4); 
		
		mat01_elasticModulus obj01 = new mat01_elasticModulus();
		if(obj.getYM_Constant().equals("true")){
			// Constant
			obj01.running("constant", UILabel.F5);
		}else{
			// Table
			obj01.running("table", UILabel.F5);
		}
		
		mat02_flowStress obj02 = new mat02_flowStress();
		if(obj.getFS_Constant().equals("true")){
			// Constant
			obj02.running("constant", UILabel.F5);
		}else{
			// Table
			obj02.running("table", UILabel.F5);
		}
		
		mat03_thermalExpansion obj03 = new mat03_thermalExpansion();
		if(obj.getTEC_Constant().equals("true")){
			// Constant
			obj03.running("constant", UILabel.F5);
		}else{
			// Table
			obj03.running("table", UILabel.F5);
		}
		
		mat04_poisson obj04 = new mat04_poisson();
		if(obj.getPR_Constant().equals("true")){
			// Constant
			obj04.running("constant", UILabel.F5);
		}else{
			// Table
			obj04.running("table", UILabel.F5);
		}
		
		mat05_massDensity obj05 = new mat05_massDensity();
		obj05.running(UILabel.F5);
	}

	public void genProcMatF6(String destFolder){
		this.a3materialDefine(UILabel.F6);
		TableData_PLog obj = plogObj.get(5); 
		
		mat01_elasticModulus obj01 = new mat01_elasticModulus();
		if(obj.getYM_Constant().equals("true")){
			// Constant
			obj01.running("constant", UILabel.F6);
		}else{
			// Table
			obj01.running("table", UILabel.F6);
		}
		
		mat02_flowStress obj02 = new mat02_flowStress();
		if(obj.getFS_Constant().equals("true")){
			// Constant
			obj02.running("constant", UILabel.F6);
		}else{
			// Table
			obj02.running("table", UILabel.F6);
		}
		
		mat03_thermalExpansion obj03 = new mat03_thermalExpansion();
		if(obj.getTEC_Constant().equals("true")){
			// Constant
			obj03.running("constant", UILabel.F6);
		}else{
			// Table
			obj03.running("table", UILabel.F6);
		}
		
		mat04_poisson obj04 = new mat04_poisson();
		if(obj.getPR_Constant().equals("true")){
			// Constant
			obj04.running("constant", UILabel.F6);
		}else{
			// Table
			obj04.running("table", UILabel.F6);
		}
		
		mat05_massDensity obj05 = new mat05_massDensity();
		obj05.running(UILabel.F6);
	}

	public void genProcMatF7(String destFolder){
		this.a3materialDefine(UILabel.F7);
		TableData_PLog obj = plogObj.get(6); 
		
		mat01_elasticModulus obj01 = new mat01_elasticModulus();
		if(obj.getYM_Constant().equals("true")){
			// Constant
			obj01.running("constant", UILabel.F7);
		}else{
			// Table
			obj01.running("table", UILabel.F7);
		}
		
		mat02_flowStress obj02 = new mat02_flowStress();
		if(obj.getFS_Constant().equals("true")){
			// Constant
			obj02.running("constant", UILabel.F7);
		}else{
			// Table
			obj02.running("table", UILabel.F7);
		}
		
		mat03_thermalExpansion obj03 = new mat03_thermalExpansion();
		if(obj.getTEC_Constant().equals("true")){
			// Constant
			obj03.running("constant", UILabel.F7);
		}else{
			// Table
			obj03.running("table", UILabel.F7);
		}
		
		mat04_poisson obj04 = new mat04_poisson();
		if(obj.getPR_Constant().equals("true")){
			// Constant
			obj04.running("constant", UILabel.F7);
		}else{
			// Table
			obj04.running("table", UILabel.F7);
		}
		
		mat05_massDensity obj05 = new mat05_massDensity();
		obj05.running(UILabel.F7);
	}

	
	private void writeProc(String destFolder){
		Writer obj = new Writer(destFolder);
		obj.running(procDataList);
	}
}
