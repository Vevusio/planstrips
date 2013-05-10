package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.QualitativeConstant;

public class QualitativeConstantPart extends AbstractConditionModelPart<QualitativeConstant>{
	@Override
	public String makeConditionString() {
		return getModel().getValue();
	}
}
