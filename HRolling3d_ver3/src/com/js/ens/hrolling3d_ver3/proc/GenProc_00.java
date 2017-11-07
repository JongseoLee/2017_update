package com.js.ens.hrolling3d_ver3.proc;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.ens.hrolling3d_ver3.core.MainController;
import com.js.ens.hrolling3d_ver3.core.UILabel;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;

public class GenProc_00 {
	private MainController MC = MainController.getInstance();
	/*
	private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String userModuleFolder_con = myUtil.setPath(userModuleFolder, "consquent");
	private String userModuleFolder_ind = myUtil.setPath(userModuleFolder, "individual");
	private String destFolder;
	//*/
	
	//private String configFolder_con = FolderTree.folderPath_Config_fileList_con;
	//private String configFolder_ind = FolderTree.folderPath_Config_fileList_ind;
	private String userModuleFolder_con = FolderTree.folderPath_consquent;
	private String userModuleFolder_ind = FolderTree.folderPath_individual;
	private String destFolder;
	
	
	
	private String modelName;
	private String newFileName;
	private String StandType;
	private ArrayList<String> ori_fileDataList = new ArrayList<String>();
	private ArrayList<String> procDataList = new ArrayList<String>();
	
	public GenProc_00(String StandType) {
		// TODO Auto-generated constructor stub
		this.modelName = MC.getModelName();
		this.StandType = StandType;
		
		
	}
	
	public void genBAT(){
		this.ori_fileDataList.clear();
		this.procDataList.clear();
		
		String modulePath = "";
		String fileName = "";
		String ori_filePath = "";
		String new_filePath = "";
		
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
			fileName = "run_consq_jobs.bat";
			ori_filePath = myUtil.setPath(modulePath,fileName);
		}else{
			modulePath = userModuleFolder_ind;
			if(this.StandType.equals(UILabel.F1)){
				fileName = "run_ind_f1.bat";
			}else if(this.StandType.equals(UILabel.F2)){
				fileName = "run_ind_f2.bat";
			}else if(this.StandType.equals(UILabel.F3)){
				fileName = "run_ind_f3.bat";
			}else if(this.StandType.equals(UILabel.F4)){
				fileName = "run_ind_f4.bat";
			}else if(this.StandType.equals(UILabel.F5)){
				fileName = "run_ind_f5.bat";
			}else if(this.StandType.equals(UILabel.F6)){
				fileName = "run_ind_f6.bat";
			}else if(this.StandType.equals(UILabel.F7)){
				fileName = "run_ind_f7.bat";
			}
			ori_filePath = myUtil.setPath(modulePath,fileName);
		}
		
		if(this.StandType.equals(UILabel.F1)){
			new_filePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),fileName);	
		}else if(this.StandType.equals(UILabel.F2)){
			new_filePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),fileName);
		}else if(this.StandType.equals(UILabel.F3)){
			new_filePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),fileName);
		}else if(this.StandType.equals(UILabel.F4)){
			new_filePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),fileName);
		}else if(this.StandType.equals(UILabel.F5)){
			new_filePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),fileName);
		}else if(this.StandType.equals(UILabel.F6)){
			new_filePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),fileName);
		}else if(this.StandType.equals(UILabel.F7)){
			new_filePath = myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),fileName);
		}
		
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
	}
	
	public void genProc00F1(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "00_main_"+this.modelName+"_gen_f1.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f1"),"00_main_model_gen_f1.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),FolderTree.folderName_F1),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
		genBAT();
	}
	
	
	public void genProc00F2(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "00_main_"+this.modelName+"_gen_f2.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f2"),"00_main_model_gen_f2.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),FolderTree.folderName_F2),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
		genBAT();
	}

	public void genProc00F3(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "00_main_"+this.modelName+"_gen_f3.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f3"),"00_main_model_gen_f3.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),FolderTree.folderName_F3),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
		genBAT();
	}
	
	public void genProc00F4(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "00_main_"+this.modelName+"_gen_f4.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f4"),"00_main_model_gen_f4.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),FolderTree.folderName_F4),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
		genBAT();
	}
	
	public void genProc00F5(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "00_main_"+this.modelName+"_gen_f5.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f5"),"00_main_model_gen_f5.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),FolderTree.folderName_F5),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
		genBAT();
	}
	
	public void genProc00F6(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "00_main_"+this.modelName+"_gen_f6.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f6"),"00_main_model_gen_f6.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),FolderTree.folderName_F6),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
		genBAT();
	}
	
	public void genProc00F7(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "00_main_"+this.modelName+"_gen_f7.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f7"),"00_main_model_gen_f7.proc");
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),FolderTree.folderName_F7),this.newFileName);
		readSourceData(ori_filePath);
		swapValue();
		writeProc(new_filePath);
		genBAT();
	}
	
	private void readSourceData(String ori_filePath){
		System.out.println(ori_filePath);
		Reader reader = new Reader(ori_filePath);
		reader.running();
		
		for(String line : reader.getFileDataList()){
			this.ori_fileDataList.add(line.replace("\t", "  "));
		}
		//this.ori_fileDataList=reader.getFileDataList();
	}
	
	private void swapValue(){
		for(String line :this.ori_fileDataList){
			if(line.contains("%model%")){
				String newLien = line.replace("%model%", this.modelName);
				procDataList.add(newLien);
						
			}else{
				procDataList.add(line);
			}
		}
	}
	
	private void writeProc(String destFolder){
		Writer obj = new Writer(destFolder);
		obj.running(procDataList);
	}
}
