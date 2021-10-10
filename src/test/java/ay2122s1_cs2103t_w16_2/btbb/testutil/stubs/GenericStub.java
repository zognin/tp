package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;

/**
 * A default predicate stub.
 */
public class GenericStub {
    private GenericString name;
    private GenericString address;

    /**
     * Constructs a PredicateUtil class.
     *
     * @param name Name of dummy.
     * @param address Address of dummy.
     */
    public GenericStub(GenericString name, GenericString address) {
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
