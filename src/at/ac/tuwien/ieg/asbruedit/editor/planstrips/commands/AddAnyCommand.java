package at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.PlanLibraryProvider;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanLibrary;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.VariableDef;

/**
 * Generic command capable of adding any child to any target element as long as
 * the corresponding methods are implemented in {@link AsbruModelHandler} - the
 * methods being canAddChild(source, child, before) and addChild(source, child,
 * before)
 */
public class AddAnyCommand extends Command {
	private static final String SUPPORTED_CHILD_CHECK = "isChildSupported";
	private static final String ADD_CHILD_CHECK = "canAddChild";
	private static final String ADD_CHILD = "addChild";
	private Object parent;
	private Object child;
	private Object before;
	private PlanLibraryProvider libraryProvider;
	private RemoveAnyCommand undoCommand;
	
	// data needed for cycle checks
	private Object childModel; // model of the child that is copied
	private PlanStripsEditPart childEditPart; // edit part containing the child that is copied
	private PlanStripsEditPart parentEditPart; // edit part containing the target where the child is copied to

	public AddAnyCommand(PlanLibraryProvider libraryProvider) {
		this.libraryProvider = libraryProvider;
	}

	@Override
	public boolean canExecute() {
		// System.out.println("Can execute " + isChildSupported() + " / " +
		// canAddChild() + " / " + hasNoCycles() + " at " + parent + " > " +
		// child);
		return isChildSupported() && canAddChild() && hasNoCycles();
	}

	@Override
	public void execute() {
		Method addChild = null;
		try {
			addChild = AsbruModelHandler.class.getMethod(ADD_CHILD, parent.getClass(), Object.class, Object.class, PlanLibraryProvider.class);
			addChild.invoke(AsbruModelHandler.instance(), parent, child, before, libraryProvider);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(AsbruModelHandler.class.getPackage().getName() + "." + AsbruModelHandler.class.getSimpleName()
					+ " does not implement a required method", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(addChild.toString(), e);
		}
	}

	protected boolean isChildSupported() {
		if (parent == null || child == null) {
			return false;
		}
		Method supportedCheck = null;
		try {
			supportedCheck = AsbruModelHandler.class.getMethod(SUPPORTED_CHILD_CHECK, parent.getClass(), Class.class);
			return (Boolean) supportedCheck.invoke(AsbruModelHandler.instance(), parent, child.getClass());
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			// if the check is not implemented we infer that the parent does not
			// support any children
			return false;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(supportedCheck.toString(), e);
		}
	}

	protected boolean canAddChild() {
		if (parent == null || child == null) {
			return false;
		}
		Method addCheck = null;
		try {
			addCheck = AsbruModelHandler.class.getMethod(ADD_CHILD_CHECK, parent.getClass(), Object.class, Object.class);
			return (Boolean) addCheck.invoke(AsbruModelHandler.instance(), parent, child, before);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(AsbruModelHandler.class.getPackage().getName() + "." + AsbruModelHandler.class.getSimpleName()
					+ " does not implement a required method", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(addCheck.toString(), e);
		}
	}

	/**
	 * Checks if adding the child to the parent doesn't cause any cycles.
	 * 
	 * @return true if no cycles were found (or the test was not initialized),
	 *         false cycles were found
	 */
	private boolean hasNoCycles() {
		if (parentEditPart != null && childEditPart != null && childModel != null) {
			LinkedList<Object> unsafeCopyModels = buildUnsafeCopyModelList();
			
			PlanStripsEditPart currentCheck = parentEditPart;
			while (currentCheck.getParent() instanceof PlanStripsEditPart) {
				// if climing up the hierarchy from the parent to the root we
				// find any of the child model's children (or itself) this indicates a cycle
				if (unsafeCopyModels.contains(currentCheck.getModel())) {
					// System.out.println("CLASH");
					return false;
				}
				currentCheck = (PlanStripsEditPart) currentCheck.getParent();
			}
			// being here the check arrived at the root without finding a cycle
			// System.out.println("ALL OK");
			return true;

		} else {
			return true;
		}
	}
	
	private LinkedList<Object> buildUnsafeCopyModelList() {
		LinkedList<Object> unsafeCopyModels = new LinkedList<Object>();

		unsafeCopyModels.add(childModel);
		buildUnsafeCopyModelList(childEditPart, unsafeCopyModels);
		return unsafeCopyModels;
	}
	
	private void buildUnsafeCopyModelList(PlanStripsEditPart root, LinkedList<Object> unsafeCopyModels) {
		if(root != null) {
			unsafeCopyModels.add(root.getModel());
			for(Object cChild : root.getChildren()) {
				if(cChild instanceof PlanStripsEditPart) {
					buildUnsafeCopyModelList((PlanStripsEditPart)cChild, unsafeCopyModels);
				}
			}
		}
	}

	@Override
	public boolean canUndo() {
		return getUndoCommand().canExecute();
	}

	@Override
	public void undo() {
		getUndoCommand().execute();
	}

	protected RemoveAnyCommand getUndoCommand() {
		if (undoCommand == null) {
			undoCommand = new RemoveAnyCommand(libraryProvider);
			undoCommand.setChild(getChild());
			undoCommand.setParent(getParent());
		}
		return undoCommand;
	}
	
	public void initializeForCyclicalCheck(PlanStripsEditPart childEditPart, PlanStripsEditPart parentEditPart) {
		initializeForCyclicalCheck(childEditPart, childEditPart.getModel(), parentEditPart);
	}

	public void initializeForCyclicalCheck(PlanStripsEditPart childEditPart, Object childModel, PlanStripsEditPart parentEditPart) {
		this.childEditPart = childEditPart;
		// if the child is a plan activation check using the actual contained plan
		if (childModel instanceof PlanActivation) {
			childModel = AsbruModelHandler.instance().getPlanActivationActivatingPlan((PlanActivation) childModel);
		}
		this.childModel = childModel;
		// if the parent is a plan activation to the same
		if (parentEditPart instanceof PlanActivationEditPart) {
			parentEditPart = ((PlanActivationEditPart) parentEditPart).getActivatingPlanEditPart();
		}
		this.parentEditPart = parentEditPart;
	}

	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public Object getChild() {
		return child;
	}

	public void setChild(Object child) {
		this.child = child;
	}

	public Object getBefore() {
		return before;
	}

	public void setBefore(Object before) {
		this.before = before;
	}
}
