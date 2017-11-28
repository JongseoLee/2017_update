package com.js.ens.hrolling3d_ver3;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;

import com.js.ens.hrolling3d_ver3.core.MainController;
import com.js.ens.hrolling3d_ver3.core.Mediator;
import com.js.ens.hrolling3d_ver3.core.TableColumnLabel;
import com.js.ens.hrolling3d_ver3.core.UILabel;
import com.js.ens.hrolling3d_ver3.customWidget.CustomButton;
import com.js.ens.hrolling3d_ver3.customWidget.CustomSpinner;
import com.js.ens.hrolling3d_ver3.customWidget.CustomTabFolder;
import com.js.ens.hrolling3d_ver3.customWidget.CustomTableViewer;
import com.js.ens.hrolling3d_ver3.customWidget.CustomText;
import com.js.ens.hrolling3d_ver3.dialog.MessageDlg;
import com.js.ens.hrolling3d_ver3.handler.HandlerButton;
import com.js.ens.hrolling3d_ver3.handler.HandlerSpinner;
import com.js.ens.hrolling3d_ver3.handler.HandlerTabFolder;
import com.js.ens.hrolling3d_ver3.handler.HandlerTableViewer;
import com.js.ens.hrolling3d_ver3.handler.HandlerText;
import com.js.util.myUtil;

import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class View extends ViewPart {
	private Mediator med = Mediator.getInstance();
	private MainController MC = MainController.getInstance();
	
	public View() {
	}
	
	private TableColumnLabel tclObj = TableColumnLabel.getInstance();
	private UILabel ulObj = UILabel.getInstance();
	
	public static final String ID = "com.js.ens.hrolling3d_ver3.view";
	private Table tableSlabPlateInfo;
	private Table tableVariable;
	private Table tablePLog;
	
	private Text textTopWRDiameter;
	private Text textBottomWRDiameter;
	private Text textWRCrown;
	private Text textWRLength;
	private Text textWRMeshAngle;
	private Text textWRChamferX;
	private Text textWRChamferY;
	private Text textWRRound;
	
	private Text textTopBURDiameter;
	private Text textBottomBURDiameter;
	private Text textBURLength;
	private Text textBURMeshAngle;
	private Text textBURChamferX;
	private Text textBURChamferY;

	private Text textThickness;
	private Text textWidth;
	private Text textLength;
	private Text textEntryTemperature;
	private Text textExitTemperature;
	private Text textInitialPosition;
	private Text textMeshLength;
	private Text textThicknessMeshDivisions;
	private Text textPlateCrown;
	
	private Text textVelocity;
	private Text textRollGap;
	private Text textPassLine;
	private Text textPairCrossAngle;
	private Text textBenderForce;
	private Text textRollTorque;
	private Text textTensionStress;
	private Text textRollToPlateFrictCoef;
	private Text textRollToRollFrictCoef;
	private Text textSpeedDifferentRatioTopRoll;
	private Text textSpeedDifferentRatioBottomRoll;
	private Text textTopWRRotVel;
	private Text textBottomWRRotVel;
	private Text textTopBURRotVel;
	private Text textBottomBURRotVel;

	private Text textYoungsModulus;
	private Text textFlowStress;
	private Text textYieldStrength;
	private Text textTensileStrength;
	private Text textElongation;
	
	private Text textThermalExpansionCoefficient;
	private Text textPoissonsRatio;
	private Text textMassDensity;
	
	private Text textRollYoungsModulus;
	private Text textRollPoissonsRatio;
	
	private Text textAnalysisTime;
	private Text textNoOfInc;
	private Text textPostWritingFrequency;
	private Text textTimeIncrement;
	
	
	
	public void createPartControl(Composite parent) {
		//--------------------------------------------------------------------------
		//tclObj = new TableColumnLabel();
		//tclObj.readTableColumnLabelFile();
		
		//ulObj = new UILabel();
		//ulObj.readUILabelFile();
		//--------------------------------------------------------------------------
		parent.setLayout(new FormLayout());
		
		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		med.setTabFolder(tabFolder);
		CustomTabFolder C_tabFolder = new CustomTabFolder(Mediator.TABFOLDER_tabFolder,med);
		med.setC_tabFolder(C_tabFolder);
		C_tabFolder.setCustomWidget_tabFolder();
		FormData fd_tabFolder = new FormData();
		fd_tabFolder.top = new FormAttachment(0,50);
		fd_tabFolder.left = new FormAttachment(0,0);
		fd_tabFolder.right = new FormAttachment(100,0);
		fd_tabFolder.bottom = new FormAttachment(100,-50);
		tabFolder.setLayoutData(fd_tabFolder);
		
		TabItem tbtmTotal = new TabItem(tabFolder, SWT.NONE);
		tbtmTotal.setText(ulObj.getUILabelValue(UILabel.Tab1Label));
		
		Composite compositeTotal = new Composite(tabFolder, SWT.NONE);
		compositeTotal.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmTotal.setControl(compositeTotal);
		compositeTotal.setLayout(new FormLayout());
		
		Label lblSlabAndPlate = new Label(compositeTotal, SWT.NONE);
		FormData fd_lblSlabAndPlate = new FormData();
		fd_lblSlabAndPlate.top = new FormAttachment(0, 10);
		fd_lblSlabAndPlate.left = new FormAttachment(0, 10);
		lblSlabAndPlate.setLayoutData(fd_lblSlabAndPlate);
		lblSlabAndPlate.setText(ulObj.getUILabelValue(UILabel.Table1Label));
		
		TableViewer tableViewerSlabPlateInfo = new TableViewer(compositeTotal, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerSlabPlateInfo(tableViewerSlabPlateInfo);
		CustomTableViewer C_tableViewerSlabPlateInfo = new CustomTableViewer(Mediator.TABLEVIEWER_tableViewerSlabPlateInfo,med);
		med.setC_tableViewerSlabPlateInfo(C_tableViewerSlabPlateInfo);
		C_tableViewerSlabPlateInfo.setCustomWidget_tableViewerSlabPlateInfo();
		tableSlabPlateInfo = tableViewerSlabPlateInfo.getTable();
		tableSlabPlateInfo.setLinesVisible(true);
		tableSlabPlateInfo.setHeaderVisible(true);
		FormData fd_tableSlabPlateInfo = new FormData();
		fd_tableSlabPlateInfo.top = new FormAttachment(lblSlabAndPlate,10);
		fd_tableSlabPlateInfo.left = new FormAttachment(0,10);
		fd_tableSlabPlateInfo.right = new FormAttachment(0,745);
		fd_tableSlabPlateInfo.bottom = new FormAttachment(0,100);
		tableSlabPlateInfo.setLayoutData(fd_tableSlabPlateInfo);
		
		Label lblVariable = new Label(compositeTotal, SWT.NONE);
		FormData fd_lblVariable = new FormData();
		fd_lblVariable.top = new FormAttachment(tableSlabPlateInfo, 20);
		fd_lblVariable.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		lblVariable.setLayoutData(fd_lblVariable);
		lblVariable.setText(ulObj.getUILabelValue(UILabel.Table2Label));
		
		TableViewer tableViewerVariable = new TableViewer(compositeTotal, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerVariable(tableViewerVariable);
		CustomTableViewer C_tableViewerVariable = new CustomTableViewer(Mediator.TABLEVIEWER_tableViewerVariable,med);
		med.setC_tableViewerVariable(C_tableViewerVariable);
		C_tableViewerVariable.setCustomWidget_tableViewerVariable();
		tableVariable = tableViewerVariable.getTable();
		tableVariable.setLinesVisible(true);
		tableVariable.setHeaderVisible(true);
		FormData fd_tableVariable = new FormData();
		fd_tableVariable.top = new FormAttachment(lblVariable, 10);
		fd_tableVariable.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		fd_tableVariable.right = new FormAttachment(0,980);
		fd_tableVariable.bottom = new FormAttachment(0,205);
		tableVariable.setLayoutData(fd_tableVariable);
		
		Label lblPLog = new Label(compositeTotal, SWT.NONE);
		FormData fd_lblPLog = new FormData();
		fd_lblPLog.top = new FormAttachment(tableVariable, 20);
		fd_lblPLog.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		lblPLog.setLayoutData(fd_lblPLog);
		lblPLog.setText(ulObj.getUILabelValue(UILabel.Table3Label));
		
		TableViewer tableViewerPLog = new TableViewer(compositeTotal, SWT.BORDER | SWT.FULL_SELECTION);
		med.setTableViewerPLog(tableViewerPLog);
		CustomTableViewer C_tableViewerPLog = new CustomTableViewer(Mediator.TABLEVIEWER_tableViewerPLog,med);
		med.setC_tableViewerPLog(C_tableViewerPLog);
		C_tableViewerPLog.setCustomWidget_tableViewerPLog();
		tablePLog = tableViewerPLog.getTable();
		tablePLog.setLinesVisible(true);
		tablePLog.setHeaderVisible(true);
		FormData fd_tablePLog = new FormData();
		fd_tablePLog.top = new FormAttachment(lblPLog, 10);
		fd_tablePLog.left = new FormAttachment(lblSlabAndPlate, 0, SWT.LEFT);
		fd_tablePLog.right = new FormAttachment(0,690);
		fd_tablePLog.bottom = new FormAttachment(100,-10);
		tablePLog.setLayoutData(fd_tablePLog);
		
		Button btnImportPLog = new Button(compositeTotal, SWT.NONE);
		med.setBtnImportPLog(btnImportPLog);
		CustomButton C_btnImportPLog = new CustomButton(Mediator.BUTTON_btnImportPLog,med);
		med.setC_btnImportPLog(C_btnImportPLog);
		C_btnImportPLog.setCustomWidget_btnImportPLog();
		FormData fd_btnImportPLog = new FormData();
		fd_btnImportPLog.bottom = new FormAttachment(tablePLog, 0, SWT.BOTTOM);
		fd_btnImportPLog.right = new FormAttachment(100, -100);
		btnImportPLog.setLayoutData(fd_btnImportPLog);
		btnImportPLog.setText(ulObj.getUILabelValue(UILabel.Import_P_Log));
		
		Button btnExportPLog = new Button(compositeTotal, SWT.NONE);
		med.setBtnExportPLog(btnExportPLog);
		CustomButton C_btnExportPLog = new CustomButton(Mediator.BUTTON_btnExportPLog,med);
		med.setC_btnExportPLog(C_btnExportPLog);
		C_btnExportPLog.setCustomWidget_btnExportPLog();
		FormData fd_btnExportPLog = new FormData();
		fd_btnExportPLog.bottom = new FormAttachment(tablePLog, 0, SWT.BOTTOM);
		fd_btnExportPLog.right = new FormAttachment(100, -10);
		btnExportPLog.setLayoutData(fd_btnExportPLog);
		btnExportPLog.setText(ulObj.getUILabelValue(UILabel.Export_P_Log));
		
		
		
		//----------------------------------------------------------------------
		//----------------------------------------------------------------------
		//----------------------------------------------------------------------
		TabItem tbtmDetail = new TabItem(tabFolder, SWT.NONE);
		tbtmDetail.setText(ulObj.getUILabelValue(UILabel.Tab2Label));
		
		Composite compositeDetail = new Composite(tabFolder, SWT.NONE);
		med.setCompositeDetail(compositeDetail);
		compositeDetail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		tbtmDetail.setControl(compositeDetail);
		compositeDetail.setLayout(new FormLayout());
		
		Group groupSTAND = new Group(compositeDetail, SWT.NONE);
		med.setGroupSTAND(groupSTAND);
		groupSTAND.setText(ulObj.getUILabelValue(UILabel.STAND));
		groupSTAND.setLayout(new FormLayout());
		FormData fd_groupSTAND = new FormData();
		fd_groupSTAND.top = new FormAttachment(0, 10);
		fd_groupSTAND.left = new FormAttachment(0, 10);		
		fd_groupSTAND.right = new FormAttachment(0, 410);
		groupSTAND.setLayoutData(fd_groupSTAND);
		
		Button btnF1 = new Button(groupSTAND, SWT.RADIO);
		med.setBtnF1(btnF1);
		CustomButton C_btnF1 = new CustomButton(Mediator.BUTTON_btnF1,med);
		med.setC_btnF1(C_btnF1);
		C_btnF1.setCustomWidget_btnF1();
		btnF1.setSelection(true);
		FormData fd_btnF1 = new FormData();
		fd_btnF1.top = new FormAttachment(0, 5);
		fd_btnF1.left = new FormAttachment(0, 5);
		fd_btnF1.bottom = new FormAttachment(100,-5);
		btnF1.setLayoutData(fd_btnF1);
		btnF1.setText(ulObj.getUILabelValue(UILabel.F1));
		
		Button btnF2 = new Button(groupSTAND, SWT.RADIO);
		med.setBtnF2(btnF2);
		CustomButton C_btnF2 = new CustomButton(Mediator.BUTTON_btnF2,med);
		med.setC_btnF2(C_btnF2);
		C_btnF2.setCustomWidget_btnF2();
		FormData fd_btnF2 = new FormData();
		fd_btnF2.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF2.left = new FormAttachment(btnF1, 20);
		fd_btnF2.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF2.setLayoutData(fd_btnF2);
		btnF2.setText(ulObj.getUILabelValue(UILabel.F2));

		Button btnF3 = new Button(groupSTAND, SWT.RADIO);
		med.setBtnF3(btnF3);
		CustomButton C_btnF3 = new CustomButton(Mediator.BUTTON_btnF3,med);
		med.setC_btnF3(C_btnF3);
		C_btnF3.setCustomWidget_btnF3();
		FormData fd_btnF3 = new FormData();
		fd_btnF3.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF3.left = new FormAttachment(btnF2, 20);
		fd_btnF3.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF3.setLayoutData(fd_btnF3);
		btnF3.setText(ulObj.getUILabelValue(UILabel.F3));
		
		Button btnF4 = new Button(groupSTAND, SWT.RADIO);
		med.setBtnF4(btnF4);
		CustomButton C_btnF4 = new CustomButton(Mediator.BUTTON_btnF4,med);
		med.setC_btnF4(C_btnF4);
		C_btnF4.setCustomWidget_btnF4();
		FormData fd_btnF4 = new FormData();
		fd_btnF4.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF4.left = new FormAttachment(btnF3, 20);
		fd_btnF4.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF4.setLayoutData(fd_btnF4);
		btnF4.setText(ulObj.getUILabelValue(UILabel.F4));
		
		Button btnF5 = new Button(groupSTAND, SWT.RADIO);
		med.setBtnF5(btnF5);
		CustomButton C_btnF5 = new CustomButton(Mediator.BUTTON_btnF5,med);
		med.setC_btnF5(C_btnF5);
		C_btnF5.setCustomWidget_btnF5();
		FormData fd_btnF5 = new FormData();
		fd_btnF5.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF5.left = new FormAttachment(btnF4, 20);
		fd_btnF5.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF5.setLayoutData(fd_btnF5);
		btnF5.setText(ulObj.getUILabelValue(UILabel.F5));
		
		Button btnF6 = new Button(groupSTAND, SWT.RADIO);
		med.setBtnF6(btnF6);
		CustomButton C_btnF6 = new CustomButton(Mediator.BUTTON_btnF6,med);
		med.setC_btnF6(C_btnF6);
		C_btnF6.setCustomWidget_btnF6();
		FormData fd_btnF6 = new FormData();
		fd_btnF6.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF6.left = new FormAttachment(btnF5, 20);
		fd_btnF6.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF6.setLayoutData(fd_btnF6);
		btnF6.setText(ulObj.getUILabelValue(UILabel.F6));
		
		Button btnF7 = new Button(groupSTAND, SWT.RADIO);
		med.setBtnF7(btnF7);
		CustomButton C_btnF7 = new CustomButton(Mediator.BUTTON_btnF7,med);
		med.setC_btnF7(C_btnF7);
		C_btnF7.setCustomWidget_btnF7();
		FormData fd_btnF7 = new FormData();
		fd_btnF7.top = new FormAttachment(btnF1, 0,SWT.TOP);
		fd_btnF7.left = new FormAttachment(btnF6, 20);
		fd_btnF7.bottom = new FormAttachment(btnF1,0,SWT.BOTTOM);
		btnF7.setLayoutData(fd_btnF7);
		btnF7.setText(ulObj.getUILabelValue(UILabel.F7));
		
		
		//=============
		// Group1 start
		//
		Group grpWorkRollwrParameter = new Group(compositeDetail, SWT.NONE);
		med.setGrpWorkRollwrParameter(grpWorkRollwrParameter);
		grpWorkRollwrParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpWorkRollwrParameter.setText(ulObj.getUILabelValue(UILabel.Work_Roll_WR_Parameters));
		grpWorkRollwrParameter.setLayout(new FormLayout());
		FormData fd_grpWorkRollwrParameter = new FormData();
		fd_grpWorkRollwrParameter.top = new FormAttachment(groupSTAND, 5);
		fd_grpWorkRollwrParameter.left = new FormAttachment(0,10);
		fd_grpWorkRollwrParameter.right = new FormAttachment(0, 390);
		fd_grpWorkRollwrParameter.bottom = new FormAttachment(groupSTAND, 250, SWT.BOTTOM);
		grpWorkRollwrParameter.setLayoutData(fd_grpWorkRollwrParameter);
		
		Label lblTopWRDiameter = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblTopWRDiameter = new FormData();
		fd_lblTopWRDiameter.top = new FormAttachment(0, 10);
		fd_lblTopWRDiameter.left = new FormAttachment(0, 10);
		lblTopWRDiameter.setLayoutData(fd_lblTopWRDiameter);
		lblTopWRDiameter.setText(ulObj.getUILabelValue(UILabel.Top_WR_Diameter));
		
		textTopWRDiameter = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextTopWRDiameter(textTopWRDiameter);
		CustomText C_textTopWRDiameter = new CustomText(Mediator.TEXT_textTopWRDiameter,med);
		med.setC_textTopWRDiameter(C_textTopWRDiameter);
		C_textTopWRDiameter.setCustomWidget_textTopWRDiameter();
		FormData fd_textTopWRDiameter = new FormData();
		fd_textTopWRDiameter.top = new FormAttachment(lblTopWRDiameter,-2,SWT.TOP);
		fd_textTopWRDiameter.left = new FormAttachment(lblTopWRDiameter,30,SWT.RIGHT);
		fd_textTopWRDiameter.right = new FormAttachment(100, -10);
		textTopWRDiameter.setLayoutData(fd_textTopWRDiameter);
		
		Label lblBottomWRDiameter = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblBottomWRDiameter = new FormData();
		fd_lblBottomWRDiameter.top = new FormAttachment(lblTopWRDiameter, 10);
		fd_lblBottomWRDiameter.left = new FormAttachment(lblTopWRDiameter, 0,SWT.LEFT);
		lblBottomWRDiameter.setLayoutData(fd_lblBottomWRDiameter);
		lblBottomWRDiameter.setText(ulObj.getUILabelValue(UILabel.Bottom_WR_Diameter));
		
		textBottomWRDiameter = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextBottomWRDiameter(textBottomWRDiameter);
		CustomText C_textBottomWRDiameter = new CustomText(Mediator.TEXT_textBottomWRDiameter,med);
		med.setC_textBottomWRDiameter(C_textBottomWRDiameter);
		C_textBottomWRDiameter.setCustomWidget_textBottomWRDiameter();
		FormData fd_textBottomWRDiameter = new FormData();
		fd_textBottomWRDiameter.top = new FormAttachment(lblBottomWRDiameter,-2,SWT.TOP);
		fd_textBottomWRDiameter.left = new FormAttachment(textTopWRDiameter, 0,SWT.LEFT);
		fd_textBottomWRDiameter.right = new FormAttachment(100, -10);
		textBottomWRDiameter.setLayoutData(fd_textBottomWRDiameter);
		
		Label lblWRCrown = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblWRCrown = new FormData();
		fd_lblWRCrown.top = new FormAttachment(lblBottomWRDiameter, 10);
		fd_lblWRCrown.left = new FormAttachment(lblTopWRDiameter, 0,SWT.LEFT);
		lblWRCrown.setLayoutData(fd_lblWRCrown);
		lblWRCrown.setText(ulObj.getUILabelValue(UILabel.WR_Crown));
		
		textWRCrown = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextWRCrown(textWRCrown);
		CustomText C_textWRCrown = new CustomText(Mediator.TEXT_textWRCrown,med);
		med.setC_textWRCrown(C_textWRCrown);
		C_textWRCrown.setCustomWidget_textWRCrown();
		FormData fd_textWRCrown = new FormData();
		fd_textWRCrown.top = new FormAttachment(lblWRCrown,-2,SWT.TOP);
		fd_textWRCrown.left = new FormAttachment(textTopWRDiameter, 0,SWT.LEFT);
		fd_textWRCrown.right = new FormAttachment(100, -10);
		textWRCrown.setLayoutData(fd_textWRCrown);
		
		Label lblWRLength = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblWRLength = new FormData();
		fd_lblWRLength.top = new FormAttachment(lblWRCrown, 10);
		fd_lblWRLength.left = new FormAttachment(lblTopWRDiameter, 0, SWT.LEFT);
		lblWRLength.setLayoutData(fd_lblWRLength);
		lblWRLength.setText(ulObj.getUILabelValue(UILabel.WR_Length));
		
		textWRLength = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextWRLength(textWRLength);
		CustomText C_textWRLength = new CustomText(Mediator.TEXT_textWRLength,med);
		med.setC_textWRLength(C_textWRLength);
		C_textWRLength.setCustomWidget_textWRLength();
		FormData fd_textWRLength = new FormData();
		fd_textWRLength.top = new FormAttachment(lblWRLength,-2,SWT.TOP);
		fd_textWRLength.left = new FormAttachment(textTopWRDiameter, 0,SWT.LEFT);
		fd_textWRLength.right = new FormAttachment(100, -10);
		textWRLength.setLayoutData(fd_textWRLength);
		textWRLength.setEnabled(false);
		
		Label lblWRMeshAngle = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblWRMeshAngle = new FormData();
		fd_lblWRMeshAngle.top = new FormAttachment(lblWRLength, 10);
		fd_lblWRMeshAngle.left = new FormAttachment(lblTopWRDiameter, 0,SWT.LEFT);
		lblWRMeshAngle.setLayoutData(fd_lblWRMeshAngle);
		lblWRMeshAngle.setText(ulObj.getUILabelValue(UILabel.WR_Mesh_Angle));
		
		textWRMeshAngle = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextWRMeshAngle(textWRMeshAngle);
		CustomText C_textWRMeshAngle = new CustomText(Mediator.TEXT_textWRMeshAngle,med);
		med.setC_textWRMeshAngle(C_textWRMeshAngle);
		C_textWRMeshAngle.setCustomWidget_textWRMeshAngle();
		FormData fd_textWRMeshAngle = new FormData();
		fd_textWRMeshAngle.top = new FormAttachment(lblWRMeshAngle,-2,SWT.TOP);
		fd_textWRMeshAngle.left = new FormAttachment(textTopWRDiameter, 0,SWT.LEFT);
		fd_textWRMeshAngle.right = new FormAttachment(100, -10);
		textWRMeshAngle.setLayoutData(fd_textWRMeshAngle);
		textWRMeshAngle.setEnabled(false);
		
		Label lblWRChamferX = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblWRChamferX = new FormData();
		fd_lblWRChamferX.top = new FormAttachment(lblWRMeshAngle, 10);
		fd_lblWRChamferX.left = new FormAttachment(lblTopWRDiameter, 0, SWT.LEFT);
		lblWRChamferX.setLayoutData(fd_lblWRChamferX);
		lblWRChamferX.setText(ulObj.getUILabelValue(UILabel.WR_Chamfer_X));
		
		textWRChamferX = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextWRChamferX(textWRChamferX);
		CustomText c_textWRChamferX = new CustomText(Mediator.Text_textWRChamferX,med);
		med.setC_textWRChamferX(c_textWRChamferX);
		c_textWRChamferX.setCustomWidget_textWRChamferX();
		FormData fd_textWRChamferX = new FormData();
		fd_textWRChamferX.top = new FormAttachment(lblWRChamferX, -2, SWT.TOP);
		fd_textWRChamferX.left = new FormAttachment(textTopWRDiameter, 0, SWT.LEFT);
		fd_textWRChamferX.right = new FormAttachment(100,-10);
		textWRChamferX.setLayoutData(fd_textWRChamferX);
		
		Label lblWRChamferY = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblWRChamferY = new FormData();
		fd_lblWRChamferY.top = new FormAttachment(lblWRChamferX, 10);
		fd_lblWRChamferY.left = new FormAttachment(lblTopWRDiameter, 0, SWT.LEFT);
		lblWRChamferY.setLayoutData(fd_lblWRChamferY);
		lblWRChamferY.setText(ulObj.getUILabelValue(UILabel.WR_Chamfer_Y));
		
		textWRChamferY = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextWRChamferY(textWRChamferY);
		CustomText c_textWRChamferY = new CustomText(Mediator.Text_textWRChamferY,med);
		med.setC_textWRChamferY(c_textWRChamferY);
		c_textWRChamferY.setCustomWidget_textWRChamferY();
		FormData fd_textWRChamferY = new FormData();
		fd_textWRChamferY.top = new FormAttachment(lblWRChamferY, -2, SWT.TOP);
		fd_textWRChamferY.left = new FormAttachment(textTopWRDiameter, 0, SWT.LEFT);
		fd_textWRChamferY.right = new FormAttachment(100,-10);
		textWRChamferY.setLayoutData(fd_textWRChamferY);
		
		Label lblWRRound = new Label(grpWorkRollwrParameter, SWT.NONE);
		FormData fd_lblWRRound = new FormData();
		fd_lblWRRound.top = new FormAttachment(lblWRChamferY, 10);
		fd_lblWRRound.left = new FormAttachment(lblTopWRDiameter, 0, SWT.LEFT);
		lblWRRound.setLayoutData(fd_lblWRRound);
		lblWRRound.setText(ulObj.getUILabelValue(UILabel.WR_Round));
		
		textWRRound = new Text(grpWorkRollwrParameter, SWT.BORDER);
		med.setTextWRRound(textWRRound);
		CustomText c_textWRRound = new CustomText(Mediator.Text_textWRRound,med);
		med.setC_textWRRound(c_textWRRound);
		c_textWRRound.setCustomWidget_textWRRound();
		FormData fd_textWRRound = new FormData();
		fd_textWRRound.top = new FormAttachment(lblWRRound, -2, SWT.TOP);
		fd_textWRRound.left = new FormAttachment(textTopWRDiameter, 0, SWT.LEFT);
		fd_textWRRound.right = new FormAttachment(100,-10);
		textWRRound.setLayoutData(fd_textWRRound);
		
		//
		// Group1 End
		//=============
		
		
		//=============
		// Group2 Start
		//
		Group grpBackUpRollburParameter = new Group(compositeDetail, SWT.NONE);
		med.setGrpBackUpRollburParameter(grpBackUpRollburParameter);
		grpBackUpRollburParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpBackUpRollburParameter.setText(ulObj.getUILabelValue(UILabel.Backup_Roll_BUR_Parameters));
		grpBackUpRollburParameter.setLayout(new FormLayout());
		FormData fd_grpBackUpRollburParameter = new FormData();
		fd_grpBackUpRollburParameter.top = new FormAttachment(grpWorkRollwrParameter, 5);
		fd_grpBackUpRollburParameter.left = new FormAttachment(grpWorkRollwrParameter, 0,SWT.LEFT);
		fd_grpBackUpRollburParameter.right = new FormAttachment(grpWorkRollwrParameter, 0,SWT.RIGHT);
		fd_grpBackUpRollburParameter.bottom = new FormAttachment(grpWorkRollwrParameter, 190, SWT.BOTTOM);
		grpBackUpRollburParameter.setLayoutData(fd_grpBackUpRollburParameter);
		
		Label lblTopBURDiameter = new Label(grpBackUpRollburParameter, SWT.NONE);
		FormData fd_lblTopBURDiameter = new FormData();
		fd_lblTopBURDiameter.top = new FormAttachment(0, 10);
		fd_lblTopBURDiameter.left = new FormAttachment(0, 10);
		lblTopBURDiameter.setLayoutData(fd_lblTopBURDiameter);
		lblTopBURDiameter.setText(ulObj.getUILabelValue(UILabel.Top_BUR_Diameter));
		
		textTopBURDiameter = new Text(grpBackUpRollburParameter, SWT.BORDER);
		med.setTextTopBURDiameter(textTopBURDiameter);
		CustomText C_textTopBURDiameter = new CustomText(Mediator.TEXT_textTopBURDiameter,med);
		med.setC_textTopBURDiameter(C_textTopBURDiameter);
		C_textTopBURDiameter.setCustomWidget_textTopBURDiameter();
		FormData fd_textTopBURDiameter = new FormData();
		fd_textTopBURDiameter.top = new FormAttachment(lblTopBURDiameter,-2,SWT.TOP);
		fd_textTopBURDiameter.left = new FormAttachment(lblTopBURDiameter,30,SWT.RIGHT);
		fd_textTopBURDiameter.right = new FormAttachment(100, -10);
		textTopBURDiameter.setLayoutData(fd_textTopBURDiameter);
		
		Label lblBottomBURDiameter = new Label(grpBackUpRollburParameter, SWT.NONE);
		FormData fd_lblBottomBURDiameter = new FormData();
		fd_lblBottomBURDiameter.top = new FormAttachment(lblTopBURDiameter, 10);
		fd_lblBottomBURDiameter.left = new FormAttachment(lblTopBURDiameter, 0,SWT.LEFT);
		lblBottomBURDiameter.setLayoutData(fd_lblBottomBURDiameter);
		lblBottomBURDiameter.setText(ulObj.getUILabelValue(UILabel.Bottom_BUR_Diameter));
		
		textBottomBURDiameter = new Text(grpBackUpRollburParameter, SWT.BORDER);
		med.setTextBottomBURDiameter(textBottomBURDiameter);
		CustomText C_textBottomBURDiameter = new CustomText(Mediator.TEXT_textBottomBURDiameter,med);
		med.setC_textBottomBURDiameter(C_textBottomBURDiameter);
		C_textBottomBURDiameter.setCustomWidget_textBottomBURDiameter();
		FormData fd_textBottomBURDiameter = new FormData();
		fd_textBottomBURDiameter.top = new FormAttachment(lblBottomBURDiameter,-2,SWT.TOP);
		fd_textBottomBURDiameter.left = new FormAttachment(textTopBURDiameter, 0,SWT.LEFT);
		fd_textBottomBURDiameter.right = new FormAttachment(100, -10);
		textBottomBURDiameter.setLayoutData(fd_textBottomBURDiameter);
		
		Label lblBURLength = new Label(grpBackUpRollburParameter, SWT.NONE);
		FormData fd_lblBURLength = new FormData();
		fd_lblBURLength.top = new FormAttachment(lblBottomBURDiameter, 10);
		fd_lblBURLength.left = new FormAttachment(lblTopBURDiameter, 0,SWT.LEFT);
		lblBURLength.setLayoutData(fd_lblBURLength);
		lblBURLength.setText(ulObj.getUILabelValue(UILabel.BUR_Length));
		
		textBURLength = new Text(grpBackUpRollburParameter, SWT.BORDER);
		med.setTextBURLength(textBURLength);
		CustomText C_textBURLength = new CustomText(Mediator.TEXT_textBURLength,med);
		med.setC_textBURLength(C_textBURLength);
		C_textBURLength.setCustomWidget_textBURLength();
		FormData fd_textBURLength = new FormData();
		fd_textBURLength.top = new FormAttachment(lblBURLength,-2,SWT.TOP);
		fd_textBURLength.left = new FormAttachment(textTopBURDiameter, 0,SWT.LEFT);
		fd_textBURLength.right = new FormAttachment(100, -10);
		textBURLength.setLayoutData(fd_textBURLength);
		textBURLength.setEnabled(false);
		
		Label lblBURMeshAngle = new Label(grpBackUpRollburParameter, SWT.NONE);
		FormData fd_lblBURMeshAngle = new FormData();
		fd_lblBURMeshAngle.top = new FormAttachment(lblBURLength, 10);
		fd_lblBURMeshAngle.left = new FormAttachment(lblTopBURDiameter, 0, SWT.LEFT);
		lblBURMeshAngle.setLayoutData(fd_lblBURMeshAngle);
		lblBURMeshAngle.setText(ulObj.getUILabelValue(UILabel.BUR_Mesh_Angle));
		
		textBURMeshAngle = new Text(grpBackUpRollburParameter, SWT.BORDER);
		med.setTextBURMeshAngle(textBURMeshAngle);
		CustomText C_textBURMeshAngle = new CustomText(Mediator.TEXT_textBURMeshAngle,med);
		med.setC_textBURMeshAngle(C_textBURMeshAngle);
		C_textBURMeshAngle.setCustomWidget_textBURMeshAngle();
		FormData fd_textBURMeshAngle = new FormData();
		fd_textBURMeshAngle.top = new FormAttachment(lblBURMeshAngle,-2,SWT.TOP);
		fd_textBURMeshAngle.left = new FormAttachment(textTopBURDiameter, 0,SWT.LEFT);
		fd_textBURMeshAngle.right = new FormAttachment(100, -10);
		textBURMeshAngle.setLayoutData(fd_textBURMeshAngle);
		textBURMeshAngle.setEnabled(false);
		
		Label lblBURChamferX = new Label(grpBackUpRollburParameter, SWT.NONE);
		FormData fd_lblBURChamferX = new FormData();
		fd_lblBURChamferX.top = new FormAttachment(lblBURMeshAngle, 10);
		fd_lblBURChamferX.left = new FormAttachment(lblTopBURDiameter, 0, SWT.LEFT);
		lblBURChamferX.setLayoutData(fd_lblBURChamferX);
		lblBURChamferX.setText(ulObj.getUILabelValue(UILabel.BUR_Chamfer_X));
		
		textBURChamferX = new Text(grpBackUpRollburParameter, SWT.BORDER);
		med.setTextBURChamferX(textBURChamferX);
		CustomText c_textBURChamferX = new CustomText(Mediator.TEXT_textBURChamferX,med);
		med.setC_textBURChamferX(c_textBURChamferX);
		c_textBURChamferX.setCustomWidget_textBURChamferX();
		FormData fd_textBURChamferX = new FormData();
		fd_textBURChamferX.top = new FormAttachment(lblBURChamferX,-2,SWT.TOP);
		fd_textBURChamferX.left = new FormAttachment(textTopBURDiameter, 0,SWT.LEFT);
		fd_textBURChamferX.right = new FormAttachment(100, -10);
		textBURChamferX.setLayoutData(fd_textBURChamferX);
		
		Label lblBURChamferY = new Label(grpBackUpRollburParameter, SWT.NONE);
		FormData fd_lblBURChamferY = new FormData();
		fd_lblBURChamferY.top = new FormAttachment(lblBURChamferX, 10);
		fd_lblBURChamferY.left = new FormAttachment(lblTopBURDiameter, 0, SWT.LEFT);
		lblBURChamferY.setLayoutData(fd_lblBURChamferY);
		lblBURChamferY.setText(ulObj.getUILabelValue(UILabel.BUR_Chamfer_Y));
		
		textBURChamferY = new Text(grpBackUpRollburParameter, SWT.BORDER);
		med.setTextBURChamferY(textBURChamferY);
		CustomText c_textBURChamferY = new CustomText(Mediator.TEXT_textBURChamferY,med);
		med.setC_textBURChamferY(c_textBURChamferY);
		c_textBURChamferY.setCustomWidget_textBURChamfery();
		FormData fd_textBURChamferY = new FormData();
		fd_textBURChamferY.top = new FormAttachment(lblBURChamferY,-2,SWT.TOP);
		fd_textBURChamferY.left = new FormAttachment(textTopBURDiameter, 0,SWT.LEFT);
		fd_textBURChamferY.right = new FormAttachment(100, -10);
		textBURChamferY.setLayoutData(fd_textBURChamferY);
		//
		// Group2 End
		//=============
		
		//=============		
		// Group3 Start
		// 
		
		Group grpPlateParameter = new Group(compositeDetail, SWT.NONE);
		med.setGrpPlateParameter(grpPlateParameter);
		grpPlateParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpPlateParameter.setText(ulObj.getUILabelValue(UILabel.Plate_Parameters));
		grpPlateParameter.setLayout(new FormLayout());
		FormData fd_grpPlateParameter = new FormData();
		fd_grpPlateParameter.top = new FormAttachment(grpBackUpRollburParameter, 5);
		fd_grpPlateParameter.left = new FormAttachment(grpWorkRollwrParameter, 0,SWT.LEFT);
		fd_grpPlateParameter.right = new FormAttachment(grpWorkRollwrParameter, 0,SWT.RIGHT);
		fd_grpPlateParameter.bottom = new FormAttachment(grpBackUpRollburParameter, 265, SWT.BOTTOM);
		grpPlateParameter.setLayoutData(fd_grpPlateParameter);
		
		Label lblThickness = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblThickness = new FormData();
		fd_lblThickness.top = new FormAttachment(0, 10);
		fd_lblThickness.left = new FormAttachment(0, 10);
		lblThickness.setLayoutData(fd_lblThickness);
		lblThickness.setText(ulObj.getUILabelValue(UILabel.Thickness));
		
		textThickness = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextThickness(textThickness);
		CustomText C_textThickness = new CustomText(Mediator.TEXT_textThickness,med);
		med.setC_textThickness(C_textThickness);
		C_textThickness.setCustomWidget_textThickness();
		FormData fd_textThickness = new FormData();
		fd_textThickness.top = new FormAttachment(lblThickness,-2,SWT.TOP);
		fd_textThickness.left = new FormAttachment(lblThickness,80,SWT.RIGHT);
		fd_textThickness.right = new FormAttachment(100, -10);
		textThickness.setLayoutData(fd_textThickness);
		
		Label lblWidth = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblWidth = new FormData();
		fd_lblWidth.top = new FormAttachment(lblThickness, 10);
		fd_lblWidth.left = new FormAttachment(lblThickness, 0,SWT.LEFT);
		lblWidth.setLayoutData(fd_lblWidth);
		lblWidth.setText(ulObj.getUILabelValue(UILabel.Width));
		
		textWidth = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextWidth(textWidth);
		CustomText C_textWidth = new CustomText(Mediator.TEXT_textWidth,med);
		med.setC_textWidth(C_textWidth);
		C_textWidth.setCustomWidget_textWidth();
		FormData fd_textWidth = new FormData();
		fd_textWidth.top = new FormAttachment(lblWidth,-2,SWT.TOP);
		fd_textWidth.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textWidth.right = new FormAttachment(100, -10);
		textWidth.setLayoutData(fd_textWidth);
		
		Label lblLength = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblLength = new FormData();
		fd_lblLength.top = new FormAttachment(lblWidth, 10);
		fd_lblLength.left = new FormAttachment(lblThickness, 0,SWT.LEFT);
		lblLength.setLayoutData(fd_lblLength);
		lblLength.setText(ulObj.getUILabelValue(UILabel.Length));
		
		textLength = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextLength(textLength);
		CustomText C_textLength = new CustomText(Mediator.TEXT_textLength,med);
		med.setC_textLength(C_textLength);
		C_textLength.setCustomWidget_textLength();
		FormData fd_textLength = new FormData();
		fd_textLength.top = new FormAttachment(lblLength,-2,SWT.TOP);
		fd_textLength.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textLength.right = new FormAttachment(100, -10);
		textLength.setLayoutData(fd_textLength);
		
		Label lblEntryTemperature = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblEntryTemperature = new FormData();
		fd_lblEntryTemperature.top = new FormAttachment(lblLength, 10);
		fd_lblEntryTemperature.left = new FormAttachment(lblThickness, 0, SWT.LEFT);
		lblEntryTemperature.setLayoutData(fd_lblEntryTemperature);
		lblEntryTemperature.setText(ulObj.getUILabelValue(UILabel.Entry_Temperature));
		
		textEntryTemperature = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextEntryTemperature(textEntryTemperature);
		CustomText C_textEntryTemperature = new CustomText(Mediator.TEXT_textEntryTemperature,med);
		med.setC_textEntryTemperature(C_textEntryTemperature);
		C_textEntryTemperature.setCustomWidget_textEntryTemperature();
		FormData fd_textEntryTemperature = new FormData();
		fd_textEntryTemperature.top = new FormAttachment(lblEntryTemperature,-2,SWT.TOP);
		fd_textEntryTemperature.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textEntryTemperature.right = new FormAttachment(100, -10);
		textEntryTemperature.setLayoutData(fd_textEntryTemperature);
		
		Label lblExitTemperature = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblExitTemperature = new FormData();
		fd_lblExitTemperature.top = new FormAttachment(lblEntryTemperature, 10);
		fd_lblExitTemperature.left = new FormAttachment(lblThickness, 0,SWT.LEFT);
		lblExitTemperature.setLayoutData(fd_lblExitTemperature);
		lblExitTemperature.setText(ulObj.getUILabelValue(UILabel.Exit_Temperature));
		
		textExitTemperature = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextExitTemperature(textExitTemperature);
		CustomText C_textExitTemperature = new CustomText(Mediator.TEXT_textExitTemperature,med);
		med.setC_textExitTemperature(C_textExitTemperature);
		C_textExitTemperature.setCustomWidget_textExitTemperature();
		FormData fd_textExitTemperature = new FormData();
		fd_textExitTemperature.top = new FormAttachment(lblExitTemperature,-2,SWT.TOP);
		fd_textExitTemperature.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textExitTemperature.right = new FormAttachment(100, -10);
		textExitTemperature.setLayoutData(fd_textExitTemperature);
		
		Label lblInitialPosition = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblInitialPosition = new FormData();
		fd_lblInitialPosition.top = new FormAttachment(lblExitTemperature, 10);
		fd_lblInitialPosition.left = new FormAttachment(lblThickness, 0,SWT.LEFT);
		lblInitialPosition.setLayoutData(fd_lblInitialPosition);
		lblInitialPosition.setText(ulObj.getUILabelValue(UILabel.Initial_Position));
		
		textInitialPosition = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextInitialPosition(textInitialPosition);
		CustomText C_textInitialPosition = new CustomText(Mediator.TEXT_textInitialPosition,med);
		med.setC_textInitialPosition(C_textInitialPosition);
		C_textInitialPosition.setCustomWidget_textInitialPosition();
		FormData fd_textInitialPosition = new FormData();
		fd_textInitialPosition.top = new FormAttachment(lblInitialPosition,-2,SWT.TOP);
		fd_textInitialPosition.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textInitialPosition.right = new FormAttachment(100, -10);
		textInitialPosition.setLayoutData(fd_textInitialPosition);
		
		Label lblMeshLength = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblMeshLength = new FormData();
		fd_lblMeshLength.top = new FormAttachment(lblInitialPosition, 10);
		fd_lblMeshLength.left = new FormAttachment(lblThickness, 0,SWT.LEFT);
		lblMeshLength.setLayoutData(fd_lblMeshLength);
		lblMeshLength.setText(ulObj.getUILabelValue(UILabel.Mesh_Length));
		
		textMeshLength = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextMeshLength(textMeshLength);
		CustomText C_textMeshLength = new CustomText(Mediator.TEXT_textMeshLength,med);
		med.setC_textMeshLength(C_textMeshLength);
		C_textMeshLength.setCustomWidget_textMeshLength();
		FormData fd_textMeshLength = new FormData();
		fd_textMeshLength.top = new FormAttachment(lblMeshLength,-2,SWT.TOP);
		fd_textMeshLength.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textMeshLength.right = new FormAttachment(100, -10);
		textMeshLength.setLayoutData(fd_textMeshLength);
		
		Label lblThicknessMeshDivisions = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblThicknessMeshDivisions = new FormData();
		fd_lblThicknessMeshDivisions.top = new FormAttachment(lblMeshLength, 10);
		fd_lblThicknessMeshDivisions.left = new FormAttachment(lblThickness, 0,SWT.LEFT);
		lblThicknessMeshDivisions.setLayoutData(fd_lblThicknessMeshDivisions);
		lblThicknessMeshDivisions.setText(ulObj.getUILabelValue(UILabel.Thickness_Mesh_Divisions));
		
		textThicknessMeshDivisions = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextThicknessMeshDivisions(textThicknessMeshDivisions);
		CustomText C_textThicknessMeshDivisions = new CustomText(Mediator.TEXT_textThicknessMeshDivisions,med);
		med.setC_textThicknessMeshDivisions(C_textThicknessMeshDivisions);
		C_textThicknessMeshDivisions.setCustomWidget_textThicknessMeshDivisions();
		FormData fd_textThicknessMeshDivisions = new FormData();
		fd_textThicknessMeshDivisions.top = new FormAttachment(lblThicknessMeshDivisions,-2,SWT.TOP);
		fd_textThicknessMeshDivisions.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textThicknessMeshDivisions.right = new FormAttachment(100, -10);
		textThicknessMeshDivisions.setLayoutData(fd_textThicknessMeshDivisions);
		
		Label lblPlateCrown = new Label(grpPlateParameter, SWT.NONE);
		FormData fd_lblPlateCrown = new FormData();
		fd_lblPlateCrown.top = new FormAttachment(lblThicknessMeshDivisions, 10);
		fd_lblPlateCrown.left = new FormAttachment(lblThickness, 0,SWT.LEFT);
		lblPlateCrown.setLayoutData(fd_lblPlateCrown);
		lblPlateCrown.setText(ulObj.getUILabelValue(UILabel.Plate_Crown));
		
		textPlateCrown = new Text(grpPlateParameter, SWT.BORDER);
		med.setTextPlateCrown(textPlateCrown);
		CustomText c_textPlateCrown = new CustomText(Mediator.TEXT_textPlateCrown,med);
		med.setC_textPlateCrown(c_textPlateCrown);
		c_textPlateCrown.setCustomWidget_textPlateCrown();
		FormData fd_textPlateCrown = new FormData();
		fd_textPlateCrown.top = new FormAttachment(lblPlateCrown,-2,SWT.TOP);
		fd_textPlateCrown.left = new FormAttachment(textThickness, 0,SWT.LEFT);
		fd_textPlateCrown.right = new FormAttachment(100, -10);
		textPlateCrown.setLayoutData(fd_textPlateCrown);
		
		
		//
		// Group3 End
		//=============
		
		//=============
		// Group4 End
		// 
		Group grpProcessInformation = new Group(compositeDetail, SWT.NONE);
		med.setGrpProcessInformation(grpProcessInformation);
		grpProcessInformation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpProcessInformation.setText(ulObj.getUILabelValue(UILabel.Process_Information));
		grpProcessInformation.setLayout(new FormLayout());
		FormData fd_grpProcessInformation = new FormData();
		fd_grpProcessInformation.top = new FormAttachment(grpWorkRollwrParameter, 0, SWT.TOP);
		fd_grpProcessInformation.left = new FormAttachment(grpWorkRollwrParameter, 10, SWT.RIGHT);
		fd_grpProcessInformation.right = new FormAttachment(grpWorkRollwrParameter, 410,SWT.RIGHT);
		fd_grpProcessInformation.bottom = new FormAttachment(groupSTAND, 180, SWT.BOTTOM);
		grpProcessInformation.setLayoutData(fd_grpProcessInformation);
		
		Label lblVelocity = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblVelocity = new FormData();
		fd_lblVelocity.top = new FormAttachment(0, 200);
		fd_lblVelocity.left = new FormAttachment(0, 10);
		lblVelocity.setLayoutData(fd_lblVelocity);
		lblVelocity.setText(ulObj.getUILabelValue(UILabel.Velocity));
		lblVelocity.setVisible(false);
		
		textVelocity = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextVelocity(textVelocity);
		CustomText C_textVelocity = new CustomText(Mediator.TEXT_textVelocity,med);
		med.setC_textVelocity(C_textVelocity);
		C_textVelocity.setCustomWidget_textVelocity();
		FormData fd_textVelocity = new FormData();
		fd_textVelocity.top = new FormAttachment(lblVelocity,-2,SWT.TOP);
		fd_textVelocity.left = new FormAttachment(lblVelocity,130,SWT.RIGHT);
		fd_textVelocity.right = new FormAttachment(100, -10);
		textVelocity.setLayoutData(fd_textVelocity);
		textVelocity.setVisible(false);
		
		Label lblRollGap = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblRollGap = new FormData();
		fd_lblRollGap.top = new FormAttachment(0, 10);
		fd_lblRollGap.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblRollGap.setLayoutData(fd_lblRollGap);
		lblRollGap.setText(ulObj.getUILabelValue(UILabel.Roll_Gap));

		textRollGap = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextRollGap(textRollGap);
		CustomText C_textRollGap = new CustomText(Mediator.TEXT_textRollGap,med);
		med.setC_textRollGap(C_textRollGap);
		C_textRollGap.setCustomWidget_textRollGap();
		FormData fd_textRollGap = new FormData();
		fd_textRollGap.top = new FormAttachment(lblRollGap,-2,SWT.TOP);
		fd_textRollGap.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textRollGap.right = new FormAttachment(100, -10);
		textRollGap.setLayoutData(fd_textRollGap);
		
		Label lblPassLine = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblPassLine = new FormData();
		fd_lblPassLine.top = new FormAttachment(lblVelocity, 10);
		fd_lblPassLine.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblPassLine.setLayoutData(fd_lblPassLine);
		lblPassLine.setText(ulObj.getUILabelValue(UILabel.Pass_Line));
		lblPassLine.setVisible(false);
		
		textPassLine = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextPassLine(textPassLine);
		CustomText C_textPassLine = new CustomText(Mediator.TEXT_textPassLine,med);
		med.setC_textPassLine(C_textPassLine);
		C_textPassLine.setCustomWidget_textPassLine();
		FormData fd_textPassLine = new FormData();
		fd_textPassLine.top = new FormAttachment(lblPassLine,-2,SWT.TOP);
		fd_textPassLine.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textPassLine.right = new FormAttachment(100, -10);
		textPassLine.setLayoutData(fd_textPassLine);
		textPassLine.setVisible(false);

		Label lblPairCrossAngle = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblPairCrossAngle = new FormData();
		fd_lblPairCrossAngle.top = new FormAttachment(lblPassLine, 10);
		fd_lblPairCrossAngle.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblPairCrossAngle.setLayoutData(fd_lblPairCrossAngle);
		lblPairCrossAngle.setText(ulObj.getUILabelValue(UILabel.Pair_Cross_Angle));
		lblPairCrossAngle.setVisible(false);
		
		textPairCrossAngle = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextPairCrossAngle(textPairCrossAngle);
		CustomText C_textPairCrossAngle = new CustomText(Mediator.TEXT_textPairCrossAngle,med);
		med.setC_textPairCrossAngle(C_textPairCrossAngle);
		C_textPairCrossAngle.setCustomWidget_textPairCrossAngle();
		FormData fd_textPairCrossAngle = new FormData();
		fd_textPairCrossAngle.top = new FormAttachment(lblPairCrossAngle,-2,SWT.TOP);
		fd_textPairCrossAngle.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textPairCrossAngle.right = new FormAttachment(100, -10);
		textPairCrossAngle.setLayoutData(fd_textPairCrossAngle);
		textPairCrossAngle.setVisible(false);
		
		Label lblBenderForce = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblBenderForce = new FormData();
		fd_lblBenderForce.top = new FormAttachment(lblRollGap, 10);
		fd_lblBenderForce.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblBenderForce.setLayoutData(fd_lblBenderForce);
		lblBenderForce.setText(ulObj.getUILabelValue(UILabel.Bender_Force));

		textBenderForce = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextBenderForce(textBenderForce);
		CustomText C_textBenderForce = new CustomText(Mediator.TEXT_textBenderForce,med);
		med.setC_textBenderForce(C_textBenderForce);
		C_textBenderForce.setCustomWidget_textBenderForce();
		FormData fd_textBenderForce = new FormData();
		fd_textBenderForce.top = new FormAttachment(lblBenderForce,-2,SWT.TOP);
		fd_textBenderForce.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textBenderForce.right = new FormAttachment(100, -10);
		textBenderForce.setLayoutData(fd_textBenderForce);
		
		Label lblRollTorque = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblRollTorque = new FormData();
		fd_lblRollTorque.top = new FormAttachment(lblPassLine, 10);
		fd_lblRollTorque.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblRollTorque.setLayoutData(fd_lblRollTorque);
		lblRollTorque.setText(ulObj.getUILabelValue(UILabel.Roll_Torque));
		lblRollTorque.setVisible(false);

		textRollTorque = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextRollTorque(textRollTorque);
		CustomText C_textRollTorque =new CustomText(Mediator.TEXT_textRollTorque,med);
		med.setC_textRollTorque(C_textRollTorque);
		C_textRollTorque.setCustomWidget_textRollTorque();
		FormData fd_textRollTorque = new FormData();
		fd_textRollTorque.top = new FormAttachment(lblRollTorque,-2,SWT.TOP);
		fd_textRollTorque.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textRollTorque.right = new FormAttachment(100, -10);
		textRollTorque.setLayoutData(fd_textRollTorque);
		textRollTorque.setVisible(false);
		
		Label lblTensionStress = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblTensionStress = new FormData();
		fd_lblTensionStress.top = new FormAttachment(lblBenderForce, 10);
		fd_lblTensionStress.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblTensionStress.setLayoutData(fd_lblTensionStress);
		lblTensionStress.setText(ulObj.getUILabelValue(UILabel.Tension_Stress));

		textTensionStress = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextTensionStress(textTensionStress);
		CustomText C_textTensionStress = new CustomText(Mediator.TEXT_textTensionStress,med);
		med.setC_textTensionStress(C_textTensionStress);
		C_textTensionStress.setCustomWidget_textTensionStress();
		FormData fd_textTensionStress = new FormData();
		fd_textTensionStress.top = new FormAttachment(lblTensionStress,-2,SWT.TOP);
		fd_textTensionStress.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textTensionStress.right = new FormAttachment(100, -10);
		textTensionStress.setLayoutData(fd_textTensionStress);
		
		Label lblRollToPlateFrictCoef = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblRollToPlateFrictCoef = new FormData();
		fd_lblRollToPlateFrictCoef.top = new FormAttachment(lblTensionStress, 10);
		fd_lblRollToPlateFrictCoef.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblRollToPlateFrictCoef.setLayoutData(fd_lblRollToPlateFrictCoef);
		lblRollToPlateFrictCoef.setText(ulObj.getUILabelValue(UILabel.Roll_to_Plate_Frict_Coef));

		textRollToPlateFrictCoef = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextRollToPlateFrictCoef(textRollToPlateFrictCoef);
		CustomText C_textRollToPlateFrictCoef = new CustomText(Mediator.TEXT_textRollToPlateFrictCoef,med);
		med.setC_textRollToPlateFrictCoef(C_textRollToPlateFrictCoef);
		C_textRollToPlateFrictCoef.setCustomWidget_textRollToPlateFrictCoef();
		FormData fd_textRollToPlateFrictCoef = new FormData();
		fd_textRollToPlateFrictCoef.top = new FormAttachment(lblRollToPlateFrictCoef,-2,SWT.TOP);
		fd_textRollToPlateFrictCoef.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textRollToPlateFrictCoef.right = new FormAttachment(100, -10);
		textRollToPlateFrictCoef.setLayoutData(fd_textRollToPlateFrictCoef);
		
		Label lblRollToRollFrictCoef = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblRollToRollFrictCoef = new FormData();
		fd_lblRollToRollFrictCoef.top = new FormAttachment(lblRollToPlateFrictCoef, 10);
		fd_lblRollToRollFrictCoef.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblRollToRollFrictCoef.setLayoutData(fd_lblRollToRollFrictCoef);
		lblRollToRollFrictCoef.setText(ulObj.getUILabelValue(UILabel.Roll_to_Roll_Fric_Coef));

		textRollToRollFrictCoef = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextRollToRollFrictCoef(textRollToRollFrictCoef);
		CustomText C_textRollToRollFrictCoef = new CustomText(Mediator.TEXT_textRollToRollFrictCoef,med);
		med.setC_textRollToRollFrictCoef(C_textRollToRollFrictCoef);
		C_textRollToRollFrictCoef.setCustomWidget_textRollToRollFrictCoef();
		FormData fd_textRollToRollFrictCoef = new FormData();
		fd_textRollToRollFrictCoef.top = new FormAttachment(lblRollToRollFrictCoef,-2,SWT.TOP);
		fd_textRollToRollFrictCoef.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textRollToRollFrictCoef.right = new FormAttachment(100, -10);
		textRollToRollFrictCoef.setLayoutData(fd_textRollToRollFrictCoef);
		
		Label lblSpeedDifferentRatioTopRoll = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblSpeedDifferentRatioTopRoll = new FormData();
		fd_lblSpeedDifferentRatioTopRoll.top = new FormAttachment(lblRollToRollFrictCoef, 20);
		fd_lblSpeedDifferentRatioTopRoll.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblSpeedDifferentRatioTopRoll.setLayoutData(fd_lblSpeedDifferentRatioTopRoll);
		lblSpeedDifferentRatioTopRoll.setText(ulObj.getUILabelValue(UILabel.Speed_Different_Ratio_top_roll));
		lblSpeedDifferentRatioTopRoll.setVisible(false);
		
		textSpeedDifferentRatioTopRoll = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextSpeedDifferentRatioTopRoll(textSpeedDifferentRatioTopRoll);		    
		CustomText C_textSpeedDifferentRatioTopRoll = new CustomText(Mediator.TEXT_textSpeedDifferentRatioTopRoll,med);
		med.setC_textSpeedDifferentRatioTopRoll(C_textSpeedDifferentRatioTopRoll);
		C_textSpeedDifferentRatioTopRoll.setCustomWidget_textSpeedDifferentRatioTopRoll();
		FormData fd_textSpeedDifferentRatioTopRoll = new FormData();
		fd_textSpeedDifferentRatioTopRoll.top = new FormAttachment(lblSpeedDifferentRatioTopRoll,-2,SWT.TOP);
		fd_textSpeedDifferentRatioTopRoll.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textSpeedDifferentRatioTopRoll.right = new FormAttachment(100, -10);
		textSpeedDifferentRatioTopRoll.setLayoutData(fd_textSpeedDifferentRatioTopRoll);
		textSpeedDifferentRatioTopRoll.setVisible(false);
		
		Label lblSpeedDifferentRatioBottomRoll = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblSpeedDifferentRatioBottomRoll = new FormData();
		fd_lblSpeedDifferentRatioBottomRoll.top = new FormAttachment(lblSpeedDifferentRatioTopRoll, 10);
		fd_lblSpeedDifferentRatioBottomRoll.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblSpeedDifferentRatioBottomRoll.setLayoutData(fd_lblSpeedDifferentRatioBottomRoll);
		lblSpeedDifferentRatioBottomRoll.setText(ulObj.getUILabelValue(UILabel.Speed_Different_Ratio_bottom_roll));
		lblSpeedDifferentRatioBottomRoll.setVisible(false);
		
		textSpeedDifferentRatioBottomRoll = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextSpeedDifferentRatioBottomRoll(textSpeedDifferentRatioBottomRoll);		    
		CustomText C_textSpeedDifferentRatioBottomRoll = new CustomText(Mediator.TEXT_textSpeedDifferentRatioBottomRoll,med);
		med.setC_textSpeedDifferentRatioBottomRoll(C_textSpeedDifferentRatioBottomRoll);
		C_textSpeedDifferentRatioBottomRoll.setCustomWidget_textSpeedDifferentRatioBottomRoll();
		FormData fd_textSpeedDifferentRatioBottomRoll = new FormData();
		fd_textSpeedDifferentRatioBottomRoll.top = new FormAttachment(lblSpeedDifferentRatioBottomRoll,-2,SWT.TOP);
		fd_textSpeedDifferentRatioBottomRoll.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textSpeedDifferentRatioBottomRoll.right = new FormAttachment(100, -10);
		textSpeedDifferentRatioBottomRoll.setLayoutData(fd_textSpeedDifferentRatioBottomRoll);
		textSpeedDifferentRatioBottomRoll.setVisible(false);

		Label lblTopWRRotVel = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblTopWRRotVel = new FormData();
		fd_lblTopWRRotVel.top = new FormAttachment(lblSpeedDifferentRatioBottomRoll, 10);
		fd_lblTopWRRotVel.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblTopWRRotVel.setLayoutData(fd_lblTopWRRotVel);
		lblTopWRRotVel.setText(ulObj.getUILabelValue(UILabel.Top_WR_Rot_Vel_RPM));
		lblTopWRRotVel.setVisible(false);

		textTopWRRotVel = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextTopWRRotVel(textTopWRRotVel);
		CustomText C_textTopWRRotVel = new CustomText(Mediator.TEXT_textTopWRRotVel, med);
		med.setC_textTopWRRotVel(C_textTopWRRotVel);
		C_textTopWRRotVel.setCustomWidget_textTopWRRotVel();
		FormData fd_textTopWRRotVel = new FormData();
		fd_textTopWRRotVel.top = new FormAttachment(lblTopWRRotVel,-2,SWT.TOP);
		fd_textTopWRRotVel.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textTopWRRotVel.right = new FormAttachment(100, -10);
		textTopWRRotVel.setLayoutData(fd_textTopWRRotVel);
		textTopWRRotVel.setEnabled(false);
		textTopWRRotVel.setVisible(false);
		
		Label lblBottomWRRotVel = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblBottomWRRotVel = new FormData();
		fd_lblBottomWRRotVel.top = new FormAttachment(lblTopWRRotVel, 10);
		fd_lblBottomWRRotVel.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblBottomWRRotVel.setLayoutData(fd_lblBottomWRRotVel);
		lblBottomWRRotVel.setText(ulObj.getUILabelValue(UILabel.Bottom_WR_Rot_Vel_RPM));
		lblBottomWRRotVel.setVisible(false);

		textBottomWRRotVel = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextBottomWRRotVel(textBottomWRRotVel);
		CustomText C_textBottomWRRotVel = new CustomText(Mediator.TEXT_textBottomWRRotVel, med);		 															
		med.setC_textBottomWRRotVel(C_textBottomWRRotVel);
		C_textBottomWRRotVel.setCustomWidget_textBottomWRRotVel();
		FormData fd_textBottomWRRotVel = new FormData();
		fd_textBottomWRRotVel.top = new FormAttachment(lblBottomWRRotVel,-2,SWT.TOP);
		fd_textBottomWRRotVel.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textBottomWRRotVel.right = new FormAttachment(100, -10);
		textBottomWRRotVel.setLayoutData(fd_textBottomWRRotVel);
		textBottomWRRotVel.setEnabled(false);
		textBottomWRRotVel.setVisible(false);
		
		Label lblTopBURRotVel = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblTopBURRotVel = new FormData();
		fd_lblTopBURRotVel.top = new FormAttachment(lblBottomWRRotVel, 10);
		fd_lblTopBURRotVel.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblTopBURRotVel.setLayoutData(fd_lblTopBURRotVel);
		lblTopBURRotVel.setText(ulObj.getUILabelValue(UILabel.Top_BUR_Rot_Vel_RPM));
		lblTopBURRotVel.setVisible(false);

		textTopBURRotVel = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextTopBURRotVel(textTopBURRotVel);
		CustomText C_textTopBURRotVel = new CustomText(Mediator.TEXT_textTopBURRotVel, med);		 															
		med.setC_textTopBURRotVel(C_textTopBURRotVel);
		C_textTopBURRotVel.setCustomWidget_textTopBURRotVel();
		FormData fd_textTopBURRotVel = new FormData();
		fd_textTopBURRotVel.top = new FormAttachment(lblTopBURRotVel,-2,SWT.TOP);
		fd_textTopBURRotVel.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textTopBURRotVel.right = new FormAttachment(100, -10);
		textTopBURRotVel.setLayoutData(fd_textTopBURRotVel);
		textTopBURRotVel.setEnabled(false);
		textTopBURRotVel.setVisible(false);
		
		Label lblBottomBURRotVel = new Label(grpProcessInformation, SWT.NONE);
		FormData fd_lblBottomBURRotVel = new FormData();
		fd_lblBottomBURRotVel.top = new FormAttachment(lblTopBURRotVel, 10);
		fd_lblBottomBURRotVel.left = new FormAttachment(lblVelocity, 0,SWT.LEFT);
		lblBottomBURRotVel.setLayoutData(fd_lblBottomBURRotVel);
		lblBottomBURRotVel.setText(ulObj.getUILabelValue(UILabel.Bottom_BUR_Rot_Vel_RPM));
		lblBottomBURRotVel.setVisible(false);

		textBottomBURRotVel = new Text(grpProcessInformation, SWT.BORDER);
		med.setTextBottomBURRotVel(textBottomBURRotVel);
		CustomText C_textBottomBURRotVel = new CustomText(Mediator.TEXT_textBottomBURRotVel, med);		 															
		med.setC_textBottomBURRotVel(C_textBottomBURRotVel);
		C_textBottomBURRotVel.setCustomWidget_textBottomBURRotVel();
		FormData fd_textBottomBURRotVel = new FormData();
		fd_textBottomBURRotVel.top = new FormAttachment(lblBottomBURRotVel,-2,SWT.TOP);
		fd_textBottomBURRotVel.left = new FormAttachment(textVelocity, 0,SWT.LEFT);
		fd_textBottomBURRotVel.right = new FormAttachment(100, -10);
		textBottomBURRotVel.setLayoutData(fd_textBottomBURRotVel);
		textBottomBURRotVel.setEnabled(false);
		textBottomBURRotVel.setVisible(false);
		
		//
		// Group4 End
		//=============
		
		//=============
		// Group Roll Material Parameter Start
		//
		Group grpRollMaterialParameter= new Group(compositeDetail, SWT.NONE);
		med.setGrpRollMaterialParameter(grpRollMaterialParameter);
		grpRollMaterialParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpRollMaterialParameter.setText(ulObj.getUILabelValue(UILabel.Roll_Material_Parameter));
		grpRollMaterialParameter.setLayout(new FormLayout());
		FormData fd_grpRollMaterialParameter = new FormData();
		fd_grpRollMaterialParameter.top = new FormAttachment(grpProcessInformation, 5);
		fd_grpRollMaterialParameter.left = new FormAttachment(grpProcessInformation, 0, SWT.LEFT);
		fd_grpRollMaterialParameter.right = new FormAttachment(grpProcessInformation, 0, SWT.RIGHT);
		fd_grpRollMaterialParameter.bottom = new FormAttachment(grpProcessInformation, 100, SWT.BOTTOM);
		grpRollMaterialParameter.setLayoutData(fd_grpRollMaterialParameter);
		
		Label lblRollYoungsModulus = new Label(grpRollMaterialParameter, SWT.NONE);
		FormData fd_lblRollYoungsModulus = new FormData();
		fd_lblRollYoungsModulus.top = new FormAttachment(0, 10);
		fd_lblRollYoungsModulus.left = new FormAttachment(0, 10);
		lblRollYoungsModulus.setLayoutData(fd_lblRollYoungsModulus);
		lblRollYoungsModulus.setText(ulObj.getUILabelValue(UILabel.Roll_Youngs_Modulus));
				
		textRollYoungsModulus = new Text(grpRollMaterialParameter, SWT.BORDER);
		med.setTextRollYoungsModulus(textRollYoungsModulus);
		CustomText c_textRollYoungsModulus = new CustomText(Mediator.TEXT_textRollYoungsModulus,med);
		med.setC_textRollYoungsModulus(c_textRollYoungsModulus);
		c_textRollYoungsModulus.setCustomWidget_textRollYoungsModulus();
		FormData fd_textRollYoungsModulus = new FormData();
		fd_textRollYoungsModulus.top = new FormAttachment(lblRollYoungsModulus,-2,SWT.TOP);
		fd_textRollYoungsModulus.left = new FormAttachment(lblRollYoungsModulus, 100 ,SWT.RIGHT);
		fd_textRollYoungsModulus.right = new FormAttachment(100, -10);
		textRollYoungsModulus.setLayoutData(fd_textRollYoungsModulus);
		
		Label lblRollPoissonsRatio = new Label(grpRollMaterialParameter, SWT.NONE);
		FormData fd_lblRollPoissonsRatio = new FormData();
		fd_lblRollPoissonsRatio.top = new FormAttachment(lblRollYoungsModulus, 10);
		fd_lblRollPoissonsRatio.left = new FormAttachment(lblRollYoungsModulus, 0, SWT.LEFT);
		lblRollPoissonsRatio.setLayoutData(fd_lblRollPoissonsRatio);
		lblRollPoissonsRatio.setText(ulObj.getUILabelValue(UILabel.Roll_Poissons_Ratio));
				
		textRollPoissonsRatio = new Text(grpRollMaterialParameter, SWT.BORDER);
		med.setTextRollPoissonsRatio(textRollPoissonsRatio);
		CustomText c_textRollPoissonsRatio = new CustomText(Mediator.TEXT_textRollPoissonsRatio,med);
		med.setC_textRollPoissonsRatio(c_textRollPoissonsRatio);
		c_textRollPoissonsRatio.setCustomWidget_textRollPossionRatio();
		FormData fd_textRollPoissonsRatio = new FormData();
		fd_textRollPoissonsRatio.top = new FormAttachment(lblRollPoissonsRatio,-2,SWT.TOP);
		fd_textRollPoissonsRatio.left = new FormAttachment(textRollYoungsModulus, 0,SWT.LEFT);
		fd_textRollPoissonsRatio.right = new FormAttachment(100, -10);
		textRollPoissonsRatio.setLayoutData(fd_textRollPoissonsRatio);
		//
		//Group Roll Material ParameterEnd
		//=============

		//=============
		// Group5 Start
		//
		Group grpMaterialParameter = new Group(compositeDetail, SWT.NONE);
		med.setGrpMaterialParameter(grpMaterialParameter);
		grpMaterialParameter.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpMaterialParameter.setText(ulObj.getUILabelValue(UILabel.Plate_Material_parameter));
		grpMaterialParameter.setLayout(new FormLayout());
		FormData fd_grpMaterialParameter = new FormData();
		fd_grpMaterialParameter.top = new FormAttachment(grpWorkRollwrParameter, 0, SWT.TOP);
		fd_grpMaterialParameter.left = new FormAttachment(grpProcessInformation, 10, SWT.RIGHT);
		fd_grpMaterialParameter.right = new FormAttachment(grpProcessInformation, 410,SWT.RIGHT);
		fd_grpMaterialParameter.bottom = new FormAttachment(groupSTAND, 460, SWT.BOTTOM);
		grpMaterialParameter.setLayoutData(fd_grpMaterialParameter);
		//--------------------------------------------------------------------------------------------------------
		Label lblYoungsModulus = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblYoungsModulus = new FormData();
		fd_lblYoungsModulus.top = new FormAttachment(0, 10);
		fd_lblYoungsModulus.left = new FormAttachment(0, 10);
		lblYoungsModulus.setLayoutData(fd_lblYoungsModulus);
		lblYoungsModulus.setText(ulObj.getUILabelValue(UILabel.Youngs_Modulus));
		
		Composite compositeYoungsModulus = new Composite(grpMaterialParameter, SWT.NONE);
		compositeYoungsModulus.setLayout(new FormLayout());
		FormData fd_compositeYoungsModulus = new FormData();
		fd_compositeYoungsModulus.top = new FormAttachment(lblYoungsModulus, 5);
		fd_compositeYoungsModulus.left = new FormAttachment(lblYoungsModulus, 10, SWT.LEFT);
		fd_compositeYoungsModulus.right = new FormAttachment(lblYoungsModulus,100,SWT.RIGHT);
		fd_compositeYoungsModulus.bottom = new FormAttachment(lblYoungsModulus,20,SWT.BOTTOM);
		compositeYoungsModulus.setLayoutData(fd_compositeYoungsModulus);
		
		Button btnConstant1_YM = new Button(compositeYoungsModulus, SWT.RADIO);
		med.setBtnConstant1_YM(btnConstant1_YM);
		CustomButton C_btnConstant1_YM = new CustomButton(Mediator.BUTTON_btnConstant1_YM,med);
		med.setC_btnConstant1_YM(C_btnConstant1_YM);
		C_btnConstant1_YM.setCustomWidget_btnConstant1_YM();
		FormData fd_btnConstant1_YM = new FormData();
		fd_btnConstant1_YM.top = new FormAttachment(0, 0);
		fd_btnConstant1_YM.left = new FormAttachment(0, 0);
		btnConstant1_YM.setLayoutData(fd_btnConstant1_YM);
		btnConstant1_YM.setText(ulObj.getUILabelValue(UILabel.Constatnt));
		
		Button btnTable1_YM = new Button(compositeYoungsModulus, SWT.RADIO);
		med.setBtnTable1_YM(btnTable1_YM);
		CustomButton C_btnTable1_YM = new CustomButton(Mediator.BUTTON_btnTable1_YM,med);
		med.setC_btnTable1_YM(C_btnTable1_YM);
		C_btnTable1_YM.setCustomWidget_btnTable1_YM();
		FormData fd_btnTable1_YM = new FormData();
		fd_btnTable1_YM.top = new FormAttachment(0, 0);
		fd_btnTable1_YM.left = new FormAttachment(btnConstant1_YM,10,SWT.RIGHT);
		btnTable1_YM.setLayoutData(fd_btnTable1_YM);
		btnTable1_YM.setText(ulObj.getUILabelValue(UILabel.Table));
		
		textYoungsModulus = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextYoungsModulus(textYoungsModulus);
		CustomText C_textYoungsModulus = new CustomText(Mediator.TEXT_textYoungsModulus,med);
		med.setC_textYoungsModulus(C_textYoungsModulus);
		C_textYoungsModulus.setCustomWidget_textYoungsModulus();
		FormData fd_textYoungsModulus = new FormData();
		fd_textYoungsModulus.top = new FormAttachment(compositeYoungsModulus, 10);
		fd_textYoungsModulus.left = new FormAttachment(compositeYoungsModulus, 0, SWT.LEFT);
		fd_textYoungsModulus.right = new FormAttachment(100,-40);
		textYoungsModulus.setLayoutData(fd_textYoungsModulus);
		
		Button btnExplorerYoungsModulus = new Button(grpMaterialParameter, SWT.NONE);
		med.setBtnExplorerYoungsModulus(btnExplorerYoungsModulus);
		CustomButton C_btnExplorerYoungsModulus = new CustomButton(Mediator.BUTTON_btnExplorerYoungsModulus,med);
		med.setC_btnExplorerYoungsModulus(C_btnExplorerYoungsModulus);
		C_btnExplorerYoungsModulus.setCustomWidget_btnExplorerYoungsModulus();
		FormData fd_btnExplorerYoungsModulus = new FormData();
		fd_btnExplorerYoungsModulus.top = new FormAttachment(textYoungsModulus, -2, SWT.TOP);
		fd_btnExplorerYoungsModulus.left = new FormAttachment(textYoungsModulus, 5);
		btnExplorerYoungsModulus.setLayoutData(fd_btnExplorerYoungsModulus);
		btnExplorerYoungsModulus.setText("...");
		//--------------------------------------------------------------------------------------------------------
		
		Label lblFlowStress = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblFlowStress = new FormData();
		fd_lblFlowStress.top = new FormAttachment(textYoungsModulus, 12);
		fd_lblFlowStress.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblFlowStress.setLayoutData(fd_lblFlowStress);
		lblFlowStress.setText(ulObj.getUILabelValue(UILabel.Flow_Stress));
		
		Composite compositeFlowStress = new Composite(grpMaterialParameter, SWT.NONE);
		compositeFlowStress.setLayout(new FormLayout());
		FormData fd_compositeFlowStress = new FormData();
		fd_compositeFlowStress.top = new FormAttachment(lblFlowStress, 5);
		fd_compositeFlowStress.left = new FormAttachment(lblFlowStress, 10,SWT.LEFT);
		compositeFlowStress.setLayoutData(fd_compositeFlowStress);
		
		
		Button btnConstant4_FS = new Button(compositeFlowStress, SWT.RADIO);
		med.setBtnConstant4_FS(btnConstant4_FS);
		CustomButton c_btnConstant4_FS = new CustomButton(Mediator.BUTTON_btnConstant4_FS,med);
		med.setC_btnConstant4_FS(c_btnConstant4_FS);
		c_btnConstant4_FS.setCustomWidget_btnConstant4_FS();
		FormData fd_btnConstant4_FS = new FormData();
		fd_btnConstant4_FS.top = new FormAttachment(0, 0);
		fd_btnConstant4_FS.left = new FormAttachment(0, 0);
		btnConstant4_FS.setLayoutData(fd_btnConstant4_FS);
		btnConstant4_FS.setText(ulObj.getUILabelValue(UILabel.Constatnt));
		//btnConstant4_FS.setSelection(true);
		
		
		Button btnTable4_FS = new Button(compositeFlowStress, SWT.RADIO);
		med.setBtnTable4_FS(btnTable4_FS);
		CustomButton c_btnTable4_FS = new CustomButton(Mediator.BUTTON_btnTable4_FS, med);
		med.setC_btnTable4_FS(c_btnTable4_FS);
		c_btnTable4_FS.setCustomWidget_btnTable4_FS();
		FormData fd_btnTable4_FS = new FormData();
		fd_btnTable4_FS.top = new FormAttachment(btnConstant4_FS, 0, SWT.TOP);
		fd_btnTable4_FS.left = new FormAttachment(btnConstant4_FS, 20);
		btnTable4_FS.setLayoutData(fd_btnTable4_FS);
		btnTable4_FS.setText(ulObj.getUILabelValue(UILabel.Table));
		//btnTable4_FS.setSelection(false);
		
		textFlowStress = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextFlowStress(textFlowStress);
		CustomText c_textFlowStress = new CustomText(Mediator.TEXT_textFlowStress,med);
		med.setC_textFlowStress(c_textFlowStress);
		c_textFlowStress.setCustomWidget_textFlowStress();
		FormData fd_textFlowStress = new FormData();
		fd_textFlowStress.top = new FormAttachment(compositeFlowStress, 5);
		fd_textFlowStress.left = new FormAttachment(textYoungsModulus,0,SWT.LEFT);
		fd_textFlowStress.right = new FormAttachment(textYoungsModulus, 0,SWT.RIGHT);
		textFlowStress.setLayoutData(fd_textFlowStress);
		
		Button btnExplorerFlowStress = new Button(grpMaterialParameter, SWT.NONE);
		med.setBtnExplorerFlowStress(btnExplorerFlowStress);
		CustomButton c_btnExplorerFlowStress = new CustomButton(Mediator.BUTTON_btnExplorerFlowStress,med);
		med.setC_btnExplorerFlowStress(c_btnExplorerFlowStress);
		c_btnExplorerFlowStress.setCustomWidget_btnExplorerFlowStress();
		FormData fd_btnExplorerFlowStress = new FormData();
		fd_btnExplorerFlowStress.top = new FormAttachment(textFlowStress, -2, SWT.TOP);
		fd_btnExplorerFlowStress.left = new FormAttachment(textFlowStress, 5);
		btnExplorerFlowStress.setLayoutData(fd_btnExplorerFlowStress);
		btnExplorerFlowStress.setText("...");
		
		//-------------------------------------------------------------------------------------
		
		Label lblYieldStrength = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblYieldStrength = new FormData();
		fd_lblYieldStrength.top = new FormAttachment(textFlowStress, 12);
		fd_lblYieldStrength.left = new FormAttachment(lblYoungsModulus,20, SWT.LEFT);
		lblYieldStrength.setLayoutData(fd_lblYieldStrength);
		lblYieldStrength.setText(ulObj.getUILabelValue(UILabel.Yield_Strength));
		
		textYieldStrength = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextYieldStrength(textYieldStrength);
		CustomText c_textYieldStrength = new CustomText(Mediator.TEXT_textYieldStrength,med);
		med.setC_textYieldStrength(c_textYieldStrength);
		c_textYieldStrength.setCustomWidget_textYieldStrength();
		FormData fd_textYieldStrength = new FormData();
		fd_textYieldStrength.top = new FormAttachment(lblYieldStrength, -2,SWT.TOP);
		fd_textYieldStrength.left = new FormAttachment(lblYieldStrength,20,SWT.RIGHT);
		fd_textYieldStrength.right = new FormAttachment(btnExplorerFlowStress, 0,SWT.RIGHT);
		textYieldStrength.setLayoutData(fd_textYieldStrength);
		textYieldStrength.setEnabled(false);
		
		Label lblTensileStrength = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblTensileStrength = new FormData();
		fd_lblTensileStrength.top = new FormAttachment(lblYieldStrength, 12);
		fd_lblTensileStrength.left = new FormAttachment(lblYoungsModulus, 20, SWT.LEFT);
		lblTensileStrength.setLayoutData(fd_lblTensileStrength);
		lblTensileStrength.setText(ulObj.getUILabelValue(UILabel.Tensile_Strength));
		
		textTensileStrength = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextTensileStrength(textTensileStrength);
		CustomText c_textTensileStrength = new CustomText(Mediator.TEXT_textTensileStrength,med);
		med.setC_textTensileStrength(c_textTensileStrength);
		c_textTensileStrength.setCustomWidget_textTensileStrength();
		FormData fd_tensileStrength_2D = new FormData();
		fd_tensileStrength_2D.top = new FormAttachment(lblTensileStrength,-2, SWT.TOP);
		fd_tensileStrength_2D.left = new FormAttachment(textYieldStrength,0,SWT.LEFT);
		fd_tensileStrength_2D.right = new FormAttachment(textYieldStrength, 0,SWT.RIGHT);
		textTensileStrength.setLayoutData(fd_tensileStrength_2D);
		textTensileStrength.setEnabled(false);
		
		Label lblElongation = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblElongation = new FormData();
		fd_lblElongation.top = new FormAttachment(lblTensileStrength, 12);
		fd_lblElongation.left = new FormAttachment(lblYoungsModulus, 20, SWT.LEFT);
		lblElongation.setLayoutData(fd_lblElongation);
		lblElongation.setText(ulObj.getUILabelValue(UILabel.Elongation));
		
		textElongation = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextElongation(textElongation);
		CustomText c_textElongation = new CustomText(Mediator.Text_textElongation,med);
		med.setC_textElongation(c_textElongation);
		c_textElongation.setCustomWidget_textElongation();
		FormData fd_textElongation = new FormData();
		fd_textElongation.top = new FormAttachment(lblElongation,-2,SWT.TOP);
		fd_textElongation.left = new FormAttachment(textYieldStrength, 0, SWT.LEFT);
		fd_textElongation.right = new FormAttachment(textYieldStrength, 0, SWT.RIGHT);
		textElongation.setLayoutData(fd_textElongation);
		textElongation.setEnabled(false);
		//-------------------------------------------------------------------------------------
		/* */
		
		Label lblThermalExpansionCoefficient = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblThermalExpansionCoefficient = new FormData();
		fd_lblThermalExpansionCoefficient.top = new FormAttachment(lblElongation, 10);
		fd_lblThermalExpansionCoefficient.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblThermalExpansionCoefficient.setLayoutData(fd_lblThermalExpansionCoefficient);
		lblThermalExpansionCoefficient.setText(ulObj.getUILabelValue(UILabel.Thermal_Expansion_Coefficient));
		
		Composite compositeThermalExpansionCoefficient = new Composite(grpMaterialParameter, SWT.NONE);
		compositeThermalExpansionCoefficient.setLayout(new FormLayout());
		FormData fd_compositeThermalExpansionCoefficient = new FormData();
		fd_compositeThermalExpansionCoefficient.top = new FormAttachment(lblThermalExpansionCoefficient, 5);
		fd_compositeThermalExpansionCoefficient.left = new FormAttachment(lblThermalExpansionCoefficient, 10, SWT.LEFT);
		fd_compositeThermalExpansionCoefficient.right = new FormAttachment(lblThermalExpansionCoefficient,100,SWT.RIGHT);
		fd_compositeThermalExpansionCoefficient.bottom = new FormAttachment(lblThermalExpansionCoefficient,20,SWT.BOTTOM);
		compositeThermalExpansionCoefficient.setLayoutData(fd_compositeThermalExpansionCoefficient);
		
		Button btnConstant2_TEC = new Button(compositeThermalExpansionCoefficient, SWT.RADIO);
		med.setBtnConstant2_TEC(btnConstant2_TEC);
		CustomButton C_btnConstant2_TEC = new CustomButton(Mediator.BUTTON_btnConstant2_TEC,med);
		med.setC_btnConstant2_TEC(C_btnConstant2_TEC);
		C_btnConstant2_TEC.setCustomWidget_btnConstant2_TEC();
		FormData fd_btnConstant2_TEC = new FormData();
		fd_btnConstant2_TEC.top = new FormAttachment(0, 0);
		fd_btnConstant2_TEC.left = new FormAttachment(0, 0);
		btnConstant2_TEC.setLayoutData(fd_btnConstant2_TEC);
		btnConstant2_TEC.setText(ulObj.getUILabelValue(UILabel.Constatnt));
		
		Button btnTable2_TEC = new Button(compositeThermalExpansionCoefficient, SWT.RADIO);
		med.setBtnTable2_TEC(btnTable2_TEC);
		CustomButton C_btnTable2_TEC = new CustomButton(Mediator.BUTTON_btnTable2_TEC,med);
		med.setC_btnTable2_TEC(C_btnTable2_TEC);
		C_btnTable2_TEC.setCustomWidget_btnTable2_TEC();
		FormData fd_btnTable2 = new FormData();
		fd_btnTable2.top = new FormAttachment(0, 0);
		fd_btnTable2.left = new FormAttachment(btnConstant2_TEC,10,SWT.RIGHT);
		btnTable2_TEC.setLayoutData(fd_btnTable2);
		btnTable2_TEC.setText(ulObj.getUILabelValue(UILabel.Table));
		
		textThermalExpansionCoefficient = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextThermalExpansionCoefficient(textThermalExpansionCoefficient);
		CustomText C_textThermalExpansionCoefficient = new CustomText(Mediator.TEXT_textThermalExpansionCoefficient,med);
		med.setC_textThermalExpansionCoefficient(C_textThermalExpansionCoefficient);
		C_textThermalExpansionCoefficient.setCustomWidget_textThermalExpansionCoefficient();
		FormData fd_textThermalExpansionCoefficient = new FormData();
		fd_textThermalExpansionCoefficient.top = new FormAttachment(compositeThermalExpansionCoefficient, 10);
		fd_textThermalExpansionCoefficient.left = new FormAttachment(compositeThermalExpansionCoefficient, 0, SWT.LEFT);
		fd_textThermalExpansionCoefficient.right = new FormAttachment(100,-40);
		textThermalExpansionCoefficient.setLayoutData(fd_textThermalExpansionCoefficient);
		
		Button btnExplorerThermalExpansionCoefficient = new Button(grpMaterialParameter, SWT.NONE);
		med.setBtnExplorerThermalExpansionCoefficient(btnExplorerThermalExpansionCoefficient);
		CustomButton C_btnExplorerThermalExpansionCoefficient = new CustomButton(Mediator.Button_btnExplorerThermalExpansionCoefficient,med);
		med.setC_btnExplorerThermalExpansionCoefficient(C_btnExplorerThermalExpansionCoefficient);
		C_btnExplorerThermalExpansionCoefficient.setCustomWidget_btnExplorerThermalExpansionCoefficient();
		FormData fd_btnExplorerThermalExpansionCoefficient = new FormData();
		fd_btnExplorerThermalExpansionCoefficient.top = new FormAttachment(textThermalExpansionCoefficient, -2, SWT.TOP);
		fd_btnExplorerThermalExpansionCoefficient.left = new FormAttachment(textThermalExpansionCoefficient, 5);
		btnExplorerThermalExpansionCoefficient.setLayoutData(fd_btnExplorerThermalExpansionCoefficient);
		btnExplorerThermalExpansionCoefficient.setText("...");
		
		
		Label lblPoissonsRatio = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblPoissonsRatio = new FormData();
		fd_lblPoissonsRatio.top = new FormAttachment(textThermalExpansionCoefficient, 10);
		fd_lblPoissonsRatio.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblPoissonsRatio.setLayoutData(fd_lblPoissonsRatio);
		lblPoissonsRatio.setText(ulObj.getUILabelValue(UILabel.Poissons_Ratio));
		
		Composite compositePoissonsRatio = new Composite(grpMaterialParameter, SWT.NONE);
		compositePoissonsRatio.setLayout(new FormLayout());
		FormData fd_compositePoissonsRatio = new FormData();
		fd_compositePoissonsRatio.top = new FormAttachment(lblPoissonsRatio, 5);
		fd_compositePoissonsRatio.left = new FormAttachment(lblPoissonsRatio, 10, SWT.LEFT);
		fd_compositePoissonsRatio.right = new FormAttachment(lblPoissonsRatio,100,SWT.RIGHT);
		fd_compositePoissonsRatio.bottom = new FormAttachment(lblPoissonsRatio,20,SWT.BOTTOM);
		compositePoissonsRatio.setLayoutData(fd_compositePoissonsRatio);
		
		Button btnConstant3_PR = new Button(compositePoissonsRatio, SWT.RADIO);
		med.setBtnConstant3_PR(btnConstant3_PR);
		CustomButton C_btnConstant3_PR = new CustomButton(Mediator.BUTTON_btnConstant3_PR,med);
		med.setC_btnConstant3_PR(C_btnConstant3_PR);
		C_btnConstant3_PR.setCustomWidget_btnConstant3_PR();
		FormData fd_btnConstant3_PR = new FormData();
		fd_btnConstant3_PR.top = new FormAttachment(0, 0);
		fd_btnConstant3_PR.left = new FormAttachment(0, 0);
		btnConstant3_PR.setLayoutData(fd_btnConstant3_PR);
		btnConstant3_PR.setText(ulObj.getUILabelValue(UILabel.Constatnt));
		
		Button btnTable3_PR = new Button(compositePoissonsRatio, SWT.RADIO);
		med.setBtnTable3_PR(btnTable3_PR);
		CustomButton C_btnTable3_PR = new CustomButton(Mediator.BUTTON_btnTable3_PR,med);
		med.setC_btnTable3(C_btnTable3_PR);
		C_btnTable3_PR.setCustomWidget_btnTable3_PR();
		FormData fd_btnTable3_PR = new FormData();
		fd_btnTable3_PR.top = new FormAttachment(0, 0);
		fd_btnTable3_PR.left = new FormAttachment(btnConstant3_PR,10,SWT.RIGHT);
		btnTable3_PR.setLayoutData(fd_btnTable3_PR);
		btnTable3_PR.setText(ulObj.getUILabelValue(UILabel.Table));
		
		textPoissonsRatio = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextPoissonsRatio(textPoissonsRatio);
		CustomText C_textPoissonsRatio = new CustomText(Mediator.TEXT_textPoissonsRatio,med);
		med.setC_textPoissonsRatio(C_textPoissonsRatio);
		C_textPoissonsRatio.setCustomWidget_textPoissonsRatio();
		FormData fd_textPoissonsRatio = new FormData();
		fd_textPoissonsRatio.top = new FormAttachment(compositePoissonsRatio, 10);
		fd_textPoissonsRatio.left = new FormAttachment(compositePoissonsRatio, 0, SWT.LEFT);
		fd_textPoissonsRatio.right = new FormAttachment(100,-40);
		textPoissonsRatio.setLayoutData(fd_textPoissonsRatio);
		
		Button btnExplorerPoissonsRatio = new Button(grpMaterialParameter, SWT.NONE);
		med.setBtnExplorerPoissonsRatio(btnExplorerPoissonsRatio);
		CustomButton C_btnExplorerPoissonsRatio = new CustomButton(Mediator.BUTTON_btnExplorerPoissonsRatio,med);
		med.setC_btnExplorerPoissonsRatio(C_btnExplorerPoissonsRatio);
		C_btnExplorerPoissonsRatio.setCustomWidget_btnExplorerPoissonsRatio();
		FormData fd_btnExplorerPoissonsRatio = new FormData();
		fd_btnExplorerPoissonsRatio.top = new FormAttachment(textPoissonsRatio, -2, SWT.TOP);
		fd_btnExplorerPoissonsRatio.left = new FormAttachment(textPoissonsRatio, 5);
		btnExplorerPoissonsRatio.setLayoutData(fd_btnExplorerPoissonsRatio);
		btnExplorerPoissonsRatio.setText("...");
		
		
		Label lblMassDensity = new Label(grpMaterialParameter, SWT.NONE);
		FormData fd_lblMassDensity = new FormData();
		fd_lblMassDensity.top = new FormAttachment(textPoissonsRatio, 10);
		fd_lblMassDensity.left = new FormAttachment(lblYoungsModulus, 0, SWT.LEFT);
		lblMassDensity.setLayoutData(fd_lblMassDensity);
		lblMassDensity.setText(ulObj.getUILabelValue(UILabel.Mass_Density));
		
		textMassDensity = new Text(grpMaterialParameter, SWT.BORDER);
		med.setTextMassDensity(textMassDensity);
		CustomText C_textMassDensity = new CustomText(Mediator.TEXT_textMassDensity,med);
		med.setC_textMassDensity(C_textMassDensity);
		C_textMassDensity.setCustomWidget_textMassDensity();
		FormData fd_textMassDensity = new FormData();
		fd_textMassDensity.top = new FormAttachment(lblMassDensity, -2,SWT.TOP);
		fd_textMassDensity.left = new FormAttachment(lblMassDensity, 50, SWT.RIGHT);
		fd_textMassDensity.right = new FormAttachment(100,-10);
		textMassDensity.setLayoutData(fd_textMassDensity);
		//
		// Group5 End
		//=============
		
		
		//=============
		// Group6 Start
		//
		
		Group grpAnalysisInformation = new Group(compositeDetail, SWT.NONE);
		med.setGrpAnalysisInformation(grpAnalysisInformation);
		grpAnalysisInformation.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		grpAnalysisInformation.setText(ulObj.getUILabelValue(UILabel.Analysis_Information));
		grpAnalysisInformation.setLayout(new FormLayout());
		FormData fd_grpAnalysisInformation = new FormData();
		fd_grpAnalysisInformation.top = new FormAttachment(grpMaterialParameter, 5);
		fd_grpAnalysisInformation.left = new FormAttachment(grpMaterialParameter, 0, SWT.LEFT);
		fd_grpAnalysisInformation.right = new FormAttachment(grpMaterialParameter, 0,SWT.RIGHT);
		fd_grpAnalysisInformation.bottom = new FormAttachment(grpMaterialParameter, 240, SWT.BOTTOM);
		grpAnalysisInformation.setLayoutData(fd_grpAnalysisInformation);
		
		Label lblAnalysisTime = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblAnalysisTime = new FormData();
		fd_lblAnalysisTime.top = new FormAttachment(0, 10);
		fd_lblAnalysisTime.left = new FormAttachment(0, 10);
		lblAnalysisTime.setLayoutData(fd_lblAnalysisTime);
		lblAnalysisTime.setText(ulObj.getUILabelValue(UILabel.Analysis_Time));
		
		textAnalysisTime = new Text(grpAnalysisInformation, SWT.BORDER);
		med.setTextAnalysisTime(textAnalysisTime);
		CustomText C_textAnalysisTime = new CustomText(Mediator.TEXT_textAnalysisTime,med);
		med.setC_textAnalysisTime(C_textAnalysisTime);
		C_textAnalysisTime.setCustomWidget_textAnalysisTime();
		FormData fd_textAnalysisTime = new FormData();
		fd_textAnalysisTime.top = new FormAttachment(lblAnalysisTime, -2,SWT.TOP);
		fd_textAnalysisTime.left = new FormAttachment(lblAnalysisTime, 80, SWT.RIGHT);
		fd_textAnalysisTime.right = new FormAttachment(100,-10);
		textAnalysisTime.setLayoutData(fd_textAnalysisTime);
		
		Label lblNoOfInc = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblNoOfInc = new FormData();
		fd_lblNoOfInc.top = new FormAttachment(lblAnalysisTime, 10);
		fd_lblNoOfInc.left = new FormAttachment(lblAnalysisTime, 0,SWT.LEFT);
		lblNoOfInc.setLayoutData(fd_lblNoOfInc);
		lblNoOfInc.setText(ulObj.getUILabelValue(UILabel.No_of_Inc));
		
		textNoOfInc = new Text(grpAnalysisInformation, SWT.BORDER);
		med.setTextNoOfInc(textNoOfInc);
		CustomText C_textNoOfInc = new CustomText(Mediator.TEXT_textNoOfInc,med);
		med.setC_textNoOfInc(C_textNoOfInc);
		C_textNoOfInc.setCustomWidget_textNoOfInc();
		FormData fd_textNoOfInc = new FormData();
		fd_textNoOfInc.top = new FormAttachment(lblNoOfInc, -2,SWT.TOP);
		fd_textNoOfInc.left = new FormAttachment(textAnalysisTime, 0, SWT.LEFT);
		fd_textNoOfInc.right = new FormAttachment(100,-10);
		textNoOfInc.setLayoutData(fd_textNoOfInc);

		Label lblPostWritingFrequency = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblPostWritingFrequency = new FormData();
		fd_lblPostWritingFrequency.top = new FormAttachment(lblNoOfInc, 10);
		fd_lblPostWritingFrequency.left = new FormAttachment(lblAnalysisTime, 0,SWT.LEFT);
		lblPostWritingFrequency.setLayoutData(fd_lblPostWritingFrequency);
		lblPostWritingFrequency.setText(ulObj.getUILabelValue(UILabel.Post_Writing_frequency));
		
		textPostWritingFrequency = new Text(grpAnalysisInformation, SWT.BORDER);
		med.setTextPostWritingFrequency(textPostWritingFrequency);
		CustomText C_textPostWritingFrequency = new CustomText(Mediator.TEXT_textPostWritingFrequency,med);
		med.setC_textPostWritingFrequency(C_textPostWritingFrequency);
		C_textPostWritingFrequency.setCustomWidget_textPostWritingFrequency();
		FormData fd_textPostWritingFrequency = new FormData();
		fd_textPostWritingFrequency.top = new FormAttachment(lblPostWritingFrequency, -2,SWT.TOP);
		fd_textPostWritingFrequency.left = new FormAttachment(textAnalysisTime, 0, SWT.LEFT);
		fd_textPostWritingFrequency.right = new FormAttachment(100,-10);
		textPostWritingFrequency.setLayoutData(fd_textPostWritingFrequency);
		
		Label lblTimeIncrement = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblTimeIncrement = new FormData();
		fd_lblTimeIncrement.top = new FormAttachment(lblPostWritingFrequency, 10);
		fd_lblTimeIncrement.left = new FormAttachment(lblAnalysisTime, 0,SWT.LEFT);
		lblTimeIncrement.setLayoutData(fd_lblTimeIncrement);
		lblTimeIncrement.setText(ulObj.getUILabelValue(UILabel.Time_Increment));
		lblTimeIncrement.setVisible(false);
		
		textTimeIncrement = new Text(grpAnalysisInformation, SWT.BORDER);
		med.setTextTimeIncrement(textTimeIncrement);
		CustomText C_textTimeIncrement = new CustomText(Mediator.TEXT_textTimeIncrement,med);
		med.setC_textTimeIncrement(C_textTimeIncrement);
		C_textTimeIncrement.setCustomWidget_textTimeIncrement();
		FormData fd_textTimeIncrement = new FormData();
		fd_textTimeIncrement.top = new FormAttachment(lblTimeIncrement, -2,SWT.TOP);
		fd_textTimeIncrement.left = new FormAttachment(textAnalysisTime, 0, SWT.LEFT);
		fd_textTimeIncrement.right = new FormAttachment(100,-10);
		textTimeIncrement.setLayoutData(fd_textTimeIncrement);
		textTimeIncrement.setVisible(false);
		
		
		Label lblParallelDDM = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblParallelDDM = new FormData();
		fd_lblParallelDDM.top = new FormAttachment(lblTimeIncrement, 10);
		fd_lblParallelDDM.left = new FormAttachment(lblAnalysisTime, 0,SWT.LEFT);
		lblParallelDDM.setLayoutData(fd_lblParallelDDM);
		lblParallelDDM.setText(ulObj.getUILabelValue(UILabel.Parallel_DDM));
		
		Button btnParallelDDM = new Button(grpAnalysisInformation, SWT.CHECK);
		med.setBtnParallelDDM(btnParallelDDM);
		CustomButton C_btnParallelDDM = new CustomButton(Mediator.BUTTON_btnParallelDDM,med);
		med.setC_btnParallelDDM(C_btnParallelDDM);
		C_btnParallelDDM.setCustomWidget_btnParallelDDM();
		FormData fd_btnParallelDDM = new FormData();
		fd_btnParallelDDM.top = new FormAttachment(lblParallelDDM, 0, SWT.TOP);
		fd_btnParallelDDM.left = new FormAttachment(textAnalysisTime, 0, SWT.LEFT);
		btnParallelDDM.setLayoutData(fd_btnParallelDDM);
		btnParallelDDM.setText(ulObj.getUILabelValue(UILabel.use));
		
		Label lblDomain = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblDomain = new FormData();
		fd_lblDomain.top = new FormAttachment(lblParallelDDM, 10);
		fd_lblDomain.left = new FormAttachment(lblAnalysisTime, 0,SWT.LEFT);
		lblDomain.setLayoutData(fd_lblDomain);
		lblDomain.setText(ulObj.getUILabelValue(UILabel.Domain));
		
		Spinner spinnerDomain = new Spinner(grpAnalysisInformation, SWT.BORDER);
		med.setSpinnerDomain(spinnerDomain);
		CustomSpinner C_spinnerDomain = new CustomSpinner(Mediator.SPINNER_spinnerDomain,med);
		med.setC_spinnerDomain(C_spinnerDomain);
		C_spinnerDomain.setCustomWidget_spinnerDomain();
		FormData fd_spinnerDomain = new FormData();
		fd_spinnerDomain.top = new FormAttachment(lblDomain, -2,SWT.TOP);
		fd_spinnerDomain.left = new FormAttachment(textAnalysisTime, 0, SWT.LEFT);
		fd_spinnerDomain.right = new FormAttachment(100,-10);
		spinnerDomain.setLayoutData(fd_spinnerDomain);
		
		
		Label lblParallelMultiThread = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblParallelMultiThread = new FormData();
		fd_lblParallelMultiThread.top = new FormAttachment(lblDomain, 10);
		fd_lblParallelMultiThread.left = new FormAttachment(lblAnalysisTime, 0,SWT.LEFT);
		lblParallelMultiThread.setLayoutData(fd_lblParallelMultiThread);
		lblParallelMultiThread.setText(ulObj.getUILabelValue(UILabel.Parallel_Multi_Thread));
		
		Button btnParallelMultiThread = new Button(grpAnalysisInformation, SWT.CHECK);
		med.setBtnParallelMultiThread(btnParallelMultiThread);
		CustomButton C_btnParallelMultiThread = new CustomButton(Mediator.BUTTON_btnParallelMultiThread,med);
		med.setC_btnParallelMultiThread(C_btnParallelMultiThread);
		C_btnParallelMultiThread.setCustomWidget_btnParallelMultiThread();
		FormData fd_btnParallelMultiThread = new FormData();
		fd_btnParallelMultiThread.top = new FormAttachment(lblParallelMultiThread, 0, SWT.TOP);
		fd_btnParallelMultiThread.left = new FormAttachment(textAnalysisTime, 0, SWT.LEFT);
		btnParallelMultiThread.setLayoutData(fd_btnParallelMultiThread);
		btnParallelMultiThread.setText(ulObj.getUILabelValue(UILabel.use));
		
		Label lblThread = new Label(grpAnalysisInformation, SWT.NONE);
		FormData fd_lblThread = new FormData();
		fd_lblThread.top = new FormAttachment(lblParallelMultiThread, 10);
		fd_lblThread.left = new FormAttachment(lblAnalysisTime, 0,SWT.LEFT);
		lblThread.setLayoutData(fd_lblThread);
		lblThread.setText(ulObj.getUILabelValue(UILabel.Thread));
		
		Spinner spinnerThread = new Spinner(grpAnalysisInformation, SWT.BORDER);
		med.setSpinnerThread(spinnerThread);
		CustomSpinner C_spinnerThread = new CustomSpinner(Mediator.SPINNER_spinnerThread,med);
		med.setC_spinnerThread(C_spinnerThread);
		C_spinnerThread.setCustomWidget_spinnerThread();
		FormData fd_spinnerThread = new FormData();
		fd_spinnerThread.top = new FormAttachment(lblThread, -2,SWT.TOP);
		fd_spinnerThread.left = new FormAttachment(textAnalysisTime, 0, SWT.LEFT);
		fd_spinnerThread.right = new FormAttachment(100,-10);
		spinnerThread.setLayoutData(fd_spinnerThread);
		
		//
		// Group6 End
		//=============
		
		
		//*/
		
		//=============
		// 
		
		Label lblModelName = new Label(parent, SWT.NONE);
		lblModelName.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblModelName = new FormData();
		fd_lblModelName.top = new FormAttachment(0, 20);
		fd_lblModelName.left = new FormAttachment(0, 10);
		lblModelName.setLayoutData(fd_lblModelName);
		lblModelName.setText(ulObj.getUILabelValue(UILabel.Model_Name));
		
		Label lblModelNameValue = new Label(parent, SWT.NONE);
		lblModelNameValue.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblModelNameValue.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		med.setLblModelNameValue(lblModelNameValue);
		FormData fd_lblModelNameValue = new FormData();
		fd_lblModelNameValue.top = new FormAttachment(lblModelName, 0, SWT.TOP);
		fd_lblModelNameValue.left = new FormAttachment(lblModelName, 5);
		fd_lblModelNameValue.right = new FormAttachment(lblModelName,400);
		lblModelNameValue.setLayoutData(fd_lblModelNameValue);
		lblModelNameValue.setText("-");
		
		Label lblWorkspace = new Label(parent, SWT.NONE);
		lblWorkspace.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblWorkspace = new FormData();
		fd_lblWorkspace.top = new FormAttachment(lblModelName, 0, SWT.TOP);
		fd_lblWorkspace.left = new FormAttachment(lblModelNameValue, 20);
		lblWorkspace.setLayoutData(fd_lblWorkspace);
		lblWorkspace.setText(ulObj.getUILabelValue(UILabel.Workspace));
		
		Label lblWorkspacePath = new Label(parent, SWT.NONE);
		lblWorkspacePath.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblWorkspacePath.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		med.setLblWorkspacePath(lblWorkspacePath);
		FormData fd_lblWorkspacePath = new FormData();
		fd_lblWorkspacePath.top = new FormAttachment(lblModelName, 0, SWT.TOP);
		fd_lblWorkspacePath.left = new FormAttachment(lblWorkspace, 5);
		fd_lblWorkspacePath.right = new FormAttachment(100,-10);
		lblWorkspacePath.setLayoutData(fd_lblWorkspacePath);
		lblWorkspacePath.setText("-");
		lblWorkspacePath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				String value = med.getLblWorkspacePath().getText();
				if(myUtil.checkPath(value)){
					if(myUtil.checkOS().equals("window")){
						try{
							Runtime.getRuntime().exec("explorer "+ value);
						}catch(Exception ezz){
						}
					}
				}
			}
		});
		
		Button btnApply = new Button(parent, SWT.NONE);
		med.setBtnApply(btnApply);
		CustomButton C_btnApply = new CustomButton(Mediator.BUTTON_btnApply,med);
		med.setC_btnApply(C_btnApply);
		C_btnApply.setCustomWidget_btnApply();
		FormData fd_btnApply = new FormData();
		fd_btnApply.top = new FormAttachment(tabFolder, 5);
		fd_btnApply.left = new FormAttachment(tabFolder,-110,SWT.RIGHT);
		fd_btnApply.right = new FormAttachment(tabFolder, -10, SWT.RIGHT);
		btnApply.setLayoutData(fd_btnApply);
		btnApply.setText(ulObj.getUILabelValue(UILabel.Apply));
		
		//
		//=============
		
		
		init_TableColunm();
		init_ActionComponent();
		med.getTabFolder().setEnabled(false);
		med.getBtnApply().setEnabled(false);
	}

	
	public void setFocus() {}
	
	public void init_TableColunm(){
		
		String [] ColumnName_SlabPlateInfo = new String[]{
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_8)			
				};
		int [] ColumnWidth_SlabPlateInfo = new int[]{
				80,80,80,80,80,80,80,80,80
				};
		int [] ColumnAligments_SlabPlateInfo = new int []{
				SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER};
		String [] ColumnProperty_SlabPlateInfo ={
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL1_8)	
				};
		
		for(int i=0;i<ColumnName_SlabPlateInfo.length;i++){
			TableColumn tableColumn_SlabPlateInfo = new TableColumn(this.tableSlabPlateInfo,ColumnAligments_SlabPlateInfo[i]);
			tableColumn_SlabPlateInfo.setText(ColumnName_SlabPlateInfo[i]);
			tableColumn_SlabPlateInfo.setWidth(ColumnWidth_SlabPlateInfo[i]);			
		}
		//med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty_SlabPlateInfo);
		med.getTableViewerSlabPlateInfo().setColumnProperties(ColumnProperty_SlabPlateInfo);
		
		String [] ColumnName_Variable = new String[]{
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_8),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_9),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_10),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_11),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_12),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_13),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_14),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_15)					
				};
		int [] ColumnWidth_Variable  = new int[]{
				60,60,60,60,60,60,60,60,60,60,60,60,60,60,60,60
				};
		int [] ColumnAligments_Variable  = new int []{
				SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER
				,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER,SWT.CENTER};
		String [] ColumnProperty_Variable  ={
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_0),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_1),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_2),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_3),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_4),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_5),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_6),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_7),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_8),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_9),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_10),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_11),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_12),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_13),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_14),
				tclObj.getTableColumnLabel(TableColumnLabel.CL2_15)					
				};
		
		for(int i=0;i<ColumnName_Variable .length;i++){
			TableColumn tableColumn_Variable = new TableColumn(this.tableVariable,ColumnAligments_Variable[i]);
			tableColumn_Variable.setText(ColumnName_Variable[i]);
			tableColumn_Variable.setWidth(ColumnWidth_Variable[i]);			
		}
		//med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty_Variable);
		med.getTableViewerVariable().setColumnProperties(ColumnProperty_Variable);
		
		String [] ColumnName_PLog = new String[]{
				tclObj.getTableColumnLabel(TableColumnLabel.CL3_0),
				"F1","F2","F3","F4","F5","F6","F7"									
				};
		int [] ColumnWidth_PLog  = new int[]{
				90,80,80,80,80,80,80,80
				};
		int [] ColumnAligments_PLog  = new int []{
				SWT.CENTER,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT,SWT.RIGHT};
		String [] ColumnProperty_PLog  ={
				tclObj.getTableColumnLabel(TableColumnLabel.CL3_0),
				"F1","F2","F3","F4","F5","F6","F7"				
				};
		
		for(int i=0;i<ColumnName_PLog.length;i++){
			TableColumn tableColumn_PLog = new TableColumn(this.tablePLog,ColumnAligments_PLog[i]);
			tableColumn_PLog.setText(ColumnName_PLog[i]);
			tableColumn_PLog.setWidth(ColumnWidth_PLog[i]);			
		}
		//med.getTableViewerUpperRoll().setColumnProperties(ColumnProperty_PLog);
		med.getTableViewerPLog().setColumnProperties(ColumnProperty_PLog);
	}
	
	public void init_ActionComponent(){
		//TabFolder
		HandlerTabFolder handlerTabFolder = new HandlerTabFolder();
		med.getTabFolder().addListener(SWT.Selection, handlerTabFolder);
		
		//Button
		HandlerButton handlerButton = new HandlerButton();
		med.getBtnImportPLog().addListener(SWT.Selection, handlerButton);
		med.getBtnExportPLog().addListener(SWT.Selection, handlerButton);
		med.getBtnApply().addListener(SWT.Selection, handlerButton);
		med.getBtnF1().addListener(SWT.Selection, handlerButton);
		med.getBtnF2().addListener(SWT.Selection, handlerButton);
		med.getBtnF3().addListener(SWT.Selection, handlerButton);
		med.getBtnF4().addListener(SWT.Selection, handlerButton);
		med.getBtnF5().addListener(SWT.Selection, handlerButton);
		med.getBtnF6().addListener(SWT.Selection, handlerButton);
		med.getBtnF7().addListener(SWT.Selection, handlerButton);
		med.getBtnConstant1_YM().addListener(SWT.Selection, handlerButton);
		med.getBtnTable1_YM().addListener(SWT.Selection, handlerButton);
		med.getBtnConstant4_FS().addListener(SWT.Selection, handlerButton);
		med.getBtnTable4_FS().addListener(SWT.Selection, handlerButton);
		med.getBtnConstant2_TEC().addListener(SWT.Selection, handlerButton);
		med.getBtnTable2_TEC().addListener(SWT.Selection, handlerButton);
		med.getBtnConstant3_PR().addListener(SWT.Selection, handlerButton);
		med.getBtnTable3_PR().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerYoungsModulus().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerFlowStress().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerThermalExpansionCoefficient().addListener(SWT.Selection, handlerButton);
		med.getBtnExplorerPoissonsRatio().addListener(SWT.Selection, handlerButton);
		med.getBtnParallelDDM().addListener(SWT.Selection, handlerButton);
		med.getBtnParallelMultiThread().addListener(SWT.Selection, handlerButton);
		
		//Spinner
		HandlerSpinner handlerSpinner = new HandlerSpinner();
		med.getSpinnerDomain().addListener(SWT.Selection, handlerSpinner);
		med.getSpinnerThread().addListener(SWT.Selection, handlerSpinner);
		
		//Text 
		HandlerText handlerText = new HandlerText();
		med.getTextTopWRDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextBottomWRDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextWRCrown().addListener(SWT.CHANGED, handlerText);
		med.getTextWRLength().addListener(SWT.CHANGED, handlerText);
		med.getTextWRMeshAngle().addListener(SWT.CHANGED, handlerText);
		med.getTextWRChamferX().addListener(SWT.CHANGED, handlerText);
		med.getTextWRChamferY().addListener(SWT.CHANGED, handlerText);
		med.getTextWRRound().addListener(SWT.CHANGED, handlerText);
		
		med.getTextTopBURDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextBottomBURDiameter().addListener(SWT.CHANGED, handlerText);
		med.getTextBURLength().addListener(SWT.CHANGED, handlerText);
		med.getTextBURMeshAngle().addListener(SWT.CHANGED, handlerText);
		med.getTextBURChamferX().addListener(SWT.CHANGED, handlerText);
		med.getTextBURChamferY().addListener(SWT.CHANGED, handlerText);
		med.getTextBURChamferY().addListener(SWT.CHANGED, handlerText);
		
		med.getTextThickness().addListener(SWT.CHANGED, handlerText);
		med.getTextWidth().addListener(SWT.CHANGED, handlerText);
		med.getTextLength().addListener(SWT.CHANGED, handlerText);
		med.getTextEntryTemperature().addListener(SWT.CHANGED, handlerText);
		med.getTextExitTemperature().addListener(SWT.CHANGED, handlerText);
		med.getTextInitialPosition().addListener(SWT.CHANGED, handlerText);
		med.getTextMeshLength().addListener(SWT.CHANGED, handlerText);
		med.getTextThicknessMeshDivisions().addListener(SWT.CHANGED, handlerText);
		med.getTextPlateCrown().addListener(SWT.CHANGED, handlerText);
		
		med.getTextVelocity().addListener(SWT.CHANGED, handlerText);
		med.getTextRollGap().addListener(SWT.CHANGED, handlerText);
		med.getTextPassLine().addListener(SWT.CHANGED, handlerText);
		med.getTextPairCrossAngle().addListener(SWT.CHANGED, handlerText);
		med.getTextBenderForce().addListener(SWT.CHANGED, handlerText);
		med.getTextRollTorque().addListener(SWT.CHANGED, handlerText);
		med.getTextTensionStress().addListener(SWT.CHANGED, handlerText);
		med.getTextRollToPlateFrictCoef().addListener(SWT.CHANGED, handlerText);
		med.getTextRollToRollFrictCoef().addListener(SWT.CHANGED, handlerText);
		med.getTextSpeedDifferentRatioTopRoll().addListener(SWT.CHANGED, handlerText);
		med.getTextSpeedDifferentRatioBottomRoll().addListener(SWT.CHANGED, handlerText);
		med.getTextTopWRRotVel().addListener(SWT.CHANGED, handlerText);
		med.getTextBottomWRRotVel().addListener(SWT.CHANGED, handlerText);
		med.getTextTopBURRotVel().addListener(SWT.CHANGED, handlerText);
		med.getTextBottomBURRotVel().addListener(SWT.CHANGED, handlerText);
		
		med.getTextRollYoungsModulus().addListener(SWT.CHANGED, handlerText);
		med.getTextRollPoissonsRatio().addListener(SWT.CHANGED, handlerText);
		
		med.getTextYoungsModulus().addListener(SWT.CHANGED, handlerText);
		med.getTextFlowStress().addListener(SWT.CHANGED, handlerText);
		med.getTextYieldStrength().addListener(SWT.CHANGED, handlerText);
		med.getTextTensileStrength().addListener(SWT.CHANGED, handlerText);
		med.getTextElongation().addListener(SWT.CHANGED, handlerText);
		med.getTextThermalExpansionCoefficient().addListener(SWT.CHANGED, handlerText);
		med.getTextPoissonsRatio().addListener(SWT.CHANGED, handlerText);
		med.getTextMassDensity().addListener(SWT.CHANGED, handlerText);
		
		med.getTextAnalysisTime().addListener(SWT.CHANGED, handlerText);
		med.getTextNoOfInc().addListener(SWT.CHANGED, handlerText);
		med.getTextPostWritingFrequency().addListener(SWT.CHANGED, handlerText);
		med.getTextTimeIncrement().addListener(SWT.CHANGED, handlerText);
		//med.getTextPostWritingFrequency().addListener(SWT.CHANGED, handlerText);
		//med.getTextTimeIncrement().addListener(SWT.CHANGED, handlerText);
		
		
		//TableViewer
		//HandlerTableViewer handlerTableViewer = new HandlerTableViewer();
		//med.getTableViewerSlabPlateInfo().addSelectionChangedListener(handlerTableViewer);
		//med.getTableViewerVariable().addSelectionChangedListener(handlerTableViewer);
		//med.getTableViewerPLog().addSelectionChangedListener(handlerTableViewer);
	}
}