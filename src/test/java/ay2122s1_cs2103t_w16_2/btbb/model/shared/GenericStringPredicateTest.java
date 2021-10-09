package ay2122s1_cs2103t_w16_2.btbb.model.shared;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.GenericDummy;

public class GenericStringPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        GenericStringPredicate<GenericDummy> firstPredicate = new GenericStringPredicate<>(GenericDummy::getName,
                firstPredicateKeywordList);
        GenericStringPredicate<GenericDummy> secondPredicate = new GenericStringPredicate<>(GenericDummy::getName,
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenericStringPredicate<GenericDummy> firstPredicateCopy = new GenericStringPredicate<>(GenericDummy::getName,
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_fieldContainsKeywords_returnsTrue() {
        GenericStringPredicate<GenericDummy> predicate =
                new GenericStringPredicate<>(GenericDummy::getName, List.of("Alice"));
        GenericDummy randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));

        // One name keyword
        assertTrue(predicate.test(randomDummy));

        // Multiple name keywords
        predicate = new GenericStringPredicate<>(GenericDummy::getName, List.of("Alice", "Bob"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Only one matching name keyword
        predicate = new GenericStringPredicate<>(GenericDummy::getName, List.of("Alice", "Bob"));
        randomDummy = new GenericDummy(new GenericString("Alice Carol"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Mixed-case name keywords
        predicate = new GenericStringPredicate<>(GenericDummy::getName, List.of("aLIce", "bOB"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // One address keyword
        predicate = new GenericStringPredicate<>(GenericDummy::getAddress, List.of("Yishun"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Multiple address keywords
        predicate = new GenericStringPredicate<>(GenericDummy::getAddress, List.of("Yishun", "Geylang"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun Geylang"));
        assertTrue(predicate.test(randomDummy));

        // Only one matching address keyword
        predicate = new GenericStringPredicate<>(GenericDummy::getAddress, List.of("Yishun", "Serangoon"));
        randomDummy = new GenericDummy(new GenericString("Alice Carol"), new GenericString("Yishun"));
        assertTrue(predicate.test(randomDummy));

        // Mixed-case address keywords
        predicate = new GenericStringPredicate<>(GenericDummy::getAddress, List.of("YiSHun", "SeRAngOOn"));
        randomDummy = new GenericDummy(new GenericString("Alice Bob"), new GenericString("Yishun Serangoon"));
        assertTrue(predicate.test(randomDummy));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero name keywords
        GenericStringPredicate<GenericDummy> predicate =
                new GenericStringPredicate<>(GenericDummy::getName, Collections.emptyList());
        GenericDummy randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Non-matching name keyword
        predicate = new GenericStringPredicate<>(GenericDummy::getName, Collections.singletonList("Carol"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Zero address keywords
        predicate = new GenericStringPredicate<>(GenericDummy::getAddress, Collections.emptyList());
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Non-matching address keyword
        predicate = new GenericStringPredicate<>(GenericDummy::getAddress, Collections.singletonList("Geylang"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Keyword matches different field
        predicate = new GenericStringPredicate<>(GenericDummy::getName, Collections.singletonList("Yishun"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));

        // Keyword matches different field
        predicate = new GenericStringPredicate<>(GenericDummy::getAddress, Collections.singletonList("Alice"));
        randomDummy = new GenericDummy(new GenericString("Alice"), new GenericString("Yishun"));
        assertFalse(predicate.test(randomDummy));
    }
}
