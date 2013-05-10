package at.ac.tuwien.ieg.asbruedit.view.condition.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Color;

import at.ac.tuwien.ieg.asbruedit.Activator;
import at.ac.tuwien.ieg.asbruedit.model.ConditionsType;
import at.ac.tuwien.ieg.asbruedit.view.condition.ConditionView;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.SHOW_CONDITION, ConditionView.DEFAULT_CONDITIONS_TYPE.getId());
	}

}
