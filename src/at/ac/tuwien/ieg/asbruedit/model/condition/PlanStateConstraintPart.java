package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanStateConstraint;

public class PlanStateConstraintPart extends AbstractConditionModelPart<PlanStateConstraint>{
	@Override
	public String makeConditionString() {
		StringBuilder result = new StringBuilder();
		result.append("{plan: ");
		//shouldn't be more than 1 child here
		result.append(buildSeparatedConditionString("(?) ", getChildren()));
		result.append(" ").append(getModel().getState()).append("}");
		return result.toString();
	}
	
}
