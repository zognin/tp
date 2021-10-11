package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

public class GenericQuantityStub {
    private final Quantity quantity;

    /**
     * Constructs a stub containing a quantity attribute.
     *
     * @param quantity Quantity belonging to the stub.
     */
    public GenericQuantityStub(Quantity quantity) {
        this.quantity = quantity;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
