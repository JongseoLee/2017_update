package com.js.ens.hrolling3d_ver2.core;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import com.js.ens.hrolling3d_ver2.customWidget.ICommand;

public class Mediator {
	private static Mediator instance = new Mediator();
	public static Mediator getInstance(){
		return instance;
	}
	
	//
	// TabFolder 
	//
	private TabFolder tabFolder;
	public ICommand C_tabFolder;
	public static String TABFOLDER_tabFolder = "tabFolder";
	
	//
	// Tab 1
	//
	private TableViewer tableViewerSlabPlateInfo;
	public ICommand C_tableViewerSlabPlateInfo;
	public static String TABLEVIEWER_tableViewerSlabPlateInfo = "tableViewerSlabPlateInfo";
	
	private TableViewer tableViewerVariable;
	public ICommand C_tableViewerVariable;
	public static String TABLEVIEWER_tableViewerVariable = "tableViewerVariable";
	
	private TableViewer tableViewerPLog;
	public ICommand C_tableViewerPLog;
	public static String TABLEVIEWER_tableViewerPLog = "tableViewerPLog";
	
	private Button btnImportPLog;
	public ICommand C_btnImportPLog;
	public static String BUTTON_btnImportPLog = "btnImportPLog";
	
	private Button btnExportPLog;
	public ICommand C_btnExportPLog;
	public static String BUTTON_btnExportPLog = "btnExportPLog";
	
	
	//
	// Tab2
	//
	private Composite compositeDetail;
	private Group groupSTAND;
	
	private Button btnF1;
	public ICommand C_btnF1;
	public static String BUTTON_btnF1 = "btnF1";
	
	private Button btnF2;
	public ICommand C_btnF2;
	public static String BUTTON_btnF2 = "btnF2";
	
	private Button btnF3;
	public ICommand C_btnF3;
	public static String BUTTON_btnF3 = "btnF3";
	
	private Button btnF4;
	public ICommand C_btnF4;
	public static String BUTTON_btnF4 = "btnF4";
	
	private Button btnF5;
	public ICommand C_btnF5;
	public static String BUTTON_btnF5 = "btnF5";
	
	private Button btnF6;
	public ICommand C_btnF6;
	public static String BUTTON_btnF6 = "btnF6";
	
	private Button btnF7;
	public ICommand C_btnF7;
	public static String BUTTON_btnF7 = "btnF7";
	
	
	//group1
	private Group grpWorkRollwrParameter;
	
	private Text textTopWRDiameter;
	public ICommand C_textTopWRDiameter;
	public static String TEXT_textTopWRDiameter = "textTopWRDiameter";
	
	private Text textBottomWRDiameter;
	public ICommand C_textBottomWRDiameter;
	public static String TEXT_textBottomWRDiameter = "textBottomWRDiameter";
	
	private Text textWRCrown;
	public ICommand C_textWRCrown;
	public static String TEXT_textWRCrown = "textWRCrown";
	
	private Text textWRLength;
	public ICommand C_textWRLength;
	public static String TEXT_textWRLength = "textWRLength";
	
	private Text textWRMeshAngle;
	public ICommand C_textWRMeshAngle;
	public static String TEXT_textWRMeshAngle = "textWRMeshAngle";
	
	private Text textWRChamferX;
	public ICommand C_textWRChamferX;
	public static String Text_textWRChamferX = "textWRChamferX";
	
	private Text textWRChamferY;
	public ICommand C_textWRChamferY;
	public static String Text_textWRChamferY = "textWRChamferY";
	
	private Text textWRRound;
	public ICommand C_textWRRound;
	public static String Text_textWRRound = "textWRRound";
	
	
	//group 2
	private Group grpBackUpRollburParameter;
	
	private Text textTopBURDiameter;
	public ICommand C_textTopBURDiameter;
	public static String TEXT_textTopBURDiameter = "textTopBURDiameter";
	
	private Text textBottomBURDiameter;
	public ICommand C_textBottomBURDiameter;
	public static String TEXT_textBottomBURDiameter = "textBottomBURDiameter";
	
	private Text textBURLength;
	public ICommand C_textBURLength;
	public static String TEXT_textBURLength = "textBURLength";
	
	private Text textBURMeshAngle;
	public ICommand C_textBURMeshAngle;
	public static String TEXT_textBURMeshAngle = "textBURMeshAngle";
	
	public Text textBURChamferX;
	public ICommand C_textBURChamferX;
	public static String TEXT_textBURChamferX = "textBURChamferX";
	
	public Text textBURChamferY;
	public ICommand C_textBURChamferY;
	public static String TEXT_textBURChamferY = "textBURChamferY";
	
	
	//group 3
	private Group grpPlateParameter;
	
	private Text textThickness;
	public ICommand C_textThickness;
	public static String TEXT_textThickness = "textThickness";
	
	private Text textWidth;
	public ICommand C_textWidth;
	public static String TEXT_textWidth = "textWidth";
	
	private Text textLength;
	public ICommand C_textLength;
	public static String TEXT_textLength = "textLength";

	private Text textEntryTemperature;
	public ICommand C_textEntryTemperature;
	public static String TEXT_textEntryTemperature = "textEntryTemperature";
	
	private Text textExitTemperature;
	public ICommand C_textExitTemperature;
	public static String TEXT_textExitTemperature = "textExitTemperature";
	
	private Text textInitialPosition;
	public ICommand C_textInitialPosition;
	public static String TEXT_textInitialPosition = "textInitialPosition";
	
	private Text textMeshLength;
	public ICommand C_textMeshLength;
	public static String TEXT_textMeshLength = "textMeshLength";
	
	private Text textThicknessMeshDivisions;
	public ICommand C_textThicknessMeshDivisions;
	public static String TEXT_textThicknessMeshDivisions = "textThicknessMeshDivisions";
	
	private Text textPlateCrown;
	public ICommand C_textPlateCrown;
	public static String TEXT_textPlateCrown = "textPlateCrown";
	
	//group 4
	private Group grpProcessInformation;
		
	private Text textVelocity;
	public ICommand C_textVelocity;
	public static String TEXT_textVelocity = "textVelocity";
	
	private Text textRollGap;
	public ICommand C_textRollGap;
	public static String TEXT_textRollGap = "textRollGap";
	
	private Text textPassLine;
	public ICommand C_textPassLine;
	public static String TEXT_textPassLine = "textPassLine";
	
	private Text textPairCrossAngle;
	public ICommand C_textPairCrossAngle;
	public static String TEXT_textPairCrossAngle = "textPairCrossAngle";
	
	private Text textBenderForce;
	public ICommand C_textBenderForce;
	public static String TEXT_textBenderForce = "textBenderForce";
	
	private Text textRollTorque;
	public ICommand C_textRollTorque;
	public static String TEXT_textRollTorque = "textRollTorque";
	
	private Text textTensionStress;
	public ICommand C_textTensionStress;
	public static String TEXT_textTensionStress = "textTensionStress";
	
	private Text textRollToPlateFrictCoef;
	public ICommand C_textRollToPlateFrictCoef;
	public static String TEXT_textRollToPlateFrictCoef = "textRollToPlateFrictCoef";
	
	private Text textRollToRollFrictCoef;
	public ICommand C_textRollToRollFrictCoef;
	public static String TEXT_textRollToRollFrictCoef = "textRollToRollFrictCoef";
	
	private Text textSpeedDifferentRatioTopRoll;
	public ICommand C_textSpeedDifferentRatioTopRoll;
	public static String TEXT_textSpeedDifferentRatioTopRoll = "textSpeedDifferentRatioTopRoll";
	
	private Text textSpeedDifferentRatioBottomRoll;
	public ICommand C_textSpeedDifferentRatioBottomRoll;
	public static String TEXT_textSpeedDifferentRatioBottomRoll = "textSpeedDifferentRatioBottomRoll";
	
	private Text textTopWRRotVel;
	public ICommand C_textTopWRRotVel;
	public static String TEXT_textTopWRRotVel = "textTopWRRotVel";
	
	private Text textBottomWRRotVel;
	public ICommand C_textBottomWRRotVel;
	public static String TEXT_textBottomWRRotVel = "textBottomWRRotVel";
	
	private Text textTopBURRotVel;
	public ICommand C_textTopBURRotVel;
	public static String TEXT_textTopBURRotVel = "textTopBURRotVel";
	
	private Text textBottomBURRotVel;
	public ICommand C_textBottomBURRotVel;
	public static String TEXT_textBottomBURRotVel = "textBottomBURRotVel";
	
	// group Roll Material Parameter
	private Group grpRollMaterialParameter;
	
	private Text textRollYoungsModulus;
	public ICommand C_textRollYoungsModulus;
	public static String TEXT_textRollYoungsModulus = "textRollYoungsModulus";
	
	private Text textRollPoissonsRatio;
	public ICommand C_textRollPoissonsRatio;
	public static String TEXT_textRollPoissonsRatio = "textRollPoissonsRatio";
	
	//group 5
	private Group grpMaterialParameter;
	
	private Button btnConstant1_YM;
	public ICommand C_btnConstant1_YM;
	public static String BUTTON_btnConstant1_YM = "btnConstant1_YM";
	
	private Button btnTable1_YM;
	public ICommand C_btnTable1_YM;
	public static String BUTTON_btnTable1_YM = "btnTable1_YM";
	
	private Text textYoungsModulus;
	public ICommand C_textYoungsModulus;
	public static String TEXT_textYoungsModulus = "textYoungsModulus";
	
	private Button btnExplorerYoungsModulus;
	public ICommand C_btnExplorerYoungsModulus;
	public static String BUTTON_btnExplorerYoungsModulus = "btnExplorerYoungsModulus";
	
	private Button btnConstant4_FS;
	public ICommand C_btnConstant4_FS;
	public static String BUTTON_btnConstant4_FS = "btnConstant4_FS";
	
	private Button btnTable4_FS;
	public ICommand C_btnTable4_FS;
	public static String BUTTON_btnTable4_FS = "btnTable4_FS";
		
	private Text textFlowStress;
	public ICommand C_textFlowStress;
	public static String TEXT_textFlowStress = "textFlowStress";
	
	private Button btnExplorerFlowStress;
	public ICommand C_btnExplorerFlowStress;
	public static String BUTTON_btnExplorerFlowStress = "btnExplorerFlowStress";
	
	private Text textYieldStrength;
	public ICommand C_textYieldStrength;
	public static String TEXT_textYieldStrength = "textYieldStrength";
	
	private Text textTensileStrength;
	public ICommand C_textTensileStrength;
	public static String TEXT_textTensileStrength = "textTensileStrength";
	
	private Text textElongation;
	public ICommand C_textElongation;
	public static String Text_textElongation = "textElongation";
	
	private Button btnConstant2_TEC;
	public ICommand C_btnConstant2_TEC;
	public static String BUTTON_btnConstant2_TEC = "btnConstant2_TEC";
	
	private Button btnTable2_TEC;
	public ICommand C_btnTable2_TEC;
	public static String BUTTON_btnTable2_TEC = "btnTable2_TEC";
	
	private Text textThermalExpansionCoefficient;
	public ICommand C_textThermalExpansionCoefficient;
	public static String TEXT_textThermalExpansionCoefficient = "textThermalExpansionCoefficient";
	
	private Button btnExplorerThermalExpansionCoefficient;
	public ICommand C_btnExplorerThermalExpansionCoefficient;
	public static String Button_btnExplorerThermalExpansionCoefficient = "btnExplorerThermalExpansionCoefficient";
	
	private Button btnConstant3_PR;
	public ICommand C_btnConstant3_PR;
	public static String BUTTON_btnConstant3_PR = "btnConstant3_PR";
	
	private Button btnTable3_PR;
	public ICommand C_btnTable3;
	public static String BUTTON_btnTable3_PR = "btnTable3_PR";
	
	private Text textPoissonsRatio;
	public ICommand C_textPoissonsRatio;
	public static String TEXT_textPoissonsRatio = "textPoissonsRatio";
	
	private Button btnExplorerPoissonsRatio;
	public ICommand C_btnExplorerPoissonsRatio;
	public static String BUTTON_btnExplorerPoissonsRatio = "btnExplorerPoissonsRatio";
	
	private Text textMassDensity;
	public ICommand C_textMassDensity;
	public static String TEXT_textMassDensity = "textMassDensity";
	
	
	//group 6
	private Group grpAnalysisInformation;
	
	private Text textAnalysisTime;
	public ICommand C_textAnalysisTime;
	public static String TEXT_textAnalysisTime = "textAnalysisTime";
	
	private Text textNoOfInc;
	public ICommand C_textNoOfInc;
	public static String TEXT_textNoOfInc = "textNoOfInc";
	
	private Text textPostWritingFrequency;
	public ICommand C_textPostWritingFrequency;
	public static String TEXT_textPostWritingFrequency = "textPostWritingFrequency";
	
	private Text textTimeIncrement;
	public ICommand C_textTimeIncrement;
	public static String TEXT_textTimeIncrement= "textTimeIncrement";
	
	private Button btnParallelDDM;
	public ICommand C_btnParallelDDM;
	public static String BUTTON_btnParallelDDM = "btnParallelDDM";
	
	private Spinner spinnerDomain;
	public ICommand C_spinnerDomain;
	public static String SPINNER_spinnerDomain = "spinnerDomain";
	
	private Button btnParallelMultiThread;
	public ICommand C_btnParallelMultiThread;
	public static String BUTTON_btnParallelMultiThread = "btnParallelMultiThread";
	
	private Spinner spinnerThread;
	public ICommand C_spinnerThread;
	public static String SPINNER_spinnerThread = "spinnerThread";
	
	//
	// Common
	//
	private Label lblModelNameValue;
	private Label lblWorkspacePath;
	
	private Button btnApply;
	public ICommand C_btnApply;
	public static String BUTTON_btnApply = "btnApply";
	
	
	
	
	//
	// set get method
	//
	public TabFolder getTabFolder() {
		return tabFolder;
	}
	public void setTabFolder(TabFolder tabFolder) {
		this.tabFolder = tabFolder;
	}
	public ICommand getC_tabFolder() {
		return C_tabFolder;
	}
	public void setC_tabFolder(ICommand c_tabFolder) {
		C_tabFolder = c_tabFolder;
	}
	
	
	public TableViewer getTableViewerSlabPlateInfo() {
		return tableViewerSlabPlateInfo;
	}
	public void setTableViewerSlabPlateInfo(TableViewer tableViewerSlabPlateInfo) {
		this.tableViewerSlabPlateInfo = tableViewerSlabPlateInfo;
	}
	public ICommand getC_tableViewerSlabPlateInfo() {
		return C_tableViewerSlabPlateInfo;
	}
	public void setC_tableViewerSlabPlateInfo(ICommand c_tableViewerSlabPlateInfo) {
		C_tableViewerSlabPlateInfo = c_tableViewerSlabPlateInfo;
	}
	
	
	public TableViewer getTableViewerVariable() {
		return tableViewerVariable;
	}
	public void setTableViewerVariable(TableViewer tableViewerVariable) {
		this.tableViewerVariable = tableViewerVariable;
	}
	public ICommand getC_tableViewerVariable() {
		return C_tableViewerVariable;
	}
	public void setC_tableViewerVariable(ICommand c_tableViewerVariable) {
		C_tableViewerVariable = c_tableViewerVariable;
	}
	
	
	public TableViewer getTableViewerPLog() {
		return tableViewerPLog;
	}
	public void setTableViewerPLog(TableViewer tableViewerPLog) {
		this.tableViewerPLog = tableViewerPLog;
	}
	public ICommand getC_tableViewerPLog() {
		return C_tableViewerPLog;
	}
	public void setC_tableViewerPLog(ICommand c_tableViewerPLog) {
		C_tableViewerPLog = c_tableViewerPLog;
	}
	
	
	public Button getBtnImportPLog() {
		return btnImportPLog;
	}
	public void setBtnImportPLog(Button btnImportPLog) {
		this.btnImportPLog = btnImportPLog;
	}
	public ICommand getC_btnImportPLog() {
		return C_btnImportPLog;
	}
	public void setC_btnImportPLog(ICommand c_btnImportPLog) {
		C_btnImportPLog = c_btnImportPLog;
	}
	
	public Button getBtnExportPLog() {
		return btnExportPLog;
	}
	public void setBtnExportPLog(Button btnExportPLog) {
		this.btnExportPLog = btnExportPLog;
	}
	public ICommand getC_btnExportPLog() {
		return C_btnExportPLog;
	}
	public void setC_btnExportPLog(ICommand c_btnExportPLog) {
		C_btnExportPLog = c_btnExportPLog;
	}
	
	public Button getBtnApply() {
		return btnApply;
	}
	public void setBtnApply(Button btnApply) {
		this.btnApply = btnApply;
	}
	public ICommand getC_btnApply() {
		return C_btnApply;
	}
	public void setC_btnApply(ICommand c_btnApply) {
		C_btnApply = c_btnApply;
	}
	
	
	public Label getLblModelNameValue() {
		return lblModelNameValue;
	}
	public void setLblModelNameValue(Label lblModelNameValue) {
		this.lblModelNameValue = lblModelNameValue;
	}
	
	
	public Label getLblWorkspacePath() {
		return lblWorkspacePath;
	}
	public void setLblWorkspacePath(Label lblWorkspacePath) {
		this.lblWorkspacePath = lblWorkspacePath;
	}
	
	
	public Button getBtnF1() {
		return btnF1;
	}
	public void setBtnF1(Button btnF1) {
		this.btnF1 = btnF1;
	}
	public ICommand getC_btnF1() {
		return C_btnF1;
	}
	public void setC_btnF1(ICommand c_btnF1) {
		C_btnF1 = c_btnF1;
	}
	
	
	public Button getBtnF2() {
		return btnF2;
	}
	public void setBtnF2(Button btnF2) {
		this.btnF2 = btnF2;
	}
	public ICommand getC_btnF2() {
		return C_btnF2;
	}
	public void setC_btnF2(ICommand c_btnF2) {
		C_btnF2 = c_btnF2;
	}
	
	
	public Button getBtnF3() {
		return btnF3;
	}
	public void setBtnF3(Button btnF3) {
		this.btnF3 = btnF3;
	}
	public ICommand getC_btnF3() {
		return C_btnF3;
	}
	public void setC_btnF3(ICommand c_btnF3) {
		C_btnF3 = c_btnF3;
	}
	
	
	public Button getBtnF4() {
		return btnF4;
	}
	public void setBtnF4(Button btnF4) {
		this.btnF4 = btnF4;
	}
	public ICommand getC_btnF4() {
		return C_btnF4;
	}
	public void setC_btnF4(ICommand c_btnF4) {
		C_btnF4 = c_btnF4;
	}
	
	
	public Button getBtnF5() {
		return btnF5;
	}
	public void setBtnF5(Button btnF5) {
		this.btnF5 = btnF5;
	}
	public ICommand getC_btnF5() {
		return C_btnF5;
	}
	public void setC_btnF5(ICommand c_btnF5) {
		C_btnF5 = c_btnF5;
	}
	
	
	public Button getBtnF6() {
		return btnF6;
	}
	public void setBtnF6(Button btnF6) {
		this.btnF6 = btnF6;
	}
	public ICommand getC_btnF6() {
		return C_btnF6;
	}
	public void setC_btnF6(ICommand c_btnF6) {
		C_btnF6 = c_btnF6;
	}
	
	
	public Button getBtnF7() {
		return btnF7;
	}
	public void setBtnF7(Button btnF7) {
		this.btnF7 = btnF7;
	}
	public ICommand getC_btnF7() {
		return C_btnF7;
	}
	public void setC_btnF7(ICommand c_btnF7) {
		C_btnF7 = c_btnF7;
	}
	
	
	public Group getGrpWorkRollwrParameter() {
		return grpWorkRollwrParameter;
	}
	public void setGrpWorkRollwrParameter(Group grpWorkRollwrParameter) {
		this.grpWorkRollwrParameter = grpWorkRollwrParameter;
	}
	
	
	public Text getTextTopWRDiameter() {
		return textTopWRDiameter;
	}
	public void setTextTopWRDiameter(Text textTopWRDiameter) {
		this.textTopWRDiameter = textTopWRDiameter;
	}
	public ICommand getC_textTopWRDiameter() {
		return C_textTopWRDiameter;
	}
	public void setC_textTopWRDiameter(ICommand c_textTopWRDiameter) {
		C_textTopWRDiameter = c_textTopWRDiameter;
	}
	
	
	public Text getTextBottomWRDiameter() {
		return textBottomWRDiameter;
	}
	public void setTextBottomWRDiameter(Text textBottomWRDiameter) {
		this.textBottomWRDiameter = textBottomWRDiameter;
	}
	public ICommand getC_textBottomWRDiameter() {
		return C_textBottomWRDiameter;
	}
	public void setC_textBottomWRDiameter(ICommand c_textBottomWRDiameter) {
		C_textBottomWRDiameter = c_textBottomWRDiameter;
	}
	
	
	public Text getTextWRCrown() {
		return textWRCrown;
	}
	public void setTextWRCrown(Text textWRCrown) {
		this.textWRCrown = textWRCrown;
	}
	public ICommand getC_textWRCrown() {
		return C_textWRCrown;
	}
	public void setC_textWRCrown(ICommand c_textWRCrown) {
		C_textWRCrown = c_textWRCrown;
	}
	
	
	public Text getTextWRLength() {
		return textWRLength;
	}
	public void setTextWRLength(Text textWRLength) {
		this.textWRLength = textWRLength;
	}
	public ICommand getC_textWRLength() {
		return C_textWRLength;
	}
	public void setC_textWRLength(ICommand c_textWRLength) {
		C_textWRLength = c_textWRLength;
	}
	
	
	public Text getTextWRMeshAngle() {
		return textWRMeshAngle;
	}
	public void setTextWRMeshAngle(Text textWRMeshAngle) {
		this.textWRMeshAngle = textWRMeshAngle;
	}
	public ICommand getC_textWRMeshAngle() {
		return C_textWRMeshAngle;
	}
	public void setC_textWRMeshAngle(ICommand c_textWRMeshAngle) {
		C_textWRMeshAngle = c_textWRMeshAngle;
	}

	
	public Text getTextWRChamferX() {
		return textWRChamferX;
	}
	public void setTextWRChamferX(Text textWRChamferX) {
		this.textWRChamferX = textWRChamferX;
	}
	public ICommand getC_textWRChamferX() {
		return C_textWRChamferX;
	}
	public void setC_textWRChamferX(ICommand c_textWRChamferX) {
		C_textWRChamferX = c_textWRChamferX;
	}
	
	
	public Text getTextWRChamferY() {
		return textWRChamferY;
	}
	public void setTextWRChamferY(Text textWRChamferY) {
		this.textWRChamferY = textWRChamferY;
	}
	public ICommand getC_textWRChamferY() {
		return C_textWRChamferY;
	}
	public void setC_textWRChamferY(ICommand c_textWRChamferY) {
		C_textWRChamferY = c_textWRChamferY;
	}
	
	
	public Text getTextWRRound() {
		return textWRRound;
	}
	public void setTextWRRound(Text textWRRound) {
		this.textWRRound = textWRRound;
	}
	public ICommand getC_textWRRound() {
		return C_textWRRound;
	}
	public void setC_textWRRound(ICommand c_textWRRound) {
		C_textWRRound = c_textWRRound;
	}
	
	
	public Group getGrpBackUpRollburParameter() {
		return grpBackUpRollburParameter;
	}
		public void setGrpBackUpRollburParameter(Group grpBackUpRollburParameter) {
		this.grpBackUpRollburParameter = grpBackUpRollburParameter;
	}
		
		
	public Text getTextTopBURDiameter() {
		return textTopBURDiameter;
	}
	public void setTextTopBURDiameter(Text textTopBURDiameter) {
		this.textTopBURDiameter = textTopBURDiameter;
	}
	public ICommand getC_textTopBURDiameter() {
		return C_textTopBURDiameter;
	}
	public void setC_textTopBURDiameter(ICommand c_textTopBURDiameter) {
		C_textTopBURDiameter = c_textTopBURDiameter;
	}
	
	
	public Text getTextBottomBURDiameter() {
		return textBottomBURDiameter;
	}
	public void setTextBottomBURDiameter(Text textBottomBURDiameter) {
		this.textBottomBURDiameter = textBottomBURDiameter;
	}
	public ICommand getC_textBottomBURDiameter() {
		return C_textBottomBURDiameter;
	}
	public void setC_textBottomBURDiameter(ICommand c_textBottomBURDiameter) {
		C_textBottomBURDiameter = c_textBottomBURDiameter;
	}
	
	
	public Text getTextBURLength() {
		return textBURLength;
	}
	public void setTextBURLength(Text textBURLength) {
		this.textBURLength = textBURLength;
	}
	public ICommand getC_textBURLength() {
		return C_textBURLength;
	}
	public void setC_textBURLength(ICommand c_textBURLength) {
		C_textBURLength = c_textBURLength;
	}
	
	
	public Text getTextBURMeshAngle() {
		return textBURMeshAngle;
	}
	public void setTextBURMeshAngle(Text textBURMeshAngle) {
		this.textBURMeshAngle = textBURMeshAngle;
	}
	public ICommand getC_textBURMeshAngle() {
		return C_textBURMeshAngle;
	}
	public void setC_textBURMeshAngle(ICommand c_textBURMeshAngle) {
		C_textBURMeshAngle = c_textBURMeshAngle;
	}
	
	public Text getTextBURChamferX() {
		return textBURChamferX;
	}
	public void setTextBURChamferX(Text textBURChamferX) {
		this.textBURChamferX = textBURChamferX;
	}
	public ICommand getC_textBURChamferX() {
		return C_textBURChamferX;
	}
	public void setC_textBURChamferX(ICommand c_textBURChamferX) {
		C_textBURChamferX = c_textBURChamferX;
	}
	
	
	public Text getTextBURChamferY() {
		return textBURChamferY;
	}
	public void setTextBURChamferY(Text textBURChamferY) {
		this.textBURChamferY = textBURChamferY;
	}
	public ICommand getC_textBURChamferY() {
		return C_textBURChamferY;
	}
	public void setC_textBURChamferY(ICommand c_textBURChamferY) {
		C_textBURChamferY = c_textBURChamferY;
	}
	
	
	
	
	
	
	public Group getGrpPlateParameter() {
		return grpPlateParameter;
	}
	public void setGrpPlateParameter(Group grpPlateParameter) {
		this.grpPlateParameter = grpPlateParameter;
	}
	
	
	public Text getTextThickness() {
		return textThickness;
	}
	public void setTextThickness(Text textThickness) {
		this.textThickness = textThickness;
	}
	public ICommand getC_textThickness() {
		return C_textThickness;
	}
	public void setC_textThickness(ICommand c_textThickness) {
		C_textThickness = c_textThickness;
	}
	
	
	public Text getTextWidth() {
		return textWidth;
	}
	public void setTextWidth(Text textWidth) {
		this.textWidth = textWidth;
	}
	public ICommand getC_textWidth() {
		return C_textWidth;
	}
	public void setC_textWidth(ICommand c_textWidth) {
		C_textWidth = c_textWidth;
	}
	
	
	public Text getTextLength() {
		return textLength;
	}
	public void setTextLength(Text textLength) {
		this.textLength = textLength;
	}
	public ICommand getC_textLength() {
		return C_textLength;
	}
	public void setC_textLength(ICommand c_textLength) {
		C_textLength = c_textLength;
	}
	
	
	public Text getTextEntryTemperature() {
		return textEntryTemperature;
	}
	public void setTextEntryTemperature(Text textEntryTemperature) {
		this.textEntryTemperature = textEntryTemperature;
	}
	public ICommand getC_textEntryTemperature() {
		return C_textEntryTemperature;
	}
	public void setC_textEntryTemperature(ICommand c_textEntryTemperature) {
		C_textEntryTemperature = c_textEntryTemperature;
	}
	
	
	public Text getTextExitTemperature() {
		return textExitTemperature;
	}
	public void setTextExitTemperature(Text textExitTemperature) {
		this.textExitTemperature = textExitTemperature;
	}
	public ICommand getC_textExitTemperature() {
		return C_textExitTemperature;
	}
	public void setC_textExitTemperature(ICommand c_textExitTemperature) {
		C_textExitTemperature = c_textExitTemperature;
	}
	
	
	public Text getTextInitialPosition() {
		return textInitialPosition;
	}
	public void setTextInitialPosition(Text textInitialPosition) {
		this.textInitialPosition = textInitialPosition;
	}
	public ICommand getC_textInitialPosition() {
		return C_textInitialPosition;
	}
	public void setC_textInitialPosition(ICommand c_textInitialPosition) {
		C_textInitialPosition = c_textInitialPosition;
	}
	
	
	public Text getTextMeshLength() {
		return textMeshLength;
	}
	public void setTextMeshLength(Text textMeshLength) {
		this.textMeshLength = textMeshLength;
	}
	public ICommand getC_textMeshLength() {
		return C_textMeshLength;
	}
	public void setC_textMeshLength(ICommand c_textMeshLength) {
		C_textMeshLength = c_textMeshLength;
	}
	
	
	public Text getTextThicknessMeshDivisions() {
		return textThicknessMeshDivisions;
	}
	public void setTextThicknessMeshDivisions(Text textThicknessMeshDivisions) {
		this.textThicknessMeshDivisions = textThicknessMeshDivisions;
	}
	public ICommand getC_textThicknessMeshDivisions() {
		return C_textThicknessMeshDivisions;
	}
	public void setC_textThicknessMeshDivisions(
			ICommand c_textThicknessMeshDivisions) {
		C_textThicknessMeshDivisions = c_textThicknessMeshDivisions;
	}
	
	
	public Text getTextPlateCrown() {
		return textPlateCrown;
	}
	public void setTextPlateCrown(Text textPlateCrown) {
		this.textPlateCrown = textPlateCrown;
	}
	public ICommand getC_textPlateCrown() {
		return C_textPlateCrown;
	}
	public void setC_textPlateCrown(ICommand c_textPlateCrown) {
		C_textPlateCrown = c_textPlateCrown;
	}
	
	
	
	public Group getGrpRollMaterialParameter() {
		return grpRollMaterialParameter;
	}
	public void setGrpRollMaterialParameter(Group grpRollMaterialParameter) {
		this.grpRollMaterialParameter = grpRollMaterialParameter;
	}
	
	
	public Text getTextRollYoungsModulus() {
		return textRollYoungsModulus;
	}
	public void setTextRollYoungsModulus(Text textRollYoungsModulus) {
		this.textRollYoungsModulus = textRollYoungsModulus;
	}
	public ICommand getC_textRollYoungsModulus() {
		return C_textRollYoungsModulus;
	}
	public void setC_textRollYoungsModulus(ICommand c_textRollYoungsModulus) {
		C_textRollYoungsModulus = c_textRollYoungsModulus;
	}
	
	
	public Text getTextRollPoissonsRatio() {
		return textRollPoissonsRatio;
	}
	public void setTextRollPoissonsRatio(Text textRollPoissonsRatio) {
		this.textRollPoissonsRatio = textRollPoissonsRatio;
	}
	public ICommand getC_textRollPoissonsRatio() {
		return C_textRollPoissonsRatio;
	}
	public void setC_textRollPoissonsRatio(ICommand c_textRollPoissonsRatio) {
		C_textRollPoissonsRatio = c_textRollPoissonsRatio;
	}
	
	
	public Group getGrpProcessInformation() {
		return grpProcessInformation;
	}
	public void setGrpProcessInformation(Group grpProcessInformation) {
		this.grpProcessInformation = grpProcessInformation;
	}
	
	
	public Text getTextVelocity() {
		return textVelocity;
	}
	public void setTextVelocity(Text textVelocity) {
		this.textVelocity = textVelocity;
	}
	public ICommand getC_textVelocity() {
		return C_textVelocity;
	}
	public void setC_textVelocity(ICommand c_textVelocity) {
		C_textVelocity = c_textVelocity;
	}
	
	
	public Text getTextRollGap() {
		return textRollGap;
	}
	public void setTextRollGap(Text textRollGap) {
		this.textRollGap = textRollGap;
	}
	public ICommand getC_textRollGap() {
		return C_textRollGap;
	}
	public void setC_textRollGap(ICommand c_textRollGap) {
		C_textRollGap = c_textRollGap;
	}
	
	
	public Text getTextPassLine() {
		return textPassLine;
	}
	public void setTextPassLine(Text textPassLine) {
		this.textPassLine = textPassLine;
	}
	public ICommand getC_textPassLine() {
		return C_textPassLine;
	}
	public void setC_textPassLine(ICommand c_textPassLine) {
		C_textPassLine = c_textPassLine;
	}
	
	
	public Text getTextPairCrossAngle() {
		return textPairCrossAngle;
	}
	public void setTextPairCrossAngle(Text textPairCrossAngle) {
		this.textPairCrossAngle = textPairCrossAngle;
	}
	public ICommand getC_textPairCrossAngle() {
		return C_textPairCrossAngle;
	}
	public void setC_textPairCrossAngle(ICommand c_textPairCrossAngle) {
		C_textPairCrossAngle = c_textPairCrossAngle;
	}
	
	
	public Text getTextBenderForce() {
		return textBenderForce;
	}
	public void setTextBenderForce(Text textBenderForce) {
		this.textBenderForce = textBenderForce;
	}
	public ICommand getC_textBenderForce() {
		return C_textBenderForce;
	}
	public void setC_textBenderForce(ICommand c_textBenderForce) {
		C_textBenderForce = c_textBenderForce;
	}
	
	
	public Text getTextRollTorque() {
		return textRollTorque;
	}
	public void setTextRollTorque(Text textRollTorque) {
		this.textRollTorque = textRollTorque;
	}
	public ICommand getC_textRollTorque() {
		return C_textRollTorque;
	}
	public void setC_textRollTorque(ICommand c_textRollTorque) {
		C_textRollTorque = c_textRollTorque;
	}
	
	
	public Text getTextTensionStress() {
		return textTensionStress;
	}
	public void setTextTensionStress(Text textTensionStress) {
		this.textTensionStress = textTensionStress;
	}
	public ICommand getC_textTensionStress() {
		return C_textTensionStress;
	}
	public void setC_textTensionStress(ICommand c_textTensionStress) {
		C_textTensionStress = c_textTensionStress;
	}
	
	
	public Text getTextRollToPlateFrictCoef() {
		return textRollToPlateFrictCoef;
	}
	public void setTextRollToPlateFrictCoef(Text textRollToPlateFrictCoef) {
		this.textRollToPlateFrictCoef = textRollToPlateFrictCoef;
	}
	public ICommand getC_textRollToPlateFrictCoef() {
		return C_textRollToPlateFrictCoef;
	}
	public void setC_textRollToPlateFrictCoef(ICommand c_textRollToPlateFrictCoef) {
		C_textRollToPlateFrictCoef = c_textRollToPlateFrictCoef;
	}
	
	
	public Text getTextRollToRollFrictCoef() {
		return textRollToRollFrictCoef;
	}
	public void setTextRollToRollFrictCoef(Text textRollToRollFrictCoef) {
		this.textRollToRollFrictCoef = textRollToRollFrictCoef;
	}
	public ICommand getC_textRollToRollFrictCoef() {
		return C_textRollToRollFrictCoef;
	}
	public void setC_textRollToRollFrictCoef(ICommand c_textRollToRollFrictCoef) {
		C_textRollToRollFrictCoef = c_textRollToRollFrictCoef;
	}
	
	
	public Group getGrpMaterialParameter() {
		return grpMaterialParameter;
	}
	public void setGrpMaterialParameter(Group grpMaterialParameter) {
		this.grpMaterialParameter = grpMaterialParameter;
	}
	
	
	public Button getBtnConstant1_YM() {
		return btnConstant1_YM;
	}
	public void setBtnConstant1_YM(Button btnConstant1_YM) {
		this.btnConstant1_YM = btnConstant1_YM;
	}
	public ICommand getC_btnConstant1_YM() {
		return C_btnConstant1_YM;
	}
	public void setC_btnConstant1_YM(ICommand c_btnConstant1_YM) {
		C_btnConstant1_YM = c_btnConstant1_YM;
	}
	
	
	public Button getBtnTable1_YM() {
		return btnTable1_YM;
	}
	public void setBtnTable1_YM(Button btnTable1_YM) {
		this.btnTable1_YM = btnTable1_YM;
	}
	public ICommand getC_btnTable1_YM() {
		return C_btnTable1_YM;
	}
	public void setC_btnTable1_YM(ICommand c_btnTable1_YM) {
		C_btnTable1_YM = c_btnTable1_YM;
	}
	
	
	public Text getTextYoungsModulus() {
		return textYoungsModulus;
	}
	public void setTextYoungsModulus(Text textYoungsModulus) {
		this.textYoungsModulus = textYoungsModulus;
	}
	public ICommand getC_textYoungsModulus() {
		return C_textYoungsModulus;
	}
	public void setC_textYoungsModulus(ICommand c_textYoungsModulus) {
		C_textYoungsModulus = c_textYoungsModulus;
	}
	
	
	public Button getBtnExplorerYoungsModulus() {
		return btnExplorerYoungsModulus;
	}
	public void setBtnExplorerYoungsModulus(Button btnExplorerYoungsModulus) {
		this.btnExplorerYoungsModulus = btnExplorerYoungsModulus;
	}
	public ICommand getC_btnExplorerYoungsModulus() {
		return C_btnExplorerYoungsModulus;
	}
	public void setC_btnExplorerYoungsModulus(ICommand c_btnExplorerYoungsModulus) {
		C_btnExplorerYoungsModulus = c_btnExplorerYoungsModulus;
	}
	
	
	public Button getBtnConstant2_TEC() {
		return btnConstant2_TEC;
	}
	public void setBtnConstant2_TEC(Button btnConstant2_TEC) {
		this.btnConstant2_TEC = btnConstant2_TEC;
	}
	public ICommand getC_btnConstant2_TEC() {
		return C_btnConstant2_TEC;
	}
	public void setC_btnConstant2_TEC(ICommand c_btnConstant2_TEC) {
		C_btnConstant2_TEC = c_btnConstant2_TEC;
	}
	
	
	public Button getBtnTable2_TEC() {
		return btnTable2_TEC;
	}
	public void setBtnTable2_TEC(Button btnTable2_TEC) {
		this.btnTable2_TEC = btnTable2_TEC;
	}
	public ICommand getC_btnTable2_TEC() {
		return C_btnTable2_TEC;
	}
	public void setC_btnTable2_TEC(ICommand c_btnTable2_TEC) {
		C_btnTable2_TEC = c_btnTable2_TEC;
	}
	
	
	public Text getTextThermalExpansionCoefficient() {
		return textThermalExpansionCoefficient;
	}
	public void setTextThermalExpansionCoefficient(
			Text textThermalExpansionCoefficient) {
		this.textThermalExpansionCoefficient = textThermalExpansionCoefficient;
	}
	public ICommand getC_textThermalExpansionCoefficient() {
		return C_textThermalExpansionCoefficient;
	}
	public void setC_textThermalExpansionCoefficient(
			ICommand c_textThermalExpansionCoefficient) {
		C_textThermalExpansionCoefficient = c_textThermalExpansionCoefficient;
	}
	
	
	public Button getBtnExplorerThermalExpansionCoefficient() {
		return btnExplorerThermalExpansionCoefficient;
	}
	public void setBtnExplorerThermalExpansionCoefficient(
			Button btnExplorerThermalExpansionCoefficient) {
		this.btnExplorerThermalExpansionCoefficient = btnExplorerThermalExpansionCoefficient;
	}
	public ICommand getC_btnExplorerThermalExpansionCoefficient() {
		return C_btnExplorerThermalExpansionCoefficient;
	}
	public void setC_btnExplorerThermalExpansionCoefficient(
			ICommand c_btnExplorerThermalExpansionCoefficient) {
		C_btnExplorerThermalExpansionCoefficient = c_btnExplorerThermalExpansionCoefficient;
	}
	
	
	public Button getBtnConstant3_PR() {
		return btnConstant3_PR;
	}
	public void setBtnConstant3_PR(Button btnConstant3_PR) {
		this.btnConstant3_PR = btnConstant3_PR;
	}
	public ICommand getC_btnConstant3_PR() {
		return C_btnConstant3_PR;
	}
	public void setC_btnConstant3_PR(ICommand c_btnConstant3_PR) {
		C_btnConstant3_PR = c_btnConstant3_PR;
	}
	
	
	public Button getBtnTable3_PR() {
		return btnTable3_PR;
	}
	public void setBtnTable3_PR(Button btnTable3_PR) {
		this.btnTable3_PR = btnTable3_PR;
	}
	public ICommand getC_btnTable3() {
		return C_btnTable3;
	}
	public void setC_btnTable3(ICommand c_btnTable3) {
		C_btnTable3 = c_btnTable3;
	}
	
	
	public Text getTextPoissonsRatio() {
		return textPoissonsRatio;
	}
	public void setTextPoissonsRatio(Text textPoissonsRatio) {
		this.textPoissonsRatio = textPoissonsRatio;
	}
	public ICommand getC_textPoissonsRatio() {
		return C_textPoissonsRatio;
	}
	public void setC_textPoissonsRatio(ICommand c_textPoissonsRatio) {
		C_textPoissonsRatio = c_textPoissonsRatio;
	}
	
	
	public Button getBtnExplorerPoissonsRatio() {
		return btnExplorerPoissonsRatio;
	}
	public void setBtnExplorerPoissonsRatio(Button btnExplorerPoissonsRatio) {
		this.btnExplorerPoissonsRatio = btnExplorerPoissonsRatio;
	}
	public ICommand getC_btnExplorerPoissonsRatio() {
		return C_btnExplorerPoissonsRatio;
	}
	public void setC_btnExplorerPoissonsRatio(ICommand c_btnExplorerPoissonsRatio) {
		C_btnExplorerPoissonsRatio = c_btnExplorerPoissonsRatio;
	}
	
	
	public Text getTextMassDensity() {
		return textMassDensity;
	}
	public void setTextMassDensity(Text textMassDensity) {
		this.textMassDensity = textMassDensity;
	}
	public ICommand getC_textMassDensity() {
		return C_textMassDensity;
	}
	public void setC_textMassDensity(ICommand c_textMassDensity) {
		C_textMassDensity = c_textMassDensity;
	}
	
	
	public Group getGrpAnalysisInformation() {
		return grpAnalysisInformation;
	}
	public void setGrpAnalysisInformation(Group grpAnalysisInformation) {
		this.grpAnalysisInformation = grpAnalysisInformation;
	}
	
	
	public Text getTextAnalysisTime() {
		return textAnalysisTime;
	}
	public void setTextAnalysisTime(Text textAnalysisTime) {
		this.textAnalysisTime = textAnalysisTime;
	}
	public ICommand getC_textAnalysisTime() {
		return C_textAnalysisTime;
	}
	public void setC_textAnalysisTime(ICommand c_textAnalysisTime) {
		C_textAnalysisTime = c_textAnalysisTime;
	}

	
	public Text getTextNoOfInc() {
		return textNoOfInc;
	}
	public void setTextNoOfInc(Text textNoOfInc) {
		this.textNoOfInc = textNoOfInc;
	}
	public ICommand getC_textNoOfInc() {
		return C_textNoOfInc;
	}
	public void setC_textNoOfInc(ICommand c_textNoOfInc) {
		C_textNoOfInc = c_textNoOfInc;
	}
	
	
	public Text getTextPostWritingFrequency() {
		return textPostWritingFrequency;
	}
	public void setTextPostWritingFrequency(Text textPostWritingFrequency) {
		this.textPostWritingFrequency = textPostWritingFrequency;
	}
	public ICommand getC_textPostWritingFrequency() {
		return C_textPostWritingFrequency;
	}
	public void setC_textPostWritingFrequency(ICommand c_textPostWritingFrequency) {
		C_textPostWritingFrequency = c_textPostWritingFrequency;
	}
	
	
	public Text getTextTimeIncrement() {
		return textTimeIncrement;
	}
	public void setTextTimeIncrement(Text textTimeIncrement) {
		this.textTimeIncrement = textTimeIncrement;
	}
	public ICommand getC_textTimeIncrement() {
		return C_textTimeIncrement;
	}
	public void setC_textTimeIncrement(ICommand c_textTimeIncrement) {
		C_textTimeIncrement = c_textTimeIncrement;
	}
	
	
	public Button getBtnParallelDDM() {
		return btnParallelDDM;
	}
	public void setBtnParallelDDM(Button btnParallelDDM) {
		this.btnParallelDDM = btnParallelDDM;
	}
	public ICommand getC_btnParallelDDM() {
		return C_btnParallelDDM;
	}
	public void setC_btnParallelDDM(ICommand c_btnParallelDDM) {
		C_btnParallelDDM = c_btnParallelDDM;
	}
	
	
	public Spinner getSpinnerDomain() {
		return spinnerDomain;
	}
	public void setSpinnerDomain(Spinner spinnerDomain) {
		this.spinnerDomain = spinnerDomain;
	}
	public ICommand getC_spinnerDomain() {
		return C_spinnerDomain;
	}
	public void setC_spinnerDomain(ICommand c_spinnerDomain) {
		C_spinnerDomain = c_spinnerDomain;
	}
	
	
	public Button getBtnParallelMultiThread() {
		return btnParallelMultiThread;
	}
	public void setBtnParallelMultiThread(Button btnParallelMultiThread) {
		this.btnParallelMultiThread = btnParallelMultiThread;
	}
	public ICommand getC_btnParallelMultiThread() {
		return C_btnParallelMultiThread;
	}
	public void setC_btnParallelMultiThread(ICommand c_btnParallelMultiThread) {
		C_btnParallelMultiThread = c_btnParallelMultiThread;
	}
	
	
	public Spinner getSpinnerThread() {
		return spinnerThread;
	}
	public void setSpinnerThread(Spinner spinnerThread) {
		this.spinnerThread = spinnerThread;
	}
	public ICommand getC_spinnerThread() {
		return C_spinnerThread;
	}
	public void setC_spinnerThread(ICommand c_spinnerThread) {
		C_spinnerThread = c_spinnerThread;
	}
	
	
	public Composite getCompositeDetail() {
		return compositeDetail;
	}
	public void setCompositeDetail(Composite compositeDetail) {
		this.compositeDetail = compositeDetail;
	}
	
	
	public Group getGroupSTAND() {
		return groupSTAND;
	}
	public void setGroupSTAND(Group groupSTAND) {
		this.groupSTAND = groupSTAND;
	}
	
	
	public Text getTextSpeedDifferentRatioTopRoll() {
		return textSpeedDifferentRatioTopRoll;
	}
	public void setTextSpeedDifferentRatioTopRoll(Text textSpeedDifferentRatioTopRoll) {
		this.textSpeedDifferentRatioTopRoll = textSpeedDifferentRatioTopRoll;
	}
	public ICommand getC_textSpeedDifferentRatioTopRoll() {
		return C_textSpeedDifferentRatioTopRoll;
	}
	public void setC_textSpeedDifferentRatioTopRoll(ICommand c_textSpeedDifferentRatioTopRoll) {
		C_textSpeedDifferentRatioTopRoll = c_textSpeedDifferentRatioTopRoll;
	}
	
	public Text getTextSpeedDifferentRatioBottomRoll() {
		return textSpeedDifferentRatioBottomRoll;
	}
	public void setTextSpeedDifferentRatioBottomRoll(Text textSpeedDifferentRatioBottomRoll) {
		this.textSpeedDifferentRatioBottomRoll = textSpeedDifferentRatioBottomRoll;
	}
	public ICommand getC_textSpeedDifferentRatioBottomRoll() {
		return C_textSpeedDifferentRatioBottomRoll;
	}
	public void setC_textSpeedDifferentRatioBottomRoll(ICommand c_textSpeedDifferentRatioBottomRoll) {
		C_textSpeedDifferentRatioBottomRoll = c_textSpeedDifferentRatioBottomRoll;
	}
	
	
	public Text getTextTopWRRotVel() {
		return textTopWRRotVel;
	}
	public void setTextTopWRRotVel(Text textTopWRRotVel) {
		this.textTopWRRotVel = textTopWRRotVel;
	}
	public ICommand getC_textTopWRRotVel() {
		return C_textTopWRRotVel;
	}
	public void setC_textTopWRRotVel(ICommand c_textTopWRRotVel) {
		C_textTopWRRotVel = c_textTopWRRotVel;
	}
	
	
	public Text getTextBottomWRRotVel() {
		return textBottomWRRotVel;
	}
	public void setTextBottomWRRotVel(Text textBottomWRRotVel) {
		this.textBottomWRRotVel = textBottomWRRotVel;
	}
	public ICommand getC_textBottomWRRotVel() {
		return C_textBottomWRRotVel;
	}
	public void setC_textBottomWRRotVel(ICommand c_textBottomWRRotVel) {
		C_textBottomWRRotVel = c_textBottomWRRotVel;
	}
	
	
	public Text getTextTopBURRotVel() {
		return textTopBURRotVel;
	}
	public void setTextTopBURRotVel(Text textTopBURRotVel) {
		this.textTopBURRotVel = textTopBURRotVel;
	}
	public ICommand getC_textTopBURRotVel() {
		return C_textTopBURRotVel;
	}
	public void setC_textTopBURRotVel(ICommand c_textTopBURRotVel) {
		C_textTopBURRotVel = c_textTopBURRotVel;
	}
	
	
	public Text getTextBottomBURRotVel() {
		return textBottomBURRotVel;
	}
	public void setTextBottomBURRotVel(Text textBottomBURRotVel) {
		this.textBottomBURRotVel = textBottomBURRotVel;
	}
	public ICommand getC_textBottomBURRotVel() {
		return C_textBottomBURRotVel;
	}
	public void setC_textBottomBURRotVel(ICommand c_textBottomBURRotVel) {
		C_textBottomBURRotVel = c_textBottomBURRotVel;
	}
	public Button getBtnConstant4_FS() {
		return btnConstant4_FS;
	}
	public void setBtnConstant4_FS(Button btnConstant4_FS) {
		this.btnConstant4_FS = btnConstant4_FS;
	}
	public ICommand getC_btnConstant4_FS() {
		return C_btnConstant4_FS;
	}
	public void setC_btnConstant4_FS(ICommand c_btnConstant4_FS) {
		C_btnConstant4_FS = c_btnConstant4_FS;
	}
	public Button getBtnTable4_FS() {
		return btnTable4_FS;
	}
	public void setBtnTable4_FS(Button btnTable4_FS) {
		this.btnTable4_FS = btnTable4_FS;
	}
	public ICommand getC_btnTable4_FS() {
		return C_btnTable4_FS;
	}
	public void setC_btnTable4_FS(ICommand c_btnTable4_FS) {
		C_btnTable4_FS = c_btnTable4_FS;
	}
	public Text getTextFlowStress() {
		return textFlowStress;
	}
	public void setTextFlowStress(Text textFlowStress) {
		this.textFlowStress = textFlowStress;
	}
	public ICommand getC_textFlowStress() {
		return C_textFlowStress;
	}
	public void setC_textFlowStress(ICommand c_textFlowStress) {
		C_textFlowStress = c_textFlowStress;
	}
	public Button getBtnExplorerFlowStress() {
		return btnExplorerFlowStress;
	}
	public void setBtnExplorerFlowStress(Button btnExplorerFlowStress) {
		this.btnExplorerFlowStress = btnExplorerFlowStress;
	}
	public ICommand getC_btnExplorerFlowStress() {
		return C_btnExplorerFlowStress;
	}
	public void setC_btnExplorerFlowStress(ICommand c_btnExplorerFlowStress) {
		C_btnExplorerFlowStress = c_btnExplorerFlowStress;
	}
	public Text getTextYieldStrength() {
		return textYieldStrength;
	}
	public void setTextYieldStrength(Text textYieldStrength) {
		this.textYieldStrength = textYieldStrength;
	}
	public ICommand getC_textYieldStrength() {
		return C_textYieldStrength;
	}
	public void setC_textYieldStrength(ICommand c_textYieldStrength) {
		C_textYieldStrength = c_textYieldStrength;
	}
	public Text getTextTensileStrength() {
		return textTensileStrength;
	}
	public void setTextTensileStrength(Text textTensileStrength) {
		this.textTensileStrength = textTensileStrength;
	}
	public ICommand getC_textTensileStrength() {
		return C_textTensileStrength;
	}
	public void setC_textTensileStrength(ICommand c_textTensileStrength) {
		C_textTensileStrength = c_textTensileStrength;
	}
	public Text getTextElongation() {
		return textElongation;
	}
	public void setTextElongation(Text textElongation) {
		this.textElongation = textElongation;
	}
	public ICommand getC_textElongation() {
		return C_textElongation;
	}
	public void setC_textElongation(ICommand c_textElongation) {
		C_textElongation = c_textElongation;
	}
	
}
