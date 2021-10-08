package ay2122s1_cs2103t_w16_2.btbb.ui.order;

import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Encapsulates a UI component that displays information of a {@code Order}.
 */
public class OrderCard extends UiPart<Region> {
    private static final String FXML = "OrderListCard.fxml";

    private final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label clientName;
    @FXML
    private Label clientPhone;
    @FXML
    private Label clientAddress;

    /**
     * Creates a {@code OrderCard} with the given {@code Order} and index to display.
     *
     * @param order Order to display.
     * @param displayedIndex Index of the order in the currently shown list.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        clientName.setText(order.getClientName().toString());
        clientPhone.setText(order.getClientPhone().toString());
        clientAddress.setText(order.getClientAddress().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderCard)) {
            return false;
        }

        // state check
        OrderCard card = (OrderCard) other;
        return id.getText().equals(card.id.getText())
                && order.equals(card.order);
    }
}
