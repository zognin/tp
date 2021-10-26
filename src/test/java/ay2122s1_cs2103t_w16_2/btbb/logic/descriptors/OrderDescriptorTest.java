package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_ORDER_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_YES;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.AVOCADO;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BREAD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.RecipeBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingClientAndRecipeAdded;

class OrderDescriptorTest {
    @Test
    public void toModelType_nullFields_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        // All fields are null
        assertThrows(CommandException.class, () -> new OrderDescriptor().toModelType(model));

        // All client fields are null
        OrderDescriptor nullClientFieldsDescriptor = new OrderDescriptorBuilder().build();
        nullClientFieldsDescriptor.setClientIndex(null);
        nullClientFieldsDescriptor.setClientName(null);
        nullClientFieldsDescriptor.setClientPhone(null);
        nullClientFieldsDescriptor.setClientAddress(null);
        assertThrows(CommandException.class, () -> nullClientFieldsDescriptor.toModelType(model));

        // All compulsory recipe fields are null
        OrderDescriptor nullRecipeFieldsDescriptor = new OrderDescriptorBuilder().build();
        nullRecipeFieldsDescriptor.setRecipeIndex(null);
        nullRecipeFieldsDescriptor.setRecipeName(null);
        assertThrows(CommandException.class, () -> nullRecipeFieldsDescriptor.toModelType(model));

        // All compulsory order fields are null
        OrderDescriptor nullOrderFieldsDescriptor = new OrderDescriptorBuilder().build();
        nullOrderFieldsDescriptor.setRecipeIndex(null); // To ensure order price is null
        nullOrderFieldsDescriptor.setOrderPrice(null);
        nullOrderFieldsDescriptor.setDeadline(null);
        nullOrderFieldsDescriptor.setQuantity(null);
        assertThrows(CommandException.class, () -> nullOrderFieldsDescriptor.toModelType(model));
    }

    @Test
    public void toModelType_validDescriptor_success() throws CommandException {
        Model modelWithClientAndRecipe = new ModelStubAcceptingClientAndRecipeAdded();
        Client client = new ClientBuilder().build();
        Recipe recipe = new RecipeBuilder().build();
        modelWithClientAndRecipe.addClient(client);
        modelWithClientAndRecipe.addRecipe(recipe);

        Order expectedModelOrder = new OrderBuilder().build();
        OrderDescriptor validDescriptor = new OrderDescriptorBuilder(expectedModelOrder).build();
        assertEquals(expectedModelOrder, validDescriptor.toModelType(modelWithClientAndRecipe));

        // With client index
        expectedModelOrder = new OrderBuilder()
                .withClientName(client.getName().toString())
                .withClientPhone(client.getPhone().toString())
                .withClientAddress(client.getAddress().toString())
                .build();
        validDescriptor = new OrderDescriptorBuilder(expectedModelOrder).withClientIndex(INDEX_FIRST).build();
        assertEquals(expectedModelOrder, validDescriptor.toModelType(modelWithClientAndRecipe));

        // With recipe index
        expectedModelOrder = new OrderBuilder()
                .withRecipeName(recipe.getName().toString())
                .withRecipeIngredients(recipe.getRecipeIngredients())
                .withOrderPrice(recipe.getRecipePrice().multiplyRecipePriceByQuantity(expectedModelOrder
                        .getQuantity()).toString())
                .build();
        validDescriptor = new OrderDescriptorBuilder(expectedModelOrder).withRecipeIndex(INDEX_FIRST).build();
        assertEquals(expectedModelOrder, validDescriptor.toModelType(modelWithClientAndRecipe));
    }

    @Test
    public void toModelTypeFrom() throws CommandException {
        Order expectedModelOrder = new OrderBuilder().build();
        Model modelWithClientAndRecipe = new ModelStubAcceptingClientAndRecipeAdded();
        Client client = new ClientBuilder().build();
        Recipe recipe = new RecipeBuilder().build();
        modelWithClientAndRecipe.addClient(client);
        modelWithClientAndRecipe.addRecipe(recipe);

        // All compulsory fields in descriptor are non null
        Order anotherModelOrder = new OrderBuilder().withClientName("Imposter").build();
        OrderDescriptor validOrderDescriptor = new OrderDescriptorBuilder(expectedModelOrder).build();
        assertEquals(expectedModelOrder,
                validOrderDescriptor.toModelTypeFrom(modelWithClientAndRecipe, anotherModelOrder));

        // Client fields in descriptor are null
        validOrderDescriptor = new OrderDescriptorBuilder(expectedModelOrder).build();
        validOrderDescriptor.setClientIndex(null);
        validOrderDescriptor.setClientName(null);
        validOrderDescriptor.setClientPhone(null);
        validOrderDescriptor.setClientAddress(null);
        assertEquals(expectedModelOrder,
                validOrderDescriptor.toModelTypeFrom(modelWithClientAndRecipe, expectedModelOrder));

        // Recipe fields in descriptor are null
        validOrderDescriptor = new OrderDescriptorBuilder(expectedModelOrder).build();
        validOrderDescriptor.setRecipeIndex(null);
        validOrderDescriptor.setRecipeName(null);
        validOrderDescriptor.setRecipeIngredients(null);
        assertEquals(expectedModelOrder,
                validOrderDescriptor.toModelTypeFrom(modelWithClientAndRecipe, expectedModelOrder));

        // Order fields in descriptor are null
        validOrderDescriptor = new OrderDescriptorBuilder(expectedModelOrder).build();
        validOrderDescriptor.setOrderPrice(null);
        validOrderDescriptor.setDeadline(null);
        validOrderDescriptor.setQuantity(null);
        validOrderDescriptor.setCompletionStatus(null);
        assertEquals(expectedModelOrder,
                validOrderDescriptor.toModelTypeFrom(modelWithClientAndRecipe, expectedModelOrder));
    }

    @Test
    public void equals() {
        // same values -> returns true
        OrderDescriptor descriptorWithSameValues = new OrderDescriptor(DESC_ORDER_AMY);
        assertTrue(DESC_ORDER_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ORDER_AMY.equals(DESC_ORDER_AMY));

        // null -> returns false
        assertFalse(DESC_ORDER_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_ORDER_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_ORDER_AMY.equals(DESC_ORDER_BOB));

        OrderDescriptor editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withClientIndex(INDEX_SECOND).build();
        // different client index -> returns false
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different client name -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY).withClientName(VALID_NAME_BOB).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different client phone -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withClientPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different client address -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withClientAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different recipe index -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withRecipeIndex(INDEX_SECOND).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different recipe name -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withRecipeName("random").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different recipe ingredients -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withRecipeIngredients(List.of(AVOCADO, BREAD)).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different order price -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withOrderPrice("10000").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different deadline -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withDeadline("12-12-1998 1830").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different quantity -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withQuantity("10000").build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));

        // different done status -> returns false
        editedOrderDescriptor = new OrderDescriptorBuilder(DESC_ORDER_AMY)
                .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES).build();
        assertFalse(DESC_ORDER_AMY.equals(editedOrderDescriptor));
    }
}
