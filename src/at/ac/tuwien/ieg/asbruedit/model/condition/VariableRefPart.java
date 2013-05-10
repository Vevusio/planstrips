package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.VariableRef;

public class VariableRefPart extends AbstractConditionModelPart<VariableRef>{

	@Override
	public String makeConditionString() {
		return getModel().getName();
	}

}
