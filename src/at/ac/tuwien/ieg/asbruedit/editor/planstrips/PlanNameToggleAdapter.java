package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;

public class PlanNameToggleAdapter {
	private GraphicalViewer viewer;
	
	public PlanNameToggleAdapter(GraphicalViewer graphicalViewer) {
		this.viewer = graphicalViewer;
	}

	public void setShowPlanNames(boolean showConditions) {
		setShowConditions(showConditions, viewer.getRootEditPart());
	}
	
	private void setShowConditions(boolean showLabels, EditPart part) {
		if(part instanceof PlanStripsEditPart) {
			PlanStripsEditPart planStripsEditPart = (PlanStripsEditPart)part;
			if(planStripsEditPart.getFigure() instanceof PlanStripsBox) {
				PlanStripsBox box = (PlanStripsBox)planStripsEditPart.getFigure();
				box.updateShowLabel();
			}
		}
		
		for(Object child : part.getChildren()) {
			setShowConditions(showLabels, (EditPart)child);
		}
	}
}
