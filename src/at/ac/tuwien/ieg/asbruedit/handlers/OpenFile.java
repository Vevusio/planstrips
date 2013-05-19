package at.ac.tuwien.ieg.asbruedit.handlers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruXMLEditorInput;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.PlanStripsEditor;

public class OpenFile extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		FileDialog dlg = new FileDialog(HandlerUtil.getActiveShell(event),  SWT.OPEN);
		dlg.setText("Open/New File");
		String path = dlg.open();
		if (path == null) {
			return null;
		}
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		
		File target = new File(path);
		if(!target.exists()) {
			if(!target.getName().endsWith(".xml")) {
				target = new File(target.getAbsolutePath().concat(".xml"));
			}
			try {
				target.createNewFile();
				FileWriter out = new FileWriter(target);
				out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><plan-library>	<library-info title=\"untitled\">		<administrative-data original-author=\"" + System.getProperty("user.name") + "\" />	</library-info>	<domain-defs>		<domain name=\"mainDomain\">			<not-yet-defined />		</domain>	</domain-defs>	<plans>		<plan-group>			<plan title=\"\" name=\"unnamed\">				<plan-body>				</plan-body>			</plan>		</plan-group>	</plans></plan-library>");
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		AsbruXMLEditorInput input = new AsbruXMLEditorInput(target);
		
		try {
			page.openEditor(input, PlanStripsEditor.ID);
		}
		catch(PartInitException e) {
			throw new RuntimeException(e);
		}
	    return null;
	}
}
