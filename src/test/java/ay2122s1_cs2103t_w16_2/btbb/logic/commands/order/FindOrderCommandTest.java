package ay2122s1_cs2103t_w16_2.btbb.logic.commands.order;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_ORDERS_LISTED_OVERVIEW;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccessWithTabChange;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_NAME_CAROL_DAVID_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.CLIENT_PHONE_9110_3216_PREDICATE;
import static ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollectionTest.addPredicates;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.PredicateUtil.makeLocalDateInListPredicate;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.PredicateUtil.makeStringContainsKeywordsPredicate;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.ORDER_FOR_FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalOrders.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.predicate.PredicateCollection;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiTab;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderCommand}.
 */
public class FindOrderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PredicateCollection<Order> predicateCollection = new PredicateCollection<Order>();
        addPredicates(predicateCollection, List.of(CLIENT_NAME_CAROL_DAVID_PREDICATE,
                CLIENT_PHONE_9110_3216_PREDICATE, CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE));

        PredicateCollection<Order> diffOrderPredicateCollection = new PredicateCollection<>();
        addPredicates(diffOrderPredicateCollection, List.of(CLIENT_PHONE_9110_3216_PREDICATE,
                CLIENT_NAME_CAROL_DAVID_PREDICATE, CLIENT_ADDRESS_EUNOS_BISHAN_PREDICATE));

        PredicateCollection<Order> diffAmountPredicateCollection = new PredicateCollection<>();
        addPredicates(diffAmountPredicateCollection, List.of(CLIENT_NAME_CAROL_DAVID_PREDICATE,
                CLIENT_PHONE_9110_3216_PREDICATE));
        FindOrderCommand findCommand = new FindOrderCommand(predicateCollection);
        FindOrderCommand diffOrderFindCommand = new FindOrderCommand(diffOrderPredicateCollection);
        FindOrderCommand diffAmountFindCommand = new FindOrderCommand(diffAmountPredicateCollection);

        // same object -> returns true
        assertTrue(findCommand.equals(findCommand));

        // same values -> returns true
        FindOrderCommand findCommandCopy = new FindOrderCommand(predicateCollection);
        assertTrue(findCommand.equals(findCommandCopy));

        // different types -> returns false
        assertFalse(findCommand.equals(1));

        // null -> returns false
        assertFalse(findCommand.equals(null));

        // different order of predicates -> returns true
        assertTrue(findCommand.equals(diffOrderFindCommand));

        // different amount of predicates -> returns false
        assertFalse(findCommand.equals(diffAmountFindCommand));
    }

    @Test
    public void execute_multipleKeywords_multipleOrdersFound() {
        String expectedMessage = String.format(MESSAGE_ORDERS_LISTED_OVERVIEW, 3);
        PredicateCollection<Order> predicateCollection = new PredicateCollection<>();
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate("Kurz Elle Kunz", Order::getClientName)
        );
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate("9535 9482 2427", Order::getClientPhone)
        );
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate("wall michegan tokyo", Order::getClientAddress)
        );
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate("prata hokkien kaya", Order::getRecipeName)
        );
        predicateCollection.addPredicate(
                makeLocalDateInListPredicate("14-12-2021 16-12-2021 17-12-2021", Order::getDeadlineDate)
        );
        predicateCollection.addPredicate(
                makeStringContainsKeywordsPredicate("yes no", Order::getCompletionStatus)
        );
        FindOrderCommand command = new FindOrderCommand(predicateCollection);
        expectedModel.updateFilteredOrderList(predicateCollection);
        assertCommandSuccessWithTabChange(command, model, expectedMessage, expectedModel, UiTab.HOME);
        assertEquals(Arrays.asList(ORDER_FOR_CARL, ORDER_FOR_ELLE, ORDER_FOR_FIONA), model.getFilteredOrderList());
    }
}
