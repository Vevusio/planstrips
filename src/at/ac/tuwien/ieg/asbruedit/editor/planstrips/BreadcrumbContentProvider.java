package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreePathContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanLibraryEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PotentialPlanChildEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.SubplansEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.UnhandledModelElementEditPart;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;

public class BreadcrumbContentProvider implements ITreePathContentProvider {
	public static final String NO_INPUT = "__" + BreadcrumbContentProvider.class.toString() + "_NO_INPUT";
	
	LinkedList<PlanStripsEditPart> inputPath;
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// ignore the new input if it is not supported
		if(newInput == null || NO_INPUT.equals(newInput) || !PlanStripsEditPart.class.isAssignableFrom(newInput.getClass())) {
			return;
		}
		// if we happen to select a plan activation we have to go one level deeper and select the plan it is activating as the input
		// so that the actual plan gets shown in the breadcrumb
		if(newInput instanceof PlanActivationEditPart) {
			Object activatingPlan = ((PlanActivationEditPart)newInput).getActivatingPlanEditPart();
			if(activatingPlan != null) {
				newInput = activatingPlan;
			}
		}
		inputPath = new LinkedList<PlanStripsEditPart>();
		PlanStripsEditPart cPart = (PlanStripsEditPart) newInput;
		// work our way up the hierarchy building the path from [plan-root.. newInput]
		while(!(cPart instanceof PlanLibraryEditPart)) {
			// add the part to the breadcrumb path if
			if(
					// it's an unhandled part
					cPart instanceof UnhandledModelElementEditPart ||
					// it's a plan part
					cPart instanceof PlanEditPart ||
					// it's a stand-alone part that could be embedded in a plan, this could be subplans, cyclical, etc.
					(PotentialPlanChildEditPart.class.isAssignableFrom(cPart.getClass()) &&
					!((PotentialPlanChildEditPart)cPart).isPlanChild())) {
				inputPath.addFirst(cPart);
			}
					
			cPart = (PlanStripsEditPart)cPart.getParent();
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// return the first element on the path (the root) if we can, else an empty string
		// can't return null or an empty array because otherwise the breadcrumb viewer dies with an exception
		return (inputPath != null && !inputPath.isEmpty()) ? new Object[]{inputPath.getFirst()} : new Object[]{NO_INPUT};
	}

	@Override
	public Object[] getChildren(TreePath parentPath) {
		if (inputPath != null) {
			// figure out the index on the input path of the current (last) segment
			int pathIndex = inputPath.indexOf(parentPath.getLastSegment());
			// if the object is contained in the input path and not be the last one
			if (pathIndex != -1 && pathIndex < inputPath.size()-1) {
				// return successor
				return new Object[] { inputPath.get(pathIndex + 1) };
			}
		}
		return null;
	}

	@Override
	public boolean hasChildren(TreePath path) {
		return false;
	}

	@Override
	public TreePath[] getParents(Object element) {
		return null;
	}
}
