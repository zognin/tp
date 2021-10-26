package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_NO;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_YES;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_PRICE_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_GEORGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;

class OrderTest {
    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(ORDER_FOR_ALICE.isSameOrder(ORDER_FOR_ALICE));

        // null -> returns false
        assertFalse(ORDER_FOR_BENSON.isSameOrder(null));

        Order editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(VALID_NAME_BOB)
                .withClientPhone(VALID_PHONE_BOB).withClientAddress(VALID_ADDRESS_BOB)
                .withRecipeName(VALID_RECIPE_NAME_LAKSA)
                .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                        new GenericString(VALID_INGREDIENT_NAME_BEEF), new Quantity(VALID_QUANTITY_BEEF),
                        new GenericString(VALID_UNIT_BEEF)))))
                .withOrderPrice(VALID_ORDER_PRICE_2).withDeadline(VALID_DEADLINE_MARCH)
                .withQuantity(VALID_ORDER_QUANTITY_2)
                .build();
        // different object, with similar fields as ORDER_FOR_BOB -> returns true
        assertTrue(ORDER_FOR_BOB.isSameOrder(editedRandomOrder));

        // different client phone -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientPhone(VALID_PHONE_BOB).build();
        assertFalse(ORDER_FOR_CARL.isSameOrder(editedRandomOrder));

        // different client name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL)
                .withClientName(VALID_NAME_AMY).build();
        assertFalse(ORDER_FOR_DANIEL.isSameOrder(editedRandomOrder));

        // different client address -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ELLE).withClientAddress("Choa Chu Kang").build();
        assertFalse(ORDER_FOR_ELLE.isSameOrder(editedRandomOrder));

        // different recipe name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_FIONA).withRecipeName("Sushi").build();
        assertFalse(ORDER_FOR_FIONA.isSameOrder(editedRandomOrder));

        // different recipe ingredients -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_GEORGE)
                .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                        new GenericString("RaNdoM inGredient"), new Quantity("10"), new GenericString("uNiT")))))
                .build();
        assertFalse(ORDER_FOR_GEORGE.isSameOrder(editedRandomOrder));

        // empty recipe ingredients -> return false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_GEORGE)
                .withRecipeIngredients(new RecipeIngredientList(new ArrayList<>())).build();
        assertFalse(ORDER_FOR_GEORGE.isSameOrder(editedRandomOrder));

        // different order price -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL).withOrderPrice("10.50").build();
        assertFalse(ORDER_FOR_DANIEL.isSameOrder(editedRandomOrder));

        // different order deadline -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_FIONA).withDeadline("11-10-2025 1000").build();
        assertFalse(ORDER_FOR_FIONA.isSameOrder(editedRandomOrder));

        // different order quantity -> returns true
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ALICE).withQuantity("5").build();
        assertTrue(ORDER_FOR_ALICE.isSameOrder(editedRandomOrder));

        // different order done status -> returns true
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BENSON)
                .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO).build();
        assertTrue(ORDER_FOR_BENSON.isSameOrder(editedRandomOrder));
    }

    @Test
    public void compareTo() {
        // same order -> returns 0
        assertEquals(0, ORDER_FOR_ALICE.compareTo(ORDER_FOR_ALICE));

        // order 1 not completed but order 2 completed -> returns negative value
        assertTrue(ORDER_FOR_BENSON.compareTo(ORDER_FOR_ALICE) < 0);

        // order 1 completed but order 2 not completed -> returns positive value
        assertTrue(ORDER_FOR_ALICE.compareTo(ORDER_FOR_BENSON) > 0);

        // both orders completed, order 1 deadline before order 2 deadline -> returns negative value
        assertTrue(ORDER_FOR_ALICE.compareTo(ORDER_FOR_CARL) < 0);

        // both orders completed, order 1 deadline after order 2 deadline -> returns positive value
        assertTrue(ORDER_FOR_CARL.compareTo(ORDER_FOR_ALICE) > 0);

        // both orders not completed, order 1 deadline before order 2 deadline -> returns negative value
        assertTrue(ORDER_FOR_BENSON.compareTo(ORDER_FOR_DANIEL) < 0);

        // both orders not completed, order 1 deadline after order 2 deadline -> returns positive value
        assertTrue(ORDER_FOR_DANIEL.compareTo(ORDER_FOR_BENSON) > 0);
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order orderCopy = new OrderBuilder(ORDER_FOR_DANIEL).build();
        assertTrue(ORDER_FOR_DANIEL.equals(orderCopy));

        // same object -> returns true
        assertTrue(ORDER_FOR_ALICE.equals(ORDER_FOR_ALICE));

        // null -> returns false
        assertFalse(ORDER_FOR_ALICE.equals(null));

        // different type -> returns false
        assertFalse(ORDER_FOR_ALICE.equals(5));

        // different order -> returns false
        assertFalse(ORDER_FOR_ALICE.equals(ORDER_FOR_ELLE));

        Order editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(VALID_NAME_BOB)
                .withClientPhone(VALID_PHONE_BOB).withClientAddress(VALID_ADDRESS_BOB)
                .withRecipeName(VALID_RECIPE_NAME_LAKSA)
                .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                        new GenericString(VALID_INGREDIENT_NAME_BEEF), new Quantity(VALID_QUANTITY_BEEF),
                        new GenericString(VALID_UNIT_BEEF)))))
                .withOrderPrice(VALID_ORDER_PRICE_2).withDeadline(VALID_DEADLINE_MARCH)
                .withQuantity(VALID_ORDER_QUANTITY_2)
                .build();
        // different object, with similar fields as ORDER_FOR_BOB -> returns true
        assertTrue(ORDER_FOR_BOB.isSameOrder(editedRandomOrder));

        // different client name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(VALID_NAME_BOB).build();
        assertFalse(ORDER_FOR_CARL.equals(editedRandomOrder));

        // different client phone -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL).withClientPhone(VALID_PHONE_BOB).build();
        assertFalse(ORDER_FOR_DANIEL.equals(editedRandomOrder));

        // different client address -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ELLE).withClientAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ORDER_FOR_ELLE.equals(editedRandomOrder));

        // different recipe name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BOB).withRecipeName("Chocolate Cake").build();
        assertFalse(ORDER_FOR_BOB.equals(editedRandomOrder));

        // different recipe ingredients -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ELLE)
                .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                        new GenericString("RaNdoM inGredient"), new Quantity("10"), new GenericString("uNiT")))))
        .build();
        assertFalse(ORDER_FOR_ELLE.equals(editedRandomOrder));

        // empty recipe ingredients -> return false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_FIONA)
                .withRecipeIngredients(new RecipeIngredientList(new ArrayList<>())).build();
        assertFalse(ORDER_FOR_FIONA.equals(editedRandomOrder));

        // different order price -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BOB).withOrderPrice("0.50").build();
        assertFalse(ORDER_FOR_DANIEL.equals(editedRandomOrder));

        // different order deadline -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_GEORGE).withDeadline("15-10-2025 1000").build();
        assertFalse(ORDER_FOR_FIONA.equals(editedRandomOrder));

        // different order quantity -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ALICE).withQuantity("40000").build();
        assertFalse(ORDER_FOR_ALICE.equals(editedRandomOrder));

        // different order done status -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BENSON).withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES)
                .build();
        assertFalse(ORDER_FOR_BENSON.equals(editedRandomOrder));
    }
}
