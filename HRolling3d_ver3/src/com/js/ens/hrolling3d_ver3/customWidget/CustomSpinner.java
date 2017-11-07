package com.js.ens.hrolling3d_ver3.customWidget;

import org.eclipse.swt.widgets.Spinner;

import com.js.ens.hrolling3d_ver3.core.MainController;
import com.js.ens.hrolling3d_ver3.core.Mediator;

public class CustomSpinner implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Spinner spinner; 
	
	public CustomSpinner(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.SPINNER_spinnerDomain)){
			String num = med.getSpinnerDomain().getText();
			//MC.�޼ҵ� ȣ��() 
			MC.ChangedTextWidget(num, Mediator.SPINNER_spinnerDomain);
		}else if(widgetName.equals(Mediator.SPINNER_spinnerThread)){
			String num = med.getSpinnerThread().getText();
			//MC.�޼ҵ� ȣ��()
			MC.ChangedTextWidget(num, Mediator.SPINNER_spinnerThread);
		}
	}
	
	public void setCustomWidget_spinnerDomain(){
		this.spinner = med.getSpinnerDomain();
	}

	public void setCustomWidget_spinnerThread(){
		this.spinner = med.getSpinnerThread();
	}
	
}
