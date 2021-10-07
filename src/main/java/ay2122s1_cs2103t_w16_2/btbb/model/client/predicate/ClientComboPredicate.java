package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

public class ClientComboPredicate implements Predicate<Client> {
    private final List<Predicate<Client>> predicates = new ArrayList<>();

    public void addClientPredicate(Predicate<Client> clientPredicate) {
        predicates.add(clientPredicate);
    }

    public boolean hasNoPredicate() {
        return predicates.isEmpty();
    }

    @Override
    public boolean test(Client client) {
        return predicates
                .stream()
                .map(predicate -> predicate.test(client))
                .reduce(Boolean.TRUE, Boolean::logicalAnd);
    }
}
