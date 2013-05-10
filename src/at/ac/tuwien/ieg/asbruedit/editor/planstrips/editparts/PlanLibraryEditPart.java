package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanLibrary;

public class PlanLibraryEditPart extends PlanStripsEditPart {
	@Override
	protected IFigure createFigure() {
	    FreeformLayer layer = new FreeformLayer();
	    layer.setLayoutManager(new FlowLayout());
	    return layer;
	}
	
	@Override
	protected List<?> getModelChildren() {
		try {
			LinkedList<Plan> rootPlan = new LinkedList<Plan>();
			rootPlan.add(AsbruModelHandler.instance().getRootPlan(getModel()));
			return rootPlan;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void setModel(Object model) {
		super.setModel(model);
	}

	@Override
	public PlanLibrary getModel() {
		return (PlanLibrary)super.getModel();
	}
}
