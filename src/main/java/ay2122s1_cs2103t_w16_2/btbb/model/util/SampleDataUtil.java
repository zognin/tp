package ay2122s1_cs2103t_w16_2.btbb.model.util;

import java.util.List;

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
                new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"))
        };
    }

    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] {
            new Ingredient(new GenericString("Almond"), new Quantity("3"), new GenericString("bags")),
            new Ingredient(new GenericString("Bamboo"), new Quantity("17"), new GenericString("shoots")),
            new Ingredient(new GenericString("Corn"), new Quantity("30"), new GenericString("whole"))
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
                    new RecipePrice("2"))
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
                        new Ingredient(new GenericString("Fig"), new Quantity("5"), new GenericString("whole")))
        );
    }

    public static Order[] getSampleOrders() {
        Client[] clients = getSampleClients();
        Recipe[] recipes = getSampleRecipes();

        return new Order[] {
            new Order(clients[0].getName(), clients[0].getPhone(), clients[0].getAddress(),
                    recipes[0].getName(), recipes[0].getRecipeIngredients(), new OrderPrice("15"),
                    new Deadline("01-08-2021 1200"), new Quantity("3"), new CompletionStatus(true)),
            new Order(clients[1].getName(), clients[1].getPhone(), clients[1].getAddress(),
                    recipes[1].getName(), recipes[1].getRecipeIngredients(), new OrderPrice("10"),
                    new Deadline("16-09-2021 1600"), new Quantity("1"), new CompletionStatus(true)),
            new Order(clients[2].getName(), clients[2].getPhone(), clients[2].getAddress(),
                    recipes[2].getName(), recipes[2].getRecipeIngredients(), new OrderPrice("20"),
                    new Deadline("13-10-2021 1200"), new Quantity("4"), new CompletionStatus(true))
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
