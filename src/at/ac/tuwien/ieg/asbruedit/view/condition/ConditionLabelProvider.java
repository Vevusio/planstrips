package at.ac.tuwien.ieg.asbruedit.view.condition;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;

import at.ac.tuwien.ieg.asbruedit.helpers.ColorTool;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.condition.PlanPart;

public class ConditionLabelProvider extends StyledCellLabelProvider {
	private final Styler fBoldStyler;

	public ConditionLabelProvider(final Font boldFont) {
		fBoldStyler = new Styler() {
			public void applyStyles(TextStyle textStyle) {
				textStyle.font = boldFont;
			}
		};
	}

	public void update(ViewerCell cell) {
		PlanPart element = (PlanPart)cell.getElement();

		if (cell.getColumnIndex() == 0) {
			// Multi-font support only works in JFace 3.5 and above
			// (specifically, 3.5 M4 and above).
			// With JFace 3.4, the font information (bold in this example)
			// will be ignored.
			Styler style = fBoldStyler;
			StyledString styledString = new StyledString(element.getPlanName(), style);

			cell.setText(styledString.toString());
			cell.setStyleRanges(styledString.getStyleRanges());
			PlanType type = element.getPlanType();
			if(type == null) {
				type = PlanType.other;
			}
			cell.setBackground(type.readPassiveColor());
		} else if(cell.getColumnIndex() == 1) {
			String conditionString = element.makeConditionString();
			StyledString styledString = new StyledString(conditionString != null ? conditionString : "", StyledString.COUNTER_STYLER);

			cell.setText(conditionString);
			cell.setStyleRanges(styledString.getStyleRanges());
		}

		super.update(cell);
	}
}
