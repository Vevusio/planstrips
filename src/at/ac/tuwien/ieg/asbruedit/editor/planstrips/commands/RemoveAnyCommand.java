package at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands;

import org.eclipse.gef.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.PlanLibraryProvider;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.VariableDef;

/**
 * Generic command capable of removing any model element as long as
 * the corresponding methods are implemented in {@link AsbruModelHandler} - the
 * methods being canRemoveChild(parent, child) and removeChild(parent, child)
 */
public class RemoveAnyCommand extends Command {
	private static final String REMOVE_CHILD_CHECK = "canRemoveChild";
	private static final String REMOVE_CHILD = "removeChild";
	private Object child;
	private Object parent;
	private AddAnyCommand undoCommand;
	private PlanLibraryProvider libraryProvider;
	
	public RemoveAnyCommand(PlanLibraryProvider libraryProvider) {
		this.libraryProvider = libraryProvider;
	}

	@Override
	public boolean canExecute() {
		Method addCheck = null;
		try {
			//System.out.print("canRemove(" + (parent != null ? parent.getClass().getSimpleName() : "null") + ", " + (child != null ? child.getClass().getSimpleName() : "null") + ") = ");
			addCheck = AsbruModelHandler.class.getMethod(
					REMOVE_CHILD_CHECK, parent.getClass(), Object.class);
			//System.out.println(addCheck.invoke(AsbruModelHandler.instance(), parent, child));
			return (Boolean) addCheck.invoke(AsbruModelHandler.instance(), parent, child);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			// due to the nature of the gef framework a remove command is created on every element
			// that potentially supports deletion - if the actual method pair to check/remove th
			// element does not exist we tell the framework that the command is not supported
			
			//System.out.println("false");
			return false;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(addCheck.toString(), e);
		}
	}

	@Override
	public void execute() {
		Method addChild  = null;
		try {
			addChild = AsbruModelHandler.class.getMethod(
					REMOVE_CHILD, parent.getClass(), Object.class);
			addChild.invoke(AsbruModelHandler.instance(), parent, child);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					AsbruModelHandler.class.getPackage().getName()
					+ "."
					+ AsbruModelHandler.class.getSimpleName()
					+ " does not implement a required method", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(addChild.toString(), e);
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
	
	protected AddAnyCommand getUndoCommand() {
		if(undoCommand == null) {
			undoCommand = new AddAnyCommand(libraryProvider);
			undoCommand.setChild(getChild());
			undoCommand.setParent(getParent());
		}
		return undoCommand;
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
}
