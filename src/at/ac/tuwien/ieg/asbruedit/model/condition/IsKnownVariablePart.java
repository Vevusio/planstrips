package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.IsKnownVariable;

public class IsKnownVariablePart extends AbstractConditionModelPart<IsKnownVariable>{

	@Override
	public String makeConditionString() {
		return getModel().getName();
	}

}
