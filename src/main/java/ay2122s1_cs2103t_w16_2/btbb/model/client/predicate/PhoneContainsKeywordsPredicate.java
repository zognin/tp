package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import java.util.List;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

public class PhoneContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> client.getPhone().toString().contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }
}
