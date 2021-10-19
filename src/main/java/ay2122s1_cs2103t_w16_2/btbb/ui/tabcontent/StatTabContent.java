package ay2122s1_cs2103t_w16_2.btbb.ui.tabcontent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import ay2122s1_cs2103t_w16_2.btbb.ui.ingredient.IngredientListPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

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
                          List<Map.Entry<Pair<GenericString, Phone>, Integer>> topTenClients) {
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
    private void setClientStatisticsPieChart(List<Map.Entry<Pair<GenericString, Phone>, Integer>> topTenClients) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableList(
                topTenClients.stream()
                        .map(entry -> {
                            Pair<GenericString, Phone> clientNamePhonePair = entry.getKey();
                            GenericString clientName = clientNamePhonePair.getKey();
                            Phone clientPhone = clientNamePhonePair.getValue();
                            return new PieChart.Data(clientName.toString() + " (" + clientPhone.toString() + ")",
                                    entry.getValue());
                        })
                        .collect(Collectors.toList())
        );

        clientPieChart.setData(pieChartData);
    }
}
