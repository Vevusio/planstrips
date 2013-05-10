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

public enum ComparisonType {
	less("less-than", "<"), 
	less_equal("less-or-equal", "<="), 
	greater("greater-than", ">"),
	greater_equal("greater-or-equal", ">="),
	equal("equal", "=="),
	not_equal("not-equal", "!=");

	private static HashMap<String, ComparisonType> typesMap;
	private String id;
	private String shorthand;
	
	static {
		typesMap = new HashMap<String, ComparisonType>();
		typesMap.put(less.id, less);
		typesMap.put(less_equal.id, less_equal);
		typesMap.put(greater.id, greater);
		typesMap.put(greater_equal.id, greater_equal);
		typesMap.put(equal.id, equal);
		typesMap.put(not_equal.id, not_equal);
	}

	private ComparisonType(String id, String shorthand) {
		this.id = id;
		this.shorthand = shorthand;
	}
	
	public String getShorthand() {
		return shorthand;
	}

	public static ComparisonType fromString(String name) {
		return typesMap.get(name.toLowerCase());
	}
	
	public String getId() {
		return id;
	}
}