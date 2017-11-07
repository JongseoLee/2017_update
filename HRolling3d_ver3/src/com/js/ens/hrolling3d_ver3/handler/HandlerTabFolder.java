package com.js.ens.hrolling3d_ver3.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.hrolling3d_ver3.core.Mediator;

public class HandlerTabFolder implements Listener {
	private Mediator med = Mediator.getInstance();
	
	public HandlerTabFolder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getTabFolder()){
			med.getC_tabFolder().execute();
		}
	}

}
