package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.time.YearMonth;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderClient;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Model stub that contains a single order.
 */
public class ModelStubWithOrder extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();
    private final ArrayList<Recipe> recipeAdded = new ArrayList<>();
    private final Order order;

    /**
     * Constructs a ModelStubWithOrder object which contains the given order.
     *
     * @param order The order that this model should contain.
     */
    public ModelStubWithOrder(Order order) {
        requireNonNull(order);
        this.order = order;
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return FXCollections.observableList(clientsAdded);
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return FXCollections.observableList(recipeAdded);
    }

    @Override
    public List<Map.Entry<YearMonth, Double>> getRevenueForPastTwelveMonths() {
        return List.of(new AbstractMap.SimpleEntry<>(
                YearMonth.from(order.getYearMonth()), order.getOrderPrice().getDoubleValue()));
    }

    @Override
    public List<Map.Entry<OrderClient, Long>> getTopTenOrderClients() {
        return List.of(new AbstractMap.SimpleEntry<>(
                new OrderClient(order.getClientName(), order.getClientPhone()), 1L));
    }

    @Override
    public List<Map.Entry<GenericString, Long>> getTopTenOrderRecipes() {
        return List.of(new AbstractMap.SimpleEntry<>(order.getRecipeName(), 1L));
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return this.order.isSameOrder(order);
    }
}
