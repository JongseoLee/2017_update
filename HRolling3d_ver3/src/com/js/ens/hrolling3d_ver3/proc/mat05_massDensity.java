package com.js.ens.hrolling3d_ver3.proc;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.ens.hrolling3d_ver3.core.MainController;
import com.js.ens.hrolling3d_ver3.core.UILabel;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableData_PLog;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class mat05_massDensity {
	private MainController MC = MainController.getInstance();
	//private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	/*
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String userModuleFolder_con = myUtil.setPath(userModuleFolder, "consquent");
	private String userModuleFolder_ind = myUtil.setPath(userModuleFolder, "individual");
	// */
	
	private String userModuleFolder_con = FolderTree.folderPath_consquent;
	private String userModuleFolder_ind = FolderTree.folderPath_individual;
	
	
	
	public String FileName_mat01Con = "mat01_elastic_modulus_const.proc";
	public String FileName_mat01Tbl = "mat01_elastic_modulus.proc";
	public String FileName_mat02Con = "mat02_flow_stress_const.proc";
	public String FileName_mat02Tbl = "mat02_flow_stress.proc";
	public String FileName_mat03Con = "mat03_thermal_expansion_const.proc";
	public String FileName_mat03Tbl = "mat03_thermal_expansion.proc";
	public String FileName_mat04Con = "mat04_poisson_const.proc";
	public String FileName_mat04Tbl = "mat04_poisson.proc";
	public String FileName_mat05 = "mat05_mass_density.proc";
	
	
	private ArrayList<String> ori_fileDataList = new ArrayList<String>();
	private ArrayList<String> procDataList = new ArrayList<String>();
	
	
	public mat05_massDensity() {
		// TODO Auto-generated constructor stub
	}
	
	public void running( String standType){
		
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		String ori_filePath = "";
		String new_filePath = "";
		ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, standType), this.FileName_mat05);
		new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),standType),this.FileName_mat05);
		
		readSourceData(ori_filePath);
		if(standType.equals(UILabel.F1)){
			swapValue(0);
		}else if(standType.equals(UILabel.F2)){
			swapValue(1);
		}else if(standType.equals(UILabel.F3)){
			swapValue(2);
		}else if(standType.equals(UILabel.F4)){
			swapValue(3);
		}else if(standType.equals(UILabel.F5)){
			swapValue(4);
		}else if(standType.equals(UILabel.F6)){
			swapValue(5);
		}else if(standType.equals(UILabel.F7)){
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
		this.procDataList = new ArrayList<String>();
		TableData_PLog obj = MC.getTableDataPLogList().get(StandType);
		String newLine = null;
		for(String line : this.ori_fileDataList){
			if(line.contains(GenProcMat.MD_value)){
				newLine = line.replace(GenProcMat.MD_value, obj.getMD_Value());
				this.procDataList.add(newLine);
			}else{
				this.procDataList.add(line);
			}
		}
	}
	
	private void writeProc(String destFolder){
		Writer obj = new Writer(destFolder);
		obj.running(procDataList);
	}
}
