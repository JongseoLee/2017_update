package com.js.ens.hrolling3d_ver3.customWidget;

import org.eclipse.swt.widgets.TabFolder;

import com.js.ens.hrolling3d_ver3.core.MainController;
import com.js.ens.hrolling3d_ver3.core.Mediator;

public class CustomTabFolder implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private TabFolder tabFolder;
	
	public CustomTabFolder(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.TABFOLDER_tabFolder)){
			// tabItem id P Log , F1~F7
			//System.out.println(tabFolder.getSelection()[0].getText());
			// tabItem no 0 , 1
			//System.out.println(tabFolder.getSelectionIndex());
			if(tabFolder.getSelectionIndex() == 0){
				MC.ChangedTabFolder(0);
			}else if(tabFolder.getSelectionIndex() == 1){
				MC.ChangedTabFolder(1);
			}
		}
	}
	
	public void setCustomWidget_tabFolder(){
		this.tabFolder = med.getTabFolder();
		
	}

}
