package ay2122s1_cs2103t_w16_2.btbb.ui.tabcontent;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
        revenueBarChart.getYAxis().setLabel("Revenue ($)");

        // To show the exact revenue (rounded to 2dp) for each month on mouse hover
        revenueBarChart.getData().get(0).getData().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                Tooltip tooltip = new Tooltip("$" + BigDecimal.valueOf(
                        data.getYValue()).setScale(2, RoundingMode.HALF_UP));
                tooltip.setShowDelay(Duration.millis(1));
                tooltip.setHideDelay(Duration.millis(1));
                Tooltip.install(data.getNode(), tooltip);
            });
        });
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
                                    + ")\n" + "Orders made: " + entry.getValue(),
                                    entry.getValue());
                        })
                        .collect(Collectors.toList())
        );

        clientPieChart.setTitle("Top 10 Most Frequent Clients");
        clientPieChart.setData(pieChartData);

        // To show labels as tooltips on mouse hover for slices that are too small for the labels to appear
        addTooltipToPieChartSlices(clientPieChart);
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
                                    + "\n" + "No. of times ordered: " + entry.getValue(),
                                    entry.getValue());
                        })
                        .collect(Collectors.toList())
        );

        recipePieChart.setTitle("Top 10 Most Popular Recipes");
        recipePieChart.setData(pieChartData);

        // To show labels as tooltips on mouse hover for slices that are too small for the labels to appear
        addTooltipToPieChartSlices(recipePieChart);
    }

    /**
     * Adds a tooltip that shows the pie chart data on mouse hover.
     *
     * @param pieChart The pie chart.
     */
    private void addTooltipToPieChartSlices(PieChart pieChart) {
        pieChart.getData().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                Tooltip tooltip = new Tooltip(data.getName());
                tooltip.setShowDelay(Duration.millis(1));
                tooltip.setHideDelay(Duration.millis(1));
                Tooltip.install(data.getNode(), tooltip);
            });
        });
    }
}
