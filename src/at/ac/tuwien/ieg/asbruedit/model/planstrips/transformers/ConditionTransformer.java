package at.ac.tuwien.ieg.asbruedit.model.planstrips.transformers;

import at.ac.tuwien.ieg.asbruedit.model.ModelTransformer;

public class ConditionTransformer<T> implements ModelTransformer<T, T> {

	public T transform(T model) {
		return model;
	}
}
