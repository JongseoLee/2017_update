package com.js.ens.hrolling3d_ver2.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.hrolling3d_ver2.core.Mediator;

public class HandlerSpinner implements Listener {
	private Mediator med = Mediator.getInstance();
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getSpinnerDomain()){
			med.getC_spinnerDomain().execute();
		}else if(event.widget == med.getSpinnerThread()){
			med.getC_spinnerThread().execute();
		}
	}

}
