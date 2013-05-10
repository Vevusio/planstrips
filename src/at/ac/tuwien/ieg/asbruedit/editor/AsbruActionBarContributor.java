package at.ac.tuwien.ieg.asbruedit.editor;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;

public class AsbruActionBarContributor extends ActionBarContributor {
	@Override
	protected void buildActions() {
		IWorkbenchWindow iww = getPage().getWorkbenchWindow();
		addRetargetAction(new UndoRetargetAction()); 
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
//		addRetargetAction(new ZoomInRetargetAction()); 
//		addRetargetAction(new ZoomOutRetargetAction());
		addRetargetAction((RetargetAction)ActionFactory.COPY.create(iww));
		addRetargetAction((RetargetAction)ActionFactory.PASTE.create(iww));

	}
	
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(getAction(ActionFactory.UNDO.getId())); 
		toolBarManager.add(getAction(ActionFactory.REDO.getId()));
		toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
//		toolBarManager.add(new Separator()); 
//		toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN)); 
//		toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT)); 
//		toolBarManager.add(new ZoomComboContributionItem(getPage()));
		toolBarManager.add(getAction(ActionFactory.COPY.getId()));
		toolBarManager.add(getAction(ActionFactory.PASTE.getId()));
	}

	@Override
	protected void declareGlobalActionKeys() {
	}
	
	public void contributeToMenu(IMenuManager menuManager) {
	}
}
