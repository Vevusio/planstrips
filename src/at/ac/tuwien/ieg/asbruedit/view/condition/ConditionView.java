package at.ac.tuwien.ieg.asbruedit.view.condition;

import java.io.File;
import java.text.MessageFormat;
import java.util.LinkedList;

import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.PlanStripsSelectionListener;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanActivationEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.helpers.ColorTool;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;
import at.ac.tuwien.ieg.asbruedit.view.condition.preferences.PreferenceConstants;

public class ConditionView extends ViewPart {
	public static final String ID = "at.ac.tuwien.ieg.asbruedit.conditionview";
	public static final ConditionsType DEFAULT_CONDITIONS_TYPE = ConditionsType.filter_precondition;
	
	private TableViewer tableViewer;
	private ConditionsType conditionsType;
	private ISelectionListener editorSelectionListener;
	private TableColumn conditionColumn;

	public void createPartControl(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL) {
			@Override
			protected void internalRefresh(Object element, boolean updateLabels) {
				super.internalRefresh(element, updateLabels);
				packTable();
			}
			
			protected void packTable() {
				// pack multiple times as workaround because otherwise (for whatever reason) the columns only get resized a small amount
				for(int i = 0; i < 5; i++) {
					for(TableColumn cColumn : getTable().getColumns()) {
						cColumn.pack();
					}
				}
			}
		};
		makeTable(tableViewer.getTable());

		// Multi-font support only works in JFace 3.5 and above (specifically,
		// 3.5 M4 and above).
		// With JFace 3.4, the font information (bold in this example) will be
		// ignored.
		FontData[] boldFontData = getModifiedFontData(tableViewer.getTable().getFont().getFontData(), SWT.BOLD);
		Font boldFont = new Font(Display.getCurrent(), boldFontData);
		ConditionLabelProvider labelProvider = new ConditionLabelProvider(boldFont);

		tableViewer.setContentProvider(new ConditionContentProvider());
		tableViewer.setLabelProvider(labelProvider);

		GridData data = new GridData(GridData.FILL, GridData.FILL, true, true);
		tableViewer.getControl().setLayoutData(data);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(editorSelectionListener = new PlanStripsSelectionListener(PlanActivationEditPart.class, PlanEditPart.class) {
			@Override
			protected void selectionChanged(IWorkbenchPart part, ISelection selection, LinkedList<Object> supportedSelection, LinkedList<Object> unsupportedSelection) {		
				// only 1 element should be selected, else we can't tell which we should display the conditions for
				if(supportedSelection.size() == 1) {
					ConditionInput input = null;
					Object selected = supportedSelection.getFirst();
					if(selected instanceof PlanActivationEditPart) {
						input = new ConditionInput((PlanActivationEditPart)selected);
					}
					else if(selected instanceof PlanEditPart) {
						input = new ConditionInput((PlanEditPart)selected);
					}
					// if we registered a supported element, update the input
					if(input != null) {
						// set the condition string generators to the conditions type (filter, setup, etc.) we are currently displaying
						input.changePlanStringGenerators(conditionsType.getRelatedModelClass());
						setInput(input);
					}
				}
			}
		});
		
		// set the conditions type to display to whatever is stored in the preferences
		setConditionType(ConditionsType.fromString(PreferenceUtil.readString(PreferenceConstants.SHOW_CONDITION)));
	}
	
	public void setConditionType(ConditionsType type) {
		if(type != null && type.equals(conditionsType)) {
			return;
		}
		PreferenceUtil.savePreference(PreferenceConstants.SHOW_CONDITION, type.getId());
		conditionsType = type;
		conditionColumn.setText(conditionsType.getId());
		if(getInput() != null) {
			getInput().changePlanStringGenerators(type.getRelatedModelClass());
		}
		tableViewer.refresh();
	}
	
	protected void setInput(ConditionInput input) {
		tableViewer.setInput(input);
	}
	
	protected ConditionInput getInput() {
		return (ConditionInput)tableViewer.getInput();
	}
	
	private void makeTable(Table table) {
		TableColumn column = new TableColumn(table, SWT.NONE);
		column.setText("Plan");
		column.setWidth(100);

		conditionColumn = new TableColumn(table, SWT.NONE);
		conditionColumn.setText("Condition");
		conditionColumn.setWidth(300);

		table.setHeaderVisible(true);
	}

	private static FontData[] getModifiedFontData(FontData[] originalData, int additionalStyle) {
		FontData[] styleData = new FontData[originalData.length];
		for (int i = 0; i < styleData.length; i++) {
			FontData base = originalData[i];
			styleData[i] = new FontData(base.getName(), base.getHeight(), base.getStyle() | additionalStyle);
		}
		return styleData;
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(editorSelectionListener);
		super.dispose();
	}
}