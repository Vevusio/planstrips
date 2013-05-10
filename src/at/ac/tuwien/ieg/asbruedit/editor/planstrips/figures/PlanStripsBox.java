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
import org.eclipse.ui.plugin.AbstractUIPlugin;

import at.ac.tuwien.ieg.asbruedit.Activator;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences.PreferenceConstants;
import at.ac.tuwien.ieg.asbruedit.helpers.ColorTool;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;

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
	private PlanType type;
	private ToolbarFigure content;
	private LineBorder lineBorder;
	private int highlightLevel;
	private DetailLevel detailLevel;
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
		ToolbarLayout tbl = new ToolbarLayout(true);
		tbl.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
		tbl.setSpacing(5);
		tbl.setStretchMinorAxis(true);
		heading.setLayoutManager(tbl);
		heading.add(label);
		
		conditionContainer = new Figure();
		ToolbarLayout conditionContainerLayout = new ToolbarLayout(true);
		conditionContainerLayout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
		conditionContainerLayout.setSpacing(5);
		conditionContainerLayout.setStretchMinorAxis(true);
		conditionContainer.setLayoutManager(conditionContainerLayout);
		
		content = new ToolbarFigure();
		add(content, BorderLayout.CENTER);
		highlightLevel = HIGHLIGHT_NONE;
		setDetailLevel(DetailLevel.full);
		setFocus(0.2f);
		
		ConditionsType figureType = ConditionsType.filter_precondition;		
		ConditionFigure filterFigure = new ConditionFigure(figureType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-filter_active_16x16.png").createImage());
		conditionStatuses.put(figureType, 
				filterFigure);
		
		figureType = ConditionsType.setup_precondition;	
		ConditionFigure setupFigure = new ConditionFigure(figureType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-setup_active_16x16.png").createImage());
		conditionStatuses.put(figureType, 
				setupFigure);

		figureType = ConditionsType.complete_condition;	
		ConditionFigure completeFigure = new ConditionFigure(figureType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-complete_active_16x16.png").createImage());
		conditionStatuses.put(figureType, 
				completeFigure);

		figureType = ConditionsType.suspend_condition;	
		ConditionFigure suspendFigure = new ConditionFigure(figureType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-suspend_active_16x16.png").createImage());
		conditionStatuses.put(figureType, 
				suspendFigure);

		figureType = ConditionsType.reactivate_condition;	
		ConditionFigure reactiveFigure = new ConditionFigure(figureType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-reactivate_active_16x16.png").createImage());
		conditionStatuses.put(figureType, 
				reactiveFigure);

		figureType = ConditionsType.abort_condition;	
		ConditionFigure abortFigure = new ConditionFigure(figureType, AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/condition-abort_active_16x16.png").createImage());
		conditionStatuses.put(figureType, 
				abortFigure);
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
		boolean anyActive = false;
		for(ConditionFigure currentFigure : conditionStatuses.values()) {
			if(currentFigure.isActive()) {
				anyActive = true;
				break;
			}
		}
		// if a condition is active and the condition container is not already added
		boolean conditionContainerVisible = heading.equals(conditionContainer.getParent());
		if(anyActive && !conditionContainerVisible) {
			heading.add(conditionContainer);
		}
		else if(!anyActive && conditionContainerVisible){
			heading.remove(conditionContainer);
		}
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
		
		// if there is text and we want it to show
		if(text != null && !"".equals(text) && !DetailLevel.none.equals(detailLevel)) {
			add(heading, BorderLayout.TOP);
		}
		// else the text is invalid or we don't want it to show remove the heading if it is currently being shown
		else if(heading.getParent() == this){
			remove(heading);
		}
	}
	
	public String getHeading() {
		return label.getText();
	}
	
	private void setFontSize(int fontSize) {
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
	
	public void setDetailLevel(DetailLevel detailLevel) {
		if(this.detailLevel == detailLevel) {
			return;
		}
		this.detailLevel = detailLevel;
		// re-set the heading, let the method figure out the display properties for itself
		setHeading(getHeading());
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
			break;
		default: //HIGHLIGHT_NONE or invalid
			setBackgroundColor(type.readPassiveColor());
			lineBorder.setColor(PreferenceUtil.readColor(PreferenceConstants.COLOR_BOX_BORDER_PASSIVE));
			break;
		}
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
	
	public static enum DetailLevel {
		full,
		none;
	}
}
