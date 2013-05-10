package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.model.ModelTransformer;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Ask;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.UnhandledModelElement;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.transformers.IdentityTransformer;

public class UnhandledModelElementEditPart extends PlanStripsEditPart {	
	@Override
	protected IFigure createFigure() {
		PlanStripsBox box = new PlanStripsBox();
		box.setHeading(super.getModel().toString());
		box.setType(getType());
		return box;
	}
	
	@Override
	public Object getModel() {
		// unwrap the underlying model element when returning it
		return ((UnhandledModelElement)super.getModel()).getModel();
	}
	
/*	@Override
	public void setSelected(int value) {
		super.setSelected(value);
		getFigure().setHighlightLevel(value);
	}*/

	@Override
	public PlanStripsBox getFigure() {
		return (PlanStripsBox)super.getFigure();
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + getModel().getClass().getSimpleName() + ")";
	}
	
	public PlanType getType() {
		return PlanType.other;
	}
}
