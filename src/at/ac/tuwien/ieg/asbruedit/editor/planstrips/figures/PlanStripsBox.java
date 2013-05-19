package at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.management.RuntimeErrorException;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FocusEvent;
import org.eclipse.draw2d.FocusListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import at.ac.tuwien.ieg.asbruedit.Activator;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.handlers.ShowConditionsHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.handlers.ShowPlanNamesHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences.PreferenceConstants;
import at.ac.tuwien.ieg.asbruedit.helpers.ColorTool;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;
import at.ac.tuwien.ieg.asbruedit.helpers.UIShortcut;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.view.condition.ConditionView;

/**
 * 
 * The plan strips box is the basic building block of the PlanStrips editor.
 *
 */
public class PlanStripsBox extends Figure {
	public static final int DEFAULT_FONT_SIZE = 10;
	protected static final float FOCUS_LEVEL_FULL = 1f;
	public static final int HIGHLIGHT_NONE = EditPart.SELECTED_NONE;
	public static final int HIGHLIGHT_WEAK = EditPart.SELECTED;
	public static final int HIGHLIGHT_STRONG = EditPart.SELECTED_PRIMARY;
	private Figure heading;
	private Figure conditionContainer;
	private Label label;
	private Color strongHighlightLabelColor;
	private Color defaultLabelColor = ColorConstants.black;
	private PlanType type;
	private ToolbarFigure content;
	private LineBorder lineBorder;
	private int highlightLevel;
	private HashMap<ConditionsType, ConditionFigure> conditionStatuses = new HashMap<ConditionsType, ConditionFigure>();

	public PlanStripsBox() {
		// allow focus requesting which is needed for mouse clicks
		setRequestFocusEnabled(true);
		// create a border layout for this component
		BorderLayout layout = new BorderLayout();
		layout.setVerticalSpacing(3);
		this.setLayoutManager(layout);
		setLayoutManager(layout);
		
		setOpaque(true);

		lineBorder = new LineBorder(2);
		MarginBorder mb = new MarginBorder(5);
		CompoundBorder cb = new CompoundBorder(lineBorder, mb);
		setBorder(cb);

		label = new Label();
		label.setLabelAlignment(PositionConstants.LEFT);
		heading = new Figure();
//		ToolbarLayout tbl = new ToolbarLayout(true);
//		tbl.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
//		tbl.setSpacing(5);
//		tbl.setStretchMinorAxis(true);
		BorderLayout headingLayout = new BorderLayout();
		headingLayout.setHorizontalSpacing(5);
		heading.setLayoutManager(headingLayout);
		add(heading, BorderLayout.TOP);
		
		conditionContainer = new Figure();
		ToolbarLayout conditionContainerLayout = new ToolbarLayout(true);
		conditionContainerLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
		conditionContainerLayout.setSpacing(5);
		conditionContainerLayout.setStretchMinorAxis(true);
		conditionContainer.setLayoutManager(conditionContainerLayout);
		
		content = new ToolbarFigure();
		add(content, BorderLayout.CENTER);
		highlightLevel = HIGHLIGHT_NONE;
		setFocus(0.2f);
		
		ConditionsType conditionType = ConditionsType.filter_precondition;		
		ConditionFigure filterFigure = new ConditionFigure(conditionType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-filter_active_16x16.png").createImage());
		filterFigure.addMouseListener(new ConditionViewController(conditionType));
		conditionStatuses.put(conditionType, 
				filterFigure);
		
		conditionType = ConditionsType.setup_precondition;	
		ConditionFigure setupFigure = new ConditionFigure(conditionType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-setup_active_16x16.png").createImage());
		setupFigure.addMouseListener(new ConditionViewController(conditionType));
		conditionStatuses.put(conditionType, 
				setupFigure);

		conditionType = ConditionsType.complete_condition;	
		ConditionFigure completeFigure = new ConditionFigure(conditionType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-complete_active_16x16.png").createImage());
		completeFigure.addMouseListener(new ConditionViewController(conditionType));
		conditionStatuses.put(conditionType, 
				completeFigure);

		conditionType = ConditionsType.suspend_condition;	
		ConditionFigure suspendFigure = new ConditionFigure(conditionType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-suspend_active_16x16.png").createImage());
		suspendFigure.addMouseListener(new ConditionViewController(conditionType));
		conditionStatuses.put(conditionType, 
				suspendFigure);

		conditionType = ConditionsType.reactivate_condition;	
		ConditionFigure reactiveFigure = new ConditionFigure(conditionType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-reactivate_active_16x16.png").createImage());
		reactiveFigure.addMouseListener(new ConditionViewController(conditionType));
		conditionStatuses.put(conditionType, 
				reactiveFigure);

		conditionType = ConditionsType.abort_condition;	
		ConditionFigure abortFigure = new ConditionFigure(conditionType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-abort_active_16x16.png").createImage());
		abortFigure.addMouseListener(new ConditionViewController(conditionType));
		conditionStatuses.put(conditionType, 
				abortFigure);
	}

	/**
	 * Toggle showing of conditions on/off depending on global state and internal values (conditions can still be individually activated/de-activated in the background)
	 */
	public void updateShowConditions() {
		boolean anyActive = false;
		for(ConditionFigure currentFigure : conditionStatuses.values()) {
			if(currentFigure.isActive()) {
				anyActive = true;
				break;
			}
		}

		boolean conditionContainerVisible = isConditionContainerActive();
		boolean showConditions = ShowConditionsHandler.isShowConditions();
		// hide conditions if they are visible already and either should not be shown or none are activated
		if(conditionContainerVisible && (!anyActive || !showConditions)) {
			heading.remove(conditionContainer);
		}
		// show the conditions if they are not shown yet, and at least one conditions is active and they should be shown or the box is currently highlighted
		else if(!conditionContainerVisible && anyActive && (showConditions || this.highlightLevel == HIGHLIGHT_STRONG)) {
			heading.add(conditionContainer, BorderLayout.CENTER);
		}
	}
	
	/**
	 * Toggle showing of plan name label on/off depending on global state and internal values
	 */
	public void updateShowLabel() {
		// if there is text and we want it to show
		boolean isValidText = label.getText() != null && !"".equals(label.getText());
		boolean labelVisible = isLabelActive();
		boolean showLabel = ShowPlanNamesHandler.isShowPlanNames();
		
		// hide the label if it is visible and either the text is invalid or should not be shown
		if(labelVisible && (!isValidText || !showLabel)) {
			heading.remove(label);
		}
		// show the label if it is not visible yet, and the text is valid, and the label should be shown or the box is currently highlighted
		else if(!labelVisible && isValidText && (showLabel || this.highlightLevel == HIGHLIGHT_STRONG)){
			heading.add(label, BorderLayout.LEFT);
		}
	}
	
	private boolean isLabelActive() {
		return heading.equals(label.getParent());
	}
	
	private boolean isConditionContainerActive() {
		return heading.equals(conditionContainer.getParent());
	}
	
	public void setConditionActive(ConditionsType condition, boolean isActive) {
		ConditionFigure conditionFigure = conditionStatuses.get(condition);
		conditionFigure.setActive(isActive);
		if(isActive) {
			conditionContainer.add(conditionFigure);
		}
		else {
			conditionContainer.remove(conditionFigure);
		}
		updateShowConditions();
	}
	
	public boolean isConditionActive(ConditionsType condition) {
		return conditionStatuses.get(condition).isActive();
	}
	
	/**
	 * Sets the focus level of the box. Focus can be between 0 and 1, where 1 = normal visibility.
	 * This changes the boxes visual appearence.
	 * @param focus
	 */
	public void setFocus(float focus) {
		if(focus == -1) {
			focus = FOCUS_LEVEL_FULL;
		}
		else if(focus < FOCUS_LEVEL_FULL) {
			focus = FOCUS_LEVEL_FULL;
		}
		setFontSize((int)(focus * (float)DEFAULT_FONT_SIZE));
	}
	
	public void setHeading(String text) {
		this.label.setText(text);
		updateShowLabel();
	}
	
	public String getHeading() {
		return label.getText();
	}
	
	public void setStrongHighlightHeadingColor(Color color) {
		this.strongHighlightLabelColor = color;
	}
	
	public void setFontSize(int fontSize) {
		Font initialFont = null;
		if(label.getFont() == null) {
			Shell shell = new Shell(Display.getCurrent());
			Text provider = new Text(shell, SWT.MULTI);
			initialFont = provider.getFont();
		}
		else {
			initialFont = label.getFont();
		}
		FontData[] fontData = initialFont.getFontData();
		for (int i = 0; i < fontData.length; i++) {
			fontData[i].setHeight(fontSize);
		}
		Font newFont = new Font(Display.getCurrent(), fontData);
		label.setFont(newFont);
	}
	
	public void setType(PlanType type) {
		this.type = type;
		content.changeOrientation(type.makesHorizontalLayout());
		updateHighlight();
	}
	
	public void setHighlightLevel(int highlightLevel) {
		this.highlightLevel = highlightLevel;
		updateHighlight();
	}
	
	private void updateHighlight() {
		if(type == null) {
			return;
		}
		switch(highlightLevel) {
		case HIGHLIGHT_STRONG:
			setBackgroundColor(type.readActiveColor());
			lineBorder.setColor(PreferenceUtil.readColor(PreferenceConstants.COLOR_BOX_BORDER_ACTIVE));
			if(strongHighlightLabelColor != null) {
				label.setForegroundColor(strongHighlightLabelColor);
			}
			break;
		case HIGHLIGHT_WEAK:
			setBackgroundColor(
					ColorTool.mixColors(
							type.readPassiveColor(), 
							type.readActiveColor(), 
							0.35f));
			lineBorder.setColor(
					ColorTool.mixColors(
							PreferenceUtil.readColor(PreferenceConstants.COLOR_BOX_BORDER_PASSIVE),
							PreferenceUtil.readColor(PreferenceConstants.COLOR_BOX_BORDER_ACTIVE), 
							0.8f));
			label.setForegroundColor(defaultLabelColor);
			break;
		default: //HIGHLIGHT_NONE or invalid
			setBackgroundColor(type.readPassiveColor());
			lineBorder.setColor(PreferenceUtil.readColor(PreferenceConstants.COLOR_BOX_BORDER_PASSIVE));
			label.setForegroundColor(defaultLabelColor);
			break;
		}
		updateShowLabel();
		updateShowConditions();
	}
	
	public ToolbarFigure getContentPane() {
		return content;
	}
	
	public static class ConditionFigure extends Label {
		private boolean active;
		private Image activeImage;

		public ConditionFigure(ConditionsType figureType, Image activeImage) {
			this.activeImage = activeImage;
			setActive(false);
			setToolTip(new Label(figureType.getDisplayName()));
		}
		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
			if(isActive()) {
				this.setIcon(activeImage);
				this.setVisible(true);
			}
			else {
				this.setIcon(null);
				this.setVisible(false);
			}
		}
	}
	
	public static class ConditionViewController implements MouseListener {
		ConditionsType type;

		public ConditionViewController(ConditionsType type) {
			this.type = type;
		}
		
		@Override
		public void mousePressed(MouseEvent me) {
			ConditionView view = (ConditionView)UIShortcut.getActivePageView(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), ConditionView.ID);
			view.setConditionType(type);
		}

		@Override
		public void mouseReleased(MouseEvent me) {
		}

		@Override
		public void mouseDoubleClicked(MouseEvent me) {
		}
	}
	
	public static enum DetailLevel {
		full,
		none;
	}
}
