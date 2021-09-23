package ay2122s1_cs2103t_w16_2.btbb.model;

import java.nio.file.Path;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {
    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();
}
