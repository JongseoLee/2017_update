package com.js.ens.hrolling3d_ver3.handler;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;

import com.js.ens.hrolling3d_ver3.core.Mediator;

public class HandlerTableViewer implements ISelectionChangedListener {
	private Mediator med = Mediator.getInstance();
	private TableViewer tableViewer;
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// TODO Auto-generated method stub
		try{
			tableViewer = (TableViewer) event.getSource();
			
			if(tableViewer == med.getTableViewerSlabPlateInfo()){
				med.getC_tableViewerSlabPlateInfo().execute();
				Table table = tableViewer.getTable();
				System.out.println("Handler - table1");
			}else if(tableViewer == med.getTableViewerVariable()){
				med.getC_tableViewerVariable().execute();
				Table table = tableViewer.getTable();
				System.out.println("Handler - table2");
			}else if(tableViewer == med.getTableViewerPLog()){
				med.getC_tableViewerPLog().execute();
				Table table = tableViewer.getTable();
				System.out.println("Handler - table3");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
