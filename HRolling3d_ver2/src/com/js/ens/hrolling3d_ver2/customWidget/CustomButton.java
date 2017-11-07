package com.js.ens.hrolling3d_ver2.customWidget;

import org.eclipse.swt.widgets.Button;

import com.js.ens.hrolling3d_ver2.core.InitValue;
import com.js.ens.hrolling3d_ver2.core.MainController;
import com.js.ens.hrolling3d_ver2.core.Mediator;

public class CustomButton implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Button button;
	
	
	public CustomButton(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		//InitValue obj = new InitValue();
		//obj.readInitValueFile();
		// TODO Auto-generated method stub
		if(widgetName.equals(Mediator.BUTTON_btnImportPLog)){
			MC.ImportPLog();
		}else if(widgetName.equals(Mediator.BUTTON_btnExportPLog)){
			MC.ExportPLog();
		}else if(widgetName.equals(Mediator.BUTTON_btnApply)){
			MC.Apply();
		}else if(widgetName.equals(Mediator.BUTTON_btnF1)){
			if(med.getBtnF1().getSelection())
				MC.ChangedSTANDValue("F1");
		}else if(widgetName.equals(Mediator.BUTTON_btnF2)){
			if(med.getBtnF2().getSelection())
				MC.ChangedSTANDValue("F2");
		}else if(widgetName.equals(Mediator.BUTTON_btnF3)){
			if(med.getBtnF3().getSelection())
				MC.ChangedSTANDValue("F3");
		}else if(widgetName.equals(Mediator.BUTTON_btnF4)){
			if(med.getBtnF4().getSelection())
				MC.ChangedSTANDValue("F4");
		}else if(widgetName.equals(Mediator.BUTTON_btnF5)){
			if(med.getBtnF5().getSelection())
				MC.ChangedSTANDValue("F5");
		}else if(widgetName.equals(Mediator.BUTTON_btnF6)){
			if(med.getBtnF6().getSelection())
				MC.ChangedSTANDValue("F6");
		}else if(widgetName.equals(Mediator.BUTTON_btnF7)){
			if(med.getBtnF7().getSelection())
				MC.ChangedSTANDValue("F7");
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant1_YM)){
			if(med.getBtnConstant1_YM().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnConstant1_YM);
				MC.ChangedMaterialParameter("true",Mediator.BUTTON_btnConstant1_YM);
				med.getBtnExplorerYoungsModulus().setEnabled(false);
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnConstant1_YM);
				MC.ChangedMaterialParameter("false",Mediator.BUTTON_btnConstant1_YM);
				med.getBtnExplorerYoungsModulus().setEnabled(true);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable1_YM)){
			if(med.getBtnTable1_YM().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnTable1_YM);
				MC.ChangedMaterialParameter("true",Mediator.BUTTON_btnTable1_YM);
				med.getBtnExplorerYoungsModulus().setEnabled(true);
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnTable1_YM);
				MC.ChangedMaterialParameter("false",Mediator.BUTTON_btnTable1_YM);
				med.getBtnExplorerYoungsModulus().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnExplorerYoungsModulus)){
			MC.Explorer_YoungsModulus();
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant4_FS)){
			//System.out.println("Button action1");
			if(med.getBtnConstant4_FS().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnConstant4_FS);
				MC.ChangedMaterialParameter("true", Mediator.BUTTON_btnConstant4_FS);
				med.getBtnExplorerFlowStress().setEnabled(false);
				
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnConstant4_FS);
				MC.ChangedMaterialParameter("false", Mediator.BUTTON_btnConstant4_FS);
				med.getBtnExplorerFlowStress().setEnabled(true);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable4_FS)){
			//System.out.println("Button action2");
			if(med.getBtnTable4_FS().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnTable4_FS);
				MC.ChangedMaterialParameter("true", Mediator.BUTTON_btnTable4_FS);
				med.getBtnExplorerFlowStress().setEnabled(true);
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnTable4_FS);
				MC.ChangedMaterialParameter("false", Mediator.BUTTON_btnTable4_FS);
				med.getBtnExplorerFlowStress().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnExplorerFlowStress)){
			MC.Explorer_FlowStress();
		}
		
		
		
		else if(widgetName.equals(Mediator.BUTTON_btnConstant2_TEC)){
			if(med.getBtnConstant2_TEC().getSelection()){
				//System.out.println("Constant - Click true");
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnConstant2_TEC);
				MC.ChangedMaterialParameter("true", Mediator.BUTTON_btnConstant2_TEC);
				med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
			}else{
				//System.out.println("Constant - unClick true");
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnConstant2_TEC);
				MC.ChangedMaterialParameter("false", Mediator.BUTTON_btnConstant2_TEC);
				med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable2_TEC)){
			if(med.getBtnTable2_TEC().getSelection()){
				//System.out.println("Table - Click true");
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnTable2_TEC);
				MC.ChangedMaterialParameter("true", Mediator.BUTTON_btnTable2_TEC);
				med.getBtnExplorerThermalExpansionCoefficient().setEnabled(true);
			}else{
				//System.out.println("Table - unClick true");
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnTable2_TEC);
				MC.ChangedMaterialParameter("false", Mediator.BUTTON_btnTable2_TEC);
				med.getBtnExplorerThermalExpansionCoefficient().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.Button_btnExplorerThermalExpansionCoefficient)){
			MC.Explorer_ThermalExpansionCoefficient();
		}else if(widgetName.equals(Mediator.BUTTON_btnConstant3_PR)){
			if(med.getBtnConstant3_PR().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnConstant3_PR);
				MC.ChangedMaterialParameter("true", Mediator.BUTTON_btnConstant3_PR);
				med.getBtnExplorerPoissonsRatio().setEnabled(false);
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnConstant3_PR);
				MC.ChangedMaterialParameter("false", Mediator.BUTTON_btnConstant3_PR);
				med.getBtnExplorerPoissonsRatio().setEnabled(true);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnTable3_PR)){
			if(med.getBtnTable3_PR().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnTable3_PR);
				MC.ChangedMaterialParameter("true", Mediator.BUTTON_btnTable3_PR);
				med.getBtnExplorerPoissonsRatio().setEnabled(true);
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnTable3_PR);
				MC.ChangedMaterialParameter("false", Mediator.BUTTON_btnTable3_PR);
				med.getBtnExplorerPoissonsRatio().setEnabled(false);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnExplorerPoissonsRatio)){
			MC.Explorer_PoissonsRatio();
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelDDM)){
			if(med.getBtnParallelDDM().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnParallelDDM);
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnParallelDDM);
			}
		}else if(widgetName.equals(Mediator.BUTTON_btnParallelMultiThread)){
			if(med.getBtnParallelMultiThread().getSelection()){
				MC.ChangedTextWidget("true", Mediator.BUTTON_btnParallelMultiThread);
			}else{
				MC.ChangedTextWidget("false", Mediator.BUTTON_btnParallelMultiThread);
			}
		}
		
	}
	
	
	public void setCustomWidget_btnImportPLog(){
		this.button = med.getBtnImportPLog();
	}
	
	public void setCustomWidget_btnExportPLog(){
		this.button = med.getBtnExportPLog();
	}
	
	public void setCustomWidget_btnApply(){
		this.button = med.getBtnApply();
	}
	
	public void setCustomWidget_btnF1(){
		this.button = med.getBtnF1();
	}

	public void setCustomWidget_btnF2(){
		this.button = med.getBtnF2();
	}
	
	public void setCustomWidget_btnF3(){
		this.button = med.getBtnF3();
	}
	
	public void setCustomWidget_btnF4(){
		this.button = med.getBtnF4();
	}
	
	public void setCustomWidget_btnF5(){
		this.button = med.getBtnF5();
	}
	
	public void setCustomWidget_btnF6(){
		this.button = med.getBtnF6();
	}
	
	public void setCustomWidget_btnF7(){
		this.button = med.getBtnF7();
	}
	
	public void setCustomWidget_btnConstant1_YM(){
		this.button = med.getBtnConstant1_YM();
	}
	
	public void setCustomWidget_btnTable1_YM(){
		this.button = med.getBtnTable1_YM();
	}
	
	public void setCustomWidget_btnExplorerYoungsModulus(){
		this.button = med.getBtnExplorerYoungsModulus();
	}
	
	public void setCustomWidget_btnConstant4_FS(){
		this.button = med.getBtnConstant4_FS();
	}
	
	public void setCustomWidget_btnTable4_FS(){
		this.button = med.getBtnTable4_FS();
	}
	
	public void setCustomWidget_btnExplorerFlowStress(){
		this.button = med.getBtnExplorerFlowStress();
	}
	
	public void setCustomWidget_btnConstant2_TEC(){
		this.button = med.getBtnConstant2_TEC();
	}
	
	public void setCustomWidget_btnTable2_TEC(){
		this.button = med.getBtnTable2_TEC();
	}
	
	public void setCustomWidget_btnExplorerThermalExpansionCoefficient(){
		this.button = med.getBtnExplorerThermalExpansionCoefficient();
	}
	
	public void setCustomWidget_btnConstant3_PR(){
		this.button = med.getBtnConstant3_PR();
	}
	
	public void setCustomWidget_btnTable3_PR(){
		this.button = med.getBtnTable3_PR();
	}
	
	public void setCustomWidget_btnExplorerPoissonsRatio(){
		this.button = med.getBtnExplorerPoissonsRatio();
	}
	
	public void setCustomWidget_btnParallelDDM(){
		this.button = med.getBtnParallelDDM();
	}
	
	public void setCustomWidget_btnParallelMultiThread(){
		this.button = med.getBtnParallelMultiThread();
	}
	
}
