package at.ac.tuwien.ieg.asbruedit.editor.planstrips.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;

public class CopyAnyCommand extends Command {
	private static final String SUPPORTED_COPY_CHECK = "isCopySupported";
	private static final String COPY_ELEMENT = "copyElement";
	
	private CopyItem toCopy;
	
	@Override
	public boolean canExecute() {
		return isCopySupported();
	}

	@Override
	public void execute() {
		Method copyElement  = null;
		try {
			copyElement = AsbruModelHandler.class.getMethod(
					COPY_ELEMENT, toCopy.model.getClass());
			toCopy.model = copyElement.invoke(AsbruModelHandler.instance(), toCopy.model);
			Clipboard.getDefault().setContents(toCopy);
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
			throw new RuntimeException(copyElement.toString(), e);
		}
	}
	
	protected boolean isCopySupported() {
		Method supportedCheck = null;
		try {
			supportedCheck = AsbruModelHandler.class.getMethod(
					SUPPORTED_COPY_CHECK, toCopy.model.getClass());
			return (Boolean) supportedCheck.invoke(AsbruModelHandler.instance(), toCopy.model);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			// if the check is not implemented we infer that the element is not supported
			return false;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(supportedCheck.toString(), e);
		}
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public void undo() {
	}
	
	public void setToCopy(PlanStripsEditPart toCopy) {
		this.toCopy = new CopyItem();
		this.toCopy.editPart = toCopy;
		this.toCopy.model = toCopy.getModel();
	}
}
