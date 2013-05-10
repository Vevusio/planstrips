package at.ac.tuwien.ieg.asbruedit.editor.planstrips.preferences;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import at.ac.tuwien.ieg.asbruedit.Activator;

/**
 * This class represents a preference page that
 * is contributed to the Preferences dialog. By 
 * subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows
 * us to create a page that is small and knows how to 
 * save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They
 * are stored in the preference store that belongs to
 * the main plug-in class. That way, preferences can
 * be accessed directly via the preference store.
 */

public class PlanStripsPreferences
	extends FieldEditorPreferencePage
	implements IWorkbenchPreferencePage {

	public PlanStripsPreferences() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Settings for the Plan Strips Editor");
	}
	
	/**
	 * Creates the field editors. Field editors are abstractions of
	 * the common GUI blocks needed to manipulate various types
	 * of preferences. Each field editor knows how to save and
	 * restore itself.
	 */
	public void createFieldEditors() {
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_SEQUENTIAL_ACTIVE, "Sequential active", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_SEQUENTIAL_PASSIVE, "Sequential passive", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_PARALLEL_ACTIVE, "Parallel active", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_PARALLEL_PASSIVE, "Parallel passive", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_ANYORDER_ACTIVE, "Any-order active", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_ANYORDER_PASSIVE, "Any-order passive", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_UNORDERED_ACTIVE, "Unordered active", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_UNORDERED_PASSIVE, "Unordered passive", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_CYCLICAL_ACTIVE, "Cyclical active", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_CYCLICAL_PASSIVE, "Cyclical passive", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_USER_ACTIVE, "User performed active", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_USER_PASSIVE, "User performed passive", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_BOX_BORDER_ACTIVE, "Box border active", getFieldEditorParent()));
		addField(new ColorFieldEditor(PreferenceConstants.COLOR_BOX_BORDER_PASSIVE, "Box border passive", getFieldEditorParent()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}
	
}