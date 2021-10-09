package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_DECEMBER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PRICE_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_INGREDIENT_LIST_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_INGREDIENT_LIST_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_CHICKEN_RICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.GEORGE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.IMRAN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ORDER_FOR_ALICE =
            new OrderBuilder().withClientName(ALICE.getName()).withClientPhone(ALICE.getPhone())
                    .withClientAddress(ALICE.getAddress()).withRecipeName(new GenericString("Chicken Rice"))
                    .withRecipeIngredients(new RecipeIngredientList("Chicken-1-whole, Rice-200-grams"))
                    .withPrice(new Price("4.50")).withDeadline(new Deadline("12-12-2021 1200"))
                    .withQuantity(new Quantity("1"))
                    .build();
    public static final Order ORDER_FOR_BENSON =
            new OrderBuilder().withClientName(BENSON.getName()).withClientPhone(BENSON.getPhone())
                    .withClientAddress(BENSON.getAddress()).withRecipeName(new GenericString("Nasi Lemak"))
                    .withRecipeIngredients(new RecipeIngredientList("Fish-1-whole, Rice-200-grams"))
                    .withPrice(new Price("5.50")).withDeadline(new Deadline("13-12-2021 1300"))
                    .withQuantity(new Quantity("2"))
                    .build();
    public static final Order ORDER_FOR_CARL =
            new OrderBuilder().withClientName(CARL.getName()).withClientPhone(CARL.getPhone())
                    .withClientAddress(CARL.getAddress()).withRecipeName(new GenericString("Prata"))
                    .withRecipeIngredients(new RecipeIngredientList())
                    .withPrice(new Price("6.50")).withDeadline(new Deadline("14-12-2021 1020"))
                    .withQuantity(new Quantity("1"))
                    .build();
    public static final Order ORDER_FOR_DANIEL =
            new OrderBuilder().withClientName(DANIEL.getName()).withClientPhone(DANIEL.getPhone())
                    .withClientAddress(DANIEL.getAddress()).withRecipeName(new GenericString("Char kuay teow"))
                    .withRecipeIngredients(new RecipeIngredientList("Noodles-500-grams"))
                    .withPrice(new Price("7.0")).withDeadline(new Deadline("15-12-2021 1510"))
                    .withQuantity(new Quantity("2"))
                    .build();
    public static final Order ORDER_FOR_ELLE =
            new OrderBuilder().withClientName(ELLE.getName()).withClientPhone(ELLE.getPhone())
                    .withClientAddress(ELLE.getAddress()).withRecipeName(new GenericString("Hokkien prawn mee"))
                    .withRecipeIngredients(new RecipeIngredientList("Prawn-5-whole, Noodles-300-grams"))
                    .withPrice(new Price("8.5")).withDeadline(new Deadline("16-12-2021 1900"))
                    .withQuantity(new Quantity("2"))
                    .build();
    public static final Order ORDER_FOR_FIONA =
            new OrderBuilder().withClientName(FIONA.getName()).withClientPhone(FIONA.getPhone())
                    .withClientAddress(FIONA.getAddress()).withRecipeName(new GenericString("Kaya toast"))
                    .withRecipeIngredients(new RecipeIngredientList("Bread-2-slices, Kaya-50-grams"))
                    .withPrice(new Price("9.57")).withDeadline(new Deadline("17-12-2021 2100"))
                    .withQuantity(new Quantity("2"))
                    .build();
    public static final Order ORDER_FOR_GEORGE =
            new OrderBuilder().withClientName(GEORGE.getName()).withClientPhone(GEORGE.getPhone())
                    .withClientAddress(GEORGE.getAddress()).withRecipeName(new GenericString("Satay"))
                    .withRecipeIngredients(new RecipeIngredientList("Chicken-1-whole"))
                    .withPrice(new Price("10")).withDeadline(new Deadline("18-12-2021 2359"))
                    .withQuantity(new Quantity("2"))
                    .build();

    // Manually added - Order's details found in {@code TypicalClients} and {@code CommandTestUtil}
    public static final Order ORDER_FOR_AMY =
            new OrderBuilder().withClientName(AMY.getName()).withClientPhone(AMY.getPhone())
                    .withClientAddress(AMY.getAddress())
                    .withRecipeName(new GenericString(VALID_RECIPE_NAME_CHICKEN_RICE))
                    .withRecipeIngredients(new RecipeIngredientList()).withPrice(new Price(VALID_PRICE_1))
                    .withDeadline(new Deadline(VALID_DEADLINE_DECEMBER))
                    .withQuantity(new Quantity(VALID_ORDER_QUANTITY_1))
                    .build();
    public static final Order ORDER_FOR_IMRAN =
            new OrderBuilder().withClientName(IMRAN.getName()).withClientPhone(IMRAN.getPhone())
                    .withClientAddress(IMRAN.getAddress())
                    .withRecipeName(new GenericString(VALID_RECIPE_NAME_CHICKEN_RICE))
                    .withRecipeIngredients(new RecipeIngredientList(VALID_RECIPE_INGREDIENT_LIST_1))
                    .withPrice(new Price(VALID_PRICE_1)).withDeadline(new Deadline(VALID_DEADLINE_DECEMBER))
                    .withQuantity(new Quantity()).build();
    public static final Order ORDER_FOR_BOB =
            new OrderBuilder().withClientName(BOB.getName()).withClientPhone(BOB.getPhone())
                    .withClientAddress(BOB.getAddress()).withRecipeName(new GenericString(VALID_RECIPE_NAME_LAKSA))
                    .withRecipeIngredients(new RecipeIngredientList(VALID_RECIPE_INGREDIENT_LIST_2))
                    .withPrice(new Price(VALID_PRICE_2)).withDeadline(new Deadline(VALID_DEADLINE_MARCH))
                    .withQuantity(new Quantity(VALID_ORDER_QUANTITY_2))
                    .build();

    /**
     * Returns an {@code AddressBook} with all the typical orders.
     *
     * @return {@code AddressBook} with all the typical orders.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    /**
     * Returns a list of typical orders.
     *
     * @return List of typical orders.
     */
    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_FOR_ALICE, ORDER_FOR_BENSON, ORDER_FOR_CARL, ORDER_FOR_DANIEL,
                ORDER_FOR_ELLE, ORDER_FOR_FIONA, ORDER_FOR_GEORGE));
    }
}
