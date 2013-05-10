package at.ac.tuwien.ieg.asbruedit.editor;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.window.Window;

import at.ac.tuwien.ieg.asbruedit.exception.NoModelFactoryEntryException;
import at.ac.tuwien.ieg.asbruedit.model.PlanType;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CyclicalPlan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.PlanActivation;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Subplans;
import at.ac.tuwien.ieg.asbruedit.model.asbru.UserPerformed;

public class AsbruModelElementFactory implements CreationFactory {
	private PlanType type;
	private Class<?> creationClass;
	
	public AsbruModelElementFactory(Class<?> creationClass) {
		this.creationClass = creationClass;
	}
	
	public AsbruModelElementFactory setPlanType(PlanType type) {
		this.type = type;
		return this;
	}

	@Override
	public Object getNewObject() {
		if(Subplans.class.equals(creationClass)) {
			return AsbruModelHandler.instance().createDefaultSubplans(type);
		}
		else if(CyclicalPlan.class.equals(creationClass)) {
			return AsbruModelHandler.instance().createDefaultCyclicalPlan();
		}
		else if(UserPerformed.class.equals(creationClass)) {
			return AsbruModelHandler.instance().createDefaultUserPerformed();
		}
		else if(Plan.class.equals(creationClass)) {
			return AsbruModelHandler.instance().createDefaultPlanActivation();
		}
		throw new NoModelFactoryEntryException(creationClass);
	}

	@Override
	public Object getObjectType() {
		return creationClass;
	}

}
