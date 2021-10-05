package ay2122s1_cs2103t_w16_2.btbb.ui;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;

/**
 * Represents a Ui tab.
 */
public enum UiTab {
    Home(0, "Home"),
    Stat(1, "Inventory & Statistics");

    private Index index;
    private String label;

    /**
     * Constructs a UiTab.
     *
     * @param zeroBasedIndex The zero based index identifying the tab.
     * @param label The string label of the tab, it should correspond to the view.
     */
    UiTab(int zeroBasedIndex, String label) {
        this.index = Index.fromZeroBased(zeroBasedIndex);
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    /**
     * Gets the zero based index that identifies the tab.
     *
     * @return An index number.
     */
    public int getTabIndex() {
        return index.getZeroBased();
    }
}
