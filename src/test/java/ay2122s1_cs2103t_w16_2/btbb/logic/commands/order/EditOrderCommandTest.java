package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_DUPLICATE_ORDER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showOrderAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingOrderAdded;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

class EditOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Order editedOrder = new OrderBuilder().build();
        OrderDescriptor descriptor = new OrderDescriptorBuilder(editedOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccessWithTabChange(editOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_editedOrderQuantityDecreased_addsIngredientQuantity() throws CommandException {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        modelStub.addOrder(ORDER_FOR_ALICE);
        Ingredient chicken = new Ingredient(new GenericString("Chicken"), new Quantity("100"),
                new GenericString("whole"));
        modelStub.addIngredient(chicken);

        Order editedOrder = new OrderBuilder(ORDER_FOR_ALICE).withQuantity("2").build();
        OrderDescriptor validOrderDescriptor = new OrderDescriptorBuilder(editedOrder).build();

        CommandResult commandResult = new EditOrderCommand(INDEX_FIRST, validOrderDescriptor).execute(modelStub);

        // Order for Alice uses 1 chicken for each order. Alice's order has decreased by 2.
        Ingredient expectedChicken = new Ingredient(
                chicken.getName(),
                chicken.getQuantity().addQuantityBy(new Quantity("1"), new Quantity("2")),
                chicken.getUnit()
        );

        assertEquals(String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(expectedChicken), modelStub.getIngredientsAdded());
    }

    @Test
    public void execute_editedOrderQuantityIncreased_reducesIngredientQuantity() throws CommandException {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        modelStub.addOrder(ORDER_FOR_ALICE);
        Ingredient chicken = new Ingredient(new GenericString("Chicken"), new Quantity("100"),
                new GenericString("whole"));
        modelStub.addIngredient(chicken);

        Order editedOrder = new OrderBuilder(ORDER_FOR_ALICE).withQuantity("6").build();
        OrderDescriptor validOrderDescriptor = new OrderDescriptorBuilder(editedOrder).build();

        CommandResult commandResult = new EditOrderCommand(INDEX_FIRST, validOrderDescriptor).execute(modelStub);

        // Order for Alice uses 1 chicken for each order. Alice's order has decreased by 2.
        Ingredient expectedChicken = new Ingredient(
                chicken.getName(),
                chicken.getQuantity().minusQuantityBy(new Quantity("1"), new Quantity("2")),
                chicken.getUnit()
        );

        assertEquals(String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(expectedChicken), modelStub.getIngredientsAdded());
    }

    @Test
    public void execute_editedOrderQuantitySame_sameIngredientQuantity() throws CommandException {
        ModelStubAcceptingOrderAdded modelStub = new ModelStubAcceptingOrderAdded();
        modelStub.addOrder(ORDER_FOR_ALICE);
        Ingredient chicken = new Ingredient(new GenericString("Chicken"), new Quantity("100"),
                new GenericString("whole"));
        modelStub.addIngredient(chicken);

        Order editedOrder = new OrderBuilder(ORDER_FOR_ALICE).build();
        OrderDescriptor validOrderDescriptor = new OrderDescriptorBuilder(editedOrder).build();

        CommandResult commandResult = new EditOrderCommand(INDEX_FIRST, validOrderDescriptor).execute(modelStub);

        assertEquals(String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(chicken), modelStub.getIngredientsAdded());
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        OrderBuilder orderInList = new OrderBuilder(lastOrder);
        Order editedOrder = orderInList.withClientName(VALID_NAME_BOB)
                .withClientPhone(VALID_PHONE_BOB).build();

        OrderDescriptor descriptor = new OrderDescriptorBuilder().withClientName(VALID_NAME_BOB)
                .withClientPhone(VALID_PHONE_BOB).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(indexLastOrder, descriptor);

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(lastOrder, editedOrder);

        assertCommandSuccessWithTabChange(editOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST, new OrderDescriptor());
        Order editedOrder = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccessWithTabChange(editOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_filteredList_success() throws NotFoundException {
        showOrderAtIndex(model, INDEX_FIRST);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Order editedOrder = new OrderBuilder(orderInFilteredList)
                .withClientName(VALID_NAME_BOB).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST,
                new OrderDescriptorBuilder().withClientName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccessWithTabChange(editOrderCommand, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_duplicateOrderUnfilteredList_failure() {
        Order firstOrder = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        OrderDescriptor descriptor = new OrderDescriptorBuilder(firstOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editOrderCommand, model, MESSAGE_DUPLICATE_ORDER);
    }

    @Test
    public void execute_duplicateOrderFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST);

        // edit order in filtered list into a duplicate in address book
        Order orderInList = model.getAddressBook().getOrderList().get(INDEX_SECOND.getZeroBased());
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST,
                new OrderDescriptorBuilder(orderInList).build());

        assertCommandFailure(editOrderCommand, model, MESSAGE_DUPLICATE_ORDER);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        OrderDescriptor descriptor = new OrderDescriptorBuilder().withClientName(VALID_NAME_BOB).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        OrderDescriptor descriptor = new OrderDescriptorBuilder().withClientName(VALID_NAME_BOB).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditOrderCommand standardCommand = new EditOrderCommand(INDEX_FIRST, DESC_ORDER_AMY);

        // same values -> returns true
        OrderDescriptor copyDescriptor = new OrderDescriptor(DESC_ORDER_AMY);
        EditOrderCommand commandWithSameValues = new EditOrderCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListOrderCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_SECOND, DESC_ORDER_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOrderCommand(INDEX_FIRST, DESC_ORDER_BOB)));
    }
}
