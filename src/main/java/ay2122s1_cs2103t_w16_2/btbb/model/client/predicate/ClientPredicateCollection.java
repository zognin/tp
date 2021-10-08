package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

/**
 * Tests that a {@code Client} matches all the predicates given.
 */
public class ClientPredicateCollection implements Predicate<Client> {
    private final List<Predicate<Client>> predicates = new ArrayList<>();

    private boolean isSamePredicates(List<Predicate<Client>> otherPredicates) {
        return predicates.containsAll(otherPredicates) && otherPredicates.containsAll(predicates);
    }

    /**
     * Adds a client predicate to the list of client predicates to test against.
     *
     * @param clientPredicate the predicate to add to the list.
     */
    public void addClientPredicate(Predicate<Client> clientPredicate) {
        predicates.add(clientPredicate);
    }

    /**
     * Checks if there are no predicates to test against.
     *
     * @return true if there are no predicates to test against
     */
    public boolean hasNoPredicate() {
        return predicates.isEmpty();
    }

    @Override
    public boolean test(Client client) {
        return predicates.stream()
                .map(predicate -> predicate.test(client))
                .reduce(Boolean.TRUE, Boolean::logicalAnd);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientPredicateCollection // instanceof handles nulls
                && isSamePredicates(((ClientPredicateCollection) other).predicates)); // state check
    }
}
