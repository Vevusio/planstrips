package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands.AddAnyCommand;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands.RemoveAnyCommand;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;

public class PlanStripsLayoutEditPolicy extends FlowLayoutEditPolicy {	
	@Override
	protected Command getOrphanChildrenCommand(Request generic) {
		GroupRequest request = (GroupRequest)generic;
		CompoundCommand orphanCommand = new CompoundCommand();
		List<EditPart> children = request.getEditParts();
		for(EditPart cChild : children) {
			orphanCommand.add(createOrphanChildCommand(request, cChild));
		}
		return orphanCommand;
	}

	protected Command createOrphanChildCommand(GroupRequest request, EditPart child) {
		RemoveAnyCommand removeCommand = new RemoveAnyCommand(((PlanStripsEditPart)getHost()));
		removeCommand.setParent(getHost().getModel());
		removeCommand.setChild(child.getModel());
		//System.out.println("o:" + getHost() + " / " + child + " = " + removeCommand.canExecute());
		return removeCommand;
	}
	
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		AddAnyCommand addCommand = new AddAnyCommand(((PlanStripsEditPart)getHost()));
		addCommand.setParent(getHost().getModel());
		addCommand.setChild(request.getNewObject());
		EditPart reference = getInsertionReference(request);
		if(reference != null) {
			addCommand.setBefore(reference.getModel());
		}
		return addCommand;
	}

	protected Command createAddCommand(EditPart child, EditPart reference, Request request) {
		AddAnyCommand addCommand = new AddAnyCommand(((PlanStripsEditPart)getHost()));
		addCommand.setParent(getHost().getModel());
		addCommand.setChild(child.getModel());
		addCommand.initializeForCyclicalCheck((PlanStripsEditPart)child, (PlanStripsEditPart)getHost());
		if(reference != null) {
			addCommand.setBefore(reference.getModel());
		}
		//System.out.println("a:" + getHost() + " / " + child + " = " + addCommand.canExecute());
		return addCommand;
	}
	
	@Override
	protected Command createAddCommand(EditPart child, EditPart reference) {
		return createAddCommand(child, reference, null);
	}

	@Override
	protected Command getAddCommand(Request req) {
		ChangeBoundsRequest request = (ChangeBoundsRequest) req;
		List editParts = request.getEditParts();
		CompoundCommand command = new CompoundCommand();
		for (int i = 0; i < editParts.size(); i++) {
			EditPart child = (EditPart) editParts.get(i);
			command.add(createAddCommand(child, getInsertionReference(request), req));
		}
		return command.unwrap();
	}

	@Override
	protected Command createMoveChildCommand(EditPart child, EditPart reference) {
		CompoundCommand moveCommand = new CompoundCommand();
		RemoveAnyCommand removeCommand = new RemoveAnyCommand(((PlanStripsEditPart)getHost()));
		removeCommand.setChild(child.getModel());
		removeCommand.setParent(getHost().getModel());
		moveCommand.add(removeCommand);
		AddAnyCommand addCommand = new AddAnyCommand(((PlanStripsEditPart)getHost()));
		addCommand.setParent(getHost().getModel());
		addCommand.setChild(child.getModel());
		addCommand.initializeForCyclicalCheck((PlanStripsEditPart)child, (PlanStripsEditPart)getHost());
		if(reference != null) {
			addCommand.setBefore(reference.getModel());
		}
		moveCommand.add(addCommand);
		//System.out.println("m: child=" + child + " ; ref=" + reference + " ; in=" + getHost());
		return moveCommand;
	}

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return null;
	}
}
