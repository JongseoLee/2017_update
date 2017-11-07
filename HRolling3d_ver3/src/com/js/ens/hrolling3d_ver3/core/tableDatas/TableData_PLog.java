package com.js.ens.hrolling3d_ver3.core.tableDatas;

import java.util.ArrayList;

import com.js.ens.hrolling3d_ver3.core.InitValue;
import com.js.ens.hrolling3d_ver3.core.Mediator;
import com.js.ens.hrolling3d_ver3.core.UILabel;

public class TableData_PLog {
	
	private String STAND = "";
	private String BUR_TDIA = "";
	private String BUR_BDIA = "";
	private String WR_TDIA = "";
	private String WR_BDIA = "";
	private String WR_ICRN = "";
	private String ENTRY_THK = "";
	private String EXIT_THK = "";
	private String PAS_LINE = "";
	private String ROL_GAP = "";
	private String STP_WID = "";
	private String STP_LEN = "";
	private String ENTRY_TEMP = "";
	private String EXIT_TEMP = "";
	private String FRCE = "";
	private String TORQ = "";
	private String SPEED = "";
	private String BEND = "";
	private String P_CROSS = "";
	private String TENS = "";
	private String ROL_TIM = "";
	private String IDL_TIM = "";
	private String BUR_WEAR = "";
	private String WR_WEAR = "";
	private String WR_THRM = "";
	
	//group1
	private String wr_len = "";
	private String wr_div_angle = "";
	
	//group2
	private String bur_len = "";
	private String bur_div_angle = "";
	
	//group3
	private String p_in = "";
	private String pl_m = "";
	private String t_div = "";
	
	//group4
	private String f_r2p = "";
	private String f_r2r = "";
	private String speed_different_ratio_top_roll = "";
	private String speed_different_ratio_bottom_roll = "";
	private String wr_trot = "";
	private String wr_brot = "";
	private String bur_trot = "";
	private String bur_brot = "";
	
	//group5
	private String YM_Constant = "";
	private String YM_Table = "";
	private String YM_Value_T = "";
	private String YM_Value = "";
	private String FS_Constant = "";
	private String FS_Table = "";
	private String FS_Value = "";
	private String YS_Value = "";
	private String TS_Value = "";
	private String E_Value = "";
	private String TEC_Constant = "";
	private String TEC_Table = "";
	private String TEC_Value_T = "";
	private String TEC_Value = "";
	private String PR_Constant = "";
	private String PR_Table = "";
	private String PR_Value_T = "";
	private String PR_Value = "";
	private String MD_Value = "";
	
	//group6
	private String lcase_time = "";
	private String lcase_inc = "";
	private String post_inc = "";
	private String lcase_dt = "";
	private String ltime_scale = "";
	private String ParallelDDM = "";
	private String Domain = "";
	private String ParallelMultiThread = "";
	private String Thread = "";
	
	public TableData_PLog() {
		// TODO Auto-generated constructor stub
		
	}

	public String getSTAND() {
		return STAND;
	}

	public void setSTAND(String sTAND) {
		STAND = sTAND;
		this.setOtherValue(STAND);
	}
	private void setOtherValue(String stand){
		switch (stand){
		case "F1":
			this.setOtherF1();
			break;
		case "F2":
			this.setOtherF2();
			break;
		case "F3":
			this.setOtherF3();
			break;
		case "F4":
			this.setOtherF4();
			break;
		case "F5":
			this.setOtherF5();
			break;
		case "F6":
			this.setOtherF6();
			break;
		case "F7":
			this.setOtherF7();
			break;
		}
	}
	
	private void setOtherF1(){
		//group1
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		wr_len = obj.getInitValue(InitValue.wr_len_F1);
		wr_div_angle = obj.getInitValue(InitValue.wr_div_angle_F1);
		
		//group2
		bur_len = obj.getInitValue(InitValue.bur_len_F1);
		bur_div_angle = obj.getInitValue(InitValue.bur_div_angle_F1);
		
		//group3
		p_in = obj.getInitValue(InitValue.p_in_F1);
		pl_m = obj.getInitValue(InitValue.pl_m_F1);
		t_div = obj.getInitValue(InitValue.t_div_F1);
		
		//group4
		f_r2p = obj.getInitValue(InitValue.f_r2p_F1);
		f_r2r = obj.getInitValue(InitValue.f_r2r_F1);
		speed_different_ratio_top_roll = obj.getInitValue(InitValue.vel_rate_top_F1);
		speed_different_ratio_bottom_roll = obj.getInitValue(InitValue.vel_rate_top_F1);
		wr_trot = obj.getInitValue(InitValue.wr_trot_F1);
		wr_brot = obj.getInitValue(InitValue.wr_brot_F1);
		bur_trot = obj.getInitValue(InitValue.bur_trot_F1);
		bur_brot = obj.getInitValue(InitValue.bur_brot_F1);
		
		//group5
		YM_Constant = obj.getInitValue(InitValue.YM_Constant_F1);
		YM_Table = obj.getInitValue(InitValue.YM_Table_F1);
		YM_Value = obj.getInitValue(InitValue.YM_Value_F1);
		YM_Value_T = obj.getYM_tableValuePath();
		FS_Constant = obj.getInitValue(InitValue.FS_Constant_F1);
		FS_Table = obj.getInitValue(InitValue.FS_Table_F1);
		FS_Value = obj.getFS_tableValuePath();
		YS_Value = obj.getInitValue(InitValue.YS_Value_F1);
		TS_Value = obj.getInitValue(InitValue.TS_Value_F1);
		E_Value = obj.getInitValue(InitValue.E_Value_F1);
		//System.out.println(YS_Value+"|"+TS_Value+"|"+E_Value);
		TEC_Constant = obj.getInitValue(InitValue.TEC_Constant_F1);
		TEC_Table = obj.getInitValue(InitValue.TEC_Table_F1);
		TEC_Value = obj.getInitValue(InitValue.TEC_Value_F1);
		TEC_Value_T = obj.getTEC_tableValuePath();
		PR_Constant = obj.getInitValue(InitValue.PR_Constant_F1);
		PR_Table = obj.getInitValue(InitValue.PR_Table_F1);
		PR_Value = obj.getInitValue(InitValue.PR_Value_F1);
		PR_Value_T = obj.getPR_tableValuePath();
		MD_Value = obj.getInitValue(InitValue.MD_Value_F1);
		
		//group6
		lcase_time = obj.getInitValue(InitValue.lcase_time_F1);
		lcase_inc = obj.getInitValue(InitValue.lcase_inc_F1);
		post_inc = obj.getInitValue(InitValue.post_inc_F1);
		lcase_dt = obj.getInitValue(InitValue.lcase_dt_F1);
		ltime_scale = obj.getInitValue(InitValue.ltime_scale_F1);
		ParallelDDM = obj.getInitValue(InitValue.ParallelDDM_F1);
		Domain = obj.getInitValue(InitValue.Domain_F1);
		ParallelMultiThread = obj.getInitValue(InitValue.ParallelMultiThread_F1);
		Thread = obj.getInitValue(InitValue.Thread_F1);
	}
	
	private void setOtherF2(){
		//group1
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		wr_len = obj.getInitValue(InitValue.wr_len_F2);
		wr_div_angle = obj.getInitValue(InitValue.wr_div_angle_F2);
		
		//group2
		bur_len = obj.getInitValue(InitValue.bur_len_F2);
		bur_div_angle = obj.getInitValue(InitValue.bur_div_angle_F2);
		
		//group3
		p_in = obj.getInitValue(InitValue.p_in_F2);
		pl_m = obj.getInitValue(InitValue.pl_m_F2);
		t_div = obj.getInitValue(InitValue.t_div_F2);
		
		//group4
		f_r2p = obj.getInitValue(InitValue.f_r2p_F2);
		f_r2r = obj.getInitValue(InitValue.f_r2r_F2);
		speed_different_ratio_top_roll = obj.getInitValue(InitValue.vel_rate_top_F2);
		speed_different_ratio_bottom_roll = obj.getInitValue(InitValue.vel_rate_bottom_F2);
		wr_trot = obj.getInitValue(InitValue.wr_trot_F2);
		wr_brot = obj.getInitValue(InitValue.wr_brot_F2);
		bur_trot = obj.getInitValue(InitValue.bur_trot_F2);
		bur_brot = obj.getInitValue(InitValue.bur_brot_F2);
		
		//group5
		YM_Constant = obj.getInitValue(InitValue.YM_Constant_F2);
		YM_Table = obj.getInitValue(InitValue.YM_Table_F2);
		YM_Value_T = obj.getYM_tableValuePath();
		YM_Value = obj.getInitValue(InitValue.YM_Value_F2);
		FS_Constant = obj.getInitValue(InitValue.FS_Constant_F2);
		FS_Table = obj.getInitValue(InitValue.FS_Table_F2);
		FS_Value = obj.getFS_tableValuePath();
		YS_Value = obj.getInitValue(InitValue.YS_Value_F2);
		TS_Value = obj.getInitValue(InitValue.TS_Value_F2);
		E_Value = obj.getInitValue(InitValue.E_Value_F2);
		TEC_Constant = obj.getInitValue(InitValue.TEC_Constant_F2);
		TEC_Table = obj.getInitValue(InitValue.TEC_Table_F2);
		TEC_Value_T = obj.getTEC_tableValuePath();
		TEC_Value = obj.getInitValue(InitValue.TEC_Value_F2);
		PR_Constant = obj.getInitValue(InitValue.PR_Constant_F2);
		PR_Table = obj.getInitValue(InitValue.PR_Table_F2);
		PR_Value_T = obj.getPR_tableValuePath();
		PR_Value = obj.getInitValue(InitValue.PR_Value_F2);
		MD_Value = obj.getInitValue(InitValue.MD_Value_F2);
		//group6
		lcase_time = obj.getInitValue(InitValue.lcase_time_F2);
		lcase_inc = obj.getInitValue(InitValue.lcase_inc_F2);
		post_inc = obj.getInitValue(InitValue.post_inc_F2);
		lcase_dt = obj.getInitValue(InitValue.lcase_dt_F2);
		ltime_scale = obj.getInitValue(InitValue.ltime_scale_F2);
		ParallelDDM = obj.getInitValue(InitValue.ParallelDDM_F2);
		Domain = obj.getInitValue(InitValue.Domain_F2);
		ParallelMultiThread = obj.getInitValue(InitValue.ParallelMultiThread_F2);
		Thread = obj.getInitValue(InitValue.Thread_F2);
	}
	
	private void setOtherF3(){
		//group1
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		wr_len = obj.getInitValue(InitValue.wr_len_F3);
		wr_div_angle = obj.getInitValue(InitValue.wr_div_angle_F3);
		
		//group2
		bur_len = obj.getInitValue(InitValue.bur_len_F3);
		bur_div_angle = obj.getInitValue(InitValue.bur_div_angle_F3);
		
		//group3
		p_in = obj.getInitValue(InitValue.p_in_F3);
		pl_m = obj.getInitValue(InitValue.pl_m_F3);
		t_div = obj.getInitValue(InitValue.t_div_F3);
		
		//group4
		f_r2p = obj.getInitValue(InitValue.f_r2p_F3);
		f_r2r = obj.getInitValue(InitValue.f_r2r_F3);
		speed_different_ratio_top_roll = obj.getInitValue(InitValue.vel_rate_top_F3);
		speed_different_ratio_bottom_roll = obj.getInitValue(InitValue.vel_rate_bottom_F3);
		wr_trot = obj.getInitValue(InitValue.wr_trot_F3);
		wr_brot = obj.getInitValue(InitValue.wr_brot_F3);
		bur_trot = obj.getInitValue(InitValue.bur_trot_F3);
		bur_brot = obj.getInitValue(InitValue.bur_brot_F3);
		
		//group5
		YM_Constant = obj.getInitValue(InitValue.YM_Constant_F3);
		YM_Table = obj.getInitValue(InitValue.YM_Table_F3);
		YM_Value_T = obj.getYM_tableValuePath();
		YM_Value = obj.getInitValue(InitValue.YM_Value_F3);
		FS_Constant = obj.getInitValue(InitValue.FS_Constant_F3);
		FS_Table = obj.getInitValue(InitValue.FS_Table_F3);
		FS_Value = obj.getFS_tableValuePath();
		YS_Value = obj.getInitValue(InitValue.YS_Value_F3);
		TS_Value = obj.getInitValue(InitValue.TS_Value_F3);
		E_Value = obj.getInitValue(InitValue.E_Value_F3);
		TEC_Constant = obj.getInitValue(InitValue.TEC_Constant_F3);
		TEC_Table = obj.getInitValue(InitValue.TEC_Table_F3);
		TEC_Value_T = obj.getTEC_tableValuePath();
		TEC_Value = obj.getInitValue(InitValue.TEC_Value_F3);
		PR_Constant = obj.getInitValue(InitValue.PR_Constant_F3);
		PR_Table = obj.getInitValue(InitValue.PR_Table_F3);
		PR_Value_T = obj.getPR_tableValuePath();
		PR_Value = obj.getInitValue(InitValue.PR_Value_F3);
		MD_Value = obj.getInitValue(InitValue.MD_Value_F3);
		
		//group6
		lcase_time = obj.getInitValue(InitValue.lcase_time_F3);
		lcase_inc = obj.getInitValue(InitValue.lcase_inc_F3);
		post_inc = obj.getInitValue(InitValue.post_inc_F3);
		lcase_dt = obj.getInitValue(InitValue.lcase_dt_F3);
		ltime_scale = obj.getInitValue(InitValue.ltime_scale_F3);
		ParallelDDM = obj.getInitValue(InitValue.ParallelDDM_F3);
		Domain = obj.getInitValue(InitValue.Domain_F3);
		ParallelMultiThread = obj.getInitValue(InitValue.ParallelMultiThread_F3);
		Thread = obj.getInitValue(InitValue.Thread_F3);
	}
	
	private void setOtherF4(){
		//group1
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		wr_len = obj.getInitValue(InitValue.wr_len_F4);
		wr_div_angle = obj.getInitValue(InitValue.wr_div_angle_F4);
		
		//group2
		bur_len = obj.getInitValue(InitValue.bur_len_F4);
		bur_div_angle = obj.getInitValue(InitValue.bur_div_angle_F4);
		
		//group3
		p_in = obj.getInitValue(InitValue.p_in_F4);
		pl_m = obj.getInitValue(InitValue.pl_m_F4);
		t_div = obj.getInitValue(InitValue.t_div_F4);
		
		//group4
		f_r2p = obj.getInitValue(InitValue.f_r2p_F4);
		f_r2r = obj.getInitValue(InitValue.f_r2r_F4);
		speed_different_ratio_top_roll = obj.getInitValue(InitValue.vel_rate_top_F4);
		speed_different_ratio_bottom_roll = obj.getInitValue(InitValue.vel_rate_bottom_F4);
		wr_trot = obj.getInitValue(InitValue.wr_trot_F4);
		wr_brot = obj.getInitValue(InitValue.wr_brot_F4);
		bur_trot = obj.getInitValue(InitValue.bur_trot_F4);
		bur_brot = obj.getInitValue(InitValue.bur_brot_F4);
		
		//group5
		YM_Constant = obj.getInitValue(InitValue.YM_Constant_F4);
		YM_Table = obj.getInitValue(InitValue.YM_Table_F4);
		YM_Value_T = obj.getYM_tableValuePath();
		YM_Value = obj.getInitValue(InitValue.YM_Value_F4);
		FS_Constant = obj.getInitValue(InitValue.FS_Constant_F4);
		FS_Table = obj.getInitValue(InitValue.FS_Table_F4);
		FS_Value = obj.getFS_tableValuePath();
		YS_Value = obj.getInitValue(InitValue.YS_Value_F4);
		TS_Value = obj.getInitValue(InitValue.TS_Value_F4);
		E_Value = obj.getInitValue(InitValue.E_Value_F4);
		TEC_Constant = obj.getInitValue(InitValue.TEC_Constant_F4);
		TEC_Table = obj.getInitValue(InitValue.TEC_Table_F4);
		TEC_Value_T = obj.getTEC_tableValuePath();
		TEC_Value = obj.getInitValue(InitValue.TEC_Value_F4);
		PR_Constant = obj.getInitValue(InitValue.PR_Constant_F4);
		PR_Table = obj.getInitValue(InitValue.PR_Table_F4);
		PR_Value_T = obj.getPR_tableValuePath();
		PR_Value = obj.getInitValue(InitValue.PR_Value_F4);
		MD_Value = obj.getInitValue(InitValue.MD_Value_F4);
		
		//group6
		lcase_time = obj.getInitValue(InitValue.lcase_time_F4);
		lcase_inc = obj.getInitValue(InitValue.lcase_inc_F4);
		post_inc = obj.getInitValue(InitValue.post_inc_F4);
		lcase_dt = obj.getInitValue(InitValue.lcase_dt_F4);
		ltime_scale = obj.getInitValue(InitValue.ltime_scale_F4);
		ParallelDDM = obj.getInitValue(InitValue.ParallelDDM_F4);
		Domain = obj.getInitValue(InitValue.Domain_F4);
		ParallelMultiThread = obj.getInitValue(InitValue.ParallelMultiThread_F4);
		Thread = obj.getInitValue(InitValue.Thread_F4);
	}

	private void setOtherF5(){
		//group1
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		wr_len = obj.getInitValue(InitValue.wr_len_F5);
		wr_div_angle = obj.getInitValue(InitValue.wr_div_angle_F5);
		
		//group2
		bur_len = obj.getInitValue(InitValue.bur_len_F5);
		bur_div_angle = obj.getInitValue(InitValue.bur_div_angle_F5);
		
		//group3
		p_in = obj.getInitValue(InitValue.p_in_F5);
		pl_m = obj.getInitValue(InitValue.pl_m_F5);
		t_div = obj.getInitValue(InitValue.t_div_F5);
		
		//group4
		f_r2p = obj.getInitValue(InitValue.f_r2p_F5);
		f_r2r = obj.getInitValue(InitValue.f_r2r_F5);
		speed_different_ratio_top_roll = obj.getInitValue(InitValue.vel_rate_top_F5);
		speed_different_ratio_bottom_roll = obj.getInitValue(InitValue.vel_rate_bottom_F5);
		wr_trot = obj.getInitValue(InitValue.wr_trot_F5);
		wr_brot = obj.getInitValue(InitValue.wr_brot_F5);
		bur_trot = obj.getInitValue(InitValue.bur_trot_F5);
		bur_brot = obj.getInitValue(InitValue.bur_brot_F5);
		
		//group5
		YM_Constant = obj.getInitValue(InitValue.YM_Constant_F5);
		YM_Table = obj.getInitValue(InitValue.YM_Table_F5);
		YM_Value_T = obj.getYM_tableValuePath();
		YM_Value = obj.getInitValue(InitValue.YM_Value_F5);
		FS_Constant = obj.getInitValue(InitValue.FS_Constant_F5);
		FS_Table = obj.getInitValue(InitValue.FS_Table_F5);
		FS_Value = obj.getFS_tableValuePath();
		YS_Value = obj.getInitValue(InitValue.YS_Value_F5);
		TS_Value = obj.getInitValue(InitValue.TS_Value_F5);
		E_Value = obj.getInitValue(InitValue.E_Value_F5);
		TEC_Constant = obj.getInitValue(InitValue.TEC_Constant_F5);
		TEC_Table = obj.getInitValue(InitValue.TEC_Table_F5);
		TEC_Value_T = obj.getTEC_tableValuePath();
		TEC_Value = obj.getInitValue(InitValue.TEC_Value_F5);
		PR_Constant = obj.getInitValue(InitValue.PR_Constant_F5);
		PR_Table = obj.getInitValue(InitValue.PR_Table_F5);
		PR_Value_T = obj.getPR_tableValuePath();
		PR_Value = obj.getInitValue(InitValue.PR_Value_F5);
		MD_Value = obj.getInitValue(InitValue.MD_Value_F5);
		
		//group6
		lcase_time = obj.getInitValue(InitValue.lcase_time_F5);
		lcase_inc = obj.getInitValue(InitValue.lcase_inc_F5);
		post_inc = obj.getInitValue(InitValue.post_inc_F5);
		lcase_dt = obj.getInitValue(InitValue.lcase_dt_F5);
		ltime_scale = obj.getInitValue(InitValue.ltime_scale_F5);
		ParallelDDM = obj.getInitValue(InitValue.ParallelDDM_F5);
		Domain = obj.getInitValue(InitValue.Domain_F5);
		ParallelMultiThread = obj.getInitValue(InitValue.ParallelMultiThread_F5);
		Thread = obj.getInitValue(InitValue.Thread_F5);
	}
	
	private void setOtherF6(){
		//group1
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		wr_len = obj.getInitValue(InitValue.wr_len_F6);
		wr_div_angle = obj.getInitValue(InitValue.wr_div_angle_F6);
		
		//group2
		bur_len = obj.getInitValue(InitValue.bur_len_F6);
		bur_div_angle = obj.getInitValue(InitValue.bur_div_angle_F6);
		
		//group3
		p_in = obj.getInitValue(InitValue.p_in_F6);
		pl_m = obj.getInitValue(InitValue.pl_m_F6);
		t_div = obj.getInitValue(InitValue.t_div_F6);
		
		//group4
		f_r2p = obj.getInitValue(InitValue.f_r2p_F6);
		f_r2r = obj.getInitValue(InitValue.f_r2r_F6);
		speed_different_ratio_top_roll = obj.getInitValue(InitValue.vel_rate_top_F6);
		speed_different_ratio_bottom_roll = obj.getInitValue(InitValue.vel_rate_bottom_F6);
		wr_trot = obj.getInitValue(InitValue.wr_trot_F6);
		wr_brot = obj.getInitValue(InitValue.wr_brot_F6);
		bur_trot = obj.getInitValue(InitValue.bur_trot_F6);
		bur_brot = obj.getInitValue(InitValue.bur_brot_F6);
		
		//group5
		YM_Constant = obj.getInitValue(InitValue.YM_Constant_F6);
		YM_Table = obj.getInitValue(InitValue.YM_Table_F6);
		YM_Value_T = obj.getYM_tableValuePath();
		YM_Value = obj.getInitValue(InitValue.YM_Value_F6);
		FS_Constant = obj.getInitValue(InitValue.FS_Constant_F6);
		FS_Table = obj.getInitValue(InitValue.FS_Table_F6);
		FS_Value = obj.getFS_tableValuePath();
		YS_Value = obj.getInitValue(InitValue.YS_Value_F6);
		TS_Value = obj.getInitValue(InitValue.TS_Value_F6);
		E_Value = obj.getInitValue(InitValue.E_Value_F6);
		TEC_Constant = obj.getInitValue(InitValue.TEC_Constant_F6);
		TEC_Table = obj.getInitValue(InitValue.TEC_Table_F6);
		TEC_Value_T = obj.getTEC_tableValuePath();
		TEC_Value = obj.getInitValue(InitValue.TEC_Value_F6);
		PR_Constant = obj.getInitValue(InitValue.PR_Constant_F6);
		PR_Table = obj.getInitValue(InitValue.PR_Table_F6);
		PR_Value_T = obj.getPR_tableValuePath();
		PR_Value = obj.getInitValue(InitValue.PR_Value_F6);
		MD_Value = obj.getInitValue(InitValue.MD_Value_F6);
		
		//group6
		lcase_time = obj.getInitValue(InitValue.lcase_time_F6);
		lcase_inc = obj.getInitValue(InitValue.lcase_inc_F6);
		post_inc = obj.getInitValue(InitValue.post_inc_F6);
		lcase_dt = obj.getInitValue(InitValue.lcase_dt_F6);
		ltime_scale = obj.getInitValue(InitValue.ltime_scale_F6);
		ParallelDDM = obj.getInitValue(InitValue.ParallelDDM_F6);
		Domain = obj.getInitValue(InitValue.Domain_F6);
		ParallelMultiThread = obj.getInitValue(InitValue.ParallelMultiThread_F6);
		Thread = obj.getInitValue(InitValue.Thread_F6);
	}
	
	private void setOtherF7(){
		//group1
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		wr_len = obj.getInitValue(InitValue.wr_len_F7);
		wr_div_angle = obj.getInitValue(InitValue.wr_div_angle_F7);
		
		//group2
		bur_len = obj.getInitValue(InitValue.bur_len_F7);
		bur_div_angle = obj.getInitValue(InitValue.bur_div_angle_F7);
		
		//group3
		p_in = obj.getInitValue(InitValue.p_in_F7);
		pl_m = obj.getInitValue(InitValue.pl_m_F7);
		t_div = obj.getInitValue(InitValue.t_div_F7);
		
		//group4
		f_r2p = obj.getInitValue(InitValue.f_r2p_F7);
		f_r2r = obj.getInitValue(InitValue.f_r2r_F7);
		speed_different_ratio_top_roll = obj.getInitValue(InitValue.vel_rate_top_F7);
		speed_different_ratio_bottom_roll = obj.getInitValue(InitValue.vel_rate_bottom_F7);
		wr_trot = obj.getInitValue(InitValue.wr_trot_F7);
		wr_brot = obj.getInitValue(InitValue.wr_brot_F7);
		bur_trot = obj.getInitValue(InitValue.bur_trot_F7);
		bur_brot = obj.getInitValue(InitValue.bur_brot_F7);
		
		//group5
		YM_Constant = obj.getInitValue(InitValue.YM_Constant_F7);
		YM_Table = obj.getInitValue(InitValue.YM_Table_F7);
		YM_Value_T = obj.getYM_tableValuePath();
		YM_Value = obj.getInitValue(InitValue.YM_Value_F7);
		FS_Constant = obj.getInitValue(InitValue.FS_Constant_F7);
		FS_Table = obj.getInitValue(InitValue.FS_Table_F7);
		FS_Value = obj.getFS_tableValuePath();
		YS_Value = obj.getInitValue(InitValue.YS_Value_F7);
		TS_Value = obj.getInitValue(InitValue.TS_Value_F7);
		E_Value = obj.getInitValue(InitValue.E_Value_F7);
		TEC_Constant = obj.getInitValue(InitValue.TEC_Constant_F7);
		TEC_Table = obj.getInitValue(InitValue.TEC_Table_F7);
		TEC_Value_T = obj.getTEC_tableValuePath();
		TEC_Value = obj.getInitValue(InitValue.TEC_Value_F7);
		PR_Constant = obj.getInitValue(InitValue.PR_Constant_F7);
		PR_Table = obj.getInitValue(InitValue.PR_Table_F7);
		PR_Value_T = obj.getPR_tableValuePath();
		PR_Value = obj.getInitValue(InitValue.PR_Value_F7);
		MD_Value = obj.getInitValue(InitValue.MD_Value_F7);
		
		//group6
		lcase_time = obj.getInitValue(InitValue.lcase_time_F7);
		lcase_inc = obj.getInitValue(InitValue.lcase_inc_F7);
		post_inc = obj.getInitValue(InitValue.post_inc_F7);
		lcase_dt = obj.getInitValue(InitValue.lcase_dt_F7);
		ltime_scale = obj.getInitValue(InitValue.ltime_scale_F7);
		ParallelDDM = obj.getInitValue(InitValue.ParallelDDM_F7);
		Domain = obj.getInitValue(InitValue.Domain_F7);
		ParallelMultiThread = obj.getInitValue(InitValue.ParallelMultiThread_F7);
		Thread = obj.getInitValue(InitValue.Thread_F7);
	}
	
	public String getBUR_TDIA() {
		return BUR_TDIA;
	}

	public void setBUR_TDIA(String bUR_TDIA) {
		BUR_TDIA = bUR_TDIA;
	}

	public String getBUR_BDIA() {
		return BUR_BDIA;
	}

	public void setBUR_BDIA(String bUR_BDIA) {
		BUR_BDIA = bUR_BDIA;
	}

	public String getWR_TDIA() {
		return WR_TDIA;
	}

	public void setWR_TDIA(String wR_TDIA) {
		WR_TDIA = wR_TDIA;
	}

	public String getWR_BDIA() {
		return WR_BDIA;
	}

	public void setWR_BDIA(String wR_BDIA) {
		WR_BDIA = wR_BDIA;
	}

	public String getWR_ICRN() {
		return WR_ICRN;
	}

	public void setWR_ICRN(String wR_ICRN) {
		WR_ICRN = wR_ICRN;
	}

	public String getENTRY_THK() {
		return ENTRY_THK;
	}

	public void setENTRY_THK(String eNTRY_THK) {
		ENTRY_THK = eNTRY_THK;
	}

	public String getEXIT_THK() {
		return EXIT_THK;
	}

	public void setEXIT_THK(String eXIT_THK) {
		EXIT_THK = eXIT_THK;
	}

	public String getPAS_LINE() {
		return PAS_LINE;
	}

	public void setPAS_LINE(String pAS_LINE) {
		PAS_LINE = pAS_LINE;
	}

	public String getROL_GAP() {
		return ROL_GAP;
	}

	public void setROL_GAP(String rOL_GAP) {
		ROL_GAP = rOL_GAP;
	}

	public String getSTP_WID() {
		return STP_WID;
	}

	public void setSTP_WID(String sTP_WID) {
		STP_WID = sTP_WID;
	}

	public String getSTP_LEN() {
		return STP_LEN;
	}

	public void setSTP_LEN(String sTP_LEN) {
		STP_LEN = sTP_LEN;
	}

	public String getENTRY_TEMP() {
		return ENTRY_TEMP;
	}

	public void setENTRY_TEMP(String eNTRY_TEMP) {
		ENTRY_TEMP = eNTRY_TEMP;
	}

	public String getEXIT_TEMP() {
		return EXIT_TEMP;
	}

	public void setEXIT_TEMP(String eXIT_TEMP) {
		EXIT_TEMP = eXIT_TEMP;
	}

	public String getFRCE() {
		return FRCE;
	}

	public void setFRCE(String fRCE) {
		FRCE = fRCE;
	}

	public String getTORQ() {
		return TORQ;
	}

	public void setTORQ(String tORQ) {
		TORQ = tORQ;
	}

	public String getSPEED() {
		return SPEED;
	}

	public void setSPEED(String sPEED) {
		SPEED = sPEED;
	}

	public String getBEND() {
		return BEND;
	}

	public void setBEND(String bEND) {
		BEND = bEND;
	}

	public String getP_CROSS() {
		return P_CROSS;
	}

	public void setP_CROSS(String p_CROSS) {
		P_CROSS = p_CROSS;
	}

	public String getTENS() {
		return TENS;
	}

	public void setTENS(String tENS) {
		TENS = tENS;
	}

	public String getROL_TIM() {
		return ROL_TIM;
	}

	public void setROL_TIM(String rOL_TIM) {
		ROL_TIM = rOL_TIM;
	}

	public String getIDL_TIM() {
		return IDL_TIM;
	}

	public void setIDL_TIM(String iDL_TIM) {
		IDL_TIM = iDL_TIM;
	}

	public String getBUR_WEAR() {
		return BUR_WEAR;
	}

	public void setBUR_WEAR(String bUR_WEAR) {
		BUR_WEAR = bUR_WEAR;
	}

	public String getWR_WEAR() {
		return WR_WEAR;
	}

	public void setWR_WEAR(String wR_WEAR) {
		WR_WEAR = wR_WEAR;
	}

	public String getWR_THRM() {
		return WR_THRM;
	}

	public void setWR_THRM(String wR_THRM) {
		WR_THRM = wR_THRM;
	}
	
	public String getWr_len() {
		return wr_len;
	}

	public void setWr_len(String wr_len) {
		this.wr_len = wr_len;
	}

	public String getWr_div_angle() {
		return wr_div_angle;
	}

	public void setWr_div_angle(String wr_div_angle) {
		this.wr_div_angle = wr_div_angle;
	}

	public String getBur_len() {
		return bur_len;
	}

	public void setBur_len(String bur_len) {
		this.bur_len = bur_len;
	}

	public String getBur_div_angle() {
		return bur_div_angle;
	}

	public void setBur_div_angle(String bur_div_angle) {
		this.bur_div_angle = bur_div_angle;
	}

	public String getP_in() {
		return p_in;
	}

	public void setP_in(String p_in) {
		this.p_in = p_in;
	}

	public String getPl_m() {
		return pl_m;
	}

	public void setPl_m(String pl_m) {
		this.pl_m = pl_m;
	}

	public String getT_div() {
		return t_div;
	}

	public void setT_div(String t_div) {
		this.t_div = t_div;
	}

	public String getF_r2p() {
		return f_r2p;
	}

	public void setF_r2p(String f_r2p) {
		this.f_r2p = f_r2p;
	}

	public String getF_r2r() {
		return f_r2r;
	}

	public void setF_r2r(String f_r2r) {
		this.f_r2r = f_r2r;
	}

	public String getYM_Value() {
		return YM_Value;
	}

	public void setYM_Value(String yM_Value) {
		YM_Value = yM_Value;
	}

	public String getTEC_Value() {
		return TEC_Value;
	}

	public void setTEC_Value(String tEC_Value) {
		TEC_Value = tEC_Value;
	}

	public String getPR_Value() {
		return PR_Value;
	}

	public void setPR_Value(String pR_Value) {
		PR_Value = pR_Value;
	}

	public String getMD_Value() {
		return MD_Value;
	}

	public void setMD_Value(String mD_Value) {
		MD_Value = mD_Value;
	}

	public String getLcase_time() {
		return lcase_time;
	}

	public void setLcase_time(String lcase_time) {
		this.lcase_time = lcase_time;
	}

	public String getlcase_inc() {
		return lcase_inc;
	}

	public void setlcase_inc(String lcase_inc) {
		this.lcase_inc = lcase_inc;
	}

	public String getPost_inc() {
		return post_inc;
	}

	public void setPost_inc(String post_inc) {
		this.post_inc = post_inc;
	}

	public String getlcase_dt() {
		return lcase_dt;
	}

	public void setlcase_dt(String lcase_dt) {
		this.lcase_dt = lcase_dt;
	}
	
	public String getLtime_scale() {
		return ltime_scale;
	}

	public void setLtime_scale(String ltime_scale) {
		this.ltime_scale = ltime_scale;
	}

	public String getParallelDDM() {
		return ParallelDDM;
	}

	public void setParallelDDM(String parallelDDM) {
		ParallelDDM = parallelDDM;
	}

	public String getDomain() {
		return Domain;
	}

	public void setDomain(String domain) {
		Domain = domain;
	}

	public String getParallelMultiThread() {
		return ParallelMultiThread;
	}

	public void setParallelMultiThread(String parallelMultiThread) {
		ParallelMultiThread = parallelMultiThread;
	}

	public String getThread() {
		return Thread;
	}

	public void setThread(String thread) {
		Thread = thread;
	}

	public String getYM_Constant() {
		return YM_Constant;
	}

	public void setYM_Constant(String yM_Constant) {
		YM_Constant = yM_Constant;
	}

	public String getYM_Table() {
		return YM_Table;
	}

	public void setYM_Table(String yM_Table) {
		YM_Table = yM_Table;
	}

	public String getFS_Constant() {
		return FS_Constant;
	}

	public void setFS_Constant(String fS_Constant) {
		FS_Constant = fS_Constant;
	}

	public String getFS_Table() {
		return FS_Table;
	}

	public void setFS_Table(String fS_Table) {
		FS_Table = fS_Table;
	}

	public String getFS_Value() {
		return FS_Value;
	}

	public void setFS_Value(String fS_Value) {
		FS_Value = fS_Value;
	}

	public String getYS_Value() {
		return YS_Value;
	}

	public void setYS_Value(String yS_Value) {
		YS_Value = yS_Value;
	}

	public String getTS_Value() {
		return TS_Value;
	}

	public void setTS_Value(String tS_Value) {
		TS_Value = tS_Value;
	}

	public String getE_Value() {
		return E_Value;
	}

	public void setE_Value(String e_Value) {
		E_Value = e_Value;
	}

	public String getTEC_Constant() {
		return TEC_Constant;
	}

	public void setTEC_Constant(String tEC_Constant) {
		TEC_Constant = tEC_Constant;
	}

	public String getTEC_Table() {
		return TEC_Table;
	}

	public void setTEC_Table(String tEC_Table) {
		TEC_Table = tEC_Table;
	}

	public String getPR_Constant() {
		return PR_Constant;
	}

	public void setPR_Constant(String pR_Constant) {
		PR_Constant = pR_Constant;
	}

	public String getPR_Table() {
		return PR_Table;
	}

	public String getYM_Value_T() {
		return YM_Value_T;
	}

	public void setYM_Value_T(String yM_Value_T) {
		YM_Value_T = yM_Value_T;
	}

	public String getTEC_Value_T() {
		return TEC_Value_T;
	}

	public void setTEC_Value_T(String tEC_Value_T) {
		TEC_Value_T = tEC_Value_T;
	}

	public String getPR_Value_T() {
		return PR_Value_T;
	}

	public void setPR_Value_T(String pR_Value_T) {
		PR_Value_T = pR_Value_T;
	}

	public void setPR_Table(String pR_Table) {
		PR_Table = pR_Table;
	}

	public String getSpeed_different_ratio_top_roll() {
		return speed_different_ratio_top_roll;
	}

	public void setSpeed_different_ratio_top_roll(String speed_different_ratio_top_roll) {
		this.speed_different_ratio_top_roll = speed_different_ratio_top_roll;
	}
	
	public String getSpeed_different_ratio_bottom_roll() {
		return speed_different_ratio_bottom_roll;
	}

	public void setSpeed_different_ratio_bottom_roll(String speed_different_ratio_bottom_roll) {
		this.speed_different_ratio_bottom_roll = speed_different_ratio_bottom_roll;
	}

	public String getWr_trot() {
		return wr_trot;
	}

	public void setWr_trot(String wr_trot) {
		this.wr_trot = wr_trot;
	}

	public String getWr_brot() {
		return wr_brot;
	}

	public void setWr_brot(String wr_brot) {
		this.wr_brot = wr_brot;
	}

	public String getBur_trot() {
		return bur_trot;
	}

	public void setBur_trot(String bur_trot) {
		this.bur_trot = bur_trot;
	}

	public String getBur_brot() {
		return bur_brot;
	}

	public void setBur_brot(String bur_brot) {
		this.bur_brot = bur_brot;
	}

	public ArrayList<String> getDB(){
		ArrayList<String> DB = new ArrayList<String>();
		
		DB.add("#########################################");
		DB.add("#->STAND "+this.STAND);
		
		//group1 
		DB.add(UILabel.Top_WR_Diameter			+"_"+this.STAND+"="+	this.WR_TDIA);
		DB.add(UILabel.Bottom_WR_Diameter		+"_"+this.STAND+"="+	this.WR_BDIA);
		DB.add(UILabel.WR_Crown					+"_"+this.STAND+"="+	this.WR_ICRN);
		DB.add(UILabel.WR_Length				+"_"+this.STAND+"="+	this.wr_len);	
		DB.add(UILabel.WR_Mesh_Angle			+"_"+this.STAND+"="+	this.wr_div_angle);
		//group2
		DB.add(UILabel.Top_BUR_Diameter			+"_"+this.STAND+"="+	this.BUR_TDIA);
		DB.add(UILabel.Bottom_BUR_Diameter		+"_"+this.STAND+"="+	this.BUR_BDIA);
		DB.add(UILabel.BUR_Length				+"_"+this.STAND+"="+	this.bur_len);
		DB.add(UILabel.BUR_Mesh_Angle			+"_"+this.STAND+"="+	this.bur_div_angle);
		//group3
		DB.add(UILabel.Thickness				+"_"+this.STAND+"="+	this.ENTRY_THK);
		DB.add(UILabel.Width					+"_"+this.STAND+"="+	this.STP_WID);
		DB.add(UILabel.Length					+"_"+this.STAND+"="+	this.STP_LEN);
		DB.add(UILabel.Entry_Temperature		+"_"+this.STAND+"="+	this.ENTRY_TEMP);
		DB.add(UILabel.Exit_Temperature			+"_"+this.STAND+"="+	this.EXIT_TEMP);
		DB.add(UILabel.Initial_Position			+"_"+this.STAND+"="+	this.p_in);
		DB.add(UILabel.Mesh_Length				+"_"+this.STAND+"="+	this.pl_m);
		DB.add(UILabel.Thickness_Mesh_Divisions	+"_"+this.STAND+"="+	this.t_div);
		//group4
		DB.add(UILabel.Velocity					+"_"+this.STAND+"="+	this.SPEED);
		DB.add(UILabel.Roll_Gap					+"_"+this.STAND+"="+	this.ROL_GAP);
		DB.add(UILabel.Pass_Line				+"_"+this.STAND+"="+	this.PAS_LINE);
		DB.add(UILabel.Pair_Cross_Angle			+"_"+this.STAND+"="+	this.P_CROSS);
		DB.add(UILabel.Bender_Force				+"_"+this.STAND+"="+	this.BEND);
		DB.add(UILabel.Roll_Torque				+"_"+this.STAND+"="+	this.TORQ);
		DB.add(UILabel.Tension_Stress			+"_"+this.STAND+"="+	this.TENS);
		DB.add(UILabel.Roll_to_Plate_Frict_Coef	+"_"+this.STAND+"="+	this.f_r2p);
		DB.add(UILabel.Roll_to_Roll_Fric_Coef	+"_"+this.STAND+"="+	this.f_r2r);
		DB.add(UILabel.Speed_Different_Ratio_top_roll	+"_"+this.STAND+"="+	this.speed_different_ratio_top_roll);
		DB.add(UILabel.Speed_Different_Ratio_bottom_roll+"_"+this.STAND+"="+	this.speed_different_ratio_bottom_roll);
		DB.add(UILabel.Top_WR_Rot_Vel_RPM		+"_"+this.STAND+"="+	this.wr_trot);
		DB.add(UILabel.Bottom_WR_Rot_Vel_RPM	+"_"+this.STAND+"="+	this.wr_brot);
		DB.add(UILabel.Top_BUR_Rot_Vel_RPM		+"_"+this.STAND+"="+	this.bur_trot);
		DB.add(UILabel.Bottom_BUR_Rot_Vel_RPM	+"_"+this.STAND+"="+	this.bur_brot);
		//group5
		DB.add("YM_Constant"					+"_"+this.STAND+"="+	this.YM_Constant);
		DB.add("YM_Table"						+"_"+this.STAND+"="+	this.YM_Table);
		DB.add(UILabel.Youngs_Modulus			+"_"+this.STAND+"="+	this.YM_Value);
		DB.add(UILabel.Youngs_Modulus			+"_T_"+this.STAND+"="+	this.YM_Value_T);
		
		DB.add("FS_Constant"					+"_"+this.STAND+"="+	this.FS_Constant);
		DB.add("FS_Table"						+"_"+this.STAND+"="+	this.FS_Table);
		
		DB.add(UILabel.Flow_Stress				+"_"+this.STAND+"="+	this.FS_Value);
		DB.add(UILabel.Yield_Strength			+"_"+this.STAND+"="+	this.YS_Value);
		DB.add(UILabel.Tensile_Strength			+"_"+this.STAND+"="+	this.TS_Value);
		DB.add(UILabel.Elongation				+"_"+this.STAND+"="+	this.E_Value);		
		
		DB.add("TEC_Constant"					+"_"+this.STAND+"="+	this.TEC_Constant);
		DB.add("TEC_Table"						+"_"+this.STAND+"="+	this.TEC_Table);
		DB.add(UILabel.Thermal_Expansion_Coefficient	+"_"+this.STAND+"="+	this.TEC_Value);
		DB.add(UILabel.Thermal_Expansion_Coefficient	+"_T_"+this.STAND+"="+	this.TEC_Value_T);
		
		DB.add("PR_Constant"					+"_"+this.STAND+"="+	this.PR_Constant);
		DB.add("PR_Table"						+"_"+this.STAND+"="+	this.PR_Table);
		DB.add(UILabel.Poissons_Ratio			+"_"+this.STAND+"="+	this.PR_Value);
		DB.add(UILabel.Poissons_Ratio			+"_T_"+this.STAND+"="+	this.PR_Value_T);
		
		DB.add(UILabel.Mass_Density				+"_"+this.STAND+"="+	this.MD_Value);
		//group6
		DB.add(UILabel.Analysis_Time			+"_"+this.STAND+"="+	this.lcase_time);
		DB.add(UILabel.No_of_Inc				+"_"+this.STAND+"="+	this.lcase_inc);
		DB.add(UILabel.Post_Writing_frequency	+"_"+this.STAND+"="+	this.post_inc);
		DB.add(UILabel.Time_Increment			+"_"+this.STAND+"="+	this.lcase_dt);
		DB.add(UILabel.ltime_scale				+"_"+this.STAND+"="+	this.ltime_scale);
		DB.add(UILabel.Parallel_DDM				+"_"+this.STAND+"="+	this.ParallelDDM);
		DB.add(UILabel.Domain					+"_"+this.STAND+"="+	this.Domain);
		DB.add(UILabel.Parallel_Multi_Thread	+"_"+this.STAND+"="+	this.ParallelMultiThread);
		DB.add(UILabel.Thread					+"_"+this.STAND+"="+	this.Thread);		
		//PLog values
		DB.add("FRCE"							+"_"+this.STAND+"="+	this.FRCE);
		DB.add("EXIT THK"						+"_"+this.STAND+"="+	this.EXIT_THK);
		DB.add("ROL TIM"						+"_"+this.STAND+"="+	this.ROL_TIM);
		DB.add("IDL TIM"						+"_"+this.STAND+"="+	this.IDL_TIM);
		DB.add("BUR WEAR"						+"_"+this.STAND+"="+	this.BUR_WEAR);
		DB.add("WR WEAR"						+"_"+this.STAND+"="+	this.WR_WEAR);
		DB.add("WR THRM"						+"_"+this.STAND+"="+	this.WR_THRM);
		DB.add("#########################################");		
		
		return DB;
	}
	
	public void printAllData(){
		//System.out.println("<<Table - P Log>>");
		for(String line : this.getDB()){
			System.out.println(line);
		}
	}
}
