package at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;

import at.ac.tuwien.ieg.asbruedit.Activator;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.COLOR_SEQUENTIAL_ACTIVE, "243,146,53");
		store.setDefault(PreferenceConstants.COLOR_SEQUENTIAL_PASSIVE, "246,191,121");
		store.setDefault(PreferenceConstants.COLOR_PARALLEL_ACTIVE, "72,173,58");
		store.setDefault(PreferenceConstants.COLOR_PARALLEL_PASSIVE, "185,217,142");
		store.setDefault(PreferenceConstants.COLOR_ANYORDER_ACTIVE, "53,140,193");
		store.setDefault(PreferenceConstants.COLOR_ANYORDER_PASSIVE, "172,206,225");
		store.setDefault(PreferenceConstants.COLOR_UNORDERED_ACTIVE, "235,61,47");
		store.setDefault(PreferenceConstants.COLOR_UNORDERED_PASSIVE, "240,160,157");
		store.setDefault(PreferenceConstants.COLOR_CYCLICAL_ACTIVE, "126,85,171");
		store.setDefault(PreferenceConstants.COLOR_CYCLICAL_PASSIVE, "212,193,222");
		store.setDefault(PreferenceConstants.COLOR_USER_ACTIVE, "99,99,99");
		store.setDefault(PreferenceConstants.COLOR_USER_PASSIVE, "203,203,203");
		store.setDefault(PreferenceConstants.COLOR_BOX_BORDER_ACTIVE, "50,50,50");
		store.setDefault(PreferenceConstants.COLOR_BOX_BORDER_PASSIVE, "243,243,243");
	}

}
