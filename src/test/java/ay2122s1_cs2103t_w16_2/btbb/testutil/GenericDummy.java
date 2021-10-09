package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * Dummy utility class to test generic classes.
 */
public class GenericDummy {
    private GenericString name;
    private GenericString address;

    /**
     * Constructs a dummy utility class.
     *
     * @param name Name of dummy.
     * @param address Address of dummy.
     */
    public GenericDummy(GenericString name, GenericString address) {
        this.name = name;
        this.address = address;
    }

    public GenericString getName() {
        return name;
    }

    public GenericString getAddress() {
        return address;
    }
}
