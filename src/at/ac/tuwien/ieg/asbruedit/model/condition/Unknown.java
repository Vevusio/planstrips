package at.ac.tuwien.ieg.asbruedit.model.condition;

import java.util.Collections;
import java.util.List;

public class Unknown extends AbstractConditionModelPart<Object> {

	
	
	@Override
	public String makeConditionString() {
		return "(?" + getModel().getClass().getSimpleName() + "?)";
	}

	@Override
	protected List<Object> getModelChildren() {
		return Collections.EMPTY_LIST;
	}
	
}
