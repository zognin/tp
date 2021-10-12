package ay2122s1_cs2103t_w16_2.btbb.logic.parser.util;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.StringUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Email;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_KEYWORD = "Keywords for all provided prefixes should not be empty.";

    // Client-level parsers:

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    // Order-level parsers:

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param deadline String input to parse.
     * @return Deadline object.
     * @throws ParseException If the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     * For internal use there is a wider definition of a valid deadline.
     *
     * @param deadline String input to parse.
     * @return Deadline object.
     * @throws ParseException If the given {@code deadline} is invalid.
     */
    public static Deadline parseInternalDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Deadline.isValidInternalDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_INTERNAL_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline);
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param price String input to parse.
     * @return Price object.
     * @throws ParseException If the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(trimmedPrice)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
        return new Price(trimmedPrice);
    }

    /**
     * Parses a {@code String recipeIngredients} into a {@code RecipeIngredientList}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param recipeIngredients String input to parse.
     * @return RecipeIngredientList object.
     * @throws ParseException If the given {@code recipeIngredients} is invalid.
     */
    public static RecipeIngredientList parseRecipeIngredients(String recipeIngredients) throws ParseException {
        requireNonNull(recipeIngredients);
        String trimmedRecipeIngredients = recipeIngredients.trim();
        List<Ingredient> ingredients = parseRecipeIngredientsToList(trimmedRecipeIngredients);
        if (!RecipeIngredientList.isValidRecipeIngredientList(ingredients)) {
            throw new ParseException(RecipeIngredientList.MESSAGE_CONSTRAINTS);
        }
        return new RecipeIngredientList(ingredients);
    }

    /**
     * Parses the ingredient list to a {@code List<Ingredient>}.
     *
     * @param ingredientList The ingredient list to parse.
     * @return A list of ingredients.
     */
    private static List<Ingredient> parseRecipeIngredientsToList(String ingredientList) throws ParseException {
        requireNonNull(ingredientList);
        List<Ingredient> listOfIngredients = new ArrayList<>();

        String[] ingredientListArray = ingredientList.split(",");
        for (String individualIngredient : ingredientListArray) {
            Ingredient ingredient = parseRecipeIngredient(individualIngredient.trim());
            listOfIngredients.add(ingredient);
        }

        return listOfIngredients;
    }

    /**
     * Parses the individual ingredient to a {@code Ingredient}.
     *
     * @param individualIngredient The ingredient to parse.
     * @return An {@code Optional<Ingredient>} object.
     */
    private static Ingredient parseRecipeIngredient(String individualIngredient) throws ParseException {
        String[] individualIngredientArray = individualIngredient.split("-", 3);

        if (individualIngredientArray.length != 3) {
            throw new ParseException(RecipeIngredientList.MESSAGE_CONSTRAINTS);
        }

        String recipeName = individualIngredientArray[0];
        String quantity = individualIngredientArray[1];
        String unit = individualIngredientArray[2];

        if (!Ingredient.isValidIngredient(recipeName, quantity, unit)) {
            throw new ParseException(RecipeIngredientList.MESSAGE_CONSTRAINTS);
        }

        return new Ingredient(new GenericString(recipeName), new Quantity(quantity), new GenericString(unit));
    }

    // Shared-level parsers:

    /**
     * Parses a {@code String genericString} into a {@code GenericString}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param genericString String input to parse.
     * @param attributeName name of attribute that the generic String represents (Unit, Name).
     * @return GenericString object.
     * @throws ParseException if the given {@code genericString} is invalid.
     */
    public static GenericString parseGenericString(String genericString, String attributeName) throws ParseException {
        requireNonNull(genericString);
        String trimmedGenericString = genericString.trim();
        if (!GenericString.isValidGenericString(trimmedGenericString)) {
            throw new ParseException(GenericString.getMessageConstraints(attributeName));
        }
        return new GenericString(trimmedGenericString);
    }

    /**
     * Parses a {code String keywords} into a {@code List<String>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param keywords Keywords to be parsed.
     * @return List of strings containing all the keywords.
     * @throws ParseException if the given {@code keywords} is invalid.
     */
    public static List<String> parseKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_KEYWORD);
        }
        return List.of(trimmedKeywords.split("\\s+"));
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * For internal use there is a wider definition of a valid quantity.
     *
     * @param quantity String input to parse.
     * @return Quantity object.
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseInternalQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidInternalQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_INTERNAL_CONSTRAINTS);
        }
        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param quantity String input to parse.
     * @return Quantity object.
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);
        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }
        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String quantities} into a {@code List<Quantity>}.
     *
     * @param quantities String input to be parsed into a list of quantities.
     * @return List of quantities.
     * @throws ParseException if the given {@code quantities} is invalid.
     */
    public static List<Quantity> parseQuantities(String quantities) throws ParseException {
        ArrayList<Quantity> quantityList = new ArrayList<>();
        for (String stringKeyword : parseKeywords(quantities)) {
            quantityList.add(parseInternalQuantity(stringKeyword));
        }
        return quantityList;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
