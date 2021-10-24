package ay2122s1_cs2103t_w16_2.btbb.logic.parser;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY_FROM;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_QUANTITY_TO;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_INGREDIENT_UNIT;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_NAME_ALICE_BOB_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_NAME_CAROL_DAVID_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_PHONE_9110_3216_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_PHONE_9427_3217_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.INGREDIENT_QUANTITY_5_550_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.INGREDIENT_QUANTITY_5_TO_600_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

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
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.DeleteIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.EditIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.FindIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.ListIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DeleteOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DeleteOrderIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DoneOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.EditOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.FindOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.ListOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.UndoneOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.DeleteRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.EditRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.ListRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.IngredientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.OrderDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.RecipeDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientUtil;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientUtil;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderUtil;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeUtil;

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
    public void parseCommand_addIngredient() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        IngredientDescriptor ingredientDescriptor = new IngredientDescriptorBuilder(ingredient).build();
        AddIngredientCommand command = (AddIngredientCommand) parser
                .parseCommand(IngredientUtil.getAddCommand(ingredient));
        assertEquals(new AddIngredientCommand(ingredientDescriptor), command);
    }

    @Test
    public void parseCommand_addOrder() throws Exception {
        Order order = new OrderBuilder().build();
        OrderDescriptor orderDescriptor = new OrderDescriptorBuilder(order).build();
        AddOrderCommand command = (AddOrderCommand) parser.parseCommand(OrderUtil.getAddCommand(order));
        assertEquals(new AddOrderCommand(orderDescriptor), command);
    }

    @Test
    public void parseCommand_addOrderIngredient() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        IngredientDescriptor ingredientDescriptor = new IngredientDescriptorBuilder(ingredient).build();

        AddOrderIngredientCommand command =
                (AddOrderIngredientCommand) parser.parseCommand(AddOrderIngredientCommand.COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased() + " "
                        + PREFIX_INGREDIENT_NAME + ingredient.getName().toString() + " "
                        + PREFIX_INGREDIENT_QUANTITY + ingredient.getQuantity().toString() + " "
                        + PREFIX_INGREDIENT_UNIT + ingredient.getUnit().toString()
                );
        assertEquals(new AddOrderIngredientCommand(INDEX_FIRST, ingredientDescriptor), command);
    }

    @Test
    public void parseCommand_addRecipe() throws Exception {
        Recipe recipe = new RecipeBuilder()
                .withRecipeIngredients(new RecipeIngredientList(new ArrayList<>())).build();
        RecipeDescriptor recipeDescriptor = new RecipeDescriptorBuilder(recipe)
                .withRecipeIngredients(new ArrayList<>()).build();
        AddRecipeCommand command = (AddRecipeCommand) parser.parseCommand(RecipeUtil.getAddCommand(recipe));
        assertEquals(new AddRecipeCommand(recipeDescriptor), command);
    }

    @Test
    public void parseCommand_addRecipeIngredient() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        IngredientDescriptor ingredientDescriptor = new IngredientDescriptorBuilder(ingredient).build();

        AddRecipeIngredientCommand command =
                (AddRecipeIngredientCommand) parser.parseCommand(AddRecipeIngredientCommand.COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased() + " "
                        + PREFIX_INGREDIENT_NAME + ingredient.getName().toString() + " "
                        + PREFIX_INGREDIENT_QUANTITY + ingredient.getQuantity().toString() + " "
                        + PREFIX_INGREDIENT_UNIT + ingredient.getUnit().toString()
                );
        assertEquals(new AddRecipeIngredientCommand(INDEX_FIRST, ingredientDescriptor), command);
    }

    @Test
    public void parseCommand_deleteClient() throws Exception {
        DeleteClientCommand command = (DeleteClientCommand) parser.parseCommand(
                DeleteClientCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteClientCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteIngredient() throws Exception {
        DeleteIngredientCommand command = (DeleteIngredientCommand) parser.parseCommand(
                DeleteIngredientCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteIngredientCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteOrder() throws Exception {
        DeleteOrderCommand command = (DeleteOrderCommand) parser.parseCommand(
                DeleteOrderCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteOrderCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteOrderIngredient() throws Exception {
        DeleteOrderIngredientCommand command =
                (DeleteOrderIngredientCommand) parser.parseCommand(DeleteOrderIngredientCommand.COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased() + " "
                        + PREFIX_INGREDIENT_INDEX + INDEX_FIRST.getOneBased()
                );
        assertEquals(new DeleteOrderIngredientCommand(INDEX_FIRST, INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteRecipe() throws Exception {
        DeleteRecipeCommand command = (DeleteRecipeCommand) parser.parseCommand(
                DeleteRecipeCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteRecipeCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_doneOrder() throws Exception {
        DoneOrderCommand command = (DoneOrderCommand) parser.parseCommand(
                DoneOrderCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DoneOrderCommand(INDEX_FIRST), command);
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
    public void parseCommand_editIngredient() throws Exception {
        Ingredient ingredient = new IngredientBuilder().build();
        IngredientDescriptor descriptor = new IngredientDescriptorBuilder(ingredient).build();
        EditIngredientCommand command = (EditIngredientCommand) parser.parseCommand(EditIngredientCommand.COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " "
                + IngredientUtil.getEditIngredientDescriptorDetails(descriptor));
        assertEquals(new EditIngredientCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_editOrder() throws Exception {
        Order order = new OrderBuilder().build();

        // It is not possible to edit the ingredient list using the EditOrderCommand
        OrderDescriptor descriptor = new OrderDescriptorBuilder(order).withRecipeIngredients(null)
                .withCompletionStatus(null).build();
        EditOrderCommand command = (EditOrderCommand) parser.parseCommand(EditOrderCommand.COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " "
                + OrderUtil.getEditOrderDescriptorDetails(descriptor));

        assertEquals(new EditOrderCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_editRecipe() throws Exception {
        Recipe recipe = new RecipeBuilder().build();

        // It is not possible to edit the ingredient list using the EditRecipeCommand
        RecipeDescriptor descriptor = new RecipeDescriptorBuilder(recipe).withRecipeIngredients(null).build();
        EditRecipeCommand command = (EditRecipeCommand) parser.parseCommand(EditRecipeCommand.COMMAND_WORD
                + " " + INDEX_FIRST.getOneBased() + " "
                + RecipeUtil.getEditRecipeDescriptorDetails(descriptor));

        assertEquals(new EditRecipeCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findClient() throws Exception {
        PredicateCollection<Client> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(CLIENT_NAME_ALICE_BOB_PREDICATE);
        predicateCollection.addPredicate(CLIENT_ADDRESS_YISHUN_GEYLANG_PREDICATE);
        predicateCollection.addPredicate(CLIENT_EMAIL_ALICE_BOB_GMAIL_PREDICATE);
        predicateCollection.addPredicate(CLIENT_PHONE_9427_3217_PREDICATE);
        FindClientCommand command = (FindClientCommand) parser.parseCommand(FindClientCommand.COMMAND_WORD
                + " " + PREFIX_CLIENT_NAME + "Alice Bob " + PREFIX_CLIENT_ADDRESS + "Yishun Geylang "
                + PREFIX_CLIENT_PHONE + "9427 3217 " + PREFIX_CLIENT_EMAIL + "alice@gmail.com bob@gmail.com");
        assertEquals(new FindClientCommand(predicateCollection), command);
    }

    @Test
    public void parseCommand_findIngredient() throws Exception {
        PredicateCollection<Ingredient> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(INGREDIENT_NAME_AVOCADO_BUTTER_PREDICATE);
        predicateCollection.addPredicate(INGREDIENT_QUANTITY_5_550_PREDICATE);
        predicateCollection.addPredicate(INGREDIENT_QUANTITY_5_TO_600_PREDICATE);
        predicateCollection.addPredicate(INGREDIENT_UNIT_WHOLE_GRAMS_PREDICATE);
        FindIngredientCommand command = (FindIngredientCommand) parser.parseCommand(FindIngredientCommand.COMMAND_WORD
                + " "
                + PREFIX_INGREDIENT_NAME + "Avocado Butter "
                + PREFIX_INGREDIENT_QUANTITY + "5 550 "
                + PREFIX_INGREDIENT_QUANTITY_FROM + "5 " + PREFIX_INGREDIENT_QUANTITY_TO + "600 "
                + PREFIX_INGREDIENT_UNIT + "whole grams");
        assertEquals(new FindIngredientCommand(predicateCollection), command);
    }

    @Test
    public void parseCommand_findOrder() throws Exception {
        PredicateCollection<Order> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(CLIENT_NAME_CAROL_DAVID_PREDICATE);
        predicateCollection.addPredicate(CLIENT_PHONE_9110_3216_PREDICATE);
        predicateCollection.addPredicate(CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE);
        FindOrderCommand command = (FindOrderCommand) parser.parseCommand(FindOrderCommand.COMMAND_WORD
                + " " + PREFIX_CLIENT_NAME + "Carol David " + PREFIX_CLIENT_PHONE + "9110 3216 "
                + PREFIX_CLIENT_ADDRESS + "Eunos Bishan");
        assertEquals(new FindOrderCommand(predicateCollection), command);
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
    public void parseCommand_listRecipe() throws Exception {
        assertTrue(parser.parseCommand(ListRecipeCommand.COMMAND_WORD) instanceof ListRecipeCommand);
        assertTrue(parser.parseCommand(ListRecipeCommand.COMMAND_WORD + " 3") instanceof ListRecipeCommand);
    }

    @Test
    public void parseCommand_tab() throws Exception {
        TabCommand command = (TabCommand) parser.parseCommand(
                TabCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new TabCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_undoneOrder() throws Exception {
        UndoneOrderCommand command = (UndoneOrderCommand) parser.parseCommand(
                UndoneOrderCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new UndoneOrderCommand(INDEX_FIRST), command);
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
