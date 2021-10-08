package ay2122s1_cs2103t_w16_2.btbb.ui.ingredient;

import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Encapsulates a UI component that displays information of a {@code Ingredient}.
 */
public class IngredientCard extends UiPart<Region> {
    private static final String FXML = "IngredientListCard.fxml";

    private final Ingredient ingredient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label quantity;
    @FXML
    private Label unit;

    /**
     * Creates a {@code IngredientCard} with the given {@code Ingredient} and index to display.
     *
     * @param ingredient Ingredient to display.
     * @param displayedIndex Index of the ingredient in the currently shown list.
     */
    public IngredientCard(Ingredient ingredient, int displayedIndex) {
        super(FXML);
        this.ingredient = ingredient;
        id.setText(displayedIndex + ". ");
        name.setText(ingredient.getName().toString());
        quantity.setText("x " + ingredient.getQuantity().toString());
        unit.setText(ingredient.getUnit().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IngredientCard)) {
            return false;
        }

        // state check
        IngredientCard card = (IngredientCard) other;
        return id.getText().equals(card.id.getText())
                && ingredient.equals(card.ingredient);
    }
}
