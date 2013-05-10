package at.ac.tuwien.ieg.asbruedit.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.swt.graphics.Color;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences.PreferenceConstants;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;
import at.ac.tuwien.ieg.asbruedit.model.asbru.AbortCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.CompleteCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.FilterPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.ReactivateCondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SetupPrecondition;
import at.ac.tuwien.ieg.asbruedit.model.asbru.SuspendCondition;

public enum ConditionsType {
	filter_precondition("filter-precondition", FilterPrecondition.class),
	setup_precondition("setup-precondition", SetupPrecondition.class),
	suspend_condition("suspend-condition", SuspendCondition.class),
	reactivate_condition("reactivate-condition", ReactivateCondition.class),
	complete_condition("complete-condition", CompleteCondition.class),
	abort_condition("abort-condition", AbortCondition.class);

	private static HashMap<String, ConditionsType> typesMap;
	private String id;
	private Class<?> relatedClass;
	
	static {
		typesMap = new HashMap<String, ConditionsType>();
		typesMap.put(filter_precondition.id, filter_precondition);
		typesMap.put(setup_precondition.id, setup_precondition);
		typesMap.put(suspend_condition.id, suspend_condition);
		typesMap.put(reactivate_condition.id, reactivate_condition);
		typesMap.put(complete_condition.id, complete_condition);
		typesMap.put(abort_condition.id, abort_condition);
	}

	private ConditionsType(String id, Class<?> relatedClass) {
		this.id = id;
		this.relatedClass = relatedClass;
	}
	
	public String getDisplayName() {
		return id;
	}
	
	public static ConditionsType fromString(String name) {
		return typesMap.get(name.toLowerCase());
	}	
	
	public Class<?> getRelatedModelClass() {
		return relatedClass;
	}

	public String getId() {
		return id;
	}
}