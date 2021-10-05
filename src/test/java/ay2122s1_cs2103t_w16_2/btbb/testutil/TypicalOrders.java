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
    public static final Order ORDER_BY_ALICE = new OrderBuilder().withPhone(ALICE.getPhone()).build();
    public static final Order ORDER_BY_BENSON = new OrderBuilder().withPhone(BENSON.getPhone()).build();
    public static final Order ORDER_BY_CARL = new OrderBuilder().withPhone(CARL.getPhone()).build();
    public static final Order ORDER_BY_DANIEL = new OrderBuilder().withPhone(DANIEL.getPhone()).build();
    public static final Order ORDER_BY_ELLE = new OrderBuilder().withPhone(ELLE.getPhone()).build();
    public static final Order ORDER_BY_FIONA = new OrderBuilder().withPhone(FIONA.getPhone()).build();
    public static final Order ORDER_BY_GEORGE = new OrderBuilder().withPhone(GEORGE.getPhone()).build();

    // Manually added - Order's details found in {@code TypicalClients} and {@code CommandTestUtil}
    public static final Order ORDER_BY_AMY = new OrderBuilder().withPhone(AMY.getPhone()).build();
    public static final Order ORDER_BY_BOB = new OrderBuilder().withPhone(BOB.getPhone()).build();
}
