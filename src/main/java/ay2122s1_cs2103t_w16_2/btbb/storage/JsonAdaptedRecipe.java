package ay2122s1_cs2103t_w16_2.btbb.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

public class JsonAdaptedRecipe {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recipe's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedIngredient> recipeIngredients = new ArrayList<>();
    private final String price;

    /**
     * Constructs a {@code JsonAdaptedRecipe} with the given recipe details.
     */
    @JsonCreator
    public JsonAdaptedRecipe(@JsonProperty("name") String name,
                             @JsonProperty("recipeIngredients") List<JsonAdaptedIngredient> recipeIngredients,
                             @JsonProperty("price") String price) {
        this.name = name;
        this.price = price;

        if (recipeIngredients != null) {
            this.recipeIngredients.addAll(recipeIngredients);
        }
    }

    /**
     * Converts a given {@code Recipe} into this class for Jackson use.
     *
     * @param source The recipe source to convert to this class.
     */
    public JsonAdaptedRecipe(Recipe source) {
        this.name = source.getName().toString();
        this.recipeIngredients.addAll(source.getRecipeIngredients().getIngredients()
                .stream().map(JsonAdaptedIngredient::new).collect(Collectors.toList()));
        this.price = source.getPrice().toString();
    }

    /**
     * Converts this Jackson-friendly adapted recipe object into the model's {@code Recipe} object.
     *
     * @return Recipe model type.
     * @throws IllegalValueException If there were any data constraints violated in the adapted recipe.
     */
    public Recipe toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GenericString.class.getSimpleName()));
        }
        if (!GenericString.isValidGenericString(name)) {
            throw new IllegalValueException(GenericString.getMessageConstraints("Name"));
        }
        final GenericString modelName = new GenericString(name);

        final List<Ingredient> ingredients = new ArrayList<>();
        for (JsonAdaptedIngredient ingredient : recipeIngredients) {
            Ingredient i = ingredient.toModelType();
            boolean hasValidQuantity = Quantity.isValidQuantity(i.getQuantity().toString());

            if (!hasValidQuantity) {
                throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
            }

            ingredients.add(i);
        }
        final RecipeIngredientList modelRecipeIngredients = new RecipeIngredientList(ingredients);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        return new Recipe(modelName, modelRecipeIngredients, modelPrice);
    }
}
