package at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;

/**
 * 
 * Figure with preset spacing etc. which orders its children horizontally or
 * vertically.
 * 
 */
public class ToolbarFigure extends Figure {
	ToolbarLayout layout;

	public ToolbarFigure() {
		this(false);
	}

	public ToolbarFigure(boolean hasHorizontalLayout) {
		layout = new ToolbarLayout(hasHorizontalLayout);
		layout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
		layout.setSpacing(5);
		layout.setStretchMinorAxis(true);
		setLayoutManager(layout);
	}

	public void changeOrientation(boolean hasHorizontalLayout) {
		List<IFigure> children = new LinkedList<IFigure>(this.getChildren());
		this.removeAll();
		layout.setHorizontal(hasHorizontalLayout);
		for(IFigure cChild : children) {
			add(cChild);
		}
	}
}
