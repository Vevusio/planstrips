package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands.RemoveAnyCommand;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;

public class DeleteEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest request) {
		RemoveAnyCommand cmd = new RemoveAnyCommand((PlanStripsEditPart)this.getHost());
		cmd.setChild(getHost().getModel());
		cmd.setParent(getHost().getParent().getModel());
		return cmd;
	}
	
}
