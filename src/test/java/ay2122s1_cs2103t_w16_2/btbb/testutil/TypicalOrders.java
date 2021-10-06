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

import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

/**
 * A utility class containing a list of {@code Order} objects to be used in tests.
 */
public class TypicalOrders {
    public static final Order ORDER_FOR_ALICE =
            new OrderBuilder().withClientPhone(ALICE.getPhone()).withClientName(ALICE.getName())
                    .withClientAddress(ALICE.getAddress()).build();
    public static final Order ORDER_FOR_BENSON =
            new OrderBuilder().withClientPhone(BENSON.getPhone()).withClientName(BENSON.getName())
                    .withClientAddress(BENSON.getAddress()).build();
    public static final Order ORDER_FOR_CARL =
            new OrderBuilder().withClientPhone(CARL.getPhone()).withClientName(CARL.getName())
                    .withClientAddress(CARL.getAddress()).build();
    public static final Order ORDER_FOR_DANIEL =
            new OrderBuilder().withClientPhone(DANIEL.getPhone()).withClientName(DANIEL.getName())
                    .withClientAddress(DANIEL.getAddress()).build();
    public static final Order ORDER_FOR_ELLE =
            new OrderBuilder().withClientPhone(ELLE.getPhone()).withClientName(ELLE.getName())
                    .withClientAddress(ELLE.getAddress()).build();
    public static final Order ORDER_FOR_FIONA =
            new OrderBuilder().withClientPhone(FIONA.getPhone()).withClientName(FIONA.getName())
                    .withClientAddress(FIONA.getAddress()).build();
    public static final Order ORDER_FOR_GEORGE =
            new OrderBuilder().withClientPhone(GEORGE.getPhone()).withClientName(GEORGE.getName())
                    .withClientAddress(GEORGE.getAddress()).build();

    // Manually added - Order's details found in {@code TypicalClients} and {@code CommandTestUtil}
    public static final Order ORDER_FOR_AMY =
            new OrderBuilder().withClientPhone(AMY.getPhone()).withClientName(AMY.getName())
                    .withClientAddress(AMY.getAddress()).build();
    public static final Order ORDER_FOR_BOB =
            new OrderBuilder().withClientPhone(BOB.getPhone()).withClientName(BOB.getName())
                    .withClientAddress(BOB.getAddress()).build();
}
