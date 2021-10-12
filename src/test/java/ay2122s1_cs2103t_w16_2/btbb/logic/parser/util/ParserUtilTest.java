package ay2122s1_cs2103t_w16_2.btbb.logic.parser.util;

import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil.MESSAGE_INVALID_INDEX;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

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

public class ParserUtilTest {
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";

    private static final String INVALID_DEADLINE = "12-12-2019";
    private static final String INVALID_PRICE = "$4.00";
    private static final String INVALID_RECIPE_INGREDIENTS = "Rice-one-cup";

    private static final String INVALID_INTERNAL_QUANTITY = "-3";
    private static final String INVALID_QUANTITY = "-3";

    private static final String INVALID_GENERIC_STRING = "R@chel";
    private static final String INVALID_KEYWORD = "";

    private static final String VALID_PHONE = "123456";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";

    private static final String VALID_DEADLINE = "12-12-2025 1500";
    private static final String VALID_PRICE = "4.00";
    private static final String VALID_RECIPE_INGREDIENTS = "Rice-1-cup, Chicken-1-whole";

    private static final String VALID_QUANTITY_1 = "30";
    private static final String VALID_QUANTITY_2 = "10";

    private static final String VALID_GENERIC_STRING = "Rachel Walker";
    private static final String VALID_KEYWORD_1 = "Hello";
    private static final String VALID_KEYWORD_2 = "Goodbye";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    // Client parsers

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone(null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress(null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail(null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    // Order parsers

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline(null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
    }

    @Test
    public void parsePrice_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePrice(null));
    }

    @Test
    public void parsePrice_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(INVALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithoutWhitespace_returnsPrice() throws Exception {
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(VALID_PRICE));
    }

    @Test
    public void parsePrice_validValueWithWhitespace_returnsPrice() throws Exception {
        String priceWithWhitespace = WHITESPACE + VALID_PRICE + WHITESPACE;
        Price expectedPrice = new Price(VALID_PRICE);
        assertEquals(expectedPrice, ParserUtil.parsePrice(priceWithWhitespace));
    }

    @Test
    public void parseRecipeIngredients_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRecipeIngredients(null));
    }

    @Test
    public void parseRecipeIngredients_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRecipeIngredients(INVALID_RECIPE_INGREDIENTS));
    }

    @Test
    public void parseRecipeIngredients_validValueWithoutWhitespace_returnsRecipeIngredients() throws Exception {
        Ingredient rice = new Ingredient(new GenericString("Rice"), new Quantity("1"), new GenericString("cup"));
        Ingredient chicken = new Ingredient(
                new GenericString("Chicken"), new Quantity("1"), new GenericString("whole"));
        RecipeIngredientList expectedList = new RecipeIngredientList(List.of(rice, chicken));
        assertEquals(expectedList, ParserUtil.parseRecipeIngredients(VALID_RECIPE_INGREDIENTS));
    }

    @Test
    public void parseRecipeIngredients_validValueWithWhitespace_returnsRecipeIngredients() throws Exception {
        Ingredient rice = new Ingredient(new GenericString("Rice"), new Quantity("1"), new GenericString("cup"));
        Ingredient chicken = new Ingredient(
                new GenericString("Chicken"), new Quantity("1"), new GenericString("whole"));
        String listWithWhitespace = WHITESPACE + VALID_RECIPE_INGREDIENTS + WHITESPACE;
        RecipeIngredientList expectedList = new RecipeIngredientList(List.of(rice, chicken));
        assertEquals(expectedList, ParserUtil.parseRecipeIngredients(listWithWhitespace));
    }

    // Shared parsers

    @Test
    public void parseGenericString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseGenericString(null,
                "Name"));
    }

    @Test
    public void parseGenericString_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseGenericString(INVALID_GENERIC_STRING,
                "Name"));
    }

    @Test
    public void parseGenericString_validValueWithoutWhitespace_returnsGenericString() throws Exception {
        GenericString expectedName = new GenericString(VALID_GENERIC_STRING);
        assertEquals(expectedName, ParserUtil.parseGenericString(VALID_GENERIC_STRING, "Name"));
    }

    @Test
    public void parseGenericString_validValueWithWhitespace_returnsTrimmedGenericString() throws Exception {
        String genericStringWithWhitespace = WHITESPACE + VALID_GENERIC_STRING + WHITESPACE;
        GenericString expectedGenericString = new GenericString(VALID_GENERIC_STRING);
        assertEquals(expectedGenericString, ParserUtil.parseGenericString(genericStringWithWhitespace,
                "Name"));
    }

    @Test
    public void parseKeywords_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseKeywords(null));
    }

    @Test
    public void parseKeywords_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseKeywords(INVALID_KEYWORD));
    }

    @Test
    public void parseKeywords_validValues_returnsListOfStrings() throws Exception {
        List<String> expectedList = List.of(VALID_KEYWORD_1, VALID_KEYWORD_2);
        assertEquals(expectedList, ParserUtil.parseKeywords(VALID_KEYWORD_1 + " " + VALID_KEYWORD_2));
    }

    @Test
    public void parseInternalQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseInternalQuantity(null));
    }

    @Test
    public void parseInternalQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseInternalQuantity(INVALID_INTERNAL_QUANTITY));
    }

    @Test
    public void parseInternalQuantity_validValueWithoutWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY_1);
        assertEquals(expectedQuantity, ParserUtil.parseInternalQuantity(VALID_QUANTITY_1));
    }

    @Test
    public void parseInternalQuantity_validValueWithWhitespace_returnsTrimmedQuantity() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_QUANTITY_1 + WHITESPACE;
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY_1);
        assertEquals(expectedQuantity, ParserUtil.parseInternalQuantity(quantityWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantity(null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_QUANTITY));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsQuantity() throws Exception {
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY_1);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(VALID_QUANTITY_1));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedQuantity() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_QUANTITY_1 + WHITESPACE;
        Quantity expectedQuantity = new Quantity(VALID_QUANTITY_1);
        assertEquals(expectedQuantity, ParserUtil.parseQuantity(quantityWithWhitespace));
    }

    @Test
    public void parseQuantities_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQuantities(null));
    }

    @Test
    public void parseQuantities_invalidValues_throwsParseException() {
        assertThrows(ParseException.class, () ->
                ParserUtil.parseQuantities(INVALID_INTERNAL_QUANTITY));
        assertThrows(ParseException.class, () ->
                ParserUtil.parseQuantities(VALID_QUANTITY_1 + " " + INVALID_INTERNAL_QUANTITY));
        assertThrows(ParseException.class, () ->
                ParserUtil.parseQuantities(INVALID_QUANTITY + " " + VALID_QUANTITY_2));
        assertThrows(ParseException.class, () ->
                ParserUtil.parseQuantities(VALID_GENERIC_STRING + " " + VALID_QUANTITY_2));
    }

    @Test
    public void parseQuantities_validValues_returnsListOfQuantities() throws Exception {
        List<Quantity> expectedList = List.of(new Quantity(VALID_QUANTITY_1), new Quantity(VALID_QUANTITY_2));
        assertEquals(expectedList, ParserUtil.parseQuantities(VALID_QUANTITY_1 + " " + VALID_QUANTITY_2));
    }
}
