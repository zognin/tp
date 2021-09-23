package ay2122s1_cs2103t_w16_2.btbb.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.exception.DataConversionException;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyUserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;
}
