package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.ReflectivePartFactory;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.SubplansEditPart;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanLibrary;

public class PlanStripsEditPartFactory extends ReflectivePartFactory implements EditPartFactory {
	protected static final String EDIT_PARTS_CLASS_SUFFIX = "EditPart";
	protected static final String EDIT_PARTS_CHILD_PACKAGE = "editparts";

	@Override
	protected String getPartSuffix() {
		return EDIT_PARTS_CLASS_SUFFIX;
	}

	@Override
	protected String getPartPackage() {// figure out which package the edit
										// parts for this factory are located in
		Package factoryPackage = this.getClass().getPackage();
		return factoryPackage.getName() + "." + EDIT_PARTS_CHILD_PACKAGE;
	}

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart newPart = (EditPart) createPart(context, model);
		return newPart;
	}
}
