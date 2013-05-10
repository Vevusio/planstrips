package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.view.condition.ConditionInput;

public abstract class PlanStripsSelectionListener implements ISelectionListener {
	private Class<?>[] supportedClasses;
	
	public PlanStripsSelectionListener(Class<?>... supportedClasses) {
		this.supportedClasses = supportedClasses;
	}
	
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// sort out the class of the selection to see if its supported
		if(selection instanceof StructuredSelection) {
			StructuredSelection s = (StructuredSelection)selection;
			Object[] selectedElements = s.toArray();
			LinkedList<Object> supportedElements = new LinkedList<Object>();
			LinkedList<Object> unsupportedElements = new LinkedList<Object>();
			for(Object cElement : selectedElements) {
				boolean isSupported = false;
				for(Class<?> supportedClass : supportedClasses) {
					if(supportedClass.isAssignableFrom(cElement.getClass())) {
						isSupported = true;
						break;
					}
				}
				if(isSupported) {
					supportedElements.add(cElement);
				}
				else {
					unsupportedElements.add(cElement);
				}
			}
			selectionChanged(part, selection, supportedElements, unsupportedElements);
		}
	}
	
	/**
	 * Called when the selection has changed. This method is called by {@link #selectionChanged(IWorkbenchPart, ISelection)} passing on the first 2 parameters.
	 * @param part the workbench part containing the selection
	 * @param selection the current selection
	 * @param supportedSelection list of objects which matched the supported classes
	 * @param unsupportedSelection list of objects which did not match the supported classes
	 */
	protected abstract void selectionChanged(IWorkbenchPart part, ISelection selection, LinkedList<Object> supportedSelection, LinkedList<Object> unsupportedSelection);
}
