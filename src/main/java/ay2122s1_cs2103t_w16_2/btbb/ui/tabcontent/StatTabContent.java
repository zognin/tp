package ay2122s1_cs2103t_w16_2.btbb.ui.tabcontent;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import ay2122s1_cs2103t_w16_2.btbb.ui.ingredient.IngredientListPanel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Encapsulates the statistics tab.
 */
public class StatTabContent extends UiPart<Region> {
    private static final String FXML = "StatTabContent.fxml";

    @FXML
    private StackPane ingredientListPanelPlaceholder;

    /**
     * Constructs a {@code StatTab}.
     *
     * @param ingredientList List of ingredients to display in the tab.
     */
    public StatTabContent(ObservableList<Ingredient> ingredientList) {
        super(FXML);

        IngredientListPanel ingredientListPanel = new IngredientListPanel(ingredientList);
        ingredientListPanelPlaceholder.getChildren().add(ingredientListPanel.getRoot());
    }
}
