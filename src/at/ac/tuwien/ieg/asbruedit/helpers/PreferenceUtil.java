package at.ac.tuwien.ieg.asbruedit.helpers;

import org.eclipse.swt.graphics.Color;

import at.ac.tuwien.ieg.asbruedit.Activator;

public class PreferenceUtil {
	public static String readString(String preference) {
		return Activator.getDefault().getPreferenceStore().getString(preference);
	}
	
	public static void savePreference(String preference, String value) {
		Activator.getDefault().getPreferenceStore().putValue(preference, value);
	}
	
	public static Color readColor(String preference) {
		return ColorTool.fromRGBString(readString(preference));
	}
}
