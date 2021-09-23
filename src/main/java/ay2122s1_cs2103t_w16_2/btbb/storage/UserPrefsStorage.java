package ay2122s1_cs2103t_w16_2.btbb.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.exception.DataConversionException;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyUserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;

/**
 * Represents a storage for {@link UserPrefs}.
 */
public interface UserPrefsStorage {
    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getUserPrefsFilePath();

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    /**
     * Saves the given {@link ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyUserPrefs} to the storage.
     *
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;
}
