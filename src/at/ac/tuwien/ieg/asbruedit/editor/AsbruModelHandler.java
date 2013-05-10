package at.ac.tuwien.ieg.asbruedit.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

import javax.naming.LinkRef;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;

import at.ac.tuwien.ieg.asbruedit.exception.ModelSemanticException;
import at.ac.tuwien.ieg.asbruedit.exception.ObjectTraversalException;
import at.ac.tuwien.ieg.asbruedit.exception.UnknownModelElementException;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.AbortCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CompleteCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Conditions;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ConstraintCombination;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Context;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlanBody;
import at.ac.tuwien.ieg.asbruedit.model.asbru.FilterPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.LeftHandSide;
import at.ac.tuwien.ieg.asbruedit.model.asbru.OnAbort;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanBody;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanGroup;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanLibrary;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanSchema;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanStateConstraint;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ReactivateCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.RightHandSide;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SetupPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SimpleCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SuspendCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.TimeAnnotation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ToBeDefined;
import at.ac.tuwien.ieg.asbruedit.model.asbru.UserPerformed;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ValueDescription;
import at.ac.tuwien.ieg.asbruedit.model.asbru.WaitFor;
import at.ac.tuwien.ieg.asbruedit.model.condition.AbstractConditionModelPart;

public class AsbruModelHandler implements AsbruModelEventConstants {
	public static final String PLAN_LIBRARY_HANDLER_KEY = "at.ac.tuwien.ieg.asbruedit.AsbruModelHandler";
	private static AsbruModelHandler instance;
	private HashMap<Object, PropertyChangeSupport> listeners = new HashMap<Object, PropertyChangeSupport>();
	
	private AsbruModelHandler() {
	}
	
	public static AsbruModelHandler instance() {
		if(instance == null) {
			instance = new AsbruModelHandler();
		}
		return instance;
	}
	
	/**
	 * Adds a listener that will be informed about changes on a specific model element
	 * @param model Model element whose changes should trigger events
	 * @param listener Listener to add
	 */
	public void addPropertyChangeListener(Object model, PropertyChangeListener listener) {
		if(!listeners.containsKey(model)) {
			PropertyChangeSupport support = new PropertyChangeSupport(model);
			listeners.put(model, support);
		}
		listeners.get(model).addPropertyChangeListener(listener);
	}
	
	/**
	 * Removes the listener for the model element. If there are no more listeners the model element is removed
	 * from the listener map.
	 * @param model Model element to remove listener for
	 * @param listener Listener to remove
	 */
	public void removePropertyChangeListener(Object model, PropertyChangeListener listener) {
		PropertyChangeSupport support = listeners.get(model);
		if(support != null) {
			support.removePropertyChangeListener(listener);
			if(support.getPropertyChangeListeners().length == 0) {
				listeners.remove(support);
			}
		}
		else {
			listeners.remove(support);
		}
	}
	
	private void notifyListeners(Object source, PropertyChangeEvent changeEvent) {
		PropertyChangeSupport support = listeners.get(source);
		if(support != null) {
			support.firePropertyChange(changeEvent);
		}
	}
	
	public Plan getRootPlan(PlanLibrary library) throws ObjectTraversalException, ModelSemanticException {
		//gather all plans which are referenced by other plans as sub-plans
		LinkedList<Plan> invokedPlans = new LinkedList<Plan>();
		LinkedList<PlanActivation> activations = getObjectsByClass(library, PlanActivation.class);
		for(PlanActivation activation : activations) {
			Object referencedItem = activation.getPlanSchema().getName();
			if(referencedItem instanceof Plan) {
				invokedPlans.add((Plan)referencedItem);
			}
		}
		//gather all plans
		LinkedList<Plan> plans = getObjectsByClass(library, Plan.class);
		//subtract all invoked plans from all plans - this will leave non-invoked plans
		plans.removeAll(invokedPlans);
		//TODO : ask user or smth
		if(plans.size() != 1) {
			//throw new ModelSemanticException("No definitive root plan found. Orphan plans: " + plans.size() + " (should be 1)");
		}
		return plans.poll();
	}
	
	public <E> LinkedList<E> getObjectsByClass(Object root, Class<E> targetClass) throws ObjectTraversalException {
		return getObjectsByClass(root, targetClass, -1);
	}
	
	public <E> LinkedList<E> getObjectsByClass(PlanLibrary library, Class<E> targetClass, int maxDepth) throws ObjectTraversalException {
		return getObjectsByClass(library, targetClass, maxDepth);
	}
	
	public <E> LinkedList<E> getObjectsByClass(Object root, Class<E> targetClass, int maxDepth) throws ObjectTraversalException {
		LinkedList<E> objects = new LinkedList<E>();
		try {
			getObjectsByClass(root, targetClass, objects, new LinkedList<Object>(), maxDepth);
		} catch (IllegalArgumentException e) {
			throw new ObjectTraversalException(e);
		} catch (IllegalAccessException e) {
			throw new ObjectTraversalException(e);
		}
		return objects;
	}
	
	private <E> void getObjectsByClass(Object root, Class<E> targetClass, LinkedList<E> objects, LinkedList<Object> traversedObjects, int maxDepth) throws IllegalArgumentException, IllegalAccessException {
		if(traversedObjects.contains(root)) {
			return;
		}
		traversedObjects.add(root);
		// if the currently checked root element fits the criteria add it to the list
		if(targetClass.isAssignableFrom(root.getClass())) {
			objects.add(targetClass.cast(root));
		}
		if(maxDepth == 0) {
			return;
		}
		//then check all its contained fields
		Field[] fields = root.getClass().getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			Object fieldValue = field.get(root);
			field.setAccessible(false);
			if(fieldValue == null) {
				continue;
			}
			//first check if the field is a collection - if yes traverse that
			if(Collection.class.isAssignableFrom(field.getType())) {
				Collection<?> containedCollection = (Collection<?>) fieldValue;
				for(Object o : containedCollection) {
					getObjectsByClass(o, targetClass, objects, traversedObjects, maxDepth-1);
				}
			}
			//otherwise just check the object located at the field itself
			else {
				getObjectsByClass(fieldValue, targetClass, objects, traversedObjects, maxDepth-1);
			}
		}
	}
	
	public boolean isChildSupported(Class<?> targetClass, String targetField, Class<?> childClass) {
		try {
			XmlElements xmlElements = targetClass.getDeclaredField(targetField).getAnnotation(XmlElements.class);
			for(XmlElement cElement : xmlElements.value()) {
				if(cElement.type().isAssignableFrom(childClass)) {
					return true;
				}
			}
			return false;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException("Invalid constant in " + AsbruModelFieldNames.class.toString() + " for " + targetClass.toString(), e);
		}
	}
	
	private void insertBefore(List<Object> list, Object toInsert, Object before) {
		int insertPosition = 0;
		for(Object current : list) {
			if(before == current) {
				break;
			}
			insertPosition++;
		}
		list.add(insertPosition, toInsert);
	}
	
	/**
	 * get the default plan group to insert new plans into -- TODO this is currently 
	 * the first plan group the tool finds, maybe add a config option for this
	 * @param library library that contains the default plan group
	 */
	public PlanGroup getDefaultPlanGroup(PlanLibrary library) {
		return getObjectsByClass(library, PlanGroup.class).getFirst();
	}
	
	/* ******************************************************** */
	/* ************************* PLAN ************************* */
	/* ******************************************************** */		
	/**
	 * @param plan plan to look for
	 * @param library library of the model
	 * @return plan group containing the plan or null if none was found
	 */
	public PlanGroup getPlanGroupForPlan(Plan plan, PlanLibrary library) {
		for(PlanGroup group : getObjectsByClass(library, PlanGroup.class)) {
			if(group.getPlanOrPlanGroup().contains(plan)) {
				return group;
			}
		}
		return null;
	}
	
	/**
	 * Retrieves the plan of the given name from the library
	 * @param library library possible containing the plan
	 * @param name name of the plan to find
	 * @return the first matched plan or null if no plan was found
	 */
	public Plan getPlan(PlanLibrary library, String name) {
		LinkedList<Plan> plans;
		plans = getObjectsByClass(library, Plan.class);
		for(Plan currentPlan : plans) {
			if(currentPlan.getName().equals(name)) {
				return currentPlan;
			}
		}
		return null;
	}
	
	public void addPlan(Plan plan, PlanGroup planGroup) {
		planGroup.getPlanOrPlanGroup().add(plan);
	}
	
	/**
	 * @param plan plan to remove
	 * @param library library to remove the plan from
	 * @return plan group that the plan was removed from
	 */
	public PlanGroup removePlan(Plan plan, PlanLibrary library) {
		PlanGroup group = getPlanGroupForPlan(plan, library);
		group.getPlanOrPlanGroup().remove(plan);
		return group;
	}
	
	public void replacePlan(Plan oldPlan, Plan newPlan, PlanLibrary library) {
		PlanGroup group = getPlanGroupForPlan(oldPlan, library);
		// remove the old plan
		group.getPlanOrPlanGroup().remove(oldPlan);
		// add new plan
		group.getPlanOrPlanGroup().add(newPlan);
		// update all references
		LinkedList<PlanActivation> planActivations = getObjectsByClass(library, PlanActivation.class);
		for(PlanActivation currentActivation : planActivations) {
			if(oldPlan.equals(currentActivation.getPlanSchema().getName())) {
				currentActivation.getPlanSchema().setName(newPlan);
				notifyListeners(currentActivation, new PropertyChangeEvent(currentActivation, PROPERTY_CHANGE_CHILD, oldPlan, newPlan));
			}
		}
	}
	
	/**
	 * Checks whether or not a plan has a child (ie. plan body with a subplans/if-then-else/...)
	 * @param plan Plan to check
	 * @return true if a child is present, false otherwise
	 */
	public boolean planHasChild(Plan plan) {
		return 
				plan != null && 
				plan.getPlanBody() != null && 
				!plan.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().isEmpty();
	}
	
	/**
	 * Retrieves the child of the plan, this method may only be called if {@link #planHasChild(Plan)} was true
	 * @param plan Plan which's child to get
	 * @return Child of the plan
	 */
	public Object planGetChild(Plan plan) {
		return plan.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().get(0);
	}
	
	/**
	 * Checks if a child of this type can be added.
	 * @param plan Parent to check
	 * @param child Class of the child
	 * @return true if child is supported, false otherwise
	 */
	public boolean isChildSupported(Plan plan, Class<?> childClass) {
		return isChildSupported(PlanBody.class, AsbruModelFieldNames.PLAN_BODY_CHILDREN, childClass);
	}
	
	/**
	 * Checks whether or not a child can be added to a plan's body
	 * @param plan Plan to validate
	 * @param child Child to add
	 * @param before Child before which to add (null if last)
	 * @return true if a child can be added, false otherwise
	 */
	public boolean canAddChild(Plan plan, Object child, Object before) {
		// if the plan is null we can't add
		if(plan == null || child == null) {
			return false;
		}	
		if(
				// if the plan body is null we can add a child (by creating a new body)
				plan.getPlanBody() == null || 
				// if the plan body exists and has no children (yet) we can add a child as well
				// (according to the dtd a plan-body can only take one child; despite the plan body containing a children list
				plan.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().isEmpty()) {
			//being in here we have to check the child itself - add semantic checks as needed for asbru definition
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a child element to the plan, this method may only be invoked if {@link #canAddChild(Plan, Object, Object)} returns true
	 * @param plan Plan to add child to
	 * @param child Child to add
	 * @param before Child before which to add (null if last)
	 */
	public void addChild(Plan plan, Object child, Object before, PlanLibraryProvider libraryProvider) {
		if(plan.getPlanBody() == null) {
			plan.setPlanBody(createDefaultPlanBody());
		}
		List<Object> children = plan.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment();
		if(child instanceof PlanActivation) {
			boolean activationSuccessful = preparePlanActivationForInsert((PlanActivation)child, libraryProvider);
			if(!activationSuccessful) {
				return;
			}
		}
		insertBefore(children, child, before);
		notifyListeners(plan, new PropertyChangeEvent(plan, PROPERTY_ADD_CHILD, null, child));
	}
	
	/**
	 * Checks whether or not a child can be removed from a plan's body
	 * @param plan Plan to validate
	 * @param child Child to remove
	 * @return true if a child can be removed, false otherwise
	 */
	public boolean canRemoveChild(Plan plan, Object child) {
		// if the plan/child is null or the plan has no body or the body has no child list we can't remove
		if(plan == null || child == null || plan.getPlanBody() == null || null == plan.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment()) {
			return false;
		}	
		// otherwise we can remove if the plan body contains the child
		return plan.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().contains(child);
	}
	
	/**
	 * Remove a child element from the plan, this method may only be invoked if {@link #canRemoveChild(Plan, Object)} returns true
	 * @param plan Plan to remove child from
	 * @param child Child to remove
	 */
	public void removeChild(Plan plan, Object child) {
		plan.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().remove(child);
		notifyListeners(plan, new PropertyChangeEvent(plan, PROPERTY_REMOVE_CHILD, null, child));
	}
	
	public Plan createDefaultPlan() {
		Plan plan = new Plan();
		plan.setPlanBody(createDefaultPlanBody());
		return plan;
	}
	
	/**
	 * Returns the type of a plan
	 * @param plan Plan to get type for
	 * @return type of plan or null if none could be determined
	 */
	public PlanType getType(Plan plan) {
		if(planHasChild(plan)) {
			Object child = planGetChild(plan);
			if(child instanceof Subplans) {
				return getType((Subplans)child);
			}
			else if(child instanceof CyclicalPlan) {
				return getType((CyclicalPlan)child);
			}
			else if(child instanceof UserPerformed) {
				return getType((UserPerformed)child);
			}
		}
		return null;
	}
	
	/**
	 * Get the name associated with a plan
	 * @param plan Plan to retrieve name from
	 * @return name of the plan
	 */
	public String getPlanName(Plan plan) {
		return plan.getName();
	}
	
	/**
	 * Sets the name associated with a plan
	 * @param plan Plan to set name on
	 * @param name Name to set
	 */
	public void setPlanName(Plan plan, String name) {
		String oldValue = getPlanName(plan);
		plan.setName(name);
		notifyListeners(plan, new PropertyChangeEvent(plan, PROPERTY_NAME, oldValue, name));
	}
	
	/**
	 * Get the title associated with a plan
	 * @param plan Plan to retrieve title from
	 * @return title of the plan, null if no title was set
	 */
	public String getPlanTitle(Plan plan) {
		return plan.getTitle();
	}
	
	/**
	 * Sets the title associated with a plan
	 * @param plan Plan to set title on
	 * @param title Title to set
	 */
	public void setPlanTitle(Plan plan, String title) {
		String oldValue = getPlanTitle(plan);
		plan.setTitle(title);
		notifyListeners(plan, new PropertyChangeEvent(plan, PROPERTY_TITLE, oldValue, title));
	}
	
	public List<Object> getModelChildren(Plan model) {
		List<Object> children = model.getPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment();
		return children;
	}
	
	public PlanBody createDefaultPlanBody() {
		return new PlanBody();
	}
	
	/* ******************************************************** */
	/* ******************** CYCLICAL-PLAN ********************* */
	/* ******************************************************** */	
	/**
	 * Checks whether or not a cyclical plan has a child (ie. cyclical plan body with a subplans/if-then-else/...)
	 * @param plan Plan to check
	 * @return true if a child is present, false otherwise
	 */
	public boolean cyclicalPlanHasChild(CyclicalPlan plan) {
		return 
				plan != null && 
				plan.getCyclicalPlanBody() != null && 
				!plan.getCyclicalPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().isEmpty();
	}
	
	/**
	 * Checks if a child of this type can be added.
	 * @param plan Parent to check
	 * @param child Class of the child
	 * @return true if child is supported, false otherwise
	 */
	public boolean isChildSupported(CyclicalPlan plan, Class<?> childClass) {
		return isChildSupported(CyclicalPlanBody.class, AsbruModelFieldNames.CYCLICAL_PLAN_BODY_CHILDREN, childClass);
	}
	
	/**
	 * Checks whether or not a child can be added to a cyclical plan's body
	 * @param plan Cyclical-plan to validate
	 * @param child Child to add
	 * @param before Child before which to add (null if last)
	 * @return true if a child can be added, false otherwise
	 */
	public boolean canAddChild(CyclicalPlan plan, Object child, Object before) {
		// if the plan is null we can't add
		if(plan == null || child == null) {
			System.out.println("Child of cycplan is null");
			return false;
		}	
		if(
				// if the plan body is null we can add a child (by creating a new body)
				plan.getCyclicalPlanBody() == null || 
				// if the plan body exists and has no children (yet) we can add a child as well
				// (according to the dtd a plan-body can only take one child; despite the plan body containing a children list
				plan.getCyclicalPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().isEmpty()) {
			//being in here we have to check the child itself - add semantic checks as needed for asbru definition
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a child element to the cyclical plan, this method may only be invoked if {@link #canAddChild(CyclicalPlan, Object, Object)} returns true
	 * @param plan Plan to add child to
	 * @param before Child before which to add (null if last)
	 * @param child Child to add
	 */
	public void addChild(CyclicalPlan plan, Object child, Object before, PlanLibraryProvider libraryProvider) {
		if(plan.getCyclicalPlanBody() == null) {
			plan.setCyclicalPlanBody(createDefaultCyclicalPlanBody());
		}
		List<Object> children = plan.getCyclicalPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment();
		if(child instanceof PlanActivation) {
			boolean activationSuccessful = preparePlanActivationForInsert((PlanActivation)child, libraryProvider);
			if(!activationSuccessful) {
				return;
			}
		}
		insertBefore(children, child, before);
		notifyListeners(plan, new PropertyChangeEvent(plan, PROPERTY_ADD_CHILD, null, child));
	}
	
	/**
	 * Checks whether or not a child can be removed from a cyclical-plan's body
	 * @param plan CyclicalPlan to validate
	 * @param child Child to remove
	 * @return true if a child can be removed, false otherwise
	 */
	public boolean canRemoveChild(CyclicalPlan plan, Object child) {
		// if the plan/child is null or the plan has no body or the body has no child list we can't delete
		if(plan == null || child == null || plan.getCyclicalPlanBody() == null || null == plan.getCyclicalPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment()) {
			return false;
		}	
		// otherwise we can delete if the plan body contains the child
		return plan.getCyclicalPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().contains(child);
	}
	
	/**
	 * Removes a child element from the cyclical-plan, this method may only be invoked if {@link #canRemoveChild(CyclicalPlan, Object)} returns true
	 * @param plan CyclicalPlan to remove child from
	 * @param child Child to delete
	 */
	public void removeChild(CyclicalPlan plan, Object child) {
		plan.getCyclicalPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().remove(child);
		notifyListeners(plan, new PropertyChangeEvent(plan, PROPERTY_REMOVE_CHILD, null, child));
	}
	
	public PlanType getType(CyclicalPlan plan) {
		return PlanType.cyclical;
	}
	
	public CyclicalPlan createDefaultCyclicalPlan() {
		CyclicalPlan cyclicalPlan = new CyclicalPlan();
		cyclicalPlan.setCyclicalPlanBody(createDefaultCyclicalPlanBody());
		return cyclicalPlan;
	}
	
	public CyclicalPlanBody createDefaultCyclicalPlanBody() {
		CyclicalPlanBody cyclicalPlanBody = new CyclicalPlanBody();
		return cyclicalPlanBody;
	}
	
	public List<Object> getModelChildren(CyclicalPlan model) {
		return model.getCyclicalPlanBody().getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment();
	}
	
	/* ******************************************************** */
	/* *********************** SUBPLANS *********************** */
	/* ******************************************************** */	
	public Subplans copyElement(Subplans subPlans) {
		Subplans newSubplans = new Subplans();
		// TODO - this is not a deep copy - possible that race conditions might arise because of this
		for(Object current : subPlans.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment()) {
			newSubplans.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().add(current);
		}
		newSubplans.setAbortIf(subPlans.getAbortIf());
		newSubplans.setRetryAbortedSubplans(subPlans.getRetryAbortedSubplans());
		newSubplans.setWaitFor(subPlans.getWaitFor());
		newSubplans.setWaitForOptionalSubplans(subPlans.getWaitForOptionalSubplans());
		newSubplans.setLabel(subPlans.getLabel());
		newSubplans.setType(subPlans.getType());
		return newSubplans;
	}
	
	public boolean isCopySupported(Subplans subplans) {
		return subplans != null;
	}
	
	/**
	 * Checks if a child of this type can be added.
	 * @param subplans Parent to check
	 * @param child Class of the child
	 * @return true if child is supported, false otherwise
	 */
	public boolean isChildSupported(Subplans subplans, Class<?> childClass) {
		return isChildSupported(Subplans.class, AsbruModelFieldNames.SUBPLANS_CHILDREN, childClass);
	}
	
	/**
	 * Checks whether or not a child can be added to a subplans element
	 * @param subplans Subplans to validate
	 * @param child Child to add
	 * @param before Child before which to add (null if last)
	 * @return true if a child can be added, false otherwise
	 */
	public boolean canAddChild(Subplans subplans, Object child, Object before) {
		// if either is null we can't add
		if(subplans == null || child == null) {
			return false;
		}	
		//being in here we have to check the child itself - add semantic checks as needed for asbru definition
		return true;
	}
	
	/**
	 * Adds a child element to the subplans, this method may only be invoked if {@link #canAddChild(Subplans, Object, Object)} returns true
	 * @param plan Plan to add child to
	 * @param before Child before which to add (null if last)
	 * @param child Child to add
	 */
	public void addChild(Subplans subplans, Object child, Object before, PlanLibraryProvider libraryProvider) {
		List<Object> children = subplans.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment();
		if(child instanceof PlanActivation) {
			boolean activationSuccessful = preparePlanActivationForInsert((PlanActivation)child, libraryProvider);
			if(!activationSuccessful) {
				return;
			}
		}
		insertBefore(children, child, before);
		notifyListeners(subplans, new PropertyChangeEvent(subplans, PROPERTY_ADD_CHILD, null, child));
	}
	
	/**
	 * Checks whether or not a child can be removed from a subplans element
	 * @param subplans Subplans to validate
	 * @param child Child to remove
	 * @return true if a child can be removed, false otherwise
	 */
	public boolean canRemoveChild(Subplans subplans, Object child) {
		// if either is null or the subplans has no children list we can't add
		if(subplans == null || child == null || null == subplans.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment()) {
			return false;
		}	
		// otherwise we can delete if the subplans contain the child
		return subplans.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().contains(child);
	}
	
	/**
	 * Removes a child element from the subplans, this method may only be invoked if {@link #canRemoveChild(Subplans, Object)} returns true
	 * @param subplans Subplans to remove child from
	 * @param child Child to delete
	 */
	public void removeChild(Subplans subplans, Object child) {
		subplans.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().remove(child);
		notifyListeners(subplans, new PropertyChangeEvent(subplans, PROPERTY_REMOVE_CHILD, null, child));
	}
	
//	private PlanActivation subplansGetPlanActivationForPlan(Subplans subplans, Plan plan) {
//		for(Object cChild : subplans.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment()) {
//			if(cChild instanceof PlanActivation) {
//				PlanActivation activation = (PlanActivation)cChild;
//				if(activation.getPlanSchema() != null && activation.getPlanSchema().getName() == plan) {
//					return activation;
//				}
//			}
//		}
//		return null;
//	}

	public Subplans createDefaultSubplans(PlanType type) {
		Subplans subplans = new Subplans();
		subplans.setType(type.getId());
		WaitFor waitFor = new WaitFor();
		waitFor.getAllOrCardinalityOrNoneOrOneOrReferToOrStaticPlanPointerOrToBeDefinedOrWaitForGroupOrWaitForNot().add(new ToBeDefined());
		subplans.setWaitFor(waitFor);
		return subplans;
	}
	
	public List<Object> getModelChildren(Subplans model) {
		return model.getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment();
	}
	
	public PlanType getType(Subplans subplans) {
		return PlanType.fromString(subplans.getType().toLowerCase());
	}
	
	/**
	 * Checks if a {@link PlanType} can be applied to a {@link Subplans} using {@link #setType(Subplans, PlanType)}
	 * @param type Type to apply
	 * @return true if the type can be applied, false otherwise
	 */
	public boolean canSetSubplansType(PlanType type) {
		return type.isSubplansType();
	}
	
	/**
	 * Applies a type to a subplans, may only be called if {@link #canSetSubplansType(PlanType)} for the type was true
	 * @param subplans Subplans to set type upon
	 * @param type Type to set
	 */
	public void setType(Subplans subplans, PlanType type) {
		PlanType oldType = getType(subplans);
		subplans.setType(type.getId());
		notifyListeners(subplans, new PropertyChangeEvent(subplans, PROPERTY_TYPE, oldType, type));
	}
	
	/* ******************************************************** */
	/* ***************** USER-PERFORMED *********************** */
	/* ******************************************************** */	
	public UserPerformed createDefaultUserPerformed() {
		return new UserPerformed();
	}
	
	public PlanType getType(UserPerformed userPerformed) {
		return PlanType.userperformed;
	}
	
	/* ******************************************************** */
	/* ***************** PLAN-ACTICATION ********************** */
	/* ******************************************************** */		
	public PlanActivation copyElement(PlanActivation planActivation) {
		PlanActivation newActivation = new PlanActivation();
		// this simple re-uses the references and is not a deep copy
		// TODO - if onabort and onsuspend become editable this has to become a deep copy
		newActivation.setOnAbort(planActivation.getOnAbort());
		newActivation.setOnSuspend(planActivation.getOnSuspend());
		newActivation.setPlanSchema(planActivation.getPlanSchema());
		return newActivation;
	}
	
	public boolean isCopySupported(PlanActivation planActivation) {
		return planActivation != null && planActivation.getPlanSchema() != null && planActivation.getPlanSchema().getName() != null;
	}
	
	public List<Object> getModelChildren(PlanActivation model) {
		LinkedList<Object> children = new LinkedList<Object>();
		children.add(model.getPlanSchema().getName());
		return children;
	}
	
	public Plan getPlanActivationActivatingPlan(PlanActivation activation) {
		if(activation.getPlanSchema() != null && activation.getPlanSchema().getName() != null) {
			return (Plan)activation.getPlanSchema().getName();
		}
		return null;
	}
	
	public PlanActivation createDefaultPlanActivation() {
		PlanActivation activation = new PlanActivation();
		activation.setPlanSchema(createDefaultPlanSchema());
		return activation;
	}
	
	/**
	 * If the activation being inserted contains a new plan (i.e. a plan that has not yet been initialized,
	 * which is verified by checking the existance of a (mandatory) plan name) -- initialize the plan and
	 * add it to the model
	 * @param activation Activation to be inserted into a different model element
	 * @param libraryProvider 
	 * @return true if everything went ok, false if the initialization failed
	 */
	private boolean preparePlanActivationForInsert(PlanActivation activation, PlanLibraryProvider libraryProvider) {
		Plan plan = (Plan)(activation).getPlanSchema().getName();
		// no plan name exists -- thus the plan is not initialized
		if(plan.getName() == null) {
			NewPlanDialog newPlanDialog = new NewPlanDialog(null);
			newPlanDialog.create();
			if (newPlanDialog.open() == Window.OK) {
				plan.setName(newPlanDialog.getPlanName());
				plan.setTitle(newPlanDialog.getPlanTitle());
				
				PlanLibrary library = libraryProvider.getPlanLibrary();
				Plan matchPlan = getPlan(library, newPlanDialog.getPlanName());
				PlanGroup containerPlanGroup = null;
				// the plan we are adding is already contained
				if(matchPlan != null) {
					MessageDialog clashDialog = new MessageDialog(null, "Plan already exists", null,
						    "The plan name you entered is already in use. You can overwrite it or re-use the existing plan.", MessageDialog.QUESTION_WITH_CANCEL, 
						    new String[] { "Use existing plan", "Override existing plan", "Cancel insert" }, 0);
					int result = clashDialog.open();
					switch(result) {
					// use existing
					case 0:
						activation.getPlanSchema().setName(matchPlan);
						return true;
					// override
					case 1:
						replacePlan(matchPlan, plan, library);
						return true;
					// cancel
					default:
						return false;
					}
				}
				else {
					addPlan(plan, containerPlanGroup == null ? getDefaultPlanGroup(library) : containerPlanGroup);
				}
			} 
			else {
				// user did not click ok
				return false;
			}
		}
		// nothing to do, everything is ok
		return true;
	}
	
	/* ******************************************************** */
	/* ***************** PLAN-SCHEMA ************************** */
	/* ******************************************************** */	
	public PlanSchema createDefaultPlanSchema() {
		PlanSchema schema = new PlanSchema();
		schema.setName(createDefaultPlan());
		return schema;
	}
	
	/* ******************************************************** */
	/* ***************** ABORT-CONDITION ********************** */
	/* ******************************************************** */
	public List<Object> getModelChildren(AbortCondition model) {
		return model.getConstraintCombinationOrConstraintNotOrCountConstraintOrIsAutomaticOrIsManualOrNoneOrParameterPropositionOrParameterRefOrPlanStateConstraintOrReferToOrSimpleConditionOrTemporalConstraintOrToBeDefined();
	}

	/* ******************************************************** */
	/* ***************** COMPLETE-CONDITION ******************* */
	/* ******************************************************** */	
	public List<Object> getModelChildren(CompleteCondition model) {
		return model.getConstraintCombinationOrConstraintNotOrCountConstraintOrIsAutomaticOrIsManualOrNoneOrParameterPropositionOrParameterRefOrPlanStateConstraintOrReferToOrSimpleConditionOrTemporalConstraintOrToBeDefined();
	}
	
	/* ******************************************************** */
	/* ***************** FILTER-PRECONDITION ****************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(FilterPrecondition model) {
		return model.getConstraintCombinationOrConstraintNotOrCountConstraintOrIsAutomaticOrIsManualOrNoneOrParameterPropositionOrParameterRefOrPlanStateConstraintOrReferToOrSimpleConditionOrTemporalConstraintOrToBeDefined();
	}
	
	/* ******************************************************** */
	/* ***************** REACTIVATE-CONDITION ***************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(ReactivateCondition model) {
		return model.getConstraintCombinationOrConstraintNotOrCountConstraintOrIsAutomaticOrIsManualOrNoneOrParameterPropositionOrParameterRefOrPlanStateConstraintOrReferToOrSimpleConditionOrTemporalConstraintOrToBeDefined();
	}
	
	/* ******************************************************** */
	/* ***************** SETUP-PRECONDITION ******************* */
	/* ******************************************************** */	
	public List<Object> getModelChildren(SetupPrecondition model) {
		return model.getConstraintCombinationOrConstraintNotOrCountConstraintOrIsAutomaticOrIsManualOrNoneOrParameterPropositionOrParameterRefOrPlanStateConstraintOrReferToOrSimpleConditionOrTemporalConstraintOrToBeDefined();
	}
	
	/* ******************************************************** */
	/* ***************** SUSPEND-CONDITION ******************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(SuspendCondition model) {
		return model.getConstraintCombinationOrConstraintNotOrCountConstraintOrIsAutomaticOrIsManualOrNoneOrParameterPropositionOrParameterRefOrPlanStateConstraintOrReferToOrSimpleConditionOrTemporalConstraintOrToBeDefined();
	}
	
	/* ******************************************************** */
	/* ***************** SIMPLE-CONDITION ******************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(SimpleCondition model) {
		return model.getComparisonOrHasOccurredOrIsAtEndOrIsAtStartOrIsKnownVariableOrIsMemberOfOrIsUnknownVariableOrIsWithinRangeOrSimpleConditionCombinationOrSimpleConditionNot();
	}
		
	/* ******************************************************** */
	/* ***************** CONSTRAINT-COMBINATION *************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(ConstraintCombination model) {
		return model.getConstraintCombinationOrConstraintNotOrCountConstraintOrIsAutomaticOrIsManualOrNoneOrParameterPropositionOrParameterRefOrPlanStateConstraintOrReferToOrSimpleConditionOrTemporalConstraintOrToBeDefined();
	}
	
	/* ******************************************************** */
	/* ***************** LEFT-HAND-SIDE *********************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(LeftHandSide model) {
		return model.getArgumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef();
	}
	
	/* ******************************************************** */
	/* ***************** RIGHT-HAND-SIDE ********************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(RightHandSide model) {
		return model.getArgumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef();
	}
	
	/* ******************************************************** */
	/* ***************** TIME-ANNOTATION ********************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(TimeAnnotation model) {
		return model.getAlwaysOrAnyOrNowOrTimeAnnotationRefOrTimeRangeOrParameterChangeOrPlanStateTransitionOrReferencePointOrReferencesOrSelf();
	}
	
	/* ******************************************************** */
	/* ***************** VALUE-DESCRIPTION ******************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(ValueDescription model) {
		return model.getArgumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef();
	}
	
	/* ******************************************************** */
	/* ***************** CONTEXT-DESCRIPTION ******************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(Context model) {
		return model.getAnyOrContextCombinationOrContextNotOrContextRefOrOneOf();
	}
	
	/* ******************************************************** */
	/* ***************** PLAN-STATE-CONSTRAINT ******************** */
	/* ******************************************************** */	
	public List<Object> getModelChildren(PlanStateConstraint model) {
		return model.getPlanPointerOrSelf();
	}
}
