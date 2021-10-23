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
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
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
    private final String orderPrice;
    private final String deadline;
    private final String quantity;
    private final String isFinished;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("clientName") String clientName,
                            @JsonProperty("clientPhone") String clientPhone,
                            @JsonProperty("clientAddress") String clientAddress,
                            @JsonProperty("recipeName") String recipeName,
                            @JsonProperty("recipeIngredients") List<JsonAdaptedIngredient> recipeIngredients,
                            @JsonProperty("price") String orderPrice,
                            @JsonProperty("deadline") String deadline,
                            @JsonProperty("quantity") String quantity,
                            @JsonProperty("isFinished") String isFinished) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.recipeName = recipeName;
        this.orderPrice = orderPrice;
        this.deadline = deadline;
        this.quantity = quantity;
        this.isFinished = isFinished;

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
        orderPrice = source.getOrderPrice().toString();
        deadline = source.getDeadline().toJsonStorageString();
        quantity = source.getQuantity().toString();
        isFinished = source.getCompletionStatus().toString();
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

        if (orderPrice == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, OrderPrice.class.getSimpleName())
            );
        }
        if (!OrderPrice.isValidOrderPrice(orderPrice)) {
            throw new IllegalValueException(OrderPrice.MESSAGE_CONSTRAINTS);
        }
        final OrderPrice modelOrderPrice = new OrderPrice(orderPrice);

        if (deadline == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()
            ));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
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

        if (isFinished == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, CompletionStatus.class.getSimpleName()
            ));
        }
        if (!CompletionStatus.isValidCompletionStatus(isFinished)) {
            throw new IllegalValueException(CompletionStatus.MESSAGE_CONSTRAINTS);
        }
        final CompletionStatus modelCompletionStatus = new CompletionStatus(isFinished);

        return new Order(modelClientName, modelClientPhone, modelClientAddress,
                modelRecipeName, modelRecipeIngredients, modelOrderPrice, modelDeadline, modelQuantity,
                modelCompletionStatus);
    }
}
