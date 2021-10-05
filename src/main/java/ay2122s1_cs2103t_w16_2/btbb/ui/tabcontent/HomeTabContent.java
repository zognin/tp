package ay2122s1_cs2103t_w16_2.btbb.ui.tabcontent;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.ui.ClientListPanel;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import ay2122s1_cs2103t_w16_2.btbb.ui.order.OrderListPanel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Encapsulates the home tab.
 */
public class HomeTabContent extends UiPart<Region> {
    private static final String FXML = "HomeTabContent.fxml";

    @FXML
    private StackPane clientListPanelPlaceholder;

    @FXML
    private StackPane orderListPanelPlaceholder;

    /**
     * Constructs a {@code HomeTab}.
     *
     * @param clientList List of clients to display in the tab.
     * @param orderList List of orders to display in the tab.
     */
    public HomeTabContent(ObservableList<Client> clientList, ObservableList<Order> orderList) {
        super(FXML);

        ClientListPanel clientListPanel = new ClientListPanel(clientList);
        clientListPanelPlaceholder.getChildren().add(clientListPanel.getRoot());

        OrderListPanel orderListPanel = new OrderListPanel(orderList);
        orderListPanelPlaceholder.getChildren().add(orderListPanel.getRoot());
    }
}
