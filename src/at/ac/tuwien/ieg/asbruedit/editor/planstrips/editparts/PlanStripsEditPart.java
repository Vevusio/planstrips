package at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelEventConstants;
import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.editor.PlanLibraryProvider;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editparts.PlanEditPart.PropertySource;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.DeleteEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.editpolicies.PlanStripsSelectionEditPolicy;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.PlanStripsBox;
import at.ac.tuwien.ieg.asbruedit.editor.planstrips.figures.ToolbarFigure;
import at.ac.tuwien.ieg.asbruedit.model.ModelTransformer;
import at.ac.tuwien.ieg.asbruedit.model.asbru.AbortCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CompleteCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.FilterPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.IfThenElse;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanLibrary;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ReactivateCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SetupPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SuspendCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.UserPerformed;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.UnhandledModelElement;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.transformers.IdentityTransformer;

public abstract class PlanStripsEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener, AsbruModelEventConstants, PlanLibraryProvider {
	/**
	 * Classes that are processed by the editor directly (ie. they have
	 * associated edit parts) or using a transformer (ie. they can be
	 * transformed to a planstrips specific model element using the provided
	 * transformer)
	 */
	protected static HashMap<Class<?>, ModelTransformer<?,?>> supportedChildClasses;
	
	public int depth = 0;
	public int widthAtDepth;
	public static int total = 0;
	public static HashMap<Integer, LinkedList<PlanStripsEditPart>> all = new HashMap<Integer, LinkedList<PlanStripsEditPart>>();

	static {
		supportedChildClasses = new HashMap<Class<?>, ModelTransformer<?,?>>();
		supportedChildClasses.put(Subplans.class, new IdentityTransformer());
		supportedChildClasses.put(PlanActivation.class, new IdentityTransformer());
		supportedChildClasses.put(Plan.class, new IdentityTransformer());
		supportedChildClasses.put(UserPerformed.class, new IdentityTransformer());
		supportedChildClasses.put(CyclicalPlan.class, new IdentityTransformer());
	}

	@Override
	protected IFigure createFigure() {
		return new ToolbarFigure();
	}

	@Override
	public IFigure getContentPane() {
		IFigure contentPane = getFigure();
		if(getFigure() instanceof PlanStripsBox) {
			contentPane = ((PlanStripsBox)getFigure()).getContentPane();
		}
		return contentPane;
	}

	/**
	 * Convenience method to to deal with the multitude of child elements that a
	 * plan-body can contain, and that the child elements themselves can
	 * contain, this method filters these child lists in regard to elements that
	 * can actually be displayed or processed by the editor.
	 * 
	 * @param children
	 *            child elements contained by a model element
	 * @return new list of child elements according to the generic support list
	 */
	protected LinkedList<Object> buildSupportedModelChildren(Collection<Object> children) {
		LinkedList<Object> supportedChildren = new LinkedList<Object>();
		for (Object cChild : children) {
			ModelTransformer transformer = supportedChildClasses.get(cChild.getClass());
			if (transformer != null) {
				supportedChildren.add(transformer.transform(cChild));
			} else {
				supportedChildren.add(new UnhandledModelElement(cChild));
			}
		}
		return supportedChildren;
	}

	@Override
	protected void createEditPolicies() {
		EditPolicy selectionFeedbackEditPolicy = createSelectionFeedbackEditPolicy();
		if(selectionFeedbackEditPolicy != null) {
			installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, createSelectionFeedbackEditPolicy());
		}
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new DeleteEditPolicy());
		
	}
	
	protected EditPolicy createSelectionFeedbackEditPolicy() {
		return new PlanStripsSelectionEditPolicy();
	}
	
	/**
	 * Sets the focus level of the part. Focus can be between -1 (reset) or [0,1], where 1 = normal visibility.
	 * This changes the visual appearance.
	 * @param focus
	 */
	public void setFocusIntensity(float focus) {
		if(getFigure() instanceof PlanStripsBox) {
			PlanStripsBox box = (PlanStripsBox)getFigure();
			box.setFocus(focus);
		}
	}
	
	/**
	 * Resets the focus level to default.
	 */
	public void resetFocusItensity() {
		setFocusIntensity(-1f);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(
				PROPERTY_ADD_CHILD.equals(evt.getPropertyName()) || 
				PROPERTY_REMOVE_CHILD.equals(evt.getPropertyName())) {
			this.refreshChildren();
		}
	}

	@Override
	public void activate() {
		AsbruModelHandler.instance().addPropertyChangeListener(getModel(), this);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		AsbruModelHandler.instance().removePropertyChangeListener(getModel(), this);
		super.deactivate();
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	/**
	 * Method used to delegate the selection, etc. of this part from the actual host to a delegate.
	 * By default there is no delegation and the host stays the actual editpart.
	 * @return The desired host of the selection
	 */
	public PlanStripsEditPart getSelectionDelegate() {
		return this;
	}
	
	@Override
	public PlanLibrary getPlanLibrary() {
		return getModel() instanceof PlanLibrary ? (PlanLibrary)getModel() : ((PlanStripsEditPart)getParent()).getPlanLibrary();
	}

	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IPropertySourceProvider.class)) {
			return makePropertySourceProvider();
		}
		return super.getAdapter(key);
	}
	
	protected IPropertySourceProvider makePropertySourceProvider() {
		final IPropertySource source = makePropertySource();
		IPropertySourceProvider provider = null;
		if(source != null) {
			provider = new IPropertySourceProvider() {
				private IPropertySource propertySource;
				
				//pseudo constructor
				{
					propertySource = source;
				}
				
				@Override
				public IPropertySource getPropertySource(Object object) {
					return propertySource;
				}
				
				
			};
		}
		return provider;
	}
	
	protected IPropertySource makePropertySource() {
		return null;
	}
	
	public abstract class AbstractChildIncludingPropertySource implements IPropertySource {
		public static final String PROP_CHILD = "ELEMENT_CHILD@";
		private HashMap<String, IPropertySourceProvider> childPropertySourceProviders = new HashMap<String, IPropertySourceProvider>();
		private LinkedList<IPropertyDescriptor> propertyDescriptorsList = new LinkedList<IPropertyDescriptor>();
		
		@Override
		public IPropertyDescriptor[] getPropertyDescriptors() {
			beforeGetPropertyDescriptors();
			contributePropertyDescriptors(getPropertyDescriptorsList());
			contributeChildPropertyDescriptors(getPropertyDescriptorsList());
			return propertyDescriptorsList.toArray(new IPropertyDescriptor[propertyDescriptorsList.size()]);
		}
		
		protected void beforeGetPropertyDescriptors() {
			getPropertyDescriptorsList().clear();
		}

		protected void contributeChildPropertyDescriptors(LinkedList<IPropertyDescriptor> propertyDescriptors) {
			// create child descriptors
			childPropertySourceProviders.clear();
			int childIndex = 0;
			for (AbstractEditPart cChild : (List<AbstractEditPart>) getChildren()) {
				IPropertySourceProvider childPropertySourceProvider = (IPropertySourceProvider) cChild.getAdapter(IPropertySourceProvider.class);
				if (childPropertySourceProvider != null) {
					String childKey = PROP_CHILD + cChild.hashCode();
					childPropertySourceProviders.put(childKey, childPropertySourceProvider);
					PropertyDescriptor childDescriptor = new PropertyDescriptor(childKey, cChild.getModel().getClass().getSimpleName());
					propertyDescriptors.add(childDescriptor);
				}
			}
		}

		protected abstract void contributePropertyDescriptors(LinkedList<IPropertyDescriptor> propertyDescriptors);

		@Override
		public Object getPropertyValue(Object id) {
			if (id instanceof String && ((String) id).startsWith(PROP_CHILD)) {
				return childPropertySourceProviders.get((String) id);
			}
			return getPropertyValueInternal(id);
		}
		
		protected abstract Object getPropertyValueInternal(Object id);

		public LinkedList<IPropertyDescriptor> getPropertyDescriptorsList() {
			return propertyDescriptorsList;
		}

		public void setPropertyDescriptorsList(LinkedList<IPropertyDescriptor> propertyDescriptorsList) {
			this.propertyDescriptorsList = propertyDescriptorsList;
		}

		public HashMap<String, IPropertySourceProvider> getChildPropertySourceProviders() {
			return childPropertySourceProviders;
		}

		public void setChildPropertySourceProviders(HashMap<String, IPropertySourceProvider> childPropertySourceProviders) {
			this.childPropertySourceProviders = childPropertySourceProviders;
		}
		
		@Override
		public Object getEditableValue() {
			return null;
		}

		@Override
		public boolean isPropertySet(Object id) {
			return false;
		}

		@Override
		public void resetPropertyValue(Object id) {
		}
	}
	
	public class ChildIncludingPropertySource extends AbstractChildIncludingPropertySource {
		@Override
		public void setPropertyValue(Object id, Object value) {
		}

		@Override
		protected void contributePropertyDescriptors(LinkedList<IPropertyDescriptor> propertyDescriptors) {
		}

		@Override
		protected Object getPropertyValueInternal(Object id) {
			return null;
		}
	}
}
