package ay2122s1_cs2103t_w16_2.btbb.model.client.predicate;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

/**
 * Tests that a {@code Client}'s {@code Address} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public AddressContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        String lowercaseAddress = client.getAddress().toString().toLowerCase(Locale.ROOT);
        return keywords.stream()
                .anyMatch(keyword -> lowercaseAddress.contains(keyword.toLowerCase(Locale.ROOT)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AddressContainsKeywordsPredicate) other).keywords)); // state check
    }
}
