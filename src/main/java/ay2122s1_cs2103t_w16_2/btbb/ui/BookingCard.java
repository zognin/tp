package ay2122s1_cs2103t_w16_2.btbb.ui;

import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class BookingCard extends UiPart<Region> {
    private static final String FXML = "BookingListCard.fxml";

    private final Booking booking;

    @FXML
    private Label id;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;

    /**
     * Creates a {@code BookingCode} with the given {@code Booking} and index to display.
     */
    public BookingCard(Booking booking, int displayedIndex) {
        super(FXML);
        this.booking = booking;
        id.setText(displayedIndex + ". ");
        name.setText(booking.getName().toString());
        phone.setText(booking.getPhone().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingCard)) {
            return false;
        }

        // state check
        BookingCard card = (BookingCard) other;
        return id.getText().equals(card.id.getText())
                && booking.equals(card.booking);
    }
}
