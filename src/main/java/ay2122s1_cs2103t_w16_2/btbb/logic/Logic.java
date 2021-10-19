package ay2122s1_cs2103t_w16_2.btbb.logic;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.GuiSettings;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.ingredient.Ingredient;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderClient;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see ay2122s1_cs2103t_w16_2.btbb.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of clients */
    ObservableList<Client> getFilteredClientList();

    /** Returns an unmodifiable view of the filtered list of ingredients */
    ObservableList<Ingredient> getFilteredIngredientList();

    /** Returns an unmodifiable view of the filtered list of orders */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Returns the top ten clients with the most orders.
     *
     * @return List containing the top 10 clients with most orders.
     */
    List<Map.Entry<OrderClient, Long>> getTopTenOrderClients();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
