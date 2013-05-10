package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.Always;

public class AlwaysPart extends AbstractConditionModelPart<Always>{

	@Override
	public String makeConditionString() {
		return "always";
	}

}
