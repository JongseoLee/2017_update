package com.js.ens.hrolling3d_ver2.customWidget;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Table;

import com.js.ens.hrolling3d_ver2.core.Mediator;
import com.js.ens.hrolling3d_ver2.core.TableViewerModifier;

public class CustomTableViewer implements ICommand {
	private Mediator med;
	private String widgetName;
	private TableViewer tableViewer;
	private Table table;
		
	public CustomTableViewer(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.TABLEVIEWER_tableViewerSlabPlateInfo)){
			edit_tableViewerSlabPlateInfo();
			System.out.println("Custom-table1");
		}
		
		if(widgetName.equals(Mediator.TABLEVIEWER_tableViewerVariable)){
			edit_tableViewerVariable();
			System.out.println("Custom-table2");
		}
		
		if(widgetName.equals(Mediator.TABLEVIEWER_tableViewerPLog)){
			edit_tableViewerVariable();
			System.out.println("Custom-table3");
		}
	}
	
	public void setCustomWidget_tableViewerSlabPlateInfo(){
		this.tableViewer = med.getTableViewerSlabPlateInfo();
		this.table = this.tableViewer.getTable();
	}
	
	public void setCustomWidget_tableViewerVariable(){
		this.tableViewer = med.getTableViewerVariable();
		this.table = this.tableViewer.getTable();
	}
	
	public void setCustomWidget_tableViewerPLog(){
		this.tableViewer = med.getTableViewerPLog();
		this.table = this.tableViewer.getTable();
	}
	
	public void edit_tableViewerSlabPlateInfo(){
		System.out.println("Custom-table11");
		CellEditor [] editor = new CellEditor[9];
		editor[0] = new TextCellEditor(this.table);
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		editor[3] = new TextCellEditor(this.table);
		editor[4] = new TextCellEditor(this.table);
		editor[5] = new TextCellEditor(this.table);
		editor[6] = new TextCellEditor(this.table);
		editor[7] = new TextCellEditor(this.table);
		editor[8] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
	}
	
	public void edit_tableViewerVariable(){
		System.out.println("Custom-table22");
		CellEditor [] editor = new CellEditor[16];
		editor[0] = new TextCellEditor(this.table);
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		editor[3] = new TextCellEditor(this.table);
		editor[4] = new TextCellEditor(this.table);
		editor[5] = new TextCellEditor(this.table);
		editor[6] = new TextCellEditor(this.table);
		editor[7] = new TextCellEditor(this.table);
		editor[8] = new TextCellEditor(this.table);
		editor[9] = new TextCellEditor(this.table);
		editor[10] = new TextCellEditor(this.table);
		editor[11] = new TextCellEditor(this.table);
		editor[12] = new TextCellEditor(this.table);
		editor[13] = new TextCellEditor(this.table);
		editor[14] = new TextCellEditor(this.table);
		editor[15] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
	}
	
	public void edit_tableViewerPLog(){
		System.out.println("Custom-table33");
		/*
		CellEditor [] editor = new CellEditor[25];
		editor[0] = null;
		editor[1] = new TextCellEditor(this.table);
		editor[2] = new TextCellEditor(this.table);
		editor[3] = new TextCellEditor(this.table);
		editor[4] = new TextCellEditor(this.table);
		editor[5] = new TextCellEditor(this.table);
		editor[6] = new TextCellEditor(this.table);
		editor[7] = new TextCellEditor(this.table);
		editor[8] = new TextCellEditor(this.table);
		editor[9] = new TextCellEditor(this.table);
		editor[10] = new TextCellEditor(this.table);
		editor[11] = new TextCellEditor(this.table);
		editor[12] = new TextCellEditor(this.table);
		editor[13] = new TextCellEditor(this.table);
		editor[14] = new TextCellEditor(this.table);
		editor[15] = new TextCellEditor(this.table);
		editor[16] = new TextCellEditor(this.table);
		editor[17] = new TextCellEditor(this.table);
		editor[18] = new TextCellEditor(this.table);
		editor[19] = new TextCellEditor(this.table);
		editor[20] = new TextCellEditor(this.table);
		editor[21] = new TextCellEditor(this.table);
		editor[22] = new TextCellEditor(this.table);
		editor[23] = new TextCellEditor(this.table);
		editor[24] = new TextCellEditor(this.table);
		
		this.tableViewer.setCellModifier(new TableViewerModifier(this.tableViewer));
		this.tableViewer.setCellEditors(editor);
		*/
	}
	
	
}
