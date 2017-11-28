package com.js.ens.hrolling3d_ver3.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.jfree.util.Log;

import com.js.ens.hrolling3d_ver3.FolderTree;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableData_PLog;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableData_SlabPlateInfo;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableData_Variable;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_BEND;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_BUR_BDIA;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_BUR_TDIA;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_BUR_WEAR;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_ENTRY_TEMP;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_ENTRY_THK;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_EXIT_TEMP;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_EXIT_THK;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_FRCE;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_IDL_TIM;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_PAS_LINE;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_P_CROSS;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_ROL_GAP;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_ROL_TIM;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_SPEED;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_STP_LEN;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_STP_WID;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_TENS;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_TORQ;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_WR_BDIA;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_WR_ICRN;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_WR_TDIA;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_WR_THRM;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData_WR_WEAR;
import com.js.ens.hrolling3d_ver3.dialog.ApplyDlg;
import com.js.ens.hrolling3d_ver3.dialog.ExportDlg;
import com.js.ens.hrolling3d_ver3.dialog.NewDlg;
import com.js.ens.hrolling3d_ver3.dialog.OpenDlg;
import com.js.ens.hrolling3d_ver3.dialog.ResultDlg;
import com.js.ens.hrolling3d_ver3.dialog.SaveAsDlg;
import com.js.ens.hrolling3d_ver3.dialog.SaveDlg;
import com.js.ens.hrolling3d_ver3.dialog.MessageDlg;
import com.js.ens.hrolling3d_ver3.proc.ProcMaker;
import com.js.io.Reader;
import com.js.io.Writer;
import com.js.parser.ParserDefault;
import com.js.util.myUtil;

public class MainController {
	
	private Mediator med = Mediator.getInstance();
	
	private static MainController instance = new MainController();
	public static MainController getInstance(){
		return instance;
	}
	//--------------
	// common
	private ArrayList<Boolean> ApplyResult = new ArrayList<Boolean>(); 
	
	private String modelName = null;
	private String workspace = null;
	private String exportResult = null;
	//--------------
	// tab1
	private String PLogFilePath = null;
	private ArrayList<String> PLogFileDataList = null;
	
	private TableData_SlabPlateInfo TableDataSlabPlateInfoObj = null;
	private ArrayList<TableData_SlabPlateInfo> tableDataSlabPlateInfoList = null;
	//private Map<String,String> tableDataSlabPlateInfoMap = null;
	
	private TableData_Variable TableDataVariableObj = null;
	private ArrayList<TableData_Variable> tableDataVariableList = null;
	//private Map<String,String> tableDataVariableMap = null;
	
	// tableDataPLogList �� F1 ~ F7 ������ ������ 7�� obj�� �����
	//private TableData_common  tabeDataCommon = null;
	private ArrayList<TableData_PLog> tableDataPLogList = null;
	private ArrayList<String> otherValueList = null;
	//private Map<String,String> tableDataPLogMap; 
	private ArrayList<TableRowData> tableRowDataList = null;
	
	//--------------
	// tab2
	private String StandValue = "F1";
	//private ArrayList<TableData_PLog> F_ObjList = new ArrayList<TableData_PLog>();
	private String RunType = ""; // Multiful || Single
	public static String RunType_Multiful = "Multiful";
	public static String RunType_Single = "Single";
	private String ApplyType = ""; // Consequent || Individual
	public static String ApplyType_Consequent = "Consequent";
	public static String ApplyType_Individual = "Individual";
	private String sectionFilePath = "";
	private String dummySectionFilePath = "";
	
	
	public MainController() {
		// TODO Auto-generated constructor stub
	}
	//=====================================================================
	//	Button - Import P Log
	//
	public void ImportPLog(){
		
		FileDialog dlg = new FileDialog(med.getBtnImportPLog().getShell(),SWT.OPEN);
		dlg.setText("Select P Log.csv file.");
		String [] extNames = {"ALL(*.*)","CSV"};
		String [] extType = {"*.*","*.csv"}; 
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		String path = dlg.open();
		if(path == null){
			PLogFilePath = null;
			return;
		}else { 
			PLogFilePath = path;
			this.parsingPLogFile();
			this.calcAllData("full");
		}
	}
	
	private void parsingPLogFile(){
		//System.out.println("P Log Path : "+this.PLogFilePath);
		if(myUtil.checkPath(PLogFilePath)){
			
			Reader obj = new Reader(PLogFilePath);
			obj.running();
			PLogFileDataList = new ArrayList<String>();
			PLogFileDataList = obj.getFileDataList();
			
			TableColumnLabel tclObj = new TableColumnLabel();
			tclObj.readTableColumnLabelFile();
			
			initTableData_SlabPlateInfo(PLogFileDataList,tclObj);
			initTableData_Variable(PLogFileDataList,tclObj);
			initTableData_PLog(PLogFileDataList,tclObj);
			
			this.updateTableData();
		}else{
		}
	}
	
	private void parsingPLogFile(ArrayList<String> initDataList){
		TableColumnLabel tclObj = new TableColumnLabel();
		tclObj.readTableColumnLabelFile();
		
		
		initTableData_SlabPlateInfo(initDataList,tclObj);
		initTableData_Variable(initDataList,tclObj);
		initTableData_PLog(initDataList,tclObj);
		
		this.updateTableData();
		
	}

	private void initTableData_SlabPlateInfo(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		// line 0~1 => column line0, data line1
		int lineNumber = 0;
		String data = "";
		for(String line:fileDataList){
			ArrayList<String> tempList = ParserDefault.splitLineData(line, ",");
			if(tempList.size()!=0){
				if(tempList.get(0).toLowerCase().equals(tclObj.getTableColumnLabel(TableColumnLabel.CL1_0).toLowerCase())){
					data = fileDataList.get(lineNumber+1);
					break;
				}
			}
			lineNumber++;
		}
		
		TableData_SlabPlateInfo obj = new TableData_SlabPlateInfo();
		obj.setAllData(data);
		this.TableDataSlabPlateInfoObj = new TableData_SlabPlateInfo();
		this.TableDataSlabPlateInfoObj = obj;
		//this.TableDataSlabPlateInfoObj.printAllData();
		this.tableDataSlabPlateInfoList = new ArrayList<TableData_SlabPlateInfo>();
		this.tableDataSlabPlateInfoList.add(obj);
		
	}
	
	private void initTableData_Variable(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		// line 3~4 => column line3, data line4
		int lineNumber = 0;
		String data = "";
		for(String line:fileDataList){
			//System.out.println(lineNumber +" : "+line);
			ArrayList<String> tempList = ParserDefault.splitLineData(line, ",");
			if(tempList.size()!=0){
				if(tempList.get(0).toLowerCase().equals(tclObj.getTableColumnLabel(TableColumnLabel.CL2_0).toLowerCase())){
					data = fileDataList.get(lineNumber+1);
					break;
				}
			}
			lineNumber++;
		}
		
		TableData_Variable obj = new TableData_Variable();
		obj.setAllData(data);
		
		this.TableDataVariableObj = new TableData_Variable(); 
		this.TableDataVariableObj = obj;
		//this.TableDataVariableObj.printAllData();
		this.tableDataVariableList = new ArrayList<TableData_Variable>();
		this.tableDataVariableList.add(obj);
	}
	
	private void initTableData_PLog(ArrayList<String> fileDataList,TableColumnLabel tclObj){
		
		// line 7~31 => column 6, data line 7~30
		int lineNumber = 0;
		ArrayList<String> dataList = new ArrayList<String>();

		//CSV ���Ͽ��� PLog ���̺� ���� �� ã��
		for(String line:fileDataList){
			ArrayList<String> tempList = ParserDefault.splitLineData(line, ",");
			if(tempList.size()!=0){
				if(tempList.get(0).toLowerCase().equals(tclObj.getTableColumnLabel(TableColumnLabel.CL3_0).toLowerCase())){
					break;
				}
			}
			lineNumber++;
		}
		
		
		// dataList�� PLog�� Line ������ ����
		for(int i = lineNumber+1 ; i<lineNumber+25;i++){
			dataList.add(fileDataList.get(i));
		}
		this.createPLogObj(dataList);
		
		// UI�� ǥ���� ������ ������� �� ����
		this.createTableRowData();
		
	}
	
	//F1~F7 Obj �����
	private void createPLogObj(ArrayList<String> dataList){
		// dataList �� CSV �� PLog�����͸� �Ѿ��
		this.saveOtherValues();
		
		this.tableDataPLogList = new ArrayList<TableData_PLog>();
		for(int i=0;i<7;i++){
			TableData_PLog obj = new TableData_PLog();
			obj.setSTAND("F"+(i+1));
			this.tableDataPLogList.add(obj);
		}
		
		for (String line : dataList){
			ArrayList<String> tempList = ParserDefault.splitLineData_table3(line, ",");
			if(tempList.get(0).equals(UILabel.BUR_TDIA)){
				this.tableDataPLogList.get(0).setBUR_TDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setBUR_TDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setBUR_TDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setBUR_TDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setBUR_TDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setBUR_TDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setBUR_TDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.BUR_BDIA)){
				this.tableDataPLogList.get(0).setBUR_BDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setBUR_BDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setBUR_BDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setBUR_BDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setBUR_BDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setBUR_BDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setBUR_BDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_TDIA)){
				this.tableDataPLogList.get(0).setWR_TDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_TDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_TDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_TDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_TDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_TDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_TDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_BDIA)){
				this.tableDataPLogList.get(0).setWR_BDIA(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_BDIA(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_BDIA(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_BDIA(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_BDIA(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_BDIA(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_BDIA(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_ICRN)){
				this.tableDataPLogList.get(0).setWR_ICRN(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_ICRN(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_ICRN(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_ICRN(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_ICRN(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_ICRN(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_ICRN(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ENTRY_THK)){
				this.tableDataPLogList.get(0).setENTRY_THK(tempList.get(1));
				this.tableDataPLogList.get(1).setENTRY_THK(tempList.get(2));
				this.tableDataPLogList.get(2).setENTRY_THK(tempList.get(3));
				this.tableDataPLogList.get(3).setENTRY_THK(tempList.get(4));
				this.tableDataPLogList.get(4).setENTRY_THK(tempList.get(5));
				this.tableDataPLogList.get(5).setENTRY_THK(tempList.get(6));
				this.tableDataPLogList.get(6).setENTRY_THK(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.EXIT_THK)){
				this.tableDataPLogList.get(0).setEXIT_THK(tempList.get(1));
				this.tableDataPLogList.get(1).setEXIT_THK(tempList.get(2));
				this.tableDataPLogList.get(2).setEXIT_THK(tempList.get(3));
				this.tableDataPLogList.get(3).setEXIT_THK(tempList.get(4));
				this.tableDataPLogList.get(4).setEXIT_THK(tempList.get(5));
				this.tableDataPLogList.get(5).setEXIT_THK(tempList.get(6));
				this.tableDataPLogList.get(6).setEXIT_THK(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.PAS_LINE)){
				this.tableDataPLogList.get(0).setPAS_LINE(tempList.get(1));
				this.tableDataPLogList.get(1).setPAS_LINE(tempList.get(2));
				this.tableDataPLogList.get(2).setPAS_LINE(tempList.get(3));
				this.tableDataPLogList.get(3).setPAS_LINE(tempList.get(4));
				this.tableDataPLogList.get(4).setPAS_LINE(tempList.get(5));
				this.tableDataPLogList.get(5).setPAS_LINE(tempList.get(6));
				this.tableDataPLogList.get(6).setPAS_LINE(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ROL_GAP)){
				this.tableDataPLogList.get(0).setROL_GAP(tempList.get(1));
				this.tableDataPLogList.get(1).setROL_GAP(tempList.get(2));
				this.tableDataPLogList.get(2).setROL_GAP(tempList.get(3));
				this.tableDataPLogList.get(3).setROL_GAP(tempList.get(4));
				this.tableDataPLogList.get(4).setROL_GAP(tempList.get(5));
				this.tableDataPLogList.get(5).setROL_GAP(tempList.get(6));
				this.tableDataPLogList.get(6).setROL_GAP(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.STP_WID)){
				this.tableDataPLogList.get(0).setSTP_WID(tempList.get(1));
				this.tableDataPLogList.get(1).setSTP_WID(tempList.get(2));
				this.tableDataPLogList.get(2).setSTP_WID(tempList.get(3));
				this.tableDataPLogList.get(3).setSTP_WID(tempList.get(4));
				this.tableDataPLogList.get(4).setSTP_WID(tempList.get(5));
				this.tableDataPLogList.get(5).setSTP_WID(tempList.get(6));
				this.tableDataPLogList.get(6).setSTP_WID(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.STP_LEN)){
				this.tableDataPLogList.get(0).setSTP_LEN(tempList.get(1));
				this.tableDataPLogList.get(1).setSTP_LEN(tempList.get(2));
				this.tableDataPLogList.get(2).setSTP_LEN(tempList.get(3));
				this.tableDataPLogList.get(3).setSTP_LEN(tempList.get(4));
				this.tableDataPLogList.get(4).setSTP_LEN(tempList.get(5));
				this.tableDataPLogList.get(5).setSTP_LEN(tempList.get(6));
				this.tableDataPLogList.get(6).setSTP_LEN(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ENTRY_TEMP)){
				this.tableDataPLogList.get(0).setENTRY_TEMP(tempList.get(1));
				this.tableDataPLogList.get(1).setENTRY_TEMP(tempList.get(2));
				this.tableDataPLogList.get(2).setENTRY_TEMP(tempList.get(3));
				this.tableDataPLogList.get(3).setENTRY_TEMP(tempList.get(4));
				this.tableDataPLogList.get(4).setENTRY_TEMP(tempList.get(5));
				this.tableDataPLogList.get(5).setENTRY_TEMP(tempList.get(6));
				this.tableDataPLogList.get(6).setENTRY_TEMP(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.EXIT_TEMP)){
				this.tableDataPLogList.get(0).setEXIT_TEMP(tempList.get(1));
				this.tableDataPLogList.get(1).setEXIT_TEMP(tempList.get(2));
				this.tableDataPLogList.get(2).setEXIT_TEMP(tempList.get(3));
				this.tableDataPLogList.get(3).setEXIT_TEMP(tempList.get(4));
				this.tableDataPLogList.get(4).setEXIT_TEMP(tempList.get(5));
				this.tableDataPLogList.get(5).setEXIT_TEMP(tempList.get(6));
				this.tableDataPLogList.get(6).setEXIT_TEMP(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.FRCE)){
				this.tableDataPLogList.get(0).setFRCE(tempList.get(1));
				this.tableDataPLogList.get(1).setFRCE(tempList.get(2));
				this.tableDataPLogList.get(2).setFRCE(tempList.get(3));
				this.tableDataPLogList.get(3).setFRCE(tempList.get(4));
				this.tableDataPLogList.get(4).setFRCE(tempList.get(5));
				this.tableDataPLogList.get(5).setFRCE(tempList.get(6));
				this.tableDataPLogList.get(6).setFRCE(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.TORQ)){
				this.tableDataPLogList.get(0).setTORQ(tempList.get(1));
				this.tableDataPLogList.get(1).setTORQ(tempList.get(2));
				this.tableDataPLogList.get(2).setTORQ(tempList.get(3));
				this.tableDataPLogList.get(3).setTORQ(tempList.get(4));
				this.tableDataPLogList.get(4).setTORQ(tempList.get(5));
				this.tableDataPLogList.get(5).setTORQ(tempList.get(6));
				this.tableDataPLogList.get(6).setTORQ(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.SPEED_mpm)){
				this.tableDataPLogList.get(0).setSPEED(tempList.get(1));
				this.tableDataPLogList.get(1).setSPEED(tempList.get(2));
				this.tableDataPLogList.get(2).setSPEED(tempList.get(3));
				this.tableDataPLogList.get(3).setSPEED(tempList.get(4));
				this.tableDataPLogList.get(4).setSPEED(tempList.get(5));
				this.tableDataPLogList.get(5).setSPEED(tempList.get(6));
				this.tableDataPLogList.get(6).setSPEED(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.BEND)){
				this.tableDataPLogList.get(0).setBEND(tempList.get(1));
				this.tableDataPLogList.get(1).setBEND(tempList.get(2));
				this.tableDataPLogList.get(2).setBEND(tempList.get(3));
				this.tableDataPLogList.get(3).setBEND(tempList.get(4));
				this.tableDataPLogList.get(4).setBEND(tempList.get(5));
				this.tableDataPLogList.get(5).setBEND(tempList.get(6));
				this.tableDataPLogList.get(6).setBEND(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.P_CROSS)){
				this.tableDataPLogList.get(0).setP_CROSS(tempList.get(1));
				this.tableDataPLogList.get(1).setP_CROSS(tempList.get(2));
				this.tableDataPLogList.get(2).setP_CROSS(tempList.get(3));
				this.tableDataPLogList.get(3).setP_CROSS(tempList.get(4));
				this.tableDataPLogList.get(4).setP_CROSS(tempList.get(5));
				this.tableDataPLogList.get(5).setP_CROSS(tempList.get(6));
				this.tableDataPLogList.get(6).setP_CROSS(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.TENS)){
				this.tableDataPLogList.get(0).setTENS(tempList.get(1));
				this.tableDataPLogList.get(1).setTENS(tempList.get(2));
				this.tableDataPLogList.get(2).setTENS(tempList.get(3));
				this.tableDataPLogList.get(3).setTENS(tempList.get(4));
				this.tableDataPLogList.get(4).setTENS(tempList.get(5));
				this.tableDataPLogList.get(5).setTENS(tempList.get(6));
				this.tableDataPLogList.get(6).setTENS(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.ROL_TIM)){
				this.tableDataPLogList.get(0).setROL_TIM(tempList.get(1));
				this.tableDataPLogList.get(1).setROL_TIM(tempList.get(2));
				this.tableDataPLogList.get(2).setROL_TIM(tempList.get(3));
				this.tableDataPLogList.get(3).setROL_TIM(tempList.get(4));
				this.tableDataPLogList.get(4).setROL_TIM(tempList.get(5));
				this.tableDataPLogList.get(5).setROL_TIM(tempList.get(6));
				this.tableDataPLogList.get(6).setROL_TIM(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.IDL_TIM)){
				this.tableDataPLogList.get(0).setIDL_TIM(tempList.get(1));
				this.tableDataPLogList.get(1).setIDL_TIM(tempList.get(2));
				this.tableDataPLogList.get(2).setIDL_TIM(tempList.get(3));
				this.tableDataPLogList.get(3).setIDL_TIM(tempList.get(4));
				this.tableDataPLogList.get(4).setIDL_TIM(tempList.get(5));
				this.tableDataPLogList.get(5).setIDL_TIM(tempList.get(6));
				this.tableDataPLogList.get(6).setIDL_TIM(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.BUR_WEAR)){
				this.tableDataPLogList.get(0).setBUR_WEAR(tempList.get(1));
				this.tableDataPLogList.get(1).setBUR_WEAR(tempList.get(2));
				this.tableDataPLogList.get(2).setBUR_WEAR(tempList.get(3));
				this.tableDataPLogList.get(3).setBUR_WEAR(tempList.get(4));
				this.tableDataPLogList.get(4).setBUR_WEAR(tempList.get(5));
				this.tableDataPLogList.get(5).setBUR_WEAR(tempList.get(6));
				this.tableDataPLogList.get(6).setBUR_WEAR(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_WEAR)){
				this.tableDataPLogList.get(0).setWR_WEAR(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_WEAR(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_WEAR(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_WEAR(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_WEAR(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_WEAR(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_WEAR(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.WR_THRM)){
				this.tableDataPLogList.get(0).setWR_THRM(tempList.get(1));
				this.tableDataPLogList.get(1).setWR_THRM(tempList.get(2));
				this.tableDataPLogList.get(2).setWR_THRM(tempList.get(3));
				this.tableDataPLogList.get(3).setWR_THRM(tempList.get(4));
				this.tableDataPLogList.get(4).setWR_THRM(tempList.get(5));
				this.tableDataPLogList.get(5).setWR_THRM(tempList.get(6));
				this.tableDataPLogList.get(6).setWR_THRM(tempList.get(7));
			}
		}
		
		for (String line : this.otherValueList){
			ArrayList<String> tempList = ParserDefault.splitLineData_table3(line, ",");
			if(tempList.get(0).equals(UILabel.wr_len)){
				this.tableDataPLogList.get(0).setWr_len(tempList.get(1));
				this.tableDataPLogList.get(1).setWr_len(tempList.get(2));
				this.tableDataPLogList.get(2).setWr_len(tempList.get(3));
				this.tableDataPLogList.get(3).setWr_len(tempList.get(4));
				this.tableDataPLogList.get(4).setWr_len(tempList.get(5));
				this.tableDataPLogList.get(5).setWr_len(tempList.get(6));
				this.tableDataPLogList.get(6).setWr_len(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.wr_div_angle)){
				this.tableDataPLogList.get(0).setWr_div_angle(tempList.get(1));
				this.tableDataPLogList.get(1).setWr_div_angle(tempList.get(2));
				this.tableDataPLogList.get(2).setWr_div_angle(tempList.get(3));
				this.tableDataPLogList.get(3).setWr_div_angle(tempList.get(4));
				this.tableDataPLogList.get(4).setWr_div_angle(tempList.get(5));
				this.tableDataPLogList.get(5).setWr_div_angle(tempList.get(6));
				this.tableDataPLogList.get(6).setWr_div_angle(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.bur_len)){
				this.tableDataPLogList.get(0).setBur_len(tempList.get(1));
				this.tableDataPLogList.get(1).setBur_len(tempList.get(2));
				this.tableDataPLogList.get(2).setBur_len(tempList.get(3));
				this.tableDataPLogList.get(3).setBur_len(tempList.get(4));
				this.tableDataPLogList.get(4).setBur_len(tempList.get(5));
				this.tableDataPLogList.get(5).setBur_len(tempList.get(6));
				this.tableDataPLogList.get(6).setBur_len(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.bur_div_angle)){
				this.tableDataPLogList.get(0).setBur_div_angle(tempList.get(1));
				this.tableDataPLogList.get(1).setBur_div_angle(tempList.get(2));
				this.tableDataPLogList.get(2).setBur_div_angle(tempList.get(3));
				this.tableDataPLogList.get(3).setBur_div_angle(tempList.get(4));
				this.tableDataPLogList.get(4).setBur_div_angle(tempList.get(5));
				this.tableDataPLogList.get(5).setBur_div_angle(tempList.get(6));
				this.tableDataPLogList.get(6).setBur_div_angle(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.p_in)){
				this.tableDataPLogList.get(0).setP_in(tempList.get(1));
				this.tableDataPLogList.get(1).setP_in(tempList.get(2));
				this.tableDataPLogList.get(2).setP_in(tempList.get(3));
				this.tableDataPLogList.get(3).setP_in(tempList.get(4));
				this.tableDataPLogList.get(4).setP_in(tempList.get(5));
				this.tableDataPLogList.get(5).setP_in(tempList.get(6));
				this.tableDataPLogList.get(6).setP_in(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.pl_m)){
				this.tableDataPLogList.get(0).setPl_m(tempList.get(1));
				this.tableDataPLogList.get(1).setPl_m(tempList.get(2));
				this.tableDataPLogList.get(2).setPl_m(tempList.get(3));
				this.tableDataPLogList.get(3).setPl_m(tempList.get(4));
				this.tableDataPLogList.get(4).setPl_m(tempList.get(5));
				this.tableDataPLogList.get(5).setPl_m(tempList.get(6));
				this.tableDataPLogList.get(6).setPl_m(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.t_div)){
				this.tableDataPLogList.get(0).setT_div(tempList.get(1));
				this.tableDataPLogList.get(1).setT_div(tempList.get(2));
				this.tableDataPLogList.get(2).setT_div(tempList.get(3));
				this.tableDataPLogList.get(3).setT_div(tempList.get(4));
				this.tableDataPLogList.get(4).setT_div(tempList.get(5));
				this.tableDataPLogList.get(5).setT_div(tempList.get(6));
				this.tableDataPLogList.get(6).setT_div(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.f_r2p)){
				this.tableDataPLogList.get(0).setF_r2p(tempList.get(1));
				this.tableDataPLogList.get(1).setF_r2p(tempList.get(2));
				this.tableDataPLogList.get(2).setF_r2p(tempList.get(3));
				this.tableDataPLogList.get(3).setF_r2p(tempList.get(4));
				this.tableDataPLogList.get(4).setF_r2p(tempList.get(5));
				this.tableDataPLogList.get(5).setF_r2p(tempList.get(6));
				this.tableDataPLogList.get(6).setF_r2p(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.f_r2r)){
				this.tableDataPLogList.get(0).setF_r2r(tempList.get(1));
				this.tableDataPLogList.get(1).setF_r2r(tempList.get(2));
				this.tableDataPLogList.get(2).setF_r2r(tempList.get(3));
				this.tableDataPLogList.get(3).setF_r2r(tempList.get(4));
				this.tableDataPLogList.get(4).setF_r2r(tempList.get(5));
				this.tableDataPLogList.get(5).setF_r2r(tempList.get(6));
				this.tableDataPLogList.get(6).setF_r2r(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.vel_rate_top)){
				this.tableDataPLogList.get(0).setSpeed_different_ratio_top_roll(tempList.get(1));
				this.tableDataPLogList.get(1).setSpeed_different_ratio_top_roll(tempList.get(2));
				this.tableDataPLogList.get(2).setSpeed_different_ratio_top_roll(tempList.get(3));
				this.tableDataPLogList.get(3).setSpeed_different_ratio_top_roll(tempList.get(4));
				this.tableDataPLogList.get(4).setSpeed_different_ratio_top_roll(tempList.get(5));
				this.tableDataPLogList.get(5).setSpeed_different_ratio_top_roll(tempList.get(6));
				this.tableDataPLogList.get(6).setSpeed_different_ratio_top_roll(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.vel_rate_bottom)){
				this.tableDataPLogList.get(0).setSpeed_different_ratio_bottom_roll(tempList.get(1));
				this.tableDataPLogList.get(1).setSpeed_different_ratio_bottom_roll(tempList.get(2));
				this.tableDataPLogList.get(2).setSpeed_different_ratio_bottom_roll(tempList.get(3));
				this.tableDataPLogList.get(3).setSpeed_different_ratio_bottom_roll(tempList.get(4));
				this.tableDataPLogList.get(4).setSpeed_different_ratio_bottom_roll(tempList.get(5));
				this.tableDataPLogList.get(5).setSpeed_different_ratio_bottom_roll(tempList.get(6));
				this.tableDataPLogList.get(6).setSpeed_different_ratio_bottom_roll(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.wr_trot)){
				this.tableDataPLogList.get(0).setWr_trot(tempList.get(1));
				this.tableDataPLogList.get(1).setWr_trot(tempList.get(2));
				this.tableDataPLogList.get(2).setWr_trot(tempList.get(3));
				this.tableDataPLogList.get(3).setWr_trot(tempList.get(4));
				this.tableDataPLogList.get(4).setWr_trot(tempList.get(5));
				this.tableDataPLogList.get(5).setWr_trot(tempList.get(6));
				this.tableDataPLogList.get(6).setWr_trot(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.wr_brot)){
				this.tableDataPLogList.get(0).setWr_brot(tempList.get(1));
				this.tableDataPLogList.get(1).setWr_brot(tempList.get(2));
				this.tableDataPLogList.get(2).setWr_brot(tempList.get(3));
				this.tableDataPLogList.get(3).setWr_brot(tempList.get(4));
				this.tableDataPLogList.get(4).setWr_brot(tempList.get(5));
				this.tableDataPLogList.get(5).setWr_brot(tempList.get(6));
				this.tableDataPLogList.get(6).setWr_brot(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.bur_trot)){
				this.tableDataPLogList.get(0).setBur_trot(tempList.get(1));
				this.tableDataPLogList.get(1).setBur_trot(tempList.get(2));
				this.tableDataPLogList.get(2).setBur_trot(tempList.get(3));
				this.tableDataPLogList.get(3).setBur_trot(tempList.get(4));
				this.tableDataPLogList.get(4).setBur_trot(tempList.get(5));
				this.tableDataPLogList.get(5).setBur_trot(tempList.get(6));
				this.tableDataPLogList.get(6).setBur_trot(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.bur_brot)){
				this.tableDataPLogList.get(0).setBur_brot(tempList.get(1));
				this.tableDataPLogList.get(1).setBur_brot(tempList.get(2));
				this.tableDataPLogList.get(2).setBur_brot(tempList.get(3));
				this.tableDataPLogList.get(3).setBur_brot(tempList.get(4));
				this.tableDataPLogList.get(4).setBur_brot(tempList.get(5));
				this.tableDataPLogList.get(5).setBur_brot(tempList.get(6));
				this.tableDataPLogList.get(6).setBur_brot(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.YM_Constant)){
				this.tableDataPLogList.get(0).setYM_Constant(tempList.get(1));
				this.tableDataPLogList.get(1).setYM_Constant(tempList.get(2));
				this.tableDataPLogList.get(2).setYM_Constant(tempList.get(3));
				this.tableDataPLogList.get(3).setYM_Constant(tempList.get(4));
				this.tableDataPLogList.get(4).setYM_Constant(tempList.get(5));
				this.tableDataPLogList.get(5).setYM_Constant(tempList.get(6));
				this.tableDataPLogList.get(6).setYM_Constant(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.YM_Table)){
				this.tableDataPLogList.get(0).setYM_Table(tempList.get(1));
				this.tableDataPLogList.get(1).setYM_Table(tempList.get(2));
				this.tableDataPLogList.get(2).setYM_Table(tempList.get(3));
				this.tableDataPLogList.get(3).setYM_Table(tempList.get(4));
				this.tableDataPLogList.get(4).setYM_Table(tempList.get(5));
				this.tableDataPLogList.get(5).setYM_Table(tempList.get(6));
				this.tableDataPLogList.get(6).setYM_Table(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.YM_Value)){
				this.tableDataPLogList.get(0).setYM_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setYM_Value(tempList.get(2));
				this.tableDataPLogList.get(2).setYM_Value(tempList.get(3));
				this.tableDataPLogList.get(3).setYM_Value(tempList.get(4));
				this.tableDataPLogList.get(4).setYM_Value(tempList.get(5));
				this.tableDataPLogList.get(5).setYM_Value(tempList.get(6));
				this.tableDataPLogList.get(6).setYM_Value(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.TEC_Constant)){
				this.tableDataPLogList.get(0).setTEC_Constant(tempList.get(1));
				this.tableDataPLogList.get(1).setTEC_Constant(tempList.get(2));
				this.tableDataPLogList.get(2).setTEC_Constant(tempList.get(3));
				this.tableDataPLogList.get(3).setTEC_Constant(tempList.get(4));
				this.tableDataPLogList.get(4).setTEC_Constant(tempList.get(5));
				this.tableDataPLogList.get(5).setTEC_Constant(tempList.get(6));
				this.tableDataPLogList.get(6).setTEC_Constant(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.TEC_Table)){
				this.tableDataPLogList.get(0).setTEC_Table(tempList.get(1));
				this.tableDataPLogList.get(1).setTEC_Table(tempList.get(2));
				this.tableDataPLogList.get(2).setTEC_Table(tempList.get(3));
				this.tableDataPLogList.get(3).setTEC_Table(tempList.get(4));
				this.tableDataPLogList.get(4).setTEC_Table(tempList.get(5));
				this.tableDataPLogList.get(5).setTEC_Table(tempList.get(6));
				this.tableDataPLogList.get(6).setTEC_Table(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.TEC_Value)){
				this.tableDataPLogList.get(0).setTEC_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setTEC_Value(tempList.get(2));
				this.tableDataPLogList.get(2).setTEC_Value(tempList.get(3));
				this.tableDataPLogList.get(3).setTEC_Value(tempList.get(4));
				this.tableDataPLogList.get(4).setTEC_Value(tempList.get(5));
				this.tableDataPLogList.get(5).setTEC_Value(tempList.get(6));
				this.tableDataPLogList.get(6).setTEC_Value(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.PR_Constant)){
				this.tableDataPLogList.get(0).setPR_Constant(tempList.get(1));
				this.tableDataPLogList.get(1).setPR_Constant(tempList.get(2));
				this.tableDataPLogList.get(2).setPR_Constant(tempList.get(3));
				this.tableDataPLogList.get(3).setPR_Constant(tempList.get(4));
				this.tableDataPLogList.get(4).setPR_Constant(tempList.get(5));
				this.tableDataPLogList.get(5).setPR_Constant(tempList.get(6));
				this.tableDataPLogList.get(6).setPR_Constant(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.PR_Table)){
				this.tableDataPLogList.get(0).setPR_Table(tempList.get(1));
				this.tableDataPLogList.get(1).setPR_Table(tempList.get(2));
				this.tableDataPLogList.get(2).setPR_Table(tempList.get(3));
				this.tableDataPLogList.get(3).setPR_Table(tempList.get(4));
				this.tableDataPLogList.get(4).setPR_Table(tempList.get(5));
				this.tableDataPLogList.get(5).setPR_Table(tempList.get(6));
				this.tableDataPLogList.get(6).setPR_Table(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.PR_Value)){
				this.tableDataPLogList.get(0).setPR_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setPR_Table(tempList.get(2));
				this.tableDataPLogList.get(2).setPR_Table(tempList.get(3));
				this.tableDataPLogList.get(3).setPR_Table(tempList.get(4));
				this.tableDataPLogList.get(4).setPR_Table(tempList.get(5));
				this.tableDataPLogList.get(5).setPR_Table(tempList.get(6));
				this.tableDataPLogList.get(6).setPR_Table(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.MD_Value)){
				this.tableDataPLogList.get(0).setMD_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setMD_Value(tempList.get(2));
				this.tableDataPLogList.get(2).setMD_Value(tempList.get(3));
				this.tableDataPLogList.get(3).setMD_Value(tempList.get(4));
				this.tableDataPLogList.get(4).setMD_Value(tempList.get(5));
				this.tableDataPLogList.get(5).setMD_Value(tempList.get(6));
				this.tableDataPLogList.get(6).setMD_Value(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.FS_Constant)){
				this.tableDataPLogList.get(0).setFS_Constant(tempList.get(1));
				this.tableDataPLogList.get(1).setFS_Constant(tempList.get(2));
				this.tableDataPLogList.get(2).setFS_Constant(tempList.get(3));
				this.tableDataPLogList.get(3).setFS_Constant(tempList.get(4));
				this.tableDataPLogList.get(4).setFS_Constant(tempList.get(5));
				this.tableDataPLogList.get(5).setFS_Constant(tempList.get(6));
				this.tableDataPLogList.get(6).setFS_Constant(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.FS_Table)){
				this.tableDataPLogList.get(0).setFS_Table(tempList.get(1));
				this.tableDataPLogList.get(1).setFS_Table(tempList.get(2));
				this.tableDataPLogList.get(2).setFS_Table(tempList.get(3));
				this.tableDataPLogList.get(3).setFS_Table(tempList.get(4));
				this.tableDataPLogList.get(4).setFS_Table(tempList.get(5));
				this.tableDataPLogList.get(5).setFS_Table(tempList.get(6));
				this.tableDataPLogList.get(6).setFS_Table(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.FS_Value)){
				this.tableDataPLogList.get(0).setFS_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setFS_Value(tempList.get(2));
				this.tableDataPLogList.get(2).setFS_Value(tempList.get(3));
				this.tableDataPLogList.get(3).setFS_Value(tempList.get(4));
				this.tableDataPLogList.get(4).setFS_Value(tempList.get(5));
				this.tableDataPLogList.get(5).setFS_Value(tempList.get(6));
				this.tableDataPLogList.get(6).setFS_Value(tempList.get(7));
			}
			if(tempList.get(0).equals(UILabel.YS_Value)){
				this.tableDataPLogList.get(0).setYS_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setYS_Value(tempList.get(2));
				this.tableDataPLogList.get(2).setYS_Value(tempList.get(3));
				this.tableDataPLogList.get(3).setYS_Value(tempList.get(4));
				this.tableDataPLogList.get(4).setYS_Value(tempList.get(5));
				this.tableDataPLogList.get(5).setYS_Value(tempList.get(6));
				this.tableDataPLogList.get(6).setYS_Value(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.TS_Value)){
				this.tableDataPLogList.get(0).setTS_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setTS_Value(tempList.get(2));
				this.tableDataPLogList.get(2).setTS_Value(tempList.get(3));
				this.tableDataPLogList.get(3).setTS_Value(tempList.get(4));
				this.tableDataPLogList.get(4).setTS_Value(tempList.get(5));
				this.tableDataPLogList.get(5).setTS_Value(tempList.get(6));
				this.tableDataPLogList.get(6).setTS_Value(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.E_Value)){
				this.tableDataPLogList.get(0).setE_Value(tempList.get(1));
				this.tableDataPLogList.get(1).setE_Value(tempList.get(2));
				this.tableDataPLogList.get(2).setE_Value(tempList.get(3));
				this.tableDataPLogList.get(3).setE_Value(tempList.get(4));
				this.tableDataPLogList.get(4).setE_Value(tempList.get(5));
				this.tableDataPLogList.get(5).setE_Value(tempList.get(6));
				this.tableDataPLogList.get(6).setE_Value(tempList.get(7));
			}
			
			
			
			
			if(tempList.get(0).equals(UILabel.lcase_time)){
				this.tableDataPLogList.get(0).setLcase_time(tempList.get(1));
				this.tableDataPLogList.get(1).setLcase_time(tempList.get(2));
				this.tableDataPLogList.get(2).setLcase_time(tempList.get(3));
				this.tableDataPLogList.get(3).setLcase_time(tempList.get(4));
				this.tableDataPLogList.get(4).setLcase_time(tempList.get(5));
				this.tableDataPLogList.get(5).setLcase_time(tempList.get(6));
				this.tableDataPLogList.get(6).setLcase_time(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.lcase_inc)){
				this.tableDataPLogList.get(0).setlcase_inc(tempList.get(1));
				this.tableDataPLogList.get(1).setlcase_inc(tempList.get(2));
				this.tableDataPLogList.get(2).setlcase_inc(tempList.get(3));
				this.tableDataPLogList.get(3).setlcase_inc(tempList.get(4));
				this.tableDataPLogList.get(4).setlcase_inc(tempList.get(5));
				this.tableDataPLogList.get(5).setlcase_inc(tempList.get(6));
				this.tableDataPLogList.get(6).setlcase_inc(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.post_inc)){
				this.tableDataPLogList.get(0).setPost_inc(tempList.get(1));
				this.tableDataPLogList.get(1).setPost_inc(tempList.get(2));
				this.tableDataPLogList.get(2).setPost_inc(tempList.get(3));
				this.tableDataPLogList.get(3).setPost_inc(tempList.get(4));
				this.tableDataPLogList.get(4).setPost_inc(tempList.get(5));
				this.tableDataPLogList.get(5).setPost_inc(tempList.get(6));
				this.tableDataPLogList.get(6).setPost_inc(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.lcase_dt)){
				this.tableDataPLogList.get(0).setlcase_dt(tempList.get(1));
				this.tableDataPLogList.get(1).setlcase_dt(tempList.get(2));
				this.tableDataPLogList.get(2).setlcase_dt(tempList.get(3));
				this.tableDataPLogList.get(3).setlcase_dt(tempList.get(4));
				this.tableDataPLogList.get(4).setlcase_dt(tempList.get(5));
				this.tableDataPLogList.get(5).setlcase_dt(tempList.get(6));
				this.tableDataPLogList.get(6).setlcase_dt(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.ParallelDDM)){
				this.tableDataPLogList.get(0).setParallelDDM(tempList.get(1));
				this.tableDataPLogList.get(1).setParallelDDM(tempList.get(2));
				this.tableDataPLogList.get(2).setParallelDDM(tempList.get(3));
				this.tableDataPLogList.get(3).setParallelDDM(tempList.get(4));
				this.tableDataPLogList.get(4).setParallelDDM(tempList.get(5));
				this.tableDataPLogList.get(5).setParallelDDM(tempList.get(6));
				this.tableDataPLogList.get(6).setParallelDDM(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.Domain)){
				this.tableDataPLogList.get(0).setDomain(tempList.get(1));
				this.tableDataPLogList.get(1).setDomain(tempList.get(2));
				this.tableDataPLogList.get(2).setDomain(tempList.get(3));
				this.tableDataPLogList.get(3).setDomain(tempList.get(4));
				this.tableDataPLogList.get(4).setDomain(tempList.get(5));
				this.tableDataPLogList.get(5).setDomain(tempList.get(6));
				this.tableDataPLogList.get(6).setDomain(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.ParallelMultiThread)){
				this.tableDataPLogList.get(0).setParallelMultiThread(tempList.get(1));
				this.tableDataPLogList.get(1).setParallelMultiThread(tempList.get(2));
				this.tableDataPLogList.get(2).setParallelMultiThread(tempList.get(3));
				this.tableDataPLogList.get(3).setParallelMultiThread(tempList.get(4));
				this.tableDataPLogList.get(4).setParallelMultiThread(tempList.get(5));
				this.tableDataPLogList.get(5).setParallelMultiThread(tempList.get(6));
				this.tableDataPLogList.get(6).setParallelMultiThread(tempList.get(7));
			}if(tempList.get(0).equals(UILabel.Thread)){
				this.tableDataPLogList.get(0).setThread(tempList.get(1));
				this.tableDataPLogList.get(1).setThread(tempList.get(2));
				this.tableDataPLogList.get(2).setThread(tempList.get(3));
				this.tableDataPLogList.get(3).setThread(tempList.get(4));
				this.tableDataPLogList.get(4).setThread(tempList.get(5));
				this.tableDataPLogList.get(5).setThread(tempList.get(6));
				this.tableDataPLogList.get(6).setThread(tempList.get(7));
			}
		}
	}
	
	//PLog ���̺� �� ������ �����
	private void createTableRowData(){
		//1
		TableRowData_BUR_TDIA r1Obj = new TableRowData_BUR_TDIA();
		r1Obj.setAllData(this.tableDataPLogList);
		//2
		TableRowData_BUR_BDIA r2Obj = new TableRowData_BUR_BDIA();
		r2Obj.setAllData(this.tableDataPLogList);
		//3
		TableRowData_WR_TDIA r3Obj = new TableRowData_WR_TDIA();
		r3Obj.setAllData(this.tableDataPLogList);
		//4
		TableRowData_WR_BDIA r4Obj = new TableRowData_WR_BDIA();
		r4Obj.setAllData(this.tableDataPLogList);
		//5
		TableRowData_WR_ICRN r5Obj = new TableRowData_WR_ICRN();
		r5Obj.setAllData(this.tableDataPLogList);
		//6
		TableRowData_ENTRY_THK r6Obj = new TableRowData_ENTRY_THK();
		r6Obj.setAllData(this.tableDataPLogList);
		//7
		TableRowData_EXIT_THK r7Obj = new TableRowData_EXIT_THK();
		r7Obj.setAllData(this.tableDataPLogList);
		//8
		TableRowData_PAS_LINE r8Obj = new TableRowData_PAS_LINE();
		r8Obj.setAllData(this.tableDataPLogList);
		//9
		TableRowData_ROL_GAP r9Obj = new TableRowData_ROL_GAP();
		r9Obj.setAllData(this.tableDataPLogList);
		//10
		TableRowData_STP_WID r10Obj = new TableRowData_STP_WID();
		r10Obj.setAllData(this.tableDataPLogList);
		//11
		TableRowData_STP_LEN r11Obj = new TableRowData_STP_LEN();
		r11Obj.setAllData(this.tableDataPLogList);
		//12
		TableRowData_ENTRY_TEMP r12Obj = new TableRowData_ENTRY_TEMP();
		r12Obj.setAllData(this.tableDataPLogList);
		//13
		TableRowData_EXIT_TEMP r13Obj = new TableRowData_EXIT_TEMP();
		r13Obj.setAllData(this.tableDataPLogList);
		//14
		TableRowData_FRCE r14Obj = new TableRowData_FRCE();
		r14Obj.setAllData(this.tableDataPLogList);
		//15
		TableRowData_TORQ r15Obj = new TableRowData_TORQ();
		r15Obj.setAllData(this.tableDataPLogList);
		//16
		TableRowData_SPEED r16Obj = new TableRowData_SPEED();
		r16Obj.setAllData(this.tableDataPLogList);
		//17
		TableRowData_BEND r17Obj = new TableRowData_BEND();
		r17Obj.setAllData(this.tableDataPLogList);
		//18
		TableRowData_P_CROSS r18Obj = new TableRowData_P_CROSS();
		r18Obj.setAllData(this.tableDataPLogList);
		//19
		TableRowData_TENS r19Obj = new TableRowData_TENS();
		r19Obj.setAllData(this.tableDataPLogList);
		//20
		TableRowData_ROL_TIM r20Obj = new TableRowData_ROL_TIM();
		r20Obj.setAllData(this.tableDataPLogList);
		//21
		TableRowData_IDL_TIM r21Obj = new TableRowData_IDL_TIM();
		r21Obj.setAllData(this.tableDataPLogList);
		//22
		TableRowData_BUR_WEAR r22Obj = new TableRowData_BUR_WEAR();
		r22Obj.setAllData(this.tableDataPLogList);
		//23
		TableRowData_WR_WEAR r23Obj = new TableRowData_WR_WEAR();
		r23Obj.setAllData(this.tableDataPLogList);
		//24
		TableRowData_WR_THRM r24Obj = new TableRowData_WR_THRM();
		r24Obj.setAllData(this.tableDataPLogList);
		
		this.tableRowDataList = new ArrayList<TableRowData>();
		this.tableRowDataList.add(r1Obj);
		this.tableRowDataList.add(r2Obj);
		this.tableRowDataList.add(r3Obj);
		this.tableRowDataList.add(r4Obj);
		this.tableRowDataList.add(r5Obj);
		this.tableRowDataList.add(r6Obj);
		this.tableRowDataList.add(r7Obj);
		this.tableRowDataList.add(r8Obj);
		this.tableRowDataList.add(r9Obj);
		this.tableRowDataList.add(r10Obj);
		this.tableRowDataList.add(r11Obj);
		this.tableRowDataList.add(r12Obj);
		this.tableRowDataList.add(r13Obj);
		this.tableRowDataList.add(r14Obj);
		this.tableRowDataList.add(r15Obj);
		this.tableRowDataList.add(r16Obj);
		this.tableRowDataList.add(r17Obj);
		this.tableRowDataList.add(r18Obj);
		this.tableRowDataList.add(r19Obj);
		this.tableRowDataList.add(r20Obj);
		this.tableRowDataList.add(r21Obj);
		this.tableRowDataList.add(r22Obj);
		this.tableRowDataList.add(r23Obj);
		this.tableRowDataList.add(r24Obj);
	}
	
	// Table�� ������ ���� 
	private void updateTableData(){
		try{
			med.getTableViewerSlabPlateInfo().setLabelProvider(new TableViewerLabelProvider_SlabPlateInfo());
			med.getTableViewerSlabPlateInfo().setContentProvider(new ArrayContentProvider());
			med.getTableViewerSlabPlateInfo().setInput(this.tableDataSlabPlateInfoList);
			
			med.getTableViewerVariable().setLabelProvider(new TableViewerLabelProvider_Variable());
			med.getTableViewerVariable().setContentProvider(new ArrayContentProvider());
			med.getTableViewerVariable().setInput(this.tableDataVariableList);
			
			med.getTableViewerPLog().setLabelProvider(new TableViewerLabelProvider_PLog());
			med.getTableViewerPLog().setContentProvider(new ArrayContentProvider());
			med.getTableViewerPLog().setInput(this.tableRowDataList);
			
		}catch (Exception e){
			String msg = "ERROR - Update Table Data";
			msg = msg +"\n"+e.getMessage();
			Log.error(msg);
		}
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// Button - Export P Log 
	//
	public void ExportPLog(){
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = TableDataSlabPlateInfoObj.getSTRIP_NO()+","+
				TableDataSlabPlateInfoObj.getSTHK()+","+
				TableDataSlabPlateInfoObj.getSWID()+","+
				TableDataSlabPlateInfoObj.getSLEN()+","+
				TableDataSlabPlateInfoObj.getSWET()+","+
				TableDataSlabPlateInfoObj.getPTHK()+","+
				TableDataSlabPlateInfoObj.getPWID()+","+
				TableDataSlabPlateInfoObj.getPLEN()+","+
				TableDataSlabPlateInfoObj.getPWET()+","+
				",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = TableDataVariableObj.getVAR1()+","+
				TableDataVariableObj.getVAR2()+","+
				TableDataVariableObj.getVAR3()+","+
				TableDataVariableObj.getVAR4()+","+
				TableDataVariableObj.getVAR5()+","+
				TableDataVariableObj.getVAR6()+","+
				TableDataVariableObj.getVAR7()+","+
				TableDataVariableObj.getVAR8()+","+
				TableDataVariableObj.getVAR9()+","+
				TableDataVariableObj.getVAR10()+","+
				TableDataVariableObj.getVAR11()+","+
				TableDataVariableObj.getVAR12()+","+
				TableDataVariableObj.getVAR13()+","+
				TableDataVariableObj.getVAR14()+","+
				TableDataVariableObj.getVAR15()+","+
				TableDataVariableObj.getVAR16();
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
				tableDataPLogList.get(0).getBUR_TDIA()+","+
				tableDataPLogList.get(1).getBUR_TDIA()+","+
				tableDataPLogList.get(2).getBUR_TDIA()+","+
				tableDataPLogList.get(3).getBUR_TDIA()+","+
				tableDataPLogList.get(4).getBUR_TDIA()+","+
				tableDataPLogList.get(5).getBUR_TDIA()+","+
				tableDataPLogList.get(6).getBUR_TDIA()+","+
				",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
				tableDataPLogList.get(0).getBUR_BDIA()+","+
				tableDataPLogList.get(1).getBUR_BDIA()+","+
				tableDataPLogList.get(2).getBUR_BDIA()+","+
				tableDataPLogList.get(3).getBUR_BDIA()+","+
				tableDataPLogList.get(4).getBUR_BDIA()+","+
				tableDataPLogList.get(5).getBUR_BDIA()+","+
				tableDataPLogList.get(6).getBUR_BDIA()+","+
				",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
				tableDataPLogList.get(0).getWR_TDIA()+","+
				tableDataPLogList.get(1).getWR_TDIA()+","+
				tableDataPLogList.get(2).getWR_TDIA()+","+
				tableDataPLogList.get(3).getWR_TDIA()+","+
				tableDataPLogList.get(4).getWR_TDIA()+","+
				tableDataPLogList.get(5).getWR_TDIA()+","+
				tableDataPLogList.get(6).getWR_TDIA()+","+
				",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
				tableDataPLogList.get(0).getWR_BDIA()+","+
				tableDataPLogList.get(1).getWR_BDIA()+","+
				tableDataPLogList.get(2).getWR_BDIA()+","+
				tableDataPLogList.get(3).getWR_BDIA()+","+
				tableDataPLogList.get(4).getWR_BDIA()+","+
				tableDataPLogList.get(5).getWR_BDIA()+","+
				tableDataPLogList.get(6).getWR_BDIA()+","+
				",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
				tableDataPLogList.get(0).getWR_ICRN()+","+
				tableDataPLogList.get(1).getWR_ICRN()+","+
				tableDataPLogList.get(2).getWR_ICRN()+","+
				tableDataPLogList.get(3).getWR_ICRN()+","+
				tableDataPLogList.get(4).getWR_ICRN()+","+
				tableDataPLogList.get(5).getWR_ICRN()+","+
				tableDataPLogList.get(6).getWR_ICRN()+","+
				",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
				tableDataPLogList.get(0).getENTRY_THK()+","+
				tableDataPLogList.get(1).getENTRY_THK()+","+
				tableDataPLogList.get(2).getENTRY_THK()+","+
				tableDataPLogList.get(3).getENTRY_THK()+","+
				tableDataPLogList.get(4).getENTRY_THK()+","+
				tableDataPLogList.get(5).getENTRY_THK()+","+
				tableDataPLogList.get(6).getENTRY_THK()+","+
				",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
				tableDataPLogList.get(0).getEXIT_THK()+","+
				tableDataPLogList.get(1).getEXIT_THK()+","+
				tableDataPLogList.get(2).getEXIT_THK()+","+
				tableDataPLogList.get(3).getEXIT_THK()+","+
				tableDataPLogList.get(4).getEXIT_THK()+","+
				tableDataPLogList.get(5).getEXIT_THK()+","+
				tableDataPLogList.get(6).getEXIT_THK()+","+
				",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
				tableDataPLogList.get(0).getPAS_LINE()+","+
				tableDataPLogList.get(1).getPAS_LINE()+","+
				tableDataPLogList.get(2).getPAS_LINE()+","+
				tableDataPLogList.get(3).getPAS_LINE()+","+
				tableDataPLogList.get(4).getPAS_LINE()+","+
				tableDataPLogList.get(5).getPAS_LINE()+","+
				tableDataPLogList.get(6).getPAS_LINE()+","+
				",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
				tableDataPLogList.get(0).getROL_GAP()+","+
				tableDataPLogList.get(1).getROL_GAP()+","+
				tableDataPLogList.get(2).getROL_GAP()+","+
				tableDataPLogList.get(3).getROL_GAP()+","+
				tableDataPLogList.get(4).getROL_GAP()+","+
				tableDataPLogList.get(5).getROL_GAP()+","+
				tableDataPLogList.get(6).getROL_GAP()+","+
				",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
				tableDataPLogList.get(0).getSTP_WID()+","+
				tableDataPLogList.get(1).getSTP_WID()+","+
				tableDataPLogList.get(2).getSTP_WID()+","+
				tableDataPLogList.get(3).getSTP_WID()+","+
				tableDataPLogList.get(4).getSTP_WID()+","+
				tableDataPLogList.get(5).getSTP_WID()+","+
				tableDataPLogList.get(6).getSTP_WID()+","+
				",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
				tableDataPLogList.get(0).getSTP_LEN()+","+
				tableDataPLogList.get(1).getSTP_LEN()+","+
				tableDataPLogList.get(2).getSTP_LEN()+","+
				tableDataPLogList.get(3).getSTP_LEN()+","+
				tableDataPLogList.get(4).getSTP_LEN()+","+
				tableDataPLogList.get(5).getSTP_LEN()+","+
				tableDataPLogList.get(6).getSTP_LEN()+","+
				",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
				tableDataPLogList.get(0).getENTRY_TEMP()+","+
				tableDataPLogList.get(1).getENTRY_TEMP()+","+
				tableDataPLogList.get(2).getENTRY_TEMP()+","+
				tableDataPLogList.get(3).getENTRY_TEMP()+","+
				tableDataPLogList.get(4).getENTRY_TEMP()+","+
				tableDataPLogList.get(5).getENTRY_TEMP()+","+
				tableDataPLogList.get(6).getENTRY_TEMP()+","+
				",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
				tableDataPLogList.get(0).getEXIT_TEMP()+","+
				tableDataPLogList.get(1).getEXIT_TEMP()+","+
				tableDataPLogList.get(2).getEXIT_TEMP()+","+
				tableDataPLogList.get(3).getEXIT_TEMP()+","+
				tableDataPLogList.get(4).getEXIT_TEMP()+","+
				tableDataPLogList.get(5).getEXIT_TEMP()+","+
				tableDataPLogList.get(6).getEXIT_TEMP()+","+
				",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
				tableDataPLogList.get(0).getFRCE()+","+
				tableDataPLogList.get(1).getFRCE()+","+
				tableDataPLogList.get(2).getFRCE()+","+
				tableDataPLogList.get(3).getFRCE()+","+
				tableDataPLogList.get(4).getFRCE()+","+
				tableDataPLogList.get(5).getFRCE()+","+
				tableDataPLogList.get(6).getFRCE()+","+					
				",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
				tableDataPLogList.get(0).getTORQ()+","+					
				tableDataPLogList.get(1).getTORQ()+","+
				tableDataPLogList.get(2).getTORQ()+","+
				tableDataPLogList.get(3).getTORQ()+","+
				tableDataPLogList.get(4).getTORQ()+","+
				tableDataPLogList.get(5).getTORQ()+","+
				tableDataPLogList.get(6).getTORQ()+","+
				",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
				tableDataPLogList.get(0).getSPEED()+","+
				tableDataPLogList.get(1).getSPEED()+","+
				tableDataPLogList.get(2).getSPEED()+","+
				tableDataPLogList.get(3).getSPEED()+","+
				tableDataPLogList.get(4).getSPEED()+","+
				tableDataPLogList.get(5).getSPEED()+","+
				tableDataPLogList.get(6).getSPEED()+","+					
				",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
				tableDataPLogList.get(0).getBEND()+","+
				tableDataPLogList.get(1).getBEND()+","+
				tableDataPLogList.get(2).getBEND()+","+
				tableDataPLogList.get(3).getBEND()+","+
				tableDataPLogList.get(4).getBEND()+","+
				tableDataPLogList.get(5).getBEND()+","+
				tableDataPLogList.get(6).getBEND()+","+
				",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
				tableDataPLogList.get(0).getP_CROSS()+","+
				tableDataPLogList.get(1).getP_CROSS()+","+
				tableDataPLogList.get(2).getP_CROSS()+","+
				tableDataPLogList.get(3).getP_CROSS()+","+
				tableDataPLogList.get(4).getP_CROSS()+","+
				tableDataPLogList.get(5).getP_CROSS()+","+
				tableDataPLogList.get(6).getP_CROSS()+","+
				",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
				tableDataPLogList.get(0).getTENS()+","+
				tableDataPLogList.get(1).getTENS()+","+
				tableDataPLogList.get(2).getTENS()+","+
				tableDataPLogList.get(3).getTENS()+","+
				tableDataPLogList.get(4).getTENS()+","+
				tableDataPLogList.get(5).getTENS()+","+
				tableDataPLogList.get(6).getTENS()+","+
				",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
				tableDataPLogList.get(0).getROL_TIM()+","+
				tableDataPLogList.get(1).getROL_TIM()+","+
				tableDataPLogList.get(2).getROL_TIM()+","+
				tableDataPLogList.get(3).getROL_TIM()+","+
				tableDataPLogList.get(4).getROL_TIM()+","+
				tableDataPLogList.get(5).getROL_TIM()+","+
				tableDataPLogList.get(6).getROL_TIM()+","+					
				",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
				tableDataPLogList.get(0).getIDL_TIM()+","+
				tableDataPLogList.get(1).getIDL_TIM()+","+
				tableDataPLogList.get(2).getIDL_TIM()+","+
				tableDataPLogList.get(3).getIDL_TIM()+","+
				tableDataPLogList.get(4).getIDL_TIM()+","+
				tableDataPLogList.get(5).getIDL_TIM()+","+
				tableDataPLogList.get(6).getIDL_TIM()+","+
				",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
				tableDataPLogList.get(0).getBUR_WEAR()+","+
				tableDataPLogList.get(1).getBUR_WEAR()+","+
				tableDataPLogList.get(2).getBUR_WEAR()+","+
				tableDataPLogList.get(3).getBUR_WEAR()+","+
				tableDataPLogList.get(4).getBUR_WEAR()+","+
				tableDataPLogList.get(5).getBUR_WEAR()+","+
				tableDataPLogList.get(6).getBUR_WEAR()+","+					
				",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
				tableDataPLogList.get(0).getWR_WEAR()+","+
				tableDataPLogList.get(1).getWR_WEAR()+","+
				tableDataPLogList.get(2).getWR_WEAR()+","+
				tableDataPLogList.get(3).getWR_WEAR()+","+
				tableDataPLogList.get(4).getWR_WEAR()+","+
				tableDataPLogList.get(5).getWR_WEAR()+","+
				tableDataPLogList.get(6).getWR_WEAR()+","+
				",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
				tableDataPLogList.get(0).getWR_THRM()+","+
				tableDataPLogList.get(1).getWR_THRM()+","+
				tableDataPLogList.get(2).getWR_THRM()+","+
				tableDataPLogList.get(3).getWR_THRM()+","+
				tableDataPLogList.get(4).getWR_THRM()+","+
				tableDataPLogList.get(5).getWR_THRM()+","+
				tableDataPLogList.get(6).getWR_THRM()+","+					
				",,,,,,,";
		initDataList.add(line31);
		String csvPath = myUtil.setPath(this.workspace, this.modelName +".csv");
		Writer obj = new Writer(csvPath);
		obj.running(initDataList);
	}

	//
	//
	//=====================================================================
	
	
	//=====================================================================
	// Button - Apply
	//
	public void Apply(){
		ApplyDlg applyDlg = new ApplyDlg(Display.getCurrent().getActiveShell());
		applyDlg.open();
	}
	
	public void RunApplyResult(ArrayList<Boolean> result){
		this.ApplyResult.clear();
		this.ApplyResult = result;
		for(int i=0;i<7;i++){
			if(ApplyResult.get(i)){
				if(i == 0) makeResultF1();
				if(i == 1) makeResultF2();
				if(i == 2) makeResultF3();
				if(i == 3) makeResultF4();
				if(i == 4) makeResultF5();
				if(i == 5) makeResultF6();
				if(i == 6) makeResultF7();
			}
		}
		// DB File ����
		this.RunSaveProject();
		// CSV ���� ����
		this.ExportPLog();
		
		this.checkResultFiles_All();
		
		if(myUtil.checkOS().equals("window")){
			try{
				String procPath = myUtil.setPath(this.workspace, FolderTree.folderName_proc);
				Runtime.getRuntime().exec("explorer "+ procPath);
			}catch(Exception e){
				String msg2 = "ERROR - Open Proc Folder";
				MessageDlg messageDlg2 = new MessageDlg(Display.getCurrent().getActiveShell(),msg2);
				messageDlg2.open();
			}
		}
	}
	
	private void checkResultFiles_All(){
		this.exportResult = "";
		this.exportResult = "[Export Result]"+"\n";
		//String userConfigPath = myUtil.setPath(System.getProperty("user.dir"), "userConfig");
		//String configFolder_con = myUtil.setPath(userConfigPath, "filelist_con");
		//String configFolder_ind = myUtil.setPath(userConfigPath, "filelist_ind");
		String configFolder_con = FolderTree.folderPath_Config_fileList_con;
		String configFolder_ind = FolderTree.folderPath_Config_fileList_ind;
		
		
		String configPath = "";
		if(this.getApplyType().equals(this.ApplyType_Consequent)){
			configPath = configFolder_con;
		}else{
			configPath = configFolder_ind;
		}
		
		
		for(int i=0;i<7;i++){
			if(ApplyResult.get(i)){
				if(i == 0){
					this.exportResult += "* F1"+"\n";
					String fileListPath = myUtil.setPath(configPath, FolderTree.fileName_f1FileList);
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f1.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F1),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "SKIP - "+filePath+"\n";
						}
					}
				}else if(i == 1){
					String fileListPath = myUtil.setPath(configPath, FolderTree.fileName_f2FileList);
					this.exportResult += "* F2"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f2.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F2),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "SKIP - "+filePath+"\n";
						}
					}
				}else if(i == 2){
					String fileListPath = myUtil.setPath(configPath, FolderTree.fileName_f3FileList);
					this.exportResult += "* F3"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f3.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace,FolderTree.folderName_proc),FolderTree.folderName_F3),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "SKIP - "+filePath+"\n";
						}
					}
				}else if(i == 3){
					String fileListPath = myUtil.setPath(configPath, FolderTree.fileName_f4FileList);
					this.exportResult += "* F4"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f4.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F4),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "SKIP - "+filePath+"\n";
						}
					}
				}else if(i == 4){
					String fileListPath = myUtil.setPath(configPath, FolderTree.fileName_f5FileList);
					this.exportResult += "* F5"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f5.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F5),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "SKIP - "+filePath+"\n";
						}
					}
				}else if(i == 5){
					String fileListPath = myUtil.setPath(configPath, FolderTree.fileName_f6FileList);
					this.exportResult += "* F6"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f6.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F6),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "SKIP - "+filePath+"\n";
						}
					}
				}else if(i == 6){
					String fileListPath = myUtil.setPath(configPath, FolderTree.fileName_f7FileList);
					this.exportResult += "* F7"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f7.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F7),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "SKIP - "+filePath+"\n";
						}
					}
				}
			}
		}
		
		String dbFile = myUtil.setPath(this.workspace, this.modelName+".ens");
		this.exportResult += "* DB file"+"\n";
		if(myUtil.checkPath(dbFile)){
			this.exportResult += "SUCCESS - "+dbFile+"\n";
		}else{
			this.exportResult += "FAIL - "+dbFile+"\n";
		}
		
		String csvFile = myUtil.setPath(this.workspace, this.modelName+".csv");
		this.exportResult +="* P Log file"+"\n";
		if(myUtil.checkPath(csvFile)){
			this.exportResult += "SUCCESS - "+csvFile+"\n";
		}else{
			this.exportResult += "FAIL - "+csvFile+"\n";
		}
		
		MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),exportResult);
		messageDlg.open();
	}
	
	private void makeResultF1(){
		ProcMaker procObj1 = new ProcMaker(UILabel.F1);
		procObj1.running();		
	}
	private void makeResultF2(){
		ProcMaker procObj2 = new ProcMaker(UILabel.F2);
		procObj2.running();
	}
	private void makeResultF3(){
		ProcMaker procObj3 = new ProcMaker(UILabel.F3);
		procObj3.running();
	}
	private void makeResultF4(){
		ProcMaker procObj4 = new ProcMaker(UILabel.F4);
		procObj4.running();
	}
	private void makeResultF5(){
		ProcMaker procObj5 = new ProcMaker(UILabel.F5);
		procObj5.running();
	}
	private void makeResultF6(){
		ProcMaker procObj6 = new ProcMaker(UILabel.F6);
		procObj6.running();
	}
	private void makeResultF7(){
		ProcMaker procObj7 = new ProcMaker(UILabel.F7);
		procObj7.running();
	}
	//
	// 
	//=====================================================================

	//=====================================================================
	// FileMenu - New Project
	//
	public void NewProject(){
		NewDlg newDlg = new NewDlg(Display.getCurrent().getActiveShell());
		newDlg.open();
	}
	
	public void RunNewProject(String modelName, String workspace){
		// NewDlg -> MC
		
		this.modelName = modelName;
		String topFolder = myUtil.setPath(workspace, this.modelName);
		String procFolder = myUtil.setPath(topFolder, "proc");
		String resultFolder = myUtil.setPath(topFolder, "result");
		this.workspace = topFolder;
		
		if(!myUtil.makeDir(topFolder)) System.out.println("[Model Name:"+modelName +"] topFolder did not make.");
		if(!myUtil.makeDir(procFolder)) System.out.println("[Model Name:"+modelName +"] prodFolder did not make.");
		if(!myUtil.makeDir(resultFolder)) System.out.println("[Model Name:"+modelName +"] resultFolder did not make.");
		
		this.setUpDataSheet();
		this.AllComponentEnable();
		this.InitComponentValue();
		
		med.getLblModelNameValue().setText(this.modelName);
		med.getLblWorkspacePath().setText(this.workspace);
		
		this.calcAllData("full");
		
	}
	
	private void AllComponentEnable(){
		med.getTabFolder().setEnabled(true);
		med.getBtnApply().setEnabled(true);
	}
	
	private void CleanAllData(){
		this.setUpDataSheet();
		this.AllComponentEnable();
		this.InitComponentValue();
	}
	
	//F1~F7���� �Է��ؼ� ������ �����Ҷ� Obj ���ϴ� ��
	private void setUpDataSheet(){
		this.tableDataPLogList = new ArrayList<TableData_PLog>();
		for(int i=0;i<7;i++){
			TableData_PLog obj = new TableData_PLog();
			obj.setSTAND("F"+(i+1));
			this.tableDataPLogList.add(obj);
		}
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Open Project
	//
	public void OpenProject(){
		OpenDlg openDlg = new OpenDlg(Display.getCurrent().getActiveShell());
		openDlg.open();
	}
	
	public void RunOpenProject(String DBFilePath){
		//System.out.println(DBFilePath);
		this.CleanAllData();	//InitValue�� �ʱ�ȭ
		this.readDBFile(DBFilePath);
		this.calcAllData("full");
	}
	
	private void readDBFile(String DBFilePath){
		
		String path = myUtil.getParentPath(DBFilePath);
		String fileName = myUtil.getFileName(DBFilePath);
		this.modelName = ParserDefault.splitLineData(fileName, "\\.").get(0);
		
		if(myUtil.getFileName(path).equals(this.modelName)){
			String procFolder = myUtil.setPath(path, "proc");
			String resultFolder = myUtil.setPath(path, "result");
			
			if(!myUtil.checkPath(procFolder)){
				myUtil.makeDir(procFolder);
			}
			if(!myUtil.checkPath(resultFolder)){
				myUtil.makeDir(resultFolder);
			}
			
			this.workspace = path;
			//System.out.println(this.workspace);
			
			med.getLblModelNameValue().setText(this.modelName);
			med.getLblWorkspacePath().setText(this.workspace);
			setAllDataUI(DBFilePath);
			AllComponentEnable();
			
		}else {
			String topFolder =  myUtil.setPath(path, this.modelName);
			String procFolder = myUtil.setPath(topFolder, "proc");
			String resultFolder = myUtil.setPath(topFolder, "result");
			String newDBFilePath = myUtil.setPath(topFolder, this.modelName+".ens");
			this.workspace = topFolder;
			
			myUtil.makeDir(topFolder);
			myUtil.makeDir(procFolder);
			myUtil.makeDir(resultFolder);
			myUtil.fileCopy(DBFilePath, newDBFilePath);
			
			med.getLblModelNameValue().setText(this.modelName);
			med.getLblWorkspacePath().setText(this.workspace);
			setAllDataUI(newDBFilePath);
			AllComponentEnable();
		}
	}
	
	private void setAllDataUI(String DBFilePath){
		
		Reader obj = new Reader(DBFilePath);
		obj.running();

		Map<String,String> openDBMap = new HashMap<String,String>();
		ArrayList<String> parsingDataList;
		
		for(String line : obj.getFileDataList()){
			parsingDataList = ParserDefault.splitLineData(line, "=");
			if(parsingDataList.size()==1){
				openDBMap.put(parsingDataList.get(0), " ");
			}else{
				openDBMap.put(parsingDataList.get(0), parsingDataList.get(1));
			}
		}
		
		
		for(TableData_PLog Pobj : this.tableDataPLogList){
			Pobj.setWR_TDIA(openDBMap.get(UILabel.Top_WR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setWR_BDIA(openDBMap.get(UILabel.Bottom_WR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setWR_ICRN(openDBMap.get(UILabel.WR_Crown+"_"+Pobj.getSTAND()));
			Pobj.setWr_len(openDBMap.get(UILabel.WR_Length+"_"+Pobj.getSTAND()));
			Pobj.setWr_div_angle(openDBMap.get(UILabel.WR_Mesh_Angle+"_"+Pobj.getSTAND()));
			
			Pobj.setBUR_TDIA(openDBMap.get(UILabel.Top_BUR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setBUR_BDIA(openDBMap.get(UILabel.Bottom_BUR_Diameter+"_"+Pobj.getSTAND()));
			Pobj.setBur_len(openDBMap.get(UILabel.BUR_Length+"_"+Pobj.getSTAND()));
			Pobj.setBur_div_angle(openDBMap.get(UILabel.BUR_Mesh_Angle+"_"+Pobj.getSTAND()));
			
			Pobj.setENTRY_THK(openDBMap.get(UILabel.Thickness+"_"+Pobj.getSTAND()));
			Pobj.setSTP_WID(openDBMap.get(UILabel.Width+"_"+Pobj.getSTAND()));
			Pobj.setSTP_LEN(openDBMap.get(UILabel.Length+"_"+Pobj.getSTAND()));
			Pobj.setENTRY_TEMP(openDBMap.get(UILabel.Entry_Temperature+"_"+Pobj.getSTAND()));
			Pobj.setEXIT_TEMP(openDBMap.get(UILabel.Exit_Temperature+"_"+Pobj.getSTAND()));
			Pobj.setP_in(openDBMap.get(UILabel.Initial_Position+"_"+Pobj.getSTAND()));
			Pobj.setPl_m(openDBMap.get(UILabel.Mesh_Length+"_"+Pobj.getSTAND()));
			Pobj.setT_div(openDBMap.get(UILabel.Thickness_Mesh_Divisions+"_"+Pobj.getSTAND()));
			
			Pobj.setSPEED(openDBMap.get(UILabel.Velocity+"_"+Pobj.getSTAND()));
			Pobj.setROL_GAP(openDBMap.get(UILabel.Roll_Gap+"_"+Pobj.getSTAND()));
			Pobj.setPAS_LINE(openDBMap.get(UILabel.Pass_Line+"_"+Pobj.getSTAND()));
			Pobj.setP_CROSS(openDBMap.get(UILabel.Pair_Cross_Angle+"_"+Pobj.getSTAND()));
			Pobj.setBEND(openDBMap.get(UILabel.Bender_Force+"_"+Pobj.getSTAND()));
			Pobj.setTORQ(openDBMap.get(UILabel.Roll_Torque+"_"+Pobj.getSTAND()));
			Pobj.setTENS(openDBMap.get(UILabel.Tension_Stress+"_"+Pobj.getSTAND()));
			Pobj.setF_r2p(openDBMap.get(UILabel.Roll_to_Plate_Frict_Coef+"_"+Pobj.getSTAND()));
			Pobj.setF_r2r(openDBMap.get(UILabel.Roll_to_Roll_Fric_Coef+"_"+Pobj.getSTAND()));
			
			Pobj.setSpeed_different_ratio_top_roll(openDBMap.get(UILabel.Speed_Different_Ratio_top_roll+"_"+Pobj.getSTAND()));
			Pobj.setSpeed_different_ratio_bottom_roll(openDBMap.get(UILabel.Speed_Different_Ratio_bottom_roll+"_"+Pobj.getSTAND()));
			Pobj.setWr_trot(openDBMap.get(UILabel.Top_WR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			Pobj.setWr_brot(openDBMap.get(UILabel.Bottom_BUR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			Pobj.setBur_trot(openDBMap.get(UILabel.Top_BUR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			Pobj.setBur_brot(openDBMap.get(UILabel.Bottom_BUR_Rot_Vel_RPM+"_"+Pobj.getSTAND()));
			
			Pobj.setYM_Constant(openDBMap.get("YM_Constant"+"_"+Pobj.getSTAND()));
			Pobj.setYM_Table(openDBMap.get("YM_Table"+"_"+Pobj.getSTAND()));
			Pobj.setYM_Value(openDBMap.get(UILabel.Youngs_Modulus+"_"+Pobj.getSTAND()));
			Pobj.setYM_Value_T(openDBMap.get(UILabel.Youngs_Modulus+"_T_"+Pobj.getSTAND()));
			
			Pobj.setTEC_Constant(openDBMap.get("TEC_Constant"+"_"+Pobj.getSTAND()));
			Pobj.setTEC_Table(openDBMap.get("TEC_Table"+"_"+Pobj.getSTAND()));
			Pobj.setTEC_Value(openDBMap.get(UILabel.Thermal_Expansion_Coefficient+"_"+Pobj.getSTAND()));
			Pobj.setTEC_Value_T(openDBMap.get(UILabel.Thermal_Expansion_Coefficient+"_T_"+Pobj.getSTAND()));
			
			Pobj.setPR_Constant(openDBMap.get("PR_Constant"+"_"+Pobj.getSTAND()));
			Pobj.setPR_Table(openDBMap.get("PR_Table"+"_"+Pobj.getSTAND()));
			Pobj.setPR_Value(openDBMap.get(UILabel.Poissons_Ratio+"_"+Pobj.getSTAND()));
			Pobj.setPR_Value_T(openDBMap.get(UILabel.Poissons_Ratio+"_T_"+Pobj.getSTAND()));
			                                 //  FS_Constant
			Pobj.setFS_Constant(openDBMap.get("FS_Constant"+"_"+Pobj.getSTAND()));
			Pobj.setFS_Table(openDBMap.get("FS_Table"+"_"+Pobj.getSTAND()));
			Pobj.setFS_Value(openDBMap.get(UILabel.Flow_Stress+"_"+Pobj.getSTAND()));
			Pobj.setYS_Value(openDBMap.get(UILabel.Yield_Strength+"_"+Pobj.getSTAND()));
			Pobj.setTS_Value(openDBMap.get(UILabel.Tensile_Strength+"_"+Pobj.getSTAND()));
			Pobj.setE_Value(openDBMap.get(UILabel.Elongation+"_"+Pobj.getSTAND()));
			
			Pobj.setMD_Value(openDBMap.get(UILabel.Mass_Density+"_"+Pobj.getSTAND()));
			
			Pobj.setLcase_time(openDBMap.get(UILabel.Analysis_Time+"_"+Pobj.getSTAND()));
			Pobj.setlcase_inc(openDBMap.get(UILabel.No_of_Inc+"_"+Pobj.getSTAND()));
			Pobj.setPost_inc(openDBMap.get(UILabel.Post_Writing_frequency+"_"+Pobj.getSTAND()));
			Pobj.setlcase_dt(openDBMap.get(UILabel.Time_Increment+"_"+Pobj.getSTAND()));
			Pobj.setParallelDDM(openDBMap.get(UILabel.Parallel_DDM+"_"+Pobj.getSTAND()));
			Pobj.setDomain(openDBMap.get(UILabel.Domain+"_"+Pobj.getSTAND()));
			Pobj.setParallelMultiThread(openDBMap.get(UILabel.Parallel_Multi_Thread+"_"+Pobj.getSTAND()));
			Pobj.setThread(openDBMap.get(UILabel.Thread+"_"+Pobj.getSTAND()));
			
			Pobj.setFRCE(openDBMap.get("FRCE"+"_"+Pobj.getSTAND()));
			Pobj.setEXIT_THK(openDBMap.get("EXIT THK"+"_"+Pobj.getSTAND()));
			Pobj.setROL_TIM(openDBMap.get("ROL TIM"+"_"+Pobj.getSTAND()));
			Pobj.setIDL_TIM(openDBMap.get("IDL TIM"+"_"+Pobj.getSTAND()));
			Pobj.setBUR_WEAR(openDBMap.get("BUR WEAR"+"_"+Pobj.getSTAND()));
			Pobj.setWR_WEAR(openDBMap.get("WR WEAR"+"_"+Pobj.getSTAND()));
			Pobj.setWR_THRM(openDBMap.get("WR THRM"+"_"+Pobj.getSTAND()));
			
			
		}
		this.initPLogTables_open(openDBMap);
		
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Save Project
	//
	public void SaveProject(){
		SaveDlg saveDlg = new SaveDlg(Display.getCurrent().getActiveShell());
		saveDlg.open();
	}
	
	public void RunSaveProject(){
		//System.out.println("Save Project");
		// Create DB File => modelName.ens
		ArrayList<String> outputDBList = new ArrayList<String>();
		String DBPath = myUtil.setPath(this.workspace, this.modelName+".ens");
		outputDBList.add("###################");
		outputDBList.add("### ENS DB FILE ###");
		outputDBList.add("###################");
		//outputDBList.add("Model Name="+this.modelName);
		//outputDBList.add("Workspace="+this.workspace);
		try{
			for(String line1 : this.TableDataSlabPlateInfoObj.getDB()){
				outputDBList.add(line1);
			}
			for(String line2 : this.TableDataVariableObj.getDB()){
				outputDBList.add(line2);
			}
			for(TableData_PLog obj : this.tableDataPLogList){
				for(String line3 : obj.getDB()){
					outputDBList.add(line3);
				}
			}
			for(String line4 : this.tableDataPLogList.get(0).getCommonDB()){
				outputDBList.add(line4);
			}
			Writer obj = new Writer(DBPath);
			obj.running(outputDBList);
			/*
			String msg = "* [Save] DB File"+"\n";
			msg +=  "SUCCESS - "+DBPath+"\n";
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
			*/
		}catch(Exception e){
			String msg = "ERROR - Save Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
		}
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Save As Project
	//
	public void SaveAsProject(){
		SaveAsDlg saveAsDlg = new SaveAsDlg(Display.getCurrent().getActiveShell());
		saveAsDlg.open();
	}
	
	public void RunSaveAsProject(String newPath, String newModelName){
		// SaveAs DB File => modelName.ens 
		ArrayList<String> outputDBList = new ArrayList<String>();
		this.modelName = newModelName;
		String topFolder =  myUtil.setPath(newPath, newModelName);
		String procFolder = myUtil.setPath(topFolder, "proc");
		String resultFolder = myUtil.setPath(topFolder, "result");
		String newDBFilePath = myUtil.setPath(topFolder, newModelName+".ens");
		this.workspace = topFolder;
		
		med.getLblModelNameValue().setText(this.modelName);
		med.getLblWorkspacePath().setText(this.workspace);

		myUtil.makeDir(topFolder);
		myUtil.makeDir(procFolder);
		myUtil.makeDir(resultFolder);
		
		outputDBList.add("###################");
		outputDBList.add("### ENS DB FILE ###");
		outputDBList.add("###################");
		//outputDBList.add("Model Name="+this.modelName);
		//outputDBList.add("Workspace="+this.workspace);
		
		try{
			if(newDBFilePath != null){
				for(String line1 : this.TableDataSlabPlateInfoObj.getDB()){
					outputDBList.add(line1);
				}
				for(String line2 : this.TableDataVariableObj.getDB()){
					outputDBList.add(line2);
				}
				for(TableData_PLog obj : this.tableDataPLogList){
					for(String line3 : obj.getDB()){
						outputDBList.add(line3);
					}
				}
				for(String line4 : this.tableDataPLogList.get(0).getCommonDB()){
					outputDBList.add(line4);
				}
				Writer obj = new Writer(newDBFilePath);
				obj.running(outputDBList);
			}
			/*
			String msg = "*[Save As] DB File"+"\n";
			msg +=  "SUCCESS - "+newDBFilePath+"\n";
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
			*/
		}catch(Exception e){
			String msg = "ERROR - Save Data";
			msg = msg +"\n"+e.getMessage();
			MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),msg);
			messageDlg.open();
			Log.error(msg);
		}
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Export Project
	//
	public void ExportProject(){
		ExportDlg exportDlg = new ExportDlg(Display.getCurrent().getActiveShell());
		exportDlg.open();
	}
	
	public void RunExportProject(){
		//��� proc ��
		//System.out.println("Export");
		/*
		ProcMaker procObj1 = new ProcMaker(UILabel.F1);
		procObj1.running();
		ProcMaker procObj2 = new ProcMaker(UILabel.F2);
		procObj2.running();
		ProcMaker procObj3 = new ProcMaker(UILabel.F3);
		procObj3.running();
		ProcMaker procObj4 = new ProcMaker(UILabel.F4);
		procObj4.running();
		ProcMaker procObj5 = new ProcMaker(UILabel.F5);
		procObj5.running();
		ProcMaker procObj6 = new ProcMaker(UILabel.F6);
		procObj6.running();
		ProcMaker procObj7 = new ProcMaker(UILabel.F7);
		procObj7.running();
		*/
		this.makeResultF1();
		this.makeResultF2();
		this.makeResultF3();
		this.makeResultF4();
		this.makeResultF5();
		this.makeResultF6();
		this.makeResultF7();
		
		this.checkResultFiles_proc();
		if(myUtil.checkOS().equals("window")){
			try{
				String procPath = myUtil.setPath(this.workspace, "proc");
				Runtime.getRuntime().exec("explorer "+ procPath);
			}catch(Exception e){
				String msg2 = "ERROR - Open Proc Folder";
				MessageDlg messageDlg2 = new MessageDlg(Display.getCurrent().getActiveShell(),msg2);
				messageDlg2.open();
			}
		}
	}
	
	private void checkResultFiles_proc(){
		this.ApplyResult.clear();
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		this.ApplyResult.add(true);
		
		this.exportResult = "";
		this.exportResult = "[Export Reulst]"+"\n";
		//String userConfigPath = myUtil.setPath(System.getProperty("user.dir"), "userConfig");
		String userConfigPath = FolderTree.folderPath_Config;
		
		for(int i=0;i<7;i++){
			if(ApplyResult.get(i)){
				if(i == 0){
					this.exportResult += "* F1"+"\n";
					String fileListPath = myUtil.setPath(userConfigPath, FolderTree.fileName_f1FileList);
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f1.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F1),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 1){
					String fileListPath = myUtil.setPath(userConfigPath, FolderTree.fileName_f2FileList);
					this.exportResult += "* F2"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f2.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F2),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 2){
					String fileListPath = myUtil.setPath(userConfigPath, FolderTree.fileName_f3FileList);
					this.exportResult += "* F3"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f3.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F3),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 3){
					String fileListPath = myUtil.setPath(userConfigPath, FolderTree.fileName_f4FileList);
					this.exportResult += "* F4"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f4.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F4),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 4){
					String fileListPath = myUtil.setPath(userConfigPath, FolderTree.fileName_f5FileList);
					this.exportResult += "* F5"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f5.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F5),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 5){
					String fileListPath = myUtil.setPath(userConfigPath, FolderTree.fileName_f6FileList);
					this.exportResult += "* F6"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f6.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F6),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}else if(i == 6){
					String fileListPath = myUtil.setPath(userConfigPath, FolderTree.fileName_f7FileList);
					this.exportResult += "* F7"+"\n";
					Reader obj = new Reader(fileListPath);
					obj.running();
					for(int j = 0; j<obj.getFileDataList().size();j++){
						String fileName = "";
						if(j==0){
							fileName = "00_main_"+this.modelName+"_gen_f7.proc";
						}else{
							fileName = obj.getFileDataList().get(j);
						}
						String filePath = myUtil.setPath(myUtil.setPath(myUtil.setPath(this.workspace, FolderTree.folderName_proc),FolderTree.folderName_F7),fileName);
						if(myUtil.checkPath(filePath)){
							this.exportResult += "SUCCESS - "+filePath+"\n";
						}else{
							this.exportResult += "FAIL - "+filePath+"\n";
						}
					}
				}
			}
		}
		
		MessageDlg messageDlg = new MessageDlg(Display.getCurrent().getActiveShell(),exportResult);
		messageDlg.open();
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// File menu - Result Project
	//
	public void ResultProject(){
		ResultDlg resultDlg = new ResultDlg(Display.getCurrent().getActiveShell());
		resultDlg.open();
	}
	//
	//
	//=====================================================================
	
	//=====================================================================
	// ������ �κ�
	//
	
	private void InitComponentValue(){
		// New Project --> Call InitCompoenetValue
		this.initF7Values();
		this.saveAllF7Values();
		this.initF6Values();
		this.saveAllF6Values();
		this.initF5Values();
		this.saveAllF5Values();
		this.initF4Values();
		this.saveAllF4Values();
		this.initF3Values();
		this.saveAllF3Values();
		this.initF2Values();
		this.saveAllF2Values();
		this.initF1Values();
		this.saveAllF1Values();
	
		// initvalue -> PLog table ����ȭ
		this.initPLogTables();
	}
	
	private void initPLogTables(){
		InitValue obj =new InitValue();
		obj.readInitValueFile();
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = obj.getInitValue(InitValue.STRIP_NO)+","+
						obj.getInitValue(InitValue.STHK)+","+
						obj.getInitValue(InitValue.SWID)+","+
						obj.getInitValue(InitValue.SLEN)+","+
						obj.getInitValue(InitValue.SWET)+","+
						obj.getInitValue(InitValue.PTHK)+","+
						obj.getInitValue(InitValue.PWID)+","+
						obj.getInitValue(InitValue.PLEN)+","+
						obj.getInitValue(InitValue.PWET)+","+
						",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = obj.getInitValue(InitValue.VAR1)+","+
						obj.getInitValue(InitValue.VAR2)+","+
						obj.getInitValue(InitValue.VAR3)+","+
						obj.getInitValue(InitValue.VAR4)+","+
						obj.getInitValue(InitValue.VAR5)+","+
						obj.getInitValue(InitValue.VAR6)+","+
						obj.getInitValue(InitValue.VAR7)+","+
						obj.getInitValue(InitValue.VAR8)+","+
						obj.getInitValue(InitValue.VAR9)+","+
						obj.getInitValue(InitValue.VAR10)+","+
						obj.getInitValue(InitValue.VAR11)+","+
						obj.getInitValue(InitValue.VAR12)+","+
						obj.getInitValue(InitValue.VAR13)+","+
						obj.getInitValue(InitValue.VAR14)+","+
						obj.getInitValue(InitValue.VAR15)+","+
						obj.getInitValue(InitValue.VAR16);
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
					tableDataPLogList.get(0).getBUR_TDIA()+","+
					tableDataPLogList.get(1).getBUR_TDIA()+","+
					tableDataPLogList.get(2).getBUR_TDIA()+","+
					tableDataPLogList.get(3).getBUR_TDIA()+","+
					tableDataPLogList.get(4).getBUR_TDIA()+","+
					tableDataPLogList.get(5).getBUR_TDIA()+","+
					tableDataPLogList.get(6).getBUR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
					tableDataPLogList.get(0).getBUR_BDIA()+","+
					tableDataPLogList.get(1).getBUR_BDIA()+","+
					tableDataPLogList.get(2).getBUR_BDIA()+","+
					tableDataPLogList.get(3).getBUR_BDIA()+","+
					tableDataPLogList.get(4).getBUR_BDIA()+","+
					tableDataPLogList.get(5).getBUR_BDIA()+","+
					tableDataPLogList.get(6).getBUR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
					tableDataPLogList.get(0).getWR_TDIA()+","+
					tableDataPLogList.get(1).getWR_TDIA()+","+
					tableDataPLogList.get(2).getWR_TDIA()+","+
					tableDataPLogList.get(3).getWR_TDIA()+","+
					tableDataPLogList.get(4).getWR_TDIA()+","+
					tableDataPLogList.get(5).getWR_TDIA()+","+
					tableDataPLogList.get(6).getWR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
					tableDataPLogList.get(0).getWR_BDIA()+","+
					tableDataPLogList.get(1).getWR_BDIA()+","+
					tableDataPLogList.get(2).getWR_BDIA()+","+
					tableDataPLogList.get(3).getWR_BDIA()+","+
					tableDataPLogList.get(4).getWR_BDIA()+","+
					tableDataPLogList.get(5).getWR_BDIA()+","+
					tableDataPLogList.get(6).getWR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
					tableDataPLogList.get(0).getWR_ICRN()+","+
					tableDataPLogList.get(1).getWR_ICRN()+","+
					tableDataPLogList.get(2).getWR_ICRN()+","+
					tableDataPLogList.get(3).getWR_ICRN()+","+
					tableDataPLogList.get(4).getWR_ICRN()+","+
					tableDataPLogList.get(5).getWR_ICRN()+","+
					tableDataPLogList.get(6).getWR_ICRN()+","+
					",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
					tableDataPLogList.get(0).getENTRY_THK()+","+
					tableDataPLogList.get(1).getENTRY_THK()+","+
					tableDataPLogList.get(2).getENTRY_THK()+","+
					tableDataPLogList.get(3).getENTRY_THK()+","+
					tableDataPLogList.get(4).getENTRY_THK()+","+
					tableDataPLogList.get(5).getENTRY_THK()+","+
					tableDataPLogList.get(6).getENTRY_THK()+","+
					",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
					tableDataPLogList.get(0).getEXIT_THK()+","+
					tableDataPLogList.get(1).getEXIT_THK()+","+
					tableDataPLogList.get(2).getEXIT_THK()+","+
					tableDataPLogList.get(3).getEXIT_THK()+","+
					tableDataPLogList.get(4).getEXIT_THK()+","+
					tableDataPLogList.get(5).getEXIT_THK()+","+
					tableDataPLogList.get(6).getEXIT_THK()+","+
					",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
					tableDataPLogList.get(0).getPAS_LINE()+","+
					tableDataPLogList.get(1).getPAS_LINE()+","+
					tableDataPLogList.get(2).getPAS_LINE()+","+
					tableDataPLogList.get(3).getPAS_LINE()+","+
					tableDataPLogList.get(4).getPAS_LINE()+","+
					tableDataPLogList.get(5).getPAS_LINE()+","+
					tableDataPLogList.get(6).getPAS_LINE()+","+
					",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
					tableDataPLogList.get(0).getROL_GAP()+","+
					tableDataPLogList.get(1).getROL_GAP()+","+
					tableDataPLogList.get(2).getROL_GAP()+","+
					tableDataPLogList.get(3).getROL_GAP()+","+
					tableDataPLogList.get(4).getROL_GAP()+","+
					tableDataPLogList.get(5).getROL_GAP()+","+
					tableDataPLogList.get(6).getROL_GAP()+","+
					",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
					tableDataPLogList.get(0).getSTP_WID()+","+
					tableDataPLogList.get(1).getSTP_WID()+","+
					tableDataPLogList.get(2).getSTP_WID()+","+
					tableDataPLogList.get(3).getSTP_WID()+","+
					tableDataPLogList.get(4).getSTP_WID()+","+
					tableDataPLogList.get(5).getSTP_WID()+","+
					tableDataPLogList.get(6).getSTP_WID()+","+
					",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
					tableDataPLogList.get(0).getENTRY_TEMP()+","+
					tableDataPLogList.get(1).getENTRY_TEMP()+","+
					tableDataPLogList.get(2).getENTRY_TEMP()+","+
					tableDataPLogList.get(3).getENTRY_TEMP()+","+
					tableDataPLogList.get(4).getENTRY_TEMP()+","+
					tableDataPLogList.get(5).getENTRY_TEMP()+","+
					tableDataPLogList.get(6).getENTRY_TEMP()+","+
					",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
					tableDataPLogList.get(0).getEXIT_TEMP()+","+
					tableDataPLogList.get(1).getEXIT_TEMP()+","+
					tableDataPLogList.get(2).getEXIT_TEMP()+","+
					tableDataPLogList.get(3).getEXIT_TEMP()+","+
					tableDataPLogList.get(4).getEXIT_TEMP()+","+
					tableDataPLogList.get(5).getEXIT_TEMP()+","+
					tableDataPLogList.get(6).getEXIT_TEMP()+","+
					",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
					tableDataPLogList.get(0).getFRCE()+","+
					tableDataPLogList.get(1).getFRCE()+","+
					tableDataPLogList.get(2).getFRCE()+","+
					tableDataPLogList.get(3).getFRCE()+","+
					tableDataPLogList.get(4).getFRCE()+","+
					tableDataPLogList.get(5).getFRCE()+","+
					tableDataPLogList.get(6).getFRCE()+","+					
					",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
					tableDataPLogList.get(0).getTORQ()+","+					
					tableDataPLogList.get(1).getTORQ()+","+
					tableDataPLogList.get(2).getTORQ()+","+
					tableDataPLogList.get(3).getTORQ()+","+
					tableDataPLogList.get(4).getTORQ()+","+
					tableDataPLogList.get(5).getTORQ()+","+
					tableDataPLogList.get(6).getTORQ()+","+
					",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
					tableDataPLogList.get(0).getSPEED()+","+
					tableDataPLogList.get(1).getSPEED()+","+
					tableDataPLogList.get(2).getSPEED()+","+
					tableDataPLogList.get(3).getSPEED()+","+
					tableDataPLogList.get(4).getSPEED()+","+
					tableDataPLogList.get(5).getSPEED()+","+
					tableDataPLogList.get(6).getSPEED()+","+					
					",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
					tableDataPLogList.get(0).getBEND()+","+
					tableDataPLogList.get(1).getBEND()+","+
					tableDataPLogList.get(2).getBEND()+","+
					tableDataPLogList.get(3).getBEND()+","+
					tableDataPLogList.get(4).getBEND()+","+
					tableDataPLogList.get(5).getBEND()+","+
					tableDataPLogList.get(6).getBEND()+","+
					",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
					tableDataPLogList.get(0).getP_CROSS()+","+
					tableDataPLogList.get(1).getP_CROSS()+","+
					tableDataPLogList.get(2).getP_CROSS()+","+
					tableDataPLogList.get(3).getP_CROSS()+","+
					tableDataPLogList.get(4).getP_CROSS()+","+
					tableDataPLogList.get(5).getP_CROSS()+","+
					tableDataPLogList.get(6).getP_CROSS()+","+
					",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
					tableDataPLogList.get(0).getTENS()+","+
					tableDataPLogList.get(1).getTENS()+","+
					tableDataPLogList.get(2).getTENS()+","+
					tableDataPLogList.get(3).getTENS()+","+
					tableDataPLogList.get(4).getTENS()+","+
					tableDataPLogList.get(5).getTENS()+","+
					tableDataPLogList.get(6).getTENS()+","+
					",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
					tableDataPLogList.get(0).getROL_TIM()+","+
					tableDataPLogList.get(1).getROL_TIM()+","+
					tableDataPLogList.get(2).getROL_TIM()+","+
					tableDataPLogList.get(3).getROL_TIM()+","+
					tableDataPLogList.get(4).getROL_TIM()+","+
					tableDataPLogList.get(5).getROL_TIM()+","+
					tableDataPLogList.get(6).getROL_TIM()+","+					
					",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
					tableDataPLogList.get(0).getIDL_TIM()+","+
					tableDataPLogList.get(1).getIDL_TIM()+","+
					tableDataPLogList.get(2).getIDL_TIM()+","+
					tableDataPLogList.get(3).getIDL_TIM()+","+
					tableDataPLogList.get(4).getIDL_TIM()+","+
					tableDataPLogList.get(5).getIDL_TIM()+","+
					tableDataPLogList.get(6).getIDL_TIM()+","+
					",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
					tableDataPLogList.get(0).getBUR_WEAR()+","+
					tableDataPLogList.get(1).getBUR_WEAR()+","+
					tableDataPLogList.get(2).getBUR_WEAR()+","+
					tableDataPLogList.get(3).getBUR_WEAR()+","+
					tableDataPLogList.get(4).getBUR_WEAR()+","+
					tableDataPLogList.get(5).getBUR_WEAR()+","+
					tableDataPLogList.get(6).getBUR_WEAR()+","+					
					",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
					tableDataPLogList.get(0).getWR_WEAR()+","+
					tableDataPLogList.get(1).getWR_WEAR()+","+
					tableDataPLogList.get(2).getWR_WEAR()+","+
					tableDataPLogList.get(3).getWR_WEAR()+","+
					tableDataPLogList.get(4).getWR_WEAR()+","+
					tableDataPLogList.get(5).getWR_WEAR()+","+
					tableDataPLogList.get(6).getWR_WEAR()+","+
					",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
					tableDataPLogList.get(0).getWR_THRM()+","+
					tableDataPLogList.get(1).getWR_THRM()+","+
					tableDataPLogList.get(2).getWR_THRM()+","+
					tableDataPLogList.get(3).getWR_THRM()+","+
					tableDataPLogList.get(4).getWR_THRM()+","+
					tableDataPLogList.get(5).getWR_THRM()+","+
					tableDataPLogList.get(6).getWR_THRM()+","+					
					",,,,,,,";
		initDataList.add(line31);
		this.parsingPLogFile(initDataList);
	}
	
	private void initPLogTables_open(Map<String,String> openDBMap){
		
		InitValue obj =new InitValue();
		obj.readInitValueFile();
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = openDBMap.get(InitValue.STRIP_NO)+","+
						openDBMap.get(InitValue.STHK)+","+
						openDBMap.get(InitValue.SWID)+","+
						openDBMap.get(InitValue.SLEN)+","+
						openDBMap.get(InitValue.SWET)+","+
						openDBMap.get(InitValue.PTHK)+","+
						openDBMap.get(InitValue.PWID)+","+
						openDBMap.get(InitValue.PLEN)+","+
						openDBMap.get(InitValue.PWET)+","+
						",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = openDBMap.get(InitValue.VAR1)+","+
						openDBMap.get(InitValue.VAR2)+","+
						openDBMap.get(InitValue.VAR3)+","+
						openDBMap.get(InitValue.VAR4)+","+
						openDBMap.get(InitValue.VAR5)+","+
						openDBMap.get(InitValue.VAR6)+","+
						openDBMap.get(InitValue.VAR7)+","+
						openDBMap.get(InitValue.VAR8)+","+
						openDBMap.get(InitValue.VAR9)+","+
						openDBMap.get(InitValue.VAR10)+","+
						openDBMap.get(InitValue.VAR11)+","+
						openDBMap.get(InitValue.VAR12)+","+
						openDBMap.get(InitValue.VAR13)+","+
						openDBMap.get(InitValue.VAR14)+","+
						openDBMap.get(InitValue.VAR15)+","+
						openDBMap.get(InitValue.VAR16);
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
					tableDataPLogList.get(0).getBUR_TDIA()+","+
					tableDataPLogList.get(1).getBUR_TDIA()+","+
					tableDataPLogList.get(2).getBUR_TDIA()+","+
					tableDataPLogList.get(3).getBUR_TDIA()+","+
					tableDataPLogList.get(4).getBUR_TDIA()+","+
					tableDataPLogList.get(5).getBUR_TDIA()+","+
					tableDataPLogList.get(6).getBUR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
					tableDataPLogList.get(0).getBUR_BDIA()+","+
					tableDataPLogList.get(1).getBUR_BDIA()+","+
					tableDataPLogList.get(2).getBUR_BDIA()+","+
					tableDataPLogList.get(3).getBUR_BDIA()+","+
					tableDataPLogList.get(4).getBUR_BDIA()+","+
					tableDataPLogList.get(5).getBUR_BDIA()+","+
					tableDataPLogList.get(6).getBUR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
					tableDataPLogList.get(0).getWR_TDIA()+","+
					tableDataPLogList.get(1).getWR_TDIA()+","+
					tableDataPLogList.get(2).getWR_TDIA()+","+
					tableDataPLogList.get(3).getWR_TDIA()+","+
					tableDataPLogList.get(4).getWR_TDIA()+","+
					tableDataPLogList.get(5).getWR_TDIA()+","+
					tableDataPLogList.get(6).getWR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
					tableDataPLogList.get(0).getWR_BDIA()+","+
					tableDataPLogList.get(1).getWR_BDIA()+","+
					tableDataPLogList.get(2).getWR_BDIA()+","+
					tableDataPLogList.get(3).getWR_BDIA()+","+
					tableDataPLogList.get(4).getWR_BDIA()+","+
					tableDataPLogList.get(5).getWR_BDIA()+","+
					tableDataPLogList.get(6).getWR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
					tableDataPLogList.get(0).getWR_ICRN()+","+
					tableDataPLogList.get(1).getWR_ICRN()+","+
					tableDataPLogList.get(2).getWR_ICRN()+","+
					tableDataPLogList.get(3).getWR_ICRN()+","+
					tableDataPLogList.get(4).getWR_ICRN()+","+
					tableDataPLogList.get(5).getWR_ICRN()+","+
					tableDataPLogList.get(6).getWR_ICRN()+","+
					",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
					tableDataPLogList.get(0).getENTRY_THK()+","+
					tableDataPLogList.get(1).getENTRY_THK()+","+
					tableDataPLogList.get(2).getENTRY_THK()+","+
					tableDataPLogList.get(3).getENTRY_THK()+","+
					tableDataPLogList.get(4).getENTRY_THK()+","+
					tableDataPLogList.get(5).getENTRY_THK()+","+
					tableDataPLogList.get(6).getENTRY_THK()+","+
					",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
					tableDataPLogList.get(0).getEXIT_THK()+","+
					tableDataPLogList.get(1).getEXIT_THK()+","+
					tableDataPLogList.get(2).getEXIT_THK()+","+
					tableDataPLogList.get(3).getEXIT_THK()+","+
					tableDataPLogList.get(4).getEXIT_THK()+","+
					tableDataPLogList.get(5).getEXIT_THK()+","+
					tableDataPLogList.get(6).getEXIT_THK()+","+
					",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
					tableDataPLogList.get(0).getPAS_LINE()+","+
					tableDataPLogList.get(1).getPAS_LINE()+","+
					tableDataPLogList.get(2).getPAS_LINE()+","+
					tableDataPLogList.get(3).getPAS_LINE()+","+
					tableDataPLogList.get(4).getPAS_LINE()+","+
					tableDataPLogList.get(5).getPAS_LINE()+","+
					tableDataPLogList.get(6).getPAS_LINE()+","+
					",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
					tableDataPLogList.get(0).getROL_GAP()+","+
					tableDataPLogList.get(1).getROL_GAP()+","+
					tableDataPLogList.get(2).getROL_GAP()+","+
					tableDataPLogList.get(3).getROL_GAP()+","+
					tableDataPLogList.get(4).getROL_GAP()+","+
					tableDataPLogList.get(5).getROL_GAP()+","+
					tableDataPLogList.get(6).getROL_GAP()+","+
					",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
					tableDataPLogList.get(0).getSTP_WID()+","+
					tableDataPLogList.get(1).getSTP_WID()+","+
					tableDataPLogList.get(2).getSTP_WID()+","+
					tableDataPLogList.get(3).getSTP_WID()+","+
					tableDataPLogList.get(4).getSTP_WID()+","+
					tableDataPLogList.get(5).getSTP_WID()+","+
					tableDataPLogList.get(6).getSTP_WID()+","+
					",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
					tableDataPLogList.get(0).getENTRY_TEMP()+","+
					tableDataPLogList.get(1).getENTRY_TEMP()+","+
					tableDataPLogList.get(2).getENTRY_TEMP()+","+
					tableDataPLogList.get(3).getENTRY_TEMP()+","+
					tableDataPLogList.get(4).getENTRY_TEMP()+","+
					tableDataPLogList.get(5).getENTRY_TEMP()+","+
					tableDataPLogList.get(6).getENTRY_TEMP()+","+
					",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
					tableDataPLogList.get(0).getEXIT_TEMP()+","+
					tableDataPLogList.get(1).getEXIT_TEMP()+","+
					tableDataPLogList.get(2).getEXIT_TEMP()+","+
					tableDataPLogList.get(3).getEXIT_TEMP()+","+
					tableDataPLogList.get(4).getEXIT_TEMP()+","+
					tableDataPLogList.get(5).getEXIT_TEMP()+","+
					tableDataPLogList.get(6).getEXIT_TEMP()+","+
					",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
					tableDataPLogList.get(0).getFRCE()+","+
					tableDataPLogList.get(1).getFRCE()+","+
					tableDataPLogList.get(2).getFRCE()+","+
					tableDataPLogList.get(3).getFRCE()+","+
					tableDataPLogList.get(4).getFRCE()+","+
					tableDataPLogList.get(5).getFRCE()+","+
					tableDataPLogList.get(6).getFRCE()+","+					
					",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
					tableDataPLogList.get(0).getTORQ()+","+					
					tableDataPLogList.get(1).getTORQ()+","+
					tableDataPLogList.get(2).getTORQ()+","+
					tableDataPLogList.get(3).getTORQ()+","+
					tableDataPLogList.get(4).getTORQ()+","+
					tableDataPLogList.get(5).getTORQ()+","+
					tableDataPLogList.get(6).getTORQ()+","+
					",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
					tableDataPLogList.get(0).getSPEED()+","+
					tableDataPLogList.get(1).getSPEED()+","+
					tableDataPLogList.get(2).getSPEED()+","+
					tableDataPLogList.get(3).getSPEED()+","+
					tableDataPLogList.get(4).getSPEED()+","+
					tableDataPLogList.get(5).getSPEED()+","+
					tableDataPLogList.get(6).getSPEED()+","+					
					",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
					tableDataPLogList.get(0).getBEND()+","+
					tableDataPLogList.get(1).getBEND()+","+
					tableDataPLogList.get(2).getBEND()+","+
					tableDataPLogList.get(3).getBEND()+","+
					tableDataPLogList.get(4).getBEND()+","+
					tableDataPLogList.get(5).getBEND()+","+
					tableDataPLogList.get(6).getBEND()+","+
					",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
					tableDataPLogList.get(0).getP_CROSS()+","+
					tableDataPLogList.get(1).getP_CROSS()+","+
					tableDataPLogList.get(2).getP_CROSS()+","+
					tableDataPLogList.get(3).getP_CROSS()+","+
					tableDataPLogList.get(4).getP_CROSS()+","+
					tableDataPLogList.get(5).getP_CROSS()+","+
					tableDataPLogList.get(6).getP_CROSS()+","+
					",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
					tableDataPLogList.get(0).getTENS()+","+
					tableDataPLogList.get(1).getTENS()+","+
					tableDataPLogList.get(2).getTENS()+","+
					tableDataPLogList.get(3).getTENS()+","+
					tableDataPLogList.get(4).getTENS()+","+
					tableDataPLogList.get(5).getTENS()+","+
					tableDataPLogList.get(6).getTENS()+","+
					",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
					tableDataPLogList.get(0).getROL_TIM()+","+
					tableDataPLogList.get(1).getROL_TIM()+","+
					tableDataPLogList.get(2).getROL_TIM()+","+
					tableDataPLogList.get(3).getROL_TIM()+","+
					tableDataPLogList.get(4).getROL_TIM()+","+
					tableDataPLogList.get(5).getROL_TIM()+","+
					tableDataPLogList.get(6).getROL_TIM()+","+					
					",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
					tableDataPLogList.get(0).getIDL_TIM()+","+
					tableDataPLogList.get(1).getIDL_TIM()+","+
					tableDataPLogList.get(2).getIDL_TIM()+","+
					tableDataPLogList.get(3).getIDL_TIM()+","+
					tableDataPLogList.get(4).getIDL_TIM()+","+
					tableDataPLogList.get(5).getIDL_TIM()+","+
					tableDataPLogList.get(6).getIDL_TIM()+","+
					",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
					tableDataPLogList.get(0).getBUR_WEAR()+","+
					tableDataPLogList.get(1).getBUR_WEAR()+","+
					tableDataPLogList.get(2).getBUR_WEAR()+","+
					tableDataPLogList.get(3).getBUR_WEAR()+","+
					tableDataPLogList.get(4).getBUR_WEAR()+","+
					tableDataPLogList.get(5).getBUR_WEAR()+","+
					tableDataPLogList.get(6).getBUR_WEAR()+","+					
					",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
					tableDataPLogList.get(0).getWR_WEAR()+","+
					tableDataPLogList.get(1).getWR_WEAR()+","+
					tableDataPLogList.get(2).getWR_WEAR()+","+
					tableDataPLogList.get(3).getWR_WEAR()+","+
					tableDataPLogList.get(4).getWR_WEAR()+","+
					tableDataPLogList.get(5).getWR_WEAR()+","+
					tableDataPLogList.get(6).getWR_WEAR()+","+
					",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
					tableDataPLogList.get(0).getWR_THRM()+","+
					tableDataPLogList.get(1).getWR_THRM()+","+
					tableDataPLogList.get(2).getWR_THRM()+","+
					tableDataPLogList.get(3).getWR_THRM()+","+
					tableDataPLogList.get(4).getWR_THRM()+","+
					tableDataPLogList.get(5).getWR_THRM()+","+
					tableDataPLogList.get(6).getWR_THRM()+","+					
					",,,,,,,";
		initDataList.add(line31);
		
		
		this.parsingPLogFile(initDataList);
		
		TableData_PLog Pobj4 =    this.tableDataPLogList.get(0);
		
	}
	
	private void saveAllF1Values(){
		TableData_PLog obj = tableDataPLogList.get(0);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		obj.setWr_chamferX(med.getTextWRChamferX().getText());
		obj.setWr_chamferY(med.getTextWRChamferY().getText());
		obj.setWr_round(med.getTextWRRound().getText());

		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		obj.setBur_chamferX(med.getTextBURChamferX().getText());
		obj.setBur_chamferY(med.getTextBURChamferY().getText());

		
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		obj.setP_cr(med.getTextPlateCrown().getText());

		
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setSpeed_different_ratio_top_roll(med.getTextSpeedDifferentRatioTopRoll().getText());
		obj.setSpeed_different_ratio_bottom_roll(med.getTextSpeedDifferentRatioBottomRoll().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		// Roll Material Parameter
		obj.setYM_Roll_constant(med.getTextRollYoungsModulus().getText());
		obj.setPR_Roll_constant(med.getTextRollPoissonsRatio().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");
			obj.setYM_Table("false");
			obj.setYM_Value(med.getTextYoungsModulus().getText());
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
			obj.setYM_Value_T(med.getTextYoungsModulus().getText());
		}
		
		
		if(med.getBtnConstant4_FS().getSelection()){
			obj.setFS_Constant("true");
			obj.setFS_Table("false");
			//obj.setFS_Value(med.getTextFlowStress().getText());
			obj.setYS_Value(med.getTextYieldStrength().getText());
			obj.setTS_Value(med.getTextTensileStrength().getText());
			obj.setE_Value(med.getTextElongation().getText());
		}else{
			obj.setFS_Table("true");
			obj.setFS_Constant("false");
			obj.setFS_Value(med.getTextFlowStress().getText());
			//obj.setYS_Value(med.getTextYieldStrength().getText());
			//obj.setTS_Value(med.getTextTensileStrength().getText());
			//obj.setE_Value(med.getTextElongation().getText());
		}
		/*
		obj.setFS_Value(med.getTextFlowStress().getText());
		obj.setYS_Value(med.getTextYieldStrength().getText());
		obj.setTS_Value(med.getTextTensileStrength().getText());
		obj.setE_Value(med.getTextElongation().getText());
		*/
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
			obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
			obj.setTEC_Value_T(med.getTextThermalExpansionCoefficient().getText());
		}
		
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
			obj.setPR_Value(med.getTextPoissonsRatio().getText());
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("false");
			obj.setPR_Value_T(med.getTextPoissonsRatio().getText());
		}
		
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextAnalysisTime().getText());
		obj.setlcase_inc(med.getTextNoOfInc().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setlcase_dt(med.getTextTimeIncrement().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F1));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F1));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F1));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F1));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F1));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F1));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F1));
	}
	
	private void saveAllF2Values(){
		TableData_PLog obj = tableDataPLogList.get(1);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		obj.setWr_chamferX(med.getTextWRChamferX().getText());
		obj.setWr_chamferY(med.getTextWRChamferY().getText());
		obj.setWr_round(med.getTextWRRound().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		obj.setBur_chamferX(med.getTextBURChamferX().getText());
		obj.setBur_chamferY(med.getTextBURChamferY().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		obj.setP_cr(med.getTextPlateCrown().getText());

		
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setSpeed_different_ratio_top_roll(med.getTextSpeedDifferentRatioTopRoll().getText());
		obj.setSpeed_different_ratio_bottom_roll(med.getTextSpeedDifferentRatioBottomRoll().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		// Roll Material Parameter
		obj.setYM_Roll_constant(med.getTextRollYoungsModulus().getText());
		obj.setPR_Roll_constant(med.getTextRollPoissonsRatio().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
			obj.setYM_Value(med.getTextYoungsModulus().getText());
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
			obj.setYM_Value_T(med.getTextYoungsModulus().getText());
		}
		
		
		if(med.getBtnConstant4_FS().getSelection()){
			obj.setFS_Constant("true");
			obj.setFS_Table("false");
			obj.setYS_Value(med.getTextYieldStrength().getText());
			obj.setTS_Value(med.getTextTensileStrength().getText());
			obj.setE_Value(med.getTextElongation().getText());
		}else{
			obj.setFS_Table("true");
			obj.setFS_Constant("false");
			obj.setFS_Value(med.getTextFlowStress().getText());
		}
		
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
			obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
			obj.setTEC_Value_T(med.getTextThermalExpansionCoefficient().getText());
		}
		
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
			obj.setPR_Value(med.getTextPoissonsRatio().getText());
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("false");
			obj.setPR_Value_T(med.getTextPoissonsRatio().getText());
		}
		
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextAnalysisTime().getText());
		obj.setlcase_inc(med.getTextNoOfInc().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setlcase_dt(med.getTextTimeIncrement().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F2));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F2));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F2));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F2));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F2));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F2));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F2));
	}
	
	private void saveAllF3Values(){
		TableData_PLog obj = tableDataPLogList.get(2);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		obj.setWr_chamferX(med.getTextWRChamferX().getText());
		obj.setWr_chamferY(med.getTextWRChamferY().getText());
		obj.setWr_round(med.getTextWRRound().getText());

		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		obj.setBur_chamferX(med.getTextBURChamferX().getText());
		obj.setBur_chamferY(med.getTextBURChamferY().getText());


		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		obj.setP_cr(med.getTextPlateCrown().getText());

		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setSpeed_different_ratio_top_roll(med.getTextSpeedDifferentRatioTopRoll().getText());
		obj.setSpeed_different_ratio_bottom_roll(med.getTextSpeedDifferentRatioBottomRoll().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		// Roll Material Parameter
		obj.setYM_Roll_constant(med.getTextRollYoungsModulus().getText());
		obj.setPR_Roll_constant(med.getTextRollPoissonsRatio().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
			obj.setYM_Value(med.getTextYoungsModulus().getText());
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
			obj.setYM_Value_T(med.getTextYoungsModulus().getText());
		}
		
		
		if(med.getBtnConstant4_FS().getSelection()){
			obj.setFS_Constant("true");
			obj.setFS_Table("false");
			
			obj.setYS_Value(med.getTextYieldStrength().getText());
			obj.setTS_Value(med.getTextTensileStrength().getText());
			obj.setE_Value(med.getTextElongation().getText());
		}else{
			obj.setFS_Table("true");
			obj.setFS_Constant("false");
			obj.setFS_Value(med.getTextFlowStress().getText());
		}
		
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
			obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
			obj.setTEC_Value_T(med.getTextThermalExpansionCoefficient().getText());
		}
		
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
			obj.setPR_Value(med.getTextPoissonsRatio().getText());
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("false");
			obj.setPR_Value_T(med.getTextPoissonsRatio().getText());
		}
		
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextAnalysisTime().getText());
		obj.setlcase_inc(med.getTextNoOfInc().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setlcase_dt(med.getTextTimeIncrement().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F3));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F3));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F3));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F3));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F3));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F3));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F3));
	}

	private void saveAllF4Values(){
		TableData_PLog obj = tableDataPLogList.get(3);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		obj.setWr_chamferX(med.getTextWRChamferX().getText());
		obj.setWr_chamferY(med.getTextWRChamferY().getText());
		obj.setWr_round(med.getTextWRRound().getText());

		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		obj.setBur_chamferX(med.getTextBURChamferX().getText());
		obj.setBur_chamferY(med.getTextBURChamferY().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		obj.setP_cr(med.getTextPlateCrown().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setSpeed_different_ratio_top_roll(med.getTextSpeedDifferentRatioTopRoll().getText());
		obj.setSpeed_different_ratio_bottom_roll(med.getTextSpeedDifferentRatioBottomRoll().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		// Roll Material Parameter
		obj.setYM_Roll_constant(med.getTextRollYoungsModulus().getText());
		obj.setPR_Roll_constant(med.getTextRollPoissonsRatio().getText());
		
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
			obj.setYM_Value(med.getTextYoungsModulus().getText());
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
			obj.setYM_Value_T(med.getTextYoungsModulus().getText());
		}
		
		
		if(med.getBtnConstant4_FS().getSelection()){
			obj.setFS_Constant("true");
			obj.setFS_Table("false");
			obj.setYS_Value(med.getTextYieldStrength().getText());
			obj.setTS_Value(med.getTextTensileStrength().getText());
			obj.setE_Value(med.getTextElongation().getText());
		}else{
			obj.setFS_Table("true");
			obj.setFS_Constant("false");
			obj.setFS_Value(med.getTextFlowStress().getText());
		}
		
		
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
			obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
			obj.setTEC_Value_T(med.getTextThermalExpansionCoefficient().getText());
		}
		
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
			obj.setPR_Value(med.getTextPoissonsRatio().getText());
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("false");
			obj.setPR_Value_T(med.getTextPoissonsRatio().getText());
		}
		
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextAnalysisTime().getText());
		obj.setlcase_inc(med.getTextNoOfInc().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setlcase_dt(med.getTextTimeIncrement().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F4));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F4));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F4));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F4));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F4));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F4));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F4));
	}
	
	private void saveAllF5Values(){
		TableData_PLog obj = tableDataPLogList.get(4);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		obj.setWr_chamferX(med.getTextWRChamferX().getText());
		obj.setWr_chamferY(med.getTextWRChamferY().getText());
		obj.setWr_round(med.getTextWRRound().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		obj.setBur_chamferX(med.getTextBURChamferX().getText());
		obj.setBur_chamferY(med.getTextBURChamferY().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		obj.setP_cr(med.getTextPlateCrown().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setSpeed_different_ratio_top_roll(med.getTextSpeedDifferentRatioTopRoll().getText());
		obj.setSpeed_different_ratio_bottom_roll(med.getTextSpeedDifferentRatioBottomRoll().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		// Roll Material Parameter
		obj.setYM_Roll_constant(med.getTextRollYoungsModulus().getText());
		obj.setPR_Roll_constant(med.getTextRollPoissonsRatio().getText());
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
			obj.setYM_Value(med.getTextYoungsModulus().getText());
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
			obj.setYM_Value_T(med.getTextYoungsModulus().getText());
		}
		
		
		if(med.getBtnConstant4_FS().getSelection()){
			obj.setFS_Constant("true");
			obj.setFS_Table("false");
			
			obj.setYS_Value(med.getTextYieldStrength().getText());
			obj.setTS_Value(med.getTextTensileStrength().getText());
			obj.setE_Value(med.getTextElongation().getText());
		}else{
			obj.setFS_Table("true");
			obj.setFS_Constant("false");
			obj.setFS_Value(med.getTextFlowStress().getText());
		}
		
		
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
			obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
			obj.setTEC_Value_T(med.getTextThermalExpansionCoefficient().getText());
		}
		
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
			obj.setPR_Value(med.getTextPoissonsRatio().getText());
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("false");
			obj.setPR_Value_T(med.getTextPoissonsRatio().getText());
		}
		
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextAnalysisTime().getText());
		obj.setlcase_inc(med.getTextNoOfInc().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setlcase_dt(med.getTextTimeIncrement().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F5));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F5));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F5));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F5));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F5));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F5));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F5));
	}
	
	private void saveAllF6Values(){
		TableData_PLog obj = tableDataPLogList.get(5);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		obj.setWr_chamferX(med.getTextWRChamferX().getText());
		obj.setWr_chamferY(med.getTextWRChamferY().getText());
		obj.setWr_round(med.getTextWRRound().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		obj.setBur_chamferX(med.getTextBURChamferX().getText());
		obj.setBur_chamferY(med.getTextBURChamferY().getText());

		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		obj.setP_cr(med.getTextPlateCrown().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setSpeed_different_ratio_top_roll(med.getTextSpeedDifferentRatioTopRoll().getText());
		obj.setSpeed_different_ratio_bottom_roll(med.getTextSpeedDifferentRatioBottomRoll().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		// Roll Material Parameter
		obj.setYM_Roll_constant(med.getTextRollYoungsModulus().getText());
		obj.setPR_Roll_constant(med.getTextRollPoissonsRatio().getText());
		
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
			obj.setYM_Value(med.getTextYoungsModulus().getText());
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
			obj.setYM_Value_T(med.getTextYoungsModulus().getText());
		}
		
		
		if(med.getBtnConstant4_FS().getSelection()){
			obj.setFS_Constant("true");
			obj.setFS_Table("false");
			
			obj.setYS_Value(med.getTextYieldStrength().getText());
			obj.setTS_Value(med.getTextTensileStrength().getText());
			obj.setE_Value(med.getTextElongation().getText());
		}else{
			obj.setFS_Table("true");
			obj.setFS_Constant("false");
			obj.setFS_Value(med.getTextFlowStress().getText());
		}
		
		
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
			obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
			obj.setTEC_Value_T(med.getTextThermalExpansionCoefficient().getText());
		}
		
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
			obj.setPR_Value(med.getTextPoissonsRatio().getText());
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("false");
			obj.setPR_Value_T(med.getTextPoissonsRatio().getText());
		}
		
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextAnalysisTime().getText());
		obj.setlcase_inc(med.getTextNoOfInc().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setlcase_dt(med.getTextTimeIncrement().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F6));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F6));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F6));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F6));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F6));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F6));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F6));
	}
	
	private void saveAllF7Values(){
		TableData_PLog obj = tableDataPLogList.get(6);
		
		//Group1
		obj.setWR_TDIA(med.getTextTopWRDiameter().getText());
		obj.setWR_BDIA(med.getTextBottomWRDiameter().getText());
		obj.setWR_ICRN(med.getTextWRCrown().getText());
		obj.setWr_len(med.getTextWRLength().getText());
		obj.setWr_div_angle(med.getTextWRMeshAngle().getText());
		obj.setWr_chamferX(med.getTextWRChamferX().getText());
		obj.setWr_chamferY(med.getTextWRChamferY().getText());
		obj.setWr_round(med.getTextWRRound().getText());
		//Group2
		obj.setBUR_TDIA(med.getTextTopBURDiameter().getText());
		obj.setBUR_BDIA(med.getTextBottomBURDiameter().getText());
		obj.setBur_len(med.getTextBURLength().getText());
		obj.setBur_div_angle(med.getTextBURMeshAngle().getText());
		obj.setBur_chamferX(med.getTextBURChamferX().getText());
		obj.setBur_chamferY(med.getTextBURChamferY().getText());
		//Group3
		obj.setENTRY_THK(med.getTextThickness().getText());
		obj.setSTP_WID(med.getTextWidth().getText());
		obj.setSTP_LEN(med.getTextLength().getText());
		obj.setENTRY_TEMP(med.getTextEntryTemperature().getText());
		obj.setEXIT_TEMP(med.getTextExitTemperature().getText());
		obj.setP_in(med.getTextInitialPosition().getText());
		obj.setPl_m(med.getTextMeshLength().getText());
		obj.setT_div(med.getTextThicknessMeshDivisions().getText());
		obj.setP_cr(med.getTextPlateCrown().getText());
		//Group4
		obj.setSPEED(med.getTextVelocity().getText());
		obj.setROL_GAP(med.getTextRollGap().getText());
		obj.setPAS_LINE(med.getTextPassLine().getText());
		obj.setP_CROSS(med.getTextPairCrossAngle().getText());
		obj.setBEND(med.getTextBenderForce().getText());
		obj.setTORQ(med.getTextRollTorque().getText());
		obj.setTENS(med.getTextTensionStress().getText());
		obj.setF_r2p(med.getTextRollToPlateFrictCoef().getText());
		obj.setF_r2r(med.getTextRollToRollFrictCoef().getText());
		obj.setSpeed_different_ratio_top_roll(med.getTextSpeedDifferentRatioTopRoll().getText());
		obj.setSpeed_different_ratio_bottom_roll(med.getTextSpeedDifferentRatioBottomRoll().getText());
		obj.setWr_trot(med.getTextTopWRRotVel().getText());
		obj.setWr_brot(med.getTextBottomWRRotVel().getText());
		obj.setBur_trot(med.getTextTopBURRotVel().getText());
		obj.setBur_brot(med.getTextBottomBURRotVel().getText());
		// Roll Material Parameter
		obj.setYM_Roll_constant(med.getTextRollYoungsModulus().getText());
		obj.setPR_Roll_constant(med.getTextRollPoissonsRatio().getText());
		
		//Group5
		if(med.getBtnConstant1_YM().getSelection()){
			obj.setYM_Constant("true");;
			obj.setYM_Table("false");
			obj.setYM_Value(med.getTextYoungsModulus().getText());
		}else{
			obj.setYM_Table("true");
			obj.setYM_Constant("false");
			obj.setYM_Value_T(med.getTextYoungsModulus().getText());
		}
		
		
		if(med.getBtnConstant4_FS().getSelection()){
			obj.setFS_Constant("true");
			obj.setFS_Table("false");
			
			obj.setYS_Value(med.getTextYieldStrength().getText());
			obj.setTS_Value(med.getTextTensileStrength().getText());
			obj.setE_Value(med.getTextElongation().getText());
		}else{
			obj.setFS_Table("true");
			obj.setFS_Constant("false");
			obj.setFS_Value(med.getTextFlowStress().getText());
		}
		
		
		
		if(med.getBtnConstant2_TEC().getSelection()){
			obj.setTEC_Constant("true");
			obj.setTEC_Table("false");
			obj.setTEC_Value(med.getTextThermalExpansionCoefficient().getText());
		}else{
			obj.setTEC_Table("true");
			obj.setTEC_Constant("false");
			obj.setTEC_Value_T(med.getTextThermalExpansionCoefficient().getText());
		}
		
		
		if(med.getBtnConstant3_PR().getSelection()){
			obj.setPR_Constant("true");
			obj.setPR_Table("false");
			obj.setPR_Value(med.getTextPoissonsRatio().getText());
		}else{
			obj.setPR_Table("true");
			obj.setPR_Constant("false");
			obj.setPR_Value_T(med.getTextPoissonsRatio().getText());
		}
		
		
		obj.setMD_Value(med.getTextMassDensity().getText());
		//Group6
		obj.setLcase_time(med.getTextAnalysisTime().getText());
		obj.setlcase_inc(med.getTextNoOfInc().getText());
		obj.setPost_inc(med.getTextPostWritingFrequency().getText());
		obj.setlcase_dt(med.getTextTimeIncrement().getText());
		if(med.getBtnParallelDDM().getSelection()){
			obj.setParallelDDM("true");
		}else{
			obj.setParallelDDM("false");
		}
		obj.setDomain(med.getSpinnerDomain().getText());
		
		if(med.getBtnParallelMultiThread().getSelection()){
			obj.setParallelMultiThread("true");
		}else{
			obj.setParallelMultiThread("false");
		}
		obj.setThread(med.getSpinnerThread().getText());
		
		InitValue Vobj = new InitValue();
		Vobj.readInitValueFile();
		
		obj.setFRCE(Vobj.getInitValue(InitValue.FRCE_F7));
		obj.setEXIT_THK(Vobj.getInitValue(InitValue.EXIT_THK_F7));
		obj.setROL_TIM(Vobj.getInitValue(InitValue.ROL_TIM_F7));
		obj.setIDL_TIM(Vobj.getInitValue(InitValue.IDL_TIM_F7));
		obj.setBUR_WEAR(Vobj.getInitValue(InitValue.BUR_WEAR_F7));
		obj.setWR_WEAR(Vobj.getInitValue(InitValue.WR_WEAR_F7));
		obj.setWR_THRM(Vobj.getInitValue(InitValue.WR_THRM_F7));
	}
	
	private void initF1Values(){
		//System.out.println("asdfasdfasdfasdfasdf");
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(true);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F1));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F1));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F1));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F1));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F1));
		med.getTextWRChamferX().setText(obj.getInitValue(InitValue.wr_chamferX_F1));
		med.getTextWRChamferY().setText(obj.getInitValue(InitValue.wr_chamferY_F1));
		med.getTextWRRound().setText(obj.getInitValue(InitValue.wr_round_F1));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F1));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F1));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F1));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F1));
		med.getTextBURChamferX().setText(obj.getInitValue(InitValue.bur_chamferX_F1));
		med.getTextBURChamferY().setText(obj.getInitValue(InitValue.bur_chamferY_F1));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F1));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F1));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F1));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F1));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F1));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F1));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F1));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F1));
		med.getTextPlateCrown().setText(obj.getInitValue(InitValue.p_cr_F1));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F1));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F1));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F1));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F1));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F1));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F1));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F1));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F1));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F1));
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getInitValue(InitValue.vel_rate_top_F1));
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getInitValue(InitValue.vel_rate_bottom_F1));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F1));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F1));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F1));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F1));
		// Roll Material Parameter
		med.getTextRollYoungsModulus().setText(obj.getInitValue(InitValue.YM_Roll_Constant_F1));
		med.getTextRollPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Roll_Constant_F1));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F1).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F1));
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_tableValuePath());
		}
		
		//System.out.println(obj.getInitValue(InitValue.YS_Value_F1)+"|"+obj.getInitValue(InitValue.TS_Value_F1)+"|"+obj.getInitValue(InitValue.E_Value_F1));
		//System.out.println("-------------------");
		if(obj.getInitValue(InitValue.FS_Constant_F1).toLowerCase().equals("true")){
			//System.out.println("1");
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getInitValue(InitValue.YS_Value_F1));
			med.getTextTensileStrength().setText(obj.getInitValue(InitValue.TS_Value_F1));
			med.getTextElongation().setText(obj.getInitValue(InitValue.E_Value_F1));
			//System.out.println(obj.getInitValue(InitValue.YS_Value_F1)+"|"+obj.getInitValue(InitValue.TS_Value_F1)+"|"+obj.getInitValue(InitValue.E_Value_F1));
			
		}else{
			//System.out.println("2");
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_tableValuePath());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
		}
		
		
		if(obj.getInitValue(InitValue.TEC_Constant_F1).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F1));
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_tableValuePath());
		}
		
		
		
		if(obj.getInitValue(InitValue.PR_Constant_F1).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F1));
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_tableValuePath());
		}
		
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F1));
		//Group6
		med.getTextAnalysisTime().setText(obj.getInitValue(InitValue.lcase_time_F1));
		med.getTextNoOfInc().setText(obj.getInitValue(InitValue.lcase_inc_F1));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F1));
		med.getTextTimeIncrement().setText(obj.getInitValue(InitValue.lcase_dt_F1));
		if(obj.getInitValue(InitValue.ParallelDDM_F1).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F1)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F1)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F1).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F1)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F1)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
		//this.checkObj();
	}
	
	private void initF2Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(true);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F2));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F2));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F2));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F2));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F2));
		med.getTextWRChamferX().setText(obj.getInitValue(InitValue.wr_chamferX_F2));
		med.getTextWRChamferY().setText(obj.getInitValue(InitValue.wr_chamferY_F2));
		med.getTextWRRound().setText(obj.getInitValue(InitValue.wr_round_F2));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F2));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F2));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F2));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F2));
		med.getTextBURChamferX().setText(obj.getInitValue(InitValue.bur_chamferX_F2));
		med.getTextBURChamferY().setText(obj.getInitValue(InitValue.bur_chamferY_F2));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F2));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F2));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F2));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F2));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F2));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F2));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F2));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F2));
		med.getTextPlateCrown().setText(obj.getInitValue(InitValue.p_cr_F2));
		
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F2));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F2));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F2));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F2));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F2));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F2));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F2));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F2));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F2));
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getInitValue(InitValue.vel_rate_top_F2));
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getInitValue(InitValue.vel_rate_bottom_F2));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F2));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F2));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F2));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F2));
		// Roll Material Parameter
		med.getTextRollYoungsModulus().setText(obj.getInitValue(InitValue.YM_Roll_Constant_F2));
		med.getTextRollPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Roll_Constant_F2));
				
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F2).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F2));
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.FS_Constant_F2).toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getInitValue(InitValue.YS_Value_F2));
			med.getTextTensileStrength().setText(obj.getInitValue(InitValue.TS_Value_F2));
			med.getTextElongation().setText(obj.getInitValue(InitValue.E_Value_F2));
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_tableValuePath());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
		}
		
		
		if(obj.getInitValue(InitValue.TEC_Constant_F2).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F2));
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.PR_Constant_F2).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F2));
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_tableValuePath());
		}
		
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F2));
		//Group6
		med.getTextAnalysisTime().setText(obj.getInitValue(InitValue.lcase_time_F2));
		med.getTextNoOfInc().setText(obj.getInitValue(InitValue.lcase_inc_F2));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F2));
		med.getTextTimeIncrement().setText(obj.getInitValue(InitValue.lcase_dt_F2));
		if(obj.getInitValue(InitValue.ParallelDDM_F2).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F2)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F2)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F2).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F2)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F2)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF3Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(true);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F3));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F3));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F3));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F3));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F3));
		med.getTextWRChamferX().setText(obj.getInitValue(InitValue.wr_chamferX_F3));
		med.getTextWRChamferY().setText(obj.getInitValue(InitValue.wr_chamferY_F3));
		med.getTextWRRound().setText(obj.getInitValue(InitValue.wr_round_F3));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F3));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F3));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F3));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F3));
		med.getTextBURChamferX().setText(obj.getInitValue(InitValue.bur_chamferX_F3));
		med.getTextBURChamferY().setText(obj.getInitValue(InitValue.bur_chamferY_F3));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F3));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F3));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F3));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F3));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F3));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F3));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F3));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F3));
		med.getTextPlateCrown().setText(obj.getInitValue(InitValue.p_cr_F3));
		
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F3));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F3));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F3));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F3));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F3));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F3));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F3));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F3));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F3));
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getInitValue(InitValue.vel_rate_top_F3));
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getInitValue(InitValue.vel_rate_bottom_F3));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F3));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F3));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F3));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F3));
		// Roll Material Parameter
		med.getTextRollYoungsModulus().setText(obj.getInitValue(InitValue.YM_Roll_Constant_F3));
		med.getTextRollPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Roll_Constant_F3));
				
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F3).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F3));
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_tableValuePath());
			
		}
		
		
		if(obj.getInitValue(InitValue.FS_Constant_F3).toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getInitValue(InitValue.YS_Value_F3));
			med.getTextTensileStrength().setText(obj.getInitValue(InitValue.TS_Value_F3));
			med.getTextElongation().setText(obj.getInitValue(InitValue.E_Value_F3));
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_tableValuePath());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
		}
		
		
		if(obj.getInitValue(InitValue.TEC_Constant_F3).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F3));
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.PR_Constant_F3).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F3));
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_tableValuePath());
		}
		
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F3));
		//Group6
		med.getTextAnalysisTime().setText(obj.getInitValue(InitValue.lcase_time_F3));
		med.getTextNoOfInc().setText(obj.getInitValue(InitValue.lcase_inc_F3));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F3));
		med.getTextTimeIncrement().setText(obj.getInitValue(InitValue.lcase_dt_F3));
		if(obj.getInitValue(InitValue.ParallelDDM_F3).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F3)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			///med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F3)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F3).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F3)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F3)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF4Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(true);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F4));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F4));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F4));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F4));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F4));
		med.getTextWRChamferX().setText(obj.getInitValue(InitValue.wr_chamferX_F4));
		med.getTextWRChamferY().setText(obj.getInitValue(InitValue.wr_chamferY_F4));
		med.getTextWRRound().setText(obj.getInitValue(InitValue.wr_round_F4));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F4));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F4));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F4));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F4));
		med.getTextBURChamferX().setText(obj.getInitValue(InitValue.bur_chamferX_F4));
		med.getTextBURChamferY().setText(obj.getInitValue(InitValue.bur_chamferY_F4));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F4));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F4));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F4));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F4));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F4));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F4));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F4));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F4));
		med.getTextPlateCrown().setText(obj.getInitValue(InitValue.p_cr_F4));

		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F4));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F4));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F4));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F4));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F4));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F4));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F4));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F4));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F4));
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getInitValue(InitValue.vel_rate_top_F4));
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getInitValue(InitValue.vel_rate_bottom_F4));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F4));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F4));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F4));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F4));
		// Roll Material Parameter
		med.getTextRollYoungsModulus().setText(obj.getInitValue(InitValue.YM_Roll_Constant_F4));
		med.getTextRollPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Roll_Constant_F4));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F4).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F4));
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.FS_Constant_F4).toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getInitValue(InitValue.YS_Value_F4));
			med.getTextTensileStrength().setText(obj.getInitValue(InitValue.TS_Value_F4));
			med.getTextElongation().setText(obj.getInitValue(InitValue.E_Value_F4));
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_tableValuePath());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
		}
		
		
		if(obj.getInitValue(InitValue.TEC_Constant_F4).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F4));
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.PR_Constant_F4).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F4));
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_tableValuePath());
		}
		
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F4));
		//Group6
		med.getTextAnalysisTime().setText(obj.getInitValue(InitValue.lcase_time_F4));
		med.getTextNoOfInc().setText(obj.getInitValue(InitValue.lcase_inc_F4));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F4));
		med.getTextTimeIncrement().setText(obj.getInitValue(InitValue.lcase_dt_F4));
		if(obj.getInitValue(InitValue.ParallelDDM_F4).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F4)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F4)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F4).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F4)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F4)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF5Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(true);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F5));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F5));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F5));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F5));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F5));
		med.getTextWRChamferX().setText(obj.getInitValue(InitValue.wr_chamferX_F5));
		med.getTextWRChamferY().setText(obj.getInitValue(InitValue.wr_chamferY_F5));
		med.getTextWRRound().setText(obj.getInitValue(InitValue.wr_round_F5));
		
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F5));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F5));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F5));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F5));
		med.getTextBURChamferX().setText(obj.getInitValue(InitValue.bur_chamferX_F5));
		med.getTextBURChamferY().setText(obj.getInitValue(InitValue.bur_chamferY_F5));
		
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F5));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F5));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F5));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F5));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F5));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F5));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F5));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F5));
		med.getTextPlateCrown().setText(obj.getInitValue(InitValue.p_cr_F5));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F5));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F5));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F5));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F5));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F5));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F5));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F5));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F5));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F5));
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getInitValue(InitValue.vel_rate_top_F5));
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getInitValue(InitValue.vel_rate_bottom_F5));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F5));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F5));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F5));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F5));
		// Roll Material Parameter
		med.getTextRollYoungsModulus().setText(obj.getInitValue(InitValue.YM_Roll_Constant_F5));
		med.getTextRollPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Roll_Constant_F5));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F5).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F5));
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.FS_Constant_F5).toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getInitValue(InitValue.YS_Value_F5));
			med.getTextTensileStrength().setText(obj.getInitValue(InitValue.TS_Value_F5));
			med.getTextElongation().setText(obj.getInitValue(InitValue.E_Value_F5));
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_tableValuePath());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
		}
		
		
		if(obj.getInitValue(InitValue.TEC_Constant_F5).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F5));
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.PR_Constant_F5).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F5));
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_tableValuePath());
		}
		
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F5));
		//Group6
		med.getTextAnalysisTime().setText(obj.getInitValue(InitValue.lcase_time_F5));
		med.getTextNoOfInc().setText(obj.getInitValue(InitValue.lcase_inc_F5));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F5));
		med.getTextTimeIncrement().setText(obj.getInitValue(InitValue.lcase_dt_F5));
		if(obj.getInitValue(InitValue.ParallelDDM_F5).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F5)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F5)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F5).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F5)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F5)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF6Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(true);
		med.getBtnF7().setSelection(false);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F6));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F6));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F6));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F6));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F6));
		med.getTextWRChamferX().setText(obj.getInitValue(InitValue.wr_chamferX_F6));
		med.getTextWRChamferY().setText(obj.getInitValue(InitValue.wr_chamferY_F6));
		med.getTextWRRound().setText(obj.getInitValue(InitValue.wr_round_F6));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F6));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F6));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F6));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F6));
		med.getTextBURChamferX().setText(obj.getInitValue(InitValue.bur_chamferX_F6));
		med.getTextBURChamferY().setText(obj.getInitValue(InitValue.bur_chamferY_F6));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F6));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F6));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F6));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F6));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F6));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F6));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F6));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F6));
		med.getTextPlateCrown().setText(obj.getInitValue(InitValue.p_cr_F6));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F6));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F6));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F6));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F6));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F6));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F6));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F6));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F6));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F6));
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getInitValue(InitValue.vel_rate_top_F6));
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getInitValue(InitValue.vel_rate_bottom_F6));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F6));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F6));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F6));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F6));
		// Roll Material Parameter
		med.getTextRollYoungsModulus().setText(obj.getInitValue(InitValue.YM_Roll_Constant_F6));
		med.getTextRollPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Roll_Constant_F6));
				
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F6).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F6));
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.FS_Constant_F6).toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getInitValue(InitValue.YS_Value_F6));
			med.getTextTensileStrength().setText(obj.getInitValue(InitValue.TS_Value_F6));
			med.getTextElongation().setText(obj.getInitValue(InitValue.E_Value_F6));
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_tableValuePath());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
		}
		
		
		if(obj.getInitValue(InitValue.TEC_Constant_F6).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F6));
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.PR_Constant_F6).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F6));
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_tableValuePath());
		}
		
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F6));
		//Group6
		med.getTextAnalysisTime().setText(obj.getInitValue(InitValue.lcase_time_F6));
		med.getTextNoOfInc().setText(obj.getInitValue(InitValue.lcase_inc_F6));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F6));
		med.getTextTimeIncrement().setText(obj.getInitValue(InitValue.lcase_dt_F6));
		if(obj.getInitValue(InitValue.ParallelDDM_F6).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F6)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F6)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F6).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F6)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F6)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void initF7Values(){
		InitValue obj = new InitValue();
		obj.readInitValueFile();
		
		med.getBtnF1().setSelection(false);
		med.getBtnF2().setSelection(false);
		med.getBtnF3().setSelection(false);
		med.getBtnF4().setSelection(false);
		med.getBtnF5().setSelection(false);
		med.getBtnF6().setSelection(false);
		med.getBtnF7().setSelection(true);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getInitValue(InitValue.WR_TDIA_F7));
		med.getTextBottomWRDiameter().setText(obj.getInitValue(InitValue.WR_BDIA_F7));
		med.getTextWRCrown().setText(obj.getInitValue(InitValue.WR_ICRN_F7));
		med.getTextWRLength().setText(obj.getInitValue(InitValue.wr_len_F7));
		med.getTextWRMeshAngle().setText(obj.getInitValue(InitValue.wr_div_angle_F7));
		med.getTextWRChamferX().setText(obj.getInitValue(InitValue.wr_chamferX_F7));
		med.getTextWRChamferY().setText(obj.getInitValue(InitValue.wr_chamferY_F7));
		med.getTextWRRound().setText(obj.getInitValue(InitValue.wr_round_F7));
		//Group2
		med.getTextTopBURDiameter().setText(obj.getInitValue(InitValue.BUR_TDIA_F7));
		med.getTextBottomBURDiameter().setText(obj.getInitValue(InitValue.BUR_BDIA_F7));
		med.getTextBURLength().setText(obj.getInitValue(InitValue.bur_len_F7));
		med.getTextBURMeshAngle().setText(obj.getInitValue(InitValue.bur_div_angle_F7));
		//Group3
		med.getTextThickness().setText(obj.getInitValue(InitValue.ENTRY_THK_F7));
		med.getTextWidth().setText(obj.getInitValue(InitValue.STP_WID_F7));
		med.getTextLength().setText(obj.getInitValue(InitValue.STP_LEN_F7));
		med.getTextEntryTemperature().setText(obj.getInitValue(InitValue.ENTRY_TEMP_F7));
		med.getTextExitTemperature().setText(obj.getInitValue(InitValue.EXIT_TEMP_F7));
		med.getTextInitialPosition().setText(obj.getInitValue(InitValue.p_in_F7));
		med.getTextMeshLength().setText(obj.getInitValue(InitValue.pl_m_F7));
		med.getTextThicknessMeshDivisions().setText(obj.getInitValue(InitValue.t_div_F7));
		//Group4
		med.getTextVelocity().setText(obj.getInitValue(InitValue.SPEED_mpm_F7));
		med.getTextRollGap().setText(obj.getInitValue(InitValue.ROL_GAP_F7));
		med.getTextPassLine().setText(obj.getInitValue(InitValue.PAS_LINE_F7));
		med.getTextPairCrossAngle().setText(obj.getInitValue(InitValue.P_CROSS_F7));
		med.getTextBenderForce().setText(obj.getInitValue(InitValue.BEND_F7));
		med.getTextRollTorque().setText(obj.getInitValue(InitValue.TORQ_F7));
		med.getTextTensionStress().setText(obj.getInitValue(InitValue.TENS_F7));
		med.getTextRollToPlateFrictCoef().setText(obj.getInitValue(InitValue.f_r2p_F7));
		med.getTextRollToRollFrictCoef().setText(obj.getInitValue(InitValue.f_r2r_F7));
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getInitValue(InitValue.vel_rate_top_F7));
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getInitValue(InitValue.vel_rate_bottom_F7));
		med.getTextTopWRRotVel().setText(obj.getInitValue(InitValue.wr_trot_F7));
		med.getTextBottomWRRotVel().setText(obj.getInitValue(InitValue.wr_brot_F7));
		med.getTextTopBURRotVel().setText(obj.getInitValue(InitValue.bur_trot_F7));
		med.getTextBottomBURRotVel().setText(obj.getInitValue(InitValue.bur_brot_F7));
		//Group5
		if(obj.getInitValue(InitValue.YM_Constant_F7).toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getInitValue(InitValue.YM_Value_F7));
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.FS_Constant_F7).toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getInitValue(InitValue.FS_Value_F7));
			med.getTextYieldStrength().setText(obj.getInitValue(InitValue.YS_Value_F7));
			med.getTextTensileStrength().setText(obj.getInitValue(InitValue.TS_Value_F7));
			med.getTextElongation().setText(obj.getInitValue(InitValue.E_Value_F7));
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_tableValuePath());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
		}
		
		
		if(obj.getInitValue(InitValue.TEC_Constant_F7).toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getInitValue(InitValue.TEC_Value_F7));
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_tableValuePath());
		}
		
		
		if(obj.getInitValue(InitValue.PR_Constant_F7).toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getInitValue(InitValue.PR_Value_F7));
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_tableValuePath());
		}
		
		
		med.getTextMassDensity().setText(obj.getInitValue(InitValue.MD_Value_F7));
		//Group6
		med.getTextAnalysisTime().setText(obj.getInitValue(InitValue.lcase_time_F7));
		med.getTextNoOfInc().setText(obj.getInitValue(InitValue.lcase_inc_F7));
		med.getTextPostWritingFrequency().setText(obj.getInitValue(InitValue.post_inc_F7));
		med.getTextTimeIncrement().setText(obj.getInitValue(InitValue.lcase_dt_F7));
		if(obj.getInitValue(InitValue.ParallelDDM_F7).toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F7)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Domain_F7)));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getInitValue(InitValue.ParallelMultiThread_F7).toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F7)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getInitValue(InitValue.Thread_F7)));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	public void ChangedSTANDValue(String value){
		this.StandValue = value;
		//System.out.println("Current STAND : "+value);
		// UI �ʱⰪ ���� �ڵ� ����
		switch(this.StandValue){
		case "F1":
			this.changedF1UIValues();
			//System.out.println(tableDataPLogList.get(0).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(0).getPR_Value());
			break;
		case "F2":
			this.changedF2UIValues();
			//System.out.println(tableDataPLogList.get(1).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(1).getPR_Value());
			break;
		case "F3":
			this.changedF3UIValues();
			//System.out.println(tableDataPLogList.get(2).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(2).getPR_Value());
			break;
		case "F4":
			this.changedF4UIValues();
			//System.out.println(tableDataPLogList.get(3).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(3).getPR_Value());
			break;
		case "F5":
			this.changedF5UIValues();
			//System.out.println(tableDataPLogList.get(4).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(4).getPR_Value());
			break;
		case "F6":
			this.changedF6UIValues();
			//System.out.println(tableDataPLogList.get(5).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(5).getPR_Value());
			break;
		case "F7":
			this.changedF7UIValues();
			//System.out.println(tableDataPLogList.get(6).getENTRY_THK());
			//System.out.println(tableDataPLogList.get(6).getPR_Value());
			break;
		}
		this.calcAllData("noFull");
	}
	
	private void changedF1UIValues(){
		//InitValue objInitValue = new InitValue();
		//objInitValue.readInitValueFile();
		
		TableData_PLog obj = tableDataPLogList.get(0);
		//this.checkObj();
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		med.getTextWRChamferX().setText(obj.getWr_chamferX());
		med.getTextWRChamferY().setText(obj.getWr_chamferY());
		med.getTextWRRound().setText(obj.getWr_round());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		med.getTextBURChamferX().setText(obj.getBur_chamferX());
		med.getTextBURChamferY().setText(obj.getBur_chamferY());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		med.getTextPlateCrown().setText(obj.getP_cr());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getSpeed_different_ratio_top_roll());
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getSpeed_different_ratio_bottom_roll());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		// Roll Materiap Parameter
		med.getTextRollYoungsModulus().setText(obj.getYM_Roll_constant());
		med.getTextRollPoissonsRatio().setText(obj.getPR_Roll_constant());
		
		//Group5
		/*
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}
		med.getTextYoungsModulus().setText(obj.getYM_Value());
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		// */
		//////////////
		
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value());
			med.getBtnExplorerYoungsModulus().setEnabled(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			med.getBtnExplorerYoungsModulus().setEnabled(true);
		}
		
		if(obj.getFS_Constant().toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getYS_Value());
			med.getTextTensileStrength().setText(obj.getTS_Value());
			med.getTextElongation().setText(obj.getE_Value());
			
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
			med.getBtnExplorerFlowStress().setEnabled(false);
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_Value());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText("" );
			med.getTextElongation().setText(" ");
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			med.getBtnExplorerFlowStress().setEnabled(true);
		}
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
		}

		
		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value());
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
		}
		med.getTextMassDensity().setText(obj.getMD_Value());
		
		
		
		//Group6
		med.getTextAnalysisTime().setText(obj.getLcase_time());
		med.getTextNoOfInc().setText(obj.getlcase_inc());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextTimeIncrement().setText(obj.getlcase_dt());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF2UIValues(){
		InitValue objInitValue = new InitValue();
		objInitValue.readInitValueFile();
		
		TableData_PLog obj = tableDataPLogList.get(1);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		med.getTextWRChamferX().setText(obj.getWr_chamferX());
		med.getTextWRChamferY().setText(obj.getWr_chamferY());
		med.getTextWRRound().setText(obj.getWr_round());
		
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		med.getTextBURChamferX().setText(obj.getBur_chamferX());
		med.getTextBURChamferY().setText(obj.getBur_chamferY());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		med.getTextPlateCrown().setText(obj.getP_cr());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getSpeed_different_ratio_top_roll());
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getSpeed_different_ratio_bottom_roll());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		// Roll Materiap Parameter
		med.getTextRollYoungsModulus().setText(obj.getYM_Roll_constant());
		med.getTextRollPoissonsRatio().setText(obj.getPR_Roll_constant());
		//Group5
		/*
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		// */
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value());
			med.getBtnExplorerYoungsModulus().setEnabled(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			med.getBtnExplorerYoungsModulus().setEnabled(true);
		}
		
		if(obj.getFS_Constant().toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getYS_Value());
			med.getTextTensileStrength().setText(obj.getTS_Value());
			med.getTextElongation().setText(obj.getE_Value());
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
			med.getBtnExplorerFlowStress().setEnabled(false);
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_Value());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			med.getBtnExplorerFlowStress().setEnabled(true);
		}
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
		}

		
		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value());
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
		}
		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextAnalysisTime().setText(obj.getLcase_time());
		med.getTextNoOfInc().setText(obj.getlcase_inc());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextTimeIncrement().setText(obj.getlcase_dt());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF3UIValues(){
		InitValue objInitValue = new InitValue();
		objInitValue.readInitValueFile();
		
		TableData_PLog obj = tableDataPLogList.get(2);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		med.getTextWRChamferX().setText(obj.getWr_chamferX());
		med.getTextWRChamferY().setText(obj.getWr_chamferY());
		med.getTextWRRound().setText(obj.getWr_round());
		
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		med.getTextBURChamferX().setText(obj.getBur_chamferX());
		med.getTextBURChamferY().setText(obj.getBur_chamferY());
		
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		med.getTextPlateCrown().setText(obj.getP_cr());
		
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getSpeed_different_ratio_top_roll());
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getSpeed_different_ratio_bottom_roll());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		// Roll Materiap Parameter
		med.getTextRollYoungsModulus().setText(obj.getYM_Roll_constant());
		med.getTextRollPoissonsRatio().setText(obj.getPR_Roll_constant());
		//Group5
		/*
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		// */
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value());
			med.getBtnExplorerYoungsModulus().setEnabled(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			med.getBtnExplorerYoungsModulus().setEnabled(true);
		}
		
		if(obj.getFS_Constant().toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getYS_Value());
			med.getTextTensileStrength().setText(obj.getTS_Value());
			med.getTextElongation().setText(obj.getE_Value());
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
			med.getBtnExplorerFlowStress().setEnabled(false);
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_Value());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			med.getBtnExplorerFlowStress().setEnabled(true);
		}
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
		}

		
		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value());
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
		}
		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextAnalysisTime().setText(obj.getLcase_time());
		med.getTextNoOfInc().setText(obj.getlcase_inc());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextTimeIncrement().setText(obj.getlcase_dt());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF4UIValues(){
		InitValue objInitValue = new InitValue();
		objInitValue.readInitValueFile();
		
		TableData_PLog obj = tableDataPLogList.get(3);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		med.getTextWRChamferX().setText(obj.getWr_chamferX());
		med.getTextWRChamferY().setText(obj.getWr_chamferY());
		med.getTextWRRound().setText(obj.getWr_round());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		med.getTextBURChamferX().setText(obj.getBur_chamferX());
		med.getTextBURChamferY().setText(obj.getBur_chamferY());

		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		med.getTextPlateCrown().setText(obj.getP_cr());
		
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getSpeed_different_ratio_top_roll());
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getSpeed_different_ratio_bottom_roll());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		
		// Roll Materiap Parameter
		med.getTextRollYoungsModulus().setText(obj.getYM_Roll_constant());
		med.getTextRollPoissonsRatio().setText(obj.getPR_Roll_constant());
		//Group5
		/*
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		// */
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value());
			med.getBtnExplorerYoungsModulus().setEnabled(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			med.getBtnExplorerYoungsModulus().setEnabled(true);
		}
		
		if(obj.getFS_Constant().toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getYS_Value());
			med.getTextTensileStrength().setText(obj.getTS_Value());
			med.getTextElongation().setText(obj.getE_Value());
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
			med.getBtnExplorerFlowStress().setEnabled(false);
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_Value());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			med.getBtnExplorerFlowStress().setEnabled(true);
		}
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
		}

		
		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value());
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
		}
		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextAnalysisTime().setText(obj.getLcase_time());
		med.getTextNoOfInc().setText(obj.getlcase_inc());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextTimeIncrement().setText(obj.getlcase_dt());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF5UIValues(){
		InitValue objInitValue = new InitValue();
		objInitValue.readInitValueFile();
		
		TableData_PLog obj = tableDataPLogList.get(4);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		med.getTextWRChamferX().setText(obj.getWr_chamferX());
		med.getTextWRChamferY().setText(obj.getWr_chamferY());
		med.getTextWRRound().setText(obj.getWr_round());
		
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		med.getTextBURChamferX().setText(obj.getBur_chamferX());
		med.getTextBURChamferY().setText(obj.getBur_chamferY());
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		med.getTextPlateCrown().setText(obj.getP_cr());
		
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getSpeed_different_ratio_top_roll());
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getSpeed_different_ratio_bottom_roll());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		
		// Roll Materiap Parameter
		med.getTextRollYoungsModulus().setText(obj.getYM_Roll_constant());
		med.getTextRollPoissonsRatio().setText(obj.getPR_Roll_constant());
		//Group5
		/*
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		// */
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value());
			med.getBtnExplorerYoungsModulus().setEnabled(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			med.getBtnExplorerYoungsModulus().setEnabled(true);
		}
		
		if(obj.getFS_Constant().toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getYS_Value());
			med.getTextTensileStrength().setText(obj.getTS_Value());
			med.getTextElongation().setText(obj.getE_Value());
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
			med.getBtnExplorerFlowStress().setEnabled(false);
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_Value());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			med.getBtnExplorerFlowStress().setEnabled(true);
		}
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
		}

		
		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value());
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
		}
		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextAnalysisTime().setText(obj.getLcase_time());
		med.getTextNoOfInc().setText(obj.getlcase_inc());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextTimeIncrement().setText(obj.getlcase_dt());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF6UIValues(){
		InitValue objInitValue = new InitValue();
		objInitValue.readInitValueFile();
		
		TableData_PLog obj = tableDataPLogList.get(5);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		med.getTextWRChamferX().setText(obj.getWr_chamferX());
		med.getTextWRChamferY().setText(obj.getWr_chamferY());
		med.getTextWRRound().setText(obj.getWr_round());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		med.getTextBURChamferX().setText(obj.getBur_chamferX());
		med.getTextBURChamferY().setText(obj.getBur_chamferY());
		
		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		med.getTextPlateCrown().setText(obj.getP_cr());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getSpeed_different_ratio_top_roll());
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getSpeed_different_ratio_bottom_roll());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		// Roll Materiap Parameter
		med.getTextRollYoungsModulus().setText(obj.getYM_Roll_constant());
		med.getTextRollPoissonsRatio().setText(obj.getPR_Roll_constant());
		//Group5
		/*
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		//*/
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value());
			med.getBtnExplorerYoungsModulus().setEnabled(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			med.getBtnExplorerYoungsModulus().setEnabled(true);
		}
		
		if(obj.getFS_Constant().toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getYS_Value());
			med.getTextTensileStrength().setText(obj.getTS_Value());
			med.getTextElongation().setText(obj.getE_Value());
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
			med.getBtnExplorerFlowStress().setEnabled(false);
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_Value());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			med.getBtnExplorerFlowStress().setEnabled(true);
		}
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
		}

		
		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value());
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
		}
		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextAnalysisTime().setText(obj.getLcase_time());
		med.getTextNoOfInc().setText(obj.getlcase_inc());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextTimeIncrement().setText(obj.getlcase_dt());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	
	private void changedF7UIValues(){
		InitValue objInitValue = new InitValue();
		objInitValue.readInitValueFile();
		
		TableData_PLog obj = tableDataPLogList.get(6);
		
		//Group1
		med.getTextTopWRDiameter().setText(obj.getWR_TDIA());
		med.getTextBottomWRDiameter().setText(obj.getWR_BDIA());
		med.getTextWRCrown().setText(obj.getWR_ICRN());
		med.getTextWRLength().setText(obj.getWr_len());
		med.getTextWRMeshAngle().setText(obj.getWr_div_angle());
		med.getTextWRChamferX().setText(obj.getWr_chamferX());
		med.getTextWRChamferY().setText(obj.getWr_chamferY());
		med.getTextWRRound().setText(obj.getWr_round());
		//Group2
		med.getTextTopBURDiameter().setText(obj.getBUR_TDIA());
		med.getTextBottomBURDiameter().setText(obj.getBUR_BDIA());
		med.getTextBURLength().setText(obj.getBur_len());
		med.getTextBURMeshAngle().setText(obj.getBur_div_angle());
		med.getTextBURChamferX().setText(obj.getBur_chamferX());
		med.getTextBURChamferY().setText(obj.getBur_chamferY());

		//Group3
		med.getTextThickness().setText(obj.getENTRY_THK());
		med.getTextWidth().setText(obj.getSTP_WID());
		med.getTextLength().setText(obj.getSTP_LEN());
		med.getTextEntryTemperature().setText(obj.getENTRY_TEMP());
		med.getTextExitTemperature().setText(obj.getEXIT_TEMP());
		med.getTextInitialPosition().setText(obj.getP_in());
		med.getTextMeshLength().setText(obj.getPl_m());
		med.getTextThicknessMeshDivisions().setText(obj.getT_div());
		med.getTextPlateCrown().setText(obj.getP_cr());
		//Group4
		med.getTextVelocity().setText(obj.getSPEED());
		med.getTextRollGap().setText(obj.getROL_GAP());
		med.getTextPassLine().setText(obj.getPAS_LINE());
		med.getTextPairCrossAngle().setText(obj.getP_CROSS());
		med.getTextBenderForce().setText(obj.getBEND());
		med.getTextRollTorque().setText(obj.getTORQ());
		med.getTextTensionStress().setText(obj.getTENS());
		med.getTextRollToPlateFrictCoef().setText(obj.getF_r2p());
		med.getTextRollToRollFrictCoef().setText(obj.getF_r2r());
		med.getTextSpeedDifferentRatioTopRoll().setText(obj.getSpeed_different_ratio_top_roll());
		med.getTextSpeedDifferentRatioBottomRoll().setText(obj.getSpeed_different_ratio_bottom_roll());
		med.getTextTopWRRotVel().setText(obj.getWr_trot());
		med.getTextBottomWRRotVel().setText(obj.getWr_brot());
		med.getTextTopBURRotVel().setText(obj.getBur_trot());
		med.getTextBottomBURRotVel().setText(obj.getBur_brot());
		// Roll Materiap Parameter
		med.getTextRollYoungsModulus().setText(obj.getYM_Roll_constant());
		med.getTextRollPoissonsRatio().setText(obj.getPR_Roll_constant());
		//Group5
		/*
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
		}

		med.getTextYoungsModulus().setText(obj.getYM_Value());
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
		}
		med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());

		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
		}
		med.getTextPoissonsRatio().setText(obj.getPR_Value());

		med.getTextMassDensity().setText(obj.getMD_Value());
		// */
		if(obj.getYM_Constant().toLowerCase().equals("true")){
			med.getBtnConstant1_YM().setSelection(true);
			med.getBtnTable1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value());
			med.getBtnExplorerYoungsModulus().setEnabled(false);
		}else{
			med.getBtnTable1_YM().setSelection(true);
			med.getBtnConstant1_YM().setSelection(false);
			med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			med.getBtnExplorerYoungsModulus().setEnabled(true);
		}
		
		if(obj.getFS_Constant().toLowerCase().equals("true")){
			med.getBtnConstant4_FS().setSelection(true);
			med.getBtnTable4_FS().setSelection(false);
			med.getTextFlowStress().setText(" ");
			med.getTextYieldStrength().setText(obj.getYS_Value());
			med.getTextTensileStrength().setText(obj.getTS_Value());
			med.getTextElongation().setText(obj.getE_Value());
			med.getTextFlowStress().setEnabled(false);
			med.getTextYieldStrength().setEnabled(true);
			med.getTextTensileStrength().setEnabled(true);
			med.getTextElongation().setEnabled(true);
			med.getBtnExplorerFlowStress().setEnabled(false);
		}else{
			med.getBtnTable4_FS().setSelection(true);
			med.getBtnConstant4_FS().setSelection(false);
			med.getTextFlowStress().setText(obj.getFS_Value());
			med.getTextYieldStrength().setText(" ");
			med.getTextTensileStrength().setText(" ");
			med.getTextElongation().setText(" ");
			med.getTextFlowStress().setEnabled(true);
			med.getTextYieldStrength().setEnabled(false);
			med.getTextTensileStrength().setEnabled(false);
			med.getTextElongation().setEnabled(false);
			med.getBtnExplorerFlowStress().setEnabled(true);
		}
		
		if(obj.getTEC_Constant().toLowerCase().equals("true")){
			med.getBtnConstant2_TEC().setSelection(true);
			med.getBtnTable2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
		}else{
			med.getBtnTable2_TEC().setSelection(true);
			med.getBtnConstant2_TEC().setSelection(false);
			med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
		}

		
		if(obj.getPR_Constant().toLowerCase().equals("true")){
			med.getBtnConstant3_PR().setSelection(true);
			med.getBtnTable3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value());
			med.getBtnExplorerPoissonsRatio().setEnabled(false);
		}else{
			med.getBtnTable3_PR().setSelection(true);
			med.getBtnConstant3_PR().setSelection(false);
			med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			med.getBtnExplorerPoissonsRatio().setEnabled(true);
		}
		med.getTextMassDensity().setText(obj.getMD_Value());
		//Group6
		med.getTextAnalysisTime().setText(obj.getLcase_time());
		med.getTextNoOfInc().setText(obj.getlcase_inc());
		med.getTextPostWritingFrequency().setText(obj.getPost_inc());
		med.getTextTimeIncrement().setText(obj.getlcase_dt());
		if(obj.getParallelDDM().toLowerCase().equals("true")){
			med.getBtnParallelDDM().setSelection(true);
			//med.getSpinnerDomain().setEnabled(true);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}else{
			med.getBtnParallelDDM().setSelection(false);
			//med.getSpinnerDomain().setEnabled(false);
			try{
				med.getSpinnerDomain().setSelection(Integer.parseInt(obj.getDomain()));
			}catch(Exception e){
				med.getSpinnerDomain().setSelection(0);
			}
		}
		if(obj.getParallelMultiThread().toLowerCase().equals("true")){
			med.getBtnParallelMultiThread().setSelection(true);
			//med.getSpinnerThread().setEnabled(true);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}else{
			med.getBtnParallelMultiThread().setSelection(false);
			//med.getSpinnerThread().setEnabled(false);
			try{
				med.getSpinnerThread().setSelection(Integer.parseInt(obj.getThread()));
			}catch(Exception e){
				med.getSpinnerThread().setSelection(0);
			}
		}
	}
	public void ChangedMaterialParameter(String value, String widgetName){
		switch(this.StandValue){
			case "F1":
				this.changeMaterialF1Values(value,widgetName);
				break;
			case "F2":
				this.changeMaterialF2Values(value,widgetName);
				break;
			case "F3":
				this.changeMaterialF3Values(value,widgetName);
				break;
			case "F4":
				this.changeMaterialF4Values(value,widgetName);
				break;
			case "F5":
				this.changeMaterialF5Values(value,widgetName);
				break;
			case "F6":
				this.changeMaterialF6Values(value,widgetName);
				break;
			case "F7":
				this.changeMaterialF7Values(value,widgetName);
				break;
			
		}
	}
	private void changeMaterialF1Values(String value, String widgetName){
		//InitValue objInitValue = new InitValue();
		//objInitValue.readInitValueFile();
		TableData_PLog obj = tableDataPLogList.get(0);
		
		if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(value.equals("true")){
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}
				
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(value.equals("true")){
				//System.out.println("dfsd");
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());				
				
				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
				
			}else{
				
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");

				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");
				
				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
				
			}else{
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());

				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
			}
			
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(value.equals("true")){
				//System.out.println("Constant- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}else {
				//System.out.println("Constant- false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(value.equals("true")){
				//System.out.println("Table- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}else {
				//System.out.println("Table - false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value());
			}
		}
	}
	private void changeMaterialF2Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(1);
		if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(value.equals("true")){
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}
				
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(value.equals("true")){
				//System.out.println("dfsd");
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());				
				
				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
				
			}else{
				
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");

				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");
				
				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
				
			}else{
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());

				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
			}
			
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(value.equals("true")){
				//System.out.println("Constant- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}else {
				//System.out.println("Constant- false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(value.equals("true")){
				//System.out.println("Table- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}else {
				//System.out.println("Table - false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value());
			}
		}
	}
	private void changeMaterialF3Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(2);
		if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(value.equals("true")){
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}
				
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(value.equals("true")){
				//System.out.println("dfsd");
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());				
				
				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
				
			}else{
				
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");

				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");
				
				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
				
			}else{
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());

				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
			}
			
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(value.equals("true")){
				//System.out.println("Constant- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}else {
				//System.out.println("Constant- false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(value.equals("true")){
				//System.out.println("Table- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}else {
				//System.out.println("Table - false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value());
			}
		}
	}
	private void changeMaterialF4Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(3);
		if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(value.equals("true")){
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}
				
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(value.equals("true")){
				//System.out.println("dfsd");
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());				
				
				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
				
			}else{
				
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");

				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");
				
				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
				
			}else{
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());

				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
			}
			
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(value.equals("true")){
				//System.out.println("Constant- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}else {
				//System.out.println("Constant- false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(value.equals("true")){
				//System.out.println("Table- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}else {
				//System.out.println("Table - false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value());
			}
		}
	}
	private void changeMaterialF5Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(4);
		if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(value.equals("true")){
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}
				
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(value.equals("true")){
				//System.out.println("dfsd");
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());				
				
				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
				
			}else{
				
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");

				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");
				
				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
				
			}else{
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());

				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
			}
			
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(value.equals("true")){
				//System.out.println("Constant- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}else {
				//System.out.println("Constant- false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(value.equals("true")){
				//System.out.println("Table- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}else {
				//System.out.println("Table - false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value());
			}
		}
	}
	private void changeMaterialF6Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(5);
		if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(value.equals("true")){
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}
				
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(value.equals("true")){
				//System.out.println("dfsd");
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());				
				
				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
				
			}else{
				
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");

				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");
				
				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
				
			}else{
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());

				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
			}
			
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(value.equals("true")){
				//System.out.println("Constant- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}else {
				//System.out.println("Constant- false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(value.equals("true")){
				//System.out.println("Table- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}else {
				//System.out.println("Table - false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value());
			}
		}
	}
	private void changeMaterialF7Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(6);
		if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(value.equals("true")){
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}
				
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(value.equals("true")){
				//System.out.println("dfsd");
				med.getTextYoungsModulus().setText(obj.getYM_Value_T());
			}else{
				med.getTextYoungsModulus().setText(obj.getYM_Value());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());				
				
				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
				
			}else{
				
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");

				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			if(value.equals("true")){
				med.getTextFlowStress().setText(obj.getFS_Value());
				med.getTextYieldStrength().setText(" ");
				med.getTextTensileStrength().setText(" ");
				med.getTextElongation().setText(" ");
				
				med.getTextFlowStress().setEnabled(true);
				med.getTextYieldStrength().setEnabled(false);
				med.getTextTensileStrength().setEnabled(false);
				med.getTextElongation().setEnabled(false);
				
			}else{
				med.getTextFlowStress().setText(" ");
				med.getTextYieldStrength().setText(obj.getYS_Value());
				med.getTextTensileStrength().setText(obj.getTS_Value());
				med.getTextElongation().setText(obj.getE_Value());

				med.getTextFlowStress().setEnabled(false);
				med.getTextYieldStrength().setEnabled(true);
				med.getTextTensileStrength().setEnabled(true);
				med.getTextElongation().setEnabled(true);
			}
			
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(value.equals("true")){
				//System.out.println("Constant- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}else {
				//System.out.println("Constant- false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(value.equals("true")){
				//System.out.println("Table- true");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value_T());
			}else {
				//System.out.println("Table - false");
				med.getTextThermalExpansionCoefficient().setText(obj.getTEC_Value());	
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(value.equals("true")){
				med.getTextPoissonsRatio().setText(obj.getPR_Value_T());	
			}else{
				med.getTextPoissonsRatio().setText(obj.getPR_Value());
			}
		}
	}
	
	public void ChangedTextWidget(String value,String widgetName){
		//System.out.println("[ChangedTextWidget] widgetName : "+widgetName+"  ||  key : "+value);
		switch(this.StandValue){
			case "F1":
				this.saveF1Values(value, widgetName);
				this.saveSameMaterialAllData(UILabel.F1,value, widgetName);
				break;
			case "F2":
				this.saveF2Values(value, widgetName);
				this.saveSameMaterialAllData(UILabel.F2,value, widgetName);
				break;
			case "F3":
				this.saveF3Values(value, widgetName);
				this.saveSameMaterialAllData(UILabel.F3,value, widgetName);
				break;
			case "F4":
				this.saveF4Values(value, widgetName);
				this.saveSameMaterialAllData(UILabel.F4,value, widgetName);
				break;
			case "F5":
				this.saveF5Values(value, widgetName);
				this.saveSameMaterialAllData(UILabel.F5,value, widgetName);
				break;
			case "F6":
				this.saveF6Values(value, widgetName);
				this.saveSameMaterialAllData(UILabel.F6,value, widgetName);
				break;
			case "F7":
				this.saveF7Values(value, widgetName);
				this.saveSameMaterialAllData(UILabel.F7,value, widgetName);
				break;
		}
		
		this.calcAllData("noFull");
	}
	private void saveSameMaterialAllData(String standType, String value,String widgetName){
		String V = "";
		String WN = "";
		String YM_TYPE = "";
		String FS_TYPE = "";
		String TEC_TYPE = "";
		String PR_TYPE = "";
		
		if(standType.equals(UILabel.F1)){
			V = value;
			WN = widgetName;
			YM_TYPE = this.tableDataPLogList.get(0).getYM_Constant();
			FS_TYPE = this.tableDataPLogList.get(0).getFS_Constant();
			TEC_TYPE = this.tableDataPLogList.get(0).getTEC_Constant();
			PR_TYPE = this.tableDataPLogList.get(0).getPR_Constant();
		}else if(standType.equals(UILabel.F2)){
			V = value;
			WN = widgetName;
			YM_TYPE = this.tableDataPLogList.get(0).getYM_Constant();
			FS_TYPE = this.tableDataPLogList.get(0).getFS_Constant();
			TEC_TYPE = this.tableDataPLogList.get(0).getTEC_Constant();
			PR_TYPE = this.tableDataPLogList.get(0).getPR_Constant();
		}else if(standType.equals(UILabel.F3)){
			V = value;
			WN = widgetName;
			YM_TYPE = this.tableDataPLogList.get(0).getYM_Constant();
			FS_TYPE = this.tableDataPLogList.get(0).getFS_Constant();
			TEC_TYPE = this.tableDataPLogList.get(0).getTEC_Constant();
			PR_TYPE = this.tableDataPLogList.get(0).getPR_Constant();
		}else if(standType.equals(UILabel.F4)){
			V = value;
			WN = widgetName;
			YM_TYPE = this.tableDataPLogList.get(0).getYM_Constant();
			FS_TYPE = this.tableDataPLogList.get(0).getFS_Constant();
			TEC_TYPE = this.tableDataPLogList.get(0).getTEC_Constant();
			PR_TYPE = this.tableDataPLogList.get(0).getPR_Constant();
		}else if(standType.equals(UILabel.F5)){
			V = value;
			WN = widgetName;
			YM_TYPE = this.tableDataPLogList.get(0).getYM_Constant();
			FS_TYPE = this.tableDataPLogList.get(0).getFS_Constant();
			TEC_TYPE = this.tableDataPLogList.get(0).getTEC_Constant();
			PR_TYPE = this.tableDataPLogList.get(0).getPR_Constant();
		}else if(standType.equals(UILabel.F6)){
			V = value;
			WN = widgetName;
			YM_TYPE = this.tableDataPLogList.get(0).getYM_Constant();
			FS_TYPE = this.tableDataPLogList.get(0).getFS_Constant();
			TEC_TYPE = this.tableDataPLogList.get(0).getTEC_Constant();
			PR_TYPE = this.tableDataPLogList.get(0).getPR_Constant();
		}else if(standType.equals(UILabel.F7)){
			V = value;
			WN = widgetName;
			YM_TYPE = this.tableDataPLogList.get(0).getYM_Constant();
			FS_TYPE = this.tableDataPLogList.get(0).getFS_Constant();
			TEC_TYPE = this.tableDataPLogList.get(0).getTEC_Constant();
			PR_TYPE = this.tableDataPLogList.get(0).getPR_Constant();
		}
		
		for(TableData_PLog obj : this.tableDataPLogList){
			if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
				if(med.getBtnConstant1_YM().getSelection()){
					obj.setYM_Value(V);
				}else{
					obj.setYM_Value_T(V);
				}
			}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
				obj.setFS_Value(V);
			}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
				obj.setYS_Value(V);
			}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
				obj.setTS_Value(V);
			}else if(widgetName.equals(Mediator.Text_textElongation)){
				obj.setE_Value(V);
			}
			else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
				if(med.getBtnConstant2_TEC().getSelection()){
					obj.setTEC_Value(V);	
				}else{
					obj.setTEC_Value_T(V);
				}
			}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
				if(med.getBtnConstant3_PR().getSelection()){
					obj.setPR_Value(V);
				}else{
					obj.setPR_Value_T(V);
				}
			}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
				obj.setMD_Value(V);
			}
			
			else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
				obj.setYM_Constant(V);
			}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
				obj.setYM_Table(V);
			}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
				obj.setFS_Constant(V);
			}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
				obj.setFS_Table(V);
			}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
				obj.setTEC_Constant(V);
			}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
				obj.setTEC_Table(V);
			}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
				obj.setPR_Constant(V);
			}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
				obj.setPR_Table(V);
			}
			
		}
	}
	
	private void saveF1Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(0);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferX)){
			obj.setWr_chamferX(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferY)){
			obj.setWr_chamferY(value);
		}else if(widgetName.equals(Mediator.Text_textWRRound)){
			obj.setWr_round(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferX)){
			obj.setBur_chamferX(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferY)){
			obj.setBur_chamferY(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textPlateCrown)){
			obj.setP_cr(value);
		}
		
		
		else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			obj.setSpeed_different_ratio_top_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			obj.setSpeed_different_ratio_bottom_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textRollYoungsModulus)){
			obj.setYM_Roll_constant(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollPoissonsRatio)){
			obj.setPR_Roll_constant(value);
		}
			
		
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		//      
		//   ==========================>>>>>>>>>>>>>>>>>>>>>
		//
		else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			if(med.getBtnConstant1_YM().getSelection()){
				obj.setYM_Value(value);
			}else{
				obj.setYM_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			obj.setFS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			obj.setYS_Value(value);
			//System.out.println("=>"+value);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			obj.setTS_Value(value);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			obj.setE_Value(value);
		}
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			if(med.getBtnConstant2_TEC().getSelection()){
				obj.setTEC_Value(value);	
			}else{
				obj.setTEC_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			if(med.getBtnConstant3_PR().getSelection()){
				obj.setPR_Value(value);
			}else{
				obj.setPR_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			obj.setlcase_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			obj.setlcase_dt(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		//      
		//   ==========================>>>>>>>>>>>>>>>>>>>>>
		//
		else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			obj.setFS_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			obj.setFS_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
		//
		//
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		
	}
	
	private void saveF2Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(1);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferX)){
			obj.setWr_chamferX(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferY)){
			obj.setWr_chamferY(value);
		}else if(widgetName.equals(Mediator.Text_textWRRound)){
			obj.setWr_round(value);
		}
		
		
		else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferX)){
			obj.setBur_chamferX(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferY)){
			obj.setBur_chamferY(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textPlateCrown)){
			obj.setP_cr(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			obj.setSpeed_different_ratio_top_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			obj.setSpeed_different_ratio_bottom_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textRollYoungsModulus)){
			obj.setYM_Roll_constant(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollPoissonsRatio)){
			obj.setPR_Roll_constant(value);
		}
		
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		//      
		//   ==========================>>>>>>>>>>>>>>>>>>>>>
		//
		else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			if(med.getBtnConstant1_YM().getSelection()){
				obj.setYM_Value(value);
			}else{
				obj.setYM_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			obj.setFS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			obj.setYS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			obj.setTS_Value(value);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			obj.setE_Value(value);
		}
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			if(med.getBtnConstant2_TEC().getSelection()){
				obj.setTEC_Value(value);	
			}else{
				obj.setTEC_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			if(med.getBtnConstant3_PR().getSelection()){
				obj.setPR_Value(value);
			}else{
				obj.setPR_Value_T(value);
			}
		}
		//
		//
		//     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////
		
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			obj.setlcase_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			obj.setlcase_dt(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}
		
		
		
		
		else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			obj.setFS_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			obj.setFS_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF3Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(2);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferX)){
			obj.setWr_chamferX(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferY)){
			obj.setWr_chamferY(value);
		}else if(widgetName.equals(Mediator.Text_textWRRound)){
			obj.setWr_round(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferX)){
			obj.setBur_chamferX(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferY)){
			obj.setBur_chamferY(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textPlateCrown)){
			obj.setP_cr(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			obj.setSpeed_different_ratio_top_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			obj.setSpeed_different_ratio_bottom_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textRollYoungsModulus)){
			obj.setYM_Roll_constant(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollPoissonsRatio)){
			obj.setPR_Roll_constant(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			if(med.getBtnConstant1_YM().getSelection()){
				obj.setYM_Value(value);
			}else{
				obj.setYM_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			obj.setFS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			obj.setYS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			obj.setTS_Value(value);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			obj.setE_Value(value);
		}
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			if(med.getBtnConstant2_TEC().getSelection()){
				obj.setTEC_Value(value);	
			}else{
				obj.setTEC_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			if(med.getBtnConstant3_PR().getSelection()){
				obj.setPR_Value(value);
			}else{
				obj.setPR_Value_T(value);
			}
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			obj.setlcase_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			obj.setlcase_dt(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			obj.setFS_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			obj.setFS_Table(value);
		}
		
		else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF4Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(3);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferX)){
			obj.setWr_chamferX(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferY)){
			obj.setWr_chamferY(value);
		}else if(widgetName.equals(Mediator.Text_textWRRound)){
			obj.setWr_round(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferX)){
			obj.setBur_chamferX(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferY)){
			obj.setBur_chamferY(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textPlateCrown)){
			obj.setP_cr(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			obj.setSpeed_different_ratio_top_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			obj.setSpeed_different_ratio_bottom_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textRollYoungsModulus)){
			obj.setYM_Roll_constant(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollPoissonsRatio)){
			obj.setPR_Roll_constant(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			if(med.getBtnConstant1_YM().getSelection()){
				obj.setYM_Value(value);
			}else{
				obj.setYM_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			obj.setFS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			obj.setYS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			obj.setTS_Value(value);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			obj.setE_Value(value);
		}
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			if(med.getBtnConstant2_TEC().getSelection()){
				obj.setTEC_Value(value);	
			}else{
				obj.setTEC_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			if(med.getBtnConstant3_PR().getSelection()){
				obj.setPR_Value(value);
			}else{
				obj.setPR_Value_T(value);
			}
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			obj.setlcase_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			obj.setlcase_dt(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			obj.setFS_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			obj.setFS_Table(value);
		}
		
		else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF5Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(4);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferX)){
			obj.setWr_chamferX(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferY)){
			obj.setWr_chamferY(value);
		}else if(widgetName.equals(Mediator.Text_textWRRound)){
			obj.setWr_round(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferX)){
			obj.setBur_chamferX(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferY)){
			obj.setBur_chamferY(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textPlateCrown)){
			obj.setP_cr(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			obj.setSpeed_different_ratio_top_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			obj.setSpeed_different_ratio_bottom_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textRollYoungsModulus)){
			obj.setYM_Roll_constant(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollPoissonsRatio)){
			obj.setPR_Roll_constant(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			if(med.getBtnConstant1_YM().getSelection()){
				obj.setYM_Value(value);
			}else{
				obj.setYM_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			obj.setFS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			obj.setYS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			obj.setTS_Value(value);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			obj.setE_Value(value);
		}
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			if(med.getBtnConstant2_TEC().getSelection()){
				obj.setTEC_Value(value);	
			}else{
				obj.setTEC_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			if(med.getBtnConstant3_PR().getSelection()){
				obj.setPR_Value(value);
			}else{
				obj.setPR_Value_T(value);
			}
		}
		
		
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			obj.setlcase_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			obj.setlcase_dt(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			obj.setFS_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			obj.setFS_Table(value);
		}
		
		else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF6Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(5);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferX)){
			obj.setWr_chamferX(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferY)){
			obj.setWr_chamferY(value);
		}else if(widgetName.equals(Mediator.Text_textWRRound)){
			obj.setWr_round(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferX)){
			obj.setBur_chamferX(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferY)){
			obj.setBur_chamferY(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textPlateCrown)){
			obj.setP_cr(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			obj.setSpeed_different_ratio_top_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			obj.setSpeed_different_ratio_bottom_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textRollYoungsModulus)){
			obj.setYM_Roll_constant(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollPoissonsRatio)){
			obj.setPR_Roll_constant(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			if(med.getBtnConstant1_YM().getSelection()){
				obj.setYM_Value(value);
			}else{
				obj.setYM_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			obj.setFS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			obj.setYS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			obj.setTS_Value(value);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			obj.setE_Value(value);
		}
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			if(med.getBtnConstant2_TEC().getSelection()){
				obj.setTEC_Value(value);	
			}else{
				obj.setTEC_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			if(med.getBtnConstant3_PR().getSelection()){
				obj.setPR_Value(value);
			}else{
				obj.setPR_Value_T(value);
			}
		}
		
		
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			obj.setlcase_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			obj.setlcase_dt(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			obj.setFS_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			obj.setFS_Table(value);
		}
		
		else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	private void saveF7Values(String value, String widgetName){
		TableData_PLog obj = tableDataPLogList.get(6);
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			obj.setWR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			obj.setWR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			obj.setWR_ICRN(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			obj.setWr_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			obj.setWr_div_angle(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferX)){
			obj.setWr_chamferX(value);
		}else if(widgetName.equals(Mediator.Text_textWRChamferY)){
			obj.setWr_chamferY(value);
		}else if(widgetName.equals(Mediator.Text_textWRRound)){
			obj.setWr_round(value);
		}
		
		
		else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			obj.setBUR_TDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			obj.setBUR_BDIA(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			obj.setBur_len(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			obj.setBur_div_angle(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferX)){
			obj.setBur_chamferX(value);
		}else if(widgetName.equals(Mediator.TEXT_textBURChamferY)){
			obj.setBur_chamferY(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textThickness)){
			obj.setENTRY_THK(value);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			obj.setSTP_WID(value);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			obj.setSTP_LEN(value);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			obj.setENTRY_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			obj.setEXIT_TEMP(value);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			obj.setP_in(value);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			obj.setPl_m(value);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			obj.setT_div(value);
		}else if(widgetName.equals(Mediator.TEXT_textPlateCrown)){
			obj.setP_cr(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			obj.setSPEED(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			obj.setROL_GAP(value);
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			obj.setPAS_LINE(value);
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			obj.setP_CROSS(value);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			obj.setBEND(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			obj.setTORQ(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			obj.setTENS(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			obj.setF_r2p(value);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			obj.setF_r2r(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			obj.setSpeed_different_ratio_top_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			obj.setSpeed_different_ratio_bottom_roll(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			obj.setWr_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			obj.setWr_brot(value);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			obj.setBur_trot(value);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			obj.setBur_brot(value);
		}
		
		
		
		else if(widgetName.equals(Mediator.TEXT_textRollYoungsModulus)){
			obj.setYM_Roll_constant(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Roll_constant(value);
		}
		
		else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			if(med.getBtnConstant1_YM().getSelection()){
				obj.setYM_Value(value);
			}else{
				obj.setYM_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			obj.setFS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			obj.setYS_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			obj.setTS_Value(value);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			obj.setE_Value(value);
		}
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			if(med.getBtnConstant2_TEC().getSelection()){
				obj.setTEC_Value(value);	
			}else{
				obj.setTEC_Value_T(value);
			}
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			if(med.getBtnConstant3_PR().getSelection()){
				obj.setPR_Value(value);
			}else{
				obj.setPR_Value_T(value);
			}
		}
		
		
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			obj.setTEC_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			obj.setPR_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			obj.setMD_Value(value);
		}else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			obj.setLcase_time(value);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			obj.setlcase_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			obj.setPost_inc(value);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			obj.setlcase_dt(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			obj.setParallelDDM(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			obj.setDomain(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			obj.setParallelMultiThread(value);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			obj.setThread(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			obj.setYM_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			obj.setYM_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			obj.setFS_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			obj.setFS_Table(value);
		}
		
		else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			obj.setTEC_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			obj.setTEC_Table(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			obj.setPR_Constant(value);
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			obj.setPR_Table(value);
		}
	}
	
	public void Explorer_YoungsModulus(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerYoungsModulus().getShell(),SWT.OPEN);
		dlg.setText("Select Young's Modulus File");
				
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			//this.textYoungsModulus = path;
			this.ChangedTextWidget(path, Mediator.TEXT_textYoungsModulus);
			med.getTextYoungsModulus().setText(path);
		}
	}
	
	public void Explorer_FlowStress(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerFlowStress().getShell(),SWT.OPEN);
		dlg.setText("Select Flow Stress File");
				
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			//this.textYoungsModulus = path;
			this.ChangedTextWidget(path, Mediator.TEXT_textFlowStress);
			med.getTextFlowStress().setText(path);
		}
	}
	
	public void Explorer_ThermalExpansionCoefficient(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerThermalExpansionCoefficient().getShell(),SWT.OPEN);
		dlg.setText("Select Thermal Expansion Codfficient File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			this.ChangedTextWidget(path, Mediator.TEXT_textThermalExpansionCoefficient);
			med.getTextThermalExpansionCoefficient().setText(path);
		}
	}

	public void Explorer_PoissonsRatio(){
		FileDialog  dlg = new FileDialog (med.getBtnExplorerPoissonsRatio().getShell(),SWT.OPEN);
		dlg.setText("Select Poisson Ratio File");
		
		String [] extNames = {"ALL(*.*)"};
		String [] extType = {"*.*"};
		
		dlg.setFilterNames(extNames);
		dlg.setFilterExtensions(extType);
		
		dlg.setFilterNames(extNames);
		String path = dlg.open();
		if (path == null){
			return;
		}else {
			//this.textPoissonsRatio = path;
			this.ChangedTextWidget(path, Mediator.TEXT_textPoissonsRatio);
			med.getTextPoissonsRatio().setText(path);
		}
	}
	
	public void ChangedTabFolder(int index){
		if(index == 0){
			//checkObj();
			this.loadPlogTables();
			//checkObj();
		}else if(index == 1){
			//checkObj();
			med.getBtnF1().setSelection(true);
			med.getBtnF2().setSelection(false);
			med.getBtnF3().setSelection(false);
			med.getBtnF4().setSelection(false);
			med.getBtnF5().setSelection(false);
			med.getBtnF6().setSelection(false);
			med.getBtnF7().setSelection(false);
			this.ChangedSTANDValue("F1");
			//checkObj();
		}
	}
	
	private void checkObj(){
		for(TableData_PLog obj : this.tableDataPLogList){
			obj.printAllData();
			break;
		}
	}
	
	private void loadPlogTables(){
		ArrayList<String> initDataList = new ArrayList<String>();
		initDataList.add("STRIP NO,STHK,SWID,SLEN,SWET,PTHK,PWID,PLEN,PWET,,,,,,,");
		String line2 = TableDataSlabPlateInfoObj.getSTRIP_NO()+","+
				TableDataSlabPlateInfoObj.getSTHK()+","+
				TableDataSlabPlateInfoObj.getSWID()+","+
				TableDataSlabPlateInfoObj.getSLEN()+","+
				TableDataSlabPlateInfoObj.getSWET()+","+
				TableDataSlabPlateInfoObj.getPTHK()+","+
				TableDataSlabPlateInfoObj.getPWID()+","+
				TableDataSlabPlateInfoObj.getPLEN()+","+
				TableDataSlabPlateInfoObj.getPWET()+","+
				",,,,,,";
		initDataList.add(line2);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("VAR1,VAR2,VAR3,VAR4,VAR5,VAR6,VAR7,VAR8,VAR9,VAR10,VAR11,VAR12,VAR13,VAR14,VAR15,VAR16");
		String line5 = TableDataVariableObj.getVAR1()+","+
				TableDataVariableObj.getVAR2()+","+
				TableDataVariableObj.getVAR3()+","+
				TableDataVariableObj.getVAR4()+","+
				TableDataVariableObj.getVAR5()+","+
				TableDataVariableObj.getVAR6()+","+
				TableDataVariableObj.getVAR7()+","+
				TableDataVariableObj.getVAR8()+","+
				TableDataVariableObj.getVAR9()+","+
				TableDataVariableObj.getVAR10()+","+
				TableDataVariableObj.getVAR11()+","+
				TableDataVariableObj.getVAR12()+","+
				TableDataVariableObj.getVAR13()+","+
				TableDataVariableObj.getVAR14()+","+
				TableDataVariableObj.getVAR15()+","+
				TableDataVariableObj.getVAR16();
		
		initDataList.add(line5);
		initDataList.add(",,,,,,,,,,,,,,,");
		initDataList.add("STAND,F1,F2,F3,F4,F5,F6,F7,,,,,,,,");
		String line8 = "BUR TDIA"+","+
					tableDataPLogList.get(0).getBUR_TDIA()+","+
					tableDataPLogList.get(1).getBUR_TDIA()+","+
					tableDataPLogList.get(2).getBUR_TDIA()+","+
					tableDataPLogList.get(3).getBUR_TDIA()+","+
					tableDataPLogList.get(4).getBUR_TDIA()+","+
					tableDataPLogList.get(5).getBUR_TDIA()+","+
					tableDataPLogList.get(6).getBUR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line8);
		String line9 = "BUR BDIA"+","+
					tableDataPLogList.get(0).getBUR_BDIA()+","+
					tableDataPLogList.get(1).getBUR_BDIA()+","+
					tableDataPLogList.get(2).getBUR_BDIA()+","+
					tableDataPLogList.get(3).getBUR_BDIA()+","+
					tableDataPLogList.get(4).getBUR_BDIA()+","+
					tableDataPLogList.get(5).getBUR_BDIA()+","+
					tableDataPLogList.get(6).getBUR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line9);
		String line10 = "WR TDIA"+","+
					tableDataPLogList.get(0).getWR_TDIA()+","+
					tableDataPLogList.get(1).getWR_TDIA()+","+
					tableDataPLogList.get(2).getWR_TDIA()+","+
					tableDataPLogList.get(3).getWR_TDIA()+","+
					tableDataPLogList.get(4).getWR_TDIA()+","+
					tableDataPLogList.get(5).getWR_TDIA()+","+
					tableDataPLogList.get(6).getWR_TDIA()+","+
					",,,,,,,";
		initDataList.add(line10);
		String line11 = "WR BDIA"+","+
					tableDataPLogList.get(0).getWR_BDIA()+","+
					tableDataPLogList.get(1).getWR_BDIA()+","+
					tableDataPLogList.get(2).getWR_BDIA()+","+
					tableDataPLogList.get(3).getWR_BDIA()+","+
					tableDataPLogList.get(4).getWR_BDIA()+","+
					tableDataPLogList.get(5).getWR_BDIA()+","+
					tableDataPLogList.get(6).getWR_BDIA()+","+
					",,,,,,,";
		initDataList.add(line11);
		String line12 = "WR ICRN"+","+
					tableDataPLogList.get(0).getWR_ICRN()+","+
					tableDataPLogList.get(1).getWR_ICRN()+","+
					tableDataPLogList.get(2).getWR_ICRN()+","+
					tableDataPLogList.get(3).getWR_ICRN()+","+
					tableDataPLogList.get(4).getWR_ICRN()+","+
					tableDataPLogList.get(5).getWR_ICRN()+","+
					tableDataPLogList.get(6).getWR_ICRN()+","+
					",,,,,,,";
		initDataList.add(line12);
		String line13 = "ENTRY THK"+","+
					tableDataPLogList.get(0).getENTRY_THK()+","+
					tableDataPLogList.get(1).getENTRY_THK()+","+
					tableDataPLogList.get(2).getENTRY_THK()+","+
					tableDataPLogList.get(3).getENTRY_THK()+","+
					tableDataPLogList.get(4).getENTRY_THK()+","+
					tableDataPLogList.get(5).getENTRY_THK()+","+
					tableDataPLogList.get(6).getENTRY_THK()+","+
					",,,,,,,";
		initDataList.add(line13);
		String line14 = "EXIT THK"+","+
					tableDataPLogList.get(0).getEXIT_THK()+","+
					tableDataPLogList.get(1).getEXIT_THK()+","+
					tableDataPLogList.get(2).getEXIT_THK()+","+
					tableDataPLogList.get(3).getEXIT_THK()+","+
					tableDataPLogList.get(4).getEXIT_THK()+","+
					tableDataPLogList.get(5).getEXIT_THK()+","+
					tableDataPLogList.get(6).getEXIT_THK()+","+
					",,,,,,,";
		initDataList.add(line14);
		String line15 = "PAS LINE"+","+
					tableDataPLogList.get(0).getPAS_LINE()+","+
					tableDataPLogList.get(1).getPAS_LINE()+","+
					tableDataPLogList.get(2).getPAS_LINE()+","+
					tableDataPLogList.get(3).getPAS_LINE()+","+
					tableDataPLogList.get(4).getPAS_LINE()+","+
					tableDataPLogList.get(5).getPAS_LINE()+","+
					tableDataPLogList.get(6).getPAS_LINE()+","+
					",,,,,,,";
		initDataList.add(line15);
		String line16 = "ROL GAP"+","+
					tableDataPLogList.get(0).getROL_GAP()+","+
					tableDataPLogList.get(1).getROL_GAP()+","+
					tableDataPLogList.get(2).getROL_GAP()+","+
					tableDataPLogList.get(3).getROL_GAP()+","+
					tableDataPLogList.get(4).getROL_GAP()+","+
					tableDataPLogList.get(5).getROL_GAP()+","+
					tableDataPLogList.get(6).getROL_GAP()+","+
					",,,,,,,";
		initDataList.add(line16);
		String line17 = "STP WID"+","+
					tableDataPLogList.get(0).getSTP_WID()+","+
					tableDataPLogList.get(1).getSTP_WID()+","+
					tableDataPLogList.get(2).getSTP_WID()+","+
					tableDataPLogList.get(3).getSTP_WID()+","+
					tableDataPLogList.get(4).getSTP_WID()+","+
					tableDataPLogList.get(5).getSTP_WID()+","+
					tableDataPLogList.get(6).getSTP_WID()+","+
					",,,,,,,";
		initDataList.add(line17);
		String line18 = "STP LEN"+","+
					tableDataPLogList.get(0).getSTP_LEN()+","+
					tableDataPLogList.get(1).getSTP_LEN()+","+
					tableDataPLogList.get(2).getSTP_LEN()+","+
					tableDataPLogList.get(3).getSTP_LEN()+","+
					tableDataPLogList.get(4).getSTP_LEN()+","+
					tableDataPLogList.get(5).getSTP_LEN()+","+
					tableDataPLogList.get(6).getSTP_LEN()+","+
					",,,,,,,";
		initDataList.add(line18);
		String line19 = "ENTRY TEMP"+","+
					tableDataPLogList.get(0).getENTRY_TEMP()+","+
					tableDataPLogList.get(1).getENTRY_TEMP()+","+
					tableDataPLogList.get(2).getENTRY_TEMP()+","+
					tableDataPLogList.get(3).getENTRY_TEMP()+","+
					tableDataPLogList.get(4).getENTRY_TEMP()+","+
					tableDataPLogList.get(5).getENTRY_TEMP()+","+
					tableDataPLogList.get(6).getENTRY_TEMP()+","+
					",,,,,,,";
		initDataList.add(line19);
		String line20 = "EXIT TEMP"+","+
					tableDataPLogList.get(0).getEXIT_TEMP()+","+
					tableDataPLogList.get(1).getEXIT_TEMP()+","+
					tableDataPLogList.get(2).getEXIT_TEMP()+","+
					tableDataPLogList.get(3).getEXIT_TEMP()+","+
					tableDataPLogList.get(4).getEXIT_TEMP()+","+
					tableDataPLogList.get(5).getEXIT_TEMP()+","+
					tableDataPLogList.get(6).getEXIT_TEMP()+","+
					",,,,,,,";
		initDataList.add(line20);
		String line21 = "FRCE"+","+
					tableDataPLogList.get(0).getFRCE()+","+
					tableDataPLogList.get(1).getFRCE()+","+
					tableDataPLogList.get(2).getFRCE()+","+
					tableDataPLogList.get(3).getFRCE()+","+
					tableDataPLogList.get(4).getFRCE()+","+
					tableDataPLogList.get(5).getFRCE()+","+
					tableDataPLogList.get(6).getFRCE()+","+					
					",,,,,,,";
		initDataList.add(line21);
		String line22 = "TORQ"+","+
					tableDataPLogList.get(0).getTORQ()+","+					
					tableDataPLogList.get(1).getTORQ()+","+
					tableDataPLogList.get(2).getTORQ()+","+
					tableDataPLogList.get(3).getTORQ()+","+
					tableDataPLogList.get(4).getTORQ()+","+
					tableDataPLogList.get(5).getTORQ()+","+
					tableDataPLogList.get(6).getTORQ()+","+
					",,,,,,,";
		initDataList.add(line22);
		String line23 = "SPEED(mpm)"+","+
					tableDataPLogList.get(0).getSPEED()+","+
					tableDataPLogList.get(1).getSPEED()+","+
					tableDataPLogList.get(2).getSPEED()+","+
					tableDataPLogList.get(3).getSPEED()+","+
					tableDataPLogList.get(4).getSPEED()+","+
					tableDataPLogList.get(5).getSPEED()+","+
					tableDataPLogList.get(6).getSPEED()+","+					
					",,,,,,,";
		initDataList.add(line23);
		String line24 = "BEND"+","+
					tableDataPLogList.get(0).getBEND()+","+
					tableDataPLogList.get(1).getBEND()+","+
					tableDataPLogList.get(2).getBEND()+","+
					tableDataPLogList.get(3).getBEND()+","+
					tableDataPLogList.get(4).getBEND()+","+
					tableDataPLogList.get(5).getBEND()+","+
					tableDataPLogList.get(6).getBEND()+","+
					",,,,,,,";
		initDataList.add(line24);
		String line25 = "P-CROSS"+","+
					tableDataPLogList.get(0).getP_CROSS()+","+
					tableDataPLogList.get(1).getP_CROSS()+","+
					tableDataPLogList.get(2).getP_CROSS()+","+
					tableDataPLogList.get(3).getP_CROSS()+","+
					tableDataPLogList.get(4).getP_CROSS()+","+
					tableDataPLogList.get(5).getP_CROSS()+","+
					tableDataPLogList.get(6).getP_CROSS()+","+
					",,,,,,,";
		initDataList.add(line25);
		String line26 = "TENS"+","+
					tableDataPLogList.get(0).getTENS()+","+
					tableDataPLogList.get(1).getTENS()+","+
					tableDataPLogList.get(2).getTENS()+","+
					tableDataPLogList.get(3).getTENS()+","+
					tableDataPLogList.get(4).getTENS()+","+
					tableDataPLogList.get(5).getTENS()+","+
					tableDataPLogList.get(6).getTENS()+","+
					",,,,,,,";
		initDataList.add(line26);
		String line27 = "ROL TIM"+","+
					tableDataPLogList.get(0).getROL_TIM()+","+
					tableDataPLogList.get(1).getROL_TIM()+","+
					tableDataPLogList.get(2).getROL_TIM()+","+
					tableDataPLogList.get(3).getROL_TIM()+","+
					tableDataPLogList.get(4).getROL_TIM()+","+
					tableDataPLogList.get(5).getROL_TIM()+","+
					tableDataPLogList.get(6).getROL_TIM()+","+					
					",,,,,,,";
		initDataList.add(line27);
		String line28 = "IDL TIM"+","+
					tableDataPLogList.get(0).getIDL_TIM()+","+
					tableDataPLogList.get(1).getIDL_TIM()+","+
					tableDataPLogList.get(2).getIDL_TIM()+","+
					tableDataPLogList.get(3).getIDL_TIM()+","+
					tableDataPLogList.get(4).getIDL_TIM()+","+
					tableDataPLogList.get(5).getIDL_TIM()+","+
					tableDataPLogList.get(6).getIDL_TIM()+","+
					",,,,,,,";
		initDataList.add(line28);
		String line29 = "BUR WEAR"+","+
					tableDataPLogList.get(0).getBUR_WEAR()+","+
					tableDataPLogList.get(1).getBUR_WEAR()+","+
					tableDataPLogList.get(2).getBUR_WEAR()+","+
					tableDataPLogList.get(3).getBUR_WEAR()+","+
					tableDataPLogList.get(4).getBUR_WEAR()+","+
					tableDataPLogList.get(5).getBUR_WEAR()+","+
					tableDataPLogList.get(6).getBUR_WEAR()+","+					
					",,,,,,,";
		initDataList.add(line29);
		String line30 = "WR WEAR"+","+ 
					tableDataPLogList.get(0).getWR_WEAR()+","+
					tableDataPLogList.get(1).getWR_WEAR()+","+
					tableDataPLogList.get(2).getWR_WEAR()+","+
					tableDataPLogList.get(3).getWR_WEAR()+","+
					tableDataPLogList.get(4).getWR_WEAR()+","+
					tableDataPLogList.get(5).getWR_WEAR()+","+
					tableDataPLogList.get(6).getWR_WEAR()+","+
					",,,,,,,";
		initDataList.add(line30);
		String line31 = "WR THRM"+","+
					tableDataPLogList.get(0).getWR_THRM()+","+
					tableDataPLogList.get(1).getWR_THRM()+","+
					tableDataPLogList.get(2).getWR_THRM()+","+
					tableDataPLogList.get(3).getWR_THRM()+","+
					tableDataPLogList.get(4).getWR_THRM()+","+
					tableDataPLogList.get(5).getWR_THRM()+","+
					tableDataPLogList.get(6).getWR_THRM()+","+					
					",,,,,,,";
		initDataList.add(line31);
//		this.saveOtherValues();		
		this.parsingPLogFile(initDataList);
	}
	
	private void saveOtherValues(){
		this.otherValueList = new ArrayList<String>();
		String ov_line1 = "wr_len"+","+
						tableDataPLogList.get(0).getWr_len()+","+
						tableDataPLogList.get(1).getWr_len()+","+
						tableDataPLogList.get(2).getWr_len()+","+
						tableDataPLogList.get(3).getWr_len()+","+
						tableDataPLogList.get(4).getWr_len()+","+
						tableDataPLogList.get(5).getWr_len()+","+
						tableDataPLogList.get(6).getWr_len()+","+
						",,,,,,,";
		String ov_line2 = "wr_div_angle"+","+
				tableDataPLogList.get(0).getWr_div_angle()+","+
				tableDataPLogList.get(1).getWr_div_angle()+","+
				tableDataPLogList.get(2).getWr_div_angle()+","+
				tableDataPLogList.get(3).getWr_div_angle()+","+
				tableDataPLogList.get(4).getWr_div_angle()+","+
				tableDataPLogList.get(5).getWr_div_angle()+","+
				tableDataPLogList.get(6).getWr_div_angle()+","+
				",,,,,,,";
		String ov_line3 = "bur_len"+","+
				tableDataPLogList.get(0).getBur_len()+","+
				tableDataPLogList.get(1).getBur_len()+","+
				tableDataPLogList.get(2).getBur_len()+","+
				tableDataPLogList.get(3).getBur_len()+","+
				tableDataPLogList.get(4).getBur_len()+","+
				tableDataPLogList.get(5).getBur_len()+","+
				tableDataPLogList.get(6).getBur_len()+","+
				",,,,,,,";
		String ov_line4 = "bur_div_angle"+","+
				tableDataPLogList.get(0).getBur_div_angle()+","+
				tableDataPLogList.get(1).getBur_div_angle()+","+
				tableDataPLogList.get(2).getBur_div_angle()+","+
				tableDataPLogList.get(3).getBur_div_angle()+","+
				tableDataPLogList.get(4).getBur_div_angle()+","+
				tableDataPLogList.get(5).getBur_div_angle()+","+
				tableDataPLogList.get(6).getBur_div_angle()+","+
				",,,,,,,";
		String ov_line5 = "p_in"+","+
				tableDataPLogList.get(0).getP_in()+","+
				tableDataPLogList.get(1).getP_in()+","+
				tableDataPLogList.get(2).getP_in()+","+
				tableDataPLogList.get(3).getP_in()+","+
				tableDataPLogList.get(4).getP_in()+","+
				tableDataPLogList.get(5).getP_in()+","+
				tableDataPLogList.get(6).getP_in()+","+
				",,,,,,,";
		String ov_line6 = "pl_m"+","+
				tableDataPLogList.get(0).getPl_m()+","+
				tableDataPLogList.get(1).getPl_m()+","+
				tableDataPLogList.get(2).getPl_m()+","+
				tableDataPLogList.get(3).getPl_m()+","+
				tableDataPLogList.get(4).getPl_m()+","+
				tableDataPLogList.get(5).getPl_m()+","+
				tableDataPLogList.get(6).getPl_m()+","+
				",,,,,,,";
		String ov_line7 = "t_div"+","+
				tableDataPLogList.get(0).getT_div()+","+
				tableDataPLogList.get(1).getT_div()+","+
				tableDataPLogList.get(2).getT_div()+","+
				tableDataPLogList.get(3).getT_div()+","+
				tableDataPLogList.get(4).getT_div()+","+
				tableDataPLogList.get(5).getT_div()+","+
				tableDataPLogList.get(6).getT_div()+","+
				",,,,,,,";
		String ov_line8 = "f_r2p"+","+
				tableDataPLogList.get(0).getF_r2p()+","+
				tableDataPLogList.get(1).getF_r2p()+","+
				tableDataPLogList.get(2).getF_r2p()+","+
				tableDataPLogList.get(3).getF_r2p()+","+
				tableDataPLogList.get(4).getF_r2p()+","+
				tableDataPLogList.get(5).getF_r2p()+","+
				tableDataPLogList.get(6).getF_r2p()+","+
				",,,,,,,";
		String ov_line9 = "f_r2r"+","+
				tableDataPLogList.get(0).getF_r2r()+","+
				tableDataPLogList.get(1).getF_r2r()+","+
				tableDataPLogList.get(2).getF_r2r()+","+
				tableDataPLogList.get(3).getF_r2r()+","+
				tableDataPLogList.get(4).getF_r2r()+","+
				tableDataPLogList.get(5).getF_r2r()+","+
				tableDataPLogList.get(6).getF_r2r()+","+
				",,,,,,,";
		
		String ov_line10 = "vel_rate_top"+","+
				tableDataPLogList.get(0).getSpeed_different_ratio_top_roll()+","+
				tableDataPLogList.get(1).getSpeed_different_ratio_top_roll()+","+
				tableDataPLogList.get(2).getSpeed_different_ratio_top_roll()+","+
				tableDataPLogList.get(3).getSpeed_different_ratio_top_roll()+","+
				tableDataPLogList.get(4).getSpeed_different_ratio_top_roll()+","+
				tableDataPLogList.get(5).getSpeed_different_ratio_top_roll()+","+
				tableDataPLogList.get(6).getSpeed_different_ratio_top_roll()+","+
				",,,,,,,";
		String ov_line11 = "vel_rate_bottom"+","+
				tableDataPLogList.get(0).getSpeed_different_ratio_bottom_roll()+","+
				tableDataPLogList.get(1).getSpeed_different_ratio_bottom_roll()+","+
				tableDataPLogList.get(2).getSpeed_different_ratio_bottom_roll()+","+
				tableDataPLogList.get(3).getSpeed_different_ratio_bottom_roll()+","+
				tableDataPLogList.get(4).getSpeed_different_ratio_bottom_roll()+","+
				tableDataPLogList.get(5).getSpeed_different_ratio_bottom_roll()+","+
				tableDataPLogList.get(6).getSpeed_different_ratio_bottom_roll()+","+
				",,,,,,,";
		String ov_line12 = "wr_trot"+","+
				tableDataPLogList.get(0).getWr_trot()+","+
				tableDataPLogList.get(1).getWr_trot()+","+
				tableDataPLogList.get(2).getWr_trot()+","+
				tableDataPLogList.get(3).getWr_trot()+","+
				tableDataPLogList.get(4).getWr_trot()+","+
				tableDataPLogList.get(5).getWr_trot()+","+
				tableDataPLogList.get(6).getWr_trot()+","+
				",,,,,,,";
		String ov_line13 = "wr_brot"+","+
				tableDataPLogList.get(0).getWr_brot()+","+
				tableDataPLogList.get(1).getWr_brot()+","+
				tableDataPLogList.get(2).getWr_brot()+","+
				tableDataPLogList.get(3).getWr_brot()+","+
				tableDataPLogList.get(4).getWr_brot()+","+
				tableDataPLogList.get(5).getWr_brot()+","+
				tableDataPLogList.get(6).getWr_brot()+","+
				",,,,,,,";
		String ov_line14 = "bur_trot"+","+
				tableDataPLogList.get(0).getBur_trot()+","+
				tableDataPLogList.get(1).getBur_trot()+","+
				tableDataPLogList.get(2).getBur_trot()+","+
				tableDataPLogList.get(3).getBur_trot()+","+
				tableDataPLogList.get(4).getBur_trot()+","+
				tableDataPLogList.get(5).getBur_trot()+","+
				tableDataPLogList.get(6).getBur_trot()+","+
				",,,,,,,";
		String ov_line15 = "bur_brot"+","+
				tableDataPLogList.get(0).getBur_brot()+","+
				tableDataPLogList.get(1).getBur_brot()+","+
				tableDataPLogList.get(2).getBur_brot()+","+
				tableDataPLogList.get(3).getBur_brot()+","+
				tableDataPLogList.get(4).getBur_brot()+","+
				tableDataPLogList.get(5).getBur_brot()+","+
				tableDataPLogList.get(6).getBur_brot()+","+
				",,,,,,,";
		String ov_line16 = "YM_Constant"+","+
				tableDataPLogList.get(0).getYM_Constant()+","+
				tableDataPLogList.get(1).getYM_Constant()+","+
				tableDataPLogList.get(2).getYM_Constant()+","+
				tableDataPLogList.get(3).getYM_Constant()+","+
				tableDataPLogList.get(4).getYM_Constant()+","+
				tableDataPLogList.get(5).getYM_Constant()+","+
				tableDataPLogList.get(6).getYM_Constant()+","+
				",,,,,,,";
		String ov_line17 = "YM_Table"+","+
				tableDataPLogList.get(0).getYM_Table()+","+
				tableDataPLogList.get(1).getYM_Table()+","+
				tableDataPLogList.get(2).getYM_Table()+","+
				tableDataPLogList.get(3).getYM_Table()+","+
				tableDataPLogList.get(4).getYM_Table()+","+
				tableDataPLogList.get(5).getYM_Table()+","+
				tableDataPLogList.get(6).getYM_Table()+","+
				",,,,,,,";
		String ov_line18 = "YM_Value"+","+
				tableDataPLogList.get(0).getYM_Value()+","+
				tableDataPLogList.get(1).getYM_Value()+","+
				tableDataPLogList.get(2).getYM_Value()+","+
				tableDataPLogList.get(3).getYM_Value()+","+
				tableDataPLogList.get(4).getYM_Value()+","+
				tableDataPLogList.get(5).getYM_Value()+","+
				tableDataPLogList.get(6).getYM_Value()+","+
				",,,,,,,";
		
		String ov_line19 = "TEC_Constant"+","+
				tableDataPLogList.get(0).getTEC_Constant()+","+
				tableDataPLogList.get(1).getTEC_Constant()+","+
				tableDataPLogList.get(2).getTEC_Constant()+","+
				tableDataPLogList.get(3).getTEC_Constant()+","+
				tableDataPLogList.get(4).getTEC_Constant()+","+
				tableDataPLogList.get(5).getTEC_Constant()+","+
				tableDataPLogList.get(6).getTEC_Constant()+","+
				",,,,,,,";
		String ov_line20 = "TEC_Table"+","+
				tableDataPLogList.get(0).getTEC_Table()+","+
				tableDataPLogList.get(1).getTEC_Table()+","+
				tableDataPLogList.get(2).getTEC_Table()+","+
				tableDataPLogList.get(3).getTEC_Table()+","+
				tableDataPLogList.get(4).getTEC_Table()+","+
				tableDataPLogList.get(5).getTEC_Table()+","+
				tableDataPLogList.get(6).getTEC_Table()+","+
				",,,,,,,";
		String ov_line21 = "TEC_Value"+","+
				tableDataPLogList.get(0).getTEC_Value()+","+
				tableDataPLogList.get(1).getTEC_Value()+","+
				tableDataPLogList.get(2).getTEC_Value()+","+
				tableDataPLogList.get(3).getTEC_Value()+","+
				tableDataPLogList.get(4).getTEC_Value()+","+
				tableDataPLogList.get(5).getTEC_Value()+","+
				tableDataPLogList.get(6).getTEC_Value()+","+
				",,,,,,,";
		String ov_line22 = "PR_Constant"+","+
				tableDataPLogList.get(0).getPR_Constant()+","+
				tableDataPLogList.get(1).getPR_Constant()+","+
				tableDataPLogList.get(2).getPR_Constant()+","+
				tableDataPLogList.get(3).getPR_Constant()+","+
				tableDataPLogList.get(4).getPR_Constant()+","+
				tableDataPLogList.get(5).getPR_Constant()+","+
				tableDataPLogList.get(6).getPR_Constant()+","+
				",,,,,,,";
		String ov_line23 = "PR_Table"+","+
				tableDataPLogList.get(0).getPR_Table()+","+
				tableDataPLogList.get(1).getPR_Table()+","+
				tableDataPLogList.get(2).getPR_Table()+","+
				tableDataPLogList.get(3).getPR_Table()+","+
				tableDataPLogList.get(4).getPR_Table()+","+
				tableDataPLogList.get(5).getPR_Table()+","+
				tableDataPLogList.get(6).getPR_Table()+","+
				",,,,,,,";
		String ov_line24 = "PR_Value"+","+
				tableDataPLogList.get(0).getPR_Value()+","+
				tableDataPLogList.get(1).getPR_Value()+","+
				tableDataPLogList.get(2).getPR_Value()+","+
				tableDataPLogList.get(3).getPR_Value()+","+
				tableDataPLogList.get(4).getPR_Value()+","+
				tableDataPLogList.get(5).getPR_Value()+","+
				tableDataPLogList.get(6).getPR_Value()+","+
				",,,,,,,";
		String ov_line25 = "MD_Value"+","+
				tableDataPLogList.get(0).getMD_Value()+","+
				tableDataPLogList.get(1).getMD_Value()+","+
				tableDataPLogList.get(2).getMD_Value()+","+
				tableDataPLogList.get(3).getMD_Value()+","+
				tableDataPLogList.get(4).getMD_Value()+","+
				tableDataPLogList.get(5).getMD_Value()+","+
				tableDataPLogList.get(6).getMD_Value()+","+
				",,,,,,,";
		String ov_line26 = "lcase_time"+","+
				tableDataPLogList.get(0).getLcase_time()+","+
				tableDataPLogList.get(1).getLcase_time()+","+
				tableDataPLogList.get(2).getLcase_time()+","+
				tableDataPLogList.get(3).getLcase_time()+","+
				tableDataPLogList.get(4).getLcase_time()+","+
				tableDataPLogList.get(5).getLcase_time()+","+
				tableDataPLogList.get(6).getLcase_time()+","+
				",,,,,,,";
		String ov_line27 = "lcase_inc"+","+
				tableDataPLogList.get(0).getlcase_inc()+","+
				tableDataPLogList.get(1).getlcase_inc()+","+
				tableDataPLogList.get(2).getlcase_inc()+","+
				tableDataPLogList.get(3).getlcase_inc()+","+
				tableDataPLogList.get(4).getlcase_inc()+","+
				tableDataPLogList.get(5).getlcase_inc()+","+
				tableDataPLogList.get(6).getlcase_inc()+","+
				",,,,,,,";
		String ov_line28 = "post_inc"+","+
				tableDataPLogList.get(0).getPost_inc()+","+
				tableDataPLogList.get(1).getPost_inc()+","+
				tableDataPLogList.get(2).getPost_inc()+","+
				tableDataPLogList.get(3).getPost_inc()+","+
				tableDataPLogList.get(4).getPost_inc()+","+
				tableDataPLogList.get(5).getPost_inc()+","+
				tableDataPLogList.get(6).getPost_inc()+","+
				",,,,,,,";
		String ov_line29 = "lcase_dt"+","+
				tableDataPLogList.get(0).getlcase_dt()+","+
				tableDataPLogList.get(1).getlcase_dt()+","+
				tableDataPLogList.get(2).getlcase_dt()+","+
				tableDataPLogList.get(3).getlcase_dt()+","+
				tableDataPLogList.get(4).getlcase_dt()+","+
				tableDataPLogList.get(5).getlcase_dt()+","+
				tableDataPLogList.get(6).getlcase_dt()+","+
				",,,,,,,";
		String ov_line30 = "ltime_scale"+","+
				tableDataPLogList.get(0).getLtime_scale()+","+
				tableDataPLogList.get(1).getLtime_scale()+","+
				tableDataPLogList.get(2).getLtime_scale()+","+
				tableDataPLogList.get(3).getLtime_scale()+","+
				tableDataPLogList.get(4).getLtime_scale()+","+
				tableDataPLogList.get(5).getLtime_scale()+","+
				tableDataPLogList.get(6).getLtime_scale()+","+
				",,,,,,,";
		String ov_line31 = "ParallelDDM"+","+
				tableDataPLogList.get(0).getParallelDDM()+","+
				tableDataPLogList.get(1).getParallelDDM()+","+
				tableDataPLogList.get(2).getParallelDDM()+","+
				tableDataPLogList.get(3).getParallelDDM()+","+
				tableDataPLogList.get(4).getParallelDDM()+","+
				tableDataPLogList.get(5).getParallelDDM()+","+
				tableDataPLogList.get(6).getParallelDDM()+","+
				",,,,,,,";
		String ov_line32 = "Domain"+","+
				tableDataPLogList.get(0).getDomain()+","+
				tableDataPLogList.get(1).getDomain()+","+
				tableDataPLogList.get(2).getDomain()+","+
				tableDataPLogList.get(3).getDomain()+","+
				tableDataPLogList.get(4).getDomain()+","+
				tableDataPLogList.get(5).getDomain()+","+
				tableDataPLogList.get(6).getDomain()+","+
				",,,,,,,";
		String ov_line33 = "ParallelMultiThread"+","+
				tableDataPLogList.get(0).getParallelMultiThread()+","+
				tableDataPLogList.get(1).getParallelMultiThread()+","+
				tableDataPLogList.get(2).getParallelMultiThread()+","+
				tableDataPLogList.get(3).getParallelMultiThread()+","+
				tableDataPLogList.get(4).getParallelMultiThread()+","+
				tableDataPLogList.get(5).getParallelMultiThread()+","+
				tableDataPLogList.get(6).getParallelMultiThread()+","+
				",,,,,,,";
		String ov_line34 = "Thread"+","+
				tableDataPLogList.get(0).getThread()+","+
				tableDataPLogList.get(1).getThread()+","+
				tableDataPLogList.get(2).getThread()+","+
				tableDataPLogList.get(3).getThread()+","+
				tableDataPLogList.get(4).getThread()+","+
				tableDataPLogList.get(5).getThread()+","+
				tableDataPLogList.get(6).getThread()+","+
				",,,,,,,";
		
		
		String ov_line35 = "FS_Constant"+","+
				tableDataPLogList.get(0).getFS_Constant()+","+
				tableDataPLogList.get(1).getFS_Constant()+","+
				tableDataPLogList.get(2).getFS_Constant()+","+
				tableDataPLogList.get(3).getFS_Constant()+","+
				tableDataPLogList.get(4).getFS_Constant()+","+
				tableDataPLogList.get(5).getFS_Constant()+","+
				tableDataPLogList.get(6).getFS_Constant()+","+
				",,,,,,,";
		String ov_line36 = "FS_Table"+","+
				tableDataPLogList.get(0).getFS_Table()+","+
				tableDataPLogList.get(1).getFS_Table()+","+
				tableDataPLogList.get(2).getFS_Table()+","+
				tableDataPLogList.get(3).getFS_Table()+","+
				tableDataPLogList.get(4).getFS_Table()+","+
				tableDataPLogList.get(5).getFS_Table()+","+
				tableDataPLogList.get(6).getFS_Table()+","+
				",,,,,,,";
		String ov_line37 = "FS_Value"+","+
				tableDataPLogList.get(0).getFS_Value()+","+
				tableDataPLogList.get(1).getFS_Value()+","+
				tableDataPLogList.get(2).getFS_Value()+","+
				tableDataPLogList.get(3).getFS_Value()+","+
				tableDataPLogList.get(4).getFS_Value()+","+
				tableDataPLogList.get(5).getFS_Value()+","+
				tableDataPLogList.get(6).getFS_Value()+","+
				",,,,,,,";
		String ov_line38 = "YS_Value"+","+
				tableDataPLogList.get(0).getYS_Value()+","+
				tableDataPLogList.get(1).getYS_Value()+","+
				tableDataPLogList.get(2).getYS_Value()+","+
				tableDataPLogList.get(3).getYS_Value()+","+
				tableDataPLogList.get(4).getYS_Value()+","+
				tableDataPLogList.get(5).getYS_Value()+","+
				tableDataPLogList.get(6).getYS_Value()+","+
				",,,,,,,";
		String ov_line39 = "TS_Value"+","+
				tableDataPLogList.get(0).getTS_Value()+","+
				tableDataPLogList.get(1).getTS_Value()+","+
				tableDataPLogList.get(2).getTS_Value()+","+
				tableDataPLogList.get(3).getTS_Value()+","+
				tableDataPLogList.get(4).getTS_Value()+","+
				tableDataPLogList.get(5).getTS_Value()+","+
				tableDataPLogList.get(6).getTS_Value()+","+
				",,,,,,,";
		String ov_line40 = "E_Value"+","+
				tableDataPLogList.get(0).getE_Value()+","+
				tableDataPLogList.get(1).getE_Value()+","+
				tableDataPLogList.get(2).getE_Value()+","+
				tableDataPLogList.get(3).getE_Value()+","+
				tableDataPLogList.get(4).getE_Value()+","+
				tableDataPLogList.get(5).getE_Value()+","+
				tableDataPLogList.get(6).getE_Value()+","+
				",,,,,,,";
		
		
		
		otherValueList.add(ov_line1);otherValueList.add(ov_line2);otherValueList.add(ov_line3);
		otherValueList.add(ov_line4);otherValueList.add(ov_line5);otherValueList.add(ov_line6);
		otherValueList.add(ov_line7);otherValueList.add(ov_line8);otherValueList.add(ov_line9);
		otherValueList.add(ov_line10);otherValueList.add(ov_line11);otherValueList.add(ov_line12);
		otherValueList.add(ov_line13);otherValueList.add(ov_line14);otherValueList.add(ov_line15);
		otherValueList.add(ov_line16);otherValueList.add(ov_line17);otherValueList.add(ov_line18);
		otherValueList.add(ov_line19);otherValueList.add(ov_line20);otherValueList.add(ov_line21);
		otherValueList.add(ov_line22);otherValueList.add(ov_line23);otherValueList.add(ov_line24);
		otherValueList.add(ov_line25);otherValueList.add(ov_line26);otherValueList.add(ov_line27);
		otherValueList.add(ov_line28);otherValueList.add(ov_line29);otherValueList.add(ov_line30);
		otherValueList.add(ov_line31);otherValueList.add(ov_line32);otherValueList.add(ov_line33);
		otherValueList.add(ov_line34);
		otherValueList.add(ov_line35);
		otherValueList.add(ov_line36);
		otherValueList.add(ov_line37);
		otherValueList.add(ov_line38);
		otherValueList.add(ov_line39);
		otherValueList.add(ov_line40);
		
	}
	
	//
	//
	//=====================================================================
	
	//=====================================================================
	// Equation function
	//
	
	public void calcAllData(String type){
		if(type.equals("full")){
			this.calc_lcase_time_full();
			this.calc_lcase_dt_full();
			this.calc_lcase_inc_full();
			this.calc_wr_brot_full();
			this.calc_wr_trot_full();
			this.calc_bur_trot_full();
			this.calc_bur_brot_full();
		}else{
			this.calc_lcase_time();
			this.calc_lcase_dt();
			this.calc_lcase_inc();
			this.calc_wr_brot();
			this.calc_wr_trot();
			this.calc_bur_trot();
			this.calc_bur_brot();
		}
	}
	
	private void calc_lcase_time(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		
		String eqation = eqObj.getEquation(Equation.lcase_time);
		String result = makeResult(eqation);
		if(this.StandValue.equals(UILabel.F1)){
			this.tableDataPLogList.get(0).setLcase_time(result);
		}else if(this.StandValue.equals(UILabel.F2)){
			this.tableDataPLogList.get(1).setLcase_time(result);
		}else if(this.StandValue.equals(UILabel.F3)){
			this.tableDataPLogList.get(2).setLcase_time(result);
		}else if(this.StandValue.equals(UILabel.F4)){
			this.tableDataPLogList.get(3).setLcase_time(result);
		}else if(this.StandValue.equals(UILabel.F5)){
			this.tableDataPLogList.get(4).setLcase_time(result);
		}else if(this.StandValue.equals(UILabel.F6)){
			this.tableDataPLogList.get(5).setLcase_time(result);
		}else if(this.StandValue.equals(UILabel.F7)){
			this.tableDataPLogList.get(6).setLcase_time(result);
		}
		med.getTextAnalysisTime().setText(result);
		
	}
	
	private void calc_lcase_dt(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.lcase_dt);
		String result = makeResult(eqation);
		if(this.StandValue.equals(UILabel.F1)){
			this.tableDataPLogList.get(0).setlcase_dt(result);
		}else if(this.StandValue.equals(UILabel.F2)){
			this.tableDataPLogList.get(1).setlcase_dt(result);
		}else if(this.StandValue.equals(UILabel.F3)){
			this.tableDataPLogList.get(2).setlcase_dt(result);
		}else if(this.StandValue.equals(UILabel.F4)){
			this.tableDataPLogList.get(3).setlcase_dt(result);
		}else if(this.StandValue.equals(UILabel.F5)){
			this.tableDataPLogList.get(4).setlcase_dt(result);
		}else if(this.StandValue.equals(UILabel.F6)){
			this.tableDataPLogList.get(5).setlcase_dt(result);
		}else if(this.StandValue.equals(UILabel.F7)){
			this.tableDataPLogList.get(6).setlcase_dt(result);
		}
		med.getTextTimeIncrement().setText(result);
	}
	
	private void calc_lcase_inc(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String equation = eqObj.getEquation(Equation.lcase_inc);
		String result = makeResult(equation);
		if(this.StandValue.equals(UILabel.F1)){
			this.tableDataPLogList.get(0).setlcase_inc(result);
		}else if(this.StandValue.equals(UILabel.F2)){
			this.tableDataPLogList.get(1).setlcase_inc(result);
		}else if(this.StandValue.equals(UILabel.F3)){
			this.tableDataPLogList.get(2).setlcase_inc(result);
		}else if(this.StandValue.equals(UILabel.F4)){
			this.tableDataPLogList.get(3).setlcase_inc(result);
		}else if(this.StandValue.equals(UILabel.F5)){
			this.tableDataPLogList.get(4).setlcase_inc(result);
		}else if(this.StandValue.equals(UILabel.F6)){
			this.tableDataPLogList.get(5).setlcase_inc(result);
		}else if(this.StandValue.equals(UILabel.F7)){
			this.tableDataPLogList.get(6).setlcase_inc(result);
		}
		
		med.getTextNoOfInc().setText(result);
		
	}
	private void calc_wr_trot(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.wr_trot);
		String result = makeResult(eqation);
		if(this.StandValue.equals(UILabel.F1)){
			this.tableDataPLogList.get(0).setWr_trot(result);
		}else if(this.StandValue.equals(UILabel.F2)){
			this.tableDataPLogList.get(1).setWr_trot(result);
		}else if(this.StandValue.equals(UILabel.F3)){
			this.tableDataPLogList.get(2).setWr_trot(result);
		}else if(this.StandValue.equals(UILabel.F4)){
			this.tableDataPLogList.get(3).setWr_trot(result);
		}else if(this.StandValue.equals(UILabel.F5)){
			this.tableDataPLogList.get(4).setWr_trot(result);
		}else if(this.StandValue.equals(UILabel.F6)){
			this.tableDataPLogList.get(5).setWr_trot(result);
		}else if(this.StandValue.equals(UILabel.F7)){
			this.tableDataPLogList.get(6).setWr_trot(result);
		}
		med.getTextTopWRRotVel().setText(result);
	}
	
	private void calc_wr_brot(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.wr_brot);
		String result = makeResult(eqation);
		if(this.StandValue.equals(UILabel.F1)){
			this.tableDataPLogList.get(0).setWr_brot(result);
		}else if(this.StandValue.equals(UILabel.F2)){
			this.tableDataPLogList.get(1).setWr_brot(result);
		}else if(this.StandValue.equals(UILabel.F3)){
			this.tableDataPLogList.get(2).setWr_brot(result);
		}else if(this.StandValue.equals(UILabel.F4)){
			this.tableDataPLogList.get(3).setWr_brot(result);
		}else if(this.StandValue.equals(UILabel.F5)){
			this.tableDataPLogList.get(4).setWr_brot(result);
		}else if(this.StandValue.equals(UILabel.F6)){
			this.tableDataPLogList.get(5).setWr_brot(result);
		}else if(this.StandValue.equals(UILabel.F7)){
			this.tableDataPLogList.get(6).setWr_brot(result);
		}
		med.getTextBottomWRRotVel().setText(result);
	}
	
	private void calc_bur_trot(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.bur_trot);
		String result = makeResult(eqation);
		if(this.StandValue.equals(UILabel.F1)){
			this.tableDataPLogList.get(0).setBur_trot(result);
		}else if(this.StandValue.equals(UILabel.F2)){
			this.tableDataPLogList.get(1).setBur_trot(result);
		}else if(this.StandValue.equals(UILabel.F3)){
			this.tableDataPLogList.get(2).setBur_trot(result);
		}else if(this.StandValue.equals(UILabel.F4)){
			this.tableDataPLogList.get(3).setBur_trot(result);
		}else if(this.StandValue.equals(UILabel.F5)){
			this.tableDataPLogList.get(4).setBur_trot(result);
		}else if(this.StandValue.equals(UILabel.F6)){
			this.tableDataPLogList.get(5).setBur_trot(result);
		}else if(this.StandValue.equals(UILabel.F7)){
			this.tableDataPLogList.get(6).setBur_trot(result);
		}
		med.getTextTopBURRotVel().setText(result);
	}
	
	private void calc_bur_brot(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.bur_brot);
		String result = makeResult(eqation);
		if(this.StandValue.equals(UILabel.F1)){
			this.tableDataPLogList.get(0).setBur_brot(result);
		}else if(this.StandValue.equals(UILabel.F2)){
			this.tableDataPLogList.get(1).setBur_brot(result);
		}else if(this.StandValue.equals(UILabel.F3)){
			this.tableDataPLogList.get(2).setBur_brot(result);
		}else if(this.StandValue.equals(UILabel.F4)){
			this.tableDataPLogList.get(3).setBur_brot(result);
		}else if(this.StandValue.equals(UILabel.F5)){
			this.tableDataPLogList.get(4).setBur_brot(result);
		}else if(this.StandValue.equals(UILabel.F6)){
			this.tableDataPLogList.get(5).setBur_brot(result);
		}else if(this.StandValue.equals(UILabel.F7)){
			this.tableDataPLogList.get(6).setBur_brot(result);
		}
		med.getTextBottomBURRotVel().setText(result);
		
	}
	
	
	
	
	
	
	private void calc_lcase_time_full(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.lcase_time);
		makeResult_full(eqation,Equation.lcase_time);
		
	}
	
	private void calc_lcase_dt_full(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.lcase_dt);
		makeResult_full(eqation,Equation.lcase_dt);
	}
	
	private void calc_lcase_inc_full(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.lcase_inc);
		makeResult_full(eqation,Equation.lcase_inc);
	}
	
	private void calc_wr_trot_full(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.wr_trot);
		makeResult_full(eqation,Equation.wr_trot);
	}
	
	private void calc_wr_brot_full(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.wr_brot);
		makeResult_full(eqation,Equation.wr_brot);
	}
	
	private void calc_bur_trot_full(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.bur_trot);
		makeResult_full(eqation,Equation.bur_trot);
	}
	
	private void calc_bur_brot_full(){
		Equation eqObj = new Equation();
		eqObj.readEquationFile();
		String eqation = eqObj.getEquation(Equation.bur_brot);
		makeResult_full(eqation,Equation.bur_brot);
	}
	
	
	
	
	private void makeResult_full(String equation, String key){
		String result = "";
		String newEQ = "";
		for(TableData_PLog obj : this.tableDataPLogList){
			newEQ = equation;
			//System.out.println(obj.getENTRY_THK());
			if(newEQ.contains(Mapping.wr_tdia)){
				String value = obj.getWR_TDIA();
				newEQ = newEQ.replace(Mapping.wr_tdia, value);
			}		
			if(newEQ.contains(Mapping.wr_bdia)){
				String value = obj.getWR_BDIA();
				newEQ = newEQ.replace(Mapping.wr_bdia, value);
			}
			if(newEQ.contains(Mapping.bur_tdia)){
				String value = obj.getBUR_TDIA();
				newEQ = newEQ.replace(Mapping.bur_tdia, value);
			}
			if(newEQ.contains(Mapping.bur_bdia)){
				String value = obj.getBUR_BDIA();
				newEQ = newEQ.replace(Mapping.bur_bdia, value);
			}
			if(newEQ.contains(Mapping.wr_crown)){
				String value = obj.getWR_ICRN();
				newEQ = newEQ.replace(Mapping.wr_crown, value);
			}
			
			if(newEQ.contains(Mapping.roll_gap)){
				String value = obj.getROL_GAP();
				newEQ = newEQ.replace(Mapping.roll_gap, value);
			}
			
			if(newEQ.contains(Mapping.wr_div_angle)){
				String value = obj.getWr_div_angle();
				newEQ = newEQ.replace(Mapping.wr_div_angle, value);
			}
			if(newEQ.contains(Mapping.bur_div_angle)){
				String value = obj.getBur_div_angle();
				newEQ = newEQ.replace(Mapping.bur_div_angle, value);
			}
			
			if(newEQ.contains(Mapping.wr_len)){
				String value = obj.getWr_len();
				newEQ = newEQ.replace(Mapping.wr_len, value);
			}
			if(newEQ.contains(Mapping.bur_len)){
				String value = obj.getBur_len();
				newEQ = newEQ.replace(Mapping.bur_len, value);
			}
			
			if(newEQ.contains(Mapping.p_thk)){
				String value = obj.getENTRY_THK();
				newEQ = newEQ.replace(Mapping.p_thk, value);
			}
			if(newEQ.contains(Mapping.p_wid)){
				String value = obj.getSTP_WID();
				newEQ = newEQ.replace(Mapping.p_wid, value);
			}
			if(newEQ.contains(Mapping.p_len)){
				String value = obj.getSTP_LEN();
				newEQ = newEQ.replace(Mapping.p_len, value);
			}
			if(newEQ.contains(Mapping.p_ent_temp)){
				String value = obj.getENTRY_TEMP();
				newEQ = newEQ.replace(Mapping.p_ent_temp, value);
			}
			if(newEQ.contains(Mapping.p_exit_temp)){
				String value = obj.getEXIT_TEMP();
				newEQ = newEQ.replace(Mapping.p_exit_temp, value);
			}
			if(newEQ.contains(Mapping.p_in)){
				String value = obj.getP_in();
				newEQ = newEQ.replace(Mapping.p_in, value);
			}
			if(newEQ.contains(Mapping.pl_m)){
				String value = obj.getPl_m();
				newEQ = newEQ.replace(Mapping.pl_m, value);
			}
			if(newEQ.contains(Mapping.t_div)){
				String value = obj.getT_div();
				newEQ = newEQ.replace(Mapping.t_div, value);
			}
			
			if(newEQ.contains(Mapping.pl_vel_mpm)){
				String value = obj.getSPEED();
				newEQ = newEQ.replace(Mapping.pl_vel_mpm, value);
			}
			
			if(newEQ.contains(Mapping.pass_line)){
				String value = obj.getPAS_LINE();
				newEQ = newEQ.replace(Mapping.pass_line, value);
			}
			if(newEQ.contains(Mapping.p_cross_ang)){
				String value = obj.getP_CROSS();
				newEQ = newEQ.replace(Mapping.p_cross_ang, value);
			}
			if(newEQ.contains(Mapping.roll_torque)){
				String value = obj.getTORQ();
				newEQ = newEQ.replace(Mapping.roll_torque, value);
			}
			if(newEQ.contains(Mapping.bend_f)){
				String value = obj.getBEND();
				newEQ = newEQ.replace(Mapping.bend_f, value);
			}
			if(newEQ.contains(Mapping.tens_f)){
				String value = obj.getTENS();
				newEQ = newEQ.replace(Mapping.tens_f, value);
			}
			
			if(newEQ.contains(Mapping.f_r2p)){
				String value = obj.getF_r2p();
				newEQ = newEQ.replace(Mapping.f_r2p, value);
			}
			if(newEQ.contains(Mapping.f_r2r)){
				String value = obj.getF_r2r();
				newEQ = newEQ.replace(Mapping.f_r2r, value);
			}
			
			
			if(newEQ.contains(Mapping.post_inc)){
				String value = obj.getPost_inc();
				newEQ = newEQ.replace(Mapping.post_inc, value);
			}
			
			if(newEQ.contains(Mapping.frce)){
				String value = obj.getFRCE();
				newEQ = newEQ.replace(Mapping.frce, value);
			}
			if(newEQ.contains(Mapping.exit_thk)){
				String value = obj.getEXIT_THK();
				newEQ = newEQ.replace(Mapping.exit_thk, value);
			}
			if(newEQ.contains(Mapping.rol_tim)){
				String value = obj.getROL_TIM();
				newEQ = newEQ.replace(Mapping.rol_tim, value);
			}
			if(newEQ.contains(Mapping.idl_tim)){
				String value = obj.getIDL_TIM();
				newEQ = newEQ.replace(Mapping.idl_tim, value);
			}
			if(newEQ.contains(Mapping.bur_wear)){
				String value = obj.getBUR_WEAR();
				newEQ = newEQ.replace(Mapping.bur_wear, value);
			}
			if(newEQ.contains(Mapping.wr_wear)){
				String value = obj.getWR_WEAR();
				newEQ = newEQ.replace(Mapping.wr_wear, value);
			}
			if(newEQ.contains(Mapping.wr_thrm)){
				String value = obj.getWR_THRM();
				newEQ = newEQ.replace(Mapping.wr_thrm, value);
			}
			
			if(newEQ.contains(Mapping.ym_value)){
				String value = obj.getYM_Value();
				newEQ = newEQ.replace(Mapping.ym_value, value);
			}
			if(newEQ.contains(Mapping.tec_value)){
				String value = obj.getTEC_Value();
				newEQ = newEQ.replace(Mapping.tec_value, value);
			}
			if(newEQ.contains(Mapping.pr_value)){
				String value = obj.getPR_Value();
				newEQ = newEQ.replace(Mapping.pr_value, value);
			}
			if(newEQ.contains(Mapping.md_value)){
				String value = obj.getMD_Value();
				newEQ = newEQ.replace(Mapping.md_value, value);
			}
			
			if(newEQ.contains(Mapping.lcase_time)){
				String value = obj.getLcase_time();
				newEQ = newEQ.replace(Mapping.lcase_time, value);
			}
			if(newEQ.contains(Mapping.lcase_dt)){
				String value = obj.getlcase_dt();
				newEQ = newEQ.replace(Mapping.lcase_dt, value);
			}
			if(newEQ.contains(Mapping.lcase_inc)){
				String value = obj.getlcase_inc();
				newEQ = newEQ.replace(Mapping.lcase_inc, value);
			}
			if(newEQ.contains(Mapping.ltime_scale)){
				String value = obj.getLtime_scale();
				newEQ = newEQ.replace(Mapping.ltime_scale, value);
			}
			if(newEQ.contains(Mapping.domain)){
				String value = obj.getDomain();
				newEQ = newEQ.replace(Mapping.domain, value);
			}
			if(newEQ.contains(Mapping.thread)){
				String value = obj.getThread();
				newEQ = newEQ.replace(Mapping.thread, value);
			}
			
			if(newEQ.contains(Mapping.vel_rate_top)){
				String value = obj.getSpeed_different_ratio_top_roll();
				newEQ = newEQ.replace(Mapping.vel_rate_top, value);
			}
			if(newEQ.contains(Mapping.vel_rate_bottom)){
				String value = obj.getSpeed_different_ratio_bottom_roll();
				newEQ = newEQ.replace(Mapping.vel_rate_bottom, value);
			}
			if(newEQ.contains(Mapping.wr_trot)){
				String value = obj.getWr_trot();
				newEQ = newEQ.replace(Mapping.wr_trot, value);
			}
			if(newEQ.contains(Mapping.wr_brot)){
				String value = obj.getWr_brot();
				newEQ = newEQ.replace(Mapping.wr_brot, value);
			}
			if(newEQ.contains(Mapping.bur_trot)){
				String value = obj.getBur_brot();
				newEQ = newEQ.replace(Mapping.bur_trot, value);
			}
			if(newEQ.contains(Mapping.bur_brot)){
				String value = obj.getBur_trot();
				newEQ = newEQ.replace(Mapping.bur_brot, value);
			}
			
			if(newEQ.contains(Mapping.var1)){
				String value = this.TableDataVariableObj.getVAR1();
				newEQ = newEQ.replace(Mapping.var1, value);
			}
			if(newEQ.contains(Mapping.var2)){
				String value = this.TableDataVariableObj.getVAR2();
				newEQ = newEQ.replace(Mapping.var2, value);
			}
			if(newEQ.contains(Mapping.var3)){
				String value = this.TableDataVariableObj.getVAR3();
				newEQ = newEQ.replace(Mapping.var3, value);
			}
			if(newEQ.contains(Mapping.var4)){
				String value = this.TableDataVariableObj.getVAR4();
				newEQ = newEQ.replace(Mapping.var4, value);
			}
			if(newEQ.contains(Mapping.var5)){
				String value = this.TableDataVariableObj.getVAR5();
				newEQ = newEQ.replace(Mapping.var5, value);
			}
			if(newEQ.contains(Mapping.var6)){
				String value = this.TableDataVariableObj.getVAR6();
				newEQ = newEQ.replace(Mapping.var6, value);
			}
			if(newEQ.contains(Mapping.var7)){
				String value = this.TableDataVariableObj.getVAR7();
				newEQ = newEQ.replace(Mapping.var7, value);
			}
			if(newEQ.contains(Mapping.var8)){
				String value = this.TableDataVariableObj.getVAR8();
				newEQ = newEQ.replace(Mapping.var8, value);
			}
			if(newEQ.contains(Mapping.var9)){
				String value = this.TableDataVariableObj.getVAR9();
				newEQ = newEQ.replace(Mapping.var9, value);
			}
			if(newEQ.contains(Mapping.var10)){
				String value = this.TableDataVariableObj.getVAR10();
				newEQ = newEQ.replace(Mapping.var10, value);
			}
			if(newEQ.contains(Mapping.var11)){
				String value = this.TableDataVariableObj.getVAR11();
				newEQ = newEQ.replace(Mapping.var11, value);
			}
			if(newEQ.contains(Mapping.var12)){
				String value = this.TableDataVariableObj.getVAR12();
				newEQ = newEQ.replace(Mapping.var12, value);
			}
			if(newEQ.contains(Mapping.var13)){
				String value = this.TableDataVariableObj.getVAR13();
				newEQ = newEQ.replace(Mapping.var13, value);
			}
			if(newEQ.contains(Mapping.var14)){
				String value = this.TableDataVariableObj.getVAR14();
				newEQ = newEQ.replace(Mapping.var14, value);
			}
			if(newEQ.contains(Mapping.var15)){
				String value = this.TableDataVariableObj.getVAR15();
				newEQ = newEQ.replace(Mapping.var15, value);
			}
			if(newEQ.contains(Mapping.var16)){
				String value = this.TableDataVariableObj.getVAR16();
				newEQ = newEQ.replace(Mapping.var16, value);
			}
			
			if(newEQ.contains(Mapping.sthk)){
				String value = this.TableDataSlabPlateInfoObj.getSTHK();
				newEQ = newEQ.replace(Mapping.sthk, value);
			}
			if(newEQ.contains(Mapping.swid)){
				String value = this.TableDataSlabPlateInfoObj.getSWID();
				newEQ = newEQ.replace(Mapping.swid, value);
			}
			if(newEQ.contains(Mapping.slen)){
				String value = this.TableDataSlabPlateInfoObj.getSLEN();
				newEQ = newEQ.replace(Mapping.slen, value);
			}
			if(newEQ.contains(Mapping.swet)){
				String value = this.TableDataSlabPlateInfoObj.getSWET();
				newEQ = newEQ.replace(Mapping.swet, value);
			}
			if(newEQ.contains(Mapping.pthk)){
				String value = this.TableDataSlabPlateInfoObj.getPTHK();
				newEQ = newEQ.replace(Mapping.pthk, value);
			}
			if(newEQ.contains(Mapping.pwid)){
				String value = this.TableDataSlabPlateInfoObj.getPWID();
				newEQ = newEQ.replace(Mapping.pwid, value);
			}
			if(newEQ.contains(Mapping.plen)){
				String value = this.TableDataSlabPlateInfoObj.getPLEN();
				newEQ = newEQ.replace(Mapping.plen, value);
			}
			if(newEQ.contains(Mapping.pwet)){
				String value = this.TableDataSlabPlateInfoObj.getPWET();
				newEQ = newEQ.replace(Mapping.pwet, value);
			}
			
			if(newEQ.contains(Mapping.wr_chamferX)){
				String value = obj.getWr_chamferX();
				newEQ = newEQ.replace(Mapping.wr_chamferX, value);
			}
			if(newEQ.contains(Mapping.wr_chamferY)){
				String value = obj.getWr_chamferY();
				newEQ = newEQ.replace(Mapping.wr_chamferY, value);
			}
			if(newEQ.contains(Mapping.wr_round)){
				String value = obj.getWr_round();
				newEQ = newEQ.replace(Mapping.wr_round, value);
			}
			if(newEQ.contains(Mapping.bur_chamferX)){
				String value = obj.getBur_chamferY();
				newEQ = newEQ.replace(Mapping.bur_chamferX, value);
			}
			if(newEQ.contains(Mapping.bur_chamferY)){
				String value = obj.getBur_chamferY();
				newEQ = newEQ.replace(Mapping.bur_chamferY, value);
			}
			if(newEQ.contains(Mapping.p_cr)){
				String value = obj.getP_cr();
				newEQ = newEQ.replace(Mapping.p_cr, value);
			}
			if(newEQ.contains(Mapping.ym_roll_value)){
				String value = obj.getYM_Roll_constant();
				newEQ = newEQ.replace(Mapping.ym_roll_value, value);
			}
			if(newEQ.contains(Mapping.pr_roll_value)){
				String value = obj.getPR_Roll_constant();
				newEQ = newEQ.replace(Mapping.pr_roll_value, value);
			}
			
			
			
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			
			try {
				//result = String.valueOf(engine.eval(newEQ));
				result = String.format("%.2f", engine.eval(newEQ));
				//System.out.println("New EQ Result : "+ engine.eval(newEQ));
				
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println("FULL calc : "+obj.getSTAND() + " || " + result + " || " + key );
			//System.out.println("==> eq : "+equation);
			//System.out.println("==> newEQ : "+ newEQ);
			
			if(key.equals(Equation.lcase_time)){
				obj.setLcase_time(result);
			}else if(key.equals(Equation.lcase_dt)){
				obj.setlcase_dt(result);
			}else if(key.equals(Equation.lcase_inc)){
				obj.setlcase_inc(result);
			}else if(key.equals(Equation.wr_trot)){
				obj.setWr_trot(result);
			}else if(key.equals(Equation.wr_brot)){
				obj.setWr_brot(result);
			}else if(key.equals(Equation.bur_trot)){
				obj.setBur_trot(result);
			}else if(key.equals(Equation.bur_brot)){
				obj.setBur_brot(result);
			}
			
		}
	}
	
	private String makeResult(String eqation){
		//Mapping mappingObj = new Mapping();
		//mappingObj.readMappingTableFile();
		TableData_PLog obj = getPLogObj();
		//System.out.println("ORI EQ : "+eqation);
		String newEQ = eqation;
		
		
		if(newEQ.contains(Mapping.wr_tdia)){
			String value = obj.getWR_TDIA();
			newEQ = newEQ.replace(Mapping.wr_tdia, value);
		}		
		if(newEQ.contains(Mapping.wr_bdia)){
			String value = obj.getWR_BDIA();
			newEQ = newEQ.replace(Mapping.wr_bdia, value);
		}
		if(newEQ.contains(Mapping.bur_tdia)){
			String value = obj.getBUR_TDIA();
			newEQ = newEQ.replace(Mapping.bur_tdia, value);
		}
		if(newEQ.contains(Mapping.bur_bdia)){
			String value = obj.getBUR_BDIA();
			newEQ = newEQ.replace(Mapping.bur_bdia, value);
		}
		if(newEQ.contains(Mapping.wr_crown)){
			String value = obj.getWR_ICRN();
			newEQ = newEQ.replace(Mapping.wr_crown, value);
		}
		
		if(newEQ.contains(Mapping.roll_gap)){
			String value = obj.getROL_GAP();
			newEQ = newEQ.replace(Mapping.roll_gap, value);
		}
		
		if(newEQ.contains(Mapping.wr_div_angle)){
			String value = obj.getWr_div_angle();
			newEQ = newEQ.replace(Mapping.wr_div_angle, value);
		}
		if(newEQ.contains(Mapping.bur_div_angle)){
			String value = obj.getBur_div_angle();
			newEQ = newEQ.replace(Mapping.bur_div_angle, value);
		}
		
		if(newEQ.contains(Mapping.wr_len)){
			String value = obj.getWr_len();
			newEQ = newEQ.replace(Mapping.wr_len, value);
		}
		if(newEQ.contains(Mapping.bur_len)){
			String value = obj.getBur_len();
			newEQ = newEQ.replace(Mapping.bur_len, value);
		}
		
		if(newEQ.contains(Mapping.p_thk)){
			String value = obj.getENTRY_THK();
			newEQ = newEQ.replace(Mapping.p_thk, value);
		}
		if(newEQ.contains(Mapping.p_wid)){
			String value = obj.getSTP_WID();
			newEQ = newEQ.replace(Mapping.p_wid, value);
		}
		if(newEQ.contains(Mapping.p_len)){
			String value = obj.getSTP_LEN();
			newEQ = newEQ.replace(Mapping.p_len, value);
		}
		if(newEQ.contains(Mapping.p_ent_temp)){
			String value = obj.getENTRY_TEMP();
			newEQ = newEQ.replace(Mapping.p_ent_temp, value);
		}
		if(newEQ.contains(Mapping.p_exit_temp)){
			String value = obj.getEXIT_TEMP();
			newEQ = newEQ.replace(Mapping.p_exit_temp, value);
		}
		if(newEQ.contains(Mapping.p_in)){
			String value = obj.getP_in();
			newEQ = newEQ.replace(Mapping.p_in, value);
		}
		if(newEQ.contains(Mapping.pl_m)){
			String value = obj.getPl_m();
			newEQ = newEQ.replace(Mapping.pl_m, value);
		}
		if(newEQ.contains(Mapping.t_div)){
			String value = obj.getT_div();
			newEQ = newEQ.replace(Mapping.t_div, value);
		}
		
		if(newEQ.contains(Mapping.pl_vel_mpm)){
			String value = obj.getSPEED();
			newEQ = newEQ.replace(Mapping.pl_vel_mpm, value);
		}
		
		if(newEQ.contains(Mapping.pass_line)){
			String value = obj.getPAS_LINE();
			newEQ = newEQ.replace(Mapping.pass_line, value);
		}
		if(newEQ.contains(Mapping.p_cross_ang)){
			String value = obj.getP_CROSS();
			newEQ = newEQ.replace(Mapping.p_cross_ang, value);
		}
		if(newEQ.contains(Mapping.roll_torque)){
			String value = obj.getTORQ();
			newEQ = newEQ.replace(Mapping.roll_torque, value);
		}
		if(newEQ.contains(Mapping.bend_f)){
			String value = obj.getBEND();
			newEQ = newEQ.replace(Mapping.bend_f, value);
		}
		if(newEQ.contains(Mapping.tens_f)){
			String value = obj.getTENS();
			newEQ = newEQ.replace(Mapping.tens_f, value);
		}
		
		if(newEQ.contains(Mapping.f_r2p)){
			String value = obj.getF_r2p();
			newEQ = newEQ.replace(Mapping.f_r2p, value);
		}
		if(newEQ.contains(Mapping.f_r2r)){
			String value = obj.getF_r2r();
			newEQ = newEQ.replace(Mapping.f_r2r, value);
		}
		
		
		if(newEQ.contains(Mapping.post_inc)){
			String value = obj.getPost_inc();
			newEQ = newEQ.replace(Mapping.post_inc, value);
		}
		
		if(newEQ.contains(Mapping.frce)){
			String value = obj.getFRCE();
			newEQ = newEQ.replace(Mapping.frce, value);
		}
		if(newEQ.contains(Mapping.exit_thk)){
			String value = obj.getEXIT_THK();
			newEQ = newEQ.replace(Mapping.exit_thk, value);
		}
		if(newEQ.contains(Mapping.rol_tim)){
			String value = obj.getROL_TIM();
			newEQ = newEQ.replace(Mapping.rol_tim, value);
		}
		if(newEQ.contains(Mapping.idl_tim)){
			String value = obj.getIDL_TIM();
			newEQ = newEQ.replace(Mapping.idl_tim, value);
		}
		if(newEQ.contains(Mapping.bur_wear)){
			String value = obj.getBUR_WEAR();
			newEQ = newEQ.replace(Mapping.bur_wear, value);
		}
		if(newEQ.contains(Mapping.wr_wear)){
			String value = obj.getWR_WEAR();
			newEQ = newEQ.replace(Mapping.wr_wear, value);
		}
		if(newEQ.contains(Mapping.wr_thrm)){
			String value = obj.getWR_THRM();
			newEQ = newEQ.replace(Mapping.wr_thrm, value);
		}
		
		if(newEQ.contains(Mapping.ym_value)){
			String value = obj.getYM_Value();
			newEQ = newEQ.replace(Mapping.ym_value, value);
		}
		if(newEQ.contains(Mapping.tec_value)){
			String value = obj.getTEC_Value();
			newEQ = newEQ.replace(Mapping.tec_value, value);
		}
		if(newEQ.contains(Mapping.pr_value)){
			String value = obj.getPR_Value();
			newEQ = newEQ.replace(Mapping.pr_value, value);
		}
		if(newEQ.contains(Mapping.md_value)){
			String value = obj.getMD_Value();
			newEQ = newEQ.replace(Mapping.md_value, value);
		}
		
		if(newEQ.contains(Mapping.lcase_time)){
			String value = obj.getLcase_time();
			newEQ = newEQ.replace(Mapping.lcase_time, value);
		}
		if(newEQ.contains(Mapping.lcase_dt)){
			String value = obj.getlcase_dt();
			newEQ = newEQ.replace(Mapping.lcase_dt, value);
		}
		if(newEQ.contains(Mapping.lcase_inc)){
			String value = obj.getlcase_inc();
			newEQ = newEQ.replace(Mapping.lcase_inc, value);
		}
		if(newEQ.contains(Mapping.ltime_scale)){
			String value = obj.getLtime_scale();
			newEQ = newEQ.replace(Mapping.ltime_scale, value);
		}
		if(newEQ.contains(Mapping.domain)){
			String value = obj.getDomain();
			newEQ = newEQ.replace(Mapping.domain, value);
		}
		if(newEQ.contains(Mapping.thread)){
			String value = obj.getThread();
			newEQ = newEQ.replace(Mapping.thread, value);
		}
		
		if(newEQ.contains(Mapping.vel_rate_top)){
			String value = obj.getSpeed_different_ratio_top_roll();
			newEQ = newEQ.replace(Mapping.vel_rate_top, value);
		}
		if(newEQ.contains(Mapping.vel_rate_bottom)){
			String value = obj.getSpeed_different_ratio_bottom_roll();
			newEQ = newEQ.replace(Mapping.vel_rate_bottom, value);
		}
		if(newEQ.contains(Mapping.wr_trot)){
			String value = obj.getWr_trot();
			newEQ = newEQ.replace(Mapping.wr_trot, value);
		}
		if(newEQ.contains(Mapping.wr_brot)){
			String value = obj.getWr_brot();
			newEQ = newEQ.replace(Mapping.wr_brot, value);
		}
		if(newEQ.contains(Mapping.bur_trot)){
			String value = obj.getBur_brot();
			newEQ = newEQ.replace(Mapping.bur_trot, value);
		}
		if(newEQ.contains(Mapping.bur_brot)){
			String value = obj.getBur_trot();
			newEQ = newEQ.replace(Mapping.bur_brot, value);
		}
		
		if(newEQ.contains(Mapping.var1)){
			String value = this.TableDataVariableObj.getVAR1();
			newEQ = newEQ.replace(Mapping.var1, value);
		}
		if(newEQ.contains(Mapping.var2)){
			String value = this.TableDataVariableObj.getVAR2();
			newEQ = newEQ.replace(Mapping.var2, value);
		}
		if(newEQ.contains(Mapping.var3)){
			String value = this.TableDataVariableObj.getVAR3();
			newEQ = newEQ.replace(Mapping.var3, value);
		}
		if(newEQ.contains(Mapping.var4)){
			String value = this.TableDataVariableObj.getVAR4();
			newEQ = newEQ.replace(Mapping.var4, value);
		}
		if(newEQ.contains(Mapping.var5)){
			String value = this.TableDataVariableObj.getVAR5();
			newEQ = newEQ.replace(Mapping.var5, value);
		}
		if(newEQ.contains(Mapping.var6)){
			String value = this.TableDataVariableObj.getVAR6();
			newEQ = newEQ.replace(Mapping.var6, value);
		}
		if(newEQ.contains(Mapping.var7)){
			String value = this.TableDataVariableObj.getVAR7();
			newEQ = newEQ.replace(Mapping.var7, value);
		}
		if(newEQ.contains(Mapping.var8)){
			String value = this.TableDataVariableObj.getVAR8();
			newEQ = newEQ.replace(Mapping.var8, value);
		}
		if(newEQ.contains(Mapping.var9)){
			String value = this.TableDataVariableObj.getVAR9();
			newEQ = newEQ.replace(Mapping.var9, value);
		}
		if(newEQ.contains(Mapping.var10)){
			String value = this.TableDataVariableObj.getVAR10();
			newEQ = newEQ.replace(Mapping.var10, value);
		}
		if(newEQ.contains(Mapping.var11)){
			String value = this.TableDataVariableObj.getVAR11();
			newEQ = newEQ.replace(Mapping.var11, value);
		}
		if(newEQ.contains(Mapping.var12)){
			String value = this.TableDataVariableObj.getVAR12();
			newEQ = newEQ.replace(Mapping.var12, value);
		}
		if(newEQ.contains(Mapping.var13)){
			String value = this.TableDataVariableObj.getVAR13();
			newEQ = newEQ.replace(Mapping.var13, value);
		}
		if(newEQ.contains(Mapping.var14)){
			String value = this.TableDataVariableObj.getVAR14();
			newEQ = newEQ.replace(Mapping.var14, value);
		}
		if(newEQ.contains(Mapping.var15)){
			String value = this.TableDataVariableObj.getVAR15();
			newEQ = newEQ.replace(Mapping.var15, value);
		}
		if(newEQ.contains(Mapping.var16)){
			String value = this.TableDataVariableObj.getVAR16();
			newEQ = newEQ.replace(Mapping.var16, value);
		}
		
		if(newEQ.contains(Mapping.sthk)){
			String value = this.TableDataSlabPlateInfoObj.getSTHK();
			newEQ = newEQ.replace(Mapping.sthk, value);
		}
		if(newEQ.contains(Mapping.swid)){
			String value = this.TableDataSlabPlateInfoObj.getSWID();
			newEQ = newEQ.replace(Mapping.swid, value);
		}
		if(newEQ.contains(Mapping.slen)){
			String value = this.TableDataSlabPlateInfoObj.getSLEN();
			newEQ = newEQ.replace(Mapping.slen, value);
		}
		if(newEQ.contains(Mapping.swet)){
			String value = this.TableDataSlabPlateInfoObj.getSWET();
			newEQ = newEQ.replace(Mapping.swet, value);
		}
		if(newEQ.contains(Mapping.pthk)){
			String value = this.TableDataSlabPlateInfoObj.getPTHK();
			newEQ = newEQ.replace(Mapping.pthk, value);
		}
		if(newEQ.contains(Mapping.pwid)){
			String value = this.TableDataSlabPlateInfoObj.getPWID();
			newEQ = newEQ.replace(Mapping.pwid, value);
		}
		if(newEQ.contains(Mapping.plen)){
			String value = this.TableDataSlabPlateInfoObj.getPLEN();
			newEQ = newEQ.replace(Mapping.plen, value);
		}
		if(newEQ.contains(Mapping.pwet)){
			String value = this.TableDataSlabPlateInfoObj.getPWET();
			newEQ = newEQ.replace(Mapping.pwet, value);
		}
		
		if(newEQ.contains(Mapping.wr_chamferX)){
			String value = obj.getWr_chamferX();
			newEQ = newEQ.replace(Mapping.wr_chamferX, value);
		}
		if(newEQ.contains(Mapping.wr_chamferY)){
			String value = obj.getWr_chamferY();
			newEQ = newEQ.replace(Mapping.wr_chamferY, value);
		}
		if(newEQ.contains(Mapping.wr_round)){
			String value = obj.getWr_round();
			newEQ = newEQ.replace(Mapping.wr_round, value);
		}
		if(newEQ.contains(Mapping.bur_chamferX)){
			String value = obj.getBur_chamferY();
			newEQ = newEQ.replace(Mapping.bur_chamferX, value);
		}
		if(newEQ.contains(Mapping.bur_chamferY)){
			String value = obj.getBur_chamferY();
			newEQ = newEQ.replace(Mapping.bur_chamferY, value);
		}
		if(newEQ.contains(Mapping.p_cr)){
			String value = obj.getP_cr();
			newEQ = newEQ.replace(Mapping.p_cr, value);
		}
		if(newEQ.contains(Mapping.ym_roll_value)){
			String value = obj.getYM_Roll_constant();
			newEQ = newEQ.replace(Mapping.ym_roll_value, value);
		}
		if(newEQ.contains(Mapping.pr_roll_value)){
			String value = obj.getPR_Roll_constant();
			newEQ = newEQ.replace(Mapping.pr_roll_value, value);
		}
		
		//System.out.println("New EQ : "+newEQ);
		
		
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		String result = "";
		try {
			//result = String.valueOf(engine.eval(newEQ));
			result = String.format("%.2f", engine.eval(newEQ));
			
			//System.out.println("New EQ Result : "+ engine.eval(newEQ));
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("STAND : "+ this.StandValue);
		//System.out.println("=================================");
		return result;
	}
	
	private TableData_PLog getPLogObj(){
		if(this.StandValue.equals(UILabel.F1)){
			return this.tableDataPLogList.get(0);
		}else if(this.StandValue.equals(UILabel.F2)){
			return this.tableDataPLogList.get(1);
		}else if(this.StandValue.equals(UILabel.F3)){
			return this.tableDataPLogList.get(2);
		}else if(this.StandValue.equals(UILabel.F4)){
			return this.tableDataPLogList.get(3);
		}else if(this.StandValue.equals(UILabel.F5)){
			return this.tableDataPLogList.get(4);
		}else if(this.StandValue.equals(UILabel.F6)){
			return this.tableDataPLogList.get(5);
		}else if(this.StandValue.equals(UILabel.F7)){
			return this.tableDataPLogList.get(6);
		}else {
			return null;
		}
	}
	
	
	
	
	
	//
	//
	//=====================================================================
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}
	public TableData_SlabPlateInfo getTableDataSlabPlateInfoObj() {
		return TableDataSlabPlateInfoObj;
	}
	public void setTableDataSlabPlateInfoObj(
			TableData_SlabPlateInfo tableDataSlabPlateInfoObj) {
		TableDataSlabPlateInfoObj = tableDataSlabPlateInfoObj;
	}
	public TableData_Variable getTableDataVariableObj() {
		return TableDataVariableObj;
	}
	public void setTableDataVariableObj(TableData_Variable tableDataVariableObj) {
		TableDataVariableObj = tableDataVariableObj;
	}
	public ArrayList<TableData_PLog> getTableDataPLogList() {
		return tableDataPLogList;
	}
	public void setTableDataPLogList(ArrayList<TableData_PLog> tableDataPLogList) {
		this.tableDataPLogList = tableDataPLogList;
	}
	public String getApplyType() {
		return ApplyType;
	}
	public void setApplyType(String applyType) {
		if(applyType.equals(this.ApplyType_Consequent)){
			ApplyType = this.ApplyType_Consequent;
		}else{
			ApplyType = this.ApplyType_Individual;
		}
	}
	public String getSectionFilePath() {
		return sectionFilePath;
	}
	public void setSectionFilePath(String sectionFilePath) {
		this.sectionFilePath = sectionFilePath;
	}
	public String getDummySectionFilePath() {
		return dummySectionFilePath;
	}
	public void setDummySectionFilePath(String dummySectionFilePath) {
		this.dummySectionFilePath = dummySectionFilePath;
	}
	public String getRunType() {
		return RunType;
	}
	public void setRunType(String runType) {
		if(runType.equals(this.RunType_Multiful)){
			RunType = this.RunType_Multiful;
		}else{
			RunType = this.RunType_Single;
		}
	}
	
}


