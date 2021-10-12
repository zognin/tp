package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_INGREDIENT_NAME_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_QUANTITY_BEEF;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_APPLE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_UNIT_BEEF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;

public class TypicalIngredients {
    public static final Ingredient AVOCADO = new IngredientBuilder().withIngredientName("Avocado")
            .withQuantity("5").withUnit("whole").build();
    public static final Ingredient BREAD = new IngredientBuilder().withIngredientName("Bread")
            .withQuantity("1000").withUnit("slice").build();
    public static final Ingredient BUTTER = new IngredientBuilder().withIngredientName("Butter")
            .withQuantity("550").withUnit("grams").build();
    public static final Ingredient CHICKEN = new IngredientBuilder().withIngredientName("Chicken")
            .withQuantity("10").withUnit("whole").build();
    public static final Ingredient DARK_CHOCOLATE = new IngredientBuilder().withIngredientName("Dark Chocolate")
            .withQuantity("300").withUnit("grams").build();
    public static final Ingredient EGG = new IngredientBuilder().withIngredientName("Egg")
            .withQuantity("2").withUnit("dozen").build();
    public static final Ingredient FISH = new IngredientBuilder().withIngredientName("Fish")
            .withQuantity("20").withUnit("whole").build();
    public static final Ingredient FLOUR = new IngredientBuilder().withIngredientName("Flour")
            .withQuantity("4").withUnit("kg").build();
    public static final Ingredient GINGER = new IngredientBuilder().withIngredientName("Ginger")
            .withQuantity("8").withUnit("whole").build();
    public static final Ingredient KAYA = new IngredientBuilder().withIngredientName("Kaya")
            .withQuantity("1000").withUnit("grams").build();
    public static final Ingredient NOODLES = new IngredientBuilder().withIngredientName("Noodles")
            .withQuantity("4000").withUnit("grams").build();
    public static final Ingredient PRAWN = new IngredientBuilder().withIngredientName("Prawn")
            .withQuantity("200").withUnit("whole").build();
    public static final Ingredient RICE = new IngredientBuilder().withIngredientName("Rice")
            .withQuantity("5000").withUnit("grams").build();


    public static final Ingredient APPLE = new IngredientBuilder().withIngredientName(VALID_INGREDIENT_NAME_APPLE)
            .withQuantity(VALID_QUANTITY_APPLE).withUnit(VALID_UNIT_APPLE).build();
    public static final Ingredient BEEF = new IngredientBuilder().withIngredientName(VALID_INGREDIENT_NAME_BEEF)
            .withQuantity(VALID_QUANTITY_BEEF).withUnit(VALID_UNIT_BEEF).build();


    private TypicalIngredients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical ingredients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Ingredient ingredient : getTypicalIngredients()) {
            ab.addIngredient(ingredient);
        }
        return ab;
    }

    public static List<Ingredient> getTypicalIngredients() {
        return new ArrayList<>(Arrays.asList(AVOCADO, BREAD, BUTTER, CHICKEN, DARK_CHOCOLATE, EGG, FISH, FLOUR,
                GINGER, KAYA, NOODLES, PRAWN, RICE));
    }
}
