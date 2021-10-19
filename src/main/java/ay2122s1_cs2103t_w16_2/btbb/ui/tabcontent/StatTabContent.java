package ay2122s1_cs2103t_w16_2.btbb.ui.tabcontent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderClient;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import ay2122s1_cs2103t_w16_2.btbb.ui.ingredient.IngredientListPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Encapsulates the statistics tab.
 */
public class StatTabContent extends UiPart<Region> {
    private static final String FXML = "StatTabContent.fxml";

    @FXML
    private StackPane ingredientListPanelPlaceholder;

    @FXML
    private PieChart clientPieChart;

    /**
     * Constructs a {@code StatTabContent}.
     *
     * @param ingredientList List of ingredients to display in the tab.
     * @param topTenClients Top ten clients to be shown in statistics.
     */
    public StatTabContent(ObservableList<Ingredient> ingredientList,
                          List<Map.Entry<OrderClient, Long>> topTenClients) {
        super(FXML);

        IngredientListPanel ingredientListPanel = new IngredientListPanel(ingredientList);
        ingredientListPanelPlaceholder.getChildren().add(ingredientListPanel.getRoot());

        setClientStatisticsPieChart(topTenClients);
    }

    /**
     * Converts a list of top ten clients to data points in the pie chart.
     *
     * @param topTenClients Top ten clients to be converted to data points in the pie chart.
     */
    private void setClientStatisticsPieChart(List<Map.Entry<OrderClient, Long>> topTenClients) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableList(
                topTenClients.stream()
                        .map(entry -> {
                            OrderClient orderClient = entry.getKey();
                            return new PieChart.Data(orderClient.getName().toString()
                                    + " (" + orderClient.getPhone().toString()
                                    + ")\n" + "Orders: " + entry.getValue(),
                                    entry.getValue());
                        })
                        .collect(Collectors.toList())
        );

        clientPieChart.setData(pieChartData);
        clientPieChart.setTitle("Top 10 Clients (by no. of Orders)");
    }
}
