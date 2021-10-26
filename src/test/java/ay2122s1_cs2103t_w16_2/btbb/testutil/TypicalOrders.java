package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_DECEMBER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_NO;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_YES;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_PRICE_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_PRICE_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_1;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_2;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_CHICKEN_RICE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_LAKSA;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.GEORGE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.HARRY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.HOON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.IDA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.IMRAN;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.IRIS;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.JANE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.KELLY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.BREAD;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.CHICKEN;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.FISH;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.KAYA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.NOODLES;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.PRAWN;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIngredients.RICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ORDER_FOR_ALICE =
            new OrderBuilder().withClientName(ALICE.getName().toString()).withClientPhone(ALICE.getPhone().toString())
                    .withClientAddress(ALICE.getAddress().toString()).withRecipeName("Chicken Rice")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(ALICE)))
                    .withOrderPrice("4.50").withDeadline("12-12-2021 1200")
                    .withQuantity("4").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES)
                    .build();
    public static final Order ORDER_FOR_BENSON =
            new OrderBuilder().withClientName(BENSON.getName().toString()).withClientPhone(BENSON.getPhone().toString())
                    .withClientAddress(BENSON.getAddress().toString()).withRecipeName("Nasi Lemak")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(BENSON)))
                    .withOrderPrice("5.50").withDeadline("13-12-2021 1300")
                    .withQuantity("2").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_CARL =
            new OrderBuilder().withClientName(CARL.getName().toString()).withClientPhone(CARL.getPhone().toString())
                    .withClientAddress(CARL.getAddress().toString()).withRecipeName("Prata")
                    .withOrderPrice("6.50").withDeadline("14-12-2021 1020")
                    .withQuantity("1").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES)
                    .build();
    public static final Order ORDER_FOR_DANIEL =
            new OrderBuilder().withClientName(DANIEL.getName().toString()).withClientPhone(DANIEL.getPhone().toString())
                    .withClientAddress(DANIEL.getAddress().toString()).withRecipeName("Char kuay teow")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(DANIEL)))
                    .withOrderPrice("7.00").withDeadline("15-12-2021 1510")
                    .withQuantity("2").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_ELLE =
            new OrderBuilder().withClientName(ELLE.getName().toString()).withClientPhone(ELLE.getPhone().toString())
                    .withClientAddress(ELLE.getAddress().toString()).withRecipeName("Hokkien prawn mee")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(ELLE)))
                    .withOrderPrice("8.50").withDeadline("16-12-2021 1900")
                    .withQuantity("2").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES)
                    .build();
    public static final Order ORDER_FOR_FIONA =
            new OrderBuilder().withClientName(FIONA.getName().toString()).withClientPhone(FIONA.getPhone().toString())
                    .withClientAddress(FIONA.getAddress().toString()).withRecipeName("Kaya toast")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(FIONA)))
                    .withOrderPrice("9.57").withDeadline("17-12-2021 2100")
                    .withQuantity("2").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_GEORGE =
            new OrderBuilder().withClientName(GEORGE.getName().toString()).withClientPhone(GEORGE.getPhone().toString())
                    .withClientAddress(GEORGE.getAddress().toString()).withRecipeName("Satay")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(GEORGE)))
                    .withOrderPrice("10").withDeadline("18-12-2021 2359")
                    .withQuantity("2").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES)
                    .build();
    public static final Order ORDER_FOR_HARRY =
            new OrderBuilder().withClientName(HARRY.getName().toString()).withClientPhone(HARRY.getPhone().toString())
                    .withClientAddress(HARRY.getAddress().toString()).withRecipeName("Cheese cake")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(HARRY)))
                    .withOrderPrice("7").withDeadline("18-12-2021 2200")
                    .withQuantity("2").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_IRIS =
            new OrderBuilder().withClientName(IRIS.getName().toString()).withClientPhone(IRIS.getPhone().toString())
                    .withClientAddress(IRIS.getAddress().toString()).withRecipeName("Porridge")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(IRIS)))
                    .withOrderPrice("10").withDeadline("18-11-2021 2359")
                    .withQuantity("3").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_JANE =
            new OrderBuilder().withClientName(JANE.getName().toString()).withClientPhone(JANE.getPhone().toString())
                    .withClientAddress(JANE.getAddress().toString()).withRecipeName("Prawn Noodles")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(JANE)))
                    .withOrderPrice("10").withDeadline("18-12-2021 2359")
                    .withQuantity("7").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_KELLY =
            new OrderBuilder().withClientName(KELLY.getName().toString()).withClientPhone(KELLY.getPhone().toString())
                    .withClientAddress(KELLY.getAddress().toString()).withRecipeName("Fish Soup with Milk")
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(KELLY)))
                    .withOrderPrice("10").withDeadline("18-12-2021 2359")
                    .withQuantity("6").withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_YES)
                    .build();

    // Manually added - Order's details found in {@code TypicalClients} and {@code CommandTestUtil}
    public static final Order ORDER_FOR_AMY =
            new OrderBuilder().withClientName(AMY.getName().toString()).withClientPhone(AMY.getPhone().toString())
                    .withClientAddress(AMY.getAddress().toString())
                    .withRecipeName(VALID_RECIPE_NAME_CHICKEN_RICE)
                    .withOrderPrice(VALID_ORDER_PRICE_1)
                    .withDeadline(VALID_DEADLINE_DECEMBER)
                    .withQuantity(VALID_ORDER_QUANTITY_1)
                    .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_BOB =
            new OrderBuilder().withClientName(BOB.getName().toString()).withClientPhone(BOB.getPhone().toString())
                    .withClientAddress(BOB.getAddress().toString()).withRecipeName(VALID_RECIPE_NAME_LAKSA)
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_BEEF), new Quantity(VALID_QUANTITY_BEEF),
                            new GenericString(VALID_UNIT_BEEF)))))
                    .withOrderPrice(VALID_ORDER_PRICE_2)
                    .withDeadline(VALID_DEADLINE_MARCH)
                    .withQuantity(VALID_ORDER_QUANTITY_2)
                    .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_IMRAN =
            new OrderBuilder().withClientName(IMRAN.getName().toString()).withClientPhone(IMRAN.getPhone().toString())
                    .withClientAddress(IMRAN.getAddress().toString())
                    .withRecipeName(VALID_RECIPE_NAME_CHICKEN_RICE)
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_APPLE), new Quantity(VALID_QUANTITY_APPLE),
                            new GenericString(VALID_UNIT_APPLE)))))
                    .withOrderPrice(VALID_ORDER_PRICE_1)
                    .withDeadline(VALID_DEADLINE_DECEMBER)
                    .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_HOON =
            new OrderBuilder().withClientName(HOON.getName().toString()).withClientPhone(HOON.getPhone().toString())
                    .withClientAddress(HOON.getAddress().toString())
                    .withRecipeName(VALID_RECIPE_NAME_CHICKEN_RICE)
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_APPLE), new Quantity(VALID_QUANTITY_APPLE),
                            new GenericString(VALID_UNIT_APPLE)))))
                    .withOrderPrice(VALID_ORDER_PRICE_1)
                    .withDeadline(VALID_DEADLINE_DECEMBER)
                    .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
                    .build();
    public static final Order ORDER_FOR_IDA =
            new OrderBuilder().withClientName(IDA.getName().toString()).withClientPhone(IDA.getPhone().toString())
                    .withClientAddress(IDA.getAddress().toString())
                    .withRecipeName(VALID_RECIPE_NAME_CHICKEN_RICE)
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_APPLE), new Quantity(VALID_QUANTITY_APPLE),
                            new GenericString(VALID_UNIT_APPLE)))))
                    .withOrderPrice(VALID_ORDER_PRICE_1)
                    .withDeadline(VALID_DEADLINE_DECEMBER)
                    .withCompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO)
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

    private static List<Ingredient> getRecipeIngredientListForClient(Client client) {
        HashMap<Client, List<Ingredient>> recipeIngredientsMap = getRecipeIngredientsMapForClient();
        if (!recipeIngredientsMap.containsKey(client)) {
            return new ArrayList<>();
        }
        return recipeIngredientsMap.get(client);
    }

    private static HashMap<Client, List<Ingredient>> getRecipeIngredientsMapForClient() {
        HashMap<Client, List<Ingredient>> recipeIngredientsMap = new HashMap<>();

        Ingredient breadUsage = new Ingredient(BREAD.getName(), new Quantity("2"), BREAD.getUnit());
        Ingredient chickenUsage = new Ingredient(CHICKEN.getName(), new Quantity("1"), CHICKEN.getUnit());
        Ingredient fishUsage = new Ingredient(FISH.getName(), new Quantity("1"), FISH.getUnit());
        Ingredient kayaUsage = new Ingredient(KAYA.getName(), new Quantity("100"), KAYA.getUnit());
        Ingredient noodlesUsage = new Ingredient(NOODLES.getName(), new Quantity("200"), NOODLES.getUnit());
        Ingredient prawnUsage = new Ingredient(PRAWN.getName(), new Quantity("10"), PRAWN.getUnit());
        Ingredient riceUsage = new Ingredient(RICE.getName(), new Quantity("200"), RICE.getUnit());


        recipeIngredientsMap.put(ALICE, List.of(chickenUsage, riceUsage));
        recipeIngredientsMap.put(BENSON, List.of(fishUsage, riceUsage));
        recipeIngredientsMap.put(DANIEL, List.of(noodlesUsage));
        recipeIngredientsMap.put(ELLE, List.of(noodlesUsage, prawnUsage));
        recipeIngredientsMap.put(FIONA, List.of(breadUsage, kayaUsage));
        recipeIngredientsMap.put(GEORGE, List.of(chickenUsage));

        return recipeIngredientsMap;
    }
}
