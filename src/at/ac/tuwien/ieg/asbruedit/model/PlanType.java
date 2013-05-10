package at.ac.tuwien.ieg.asbruedit.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.swt.graphics.Color;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences.PreferenceConstants;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;

public enum PlanType {
	sequential("sequentially", PreferenceConstants.COLOR_SEQUENTIAL_ACTIVE,
			PreferenceConstants.COLOR_SEQUENTIAL_PASSIVE), 
	parallel("parallel",
			PreferenceConstants.COLOR_PARALLEL_ACTIVE,
			PreferenceConstants.COLOR_PARALLEL_PASSIVE), 
	anyorder("any-order",
			PreferenceConstants.COLOR_ANYORDER_ACTIVE,
			PreferenceConstants.COLOR_ANYORDER_PASSIVE), 
	unordered("unordered",
			PreferenceConstants.COLOR_UNORDERED_ACTIVE,
			PreferenceConstants.COLOR_UNORDERED_PASSIVE), 
	cyclical("cyclical",
			PreferenceConstants.COLOR_CYCLICAL_ACTIVE,
			PreferenceConstants.COLOR_CYCLICAL_PASSIVE), 
	userperformed(
			"userperformed", PreferenceConstants.COLOR_USER_ACTIVE,
			PreferenceConstants.COLOR_USER_PASSIVE),			
	other("other", PreferenceConstants.COLOR_USER_ACTIVE,
			PreferenceConstants.COLOR_USER_PASSIVE);

	private String id;
	private String preferenceActive;
	private String preferencePassive;
	private static HashMap<String, PlanType> subplansTypesMap;
	static {
		subplansTypesMap = new HashMap<String, PlanType>();
		subplansTypesMap.put(sequential.id, sequential);
		subplansTypesMap.put(parallel.id, parallel);
		subplansTypesMap.put(anyorder.id, anyorder);
		subplansTypesMap.put(unordered.id, unordered);
	}

	private PlanType(String id, String preferenceActive,
			String preferencePassive) {
		this.id = id;
		this.preferenceActive = preferenceActive;
		this.preferencePassive = preferencePassive;
	}
	
	public static String[] getSubplansTypesArray() {
		return subplansTypesMap.keySet().toArray(new String[0]);
	}

	public static PlanType fromString(String name) {
		PlanType result = subplansTypesMap.get(name);
		if(result == null) {
			if(cyclical.equals(name)) {
				result = cyclical;
			}
			else if(userperformed.equals(name)) {
				result = userperformed;
			}
			else {
				result = other;
			}
		}
		return result;
	}
	
	public boolean makesHorizontalLayout() {
		return this == sequential;
	}
	
	public String getId() {
		return id;
	}

	public Color readActiveColor() {
		return PreferenceUtil.readColor(preferenceActive);
	}

	public Color readPassiveColor() {
		return PreferenceUtil.readColor(preferencePassive);
	}

	public boolean isSubplansType() {
		return subplansTypesMap.containsKey(this.id);
	}
}