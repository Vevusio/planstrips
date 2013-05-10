package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.Self;

public class SelfPart extends AbstractConditionModelPart<Self>{
	@Override
	public String makeConditionString() {
		return "self";
	}
}
