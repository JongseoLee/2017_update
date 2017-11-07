package com.js.ens.hrolling3d_ver2.proc;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver2.FolderTree;
import com.js.ens.hrolling3d_ver2.core.MainController;
import com.js.ens.hrolling3d_ver2.core.UILabel;
import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_PLog;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class mat02_flowStress {
	private MainController MC = MainController.getInstance();
	//private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	/*
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String userModuleFolder_con = myUtil.setPath(userModuleFolder, "consquent");
	private String userModuleFolder_ind = myUtil.setPath(userModuleFolder, "individual");
	//*/
	
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
	
	
	private String type; //Constant || Table
	private ArrayList<String> ori_fileDataList = new ArrayList<String>();
	private ArrayList<String> procDataList = new ArrayList<String>();
	private ArrayList<String> tempDataList;
	private ArrayList<String> MaterialTableDataList;
	
	private String tableName;
	private String AllCurveNum;
	private String PlasticDataSetNum;
	private String TemperatureDataSetNum;
	private String PlaticStrainDataSetNum;
	
	private ArrayList<String> plasstic_Strain;
	private ArrayList<String> Temperature;
	private ArrayList<String> Plastic_Strain_Rate;
	private ArrayList<String> curveTitleList;
	private ArrayList<String> curveDataTable;
	
	private boolean swapFinishF1 = false;
	private boolean swapFinishF2 = false;
	private boolean swapFinishF3 = false;
	private boolean swapFinishF4 = false;
	private boolean swapFinishF5 = false;
	private boolean swapFinishF6 = false;
	private boolean swapFinishF7 = false;
	
	public mat02_flowStress() {
		// TODO Auto-generated constructor stub
	}
	public void running(String type,String standType){
		this.type = type;
		
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		String ori_filePath = "";
		String new_filePath = "";
		if(this.type.equals("constant")){
			ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, standType), this.FileName_mat02Con);
			new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),standType),this.FileName_mat02Con);
		}else {
			ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, standType), this.FileName_mat02Tbl);
			new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc),standType),this.FileName_mat02Tbl);
			this.readMaterialTableData(standType);
		}
		
		
		
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
		
		while(!swapFinishF1){
			this.checkFinish(0);
		}
		while(!swapFinishF2){
			this.checkFinish(1);
		}
		while(!swapFinishF3){
			this.checkFinish(2);
		}
		while(!swapFinishF4){
			this.checkFinish(3);
		}
		while(!swapFinishF5){
			this.checkFinish(4);
		}
		while(!swapFinishF6){
			this.checkFinish(5);
		}
		while(!swapFinishF7){
			this.checkFinish(6);
		}
		this.writeProc(new_filePath);
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
	
		for(String line: this.ori_fileDataList){
			if(line.contains(GenProcMat.YS_value)){
				newLine = line.replace(GenProcMat.YS_value, obj.getYS_Value());
				this.procDataList.add(newLine);
			}else if(line.contains(GenProcMat.TS_value)){
				newLine = line.replace(GenProcMat.TS_value, obj.getTS_Value());
				this.procDataList.add(newLine);
			}else if(line.contains(GenProcMat.E_value)){
				newLine = line.replace(GenProcMat.E_value, obj.getE_Value());
				this.procDataList.add(newLine);
			}else if(line.contains(GenProcMat.FS_tableName)){
				newLine = line.replace(GenProcMat.FS_tableName, this.tableName);
				this.procDataList.add(newLine);
			}else if(line.contains(GenProcMat.FS_PlasticDataSetNum)){
				newLine = line.replace(GenProcMat.FS_PlasticDataSetNum, this.PlasticDataSetNum);
				this.procDataList.add(newLine);
			}else if(line.contains(GenProcMat.FS_TemperatureDataSetNum)){
				newLine = line.replace(GenProcMat.FS_TemperatureDataSetNum, this.TemperatureDataSetNum);
				this.procDataList.add(newLine);
			}else if(line.contains(GenProcMat.FS_PlaticStrainDataSetNum)){
				newLine = line.replace(GenProcMat.FS_PlaticStrainDataSetNum, this.PlaticStrainDataSetNum);
				this.procDataList.add(newLine);
			}else if(line.contains(GenProcMat.FS_plasstic_Strain)){
				for(String str : plasstic_Strain){
					this.procDataList.add(str);
				}
			}else if(line.contains(GenProcMat.FS_Temperature)){
				for(String str : Temperature){
					procDataList.add(str);
				}
			}else if(line.contains(GenProcMat.FS_Plastic_Strain_Rate)){
				for(String str : Plastic_Strain_Rate){
					procDataList.add(str);
				}
			}else if(line.contains(GenProcMat.FS_curveDataTable)){
				int tableColNum = 0;
				for(int i=0; i<Integer.parseInt(AllCurveNum) ;i++){
					procDataList.add("|"+curveTitleList.get(i));
					for(int j = tableColNum;j<Integer.parseInt(PlasticDataSetNum)*(i+1);j++){
						procDataList.add(curveDataTable.get(j));
						tableColNum++;
					}
				}
			}else {
				this.procDataList.add(line);
			}
		}
	}
	
	
	private void writeProc(String destFolder) {
		// TODO Auto-generated method stub
		Writer writer = new Writer(destFolder);
		writer.running(this.procDataList);
	}
	
	private void checkFinish(int StandType){
		if(StandType == 0){
			boolean isFinish = false;
			tempDataList = new ArrayList<String>();
			for(String line:procDataList){
				tempDataList.add(line);
			}
			for(String line : tempDataList){
				if(line.contains(GenProcMat.YS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.TS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.E_value)){
					isFinish = false;
					break;
				}else { 
					isFinish = true;
					this.swapFinishF1 = true;
				}
			}
			if(!isFinish){
				swapValue2(tempDataList,StandType);	
			}
			
		}else if(StandType == 1){
			boolean isFinish = false;
			tempDataList = new ArrayList<String>();
			for(String line:procDataList){
				tempDataList.add(line);
			}
			for(String line : tempDataList){
				if(line.contains(GenProcMat.YS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.TS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.E_value)){
					isFinish = false;
					break;
				}else { 
					isFinish = true;
					this.swapFinishF2 = true;
				}
			}
			if(!isFinish){
				swapValue2(tempDataList,StandType);	
			}
		}else if(StandType == 2){
			boolean isFinish = false;
			tempDataList = new ArrayList<String>();
			for(String line:procDataList){
				tempDataList.add(line);
			}
			for(String line : tempDataList){
				if(line.contains(GenProcMat.YS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.TS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.E_value)){
					isFinish = false;
					break;
				}else { 
					isFinish = true;
					this.swapFinishF3 = true;
				}
			}
			if(!isFinish){
				swapValue2(tempDataList,StandType);	
			}
		}else if(StandType == 3){
			boolean isFinish = false;
			tempDataList = new ArrayList<String>();
			for(String line:procDataList){
				tempDataList.add(line);
			}
			for(String line : tempDataList){
				if(line.contains(GenProcMat.YS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.TS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.E_value)){
					isFinish = false;
					break;
				}else { 
					isFinish = true;
					this.swapFinishF4 = true;
				}
			}
			if(!isFinish){
				swapValue2(tempDataList,StandType);	
			}
		}else if(StandType == 4){
			boolean isFinish = false;
			tempDataList = new ArrayList<String>();
			for(String line:procDataList){
				tempDataList.add(line);
			}
			for(String line : tempDataList){
				if(line.contains(GenProcMat.YS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.TS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.E_value)){
					isFinish = false;
					break;
				}else { 
					isFinish = true;
					this.swapFinishF5 = true;
				}
			}
			if(!isFinish){
				swapValue2(tempDataList,StandType);	
			}
		}else if(StandType == 5){
			boolean isFinish = false;
			tempDataList = new ArrayList<String>();
			for(String line:procDataList){
				tempDataList.add(line);
			}
			for(String line : tempDataList){
				if(line.contains(GenProcMat.YS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.TS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.E_value)){
					isFinish = false;
					break;
				}else { 
					isFinish = true;
					this.swapFinishF6 = true;
				}
			}
			if(!isFinish){
				swapValue2(tempDataList,StandType);	
			}
		}else if(StandType == 6){
			boolean isFinish = false;
			tempDataList = new ArrayList<String>();
			for(String line:procDataList){
				tempDataList.add(line);
			}
			for(String line : tempDataList){
				if(line.contains(GenProcMat.YS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.TS_value)){
					isFinish = false;
					break;
				}else if(line.contains(GenProcMat.E_value)){
					isFinish = false;
					break;
				}else { 
					isFinish = true;
					this.swapFinishF7 = true;
				}
			}
			if(!isFinish){
				swapValue2(tempDataList,StandType);	
			}
		}
	}
	
	
	
	public void swapValue2(ArrayList<String> tempDataList,int StandType){
		procDataList = new ArrayList<String>();
		TableData_PLog obj = MC.getTableDataPLogList().get(StandType);
		String newLine = null;
		for(String line : tempDataList){
			if(line.contains(GenProcMat.YS_value)){
				newLine = line.replace(GenProcMat.YS_value, obj.getYS_Value());
				procDataList.add(newLine);
			}else if(line.contains(GenProcMat.TS_value)){
				newLine = line.replace(GenProcMat.TS_value, obj.getTS_Value());
				procDataList.add(newLine);
			}else if(line.contains(GenProcMat.E_value)){
				newLine = line.replace(GenProcMat.E_value, obj.getE_Value());
				procDataList.add(newLine);
			}else {
				procDataList.add(line);
			}
		}
	}
	
	
	
	
	private void readMaterialTableData(String standType){
		if(standType.equals(UILabel.F1)){
			this.MaterialTableDataList = new ArrayList<String>();
			TableData_PLog obj = MC.getTableDataPLogList().get(0);
			Reader reader = new Reader(obj.getFS_Value());
			reader.running();
			this.MaterialTableDataList = reader.getFileDataList();
			this.parsingData();
		}else if(standType.equals(UILabel.F2)){
			this.MaterialTableDataList = new ArrayList<String>();
			TableData_PLog obj = MC.getTableDataPLogList().get(1);
			Reader reader = new Reader(obj.getFS_Value());
			reader.running();
			this.MaterialTableDataList = reader.getFileDataList();
			this.parsingData();
		}else if(standType.equals(UILabel.F3)){
			this.MaterialTableDataList = new ArrayList<String>();
			TableData_PLog obj = MC.getTableDataPLogList().get(2);
			Reader reader = new Reader(obj.getFS_Value());
			reader.running();
			this.MaterialTableDataList = reader.getFileDataList();
			this.parsingData();
		}else if(standType.equals(UILabel.F4)){
			this.MaterialTableDataList = new ArrayList<String>();
			TableData_PLog obj = MC.getTableDataPLogList().get(3);
			Reader reader = new Reader(obj.getFS_Value());
			reader.running();
			this.MaterialTableDataList = reader.getFileDataList();
			this.parsingData();
		}else if(standType.equals(UILabel.F5)){
			this.MaterialTableDataList = new ArrayList<String>();
			TableData_PLog obj = MC.getTableDataPLogList().get(4);
			Reader reader = new Reader(obj.getFS_Value());
			reader.running();
			this.MaterialTableDataList = reader.getFileDataList();
			this.parsingData();
		}else if(standType.equals(UILabel.F6)){
			this.MaterialTableDataList = new ArrayList<String>();
			TableData_PLog obj = MC.getTableDataPLogList().get(5);
			Reader reader = new Reader(obj.getFS_Value());
			reader.running();
			this.MaterialTableDataList = reader.getFileDataList();
			this.parsingData();
		}else if(standType.equals(UILabel.F7)){
			this.MaterialTableDataList = new ArrayList<String>();
			TableData_PLog obj = MC.getTableDataPLogList().get(6);
			Reader reader = new Reader(obj.getFS_Value());
			reader.running();
			this.MaterialTableDataList = reader.getFileDataList();
			this.parsingData();
		}
	}
	
	private void parsingData(){
		plasstic_Strain = new ArrayList<String>();
		Temperature = new ArrayList<String>();
		Plastic_Strain_Rate = new ArrayList<String>();
		curveTitleList = new ArrayList<String>();
		curveDataTable = new ArrayList<String>();
		
		ArrayList<String> splitDataTokens = new ArrayList<String>();
		String firstLine = MaterialTableDataList.get(1).replace("\t", " ").replace("," ,"");
		splitDataTokens = ParserDefault.splitLineData(firstLine," ");
		tableName = MaterialTableDataList.get(0);
		AllCurveNum = splitDataTokens.get(0);
		PlasticDataSetNum = splitDataTokens.get(1);
		TemperatureDataSetNum = splitDataTokens.get(2);
		PlaticStrainDataSetNum = splitDataTokens.get(3);
		
		int curveNum = 0;
		
		for(int i=5;i<MaterialTableDataList.size();i++){
			String line = MaterialTableDataList.get(i).replace("\t", " ").replace(",","");
			splitDataTokens = ParserDefault.splitLineData(line, " ");
			String firstTokens = splitDataTokens.get(0);
			
			if(firstTokens.contains("===")){
				// === CURVE_01 Sig_Yiel	 T=20.0 C	 Eps_dot=1.60 1/s
				//               0               1           2                    
				// === CURVE_02 Sig_Yiel	 T=100.0 C	 Eps_dot=1.60 1/s
				//  0      1        2            3   4       5         6 
				curveTitleList.add(line);
				//String TValue = ParserDefault.splitLineData(ParserDefault.splitLineData(splitDataTokens.get(1), "=").get(1), " ").get(0);
				//String SPValue = ParserDefault.splitLineData(ParserDefault.splitLineData(splitDataTokens.get(2), "=").get(1), " ").get(0);
				String TValue = ParserDefault.splitLineData(splitDataTokens.get(3), "=").get(1);
				String SPValue = ParserDefault.splitLineData(splitDataTokens.get(5), "=").get(1);
				if(curveNum == -1){
					Temperature.add(TValue);
					Plastic_Strain_Rate.add(SPValue);
					
				}else {
					if(!Temperature.contains(TValue)){
						Temperature.add(TValue);
					}
					if(!Plastic_Strain_Rate.contains(SPValue)){
						Plastic_Strain_Rate.add(SPValue);	
					}
				}
				i=i+1;
				curveNum++;
			}
			if(splitDataTokens.size() == 2 && myUtil.isFloatValue(firstTokens)){
				if(curveNum == 1){
					plasstic_Strain.add(splitDataTokens.get(0));
				}
				curveDataTable.add(splitDataTokens.get(1));
			}
		}
	}
}
