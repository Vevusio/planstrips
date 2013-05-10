package at.ac.tuwien.ieg.asbruedit.model.condition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import at.ac.tuwien.ieg.asbruedit.editor.AsbruModelHandler;
import at.ac.tuwien.ieg.asbruedit.model.ModelTransformer;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.FilterPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.UserPerformed;
import at.ac.tuwien.ieg.asbruedit.model.planstrips.transformers.IdentityTransformer;

public abstract class AbstractConditionModelPart<T> {
	private static final String GET_MODEL_CHILDREN = "getModelChildren";
	
	private T model; 
	private LinkedList<AbstractConditionModelPart<?>> children;
	
	public LinkedList<AbstractConditionModelPart<?>> getChildren() {
		if(children == null) {
			children = buildChildren();
		}
		return children;
	}
	
	protected LinkedList<AbstractConditionModelPart<?>> buildChildren() {
		ConditionModelPartFactory factory = new ConditionModelPartFactory();
		
		LinkedList<AbstractConditionModelPart<?>> children = new LinkedList<AbstractConditionModelPart<?>>();
		
		for(Object cModelChild : getModelChildren()) {
			AbstractConditionModelPart<?> child = (AbstractConditionModelPart<?>) factory.createPart(this, cModelChild);
			if(child != null) {
				children.add(child);
			}
		}
		
		return children;
	}
	
	protected <CHILDTYPE> CHILDTYPE getChild(Class<CHILDTYPE> childClass) {
		for(AbstractConditionModelPart<?> cChild : getChildren()) {
			if(childClass.equals(cChild.getClass())) {
				return (CHILDTYPE) cChild;
			}
		}
		return null;
	}
	
	protected <CHILDMODEL> AbstractConditionModelPart<CHILDMODEL> getChildByModel(Class<CHILDMODEL> childModelClass) {
		for(AbstractConditionModelPart<?> cChild : getChildren()) {
			if(childModelClass.equals(cChild.getModel().getClass())) {
				return (AbstractConditionModelPart<CHILDMODEL>) cChild;
			}
		}
		return null;
	}

	protected void addChild(AbstractConditionModelPart<?> child) {
		getChildren().add(child);
	}
	
	protected List<Object> getModelChildren() {
		Method getModelChildren  = null;
		try {
			getModelChildren = AsbruModelHandler.class.getMethod(
					GET_MODEL_CHILDREN, getModel().getClass());
			return (List<Object>) getModelChildren.invoke(AsbruModelHandler.instance(), getModel());
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			return Collections.EMPTY_LIST;
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(getModelChildren.toString(), e);
		}
	}

	public T getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = (T) model;
	}
	
	public String makeConditionString() {
		return buildSeparatedConditionString(" ### ", getChildren());
	}
	
	protected String buildSeparatedConditionString(String delimeter, AbstractConditionModelPart<?>... parts) {
		if(parts == null || parts.length == 0) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		
		int i;
		for(i = 0; i < parts.length - 1; i++) {
			result.append(parts[i] != null ? parts[i].makeConditionString() : "null");
			result.append(delimeter);
		}
		result.append(parts[i].makeConditionString());
		
		return result.toString();
	}
	
	protected String buildSeparatedConditionString(String delimeter, List<AbstractConditionModelPart<?>> parts) {
		return buildSeparatedConditionString(delimeter, parts.toArray(new AbstractConditionModelPart<?>[parts.size()]));
	}

	public void refresh() {
		this.children = buildChildren();
	}
}
