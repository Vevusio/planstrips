package at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.SubplansEditPart;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanSchema;

public class PasteAction extends SelectionAction {
	
	public PasteAction(IWorkbenchPart part) { 
		super(part);
		// force calculateEnabled() to be called in every context
		setLazyEnablementCalculation(true);
	}
	
	protected void init() {
		super.init(); 
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages(); 
		setText("Paste"); 
		setId(ActionFactory.PASTE.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE)) ;
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		setEnabled(false);
	}
	
	private Command createPasteCommand() { 
		List<Object> selectedObjects = getSelectedObjects();
		if (selectedObjects == null || selectedObjects.size() != 1 || !(selectedObjects.get(0) instanceof PlanStripsEditPart)) {
			return null;
		}
		PlanStripsEditPart target = (PlanStripsEditPart)selectedObjects.get(0);
		AddAnyCommand add = new AddAnyCommand(target);
		// get the edit part from the clipboard and set the child to its model element
		CopyItem clipContent = Clipboard.getDefault().getContents() instanceof CopyItem ? (CopyItem)Clipboard.getDefault().getContents() : null;
		if(clipContent != null) {
			add.setChild(((CopyItem)clipContent).model);
		}
		
		// if we have selected a plan activation
		if(target instanceof PlanActivationEditPart) {
			PlanEditPart activatingPlan = ((PlanActivationEditPart)target).getActivatingPlanEditPart();
			// forward the paste to the child of the activation plan (if any)
			if(AsbruModelHandler.instance().planHasChild(activatingPlan.getModel())) {
				add.setParent(AsbruModelHandler.instance().planGetChild(activatingPlan.getModel()));
			}
			// forward the activation plan itself (if no children are contained)
			else {
				add.setParent(activatingPlan.getModel());
			}
		}
		// else the parent is the model element of the paste target
		else {
			add.setParent(((EditPart)target).getModel());
		}
		if(clipContent != null) {
			
		}
		if(clipContent != null) {
			add.initializeForCyclicalCheck(clipContent.editPart, add.getChild(), target);
		}
		return add;
	}
	
	@Override 
	protected boolean calculateEnabled() {
		Command command = createPasteCommand();
		return command != null && command.canExecute(); 
	}
	
	@Override 
	public void run() {
		Command command = createPasteCommand(); 
		if (command != null && command.canExecute())
			execute(command);
	}
}