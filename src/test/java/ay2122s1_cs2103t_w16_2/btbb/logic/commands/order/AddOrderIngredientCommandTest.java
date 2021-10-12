package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showOrderAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.ListClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderUtil;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddOrderIngredientCommand.
 */
public class AddOrderIngredientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddOrderIngredientCommand(null, null));
        assertThrows(NullPointerException.class, () ->
                new AddOrderIngredientCommand(INDEX_FIRST, null));
        assertThrows(NullPointerException.class, () ->
                new AddOrderIngredientCommand(null, new IngredientDescriptor()));
    }

    @Test
    public void execute_validIngredientAndOrderIndexUnfilteredList_addSuccessful() throws Exception {
        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();
        AddOrderIngredientCommand command = new AddOrderIngredientCommand(INDEX_FIRST, validIngredientDescriptor);
        Order orderToEdit = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        RecipeIngredientList newRecipeIngredientList =
                OrderUtil.addIngredientToIngredientList(orderToEdit, validIngredient);
        Order editedOrder = new OrderBuilder(orderToEdit).withRecipeIngredients(newRecipeIngredientList).build();

        String expectedMessage = String.format(
                AddOrderIngredientCommand.MESSAGE_ADD_ORDER_INGREDIENT_SUCCESS, validIngredient, editedOrder);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setOrder(orderToEdit, editedOrder);

        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_validIngredientAndOrderIndexFilteredList_addSuccessful() throws Exception {
        showOrderAtIndex(model, INDEX_FIRST);

        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();
        AddOrderIngredientCommand command = new AddOrderIngredientCommand(INDEX_FIRST, validIngredientDescriptor);
        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        RecipeIngredientList newRecipeIngredientList =
                OrderUtil.addIngredientToIngredientList(orderInFilteredList, validIngredient);
        Order editedOrder =
                new OrderBuilder(orderInFilteredList).withRecipeIngredients(newRecipeIngredientList).build();

        String expectedMessage = String.format(
                AddOrderIngredientCommand.MESSAGE_ADD_ORDER_INGREDIENT_SUCCESS, validIngredient, editedOrder);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showOrderAtIndex(expectedModel, INDEX_FIRST);
        expectedModel.setOrder(orderInFilteredList, editedOrder);

        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.HOME);
    }

    @Test
    public void execute_duplicateIngredient_failure() {
        Order order = model.getFilteredOrderList().get(INDEX_FIRST.getZeroBased());
        Ingredient duplicateIngredient = order.getRecipeIngredients().getIngredients().get(INDEX_FIRST.getZeroBased());
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(duplicateIngredient).build();
        AddOrderIngredientCommand command = new AddOrderIngredientCommand(INDEX_FIRST, validIngredientDescriptor);

        assertCommandFailure(command, model, AddOrderIngredientCommand.MESSAGE_DUPLICATE_ORDER_INGREDIENT);
    }

    @Test
    public void execute_duplicateOrder_failure() {
        Ingredient firstIngredient = new IngredientBuilder().withIngredientName("Random name 1").build();
        Ingredient secondIngredient = new IngredientBuilder().withIngredientName("Random name 2").build();
        IngredientDescriptor secondIngredientDescriptor = new IngredientDescriptorBuilder(secondIngredient).build();
        RecipeIngredientList firstIngredientList = new RecipeIngredientList(List.of(firstIngredient));
        RecipeIngredientList secondIngredientList =
                new RecipeIngredientList(List.of(firstIngredient, secondIngredient));
        Order firstOrder = new OrderBuilder().withRecipeIngredients(firstIngredientList).build();
        Order secondOrder = new OrderBuilder().withRecipeIngredients(secondIngredientList).build();
        model.addOrder(firstOrder);
        model.addOrder(secondOrder);

        Index indexOfOrderToEdit = Index.fromZeroBased(model.getFilteredOrderList().indexOf(firstOrder));

        AddOrderIngredientCommand command =
                new AddOrderIngredientCommand(indexOfOrderToEdit, secondIngredientDescriptor);
        assertCommandFailure(command, model, AddOrderCommand.MESSAGE_DUPLICATE_ORDER);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();
        AddOrderIngredientCommand command = new AddOrderIngredientCommand(outOfBoundIndex, validIngredientDescriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidOrderIndexFilteredList_failure() {
        showOrderAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getOrderList().size());

        Ingredient validIngredient = new IngredientBuilder().build();
        IngredientDescriptor validIngredientDescriptor = new IngredientDescriptorBuilder(validIngredient).build();
        AddOrderIngredientCommand command = new AddOrderIngredientCommand(outOfBoundIndex, validIngredientDescriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddOrderIngredientCommand standardCommand = new AddOrderIngredientCommand(INDEX_FIRST, DESC_APPLE);

        // same values -> returns true
        IngredientDescriptor copyDescriptor = new IngredientDescriptor(DESC_APPLE);
        AddOrderIngredientCommand commandWithSameValues = new AddOrderIngredientCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListClientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddOrderIngredientCommand(INDEX_SECOND, DESC_APPLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddOrderIngredientCommand(INDEX_FIRST, DESC_BEEF)));
    }
}
