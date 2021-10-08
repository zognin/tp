package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;

public class PhoneContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PhoneContainsKeywordsPredicate firstPredicate =
                new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PhoneContainsKeywordsPredicate secondPredicate =
                new PhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy =
                new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PhoneContainsKeywordsPredicate predicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("9123"));
        assertTrue(predicate.test(new ClientBuilder().withPhone("91232467").build()));

        // Multiple keywords
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("9123", "2467"));
        assertTrue(predicate.test(new ClientBuilder().withPhone("91232467").build()));

        // Only one matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("9123", "3847"));
        assertTrue(predicate.test(new ClientBuilder().withPhone("91232467").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withPhone("91232467").build()));

        // Non-matching keyword
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("4536"));
        assertFalse(predicate.test(new ClientBuilder().withPhone("91232467").build()));

        // Keywords match name, email and address, but does not match phone
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("Alice", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withPhone("91232467")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
