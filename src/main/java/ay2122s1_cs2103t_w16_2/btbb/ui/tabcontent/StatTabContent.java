package ay2122s1_cs2103t_w16_2.btbb.ui.tabcontent;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderClient;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import ay2122s1_cs2103t_w16_2.btbb.ui.ingredient.IngredientListPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
    private BarChart<String, Double> revenueBarChart;

    @FXML
    private PieChart clientPieChart;

    @FXML
    private PieChart recipePieChart;

    /**
     * Constructs a {@code StatTabContent}.
     *
     * @param ingredientList List of ingredients to display in the tab.
     * @param topTenOrderClients Top 10 order clients to be shown in statistics.
     * @param revenueForPastTwelveMonths Revenue details for the past 12 months.
     */
    public StatTabContent(ObservableList<Ingredient> ingredientList,
                          List<Entry<YearMonth, Double>> revenueForPastTwelveMonths,
                          List<Entry<OrderClient, Long>> topTenOrderClients,
                          List<Entry<GenericString, Long>> topTenOrderRecipes) {
        super(FXML);

        IngredientListPanel ingredientListPanel = new IngredientListPanel(ingredientList);
        ingredientListPanelPlaceholder.getChildren().add(ingredientListPanel.getRoot());

        setRevenueBarChart(revenueForPastTwelveMonths);
        setTopTenOrderClientsPieChart(topTenOrderClients);
        setTopTenOrderRecipesPieChart(topTenOrderRecipes);
    }

    private void setRevenueBarChart(List<Entry<YearMonth, Double>> revenueForPastTwelveMonths) {
        List<XYChart.Data<String, Double>> barChartData = revenueForPastTwelveMonths.stream()
                        .map(entry -> new XYChart.Data<>(entry.getKey().getMonth()
                                .getDisplayName(TextStyle.SHORT, Locale.US)
                                 + " " + entry.getKey().getYear(), entry.getValue()))
                        .collect(Collectors.toList());

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.getData().addAll(barChartData);

        revenueBarChart.setData(FXCollections.observableList(List.of(series)));
        revenueBarChart.setTitle("Revenue for past 12 months");
        revenueBarChart.getXAxis().setLabel("Month & Year");
        revenueBarChart.getYAxis().setLabel("Revenue");
    }

    /**
     * Converts a list of top 10 clients to data points in the pie chart.
     *
     * @param topTenOrderClients Top 10 order clients to be converted to data points in the pie chart.
     */
    private void setTopTenOrderClientsPieChart(List<Entry<OrderClient, Long>> topTenOrderClients) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableList(
                topTenOrderClients.stream()
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

    /**
     * Converts a list of top 10 recipes to data points in the pie chart.
     *
     * @param topTenOrderRecipes Top 10 order recipes to be converted to data points in the pie chart.
     */
    private void setTopTenOrderRecipesPieChart(List<Entry<GenericString, Long>> topTenOrderRecipes) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableList(
                topTenOrderRecipes.stream()
                        .map(entry -> {
                            GenericString recipeName = entry.getKey();
                            return new PieChart.Data(recipeName.toString()
                                    + "\n" + "Orders: " + entry.getValue(),
                                    entry.getValue());
                        })
                        .collect(Collectors.toList())
        );

        recipePieChart.setData(pieChartData);
        recipePieChart.setTitle("Top 10 Recipes (by no. of Orders)");
    }
}
