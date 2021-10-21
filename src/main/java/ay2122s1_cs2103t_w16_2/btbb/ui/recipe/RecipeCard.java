package ay2122s1_cs2103t_w16_2.btbb.ui.recipe;

import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Encapsulates a UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {
    private static final String FXML = "RecipeListCard.fxml";

    private final Recipe recipe;

    @FXML
    private Label id;

    @FXML
    private Label recipeName;

    @FXML
    private Label recipeIngredients;

    @FXML
    private Label recipePrice;

    /**
     * Creates a {@code RecipeCard} with the given {@code Recipe} and index to display.
     *
     * @param recipe The recipe to display.
     * @param displayedIndex The index of the recipe in the currently shown list.
     */
    public RecipeCard(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        this.id.setText(displayedIndex + ". ");
        recipeName.setText(recipe.getName().toString());
        recipeIngredients.setText(recipe.getRecipeIngredients().toDisplayString());
        recipePrice.setText("(Price: $" + recipe.getPrice().toString() + ")");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeCard)) {
            return false;
        }

        // state check
        RecipeCard card = (RecipeCard) other;
        return id.getText().equals(card.id.getText())
                && recipe.equals(card.recipe);
    }
}
