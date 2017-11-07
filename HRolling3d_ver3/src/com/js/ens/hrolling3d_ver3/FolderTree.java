package com.js.ens.hrolling3d_ver3;

import com.js.util.myUtil;

public class FolderTree {
	

	
	// Folder name 
	private static final String appShortName = "hr3d3_";
	private static final String folderName_Config = appShortName+"Config";
	private static final String folderName_Log = appShortName+"Log";
	private static final String folderName_Module = appShortName+"Module";
	private static final String folderName_License = appShortName+"License";
	private static final String folderName_Material = appShortName+"MaterialData";
	private static final String folderName_BaseWorkspace = appShortName+"Workspace";
	
	private static final String folderName_consquent = "consquent";
	private static final String folderName_individual = "individual";
	
	private static final String folderName_filelist_con = "filelist_con";
	private static final String folderName_filelist_ind = "filelist_ind";
	
	public static final String folderName_proc = "proc";
	public static final String folderName_result = "result";
	
	public static final String folderName_F1 = "F1";
	public static final String folderName_F2 = "F2";
	public static final String folderName_F3 = "F3";
	public static final String folderName_F4 = "F4";
	public static final String folderName_F5 = "F5";
	public static final String folderName_F6 = "F6";
	public static final String folderName_F7 = "F7";
	
	// File Name 
	private static final String fileName_equation = "eq.txt";
	private static final String fileName_InitValue = "InitValue.txt";
	private static final String fileName_TableColumLabel = "TableColumnLabel.txt";
	private static final String fileName_UILabel = "UILabel.txt";
	private static final String fileName_License = "Licenes.ens";
	private static final String fileName_LicenseKey = "Key.ens";
	private static final String fileName_LogProperties = "log4j.properties";
	private static final String fileName_mappingTable = "mappingTable.csv";
	private static final String fileName_elastic_modulus = "elastic_modulus.txt";
	private static final String fileName_flow_stress = "flow_stress.txt";
	private static final String fileName_expansion = "expansion.txt";
	private static final String fileName_poisson = "poisson.txt";
	
	public static final String fileName_f1FileList = "f1.filelist";
	public static final String fileName_f2FileList = "f2.filelist";
	public static final String fileName_f3FileList = "f3.filelist";
	public static final String fileName_f4FileList = "f4.filelist";
	public static final String fileName_f5FileList = "f5.filelist";
	public static final String fileName_f6FileList = "f6.filelist";
	public static final String fileName_f7FileList = "f7.filelist";
	
	
			
	
	
	// Folder Path 
	public static final String folderPath_AppFolder = System.getProperty("user.dir");
	public static final String folderPath_Config = myUtil.setPath(folderPath_AppFolder, folderName_Config);
	public static final String folderPath_Log = myUtil.setPath(folderPath_AppFolder, folderName_Log);
	public static final String folderPath_Module = myUtil.setPath(folderPath_AppFolder, folderName_Module);
	public static final String folderPath_License = myUtil.setPath(folderPath_AppFolder, folderName_License);
	public static final String folderPath_Material = myUtil.setPath(folderPath_AppFolder, folderName_Material);
	public static final String folderPath_BaseWorkspace = myUtil.setPath(folderPath_AppFolder, folderName_BaseWorkspace);
	
	public static final String folderPath_consquent = myUtil.setPath(folderPath_Module, folderName_consquent);
	public static final String folderPath_individual = myUtil.setPath(folderPath_Module, folderName_individual);
	
	public static final String folderPath_Config_fileList_con = myUtil.setPath(folderPath_Config, folderName_filelist_con);
	public static final String folderPath_Config_fileList_ind = myUtil.setPath(folderPath_Config, folderName_filelist_ind);
	
	// File Path 
	public static final String filePath_equation = myUtil.setPath(folderPath_Config, fileName_equation);
	public static final String filePath_InitValue = myUtil.setPath(folderPath_Config, fileName_InitValue);
	public static final String filePath_TableColumLabel = myUtil.setPath(folderPath_Config, fileName_TableColumLabel);
	public static final String filePath_UILabel = myUtil.setPath(folderPath_Config, fileName_UILabel);
	public static final String filePath_License = myUtil.setPath(folderPath_Config, fileName_License);
	public static final String filePath_LicenseKey = myUtil.setPath(folderPath_Config, fileName_LicenseKey);
	public static final String filePath_LogProperties = myUtil.setPath(folderPath_Config, fileName_LogProperties);
	public static final String filePath_MappingTable = myUtil.setPath(folderPath_Config, fileName_mappingTable);
	
	public static final String filePath_YMTablePath = myUtil.setPath(folderPath_Material, fileName_elastic_modulus);
	public static final String filePath_FSTablePath = myUtil.setPath(folderPath_Material, fileName_flow_stress);
	public static final String filePath_TECTablePath= myUtil.setPath(folderPath_Material, fileName_expansion);
	public static final String filePath_PRTablePath = myUtil.setPath(folderPath_Material, fileName_poisson);
	
	public FolderTree() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean createProjectWorkspace(String workspace){
		String procPath = myUtil.setPath(workspace, FolderTree.folderName_proc);
		String resultPath = myUtil.setPath(workspace, FolderTree.folderName_result);
		
		boolean result_workspace = myUtil.makeDir(workspace);
		boolean result_procPath = myUtil.makeDir(procPath);
		boolean result_resultPath = myUtil.makeDir(resultPath);
		
		if(!result_workspace){
			System.out.println("Error - Project Folder("+workspace+")");
			return false;
		}

		if(!result_procPath){
			System.out.println("Error - Proc Folder("+procPath+")");
			return false;
		}

		if(!result_resultPath){
			System.out.println("Error - Result Folder("+resultPath+")");
			return false;
		}

		return true;
	}

}
