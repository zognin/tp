package ay2122s1_cs2103t_w16_2.btbb.model;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.model.Model.PREDICATE_SHOW_ALL_CLIENTS;
import static ay2122s1_cs2103t_w16_2.btbb.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;
import static ay2122s1_cs2103t_w16_2.btbb.model.Model.PREDICATE_SHOW_ALL_ORDERS;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_AMY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.GuiSettings;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.StringContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.AddressBookBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;

public class ModelManagerTest {
    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        modelManager.addClient(ALICE);
        assertTrue(modelManager.hasClient(ALICE));
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredClientList().remove(0));
    }

    @Test
    public void hasIngredient_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasIngredient(null));
    }

    @Test
    public void hasIngredient_ingredientNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasIngredient(APPLE));
    }

    @Test
    public void hasIngredient_ingredientInAddressBook_returnsTrue() {
        modelManager.addIngredient(APPLE);
        assertTrue(modelManager.hasIngredient(APPLE));
    }

    @Test
    public void addIngredientQuantity_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.addIngredientQuantity(null, new Quantity("1")));
    }

    @Test
    public void addIngredientQuantity_nullMultiplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addIngredientQuantity(APPLE, null));
    }

    @Test
    public void addIngredientQuantity_validTargetAndMultiplier_throwsNullPointerException() {
        modelManager.addIngredient(new IngredientBuilder(BEEF).withQuantity(VALID_QUANTITY_BEEF).build());
        Ingredient target = new IngredientBuilder(BEEF).withQuantity("1").build();
        Ingredient expectedIngredient = new IngredientBuilder(BEEF).withQuantity("32").build();
        modelManager.addIngredientQuantity(target, new Quantity("2"));
        assertTrue(modelManager.hasIngredient(expectedIngredient));
    }

    @Test
    public void minusIngredientQuantity_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                modelManager.minusIngredientQuantity(null, new Quantity("1")));
    }

    @Test
    public void minusIngredientQuantity_nullMultiplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.minusIngredientQuantity(APPLE, null));
    }

    @Test
    public void minusIngredientQuantity_validTargetAndMultiplier_throwsNullPointerException() {
        modelManager.addIngredient(new IngredientBuilder(BEEF).withQuantity(VALID_QUANTITY_BEEF).build());
        Ingredient target = new IngredientBuilder(BEEF).withQuantity("1").build();
        Ingredient expectedIngredient = new IngredientBuilder(BEEF).withQuantity("28").build();
        modelManager.addIngredientQuantity(target, new Quantity("2"));
        assertTrue(modelManager.hasIngredient(expectedIngredient));
    }

    @Test
    public void setOrder_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setOrder(null, ORDER_FOR_ALICE));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setOrder(ORDER_FOR_ALICE, null));
    }

    @Test
    public void setOrder_invalidTarget_throwsNullPointerException() throws NotFoundException {
        assertThrows(NotFoundException.class, () -> modelManager.setOrder(ORDER_FOR_ALICE, ORDER_FOR_AMY));
    }

    @Test
    public void setOrder_validTargetAndEditedOrder_throwsNullPointerException() throws NotFoundException {
        modelManager.addOrder(ORDER_FOR_ALICE);
        modelManager.setOrder(ORDER_FOR_ALICE, ORDER_FOR_AMY);
        assertFalse(modelManager.hasOrder(ORDER_FOR_ALICE));
        assertTrue(modelManager.hasOrder(ORDER_FOR_AMY));
    }

    @Test
    public void getFilteredIngredientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredIngredientList().remove(0));
    }

    @Test
    public void getFilteredOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredOrderList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withClient(ALICE).withClient(BENSON)
                .withIngredient(APPLE).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredClientList -> returns false
        String[] keywords = ALICE.getName().toString().split("\\s+");
        modelManager.updateFilteredClientList(
                new StringContainsKeywordsPredicate<>(Client::getName, Arrays.asList(keywords))
        );
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different filteredOrderList -> returns false
        keywords = ALICE.getName().toString().split("\\s+");
        modelManager.updateFilteredOrderList(
                new StringContainsKeywordsPredicate<>(Order::getClientName, Arrays.asList(keywords))
        );
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // different filteredIngredientList -> returns false
        modelManager.addIngredient(BEEF);
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        modelManager.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        modelManager.updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
