package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.NumericalConstant;
import at.ac.tuwien.ieg.asbruedit.model.asbru.QualitativeConstant;

public class NumericalConstantPart extends AbstractConditionModelPart<NumericalConstant>{
	@Override
	public String makeConditionString() {
		return getModel().getValue();
	}
}
