package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_DECEMBER;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_DEADLINE_MARCH;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ORDER_COMPLETION_STATUS_NO;
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
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ORDER_FOR_ALICE =
            new OrderBuilder().withClientName(ALICE.getName()).withClientPhone(ALICE.getPhone())
                    .withClientAddress(ALICE.getAddress()).withRecipeName(new GenericString("Chicken Rice"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(ALICE)))
                    .withOrderPrice(new OrderPrice("4.50")).withDeadline(new Deadline("12-12-2021 1200"))
                    .withQuantity(new Quantity("4")).withCompletionStatus(new CompletionStatus("yes"))
                    .build();
    public static final Order ORDER_FOR_BENSON =
            new OrderBuilder().withClientName(BENSON.getName()).withClientPhone(BENSON.getPhone())
                    .withClientAddress(BENSON.getAddress()).withRecipeName(new GenericString("Nasi Lemak"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(BENSON)))
                    .withOrderPrice(new OrderPrice("5.50")).withDeadline(new Deadline("13-12-2021 1300"))
                    .withQuantity(new Quantity("2")).withCompletionStatus(new CompletionStatus("no"))
                    .build();
    public static final Order ORDER_FOR_CARL =
            new OrderBuilder().withClientName(CARL.getName()).withClientPhone(CARL.getPhone())
                    .withClientAddress(CARL.getAddress()).withRecipeName(new GenericString("Prata"))
                    .withOrderPrice(new OrderPrice("6.50")).withDeadline(new Deadline("14-12-2021 1020"))
                    .withQuantity(new Quantity("1")).withCompletionStatus(new CompletionStatus("yes"))
                    .build();
    public static final Order ORDER_FOR_DANIEL =
            new OrderBuilder().withClientName(DANIEL.getName()).withClientPhone(DANIEL.getPhone())
                    .withClientAddress(DANIEL.getAddress()).withRecipeName(new GenericString("Char kuay teow"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(DANIEL)))
                    .withOrderPrice(new OrderPrice("7.00")).withDeadline(new Deadline("15-12-2021 1510"))
                    .withQuantity(new Quantity("2")).withCompletionStatus(new CompletionStatus("no"))
                    .build();
    public static final Order ORDER_FOR_ELLE =
            new OrderBuilder().withClientName(ELLE.getName()).withClientPhone(ELLE.getPhone())
                    .withClientAddress(ELLE.getAddress()).withRecipeName(new GenericString("Hokkien prawn mee"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(ELLE)))
                    .withOrderPrice(new OrderPrice("8.50")).withDeadline(new Deadline("16-12-2021 1900"))
                    .withQuantity(new Quantity("2")).withCompletionStatus(new CompletionStatus("yes"))
                    .build();
    public static final Order ORDER_FOR_FIONA =
            new OrderBuilder().withClientName(FIONA.getName()).withClientPhone(FIONA.getPhone())
                    .withClientAddress(FIONA.getAddress()).withRecipeName(new GenericString("Kaya toast"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(FIONA)))
                    .withOrderPrice(new OrderPrice("9.57")).withDeadline(new Deadline("17-12-2021 2100"))
                    .withQuantity(new Quantity("2")).withCompletionStatus(new CompletionStatus("no"))
                    .build();
    public static final Order ORDER_FOR_GEORGE =
            new OrderBuilder().withClientName(GEORGE.getName()).withClientPhone(GEORGE.getPhone())
                    .withClientAddress(GEORGE.getAddress()).withRecipeName(new GenericString("Satay"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(GEORGE)))
                    .withOrderPrice(new OrderPrice("10")).withDeadline(new Deadline("18-12-2021 2359"))
                    .withQuantity(new Quantity("2")).withCompletionStatus(new CompletionStatus("yes"))
                    .build();
    public static final Order ORDER_FOR_HARRY =
            new OrderBuilder().withClientName(HARRY.getName()).withClientPhone(HARRY.getPhone())
                    .withClientAddress(HARRY.getAddress()).withRecipeName(new GenericString("Cheese cake"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(HARRY)))
                    .withOrderPrice(new OrderPrice("7")).withDeadline(new Deadline("18-12-2021 2200"))
                    .withQuantity(new Quantity("2")).withCompletionStatus(new CompletionStatus("no"))
                    .build();
    public static final Order ORDER_FOR_IRIS =
            new OrderBuilder().withClientName(IRIS.getName()).withClientPhone(IRIS.getPhone())
                    .withClientAddress(IRIS.getAddress()).withRecipeName(new GenericString("Porridge"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(IRIS)))
                    .withOrderPrice(new OrderPrice("10")).withDeadline(new Deadline("18-11-2021 2359"))
                    .withQuantity(new Quantity("3")).withCompletionStatus(new CompletionStatus("no"))
                    .build();
    public static final Order ORDER_FOR_JANE =
            new OrderBuilder().withClientName(JANE.getName()).withClientPhone(JANE.getPhone())
                    .withClientAddress(JANE.getAddress()).withRecipeName(new GenericString("Prawn Noodles"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(JANE)))
                    .withOrderPrice(new OrderPrice("10")).withDeadline(new Deadline("18-12-2021 2359"))
                    .withQuantity(new Quantity("7")).withCompletionStatus(new CompletionStatus("no"))
                    .build();
    public static final Order ORDER_FOR_KELLY =
            new OrderBuilder().withClientName(KELLY.getName()).withClientPhone(KELLY.getPhone())
                    .withClientAddress(KELLY.getAddress()).withRecipeName(new GenericString("Fish Soup with Milk"))
                    .withRecipeIngredients(new RecipeIngredientList(getRecipeIngredientListForClient(KELLY)))
                    .withOrderPrice(new OrderPrice("10")).withDeadline(new Deadline("18-12-2021 2359"))
                    .withQuantity(new Quantity("6")).withCompletionStatus(new CompletionStatus("yes"))
                    .build();

    // Manually added - Order's details found in {@code TypicalClients} and {@code CommandTestUtil}
    public static final Order ORDER_FOR_AMY =
            new OrderBuilder().withClientName(AMY.getName()).withClientPhone(AMY.getPhone())
                    .withClientAddress(AMY.getAddress())
                    .withRecipeName(new GenericString(VALID_RECIPE_NAME_CHICKEN_RICE))
                    .withOrderPrice(new OrderPrice(VALID_ORDER_PRICE_1))
                    .withDeadline(new Deadline(VALID_DEADLINE_DECEMBER))
                    .withQuantity(new Quantity(VALID_ORDER_QUANTITY_1))
                    .withCompletionStatus(new CompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO))
                    .build();
    public static final Order ORDER_FOR_BOB =
            new OrderBuilder().withClientName(BOB.getName()).withClientPhone(BOB.getPhone())
                    .withClientAddress(BOB.getAddress()).withRecipeName(new GenericString(VALID_RECIPE_NAME_LAKSA))
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_BEEF), new Quantity(VALID_QUANTITY_BEEF),
                            new GenericString(VALID_UNIT_BEEF)))))
                    .withOrderPrice(new OrderPrice(VALID_ORDER_PRICE_2))
                    .withDeadline(new Deadline(VALID_DEADLINE_MARCH))
                    .withQuantity(new Quantity(VALID_ORDER_QUANTITY_2))
                    .withCompletionStatus(new CompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO))
                    .build();
    public static final Order ORDER_FOR_IMRAN =
            new OrderBuilder().withClientName(IMRAN.getName()).withClientPhone(IMRAN.getPhone())
                    .withClientAddress(IMRAN.getAddress())
                    .withRecipeName(new GenericString(VALID_RECIPE_NAME_CHICKEN_RICE))
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_APPLE), new Quantity(VALID_QUANTITY_APPLE),
                            new GenericString(VALID_UNIT_APPLE)))))
                    .withOrderPrice(new OrderPrice(VALID_ORDER_PRICE_1))
                    .withDeadline(new Deadline(VALID_DEADLINE_DECEMBER))
                    .withCompletionStatus(new CompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO))
                    .build();
    public static final Order ORDER_FOR_HOON =
            new OrderBuilder().withClientName(HOON.getName()).withClientPhone(HOON.getPhone())
                    .withClientAddress(HOON.getAddress())
                    .withRecipeName(new GenericString(VALID_RECIPE_NAME_CHICKEN_RICE))
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_APPLE), new Quantity(VALID_QUANTITY_APPLE),
                            new GenericString(VALID_UNIT_APPLE)))))
                    .withOrderPrice(new OrderPrice(VALID_ORDER_PRICE_1))
                    .withDeadline(new Deadline(VALID_DEADLINE_DECEMBER))
                    .withCompletionStatus(new CompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO))
                    .build();
    public static final Order ORDER_FOR_IDA =
            new OrderBuilder().withClientName(IDA.getName()).withClientPhone(IDA.getPhone())
                    .withClientAddress(IDA.getAddress())
                    .withRecipeName(new GenericString(VALID_RECIPE_NAME_CHICKEN_RICE))
                    .withRecipeIngredients(new RecipeIngredientList(List.of(new Ingredient(
                            new GenericString(VALID_INGREDIENT_NAME_APPLE), new Quantity(VALID_QUANTITY_APPLE),
                            new GenericString(VALID_UNIT_APPLE)))))
                    .withOrderPrice(new OrderPrice(VALID_ORDER_PRICE_1))
                    .withDeadline(new Deadline(VALID_DEADLINE_DECEMBER))
                    .withCompletionStatus(new CompletionStatus(VALID_ORDER_COMPLETION_STATUS_NO))
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
