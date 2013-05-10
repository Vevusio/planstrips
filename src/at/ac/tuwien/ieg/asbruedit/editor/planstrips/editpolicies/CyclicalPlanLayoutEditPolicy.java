package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands.AddAnyCommand;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.CyclicalPlanEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.UserPerformed;

public class CyclicalPlanLayoutEditPolicy extends PlanStripsLayoutEditPolicy {
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		Command cmd = null;
		// if the plan can take take a child
		if (!AsbruModelHandler.instance().cyclicalPlanHasChild(getHost().getModel())) {
			cmd = super.getCreateCommand(request);
		}
		// otherwise we delegate the command to the child
		else {
			List<EditPart> hostChildren = getHost().getChildren();
			// (semantically a cyclical plan can only have one child as of the
			// writing of this class with asbru version 7.3)
			if (hostChildren.size() == 1) {
				cmd = hostChildren.get(0).getCommand(request);
			}
		}
		return cmd;
	}
	
	@Override
	protected Command createAddCommand(EditPart child, EditPart reference, Request request) {
		Command cmd = null;
		// if the plan can take take a child
		if (!AsbruModelHandler.instance().cyclicalPlanHasChild(getHost().getModel())) {
			cmd = super.createAddCommand(child, reference, request);
		}
		// otherwise we delegate the command to the child
		else {
			List<EditPart> hostChildren = getHost().getChildren();
			// (semantically a cyclical plan can only have one child as of the
			// writing of this class with asbru version 7.3)
			if (hostChildren.size() == 1) {
				cmd = hostChildren.get(0).getCommand(request);
			}
		}
		return cmd;
	}

	@Override
	public CyclicalPlanEditPart getHost() {
		return (CyclicalPlanEditPart) super.getHost();
	}
}
