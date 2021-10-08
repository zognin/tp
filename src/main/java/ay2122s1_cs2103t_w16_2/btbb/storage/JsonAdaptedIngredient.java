package ay2122s1_cs2103t_w16_2.btbb.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Quantity;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * Jackson-friendly version of {@link Ingredient}.
 */
public class JsonAdaptedIngredient {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Ingredient's %s field is missing!";

    private final String name;
    private final String quantity;
    private final String unit;

    /**
     * Constructs a {@code JsonAdaptedIngredient} with the given ingredient details.
     */
    @JsonCreator
    public JsonAdaptedIngredient(@JsonProperty("ingredientName") String name,
                                 @JsonProperty("quantity") String quantity, @JsonProperty("unit") String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Converts a given {@code Ingredient} into this class for Jackson use.
     */
    public JsonAdaptedIngredient(Ingredient source) {
        name = source.getName().toString();
        quantity = source.getQuantity().toString();
        unit = source.getUnit().toString();
    }

    /**
     * Converts this Jackson-friendly adapted ingredient object into the model's {@code Ingredient} object.
     *
     * @return Ingredient model type.
     * @throws IllegalValueException If there were any data constraints violated in the adapted ingredient.
     */
    public Ingredient toModelType() throws IllegalValueException {
        // Ingredient Name:
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GenericString.class.getSimpleName()));
        }
        if (!GenericString.isValidGenericString(name)) {
            throw new IllegalValueException(GenericString.getMessageConstraints("Name"));
        }
        final GenericString modelIngredientName = new GenericString(name);

        // Quantity:
        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        // Unit:
        if (unit == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GenericString.class.getSimpleName()));
        }
        if (!GenericString.isValidGenericString(unit)) {
            throw new IllegalValueException(GenericString.getMessageConstraints("Unit"));
        }
        final GenericString modelUnit = new GenericString(unit);

        return new Ingredient(modelIngredientName, modelQuantity, modelUnit);
    }
}
