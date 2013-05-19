package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;

public class ConditionsToggleAdapter {
	private GraphicalViewer viewer;
	
	public ConditionsToggleAdapter(GraphicalViewer graphicalViewer) {
		this.viewer = graphicalViewer;
	}

	public void setShowConditions(boolean showConditions) {
		setShowConditions(showConditions, viewer.getRootEditPart());
	}
	
	private void setShowConditions(boolean showConditions, EditPart part) {
		if(part instanceof PlanStripsEditPart) {
			PlanStripsEditPart planStripsEditPart = (PlanStripsEditPart)part;
			if(planStripsEditPart.getFigure() instanceof PlanStripsBox) {
				PlanStripsBox box = (PlanStripsBox)planStripsEditPart.getFigure();
				box.updateShowConditions();
			}
		}
		
		for(Object child : part.getChildren()) {
			setShowConditions(showConditions, (EditPart)child);
		}
	}
}
