package seedu.btbb.model;

import java.nio.file.Path;

import seedu.btbb.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {
    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();
}
