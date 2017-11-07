package com.js.ens.hrolling3d_ver2.proc;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver2.FolderTree;
import com.js.ens.hrolling3d_ver2.core.MainController;
import com.js.ens.hrolling3d_ver2.core.UILabel;
import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_PLog;
import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_SlabPlateInfo;
import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_Variable;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.util.myUtil;
import com.js.ens.hrolling3d_ver2.proc.ProcMaker;

public class GenProc_01 {
	private MainController MC = MainController.getInstance();
	/*
	private String configFolder = myUtil.setPath(System.getProperty("user.dir"),"userConfig");
	private String userModuleFolder = myUtil.setPath(System.getProperty("user.dir"), "userModule");
	private String userModuleFolder_con = myUtil.setPath(userModuleFolder, "consquent");
	private String userModuleFolder_ind = myUtil.setPath(userModuleFolder, "individual");
	private String destFolder; 
	// */
	private String userModuleFolder_con = FolderTree.folderPath_consquent;
	private String userModuleFolder_ind = FolderTree.folderPath_individual;
	private String destFolder;
	
	
	
	
	
	private String newFileName;
	private ArrayList<String> ori_fileDataList = new ArrayList<String>();
	private ArrayList<String> procDataList = new ArrayList<String>();
	private ArrayList<TableData_PLog> plogObj = null;
	private TableData_SlabPlateInfo spObj = null;
	private TableData_Variable vObj = null;
	
	public GenProc_01() {
		// TODO Auto-generated constructor stub
		plogObj = MC.getTableDataPLogList();
		spObj = MC.getTableDataSlabPlateInfoObj();
		vObj = MC.getTableDataVariableObj();
	}

	public void genProc01F1(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "01_define_parameters_f1.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f1"),newFileName);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc), FolderTree.folderName_F1),this.newFileName);
		readSourceData(ori_filePath);
		swapValue(0);
		writeProc(new_filePath);
	}
	
	public void genProc01F2(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "01_define_parameters_f2.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f2"),newFileName);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc), FolderTree.folderName_F2),this.newFileName);
		readSourceData(ori_filePath);
		swapValue(1);
		writeProc(new_filePath);
	}

	public void genProc01F3(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "01_define_parameters_f3.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f3"),newFileName);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc), FolderTree.folderName_F3),this.newFileName);
		readSourceData(ori_filePath);
		swapValue(2);
		writeProc(new_filePath);
	}
	
	public void genProc01F4(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "01_define_parameters_f4.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f4"),newFileName);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc), FolderTree.folderName_F4),this.newFileName);
		readSourceData(ori_filePath);
		swapValue(3);
		writeProc(new_filePath);
	}
	
	public void genProc01F5(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "01_define_parameters_f5.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f5"),newFileName);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc), FolderTree.folderName_F5),this.newFileName);
		readSourceData(ori_filePath);
		swapValue(4);
		writeProc(new_filePath);
	}
	
	public void genProc01F6(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "01_define_parameters_f6.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f6"),newFileName);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc), FolderTree.folderName_F6),this.newFileName);
		readSourceData(ori_filePath);
		swapValue(5);
		writeProc(new_filePath);
	}
	
	public void genProc01F7(String destFolder){
		String modulePath = "";
		if(MC.getApplyType().equals(MC.ApplyType_Consequent)){
			modulePath = userModuleFolder_con;
		}else{
			modulePath = userModuleFolder_ind;
		}
		
		this.newFileName = "01_define_parameters_f7.proc";
		String ori_filePath = myUtil.setPath(myUtil.setPath(modulePath, "f7"),newFileName);
		String new_filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_proc), FolderTree.folderName_F7),this.newFileName);
		readSourceData(ori_filePath);
		swapValue(6);
		writeProc(new_filePath);
	}
	
	private void readSourceData(String ori_filePath){
		Reader reader = new Reader(ori_filePath);
		reader.running();
		//ArrayList<String> temp = new ArrayList<String>();
		for(String line : reader.getFileDataList()){
			this.ori_fileDataList.add(line.replace("\t", "  "));
		}
		//this.ori_fileDataList=reader.getFileDataList();
	}
	
	private void swapValue(int StandType){
		for(String line :this.ori_fileDataList){
			if(line.contains(ProcMaker.WR_TDIA)){
				String newLien = line.replace(ProcMaker.WR_TDIA, plogObj.get(StandType).getWR_TDIA());
				procDataList.add(newLien);
			}else if(line.contains(ProcMaker.WR_BDIA)){
				String newLien = line.replace(ProcMaker.WR_BDIA, plogObj.get(StandType).getWR_BDIA());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.BUR_BDIA)){
				String newLien = line.replace(ProcMaker.BUR_BDIA,plogObj.get(StandType).getBUR_BDIA());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.BUR_TDIA)){
				String newLien = line.replace(ProcMaker.BUR_TDIA,plogObj.get(StandType).getBUR_TDIA());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.WR_ICRN)){
				String newLien = line.replace(ProcMaker.WR_ICRN,plogObj.get(StandType).getWR_ICRN());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.ROL_GAP)){
				String newLien = line.replace(ProcMaker.ROL_GAP,plogObj.get(StandType).getROL_GAP());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.wr_div_angle)){
				String newLien = line.replace(ProcMaker.wr_div_angle,plogObj.get(StandType).getWr_div_angle());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.bur_div_angle)){
				String newLien = line.replace(ProcMaker.bur_div_angle,plogObj.get(StandType).getBur_div_angle());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.wr_len)){
				String newLien = line.replace(ProcMaker.wr_len,plogObj.get(StandType).getWr_len());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.bur_len)){
				String newLien = line.replace(ProcMaker.bur_len,plogObj.get(StandType).getBur_len());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.ENTRY_THK)){
				String newLien = line.replace(ProcMaker.ENTRY_THK,plogObj.get(StandType).getENTRY_THK());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.STP_WID)){
				String newLien = line.replace(ProcMaker.STP_WID,plogObj.get(StandType).getSTP_WID());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.STP_LEN)){
				String newLien = line.replace(ProcMaker.STP_LEN,plogObj.get(StandType).getSTP_LEN());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.ENTRY_TEMP)){
				String newLien = line.replace(ProcMaker.ENTRY_TEMP,plogObj.get(StandType).getENTRY_TEMP());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.EXIT_TEMP)){
				String newLien = line.replace(ProcMaker.EXIT_TEMP,plogObj.get(StandType).getEXIT_TEMP() );
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.p_in)){
				String newLien = line.replace(ProcMaker.p_in,plogObj.get(StandType).getP_in());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.pl_m)){
				String newLien = line.replace(ProcMaker.pl_m,plogObj.get(StandType).getPl_m());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.t_div)){
				String newLien = line.replace(ProcMaker.t_div,plogObj.get(StandType).getT_div());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.SPEED)){
				String newLien = line.replace(ProcMaker.SPEED,plogObj.get(StandType).getSPEED());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.PAS_LINE)){
				String newLien = line.replace(ProcMaker.PAS_LINE,plogObj.get(StandType).getPAS_LINE());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.P_CROSS)){
				String newLien = line.replace(ProcMaker.P_CROSS,plogObj.get(StandType).getP_CROSS());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.TORQ)){
				String newLien = line.replace(ProcMaker.TORQ,plogObj.get(StandType).getTORQ());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.BEND)){
				String newLien = line.replace(ProcMaker.BEND, plogObj.get(StandType).getBEND());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.TENS)){
				String newLien = line.replace(ProcMaker.TENS, plogObj.get(StandType).getTENS());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.f_r2p)){
				String newLien = line.replace(ProcMaker.f_r2p, plogObj.get(StandType).getF_r2p());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.f_r2r)){
				String newLien = line.replace(ProcMaker.f_r2r, plogObj.get(StandType).getF_r2r());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.lcase_inc)){
				String newLien = line.replace(ProcMaker.lcase_inc, plogObj.get(StandType).getlcase_inc());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.post_inc)){
				String newLien = line.replace(ProcMaker.post_inc, plogObj.get(StandType).getPost_inc());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.FRCE)){
				String newLien = line.replace(ProcMaker.FRCE, plogObj.get(StandType).getFRCE());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.EXIT_THK)){
				String newLien = line.replace(ProcMaker.EXIT_THK, plogObj.get(StandType).getEXIT_THK());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.ROL_TIM)){
				String newLien = line.replace(ProcMaker.ROL_TIM, plogObj.get(StandType).getROL_TIM());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.IDL_TIM)){
				String newLien = line.replace(ProcMaker.IDL_TIM, plogObj.get(StandType).getIDL_TIM());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.BUR_WEAR)){
				String newLien = line.replace(ProcMaker.BUR_WEAR, plogObj.get(StandType).getBUR_WEAR());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.WR_WEAR)){
				String newLien = line.replace(ProcMaker.WR_WEAR, plogObj.get(StandType).getWR_WEAR());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.WR_THRM)){
				String newLien = line.replace(ProcMaker.WR_THRM, plogObj.get(StandType).getWR_THRM());
				procDataList.add(newLien);	
			}
			
			/*
			else if(line.contains(ProcMaker.YM_VALUE)){
				String newLien = line.replace(ProcMaker.YM_VALUE, plogObj.get(StandType).getYM_Value());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.TEH_VALUE)){
				String newLien = line.replace(ProcMaker.TEH_VALUE, plogObj.get(StandType).getTEC_Value());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.PR_VALUE)){
				String newLien = line.replace(ProcMaker.PR_VALUE, plogObj.get(StandType).getPR_Value());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.MD_VALUE)){
				String newLien = line.replace(ProcMaker.MD_VALUE, plogObj.get(StandType).getMD_Value());
				procDataList.add(newLien);	
			}
			// */
			
			else if(line.contains(ProcMaker.lcase_time)){
				String newLien = line.replace(ProcMaker.lcase_time, plogObj.get(StandType).getLcase_time());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.lcase_dt)){
				String newLien = line.replace(ProcMaker.lcase_dt, plogObj.get(StandType).getlcase_dt());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.ltime_scale)){
				String newLien = line.replace(ProcMaker.ltime_scale, plogObj.get(StandType).getLtime_scale());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.Domain)){
				String newLien = line.replace(ProcMaker.Domain, plogObj.get(StandType).getDomain());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.Thread)){
				String newLien = line.replace(ProcMaker.Thread, plogObj.get(StandType).getThread());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.vel_rate_top)){
				String newLien = line.replace(ProcMaker.vel_rate_top, plogObj.get(StandType).getSpeed_different_ratio_top_roll());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.vel_rate_bottom)){
				String newLien = line.replace(ProcMaker.vel_rate_bottom, plogObj.get(StandType).getSpeed_different_ratio_bottom_roll());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.wr_trot)){
				String newLien = line.replace(ProcMaker.wr_trot, plogObj.get(StandType).getWr_trot());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.wr_brot)){
				String newLien = line.replace(ProcMaker.wr_brot, plogObj.get(StandType).getWr_brot());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.bur_trot)){
				String newLien = line.replace(ProcMaker.bur_trot, plogObj.get(StandType).getBur_trot());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.bur_brot)){
				String newLien = line.replace(ProcMaker.bur_brot, plogObj.get(StandType).getBur_brot());
				procDataList.add(newLien);	
			}
			
			else if(line.contains(ProcMaker.VAR1)){
				String newLien = line.replace(ProcMaker.VAR1, vObj.getVAR1());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR2)){
				String newLien = line.replace(ProcMaker.VAR2, vObj.getVAR2());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR3)){
				String newLien = line.replace(ProcMaker.VAR3, vObj.getVAR3());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR4)){
				String newLien = line.replace(ProcMaker.VAR4, vObj.getVAR4());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR5)){
				String newLien = line.replace(ProcMaker.VAR5, vObj.getVAR5());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR6)){
				String newLien = line.replace(ProcMaker.VAR6, vObj.getVAR6());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR7)){
				String newLien = line.replace(ProcMaker.VAR7, vObj.getVAR7());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR8)){
				String newLien = line.replace(ProcMaker.VAR8, vObj.getVAR8());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR9)){
				String newLien = line.replace(ProcMaker.VAR9, vObj.getVAR9());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR10)){
				String newLien = line.replace(ProcMaker.VAR10, vObj.getVAR10());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR11)){
				String newLien = line.replace(ProcMaker.VAR11, vObj.getVAR11());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR12)){
				String newLien = line.replace(ProcMaker.VAR12, vObj.getVAR12());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR13)){
				String newLien = line.replace(ProcMaker.VAR13, vObj.getVAR13());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR14)){
				String newLien = line.replace(ProcMaker.VAR14, vObj.getVAR14());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR15)){
				String newLien = line.replace(ProcMaker.VAR15, vObj.getVAR15());
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.VAR16)){
				String newLien = line.replace(ProcMaker.VAR16, vObj.getVAR16());
				procDataList.add(newLien);	
			}
			
			
			else if(line.contains(ProcMaker.STHK)){
				String newLien = line.replace(ProcMaker.STHK, spObj.getSTHK() );
				procDataList.add(newLien);	
			}else if(line.contains(ProcMaker.SWID)){
				String newLine = line.replace(ProcMaker.SWID, spObj.getSWID());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.SLEN)){
				String newLine = line.replace(ProcMaker.SLEN, spObj.getSLEN());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.SWET)){
				String newLine = line.replace(ProcMaker.SWET, spObj.getSWET());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.PTHK)){
				String newLine = line.replace(ProcMaker.PTHK, spObj.getPTHK());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.PWID)){
				String newLine = line.replace(ProcMaker.PWID, spObj.getPWID());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.PLEN)){
				String newLine = line.replace(ProcMaker.PLEN, spObj.getPLEN());
				procDataList.add(newLine);
			}else if(line.contains(ProcMaker.PWET)){
				String newLine = line.replace(ProcMaker.PWET, spObj.getPWET());
				procDataList.add(newLine);
			}
			
			else if(line.contains(ProcMaker.plateSectionFile)){
				if(MC.getRunType().equals(MC.RunType_Single)){
					String newLine = line.replace(ProcMaker.plateSectionFile, MC.getSectionFilePath());
					procDataList.add(newLine);
				}
			}else if(line.contains(ProcMaker.dummyPlateSectionFile)){
				if(MC.getRunType().equals(MC.RunType_Single)){
					String newLine = line.replace(ProcMaker.dummyPlateSectionFile, MC.getDummySectionFilePath());
					procDataList.add(newLine);	
				}
				
			}
			
			else{
				procDataList.add(line);
			}
		}
	}
	
	private void writeProc(String destFolder){
		Writer obj = new Writer(destFolder);
		obj.running(procDataList);
	}
	
}
