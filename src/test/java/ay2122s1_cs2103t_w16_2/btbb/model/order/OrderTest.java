package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PRICE_2;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
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

        Order editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(new GenericString(VALID_NAME_BOB))
                .withClientPhone(new Phone(VALID_PHONE_BOB)).withClientAddress(new Address(VALID_ADDRESS_BOB))
                .withRecipeName(new GenericString(VALID_RECIPE_NAME_LAKSA))
                .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                        new GenericString(VALID_INGREDIENT_NAME_BEEF), new Quantity(VALID_QUANTITY_BEEF),
                        new GenericString(VALID_UNIT_BEEF)))))
                .withPrice(new Price(VALID_PRICE_2)).withDeadline(new Deadline(VALID_DEADLINE_MARCH))
                .withQuantity(new Quantity(VALID_ORDER_QUANTITY_2))
                .build();
        // different object, with similar fields as ORDER_FOR_BOB -> returns true
        assertTrue(ORDER_FOR_BOB.isSameOrder(editedRandomOrder));

        // different client phone -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_FOR_CARL.isSameOrder(editedRandomOrder));

        // different client name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL)
                .withClientName(new GenericString(VALID_NAME_AMY)).build();
        assertFalse(ORDER_FOR_DANIEL.isSameOrder(editedRandomOrder));

        // different client address -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ELLE).withClientAddress(new Address("Choa Chu Kang")).build();
        assertFalse(ORDER_FOR_ELLE.isSameOrder(editedRandomOrder));

        // different recipe name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_FIONA).withRecipeName(new GenericString("Sushi")).build();
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
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL).withPrice(new Price("10.50")).build();
        assertFalse(ORDER_FOR_DANIEL.isSameOrder(editedRandomOrder));

        // different order deadline -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_FIONA).withDeadline(new Deadline("11-10-2025 1000")).build();
        assertFalse(ORDER_FOR_FIONA.isSameOrder(editedRandomOrder));

        // different order quantity -> returns true
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ALICE).withQuantity(new Quantity("5")).build();
        assertTrue(ORDER_FOR_ALICE.isSameOrder(editedRandomOrder));

        // different order done status -> returns true
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BENSON).withIsDone(new IsDone(false)).build();
        assertTrue(ORDER_FOR_BENSON.isSameOrder(editedRandomOrder));
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

        Order editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(new GenericString(VALID_NAME_BOB))
                .withClientPhone(new Phone(VALID_PHONE_BOB)).withClientAddress(new Address(VALID_ADDRESS_BOB))
                .withRecipeName(new GenericString(VALID_RECIPE_NAME_LAKSA))
                .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                        new GenericString(VALID_INGREDIENT_NAME_BEEF), new Quantity(VALID_QUANTITY_BEEF),
                        new GenericString(VALID_UNIT_BEEF)))))
                .withPrice(new Price(VALID_PRICE_2)).withDeadline(new Deadline(VALID_DEADLINE_MARCH))
                .withQuantity(new Quantity(VALID_ORDER_QUANTITY_2))
                .build();
        // different object, with similar fields as ORDER_FOR_BOB -> returns true
        assertTrue(ORDER_FOR_BOB.isSameOrder(editedRandomOrder));

        // different client name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_CARL).withClientName(new GenericString(VALID_NAME_BOB)).build();
        assertFalse(ORDER_FOR_CARL.equals(editedRandomOrder));

        // different client phone -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_DANIEL).withClientPhone(new Phone(VALID_PHONE_BOB)).build();
        assertFalse(ORDER_FOR_DANIEL.equals(editedRandomOrder));

        // different client address -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ELLE).withClientAddress(new Address(VALID_ADDRESS_BOB)).build();
        assertFalse(ORDER_FOR_ELLE.equals(editedRandomOrder));

        // different recipe name -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BOB).withRecipeName(new GenericString("Chocolate Cake")).build();
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
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BOB).withPrice(new Price("0.50")).build();
        assertFalse(ORDER_FOR_DANIEL.equals(editedRandomOrder));

        // different order deadline -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_GEORGE).withDeadline(new Deadline("15-10-2025 1000")).build();
        assertFalse(ORDER_FOR_FIONA.equals(editedRandomOrder));

        // different order quantity -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_ALICE).withQuantity(new Quantity("40000")).build();
        assertFalse(ORDER_FOR_ALICE.equals(editedRandomOrder));

        // different order done status -> returns false
        editedRandomOrder = new OrderBuilder(ORDER_FOR_BENSON).withIsDone(new IsDone(true)).build();
        assertFalse(ORDER_FOR_BENSON.equals(editedRandomOrder));
    }
}
