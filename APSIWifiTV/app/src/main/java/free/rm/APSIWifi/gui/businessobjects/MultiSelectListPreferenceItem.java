package free.rm.APSIWifi.gui.businessobjects;

import free.rm.APSIWifi.gui.businessobjects.adapters.MultiSelectListPreferenceAdapter;
import free.rm.APSIWifi.gui.businessobjects.adapters.MultiSelectListPreferenceAdapter;
import free.rm.APSIWifi.gui.businessobjects.adapters.MultiSelectListPreferenceAdapter;

/**
 * Represents an item to be used by
 * {@link MultiSelectListPreferenceAdapter}.
 */
public class MultiSelectListPreferenceItem {

	/** Item's ID */
	public String id;
	/** Item's publicly visible text. */
	public String text;
	/** Is the item checked/selected by the user? */
	public boolean isChecked;

	public MultiSelectListPreferenceItem(String id, String text) {
		this(id, text, true);
	}

	public MultiSelectListPreferenceItem(String id, String text, boolean isChecked) {
		this.id = id;
		this.text = text;
		this.isChecked = isChecked;
	}

}
