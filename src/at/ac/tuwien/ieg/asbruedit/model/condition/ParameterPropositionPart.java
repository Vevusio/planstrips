package at.ac.tuwien.ieg.asbruedit.model.condition;

import java.util.LinkedList;
import java.util.List;

import at.ac.tuwien.ieg.asbruedit.model.asbru.ParameterProposition;

public class ParameterPropositionPart extends AbstractConditionModelPart<ParameterProposition >{
	@Override
	public String makeConditionString() {
		StringBuilder result = new StringBuilder();
		result.append("{").append(getModel().getParameterName());
		ContextPart context = getChild(ContextPart.class);
		// context is mandatory, explicitly state null if not there
		result.append(" in ").append(context != null ? context.makeConditionString() : "null");
		TimeAnnotationPart timeAnnotation = getChild(TimeAnnotationPart.class);
		// time annotation is mandatory, explicitly state null if not there
		result.append(" @ ").append(timeAnnotation != null ? timeAnnotation.makeConditionString() : "null");
		result.append(" : ");
		if(isKnownParameter()) {
			result.append("known");
		}
		else if(isUnknownParameter()) {
			result.append("unknown");
		}
		else {
			ValueDescriptionPart valueDescriptionPart = getChild(ValueDescriptionPart.class);
			if(valueDescriptionPart != null) {
				result.append(valueDescriptionPart.makeConditionString());
			}
			else {
				ValueRangePart valueRangePart = getChild(ValueRangePart.class);
				if(valueRangePart != null) {
					result.append(valueRangePart.makeConditionString());
				}
			}
		}
		result.append("}");
		return result.toString();
	}
	
	private boolean isKnownParameter() {
		return getChild(IsKnownParameterPart.class) != null;
	}
	
	private boolean isUnknownParameter() {
		return getChild(IsUnknownParameterPart.class) != null;
	}

	@Override
	protected List<Object> getModelChildren() {
		LinkedList<Object> children = new LinkedList<Object>();
		Object child;
		
		child = getModel().getContext();
		if(child != null) {
			children.add(child);
		}
		child = getModel().getTimeAnnotation();
		if(child != null) {
			children.add(child);
		}
		
		children.addAll(getModel().getIsKnownParameterOrIsUnknownParameterOrValueDescriptionOrValueRange());
		
		return children;
	}
}
