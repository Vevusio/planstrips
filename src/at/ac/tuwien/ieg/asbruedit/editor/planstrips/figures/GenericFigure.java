package at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.LayoutManager;

/**
 *
 * Generic figure that by default uses a {@link FlowLayout}
 *
 */
public class GenericFigure extends Figure {
	public GenericFigure() {
		super();
		setLayoutManager(new FlowLayout());
	}

	@Override
	public FlowLayout getLayoutManager() {
		return (FlowLayout)super.getLayoutManager();
	}
}
