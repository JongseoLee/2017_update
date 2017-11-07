package com.js.ens.hrolling3d_ver3.core;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.js.ens.hrolling3d_ver3.core.tableDatas.TableData_Variable;

public class TableViewerLabelProvider_Variable extends LabelProvider implements
		ITableLabelProvider {

	public TableViewerLabelProvider_Variable() {
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
		TableData_Variable TD_VariableObj = (TableData_Variable) element;
		
		switch(columnIndex){
		case 0:
			return TD_VariableObj.getVAR1();
		case 1:
			return TD_VariableObj.getVAR1();
		case 2:
			return TD_VariableObj.getVAR2();
		case 3:
			return TD_VariableObj.getVAR3();
		case 4:
			return TD_VariableObj.getVAR4();
		case 5:
			return TD_VariableObj.getVAR5();
		case 6:
			return TD_VariableObj.getVAR6();
		case 7:
			return TD_VariableObj.getVAR7();
		case 8:
			return TD_VariableObj.getVAR8();
		case 9:
			return TD_VariableObj.getVAR9();
		case 10:
			return TD_VariableObj.getVAR10();
		case 11:
			return TD_VariableObj.getVAR11();
		case 12:
			return TD_VariableObj.getVAR12();
		case 13:
			return TD_VariableObj.getVAR13();
		case 14:
			return TD_VariableObj.getVAR14();
		case 15:
			return TD_VariableObj.getVAR15();
		}
		return "";
	}

}
