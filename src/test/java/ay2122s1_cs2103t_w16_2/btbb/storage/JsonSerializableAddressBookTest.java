package ay2122s1_cs2103t_w16_2.btbb.storage;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.util.JsonUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients;

public class JsonSerializableAddressBookTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsAddressBook.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientAddressBook.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientAddressBook.json");
    private static final Path INVALID_INGREDIENT_FILE = TEST_DATA_FOLDER.resolve("invalidIngredientAddressBook.json");
    private static final Path DUPLICATE_INGREDIENT_FILE = TEST_DATA_FOLDER
            .resolve("duplicateIngredientAddressBook.json");
    private static final Path INVALID_RECIPE_FILE_1 = TEST_DATA_FOLDER.resolve("invalidRecipeAddressBook1.json");
    private static final Path INVALID_RECIPE_FILE_2 = TEST_DATA_FOLDER.resolve("invalidRecipeAddressBook2.json");
    private static final Path INVALID_RECIPE_FILE_3 = TEST_DATA_FOLDER.resolve("invalidRecipeAddressBook3.json");
    private static final Path DUPLICATE_RECIPE_FILE = TEST_DATA_FOLDER.resolve("duplicateRecipeAddressBook.json");
    private static final Path INVALID_ORDER_FILE_1 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook1.json");
    private static final Path INVALID_ORDER_FILE_2 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook2.json");
    private static final Path INVALID_ORDER_FILE_3 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook3.json");
    private static final Path INVALID_ORDER_FILE_4 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook4.json");
    private static final Path INVALID_ORDER_FILE_5 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook5.json");
    private static final Path INVALID_ORDER_FILE_6 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook6.json");
    private static final Path INVALID_ORDER_FILE_7 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook7.json");
    private static final Path INVALID_ORDER_FILE_8 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook8.json");
    private static final Path INVALID_ORDER_FILE_9 = TEST_DATA_FOLDER.resolve("invalidOrderAddressBook9.json");
    private static final Path DUPLICATE_ORDER_FILE = TEST_DATA_FOLDER.resolve("duplicateOrderAddressBook.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalClientsAddressBook = TypicalClients.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalClientsAddressBook);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidOrderFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_1,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_2,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_3,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_4,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_5,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_6,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_7,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_8,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_ORDER_FILE_9,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateOrders_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ORDER_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_ORDER,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidIngredientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_INGREDIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateIngredients_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_INGREDIENT_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_INGREDIENT,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidRecipeFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_RECIPE_FILE_1,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_RECIPE_FILE_2, JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);

        dataFromFile = JsonUtil.readJsonFile(INVALID_RECIPE_FILE_3, JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateRecipes_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_RECIPE_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_RECIPE,
                dataFromFile::toModelType);
    }
}
