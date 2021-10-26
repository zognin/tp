package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A Model stub that always accepts the client and recipe being added.
 */
public class ModelStubAcceptingClientAndRecipeAdded extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();
    private final ArrayList<Recipe> recipesAdded = new ArrayList<>();

    @Override
    public void addClient(Client client) {
        requireNonNull(client);
        clientsAdded.add(client);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        requireNonNull(recipe);
        recipesAdded.add(recipe);
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return clientsAdded.stream().anyMatch(client::isSameClient);
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return recipesAdded.stream().anyMatch(recipe::isSameRecipe);
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return FXCollections.observableArrayList(clientsAdded);
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return FXCollections.observableArrayList(recipesAdded);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
