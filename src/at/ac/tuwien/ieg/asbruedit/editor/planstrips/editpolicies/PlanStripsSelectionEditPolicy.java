package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.SelectionRequest;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanLibraryEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox.DetailLevel;

public class PlanStripsSelectionEditPolicy extends NonResizableEditPolicy {
	private int gethostcount = 0;
	
//	@Override
//	public void showTargetFeedback(Request request) {
//		if(REQ_SELECTION.equals(request.getType())) {
//			SelectionRequest req = (SelectionRequest)request;
//			PlanStripsEditPart parent = (PlanStripsEditPart)getHost().getParent();
//			parent.setFocusIntensity(0.5f);
//			List<PlanStripsEditPart> children = getHost().getChildren();
//			for(PlanStripsEditPart child : children) {
//				child.setFocusIntensity(0.66f);
//			}
//			getHost().setFocusIntensity(1);
//		}
//	}
//	
//	@Override
//	public void eraseTargetFeedback(Request request) {
//		if(REQ_SELECTION.equals(request.getType())) {
//			PlanStripsEditPart parent = (PlanStripsEditPart)getHost().getParent();
//			parent.setFocusIntensity(-1);
//			List<PlanStripsEditPart> children = getHost().getChildren();
//			for(PlanStripsEditPart child : children) {
//				child.setFocusIntensity(-1);
//			}
//			getHost().setFocusIntensity(-1);
//		}
//	}

	@Override
	protected void showSelection() {
		if(getHostFigure() instanceof PlanStripsBox) {
			PlanStripsBox hostBox = (PlanStripsBox)getHostFigure();
			hostBox.setHighlightLevel(getHost().getSelected());
			//hostBox.setDetailLevel(DetailLevel.full);
		}
		// trigger a secondary selection up the hierarcy
		highlightHierarchy(EditPart.SELECTED);
	}

	@Override
	protected void hideSelection() {
		if(getHostFigure() instanceof PlanStripsBox) {
			PlanStripsBox hostBox = (PlanStripsBox)getHostFigure();
			hostBox.setHighlightLevel(getHost().getSelected());
			//hostBox.setDetailLevel(DetailLevel.none);
		}
		// trigger the un-selection up the hierarchy
		highlightHierarchy(EditPart.SELECTED_NONE);
	}
	
	/**
	 * Trigger highlighting up the hierarchy until we reach the plan library itself
	 * @param highlightValue Value of the highlight as in the {@link PlanStripsBox} highlighting constants
	 */
	protected void highlightHierarchy(int highlightValue) {
		GraphicalEditPart part = (GraphicalEditPart)getHost().getParent();
		while(part != null && !(part instanceof PlanLibraryEditPart)) {
			if(part.getFigure() instanceof PlanStripsBox) {
				PlanStripsBox box = (PlanStripsBox)part.getFigure();
				box.setHighlightLevel(highlightValue);
				//box.setDetailLevel(highlightValue == EditPart.SELECTED_NONE ? DetailLevel.none : DetailLevel.full);
			}
			part = (GraphicalEditPart)part.getParent();
		}
	}

	@Override
	public EditPart getTargetEditPart(Request request) {
		EditPart defaultTarget = super.getTargetEditPart(request);
		// delegate selection according to the edit part on selection requests
		if(REQ_SELECTION.equals(request.getType())) {
			// get the delegate of the host
			PlanStripsEditPart target = getHost().getSelectionDelegate();
			// if the delegate differs from the default target
			if(target != defaultTarget) {
				// return the target of the delegated target
				return target.getTargetEditPart(request);
			}
		}
		// otherwise just return the default target
		return defaultTarget;
	}

	@Override
	public PlanStripsEditPart getHost() {
		return (PlanStripsEditPart)super.getHost();
	}
}
