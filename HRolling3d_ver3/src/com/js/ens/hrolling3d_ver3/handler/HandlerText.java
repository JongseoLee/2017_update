package com.js.ens.hrolling3d_ver3.handler;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

import com.js.ens.hrolling3d_ver3.core.Mediator;

public class HandlerText implements Listener {
	private Mediator med = Mediator.getInstance();
	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		if(event.widget == med.getTextTopWRDiameter()){
			med.getC_textTopWRDiameter().execute();
		}else if(event.widget == med.getTextBottomWRDiameter()){
			med.getC_textBottomWRDiameter().execute();
		}else if(event.widget == med.getTextWRCrown()){
			med.getC_textWRCrown().execute();
		}else if(event.widget == med.getTextWRLength()){
			med.getC_textWRLength().execute();
		}else if(event.widget == med.getTextWRMeshAngle()){
			med.getC_textWRMeshAngle().execute();
		}else if(event.widget == med.getTextTopBURDiameter()){
			med.getC_textTopBURDiameter().execute();
		}else if(event.widget == med.getTextBottomBURDiameter()){
			med.getC_textBottomBURDiameter().execute();
		}else if(event.widget == med.getTextBURLength()){
			med.getC_textBURLength().execute();
		}else if(event.widget == med.getTextBURMeshAngle()){
			med.getC_textBURMeshAngle().execute();
		}else if(event.widget == med.getTextThickness()){
			med.getC_textThickness().execute();
		}else if(event.widget == med.getTextWidth()){
			med.getC_textWidth().execute();
		}else if(event.widget == med.getTextLength()){
			med.getC_textLength().execute();
		}else if(event.widget == med.getTextEntryTemperature()){
			med.getC_textEntryTemperature().execute();
		}else if(event.widget == med.getTextExitTemperature()){
			med.getC_textExitTemperature().execute();
		}else if(event.widget == med.getTextInitialPosition()){
			med.getC_textInitialPosition().execute();
		}else if(event.widget == med.getTextMeshLength()){
			med.getC_textMeshLength().execute();
		}else if(event.widget == med.getTextThicknessMeshDivisions()){
			med.getC_textThicknessMeshDivisions().execute();
		}else if(event.widget == med.getTextVelocity()){
			med.getC_textVelocity().execute();
		}else if(event.widget == med.getTextRollGap()){
			med.getC_textRollGap().execute();
		}else if(event.widget == med.getTextPassLine()){
			med.getC_textPassLine().execute();
		}else if(event.widget == med.getTextPairCrossAngle()){
			med.getC_textPairCrossAngle().execute();
		}else if(event.widget == med.getTextPairCrossAngle()){
			med.getC_textPairCrossAngle().execute();
		}else if(event.widget == med.getTextBenderForce()){
			med.getC_textBenderForce().execute();
		}else if(event.widget == med.getTextRollTorque()){
			med.getC_textRollTorque().execute();
		}else if(event.widget == med.getTextTensionStress()){
			med.getC_textTensionStress().execute();
		}else if(event.widget == med.getTextRollToPlateFrictCoef()){
			med.getC_textRollToPlateFrictCoef().execute();
		}else if(event.widget == med.getTextRollToRollFrictCoef()){
			med.getC_textRollToRollFrictCoef().execute();
		}else if(event.widget == med.getTextSpeedDifferentRatioTopRoll()){
			med.getC_textSpeedDifferentRatioTopRoll().execute();
		}else if(event.widget == med.getTextSpeedDifferentRatioBottomRoll()){
			med.getC_textSpeedDifferentRatioBottomRoll().execute();
		}else if(event.widget == med.getTextTopWRRotVel()){
			med.getC_textTopWRRotVel().execute();
		}else if(event.widget == med.getTextBottomWRRotVel()){
			med.getC_textBottomWRRotVel().execute();
		}else if(event.widget == med.getTextTopBURRotVel()){
			med.getC_textTopBURRotVel().execute();
		}else if(event.widget == med.getTextBottomBURRotVel()){
			med.getC_textBottomBURRotVel().execute();
		}else if(event.widget == med.getTextYoungsModulus()){
			med.getC_textYoungsModulus().execute();
		}else if(event.widget == med.getTextFlowStress()){
			med.getC_textFlowStress().execute();
		}else if(event.widget == med.getTextYieldStrength()){
			med.getC_textYieldStrength().execute();
		}else if(event.widget == med.getTextTensileStrength()){
			med.getC_textTensileStrength().execute();
		}else if(event.widget == med.getTextElongation()){
			med.getC_textElongation().execute();
		}
		
		
		else if(event.widget == med.getTextThermalExpansionCoefficient()){
			med.getC_textThermalExpansionCoefficient().execute();
		}else if(event.widget == med.getTextPoissonsRatio()){
			med.getC_textPoissonsRatio().execute();
		}else if(event.widget == med.getTextMassDensity()){
			med.getC_textMassDensity().execute();
		}else if(event.widget == med.getTextAnalysisTime()){
			med.getC_textAnalysisTime().execute();
		}else if(event.widget == med.getTextNoOfInc()){
			med.getC_textNoOfInc().execute();
		}else if(event.widget == med.getTextPostWritingFrequency()){
			med.getC_textPostWritingFrequency().execute();
		}else if(event.widget == med.getTextTimeIncrement()){
			med.getC_textTimeIncrement().execute();
		}
	}

}
