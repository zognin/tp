package ay2122s1_cs2103t_w16_2.btbb.logic.commands.util;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.util.CommandUtil.makeOrderWithEditedIngredients;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.testutil.IngredientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.OrderBuilder;

public class CommandUtilTest {
    @Test
    public void makeOrderWithEditedIngredientsTest() {
        RecipeIngredientList editedIngredients = new RecipeIngredientList(List.of(new IngredientBuilder().build()));
        Order expectedOrder = new OrderBuilder().withRecipeIngredients(editedIngredients).build();

        Order actualOrder = makeOrderWithEditedIngredients(editedIngredients, new OrderBuilder().build());
        assertEquals(expectedOrder, actualOrder);
    }
}
