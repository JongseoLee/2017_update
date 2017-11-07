package com.js.ens.hrolling3d_ver3.proc;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.ens.hrolling3d_ver3.core.MainController;
import com.js.ens.hrolling3d_ver3.core.UILabel;
import com.js.io.Reader;
import com.js.util.myUtil;

public class CopyProc {
	private Logger log = Logger.getLogger(CopyProc.class);
	private MainController MC = MainController.getInstance();
	/*
	private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String configFolder_con = myUtil.setPath(configFolder, "filelist_con");
	private String configFolder_ind = myUtil.setPath(configFolder, "filelist_ind");
	private String userModuleFolder_con = myUtil.setPath(userModuleFolder, "consquent");
	private String userModuleFolder_ind = myUtil.setPath(userModuleFolder, "individual");
	private String destFolder;
	//*/
	
	private String configFolder_con = FolderTree.folderPath_Config_fileList_con;
	private String configFolder_ind = FolderTree.folderPath_Config_fileList_ind;
	private String userModuleFolder_con = FolderTree.folderPath_consquent;
	private String userModuleFolder_ind = FolderTree.folderPath_individual;
	private String destFolder;
	
	
	public CopyProc() {
		// TODO Auto-generated constructor stub
		
	}
	
	public void copyF1(String destFolder){
		String configPath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configPath, FolderTree.fileName_f1FileList);
		this.copy(filelistPath,UILabel.F1);
	}
	
	public void copyF2(String destFolder){
		String configPath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configPath, FolderTree.fileName_f2FileList);
		this.copy(filelistPath,UILabel.F2);
	}

	public void copyF3(String destFolder){
		String configPath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configPath, FolderTree.fileName_f3FileList);
		this.copy(filelistPath,UILabel.F3);
	}

	public void copyF4(String destFolder){
		String configPath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configPath, FolderTree.fileName_f4FileList);
		this.copy(filelistPath,UILabel.F4);
	}
	
	public void copyF5(String destFolder){
		String configPath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configPath, FolderTree.fileName_f5FileList);
		this.copy(filelistPath,UILabel.F5);
	}
	
	public void copyF6(String destFolder){
		String configPath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configPath, FolderTree.fileName_f6FileList);
		this.copy(filelistPath,UILabel.F6);
	}
	
	public void copyF7(String destFolder){
		String configPath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		this.destFolder = destFolder;
		String filelistPath = myUtil.setPath(configPath, FolderTree.fileName_f7FileList);
		this.copy(filelistPath,UILabel.F7);
	}
	
	private void copy(String filelistPath, String StandType){
		Reader reader = new Reader(filelistPath);
		reader.running();
		ArrayList<String> fileList = new ArrayList<String>();
		fileList = reader.getFileDataList();
		
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		for(int i=12;i<fileList.size();i++){
			//log.info("* START - Write file");
			String procPath = myUtil.setPath(myUtil.setPath(modulePath, StandType), fileList.get(i));
			String destProcPath = myUtil.setPath(myUtil.setPath(destFolder,StandType), fileList.get(i));
			//System.out.println("!!=>"+procPath);
			//System.out.println("!!=>"+destProcPath);
			long start = System.currentTimeMillis();
			myUtil.fileCopy(procPath, destProcPath);
			long end =System.currentTimeMillis();
			//log.info("* End - Write file("+ (end-start)/1000.0 +"sec)");
			log.info("* Export File Path("+ (end-start)/1000.0 +"sec) : " + destProcPath);
			
		}
	}
	


}
