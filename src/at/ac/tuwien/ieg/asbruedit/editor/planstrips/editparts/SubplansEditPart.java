package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.beans.PropertyChangeEvent;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.PlanStripsLayoutEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.PlanStripsSelectionEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.ToolbarFigure;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;

public class SubplansEditPart extends PotentialPlanChildEditPart {
	@Override
	protected List<?> getModelChildren() {
		LinkedList<Object> children = buildSupportedModelChildren(AsbruModelHandler.instance()
				.getModelChildren(getModel()));
		return children;
	}

	@Override
	public Subplans getModel() {
		return (Subplans) super.getModel();
	}

	@Override
	public PlanType getType() {
		return AsbruModelHandler.instance().getType(getModel());
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PlanStripsLayoutEditPolicy());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + getType() + ")";
	}
	
	@Override
	public PlanStripsEditPart getSelectionDelegate() {
		// if this subplans is the child of a plan delegate selection to the plan
		if(getParent() instanceof PlanEditPart) {
			return (PlanStripsEditPart)getParent();
		}
		else {
			return super.getSelectionDelegate();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		if(PROPERTY_TYPE.equals(evt.getPropertyName())) {
			PlanType newType = (PlanType)evt.getNewValue();
			// update own box if we have one
			if(getFigure() instanceof PlanStripsBox) {
				((PlanStripsBox)getFigure()).setType(newType);
			}
			else {
				((ToolbarFigure)getFigure()).changeOrientation(newType.makesHorizontalLayout());
			}
			// notify listeners about the change
			getPropertyChangeListeners().firePropertyChange(evt);
		}
	}
	
	@Override
	protected IPropertySource makePropertySource() {
		return new PropertySource();
	}

	protected class PropertySource implements IPropertySource {
		private String[] typeValues = PlanType.getSubplansTypesArray();
		
		@Override
		public IPropertyDescriptor[] getPropertyDescriptors() {
			ComboBoxPropertyDescriptor typeDescriptor = new ComboBoxPropertyDescriptor(PotentialPlanChildEditPart.PROP_TYPE, "Type", typeValues);
			return new IPropertyDescriptor[] {typeDescriptor}; 
		}

		@Override
		public Object getPropertyValue(Object id) {
			if(PROP_TYPE.equals(id)) {
				PlanType currentType = getType();
				for(int i = 0; i < typeValues.length; i++) {
					if(currentType.getId().equals(typeValues[i])) {
						return i;
					}
				}
			}
			return null;
		}

		@Override
		public boolean isPropertySet(Object id) {
			return false;
		}

		@Override
		public void resetPropertyValue(Object id) {
		}

		@Override
		public void setPropertyValue(Object id, Object value) {
			if(PROP_TYPE.equals(id)) {
				PlanType type = PlanType.fromString(typeValues[(Integer)value]);
				if(AsbruModelHandler.instance().canSetSubplansType(type)) {
					AsbruModelHandler.instance().setType(getModel(), type);
				}
			}
		}

		@Override
		public Object getEditableValue() {
			return null;
		}
	}
}
