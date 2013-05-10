package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Context;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanPointer;

public class PlanPointerPart extends AbstractConditionModelPart<PlanPointer>{

	@Override
	public String makeConditionString() {
		if(getModel().getStaticPlanPointer() != null && getModel().getStaticPlanPointer().getPlanName() != null) {
			Plan plan = (Plan) getModel().getStaticPlanPointer().getPlanName();
			return AsbruModelHandler.instance().getPlanName(plan);
		}
		return "(" + getModel().getClass().getSimpleName() + ")";
	}

}
