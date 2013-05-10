package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.ConstantRef;

public class ConstantRefPart extends AbstractConditionModelPart<ConstantRef>{

	@Override
	public String makeConditionString() {
		return getModel().getName();
	}

}
