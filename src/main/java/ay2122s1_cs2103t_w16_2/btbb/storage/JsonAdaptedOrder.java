package ay2122s1_cs2103t_w16_2.btbb.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;

/**
 * Jackson-friendly version of {@link Order}.
 */
public class JsonAdaptedOrder {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String clientPhone;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("clientPhone") String clientPhone) {
        this.clientPhone = clientPhone;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        clientPhone = source.getClientPhone().toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @return Order model type.
     * @throws IllegalValueException If there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (clientPhone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(clientPhone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelClientPhone = new Phone(clientPhone);

        return new Order(modelClientPhone);
    }
}
