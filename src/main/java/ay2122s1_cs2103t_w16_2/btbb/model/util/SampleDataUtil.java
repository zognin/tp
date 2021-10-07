package ay2122s1_cs2103t_w16_2.btbb.model.util;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Email;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.IngredientName;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Unit;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[] {
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"))
        };
    }

    public static Order[] getSampleOrders() {
        Client[] people = getSampleClients();
        Order[] orders = new Order[people.length];

        for (int i = 0; i < orders.length; i++) {
            orders[i] = new Order(people[i].getName(), people[i].getPhone(), people[i].getAddress());
        }

        return orders;
    }

    public static Ingredient[] getSampleIngredients() {
        return new Ingredient[] {
            new Ingredient(new IngredientName("Almond"), new Quantity("3"), new Unit("bags")),
            new Ingredient(new IngredientName("Bamboo"), new Quantity("17"), new Unit("shoots")),
            new Ingredient(new IngredientName("Corn"), new Quantity("30"), new Unit("whole")),
            new Ingredient(new IngredientName("Duck Eggs"), new Quantity("36"), new Unit("whole")),
            new Ingredient(new IngredientName("Edamame"), new Quantity("1"), new Unit("bag")),
            new Ingredient(new IngredientName("Fig"), new Quantity("20"), new Unit("whole")),
            new Ingredient(new IngredientName("Garlic"), new Quantity("30"), new Unit("whole")),
            new Ingredient(new IngredientName("Ham"), new Quantity("10"), new Unit("packs"))
        };
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
}
