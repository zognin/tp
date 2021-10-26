package ay2122s1_cs2103t_w16_2.btbb.model.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Email;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipePrice;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new GenericString("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40")),
            new Client(new GenericString("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Client(new GenericString("Charlotte Oliveiro"), new Phone("93210283"),
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
            new Client(new GenericString("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
            new Client(new GenericString("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35")),
            new Client(new GenericString("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"))
        };
    }

    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] {
            new Ingredient(new GenericString("Almond"), new Quantity("3"), new GenericString("bags")),
            new Ingredient(new GenericString("Bamboo"), new Quantity("17"), new GenericString("shoots")),
            new Ingredient(new GenericString("Corn"), new Quantity("30"), new GenericString("whole")),
            new Ingredient(new GenericString("Duck Eggs"), new Quantity("36"), new GenericString("whole")),
            new Ingredient(new GenericString("Edamame"), new Quantity("1"), new GenericString("bag")),
            new Ingredient(new GenericString("Fig"), new Quantity("20"), new GenericString("whole")),
            new Ingredient(new GenericString("Garlic"), new Quantity("30"), new GenericString("whole")),
            new Ingredient(new GenericString("Ham"), new Quantity("10"), new GenericString("packs"))
        };
    }

    public static Recipe[] getSampleRecipes() {
        List<List<Ingredient>> ingredients = getRecipeIngredientList();
        return new Recipe[] {
            new Recipe(new GenericString("Chicken Rice"), new RecipeIngredientList(ingredients.get(0)),
                    new RecipePrice("3")),
            new Recipe(new GenericString("Nasi Lemak"), new RecipeIngredientList(ingredients.get(1)),
                    new RecipePrice("3")),
            new Recipe(new GenericString("Prata"), new RecipeIngredientList(ingredients.get(2)),
                    new RecipePrice("2")),
            new Recipe(new GenericString("Char kuay teow"), new RecipeIngredientList(ingredients.get(3)),
                    new RecipePrice("4")),
            new Recipe(new GenericString("Hokkien Prawn Mee"), new RecipeIngredientList(ingredients.get(4)),
                    new RecipePrice("4")),
            new Recipe(new GenericString("Laksa"), new RecipeIngredientList(ingredients.get(5)),
                    new RecipePrice("4")),
            new Recipe(new GenericString("Kaya toast"), new RecipeIngredientList(ingredients.get(6)),
                    new RecipePrice("2")),
            new Recipe(new GenericString("Satay"), new RecipeIngredientList(ingredients.get(7)),
                    new RecipePrice("2")),
        };
    }

    public static List<List<Ingredient>> getRecipeIngredientList() {
        return List.of(
                List.of(new Ingredient(new GenericString("Almond"), new Quantity("1"), new GenericString("bags")),
                        new Ingredient(new GenericString("Corn"), new Quantity("2"), new GenericString("whole"))
                ),
                List.of(new Ingredient(
                        new GenericString("Chicken Eggs"), new Quantity("2"), new GenericString("whole"))),
                List.of(new Ingredient(new GenericString("Garlic"), new Quantity("1"), new GenericString("whole")),
                        new Ingredient(new GenericString("Ham"), new Quantity("1"), new GenericString("packs")),
                        new Ingredient(new GenericString("Fig"), new Quantity("5"), new GenericString("whole"))),
                List.of(new Ingredient(new GenericString("Bamboo"), new Quantity("2"), new GenericString("shoots")),
                        new Ingredient(new GenericString("Edamame"), new Quantity("1"), new GenericString("bag"))),
                List.of(new Ingredient(new GenericString("Edamame"), new Quantity("1"), new GenericString("bag"))),
                List.of(new Ingredient(new GenericString("Ducks Eggs"), new Quantity("1"), new GenericString("whole")),
                        new Ingredient(new GenericString("Ham"), new Quantity("1"), new GenericString("packs")),
                        new Ingredient(new GenericString("Fig"), new Quantity("5"), new GenericString("whole")),
                        new Ingredient(new GenericString("Garlic"), new Quantity("1"), new GenericString("whole"))),
                List.of(new Ingredient(new GenericString("Corn"), new Quantity("1"), new GenericString("whole"))),
                List.of(new Ingredient(new GenericString("Fig"), new Quantity("1"), new GenericString("whole")))
        );
    }

    public static Order[] getSampleOrders() {
        Client[] clients = getSampleClients();
        Recipe[] recipes = getSampleRecipes();

        return new Order[] {
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[7].getName(), recipes[7].getRecipeIngredients(), new OrderPrice("15"),
                    new Deadline("01-11-2020 1200"), new Quantity("3"), new CompletionStatus(true)),
            new Order(clients[1].getName(), clients[1].getPhone(), clients[1].getAddress(),
                    recipes[6].getName(), recipes[6].getRecipeIngredients(), new OrderPrice("10"),
                    new Deadline("16-11-2020 1600"), new Quantity("1"), new CompletionStatus(true)),
            new Order(clients[4].getName(), clients[4].getPhone(), clients[4].getAddress(),
                    recipes[5].getName(), recipes[5].getRecipeIngredients(), new OrderPrice("20"),
                    new Deadline("13-11-2020 1200"), new Quantity("4"), new CompletionStatus(true)),
            new Order(clients[4].getName(), clients[4].getPhone(), clients[4].getAddress(),
                    recipes[4].getName(), recipes[4].getRecipeIngredients(), new OrderPrice("30"),
                    new Deadline("04-12-2020 1030"), new Quantity("6"), new CompletionStatus(true)),
            new Order(clients[4].getName(), clients[4].getPhone(), clients[4].getAddress(),
                    recipes[3].getName(), recipes[3].getRecipeIngredients(), new OrderPrice("70"),
                    new Deadline("13-12-2020 1430"), new Quantity("6"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[2].getName(), recipes[2].getRecipeIngredients(), new OrderPrice("16"),
                    new Deadline("20-12-2020 1030"), new Quantity("2"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[7].getName(), recipes[7].getRecipeIngredients(), new OrderPrice("15"),
                    new Deadline("01-01-2021 1200"), new Quantity("3"), new CompletionStatus(true)),
            new Order(clients[1].getName(), clients[1].getPhone(), clients[1].getAddress(),
                    recipes[6].getName(), recipes[6].getRecipeIngredients(), new OrderPrice("10"),
                    new Deadline("16-01-2021 1600"), new Quantity("1"), new CompletionStatus(true)),
            new Order(clients[2].getName(), clients[2].getPhone(), clients[2].getAddress(),
                    recipes[5].getName(), recipes[5].getRecipeIngredients(), new OrderPrice("20"),
                    new Deadline("13-01-2021 1200"), new Quantity("4"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[4].getName(), recipes[4].getRecipeIngredients(), new OrderPrice("30"),
                    new Deadline("04-02-2021 1030"), new Quantity("6"), new CompletionStatus(true)),
            new Order(clients[4].getName(), clients[4].getPhone(), clients[4].getAddress(),
                    recipes[3].getName(), recipes[3].getRecipeIngredients(), new OrderPrice("70"),
                    new Deadline("13-02-2021 1430"), new Quantity("6"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[2].getName(), recipes[2].getRecipeIngredients(), new OrderPrice("16"),
                    new Deadline("20-02-2021 1030"), new Quantity("2"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[1].getName(), recipes[1].getRecipeIngredients(), new OrderPrice("16"),
                    new Deadline("04-03-2021 1200"), new Quantity("2"), new CompletionStatus(true)),
            new Order(clients[1].getName(), clients[1].getPhone(), clients[1].getAddress(),
                    recipes[0].getName(), recipes[0].getRecipeIngredients(), new OrderPrice("40"),
                    new Deadline("14-03-2021 1430"), new Quantity("4"), new CompletionStatus(true)),
            new Order(clients[2].getName(), clients[2].getPhone(), clients[2].getAddress(),
                    recipes[7].getName(), recipes[7].getRecipeIngredients(), new OrderPrice("21"),
                    new Deadline("20-03-2021 1430"), new Quantity("3"), new CompletionStatus(true)),
            new Order(clients[2].getName(), clients[2].getPhone(), clients[2].getAddress(),
                    recipes[6].getName(), recipes[6].getRecipeIngredients(), new OrderPrice("9"),
                    new Deadline("06-04-2021 1200"), new Quantity("1"), new CompletionStatus(true)),
            new Order(clients[5].getName(), clients[5].getPhone(), clients[5].getAddress(),
                    recipes[5].getName(), recipes[5].getRecipeIngredients(), new OrderPrice("40"),
                    new Deadline("13-04-2021 1030"), new Quantity("4"), new CompletionStatus(true)),
            new Order(clients[5].getName(), clients[5].getPhone(), clients[5].getAddress(),
                    recipes[4].getName(), recipes[4].getRecipeIngredients(), new OrderPrice("30"),
                    new Deadline("24-04-2021 1230"), new Quantity("6"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[3].getName(), recipes[3].getRecipeIngredients(), new OrderPrice("70"),
                    new Deadline("03-05-2021 1230"), new Quantity("6"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[2].getName(), recipes[2].getRecipeIngredients(), new OrderPrice("16"),
                    new Deadline("20-05-2021 1030"), new Quantity("2"), new CompletionStatus(true)),
            new Order(clients[2].getName(), clients[2].getPhone(), clients[2].getAddress(),
                    recipes[1].getName(), recipes[1].getRecipeIngredients(), new OrderPrice("16"),
                    new Deadline("24-05-2021 1230"), new Quantity("2"), new CompletionStatus(true)),
            new Order(clients[4].getName(), clients[4].getPhone(), clients[4].getAddress(),
                    recipes[0].getName(), recipes[0].getRecipeIngredients(), new OrderPrice("40"),
                    new Deadline("01-06-2021 1230"), new Quantity("4"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[7].getName(), recipes[7].getRecipeIngredients(), new OrderPrice("9"),
                    new Deadline("20-06-2021 1200"), new Quantity("1"), new CompletionStatus(true)),
            new Order(clients[5].getName(), clients[5].getPhone(), clients[5].getAddress(),
                    recipes[6].getName(), recipes[6].getRecipeIngredients(), new OrderPrice("10"),
                    new Deadline("22-06-2021 1600"), new Quantity("1"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[5].getName(), recipes[5].getRecipeIngredients(), new OrderPrice("20"),
                    new Deadline("12-07-2021 1200"), new Quantity("4"), new CompletionStatus(true)),
            new Order(clients[1].getName(), clients[1].getPhone(), clients[1].getAddress(),
                    recipes[4].getName(), recipes[4].getRecipeIngredients(), new OrderPrice("18"),
                    new Deadline("17-07-2021 1030"), new Quantity("3"), new CompletionStatus(true)),
            new Order(clients[3].getName(), clients[3].getPhone(), clients[3].getAddress(),
                    recipes[3].getName(), recipes[3].getRecipeIngredients(), new OrderPrice("70"),
                    new Deadline("20-07-2021 1430"), new Quantity("6"), new CompletionStatus(true)),
            new Order(clients[3].getName(), clients[3].getPhone(), clients[3].getAddress(),
                    recipes[2].getName(), recipes[2].getRecipeIngredients(), new OrderPrice("80"),
                    new Deadline("20-08-2021 1030"), new Quantity("7"), new CompletionStatus(true)),
            new Order(clients[4].getName(), clients[4].getPhone(), clients[4].getAddress(),
                    recipes[1].getName(), recipes[1].getRecipeIngredients(), new OrderPrice("40"),
                    new Deadline("24-08-2021 1200"), new Quantity("4"), new CompletionStatus(true)),
            new Order(clients[5].getName(), clients[5].getPhone(), clients[5].getAddress(),
                    recipes[0].getName(), recipes[0].getRecipeIngredients(), new OrderPrice("20"),
                    new Deadline("30-08-2021 1430"), new Quantity("1"), new CompletionStatus(true)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[7].getName(), recipes[7].getRecipeIngredients(), new OrderPrice("9"),
                    new Deadline("10-09-2021 1200"), new Quantity("1"), new CompletionStatus(false)),
            new Order(clients[1].getName(), clients[1].getPhone(), clients[1].getAddress(),
                    recipes[6].getName(), recipes[6].getRecipeIngredients(), new OrderPrice("40"),
                    new Deadline("22-09-2021 1600"), new Quantity("4"), new CompletionStatus(false)),
            new Order(clients[3].getName(), clients[3].getPhone(), clients[3].getAddress(),
                    recipes[5].getName(), recipes[5].getRecipeIngredients(), new OrderPrice("24"),
                    new Deadline("29-09-2021 1200"), new Quantity("3"), new CompletionStatus(false)),
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[4].getName(), recipes[4].getRecipeIngredients(), new OrderPrice("18"),
                    new Deadline("17-10-2021 1030"), new Quantity("2"), new CompletionStatus(false)),
            new Order(clients[4].getName(), clients[4].getPhone(), clients[4].getAddress(),
                    recipes[3].getName(), recipes[3].getRecipeIngredients(), new OrderPrice("25"),
                    new Deadline("18-10-2021 1430"), new Quantity("5"), new CompletionStatus(false)),
            new Order(clients[5].getName(), clients[5].getPhone(), clients[5].getAddress(),
                    recipes[2].getName(), recipes[2].getRecipeIngredients(), new OrderPrice("80"),
                    new Deadline("25-10-2021 1030"), new Quantity("7"), new CompletionStatus(false))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }

        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleAb.addIngredient(sampleIngredient);
        }

        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }

        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleAb.addRecipe(sampleRecipe);
        }

        return sampleAb;
    }
}
