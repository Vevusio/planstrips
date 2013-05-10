package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.ContextRef;

public class ContextRefPart extends AbstractConditionModelPart<ContextRef>{

	@Override
	public String makeConditionString() {
		return getModel().getName();
	}

}
