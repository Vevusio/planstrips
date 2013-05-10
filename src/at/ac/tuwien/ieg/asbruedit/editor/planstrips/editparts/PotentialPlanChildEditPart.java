package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelEventConstants;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.PlanStripsSelectionEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.ToolbarFigure;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;

/**
 * 
 * Edit parts who can either be direct descendants of {@link PlanEditPart} or contained within other arbitrary {@link EditPart}s.
 * The expected behavior of such edit parts is as follows:<br/>
 * If the parent of the edit part is a plan
 * <ul>
 * <li>the parts figure should seamlessly integrate into the parents box</li>
 * <li>the part should notify the parent about changes to its type so that the parent can update its box accordingly</li>
 * </ul> 
 * If the parent of the edit part is <em>not</em> a plan
 * <ul>
 * <li>the part should create its own box</li>
 * <li>the part should manage changes to its type autonomously</li>
 * </ul>
 */
public abstract class PotentialPlanChildEditPart extends PlanStripsEditPart {
	public static final String PROP_TYPE = "property type";
	private PropertyChangeSupport propertyChangeListeners;
	
	@Override
	protected IFigure createFigure() {
		// if the parent of the edit part is a plan, the edit part's figure should be "transparent" and integrate into the plan's box
		// (ie. use a void figure which is invisible) - it should also reflect the ordering (horizontal or vertical) based on the type
		if(isPlanChild()) {
			return createFigureForPlanParent();
		}
		// if the subplans is contained in some other structure (eg. another subplans) give it a dedicated box
		else {
			return createFigureForGenericParent();
		}
	}
	
	/**
	 * Create figure method when the parent of the edit part is a {@link PlanEditPart}
	 */
	protected IFigure createFigureForPlanParent() {
		ToolbarFigure figure = new ToolbarFigure(getType().makesHorizontalLayout());
		return figure;
	}
	
	/**
	 * Create figure method when the parent of the edit part is a {@link PlanEditPart}
	 */
	protected PlanStripsBox createFigureForGenericParent() {
		PlanStripsBox box = new PlanStripsBox();
		box.setType(getType());
		return box;
	}
	
	/**
	 * Method which to be called whenever the type of this edit part changes
	 */
	protected void onTypeChange() {
		getPropertyChangeListeners().firePropertyChange(AsbruModelEventConstants.PROPERTY_TYPE, null, getType());
	}

	@Override
	public void setParent(EditPart parent) {
		super.setParent(parent);
		//since the parent has just changed we might have to notify it about the current type
		onTypeChange();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeListeners().addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeListeners().removePropertyChangeListener(listener);
	}
	
	protected PropertyChangeSupport getPropertyChangeListeners() {
		if(propertyChangeListeners == null) {
			propertyChangeListeners = new PropertyChangeSupport(this);
		}
		return propertyChangeListeners;
	}
	
	/**
	 * @return true if this part is the direct child of a {@link PlanEditPart}, false otherwise
	 */
	public boolean isPlanChild() {
		return getParent() instanceof PlanEditPart;
	}

	public abstract PlanType getType();
}
