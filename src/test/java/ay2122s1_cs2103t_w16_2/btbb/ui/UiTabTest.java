package ay2122s1_cs2103t_w16_2.btbb.ui;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTabTest {
    @Test
    public void getTabIndex_validIndex_zeroBasedIndex() {
        UiTab tab = UiTab.values()[INDEX_FIRST.getZeroBased()];
        assertEquals(tab.getTabIndex(), INDEX_FIRST.getZeroBased());
    }
}
