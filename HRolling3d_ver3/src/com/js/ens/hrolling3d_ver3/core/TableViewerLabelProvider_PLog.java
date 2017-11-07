package com.js.ens.hrolling3d_ver3.core;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.js.ens.hrolling3d_ver3.core.tableDatas.TableData_PLog;
import com.js.ens.hrolling3d_ver3.core.tableDatas.TableRowData;

public class TableViewerLabelProvider_PLog extends LabelProvider implements
		ITableLabelProvider {

	public TableViewerLabelProvider_PLog() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		TableRowData obj = (TableRowData) element;
		
		switch(columnIndex){
		case 0:
			return obj.getName();
		case 1:
			return obj.getF1Value();
		case 2:
			return obj.getF2Value();
		case 3:
			return obj.getF3Value();
		case 4:
			return obj.getF4Value();
		case 5:
			return obj.getF5Value();
		case 6:
			return obj.getF6Value();
		case 7:
			return obj.getF7Value();
		}
		
		return "";
		/*
		TableData_PLog TD_PLogObj = (TableData_PLog) element;
		switch(columnIndex){
		case 0:
			return TD_PLogObj.getSTAND();
		case 1:
			return TD_PLogObj.getBUR_TDIA();
		case 2:
			return TD_PLogObj.getBUR_BDIA();
		case 3:
			return TD_PLogObj.getWR_TDIA();
		case 4:
			return TD_PLogObj.getWR_BDIA();
		case 5:
			return TD_PLogObj.getWR_ICRN();
		case 6:
			return TD_PLogObj.getENTRY_THK();
		case 7:
			return TD_PLogObj.getEXIT_THK();
		case 8:
			return TD_PLogObj.getPAS_LINE();
		case 9:
			return TD_PLogObj.getROL_GAP();
		case 10:
			return TD_PLogObj.getSTP_WID();
		case 11:
			return TD_PLogObj.getSTP_LEN();
		case 12:
			return TD_PLogObj.getENTRY_TEMP();
		case 13:
			return TD_PLogObj.getEXIT_TEMP();
		case 14:
			return TD_PLogObj.getFRCE();
		case 15:
			return TD_PLogObj.getTORQ();
		case 16:
			return TD_PLogObj.getSPEED();
		case 17:
			return TD_PLogObj.getBEND();
		case 18:
			return TD_PLogObj.getP_CROSS();
		case 19:
			return TD_PLogObj.getTENS();
		case 20:
			return TD_PLogObj.getROL_TIM();
		case 21:
			return TD_PLogObj.getIDL_TIM();
		case 22:
			return TD_PLogObj.getBUR_WEAR();
		case 23:
			return TD_PLogObj.getWR_WEAR();
		case 24:
			return TD_PLogObj.getWR_THRM();
		}
		*/
		
	}

}
