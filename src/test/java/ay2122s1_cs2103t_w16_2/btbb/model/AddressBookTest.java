package ay2122s1_cs2103t_w16_2.btbb.model;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_AMY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddressBookTest {
    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getClientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void hasClient_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasClient(null));
    }

    @Test
    public void hasClient_clientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        assertTrue(addressBook.hasClient(ALICE));
    }

    @Test
    public void hasClient_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(addressBook.hasClient(editedAlice));
    }

    @Test
    public void getCLientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getClientList().remove(0));
    }

    @Test
    public void hasIngredient_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasIngredient(null));
    }

    @Test
    public void hasIngredient_ingredientNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasIngredient(APPLE));
    }

    @Test
    public void hasIngredient_ingredientInAddressBook_returnsTrue() {
        addressBook.addIngredient(APPLE);
        assertTrue(addressBook.hasIngredient(APPLE));
    }

    @Test
    public void hasIngredient_ingredientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addIngredient(APPLE);
        Ingredient editedApple = new IngredientBuilder(APPLE).withQuantity(VALID_QUANTITY_BEEF).build();
        assertTrue(addressBook.hasIngredient(editedApple));
    }

    @Test
    public void addIngredientQuantity_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                addressBook.addIngredientQuantity(null, new Quantity("1")));
    }

    @Test
    public void addIngredientQuantity_nullMultiplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                addressBook.addIngredientQuantity(APPLE, null));
    }

    @Test
    public void addIngredientQuantity_validTargetAndMultiplier_throwsNullPointerException() {
        addressBook.addIngredient(new IngredientBuilder(BEEF).withQuantity(VALID_QUANTITY_BEEF).build());
        Ingredient target = new IngredientBuilder(BEEF).withQuantity("1").build();
        Ingredient expectedIngredient = new IngredientBuilder(BEEF).withQuantity("32").build();
        addressBook.addIngredientQuantity(target, new Quantity("2"));
        assertTrue(addressBook.hasIngredient(expectedIngredient));
    }

    @Test
    public void minusIngredientQuantity_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> addressBook.minusIngredientQuantity(null, new Quantity("1")));
    }

    @Test
    public void minusIngredientQuantity_nullMultiplier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> addressBook.minusIngredientQuantity(APPLE, null));
    }

    @Test
    public void minusIngredientQuantity_validTargetAndMultiplier_throwsNullPointerException() {
        addressBook.addIngredient(new IngredientBuilder(BEEF).withQuantity(VALID_QUANTITY_BEEF).build());
        Ingredient target = new IngredientBuilder(BEEF).withQuantity("1").build();
        Ingredient expectedIngredient = new IngredientBuilder(BEEF).withQuantity("28").build();
        addressBook.addIngredientQuantity(target, new Quantity("2"));
        assertTrue(addressBook.hasIngredient(expectedIngredient));
    }

    @Test
    public void setOrder_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setOrder(null, ORDER_FOR_ALICE));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.setOrder(ORDER_FOR_ALICE, null));
    }

    @Test
    public void setOrder_invalidTarget_throwsNullPointerException() throws NotFoundException {
        assertThrows(NotFoundException.class, () -> addressBook.setOrder(ORDER_FOR_ALICE, ORDER_FOR_AMY));
    }

    @Test
    public void setOrder_validTargetAndEditedOrder_throwsNullPointerException() throws NotFoundException {
        addressBook.addOrder(ORDER_FOR_ALICE);
        addressBook.setOrder(ORDER_FOR_ALICE, ORDER_FOR_AMY);
        assertFalse(addressBook.hasOrder(ORDER_FOR_ALICE));
        assertTrue(addressBook.hasOrder(ORDER_FOR_AMY));
    }

    /**
     * A stub ReadOnlyAddressBook whose clients list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Order> orders = FXCollections.observableArrayList();
        private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

        AddressBookStub(Collection<Client> clients) {
            this.clients.setAll(clients);
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Order> getOrderList() {
            return orders;
        }

        @Override
        public ObservableList<Ingredient> getIngredientList() {
            return ingredients;
        }
    }
}
