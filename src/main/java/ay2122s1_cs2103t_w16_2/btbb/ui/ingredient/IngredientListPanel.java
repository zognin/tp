package ay2122s1_cs2103t_w16_2.btbb.ui.ingredient;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of ingredients.
 */
public class IngredientListPanel extends UiPart<Region> {
    private static final String FXML = "IngredientListPanel.fxml";

    @FXML
    private ListView<Ingredient> ingredientListView;

    /**
     * Creates a {@code IngredientListPanel} with the given {@code ObservableList}.
     *
     * @param ingredientList Ingredient list to display.
     */
    public IngredientListPanel(ObservableList<Ingredient> ingredientList) {
        super(FXML);
        ingredientListView.setItems(ingredientList);
        ingredientListView.setCellFactory(listView -> new IngredientListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Ingredient} using a {@code IngredientCard}.
     */
    class IngredientListViewCell extends ListCell<Ingredient> {
        @Override
        protected void updateItem(Ingredient ingredient, boolean empty) {
            super.updateItem(ingredient, empty);

            if (empty || ingredient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IngredientCard(ingredient, getIndex() + 1).getRoot());
            }
        }
    }
}
