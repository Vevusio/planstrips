package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelEventConstants;
import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.SubplansEditPart.PropertySource;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.DeleteEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.PlanLayoutEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.PlanStripsSelectionEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Conditions;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;

public class PlanEditPart extends PlanStripsEditPart {
	private static final PlanType DEFAULT_PLAN_TYPE = PlanType.other;

	@Override
	protected IFigure createFigure() {
		PlanStripsBox box = new PlanStripsBox();
		box.setHeading(AsbruModelHandler.instance().getPlanName(getModel()));
		// start the plan type off as other in case it has no children at all
		box.setType(DEFAULT_PLAN_TYPE);
		// set up conditions
		Conditions conditions = getModel().getConditions();
		if(conditions != null) {
			if(conditions.getAbortCondition() != null) {
				box.setConditionActive(ConditionsType.abort_condition, true);
			}
			if(conditions.getCompleteCondition() != null) {
				box.setConditionActive(ConditionsType.complete_condition, true);
			}
			if(conditions.getSuspendCondition() != null) {
				box.setConditionActive(ConditionsType.suspend_condition, true);
			}
			if(conditions.getReactivateCondition() != null) {
				box.setConditionActive(ConditionsType.reactivate_condition, true);
			}
			if(conditions.getFilterPrecondition() != null) {
				box.setConditionActive(ConditionsType.filter_precondition, true);
			}
			if(conditions.getSetupPrecondition() != null) {
				box.setConditionActive(ConditionsType.setup_precondition, true);
			}
		}
		return box;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		super.propertyChange(evt);
		if (PROPERTY_TYPE.equals(evt.getPropertyName())) {
			changeType((PlanType) evt.getNewValue());
		} 
		else if (PROPERTY_NAME.equals(evt.getPropertyName())) {
			changeName((String) evt.getNewValue());
		}
		// if we remove the only child of the plan default it's type to reflect
		// the change
		else if (PROPERTY_REMOVE_CHILD.equals(evt.getPropertyName())) {
			if (!AsbruModelHandler.instance().planHasChild(getModel())) {
				changeType(DEFAULT_PLAN_TYPE);
			}
		}
	}

	@Override
	protected List<?> getModelChildren() {
		LinkedList<Object> children = buildSupportedModelChildren(AsbruModelHandler.instance().getModelChildren(getModel()));
		return children;
	}

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new PlanLayoutEditPolicy());
	}

	@Override
	public PlanStripsEditPart getSelectionDelegate() {
		// if this plan is the child of an activation, delegate selection to the
		// activation
		if (getParent() instanceof PlanActivationEditPart) {
			return (PlanStripsEditPart) getParent();
		} 
		else { /*these are not the droids you are looking for
                                            _
                                           /~\
                                          |oo )      
                                          _\=/_
                          ___        #   /  _  \   #
                         /() \        \\//|/.\|\\//
                       _|_____|_       \/  \_/  \/
                      | | === | |         |\ /|
                      |_|  O  |_|         \_ _/
                       ||  O  ||          | | |
                       ||__*__||          | | |
                      |~ \___/ ~|         []|[]
                      /=\ /=\ /=\         | | |
                      [_]_[_]_[_]        /_]_[_\ */
			return super.getSelectionDelegate();
		}
	}

	@Override
	public PlanStripsBox getFigure() {
		return (PlanStripsBox) super.getFigure();
	}

	@Override
	public Plan getModel() {
		return (Plan) super.getModel();
	}

	@Override
	protected void addChild(EditPart child, int index) {
		if (child instanceof PotentialPlanChildEditPart) {
			((PotentialPlanChildEditPart) child).addPropertyChangeListener(this);
		}
		super.addChild(child, index);
	}

	@Override
	protected void removeChild(EditPart child) {
		if (child instanceof PotentialPlanChildEditPart) {
			((PotentialPlanChildEditPart) child).removePropertyChangeListener(this);
		}
		super.removeChild(child);
	}

	protected void changeType(PlanType type) {
		getFigure().setType(type);
	}

	protected void changeName(String name) {
		getFigure().setHeading(name);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + AsbruModelHandler.instance().getPlanName(getModel()) + ")";
	}

	@Override
	protected IPropertySource makePropertySource() {
		return new PropertySource();
	}

	protected class PropertySource extends AbstractChildIncludingPropertySource {
		public static final String PROP_NAME = "PLAN_NAME";
		public static final String PROP_TITLE = "PLAN_TITLE";

		@Override
		public void setPropertyValue(Object id, Object value) {
			if (PROP_NAME.equals(id)) {
				AsbruModelHandler.instance().setPlanName(getModel(), (String) value);
			}
			else if (PROP_TITLE.equals(id)) {
				AsbruModelHandler.instance().setPlanTitle(getModel(), (String) value);
			}
		}

		@Override
		protected void contributePropertyDescriptors(LinkedList<IPropertyDescriptor> propertyDescriptors) {
			TextPropertyDescriptor nameDescriptor = new TextPropertyDescriptor(PROP_NAME, "Name");
			propertyDescriptors.add(nameDescriptor);
			TextPropertyDescriptor titleDescriptor = new TextPropertyDescriptor(PROP_TITLE, "Title");
			propertyDescriptors.add(titleDescriptor);
		}

		@Override
		protected Object getPropertyValueInternal(Object id) {
			if (PROP_NAME.equals(id)) {
				return AsbruModelHandler.instance().getPlanName(getModel());
			}
			else if (PROP_TITLE.equals(id)) {
				String title = AsbruModelHandler.instance().getPlanTitle(getModel());
				return title != null ? title : "";
			}
			return null;
		}
	}
}
