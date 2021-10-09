package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.GenericDummy;

public class StringContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        StringContainsKeywordPredicate<GenericDummy> firstPredicate =
                new StringContainsKeywordPredicate<>(GenericDummy::getName, firstPredicateKeywordList);
        StringContainsKeywordPredicate<GenericDummy> secondPredicate =
                new StringContainsKeywordPredicate<>(GenericDummy::getName, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StringContainsKeywordPredicate<GenericDummy> firstPredicateCopy =
                new StringContainsKeywordPredicate<>(GenericDummy::getName, firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_fieldContainsKeywords_returnsTrue() {
        StringContainsKeywordPredicate<GenericDummy> predicate =
                new StringContainsKeywordPredicate<>(GenericDummy::getName, List.of("Alice"));
        GenericDummy randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));

        // One name keyword
        assertTrue(predicate.test(randomDummy));

        // Multiple name keywords
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getName, List.of("Alice", "Bob"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Only one matching name keyword
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getName, List.of("Alice", "Bob"));
        randomDummy = new GenericDummy(new GenericString("Alice Carol"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Mixed-case name keywords
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getName, List.of("aLIce", "bOB"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // One address keyword
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getAddress, List.of("Yishun"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Multiple address keywords
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getAddress, List.of("Yishun", "Geylang"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun Geylang"));
        assertTrue(predicate.test(randomDummy));

        // Only one matching address keyword
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getAddress, List.of("Yishun", "Serangoon"));
        randomDummy = new GenericDummy(new GenericString("Alice Carol"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Mixed-case address keywords
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getAddress, List.of("YiSHun", "SeRAngOOn"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun Serangoon"));
        assertTrue(predicate.test(randomDummy));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero name keywords
        StringContainsKeywordPredicate<GenericDummy> predicate =
                new StringContainsKeywordPredicate<>(GenericDummy::getName, Collections.emptyList());
        GenericDummy randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Non-matching name keyword
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getName, Collections.singletonList("Carol"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Zero address keywords
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getAddress, Collections.emptyList());
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Non-matching address keyword
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getAddress,
                Collections.singletonList("Geylang"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Keyword matches different field
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getName, Collections.singletonList("Yishun"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Keyword matches different field
        predicate = new StringContainsKeywordPredicate<>(GenericDummy::getAddress, Collections.singletonList("Alice"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));
    }
}
