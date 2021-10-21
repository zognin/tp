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

import java.time.Month;
import java.time.YearMonth;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Price;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;

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
    public void getRevenueForPastTwelveMonths_emptyOrderList_returnsZeroRevenueForPastTwelveMonths() {
        List<Entry<YearMonth, Double>> revenueList = uniqueOrderList.getRevenueForPastTwelveMonths();
        assertEquals(12, revenueList.size());
        revenueList.forEach(item -> assertEquals(0, item.getValue()));
    }

    @Test
    public void getRevenueForPastTwelveMonths_nonEmptyOrderList_returnsCorrectRevenueForPastTwelveMonths() {
        List<Order> orderList = new ArrayList<>(List.of(ORDER_FOR_ALICE, ORDER_FOR_AMY, ORDER_FOR_BOB, ORDER_FOR_BENSON,
                ORDER_FOR_CARL, ORDER_FOR_DANIEL, ORDER_FOR_ELLE, ORDER_FOR_FIONA, ORDER_FOR_GEORGE, ORDER_FOR_HOON,
                ORDER_FOR_IDA));

        YearMonth currentYearMonth = YearMonth.now();

        for (int i = 0; i < 12; i++) {
            int year = currentYearMonth.getYear();
            Month month = currentYearMonth.getMonth();

            for (Order order : orderList) {
                Order o = new OrderBuilder(order)
                        .withPrice(new Price(String.valueOf(i + 1)))
                        .withDeadline(new Deadline(
                                String.format("%s-%s-%s 1900", i + 10,
                                        month.getValue() < 10
                                                ? "0" + month.getValue()
                                                : month.getValue(),
                                        year)))
                        .withCompletionStatus(new CompletionStatus(true))
                        .build();

                uniqueOrderList.add(o);
            }

            currentYearMonth = currentYearMonth.minusMonths(1);
        }

        List<Entry<YearMonth, Double>> revenueList = uniqueOrderList.getRevenueForPastTwelveMonths();
        for (int i = 0; i < revenueList.size(); i++) {
            assertEquals((i + 1) * 11, revenueList.get(revenueList.size() - 1 - i).getValue());
        }
    }

    @Test
    public void getTopTenOrderClients_listContainsOrdersForElevenClients_returnsTopTenClients() {
        List<Order> orderList = new ArrayList<>(List.of(ORDER_FOR_ALICE, ORDER_FOR_AMY, ORDER_FOR_BOB, ORDER_FOR_BENSON,
                ORDER_FOR_CARL, ORDER_FOR_DANIEL, ORDER_FOR_ELLE, ORDER_FOR_FIONA, ORDER_FOR_GEORGE, ORDER_FOR_HOON,
                ORDER_FOR_IDA));

        for (int i = 0; i < orderList.size(); i++) {
            for (int j = 0; j < i + 1; j++) {
                uniqueOrderList.add(
                        new OrderBuilder(orderList.get(i)).withPrice(new Price(Integer.toString(i))).build()
                );
            }
        }
        List<Entry<OrderClient, Long>> topTenClients = uniqueOrderList.getTopTenOrderClients();

        orderList.remove(ORDER_FOR_ALICE);
        List<Entry<OrderClient, Long>> expectedTopTenClients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order currOrder = orderList.get(i);
            expectedTopTenClients.add(new AbstractMap.SimpleEntry<>(
                    new OrderClient(currOrder.getClientName(), currOrder.getClientPhone()), (long) i + 2));
        }
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
