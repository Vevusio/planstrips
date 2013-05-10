package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.Any;

public class AnyPart extends AbstractConditionModelPart<Any>{

	@Override
	public String makeConditionString() {
		return "any";
	}

}
