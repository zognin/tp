package ay2122s1_cs2103t_w16_2.btbb.model.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Email;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.IsDone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
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

    public static String[] getSampleRecipes() {
        return new String[] {
            "Chicken Rice",
            "Nasi Lemak",
            "Prata",
            "Char kuay teow",
            "Hokkien prawn mee",
            "Laksa",
            "Kaya toast",
            "Satay"
        };
    }

    public static List<List<Ingredient>> getRecipeIngredientList() {
        return List.of(
                List.of(new Ingredient(new GenericString("Almond"), new Quantity("1"), new GenericString("bags")),
                        new Ingredient(new GenericString("Corn"), new Quantity("2"), new GenericString("whole"))),
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
        Client[] people = getSampleClients();
        String[] recipes = getSampleRecipes();
        List<List<Ingredient>> ingredients = getRecipeIngredientList();

        Order[] orders = new Order[people.length];

        Random randomNumberGenerator = new Random();

        int loopCount = Math.min(orders.length, Math.min(recipes.length, ingredients.size()));

        for (int i = 0; i < loopCount; i++) {
            float randomPrice = randomNumberGenerator.nextFloat();
            int randomQuantity = randomNumberGenerator.nextInt(40000);
            boolean randomIsDone = (i % 2 == 0);
            orders[i] = new Order(people[i].getName(), people[i].getPhone(), people[i].getAddress(),
                    new GenericString(recipes[i]), new RecipeIngredientList(ingredients.get(i)),
                    new Price(String.format("%.2f", randomPrice)),
                    new Deadline(getSampleDateTimeString(i + 1)),
                    new Quantity(Integer.toString(randomQuantity)),
                    new IsDone(randomIsDone ? "yes" : "no"));
        }

        return orders;
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

        return sampleAb;
    }

    private static String getSampleDateTimeString(int dayOffset) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime dateTimeWithOffset = currentDateTime.plusDays(dayOffset);
        return dateTimeWithOffset.format(Deadline.INPUT_DATETIME_FORMATTER);
    }
}
