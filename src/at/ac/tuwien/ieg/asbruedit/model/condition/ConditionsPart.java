package at.ac.tuwien.ieg.asbruedit.model.condition;

import java.util.LinkedList;
import java.util.List;

import at.ac.tuwien.ieg.asbruedit.model.asbru.Conditions;

public class ConditionsPart extends AbstractConditionModelPart<Conditions> {

	@Override
	protected List<Object> getModelChildren() {
		LinkedList<Object> children = new LinkedList<Object>();
		Object child;
		child = getModel().getAbortCondition();
		if(child != null) {
			children.add(child);
		}
		child = getModel().getCompleteCondition();
		if(child != null) {
			children.add(child);
		}
		child = getModel().getFilterPrecondition();
		if(child != null) {
			children.add(child);
		}
		child = getModel().getReactivateCondition();
		if(child != null) {
			children.add(child);
		}
		child = getModel().getSetupPrecondition();
		if(child != null) {
			children.add(child);
		}
		child = getModel().getSuspendCondition();
		if(child != null) {
			children.add(child);
		}
		return children;
	}
}
