package at.ac.tuwien.ieg.asbruedit.model.condition;

import at.ac.tuwien.ieg.asbruedit.editor.ReflectivePartFactory;
import at.ac.tuwien.ieg.asbruedit.exception.MissingPartException;
import at.ac.tuwien.ieg.asbruedit.model.ModelTransformer;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Conditions;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ConstraintCombination;
import at.ac.tuwien.ieg.asbruedit.model.asbru.FilterPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.Plan;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SimpleCondition;

public class ConditionModelPartFactory extends ReflectivePartFactory {
	protected static final String PARTS_CLASS_SUFFIX = "Part";
	

	@Override
	public Object createPart(Object context, Object model) {
		try {
			AbstractConditionModelPart<?> part = (AbstractConditionModelPart<?>) super.createPart(context, model);
			part.refresh();
			return part;
		} catch (MissingPartException e) {
			Unknown unknown = new Unknown();
			unknown.setModel(model);
			return unknown;
		}
	}

	@Override
	protected String getPartSuffix() {
		return PARTS_CLASS_SUFFIX;
	}

	@Override
	protected String getPartPackage() {
		return this.getClass().getPackage().getName();
	}
}
