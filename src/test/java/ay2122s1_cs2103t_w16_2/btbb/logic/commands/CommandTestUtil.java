package ay2122s1_cs2103t_w16_2.btbb.logic.commands;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericStringPredicate;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    // Valid descriptions:
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_INGREDIENT_NAME_APPLE = "Apple";
    public static final String VALID_INGREDIENT_NAME_BEEF = "Beef";
    public static final String VALID_QUANTITY_APPLE = "10";
    public static final String VALID_QUANTITY_BEEF = "30";
    public static final String VALID_UNIT_APPLE = "whole";
    public static final String VALID_UNIT_BEEF = "cuts";

    // prefix + desciption (valid):
    public static final String NAME_DESC_AMY = " " + PREFIX_CLIENT_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_CLIENT_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_CLIENT_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_CLIENT_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_CLIENT_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_CLIENT_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_CLIENT_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_CLIENT_ADDRESS + VALID_ADDRESS_BOB;
    public static final String INDEX_DESC_AMY = " " + PREFIX_CLIENT_INDEX + INDEX_SECOND.getOneBased();
    public static final String INDEX_DESC_BOB = " " + PREFIX_CLIENT_INDEX + INDEX_FIRST.getOneBased();

    public static final String INGREDIENT_NAME_DESC_APPLE = " " + PREFIX_INGREDIENT_NAME + VALID_INGREDIENT_NAME_APPLE;
    public static final String INGREDIENT_NAME_DESC_BEEF = " " + PREFIX_INGREDIENT_NAME + VALID_INGREDIENT_NAME_BEEF;
    public static final String QUANTITY_DESC_APPLE = " " + PREFIX_INGREDIENT_QUANTITY + VALID_QUANTITY_APPLE;
    public static final String QUANTITY_DESC_BEEF = " " + PREFIX_INGREDIENT_QUANTITY + VALID_QUANTITY_BEEF;
    public static final String UNIT_DESC_APPLE = " " + PREFIX_INGREDIENT_UNIT + VALID_UNIT_APPLE;
    public static final String UNIT_DESC_BEEF = " " + PREFIX_INGREDIENT_UNIT + VALID_UNIT_BEEF;

    // prefix + desciption (invalid):
    public static final String INVALID_INDEX_DESC = " " + PREFIX_CLIENT_INDEX + "-1";
    public static final String INVALID_NAME_DESC = " " + PREFIX_CLIENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_CLIENT_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_CLIENT_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_CLIENT_ADDRESS; // no empty string for addresses

    public static final String INVALID_INGREDIENT_NAME_DESC = " " + PREFIX_INGREDIENT_NAME + "Rice&"; // '&' not allowed
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_INGREDIENT_QUANTITY + "-30"; // 'e' not allowed
    public static final String INVALID_UNIT_DESC = " " + PREFIX_INGREDIENT_UNIT; // no empty string for unit

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    // Descriptors:
    public static final ClientDescriptor DESC_AMY;
    public static final ClientDescriptor DESC_BOB;

    public static final OrderDescriptor DESC_ORDER_AMY;
    public static final OrderDescriptor DESC_ORDER_BOB;

    public static final IngredientDescriptor DESC_APPLE;
    public static final IngredientDescriptor DESC_BEEF;

    static {
        // Client
        DESC_AMY = new ClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        DESC_BOB = new ClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .build();

        // Order
        DESC_ORDER_AMY = new OrderDescriptorBuilder().withClientName(VALID_NAME_AMY).withClientPhone(VALID_PHONE_AMY)
                .withClientAddress(VALID_ADDRESS_AMY).build();
        DESC_ORDER_BOB = new OrderDescriptorBuilder().withClientName(VALID_NAME_BOB).withClientPhone(VALID_PHONE_BOB)
                .withClientAddress(VALID_ADDRESS_BOB).build();

        // Ingredient
        DESC_APPLE = new IngredientDescriptorBuilder().withIngredientName(VALID_INGREDIENT_NAME_APPLE)
                .withQuantity(VALID_QUANTITY_APPLE).withUnit(VALID_UNIT_APPLE).build();
        DESC_BEEF = new IngredientDescriptorBuilder().withIngredientName(VALID_INGREDIENT_NAME_BEEF)
                .withQuantity(VALID_QUANTITY_BEEF).withUnit(VALID_UNIT_BEEF).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}
     * and a selected tab to change to.
     *
     * @param command Command to execute.
     * @param actualModel Actual model after executing the command.
     * @param expectedMessage Expected message after executing the command.
     * @param expectedModel Expected model after executing the command.
     * @param selectedTab Selected tab to change to.
     */
    public static void assertCommandSuccessWithTabChange(Command command, Model actualModel, String expectedMessage,
            Model expectedModel, UiTab selectedTab) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        expectedCommandResult.setSelectedTab(selectedTab);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedClientFilteredList = new ArrayList<>(actualModel.getFilteredClientList());
        List<Ingredient> expectedIngredientFilteredList = new ArrayList<>(actualModel.getFilteredIngredientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedClientFilteredList, actualModel.getFilteredClientList());
        assertEquals(expectedIngredientFilteredList, actualModel.getFilteredIngredientList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().toString().split("\\s+");
        model.updateFilteredClientList(new GenericStringPredicate<>(Client::getName, Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }
}
