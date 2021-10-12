package ay2122s1_cs2103t_w16_2.btbb.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.IsDone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Jackson-friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String clientName;
    private final String clientPhone;
    private final String clientAddress;
    private final String recipeName;
    private final List<JsonAdaptedIngredient> recipeIngredients = new ArrayList<>();
    private final String price;
    private final String deadline;
    private final String quantity;
    private final String isDone;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("clientName") String clientName,
                            @JsonProperty("clientPhone") String clientPhone,
                            @JsonProperty("clientAddress") String clientAddress,
                            @JsonProperty("recipeName") String recipeName,
                            @JsonProperty("recipeIngredients") List<JsonAdaptedIngredient> recipeIngredients,
                            @JsonProperty("price") String price,
                            @JsonProperty("deadline") String deadline,
                            @JsonProperty("quantity") String quantity,
                            @JsonProperty("isDone") String isDone) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.recipeName = recipeName;
        this.price = price;
        this.deadline = deadline;
        this.quantity = quantity;
        this.isDone = isDone;

        if (recipeIngredients != null) {
            this.recipeIngredients.addAll(recipeIngredients);
        }
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        clientName = source.getClientName().toString();
        clientPhone = source.getClientPhone().toString();
        clientAddress = source.getClientAddress().toString();
        recipeName = source.getRecipeName().toString();
        recipeIngredients.addAll(source.getRecipeIngredients().getIngredients().stream()
                .map(JsonAdaptedIngredient::new).collect(Collectors.toList()));
        price = source.getPrice().toString();
        deadline = source.getDeadline().toJsonStorageString();
        quantity = source.getQuantity().toString();
        isDone = source.getIsDone().toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @return Order model type.
     * @throws IllegalValueException If there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (clientName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GenericString.class.getSimpleName()));
        }
        if (!GenericString.isValidGenericString(clientName)) {
            throw new IllegalValueException(GenericString.getMessageConstraints("Name"));
        }
        final GenericString modelClientName = new GenericString(clientName);

        if (clientPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(clientPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelClientPhone = new Phone(clientPhone);

        if (clientAddress == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(clientAddress)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelClientAddress = new Address(clientAddress);

        if (recipeName == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, GenericString.getMessageConstraints("Recipe Name")
            ));
        }
        if (!GenericString.isValidGenericString(recipeName)) {
            throw new IllegalValueException(GenericString.getMessageConstraints("Recipe Name"));
        }
        final GenericString modelRecipeName = new GenericString(recipeName);

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

        if (deadline == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()
            ));
        }
        if (!Deadline.isValidInternalDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_INTERNAL_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (quantity == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName()
            ));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (isDone == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, IsDone.class.getSimpleName()
            ));
        }
        if (!IsDone.isValidIsDone(isDone)) {
            throw new IllegalValueException(IsDone.MESSAGE_CONSTRAINTS);
        }
        final IsDone modelIsDone = new IsDone(isDone);

        return new Order(modelClientName, modelClientPhone, modelClientAddress,
                modelRecipeName, modelRecipeIngredients, modelPrice, modelDeadline, modelQuantity, modelIsDone);
    }
}
