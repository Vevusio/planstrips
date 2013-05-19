package at.ac.tuwien.ieg.asbruedit.editor.planstrips.handlers;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterValuesException;
import org.eclipse.core.commands.State;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruXMLEditorInput;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.ConditionsToggleAdapter;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.PlanNameToggleAdapter;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.PlanStripsEditor;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.PlanStripsEditorRegistry;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanStripsEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.helpers.UIShortcut;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;

public class ShowPlanNamesHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean oldValue = HandlerUtil.toggleCommandState(command);
		// use the old value and perform the operation
		for (PlanStripsEditor editor : PlanStripsEditorRegistry.instance().all()) {
			PlanNameToggleAdapter toggler = (PlanNameToggleAdapter)editor.getAdapter(PlanNameToggleAdapter.class);
			toggler.setShowPlanNames(!oldValue);
		}
		return null;
	}
	


	public static boolean isShowPlanNames() {
		ICommandService commandService = (ICommandService) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ICommandService.class);

		Command command = commandService.getCommand("at.ac.tuwien.ieg.asbruedit.show-planname-toggle");

		State state = command.getState("org.eclipse.ui.commands.toggleState");

		boolean isToggled = (Boolean) state.getValue();
		return isToggled;
	}
}
