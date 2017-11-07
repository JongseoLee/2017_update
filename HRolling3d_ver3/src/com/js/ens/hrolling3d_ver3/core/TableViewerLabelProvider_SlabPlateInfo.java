package com.js.ens.hrolling3d_ver3.core;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.js.ens.hrolling3d_ver3.core.tableDatas.TableData_SlabPlateInfo;

public class TableViewerLabelProvider_SlabPlateInfo extends LabelProvider
		implements ITableLabelProvider {

	public TableViewerLabelProvider_SlabPlateInfo() {
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
		TableData_SlabPlateInfo TD_SlabPlateInforObj = (TableData_SlabPlateInfo) element;
		
		switch(columnIndex){
		case 0:
			return TD_SlabPlateInforObj.getSTRIP_NO();
		case 1:
			return TD_SlabPlateInforObj.getSTHK();
		case 2:
			return TD_SlabPlateInforObj.getSWID();
		case 3:
			return TD_SlabPlateInforObj.getSLEN();
		case 4:
			return TD_SlabPlateInforObj.getSWET();
		case 5:
			return TD_SlabPlateInforObj.getPTHK();
		case 6:
			return TD_SlabPlateInforObj.getPWID();
		case 7:
			return TD_SlabPlateInforObj.getPLEN();
		case 8:
			return TD_SlabPlateInforObj.getPWET();
		}
		return "";
	}

}
