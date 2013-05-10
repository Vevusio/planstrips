package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.model.ComparisonType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ValueDescription;

public class ValueDescriptionPart extends AbstractConditionModelPart<ValueDescription> {

	@Override
	public String makeConditionString() {
		StringBuilder result = new StringBuilder();
		//shouldn't be more than 1 child here
		result.append(getComparisonType().getShorthand()).append(" ").append(buildSeparatedConditionString("(?) ", getChildren()));
		return result.toString();
	}

	public ComparisonType getComparisonType() {
		return ComparisonType.fromString(getModel().getType());
	}
}
