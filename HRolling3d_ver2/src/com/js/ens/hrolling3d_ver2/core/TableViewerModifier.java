package com.js.ens.hrolling3d_ver2.core;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Item;
//import org.jfree.util.Log;

import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_PLog;
import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_SlabPlateInfo;
import com.js.ens.hrolling3d_ver2.core.tableDatas.TableData_Variable;

public class TableViewerModifier implements ICellModifier {

	private Mediator med;
	private Viewer tableViewer;
	private TableData_SlabPlateInfo TD_SlabPlateInforObj;
	private TableData_Variable TD_VariableObj;
	private TableData_PLog TD_PLogObj;
	
	
	public TableViewerModifier(Viewer viewer){
		this.med = Mediator.getInstance();
		this.tableViewer = viewer;
	}
	
	@Override
	public boolean canModify(Object element, String property) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		// TODO Auto-generated method stub
		try{
			if(this.tableViewer == med.getTableViewerSlabPlateInfo()){
				TD_SlabPlateInforObj = (TableData_SlabPlateInfo) element;
				
				if(TableColumnLabel.CL1_0.equals(property)){
					return TD_SlabPlateInforObj.getSTRIP_NO();
				}else if(TableColumnLabel.CL1_1.equals(property)) {
					return TD_SlabPlateInforObj.getSTHK();
				}else if(TableColumnLabel.CL1_2.equals(property)) {
					return TD_SlabPlateInforObj.getSWID();
				}else if(TableColumnLabel.CL1_4.equals(property)) {
					return TD_SlabPlateInforObj.getSWET();
				}else if(TableColumnLabel.CL1_5.equals(property)) {
					return TD_SlabPlateInforObj.getPTHK();
				}else if(TableColumnLabel.CL1_6.equals(property)) {
					return TD_SlabPlateInforObj.getPWID();
				}else if(TableColumnLabel.CL1_7.equals(property)) {
					return TD_SlabPlateInforObj.getPLEN();
				}else if(TableColumnLabel.CL1_8.equals(property)) {
					return TD_SlabPlateInforObj.getPWET();
				}
				
			}else if(this.tableViewer == med.getTableViewerVariable()){
				TD_VariableObj = (TableData_Variable) element;
				
				if(TableColumnLabel.CL2_0.equals(property)) {
					return TD_VariableObj.getVAR1();
				}else if(TableColumnLabel.CL2_1.equals(property)) {
					return TD_VariableObj.getVAR2();
				}else if(TableColumnLabel.CL2_2.equals(property)) {
					return TD_VariableObj.getVAR3();
				}else if(TableColumnLabel.CL2_3.equals(property)) {
					return TD_VariableObj.getVAR4();
				}else if(TableColumnLabel.CL2_4.equals(property)) {
					return TD_VariableObj.getVAR5();
				}else if(TableColumnLabel.CL2_5.equals(property)) {
					return TD_VariableObj.getVAR6();
				}else if(TableColumnLabel.CL2_6.equals(property)) {
					return TD_VariableObj.getVAR7();
				}else if(TableColumnLabel.CL2_7.equals(property)) {
					return TD_VariableObj.getVAR8();
				}else if(TableColumnLabel.CL2_8.equals(property)) {
					return TD_VariableObj.getVAR9();
				}else if(TableColumnLabel.CL2_9.equals(property)) {
					return TD_VariableObj.getVAR10();
				}else if(TableColumnLabel.CL2_10.equals(property)) {
					return TD_VariableObj.getVAR11();
				}else if(TableColumnLabel.CL2_11.equals(property)) {
					return TD_VariableObj.getVAR12();
				}else if(TableColumnLabel.CL2_12.equals(property)) {
					return TD_VariableObj.getVAR13();
				}else if(TableColumnLabel.CL2_13.equals(property)) {
					return TD_VariableObj.getVAR14();
				}else if(TableColumnLabel.CL2_14.equals(property)) {
					return TD_VariableObj.getVAR15();
				}else if(TableColumnLabel.CL2_15.equals(property)) {
					return TD_VariableObj.getVAR16();
				}
				
			}else if(this.tableViewer == med.getTableViewerPLog()){
				/*
				TD_PLogObj = (TableData_PLog) element;
				
				if(TableColumnLabel.CL3_0.equals(property)){
					return TD_PLogObj.getSTAND();
				}else if(TableColumnLabel.CL3_1.equals(property)){
					return TD_PLogObj.getBUR_TDIA();
				}else if(TableColumnLabel.CL3_2.equals(property)){
					return TD_PLogObj.getBUR_BDIA();
				}else if(TableColumnLabel.CL3_3.equals(property)){
					return TD_PLogObj.getWR_TDIA();
				}else if(TableColumnLabel.CL3_4.equals(property)){
					return TD_PLogObj.getWR_BDIA();
				}else if(TableColumnLabel.CL3_5.equals(property)){
					return TD_PLogObj.getWR_ICRN();
				}else if(TableColumnLabel.CL3_6.equals(property)){
					return TD_PLogObj.getENTRY_THK();
				}else if(TableColumnLabel.CL3_7.equals(property)){
					return TD_PLogObj.getEXIT_THK();
				}else if(TableColumnLabel.CL3_8.equals(property)){
					return TD_PLogObj.getPAS_LINE();
				}else if(TableColumnLabel.CL3_9.equals(property)){
					return TD_PLogObj.getROL_GAP();
				}else if(TableColumnLabel.CL3_10.equals(property)){
					return TD_PLogObj.getSTP_WID();
				}else if(TableColumnLabel.CL3_11.equals(property)){
					return TD_PLogObj.getSTP_LEN();
				}else if(TableColumnLabel.CL3_12.equals(property)){
					return TD_PLogObj.getENTRY_TEMP();
				}else if(TableColumnLabel.CL3_13.equals(property)){
					return TD_PLogObj.getEXIT_TEMP();
				}else if(TableColumnLabel.CL3_14.equals(property)){
					return TD_PLogObj.getFRCE();
				}else if(TableColumnLabel.CL3_15.equals(property)){
					return TD_PLogObj.getTORQ();
				}else if(TableColumnLabel.CL3_16.equals(property)){
					return TD_PLogObj.getSPEED();
				}else if(TableColumnLabel.CL3_17.equals(property)){
					return TD_PLogObj.getBEND();
				}else if(TableColumnLabel.CL3_18.equals(property)){
					return TD_PLogObj.getP_CROSS();
				}else if(TableColumnLabel.CL3_19.equals(property)){
					return TD_PLogObj.getTENS();
				}else if(TableColumnLabel.CL3_20.equals(property)){
					return TD_PLogObj.getROL_TIM();
				}else if(TableColumnLabel.CL3_21.equals(property)){
					return TD_PLogObj.getIDL_TIM();
				}else if(TableColumnLabel.CL3_22.equals(property)){
					return TD_PLogObj.getBUR_WEAR();
				}else if(TableColumnLabel.CL3_23.equals(property)){
					return TD_PLogObj.getWR_WEAR();
				}else if(TableColumnLabel.CL3_24.equals(property)){
					return TD_PLogObj.getWR_THRM();
				}
				// */	
			}
		}catch (Exception e){
			
		}
		
		this.tableViewer.refresh();
		
		return null;
	}

	@Override
	public void modify(Object element, String property, Object value) {
		// TODO Auto-generated method stub
		try{
			if(element instanceof Item){
				element = ((Item) element).getData();
			}
			
			if(this.tableViewer == med.getTableViewerSlabPlateInfo()){
				TD_SlabPlateInforObj = (TableData_SlabPlateInfo) element;
				
				if(TableColumnLabel.CL1_0.equals(property)){
					TD_SlabPlateInforObj.setSTRIP_NO((String) value);
				}else if(TableColumnLabel.CL1_1.equals(property)) {
					TD_SlabPlateInforObj.setSTHK((String) value);
				}else if(TableColumnLabel.CL1_2.equals(property)) {
					TD_SlabPlateInforObj.setSWID((String) value);
				}else if(TableColumnLabel.CL1_4.equals(property)) {
					TD_SlabPlateInforObj.setSWET((String) value);
				}else if(TableColumnLabel.CL1_5.equals(property)) {
					TD_SlabPlateInforObj.setPTHK((String) value);
				}else if(TableColumnLabel.CL1_6.equals(property)) {
					TD_SlabPlateInforObj.setPWID((String) value);
				}else if(TableColumnLabel.CL1_7.equals(property)) {
					TD_SlabPlateInforObj.setPLEN((String) value);
				}else if(TableColumnLabel.CL1_8.equals(property)) {
					TD_SlabPlateInforObj.setPWET((String) value);
				}
			}else if(this.tableViewer == med.getTableViewerVariable()){
				TD_VariableObj = (TableData_Variable) element;
				
				if(TableColumnLabel.CL2_0.equals(property)) {
					TD_VariableObj.setVAR1((String) value);
				}else if(TableColumnLabel.CL2_1.equals(property)) {
					TD_VariableObj.setVAR2((String) value);
				}else if(TableColumnLabel.CL2_2.equals(property)) {
					TD_VariableObj.setVAR3((String) value);
				}else if(TableColumnLabel.CL2_3.equals(property)) {
					TD_VariableObj.setVAR4((String) value);
				}else if(TableColumnLabel.CL2_4.equals(property)) {
					TD_VariableObj.setVAR5((String) value);
				}else if(TableColumnLabel.CL2_5.equals(property)) {
					TD_VariableObj.setVAR6((String) value);
				}else if(TableColumnLabel.CL2_6.equals(property)) {
					TD_VariableObj.setVAR7((String) value);
				}else if(TableColumnLabel.CL2_7.equals(property)) {
					TD_VariableObj.setVAR8((String) value);
				}else if(TableColumnLabel.CL2_8.equals(property)) {
					TD_VariableObj.setVAR9((String) value);
				}else if(TableColumnLabel.CL2_9.equals(property)) {
					TD_VariableObj.setVAR10((String) value);
				}else if(TableColumnLabel.CL2_10.equals(property)) {
					TD_VariableObj.setVAR11((String) value);
				}else if(TableColumnLabel.CL2_11.equals(property)) {
					TD_VariableObj.setVAR12((String) value);
				}else if(TableColumnLabel.CL2_12.equals(property)) {
					TD_VariableObj.setVAR13((String) value);
				}else if(TableColumnLabel.CL2_13.equals(property)) {
					TD_VariableObj.setVAR14((String) value);
				}else if(TableColumnLabel.CL2_14.equals(property)) {
					TD_VariableObj.setVAR15((String) value);
				}else if(TableColumnLabel.CL2_15.equals(property)) {
					TD_VariableObj.setVAR16((String) value);
				}
			}else if(this.tableViewer == med.getTableViewerPLog()){
				/*
				TD_PLogObj = (TableData_PLog) element;
				
				if(TableColumnLabel.CL3_0.equals(property)){
					TD_PLogObj.setSTAND((String) value);
				}else if(TableColumnLabel.CL3_1.equals(property)){
					TD_PLogObj.setBUR_TDIA((String) value);
				}else if(TableColumnLabel.CL3_2.equals(property)){
					TD_PLogObj.setBUR_BDIA((String) value);
				}else if(TableColumnLabel.CL3_3.equals(property)){
					TD_PLogObj.setWR_TDIA((String) value);
				}else if(TableColumnLabel.CL3_4.equals(property)){
					TD_PLogObj.setWR_BDIA((String) value);
				}else if(TableColumnLabel.CL3_5.equals(property)){
					TD_PLogObj.setWR_ICRN((String) value);
				}else if(TableColumnLabel.CL3_6.equals(property)){
					TD_PLogObj.setENTRY_THK((String) value);
				}else if(TableColumnLabel.CL3_7.equals(property)){
					TD_PLogObj.setEXIT_THK((String) value);
				}else if(TableColumnLabel.CL3_8.equals(property)){
					TD_PLogObj.setPAS_LINE((String) value);
				}else if(TableColumnLabel.CL3_9.equals(property)){
					TD_PLogObj.setROL_GAP((String) value);
				}else if(TableColumnLabel.CL3_10.equals(property)){
					TD_PLogObj.setSTP_WID((String) value);
				}else if(TableColumnLabel.CL3_11.equals(property)){
					TD_PLogObj.setSTP_LEN((String) value);
				}else if(TableColumnLabel.CL3_12.equals(property)){
					TD_PLogObj.setENTRY_TEMP((String) value);
				}else if(TableColumnLabel.CL3_13.equals(property)){
					TD_PLogObj.setEXIT_TEMP((String) value);
				}else if(TableColumnLabel.CL3_14.equals(property)){
					TD_PLogObj.setFRCE((String) value);
				}else if(TableColumnLabel.CL3_15.equals(property)){
					TD_PLogObj.setTORQ((String) value);
				}else if(TableColumnLabel.CL3_16.equals(property)){
					TD_PLogObj.setSPEED((String) value);
				}else if(TableColumnLabel.CL3_17.equals(property)){
					TD_PLogObj.setBEND((String) value);
				}else if(TableColumnLabel.CL3_18.equals(property)){
					TD_PLogObj.setP_CROSS((String) value);
				}else if(TableColumnLabel.CL3_19.equals(property)){
					TD_PLogObj.setTENS((String) value);
				}else if(TableColumnLabel.CL3_20.equals(property)){
					TD_PLogObj.setROL_TIM((String) value);
				}else if(TableColumnLabel.CL3_21.equals(property)){
					TD_PLogObj.setIDL_TIM((String) value);
				}else if(TableColumnLabel.CL3_22.equals(property)){
					TD_PLogObj.setBUR_WEAR((String) value);
				}else if(TableColumnLabel.CL3_23.equals(property)){
					TD_PLogObj.setWR_WEAR((String) value);
				}else if(TableColumnLabel.CL3_24.equals(property)){
					TD_PLogObj.setWR_THRM((String) value);
				}
				// */
			}
		}catch (Exception e){
			//log.error("Table Data Save Error");
		}
		
		this.tableViewer.refresh();
	}

}
