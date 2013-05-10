package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart.ChildIncludingPropertySource;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.PlanStripsSelectionEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.GenericFigure;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.ToolbarFigure;
import at.ac.tuwien.ieg.asbruedit.model.ModelTransformer;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Ask;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.UnhandledModelElement;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.transformers.IdentityTransformer;

public class PlanActivationEditPart extends PlanStripsEditPart {	
	@Override
	protected IFigure createFigure() {
		// since the activation will only have one child (the plan its schema is holding), use a stack layout to automatically stretch the contained box
		Figure figure = new Figure();
		figure.setLayoutManager(new StackLayout());
		return figure;
	}
	
	@Override
	public PlanActivation getModel() {
		return (PlanActivation)super.getModel();
	}
	
	@Override
	protected List<?> getModelChildren() {
		LinkedList<Object> children = buildSupportedModelChildren(AsbruModelHandler.instance().getModelChildren(getModel()));
		return children;
	}

	@Override
	protected EditPolicy createSelectionFeedbackEditPolicy() {
		return new PlanStripsSelectionEditPolicy() {
			@Override
			protected void setSelectedState(int type) {
				// perform a proper selection
				super.setSelectedState(type);
				// also highlight the activations plan (if there is one)
				PlanEditPart planEditPart = getActivatingPlanEditPart();
				if(planEditPart != null) {
					((PlanStripsBox)planEditPart.getFigure()).setHighlightLevel(type);
				}
			}
		};
	}
	
	@Override
	public void setFocusIntensity(float focus) {
		// delegate the focus setting to the activating plan if there is one
		PlanEditPart planEditPart = getActivatingPlanEditPart();
		if(planEditPart != null) {
			planEditPart.setFocusIntensity(focus);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		if (PROPERTY_CHANGE_CHILD.equals(evt.getPropertyName())) {
			refreshChildren();
		} 
	}

	/**
	 * @return The {@link PlanEditPart} containing the {@link Plan} this activation is activating, null if there is none
	 */
	public PlanEditPart getActivatingPlanEditPart() {
		// if the plan activation is not empty
		if(!getChildren().isEmpty()) {
			// according to the asbru definition a plan-activation has exactly one plan-schema which references a plan
			// thus there will only be 1 child (a plan) - since the plan-schema is not explicitly reflected in the editor
			return (PlanEditPart)getChildren().get(0);
		}
		else {
			return null;
		}
	}

	@Override
	protected IPropertySource makePropertySource() {
		return new ChildIncludingPropertySource();
	}
}
