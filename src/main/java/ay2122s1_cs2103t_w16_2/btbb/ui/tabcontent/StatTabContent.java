package ay2122s1_cs2103t_w16_2.btbb.ui.tabcontent;

import ay2122s1_cs2103t_w16_2.btbb.ui.UiPart;
import javafx.scene.layout.Region;

/**
 * Encapsulates the statistics tab.
 */
public class StatTabContent extends UiPart<Region> {
    private static final String FXML = "StatTabContent.fxml";

    /**
     * Constructs a {@code StatTab}.
     */
    public StatTabContent() {
        super(FXML);
    }
}
