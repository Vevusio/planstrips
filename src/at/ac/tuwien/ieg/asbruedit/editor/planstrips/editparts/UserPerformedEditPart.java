package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.model.ModelTransformer;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.UserPerformed;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.UnhandledModelElement;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.transformers.IdentityTransformer;

public class UserPerformedEditPart extends PotentialPlanChildEditPart {		
	@Override
	protected IFigure createFigure() {
		// in the user performed edit part we always have a box, never a transient figure
		PlanStripsBox box = createFigureForGenericParent();
		box.setHeading(getModel().getClass().getSimpleName());
		return box;
	}

	@Override
	public UserPerformed getModel() {
		return (UserPerformed)super.getModel();
	}

	@Override
	public PlanType getType() {
		return PlanType.userperformed;
	}
}
