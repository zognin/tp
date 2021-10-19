package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_GEORGE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_HOON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_IDA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;
import javafx.util.Pair;

class UniqueOrderListTest {
    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
    }

    @Test
    public void contains_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(uniqueOrderList.contains(ORDER_FOR_ALICE));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        uniqueOrderList.add(ORDER_FOR_ALICE);
        assertTrue(uniqueOrderList.contains(ORDER_FOR_ALICE));
    }

    @Test
    public void getTopTenClients_listContainsOrdersForElevenClients_returnsTopTenClients() {
        List<Order> orderList = new ArrayList<>(List.of(ORDER_FOR_ALICE, ORDER_FOR_AMY, ORDER_FOR_BOB, ORDER_FOR_BENSON,
                ORDER_FOR_CARL, ORDER_FOR_DANIEL, ORDER_FOR_ELLE, ORDER_FOR_FIONA, ORDER_FOR_GEORGE, ORDER_FOR_HOON,
                ORDER_FOR_IDA));
        IntStream.range(1, orderList.size() + 1).forEach(i -> {
            IntStream.range(0, i).forEach(j -> uniqueOrderList.add(new OrderBuilder(orderList.get(i - 1))
                    .withPrice(new Price(Integer.toString(i)))
                    .build()));
        });
        List<Map.Entry<Pair<GenericString, Phone>, Integer>> topTenClients = uniqueOrderList.getTopTenClients();

        orderList.remove(ORDER_FOR_ALICE);
        List<Map.Entry<Pair<GenericString, Phone>, Integer>> expectedTopTenClients = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            Order currOrder = orderList.get(i);
            expectedTopTenClients.add(new AbstractMap.SimpleEntry<>(new Pair<>(currOrder.getClientName(),
                    currOrder.getClientPhone()), i + 2));
        });
        Collections.reverse(expectedTopTenClients);
        assertEquals(expectedTopTenClients, topTenClients);
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueOrderList.remove(ORDER_FOR_ALICE));
    }

    @Test
    public void remove_existingOrder_removesOrder() throws NotFoundException {
        uniqueOrderList.add(ORDER_FOR_ALICE);
        uniqueOrderList.remove(ORDER_FOR_ALICE);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders(null));
    }

    @Test
    public void setOrders_list_replacesOwnListWithProvidedList() {
        uniqueOrderList.add(ORDER_FOR_ALICE);
        List<Order> orderList = Collections.singletonList(ORDER_FOR_BENSON);
        uniqueOrderList.setOrders(orderList);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(ORDER_FOR_BENSON);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_nullTarget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(null, ORDER_FOR_AMY));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(ORDER_FOR_AMY, null));
    }

    @Test
    public void setOrder_invalidTarget_throwsNullPointerException() {
        assertThrows(NotFoundException.class, () -> uniqueOrderList.setOrder(ORDER_FOR_AMY, ORDER_FOR_ALICE));
    }

    @Test
    public void setOrder_validTargetAndEditedOrder_success() throws NotFoundException {
        uniqueOrderList.add(ORDER_FOR_ALICE);
        uniqueOrderList.setOrder(ORDER_FOR_ALICE, ORDER_FOR_BENSON);
        assertFalse(uniqueOrderList.contains(ORDER_FOR_ALICE));
        assertTrue(uniqueOrderList.contains(ORDER_FOR_BENSON));
    }
}
