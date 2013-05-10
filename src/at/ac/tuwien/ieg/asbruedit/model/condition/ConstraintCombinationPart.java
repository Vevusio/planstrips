package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.ConstraintCombination;

public class ConstraintCombinationPart extends AbstractConditionModelPart<ConstraintCombination>{
	@Override
	public String makeConditionString() {
		return buildSeparatedConditionString(" " + getModel().getType() + " ", getChildren());
	}

}
