package ay2122s1_cs2103t_w16_2.btbb.ui.order;

import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class OrderCard extends UiPart<Region> {
    private static final String FXML = "OrderListCard.fxml";

    private final Order order;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label clientPhone;

    /**
     * Creates a {@code OrderCard} with the given {@code Order} and index to display.
     */
    public OrderCard(Order order, int displayedIndex) {
        super(FXML);
        this.order = order;
        id.setText(displayedIndex + ". ");
        clientPhone.setText(order.getClientPhone().toString());
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
