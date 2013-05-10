package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.IsUnknownVariable;

public class IsUnknownVariablePart extends AbstractConditionModelPart<IsUnknownVariable>{

	@Override
	public String makeConditionString() {
		return getModel().getName();
	}
	
}
