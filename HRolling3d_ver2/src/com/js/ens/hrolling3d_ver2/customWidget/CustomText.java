package com.js.ens.hrolling3d_ver2.customWidget;

import org.eclipse.swt.widgets.Text;

import com.js.ens.hrolling3d_ver2.core.MainController;
import com.js.ens.hrolling3d_ver2.core.Mediator;

public class CustomText implements ICommand {
	private Mediator med;
	private MainController MC;
	private String widgetName;
	private Text text;
	
	public CustomText(String widgetName, Mediator med) {
		// TODO Auto-generated constructor stub
		this.widgetName = widgetName;
		this.med = med;
		this.MC = MainController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		String value;
		if(widgetName.equals(Mediator.TEXT_textTopWRDiameter)){
			//MC.�޼��� ȣ��()
			value = med.getTextTopWRDiameter().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textTopWRDiameter);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRDiameter)){
			value = med.getTextBottomWRDiameter().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textBottomWRDiameter);
		}else if(widgetName.equals(Mediator.TEXT_textWRCrown)){
			value = med.getTextWRCrown().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textWRCrown);
		}else if(widgetName.equals(Mediator.TEXT_textWRLength)){
			value = med.getTextWRLength().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textWRLength);
		}else if(widgetName.equals(Mediator.TEXT_textWRMeshAngle)){
			value = med.getTextWRMeshAngle().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textWRMeshAngle);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURDiameter)){
			value = med.getTextTopBURDiameter().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textTopBURDiameter);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURDiameter)){
			value = med.getTextBottomBURDiameter().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textBottomBURDiameter);
		}else if(widgetName.equals(Mediator.TEXT_textBURLength)){
			value = med.getTextBURLength().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textBURLength);
		}else if(widgetName.equals(Mediator.TEXT_textBURMeshAngle)){
			value = med.getTextBURMeshAngle().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textBURMeshAngle);
		}else if(widgetName.equals(Mediator.TEXT_textThickness)){
			value = med.getTextThickness().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textThickness);
		}else if(widgetName.equals(Mediator.TEXT_textWidth)){
			value = med.getTextWidth().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textWidth);
		}else if(widgetName.equals(Mediator.TEXT_textLength)){
			value = med.getTextLength().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textLength);
		}else if(widgetName.equals(Mediator.TEXT_textEntryTemperature)){
			value = med.getTextEntryTemperature().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textEntryTemperature);
		}else if(widgetName.equals(Mediator.TEXT_textExitTemperature)){
			value = med.getTextExitTemperature().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textExitTemperature);
		}else if(widgetName.equals(Mediator.TEXT_textInitialPosition)){
			value = med.getTextInitialPosition().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textInitialPosition);
		}else if(widgetName.equals(Mediator.TEXT_textMeshLength)){
			value = med.getTextMeshLength().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textMeshLength);
		}else if(widgetName.equals(Mediator.TEXT_textThicknessMeshDivisions)){
			value = med.getTextThicknessMeshDivisions().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textThicknessMeshDivisions);
		}else if(widgetName.equals(Mediator.TEXT_textVelocity)){
			value = med.getTextVelocity().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textVelocity);	
		}else if(widgetName.equals(Mediator.TEXT_textRollGap)){
			value = med.getTextRollGap().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textRollGap);	
		}else if(widgetName.equals(Mediator.TEXT_textPassLine)){
			value = med.getTextPassLine().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textPassLine);	
		}else if(widgetName.equals(Mediator.TEXT_textPairCrossAngle)){
			value = med.getTextPairCrossAngle().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textPairCrossAngle);
		}else if(widgetName.equals(Mediator.TEXT_textBenderForce)){
			value = med.getTextBenderForce().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textBenderForce);
		}else if(widgetName.equals(Mediator.TEXT_textRollTorque)){
			value = med.getTextRollTorque().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textRollTorque);
		}else if(widgetName.equals(Mediator.TEXT_textTensionStress)){
			value = med.getTextTensionStress().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textTensionStress);
		}else if(widgetName.equals(Mediator.TEXT_textRollToPlateFrictCoef)){
			value = med.getTextRollToPlateFrictCoef().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textRollToPlateFrictCoef);
		}else if(widgetName.equals(Mediator.TEXT_textRollToRollFrictCoef)){
			value = med.getTextRollToRollFrictCoef().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textRollToRollFrictCoef);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioTopRoll)){
			value = med.getTextSpeedDifferentRatioTopRoll().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textSpeedDifferentRatioTopRoll);
		}else if(widgetName.equals(Mediator.TEXT_textSpeedDifferentRatioBottomRoll)){
			value = med.getTextSpeedDifferentRatioBottomRoll().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textSpeedDifferentRatioBottomRoll);
		}else if(widgetName.equals(Mediator.TEXT_textTopWRRotVel)){
			value = med.getTextTopWRRotVel().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textTopWRRotVel);
		}else if(widgetName.equals(Mediator.TEXT_textBottomWRRotVel)){
			value = med.getTextBottomWRRotVel().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textBottomWRRotVel);
		}else if(widgetName.equals(Mediator.TEXT_textTopBURRotVel)){
			value = med.getTextTopBURRotVel().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textTopBURRotVel);
		}else if(widgetName.equals(Mediator.TEXT_textBottomBURRotVel)){
			value = med.getTextBottomBURRotVel().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textBottomBURRotVel);
		}else if(widgetName.equals(Mediator.TEXT_textYoungsModulus)){
			value = med.getTextYoungsModulus().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textYoungsModulus);
		}else if(widgetName.equals(Mediator.TEXT_textFlowStress)){
			value = med.getTextFlowStress().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textFlowStress);
		}else if(widgetName.equals(Mediator.TEXT_textYieldStrength)){
			value = med.getTextYieldStrength().getText().trim();
			//System.out.println(value);
			MC.ChangedTextWidget(value, Mediator.TEXT_textYieldStrength);
		}else if(widgetName.equals(Mediator.TEXT_textTensileStrength)){
			value = med.getTextTensileStrength().getText().trim();
			MC.ChangedTextWidget(value, Mediator.TEXT_textTensileStrength);
		}else if(widgetName.equals(Mediator.Text_textElongation)){
			value = med.getTextElongation().getText().trim();
			MC.ChangedTextWidget(value, Mediator.Text_textElongation);
		}
		
		
		
		else if(widgetName.equals(Mediator.TEXT_textThermalExpansionCoefficient)){
			value = med.getTextThermalExpansionCoefficient().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textThermalExpansionCoefficient);
		}else if(widgetName.equals(Mediator.TEXT_textPoissonsRatio)){
			value = med.getTextPoissonsRatio().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textPoissonsRatio);
		}else if(widgetName.equals(Mediator.TEXT_textMassDensity)){
			value = med.getTextMassDensity().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textMassDensity);
		}else if(widgetName.equals(Mediator.TEXT_textAnalysisTime)){
			value = med.getTextAnalysisTime().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textAnalysisTime);
		}else if(widgetName.equals(Mediator.TEXT_textNoOfInc)){
			value = med.getTextNoOfInc().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textNoOfInc);
		}else if(widgetName.equals(Mediator.TEXT_textPostWritingFrequency)){
			value = med.getTextPostWritingFrequency().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textPostWritingFrequency);
		}else if(widgetName.equals(Mediator.TEXT_textTimeIncrement)){
			value = med.getTextTimeIncrement().getText().trim();
			MC.ChangedTextWidget(value,Mediator.TEXT_textTimeIncrement);
		}
	}
	
	public void setCustomWidget_textTopWRDiameter(){
		this.text = med.getTextTopWRDiameter();
	}
	public void setCustomWidget_textBottomWRDiameter(){
		this.text = med.getTextBottomWRDiameter();
	}
	public void setCustomWidget_textWRCrown(){
		this.text = med.getTextWRCrown();
	}
	public void setCustomWidget_textWRLength(){
		this.text = med.getTextWRLength();
	}
	public void setCustomWidget_textWRMeshAngle(){
		this.text = med.getTextWRMeshAngle();
	}
	public void setCustomWidget_textTopBURDiameter(){
		this.text = med.getTextTopBURDiameter();
	}
	public void setCustomWidget_textBottomBURDiameter(){
		this.text = med.getTextBottomBURDiameter();
	}
	public void setCustomWidget_textBURLength(){
		this.text = med.getTextBURLength();
	}
	public void setCustomWidget_textBURMeshAngle(){
		this.text = med.getTextBURMeshAngle();
	}
	public void setCustomWidget_textThickness(){
		this.text = med.getTextThickness();
	}
	public void setCustomWidget_textWidth(){
		this.text = med.getTextWidth();
	}
	public void setCustomWidget_textLength(){
		this.text = med.getTextLength();
	}
	public void setCustomWidget_textEntryTemperature(){
		this.text = med.getTextEntryTemperature();
	}
	public void setCustomWidget_textExitTemperature(){
		this.text = med.getTextExitTemperature(); 
	}
	public void setCustomWidget_textInitialPosition(){
		this.text = med.getTextInitialPosition();
	}
	public void setCustomWidget_textMeshLength(){
		this.text = med.getTextMeshLength();
	}
	public void setCustomWidget_textThicknessMeshDivisions(){
		this.text = med.getTextThicknessMeshDivisions();
	}
	public void setCustomWidget_textVelocity(){
		this.text = med.getTextVelocity();
	}
	public void setCustomWidget_textRollGap(){
		this.text = med.getTextRollGap();
	}
	public void setCustomWidget_textPassLine(){
		this.text = med.getTextPassLine();
	}
	public void setCustomWidget_textPairCrossAngle(){
		this.text = med.getTextPairCrossAngle();
	}
	public void setCustomWidget_textBenderForce(){
		this.text = med.getTextBenderForce();
	}
	public void setCustomWidget_textRollTorque(){
		this.text = med.getTextRollTorque();
	}
	public void setCustomWidget_textTensionStress(){
		this.text = med.getTextTensionStress(); 
	}
	public void setCustomWidget_textRollToPlateFrictCoef(){
		this.text = med.getTextRollToPlateFrictCoef();
	}
	public void setCustomWidget_textRollToRollFrictCoef(){
		this.text = med.getTextRollToRollFrictCoef();
	}
	public void setCustomWidget_textSpeedDifferentRatioTopRoll(){
		this.text = med.getTextSpeedDifferentRatioTopRoll();
	}
	public void setCustomWidget_textSpeedDifferentRatioBottomRoll(){
		this.text = med.getTextSpeedDifferentRatioBottomRoll();
	}
	public void setCustomWidget_textTopWRRotVel(){
		this.text = med.getTextTopWRRotVel();
	}
	public void setCustomWidget_textBottomWRRotVel(){
		this.text = med.getTextBottomWRRotVel();
	}
	public void setCustomWidget_textTopBURRotVel(){
		this.text = med.getTextTopBURRotVel();
	}
	public void setCustomWidget_textBottomBURRotVel(){
		this.text = med.getTextBottomBURRotVel();
	}
	
	
	public void setCustomWidget_textYoungsModulus(){
		this.text = med.getTextYoungsModulus();
	}
	public void setCustomWidget_textFlowStress(){
		this.text = med.getTextFlowStress();
	}
	public void setCustomWidget_textYieldStrength(){
		this.text = med.getTextYieldStrength();
	}
	public void setCustomWidget_textTensileStrength(){
		this.text = med.getTextTensileStrength();
	}
	public void setCustomWidget_textElongation(){
		this.text = med.getTextElongation();
	}
	
	public void setCustomWidget_textThermalExpansionCoefficient(){
		this.text = med.getTextThermalExpansionCoefficient();
	}
	public void setCustomWidget_textPoissonsRatio(){
		this.text = med.getTextPoissonsRatio();
	}
	public void setCustomWidget_textMassDensity(){
		this.text = med.getTextMassDensity();
	}
	public void setCustomWidget_textAnalysisTime(){
		this.text = med.getTextAnalysisTime();
	}
	public void setCustomWidget_textNoOfInc(){
		this.text = med.getTextNoOfInc();
	}
	public void setCustomWidget_textPostWritingFrequency(){
		this.text = med.getTextPostWritingFrequency();
	}
	public void setCustomWidget_textTimeIncrement(){
		this.text = med.getTextTimeIncrement();
	}
	

}
