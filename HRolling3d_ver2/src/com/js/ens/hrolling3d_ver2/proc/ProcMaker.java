package com.js.ens.hrolling3d_ver2.proc;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Display;

import com.js.ens.hrolling3d_ver2.Application;
import com.js.ens.hrolling3d_ver2.FolderTree;
import com.js.ens.hrolling3d_ver2.core.MainController;
import com.js.ens.hrolling3d_ver2.core.UILabel;
import com.js.ens.hrolling3d_ver2.dialog.MessageDlg;
import com.js.util.myUtil;

public class ProcMaker {
	private MainController MC = MainController.getInstance();
	//private CopyProc cp = new CopyProc();
	private String StandType;
	private String workspace;
	private String destPath;
	
	public static String WR_TDIA = "%WR TDIA%";
	public static String WR_BDIA = "%WR BDIA%";
	public static String BUR_TDIA = "%BUR TDIA%";
	public static String BUR_BDIA = "%BUR BDIA%";
	public static String WR_ICRN = "%WR ICRN%";

	public static String ROL_GAP = "%ROL GAP%";

	public static String wr_div_angle = "%wr_div_angle%";
	public static String bur_div_angle = "%bur_div_angle%";

	public static String wr_len = "%wr_len%";
	public static String bur_len ="%bur_len%";

	public static String ENTRY_THK = "%ENTRY THK%";
	public static String STP_WID = "%STP WID%";
	public static String STP_LEN = "%STP LEN%";
	public static String ENTRY_TEMP = "%ENTRY TEMP%";
	public static String EXIT_TEMP = "%EXIT TEMP%";
	public static String p_in = "%p_in%";			
	public static String pl_m = "%pl_m%";
	public static String t_div = "%t_div%";

	public static String SPEED = "%SPEED%";

	public static String PAS_LINE = "%PAS LINE%";
	public static String P_CROSS = "%P-CROSS%";
	public static String TORQ = "%TORQ%";
	public static String BEND = "%BEND%";
	public static String TENS = "%TENS%";

	public static String f_r2p = "%f_r2p%";
	public static String f_r2r ="%f_r2r%";

	public static String lcase_inc ="%lcase_inc%";
	public static String post_inc ="%post_inc%";


	public static String FRCE = "%FRCE%";
	public static String EXIT_THK = "%EXIT THK%";
	public static String ROL_TIM = "%ROL TIM%"; 
	public static String IDL_TIM = "%IDL TIM%";
	public static String BUR_WEAR = "%BUR WEAR%";
	public static String WR_WEAR = "%WR WEAR%";
	public static String WR_THRM = "%WR THRM%";

	/*
	public static String YM_VALUE = "%YM VALUE%";
	public static String TEH_VALUE ="%TEH VALUE%";
	public static String PR_VALUE = "%PR VALUE%";
	public static String MD_VALUE = "%MD VALUE%";
	// */
	

	public static String lcase_time = "%lcase_time%";
	public static String lcase_dt = "%lcase_dt%";
	public static String ltime_scale = "%ltime_scale%";
	public static String Domain = "%Domain%";
	public static String Thread = "%Thread%";

	public static String vel_rate_top = "%vel_rate_top%";
	public static String vel_rate_bottom = "%vel_rate_bottom%";
	public static String wr_trot = "%wr_trot%";
	public static String wr_brot = "%wr_brot%";
	public static String bur_trot = "%bur_trot%";
	public static String bur_brot = "%bur_brot%";
	
	public static String VAR1 = "%VAR1%";
	public static String VAR2 = "%VAR2%";
	public static String VAR3 = "%VAR3%";
	public static String VAR4 = "%VAR4%";
	public static String VAR5 = "%VAR5%";
	public static String VAR6 = "%VAR6%";
	public static String VAR7 = "%VAR7%";
	public static String VAR8 = "%VAR8%";
	public static String VAR9 = "%VAR9%";
	public static String VAR10 = "%VAR10%";
	public static String VAR11 = "%VAR11%";
	public static String VAR12 = "%VAR12%";
	public static String VAR13 = "%VAR13%";
	public static String VAR14 = "%VAR14%";
	public static String VAR15 = "%VAR15%";
	public static String VAR16 = "%VAR16%";
	
	public static String STRIP_NO = "%STRIP_NO%";
	public static String STHK = "%STHK%";
	public static String SWID = "%SWID%";
	public static String SLEN = "%SLEN%";
	public static String SWET = "%SWET%";
	public static String PTHK = "%PTHK%";
	public static String PWID = "%PWID%";
	public static String PLEN = "%PLEN%";
	public static String PWET = "%PWET%";
	
	public static String plateSectionFile = "%plate_msect_filename%"; 
	public static String dummyPlateSectionFile = "%dplate_msect_filename%";
	
	public static String wr_chamferX = "%wr_chamferX%";
	public static String wr_chamferY = "%wr_chamferY%";
	public static String wr_round = "%wr_round%";
	
	public static String bur_chamferX = "%bur_chamferX%";
	public static String bur_chamferY = "%bur_chamferY%";
	
	public static String p_cr = "%p_cr%";
	
	public static String YM_ROLL_VALUE = "%YM_ROLL_VALUE%";
	public static String PR_ROLL_VALUE = "%PR_ROLL_VALUE%";
	
	public static String f1f4_wr_min_r = "%f1f4_wr_min_r%";
	public static String f5f7_wr_min_r = "%f5f7_wr_min_r%";
	
	public static String f1f4_wr_round_min_r = "%f1f4_wr_round_min_r%";
	public static String f5f7_wr_round_min_r = "%f5f7_wr_round_min_r%";
	public static String f1f7_bur_round_min_r = "%f1f7_bur_round_min_r%";

	public static String f1f4_wr_max_round = "%f1f4_wr_max_round%";
	public static String f5f7_wr_max_round = "%f5f7_wr_max_round%";
	
	public static String f1f7_bur_min_r = "%f1f7_bur_min_r%";
	public static String f1f4_wr_max_cx = "%f1f4_wr_max_cx%";
	public static String f5f7_wr_max_cx = "%f5f7_wr_max_cx%";
	public static String f1f7_bur_max_cx = "%f1f7_bur_max_cx%";
	
	

	
	public ProcMaker(String StandType) {
		// TODO Auto-generated constructor stub
		this.StandType = StandType;
	}
	
	public void running(){
		this.workspace = MC.getWorkspace();
		this.destPath = myUtil.setPath(workspace, FolderTree.folderName_proc);
		
		CopyProc cpObj = new CopyProc();
		GenProc_00 genProc00Obj = new GenProc_00(this.StandType);
		GenProc_01 genProc01Obj = new GenProc_01();
		GenProcMat genProcMatObj = new GenProcMat();
		
		if(this.StandType.equals(UILabel.F1)){
			myUtil.makeDir(myUtil.setPath(this.destPath, UILabel.F1));
			myUtil.makeDir(myUtil.setPath(myUtil.setPath(this.destPath, UILabel.F1),"rolls"));
			genProc00Obj.genProc00F1(this.destPath);
			genProc01Obj.genProc01F1(this.destPath);
			genProcMatObj.genProcMatF1(this.destPath);
			cpObj.copyF1(this.destPath);
		}else if(this.StandType.equals(UILabel.F2)){
			myUtil.makeDir(myUtil.setPath(this.destPath, UILabel.F2));
			myUtil.makeDir(myUtil.setPath(myUtil.setPath(this.destPath, UILabel.F2),"rolls"));
			genProc00Obj.genProc00F2(this.destPath);
			genProc01Obj.genProc01F2(this.destPath);
			genProcMatObj.genProcMatF2(this.destPath);
			cpObj.copyF2(this.destPath);
		}else if(this.StandType.equals(UILabel.F3)){
			myUtil.makeDir(myUtil.setPath(this.destPath, UILabel.F3));
			myUtil.makeDir(myUtil.setPath(myUtil.setPath(this.destPath, UILabel.F3),"rolls"));
			genProc00Obj.genProc00F3(this.destPath);
			genProc01Obj.genProc01F3(this.destPath);
			genProcMatObj.genProcMatF3(this.destPath);
			cpObj.copyF3(this.destPath);
		}else if(this.StandType.equals(UILabel.F4)){
			myUtil.makeDir(myUtil.setPath(this.destPath, UILabel.F4));
			myUtil.makeDir(myUtil.setPath(myUtil.setPath(this.destPath, UILabel.F4),"rolls"));
			genProc00Obj.genProc00F4(this.destPath);
			genProc01Obj.genProc01F4(this.destPath);
			genProcMatObj.genProcMatF4(this.destPath);
			cpObj.copyF4(this.destPath);
		}else if(this.StandType.equals(UILabel.F5)){
			myUtil.makeDir(myUtil.setPath(this.destPath, UILabel.F5));
			myUtil.makeDir(myUtil.setPath(myUtil.setPath(this.destPath, UILabel.F5),"rolls"));
			genProc00Obj.genProc00F5(this.destPath);
			genProc01Obj.genProc01F5(this.destPath);
			genProcMatObj.genProcMatF5(this.destPath);
			cpObj.copyF5(this.destPath);
		}else if(this.StandType.equals(UILabel.F6)){
			myUtil.makeDir(myUtil.setPath(this.destPath, UILabel.F6));
			myUtil.makeDir(myUtil.setPath(myUtil.setPath(this.destPath, UILabel.F6),"rolls"));
			genProc00Obj.genProc00F6(this.destPath);
			genProc01Obj.genProc01F6(this.destPath);
			genProcMatObj.genProcMatF6(this.destPath);
			cpObj.copyF6(this.destPath);
		}else if(this.StandType.equals(UILabel.F7)){
			myUtil.makeDir(myUtil.setPath(this.destPath, UILabel.F7));
			myUtil.makeDir(myUtil.setPath(myUtil.setPath(this.destPath, UILabel.F7),"rolls"));
			genProc00Obj.genProc00F7(this.destPath);
			genProc01Obj.genProc01F7(this.destPath);
			genProcMatObj.genProcMatF7(this.destPath);
			cpObj.copyF7(this.destPath);
		}
		
	}

}
