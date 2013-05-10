package at.ac.tuwien.ieg.asbruedit.model.condition;

import java.util.LinkedList;
import java.util.List;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Conditions;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;

public class PlanPart extends AbstractConditionModelPart<Plan> {
	private Class<?> conditionStringTargetClass;
	
	public String getPlanName() {
		return AsbruModelHandler.instance().getPlanName(getModel());
	}
	
	public PlanType getPlanType() {
		return AsbruModelHandler.instance().getType(getModel());
	}
	
	public void setConditionStringTarget(Class<?> targetClass) {
		conditionStringTargetClass = targetClass;
	}
	
	@Override
	public String makeConditionString() {
		AbstractConditionModelPart<?> conditionPart = getCondition(conditionStringTargetClass);
		return conditionPart != null ? conditionPart.makeConditionString() : null;
	}
	
	public boolean hasCondition(Class<?> conditionClass) {
		return getCondition(conditionClass) != null;
	}
	
	public <T> AbstractConditionModelPart<T> getCondition(Class<T> conditionClass) {
		ConditionsPart conditions = getConditions();
		if(conditions != null) {
			return conditions.getChildByModel(conditionClass);
		}
		return null;
	}
	
	protected ConditionsPart getConditions() {
		return getChild(ConditionsPart.class);
	}

	@Override
	protected List<Object> getModelChildren() {
		LinkedList<Object> children = new LinkedList<Object>();
		Conditions conditions = getModel().getConditions();
		if(conditions != null) {
			children.add(getModel().getConditions());
		}
		return children;
	}
}
