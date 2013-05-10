package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PotentialPlanChildEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.UnhandledModelElementEditPart;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences.PreferenceConstants;
import at.ac.tuwien.ieg.asbruedit.helpers.ColorTool;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;

public class BreadcrumbLabelProvider implements ITreePathLabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if(element instanceof PlanEditPart) {
			Plan plan = ((PlanEditPart)element).getModel();
			// set the display name to the name of the plan
			label.setText(AsbruModelHandler.instance().getPlanName(plan));
			// set the display icon to the type of the plan
			PlanType type = AsbruModelHandler.instance().getType(plan);
			label.setImage(makeTypeIcon(type != null ? type : PlanType.other));
		}
		else if(element instanceof PotentialPlanChildEditPart) {
			// set the display icon to the type
			label.setImage(makeTypeIcon(((PotentialPlanChildEditPart)element).getType()));
		}
		else if(element instanceof UnhandledModelElementEditPart) {
			// set the display name to the name of the plan
			label.setText(((UnhandledModelElementEditPart)element).getModel().getClass().getSimpleName());
			// set the display icon to the type
			label.setImage(makeTypeIcon(((UnhandledModelElementEditPart)element).getType()));
		}
		else if(BreadcrumbContentProvider.NO_INPUT.equals(element)) {
			// don't do anything
		}
		else {
			label.setText(element.toString());
			label.setImage(makeTypeIcon(PlanType.other));
		}
	}

	protected Image makeTypeIcon(PlanType type) {
		return ColorTool.createGradientBox(new Point(16, 16), 1, type.readActiveColor(), type.readPassiveColor());
	}
}
