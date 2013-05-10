package at.ac.tuwien.ieg.asbruedit.model.condition;

import java.util.LinkedList;
import java.util.List;

import at.ac.tuwien.ieg.asbruedit.model.asbru.ValueRange;

public class ValueRangePart extends AbstractConditionModelPart<ValueRange>{
	@Override
	public String makeConditionString() {
		StringBuilder result = new StringBuilder();
		LowerBoundPart lowerBound = getChild(LowerBoundPart.class);
		UpperBoundPart upperBound = getChild(UpperBoundPart.class);
		result.append("[").append(buildSeparatedConditionString(", ", lowerBound, upperBound)).append("]");
		return result.toString();
	}

	@Override
	protected List<Object> getModelChildren() {
		LinkedList<Object> children = new LinkedList<Object>();
		Object child;
		child = getModel().getLowerBound();
		if(child != null) {
			children.add(child);
		}
		child = getModel().getUpperBound();
		if(child != null) {
			children.add(child);
		}
		return children;
	}

}
