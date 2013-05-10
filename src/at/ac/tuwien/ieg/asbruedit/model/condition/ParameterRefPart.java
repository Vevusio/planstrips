package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.ParameterRef;

public class ParameterRefPart extends AbstractConditionModelPart<ParameterRef>{
	@Override
	public String makeConditionString() {
		return getModel().getName();
	}
}
