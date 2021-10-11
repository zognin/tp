package ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.showIngredientAtIndex;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

public class EditIngredientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Ingredient editedIngredient = new IngredientBuilder().build();
        IngredientDescriptor descriptor = new IngredientDescriptorBuilder(editedIngredient).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_EDIT_INGREDIENT_SUCCESS, editedIngredient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setIngredient(model.getFilteredIngredientList().get(0), editedIngredient);

        assertCommandSuccessWithTabChange(editIngredientCommand, model, expectedMessage, expectedModel,
                UiTab.INVENTORY);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws NotFoundException {
        Index indexLastIngredient = Index.fromOneBased(model.getFilteredIngredientList().size());
        Ingredient lastIngredient = model.getFilteredIngredientList().get(indexLastIngredient.getZeroBased());

        IngredientBuilder ingredientInList = new IngredientBuilder(lastIngredient);
        Ingredient editedIngredient = ingredientInList.withIngredientName(VALID_INGREDIENT_NAME_BEEF)
                .withQuantity(VALID_QUANTITY_BEEF).build();

        IngredientDescriptor descriptor = new IngredientDescriptorBuilder()
                .withIngredientName(VALID_INGREDIENT_NAME_BEEF).withQuantity(VALID_QUANTITY_BEEF).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(indexLastIngredient, descriptor);

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_EDIT_INGREDIENT_SUCCESS, editedIngredient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setIngredient(lastIngredient, editedIngredient);

        assertCommandSuccessWithTabChange(editIngredientCommand, model, expectedMessage, expectedModel,
                UiTab.INVENTORY);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditIngredientCommand editIngredientCommand =
                new EditIngredientCommand(INDEX_FIRST, new IngredientDescriptor());
        Ingredient editedIngredient = model.getFilteredIngredientList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_EDIT_INGREDIENT_SUCCESS, editedIngredient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccessWithTabChange(editIngredientCommand, model, expectedMessage, expectedModel,
                UiTab.INVENTORY);
    }

    @Test
    public void execute_filteredList_success() throws NotFoundException {
        showIngredientAtIndex(model, INDEX_FIRST);

        Ingredient ingredientInFilteredList = model.getFilteredIngredientList().get(INDEX_FIRST.getZeroBased());
        Ingredient editedIngredient = new IngredientBuilder(ingredientInFilteredList)
                .withIngredientName(VALID_INGREDIENT_NAME_BEEF).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_FIRST,
                new IngredientDescriptorBuilder().withIngredientName(VALID_INGREDIENT_NAME_BEEF).build());

        String expectedMessage = String.format(EditIngredientCommand.MESSAGE_EDIT_INGREDIENT_SUCCESS, editedIngredient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setIngredient(model.getFilteredIngredientList().get(0), editedIngredient);

        assertCommandSuccessWithTabChange(editIngredientCommand, model, expectedMessage, expectedModel,
                UiTab.INVENTORY);
    }

    @Test
    public void execute_duplicateIngredientUnfilteredList_failure() {
        Ingredient firstIngredient = model.getFilteredIngredientList().get(INDEX_FIRST.getZeroBased());
        IngredientDescriptor descriptor = new IngredientDescriptorBuilder(firstIngredient).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editIngredientCommand, model, EditIngredientCommand.MESSAGE_DUPLICATE_INGREDIENT);
    }

    @Test
    public void execute_duplicateIngredientFilteredList_failure() {
        showIngredientAtIndex(model, INDEX_FIRST);

        // edit ingredient in filtered list into a duplicate in address book
        Ingredient ingredientInList = model.getAddressBook().getIngredientList().get(INDEX_SECOND.getZeroBased());
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(INDEX_FIRST,
                new IngredientDescriptorBuilder(ingredientInList).build());

        assertCommandFailure(editIngredientCommand, model, EditIngredientCommand.MESSAGE_DUPLICATE_INGREDIENT);
    }

    @Test
    public void execute_invalidIngredientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIngredientList().size() + 1);
        IngredientDescriptor descriptor = new IngredientDescriptorBuilder()
                .withIngredientName(VALID_INGREDIENT_NAME_BEEF).build();
        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidIngredientIndexFilteredList_failure() {
        showIngredientAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getIngredientList().size());

        EditIngredientCommand editIngredientCommand = new EditIngredientCommand(outOfBoundIndex,
                new IngredientDescriptorBuilder().withIngredientName(VALID_INGREDIENT_NAME_BEEF).build());

        assertCommandFailure(editIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditIngredientCommand standardCommand = new EditIngredientCommand(INDEX_FIRST, DESC_BEEF);

        // same values -> returns true
        IngredientDescriptor copyDescriptor = new IngredientDescriptor(DESC_BEEF);
        EditIngredientCommand commandWithSameValues = new EditIngredientCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListIngredientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditIngredientCommand(INDEX_SECOND, DESC_BEEF)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditIngredientCommand(INDEX_FIRST, DESC_APPLE)));
    }
}
