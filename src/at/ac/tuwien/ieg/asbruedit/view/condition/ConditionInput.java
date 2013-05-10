package at.ac.tuwien.ieg.asbruedit.view.condition;

import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractEditPart;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.AbortCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.condition.ConditionModelPartFactory;
import at.ac.tuwien.ieg.asbruedit.model.condition.PlanPart;

public class ConditionInput {
	public LinkedList<PlanPart> inputList = new LinkedList<PlanPart>();
	
	public ConditionInput(PlanActivationEditPart planActivationEditPart) {
		this(planActivationEditPart.getActivatingPlanEditPart());
	}
	
	public void changePlanStringGenerators(Class<?> conditionStringClass) {
		for(PlanPart cPart : inputList) {
			cPart.setConditionStringTarget(conditionStringClass);
		}
	}
	
	public ConditionInput(PlanEditPart planEditPart) {
		ConditionModelPartFactory factory = new ConditionModelPartFactory();
		if(planEditPart != null) {
			EditPart part = planEditPart;
			while(part != null) {
				if(part instanceof PlanEditPart) {
					PlanEditPart cPlanPart = (PlanEditPart)part;
					PlanPart planConditionsPart = (PlanPart) factory.createPart(null, cPlanPart.getModel());
					inputList.add(planConditionsPart);
				}
				part = part.getParent();
			}
			// reverse the list so we have a top-down view
			Collections.reverse(inputList);
		}
	}
}
