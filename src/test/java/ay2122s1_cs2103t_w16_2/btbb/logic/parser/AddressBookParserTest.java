package ay2122s1_cs2103t_w16_2.btbb.logic.parser;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.AddClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.DeleteClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.EditClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.ListClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.ExitCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.HelpCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.TabCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.AddIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.ListIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.ListOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.NameContainsKeywordsPredicate;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientUtil;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientUtil;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderUtil;

public class AddressBookParserTest {
    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addClient() throws Exception {
        Client client = new ClientBuilder().build();
        ClientDescriptor clientDescriptor = new ClientDescriptorBuilder(client).build();
        AddClientCommand command = (AddClientCommand) parser.parseCommand(ClientUtil.getAddCommand(client));
        assertEquals(new AddClientCommand(clientDescriptor), command);
    }

    @Test
    public void parseCommand_addOrder() throws Exception {
        Order order = new OrderBuilder().build();
        OrderDescriptor orderDescriptor = new OrderDescriptorBuilder(order).build();
        AddOrderCommand command = (AddOrderCommand) parser.parseCommand(OrderUtil.getAddCommand(order));
        assertEquals(new AddOrderCommand(orderDescriptor), command);
    }

    @Test
    public void parseCommand_addIngredient() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        IngredientDescriptor ingredientDescriptor = new IngredientDescriptorBuilder(ingredient).build();
        AddIngredientCommand command = (AddIngredientCommand) parser
                .parseCommand(IngredientUtil.getAddCommand(ingredient));
        assertEquals(new AddIngredientCommand(ingredientDescriptor), command);
    }

    @Test
    public void parseCommand_deleteClient() throws Exception {
        DeleteClientCommand command = (DeleteClientCommand) parser.parseCommand(
                DeleteClientCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteClientCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editClient() throws Exception {
        Client person = new ClientBuilder().build();
        ClientDescriptor descriptor = new ClientDescriptorBuilder(person).build();
        EditClientCommand command = (EditClientCommand) parser.parseCommand(EditClientCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor));
        assertEquals(new EditClientCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findClient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindClientCommand command = (FindClientCommand) parser.parseCommand(
                FindClientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindClientCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listClient() throws Exception {
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD) instanceof ListClientCommand);
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD + " 3") instanceof ListClientCommand);
    }

    @Test
    public void parseCommand_listIngredient() throws Exception {
        assertTrue(parser.parseCommand(ListIngredientCommand.COMMAND_WORD) instanceof ListIngredientCommand);
        assertTrue(parser.parseCommand(ListIngredientCommand.COMMAND_WORD + " 3") instanceof ListIngredientCommand);
    }

    @Test
    public void parseCommand_listOrder() throws Exception {
        assertTrue(parser.parseCommand(ListOrderCommand.COMMAND_WORD) instanceof ListOrderCommand);
        assertTrue(parser.parseCommand(ListOrderCommand.COMMAND_WORD + " 3") instanceof ListOrderCommand);
    }

    @Test
    public void parseCommand_tab() throws Exception {
        TabCommand command = (TabCommand) parser.parseCommand(
                TabCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new TabCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
