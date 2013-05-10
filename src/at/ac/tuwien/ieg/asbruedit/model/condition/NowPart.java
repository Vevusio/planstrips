package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.Now;

public class NowPart extends AbstractConditionModelPart<Now>{

	@Override
	public String makeConditionString() {
		return "now";
	}

}
