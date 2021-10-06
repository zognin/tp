package ay2122s1_cs2103t_w16_2.btbb.model.ingredient;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

public class IngredientName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String ingredientName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public IngredientName(String name) {
        requireNonNull(name);
        checkArgument(isValidIngredientName(name), MESSAGE_CONSTRAINTS);
        ingredientName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidIngredientName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return ingredientName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientName // instanceof handles nulls
                && ingredientName.equals(((IngredientName) other).ingredientName)); // state check
    }

    @Override
    public int hashCode() {
        return ingredientName.hashCode();
    }
}
