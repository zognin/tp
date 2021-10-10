package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.GenericStub;


public class StringContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        StringContainsKeywordsPredicate<GenericStub> firstPredicate =
                new StringContainsKeywordsPredicate<>(GenericStub::getName, firstPredicateKeywordList);
        StringContainsKeywordsPredicate<GenericStub> secondPredicate =
                new StringContainsKeywordsPredicate<>(GenericStub::getName, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StringContainsKeywordsPredicate<GenericStub> firstPredicateCopy =
                new StringContainsKeywordsPredicate<>(GenericStub::getName, firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_fieldContainsKeywords_returnsTrue() {
        StringContainsKeywordsPredicate<GenericStub> predicate =
                new StringContainsKeywordsPredicate<>(GenericStub::getName, List.of("Alice"));
        GenericStub genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));

        // One name keyword
        assertTrue(predicate.test(genericStub));

        // Multiple name keywords
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getName, List.of("Alice", "Bob"));
        genericStub = new GenericStub(new GenericString("Alice Bob"), new GenericString("Yishun"));
        assertTrue(predicate.test(genericStub));

        // Only one matching name keyword
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getName, List.of("Alice", "Bob"));
        genericStub = new GenericStub(new GenericString("Alice Carol"), new GenericString("Yishun"));
        assertTrue(predicate.test(genericStub));

        // Mixed-case name keywords
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getName, List.of("aLIce", "bOB"));
        genericStub = new GenericStub(new GenericString("Alice Bob"), new GenericString("Yishun"));
        assertTrue(predicate.test(genericStub));

        // One address keyword
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getAddress, List.of("Yishun"));
        genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));
        assertTrue(predicate.test(genericStub));

        // Multiple address keywords
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getAddress, List.of("Yishun", "Geylang"));
        genericStub = new GenericStub(new GenericString("Alice Bob"), new GenericString("Yishun Geylang"));
        assertTrue(predicate.test(genericStub));

        // Only one matching address keyword
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getAddress, List.of("Yishun", "Serangoon"));
        genericStub = new GenericStub(new GenericString("Alice Carol"), new GenericString("Yishun"));
        assertTrue(predicate.test(genericStub));

        // Mixed-case address keywords
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getAddress, List.of("YiSHun", "SeRAngOOn"));
        genericStub = new GenericStub(new GenericString("Alice Bob"), new GenericString("Yishun Serangoon"));
        assertTrue(predicate.test(genericStub));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero name keywords
        StringContainsKeywordsPredicate<GenericStub> predicate =
                new StringContainsKeywordsPredicate<>(GenericStub::getName, Collections.emptyList());
        GenericStub genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(genericStub));

        // Non-matching name keyword
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getName, Collections.singletonList("Carol"));
        genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(genericStub));

        // Zero address keywords
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getAddress, Collections.emptyList());
        genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(genericStub));

        // Non-matching address keyword
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getAddress,
                Collections.singletonList("Geylang"));
        genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(genericStub));

        // Keyword matches different field
        predicate = new StringContainsKeywordsPredicate<>(GenericStub::getName, Collections.singletonList("Yishun"));
        genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(genericStub));

        // Keyword matches different field
        predicate = new StringContainsKeywordsPredicate<>(
                GenericStub::getAddress, Collections.singletonList("Alice")
        );
        genericStub = new GenericStub(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(genericStub));
    }
}
