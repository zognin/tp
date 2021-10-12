package ay2122s1_cs2103t_w16_2.btbb.model.order;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;

class UniqueOrderListTest {
    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();

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
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
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
    public void setOrder_validTargetAndEditedOrder_throwsNullPointerException() throws NotFoundException {
        uniqueOrderList.add(ORDER_FOR_ALICE);
        uniqueOrderList.setOrder(ORDER_FOR_ALICE, ORDER_FOR_BENSON);
        assertFalse(uniqueOrderList.contains(ORDER_FOR_ALICE));
        assertTrue(uniqueOrderList.contains(ORDER_FOR_BENSON));
    }
}
