package at.ac.tuwien.ieg.asbruedit.model.condition;

import java.util.LinkedList;
import java.util.List;

import at.ac.tuwien.ieg.asbruedit.model.ComparisonType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Comparison;

public class ComparisonPart extends AbstractConditionModelPart<Comparison> {
	

	@Override
	public String makeConditionString() {
		LeftHandSidePart left = getChild(LeftHandSidePart.class);
		RightHandSidePart right = getChild(RightHandSidePart.class);
		ComparisonType type = getComparisonType();
		
		String result = null;
		if(type != null) {
			result = buildSeparatedConditionString(" " + type.getShorthand() + " ", left, right);
		}
		else {
			result = "[INVALID " + getModel().getClass().getSimpleName() + "]";
		}
		return result;
	}
	
	public ComparisonType getComparisonType() {
		return ComparisonType.fromString(getModel().getType());
	}

	@Override
	protected List<Object> getModelChildren() {
		LinkedList<Object> children = new LinkedList<Object>();
		children.add(getModel().getLeftHandSide());
		children.add(getModel().getRightHandSide());
		return children;
	}
}
