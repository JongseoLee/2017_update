package com.js.ens.hrolling3d_ver3.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.js.ens.hrolling3d_ver3.core.Mediator;

public class HandlerButton implements Listener {
	private Mediator med = Mediator.getInstance();
	
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getBtnImportPLog()){
			med.getC_btnImportPLog().execute();
		}else if(event.widget == med.getBtnExportPLog()){
			med.getC_btnExportPLog().execute();
		}else if(event.widget == med.getBtnApply()){
			med.getC_btnApply().execute();
		}else if(event.widget == med.getBtnF1()){
			med.getC_btnF1().execute();
		}else if(event.widget == med.getBtnF2()){
			med.getC_btnF2().execute();
		}else if(event.widget == med.getBtnF3()){
			med.getC_btnF3().execute();
		}else if(event.widget == med.getBtnF4()){
			med.getC_btnF4().execute();
		}else if(event.widget == med.getBtnF5()){
			med.getC_btnF5().execute();
		}else if(event.widget == med.getBtnF6()){
			med.getC_btnF6().execute();
		}else if(event.widget == med.getBtnF7()){
			med.getC_btnF7().execute();
		}else if(event.widget == med.getBtnConstant1_YM()){
			med.getC_btnConstant1_YM().execute();
		}else if(event.widget == med.getBtnTable1_YM()){
			med.getC_btnTable1_YM().execute();
		}else if(event.widget == med.getBtnExplorerYoungsModulus()){
			med.getC_btnExplorerYoungsModulus().execute();
		}else if(event.widget == med.getBtnConstant4_FS()){
			med.getC_btnConstant4_FS().execute();
		}else if(event.widget == med.getBtnTable4_FS()){
			med.getC_btnTable4_FS().execute();
		}else if(event.widget == med.getBtnExplorerFlowStress()){
			med.getC_btnExplorerFlowStress().execute();
		}
		
		else if(event.widget == med.getBtnConstant2_TEC()){
			med.getC_btnConstant2_TEC().execute();
		}else if(event.widget == med.getBtnTable2_TEC()){
			med.getC_btnTable2_TEC().execute();
		}else if(event.widget == med.getBtnExplorerThermalExpansionCoefficient()){
			med.getC_btnExplorerThermalExpansionCoefficient().execute();
		}else if(event.widget == med.getBtnConstant3_PR()){
			med.getC_btnConstant3_PR().execute();
		}else if(event.widget == med.getBtnTable3_PR()){
			med.getC_btnTable3().execute();
		}else if(event.widget == med.getBtnExplorerPoissonsRatio()){
			med.getC_btnExplorerPoissonsRatio().execute();
		}else if(event.widget == med.getBtnParallelDDM()){
			med.getC_btnParallelDDM().execute();
		}else if(event.widget == med.getBtnParallelMultiThread()){
			med.getC_btnParallelMultiThread().execute();
		}
		
	}

}
