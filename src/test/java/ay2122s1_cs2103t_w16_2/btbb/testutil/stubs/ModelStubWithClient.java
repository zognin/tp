package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

/**
 * A Model stub that contains a single client.
 */
public class ModelStubWithClient extends ModelStub {
    private final Client client;

    /**
     * Constructs a ModelStubWithClient object which contains the given client.
     *
     * @param client The client that this model should contain.
     */
    public ModelStubWithClient(Client client) {
        requireNonNull(client);
        this.client = client;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return this.client.isSameClient(client);
    }
}
