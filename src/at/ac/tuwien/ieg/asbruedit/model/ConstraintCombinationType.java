package at.ac.tuwien.ieg.asbruedit.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.swt.graphics.Color;

import at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences.PreferenceConstants;
import at.ac.tuwien.ieg.asbruedit.helpers.PreferenceUtil;

public enum ConstraintCombinationType {
	and("and"), 
	or("or"), 
	xor("xor");

	private static HashMap<String, ConstraintCombinationType> typesMap;
	private String id;
	
	static {
		typesMap = new HashMap<String, ConstraintCombinationType>();
		typesMap.put(and.id, and);
		typesMap.put(or.id, or);
		typesMap.put(xor.id, xor);
	}

	private ConstraintCombinationType(String id) {
		this.id = id;
	}

	public static ConstraintCombinationType fromString(String name) {
		return typesMap.get(name.toLowerCase());
	}
	
	public String getId() {
		return id;
	}
}