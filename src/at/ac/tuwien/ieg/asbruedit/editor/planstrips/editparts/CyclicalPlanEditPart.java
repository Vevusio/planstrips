package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.beans.PropertyChangeEvent;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart.AbstractChildIncludingPropertySource;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.CyclicalPlanLayoutEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlan;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.UnhandledModelElement;

public class CyclicalPlanEditPart extends PotentialPlanChildEditPart {	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new CyclicalPlanLayoutEditPolicy());
	}
	
	@Override
	protected List<?> getModelChildren() {
		LinkedList<Object> children = buildSupportedModelChildren(AsbruModelHandler.instance().getModelChildren(getModel()));
		return children;
	}

	@Override
	public CyclicalPlan getModel() {
		return (CyclicalPlan)super.getModel();
	}

	@Override
	public PlanType getType() {
		return PlanType.cyclical;
	}
}
