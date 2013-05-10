package at.ac.tuwien.ieg.asbruedit.view.condition;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterValuesException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruXMLEditorInput;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.PlanStripsEditor;
import at.ac.tuwien.ieg.asbruedit.helpers.UIShortcut;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;

public class ConditionSelectHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {		
		if(HandlerUtil.matchesRadioState(event))
	        return null;
	 
	    String currentState = event.getParameter(RadioState.PARAMETER_ID);
	 
	    
	    ConditionView view = (ConditionView)UIShortcut.getActivePageView(HandlerUtil.getActiveWorkbenchWindow(event), ConditionView.ID);
	    ConditionsType newType = ConditionsType.fromString(currentState);
	    view.setConditionType(newType);
	    
	    HandlerUtil.updateRadioState(event.getCommand(), currentState);
	 
	    return null;  
	}
}
