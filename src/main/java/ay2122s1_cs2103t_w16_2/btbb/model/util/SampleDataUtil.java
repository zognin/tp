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
        List<Order> orders = new ArrayList<>();

        Client[] people = getSampleClients();
        Recipe[] recipes = getSampleRecipes();
        Random randomNumberGenerator = new Random();

        int loopCount = Math.min(people.length, recipes.length);

        for (int month = 0; month < 12; month++) {
            for (int i = 0; i < loopCount; i++) {
                int randomIndex = randomNumberGenerator.nextInt(people.length);
                float randomPrice = randomNumberGenerator.nextFloat() * 10;
                int randomQuantity = randomNumberGenerator.nextInt(10) + 1;

                LocalDateTime orderDeadline = getSampleDateTime(month + 1, i + 1);
                boolean completionstatus = (i % 2 == 0) && orderDeadline.isBefore(LocalDateTime.now());

                Order order = new Order(
                        people[randomIndex].getName(), people[randomIndex].getPhone(), people[randomIndex].getAddress(),
                        recipes[i].getName(), recipes[i].getRecipeIngredients(),
                        new OrderPrice(String.format("%.2f", randomPrice)),
                        new Deadline(orderDeadline.format(Deadline.INPUT_DATETIME_FORMATTER)),
                        new Quantity(Integer.toString(randomQuantity)),
                        new CompletionStatus(completionstatus ? "yes" : "no")
                );

                orders.add(order);
            }
        }

        return orders.toArray(Order[]::new);
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }

        for (Order sampleOrder : getSampleOrders()) {
            sampleAb.addOrder(sampleOrder);
        }

        for (Ingredient sampleIngredient : getSampleIngredients()) {
            sampleAb.addIngredient(sampleIngredient);
        }

        for (Recipe sampleRecipe : getSampleRecipes()) {
            sampleAb.addRecipe(sampleRecipe);
        }

        return sampleAb;
    }

    private static LocalDateTime getSampleDateTime(int month, int day) {
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, day);
        return LocalDateTime.of(date, time);
    }
}
