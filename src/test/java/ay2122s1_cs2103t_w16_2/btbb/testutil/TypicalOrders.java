package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ORDER_FOR_ALICE =
            new OrderBuilder().withClientName(ALICE.getName()).withClientPhone(ALICE.getPhone())
                    .withClientAddress(ALICE.getAddress()).build();
    public static final Order ORDER_FOR_BENSON =
            new OrderBuilder().withClientName(BENSON.getName()).withClientPhone(BENSON.getPhone())
                    .withClientAddress(BENSON.getAddress()).build();
    public static final Order ORDER_FOR_CARL =
            new OrderBuilder().withClientName(CARL.getName()).withClientPhone(CARL.getPhone())
                    .withClientAddress(CARL.getAddress()).build();
    public static final Order ORDER_FOR_DANIEL =
            new OrderBuilder().withClientName(DANIEL.getName()).withClientPhone(DANIEL.getPhone())
                    .withClientAddress(DANIEL.getAddress()).build();
    public static final Order ORDER_FOR_ELLE =
            new OrderBuilder().withClientName(ELLE.getName()).withClientPhone(ELLE.getPhone())
                    .withClientAddress(ELLE.getAddress()).build();
    public static final Order ORDER_FOR_FIONA =
            new OrderBuilder().withClientName(FIONA.getName()).withClientPhone(FIONA.getPhone())
                    .withClientAddress(FIONA.getAddress()).build();
    public static final Order ORDER_FOR_GEORGE =
            new OrderBuilder().withClientName(GEORGE.getName()).withClientPhone(GEORGE.getPhone())
                    .withClientAddress(GEORGE.getAddress()).build();

    // Manually added - Order's details found in {@code TypicalClients} and {@code CommandTestUtil}
    public static final Order ORDER_FOR_AMY =
            new OrderBuilder().withClientName(AMY.getName()).withClientPhone(AMY.getPhone())
                    .withClientAddress(AMY.getAddress()).build();
    public static final Order ORDER_FOR_BOB =
            new OrderBuilder().withClientName(BOB.getName()).withClientPhone(BOB.getPhone())
                    .withClientAddress(BOB.getAddress()).build();
    /**
     * Returns an {@code AddressBook} with all the typical orders.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }

    public static List<Order> getTypicalOrders() {
        return new ArrayList<>(Arrays.asList(ORDER_FOR_ALICE, ORDER_FOR_BENSON, ORDER_FOR_CARL, ORDER_FOR_DANIEL,
                ORDER_FOR_ELLE, ORDER_FOR_FIONA, ORDER_FOR_GEORGE));
    }
}
