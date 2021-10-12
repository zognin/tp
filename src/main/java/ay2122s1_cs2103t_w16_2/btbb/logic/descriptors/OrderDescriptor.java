package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Price;
import ay2122s1_cs2103t_w16_2.btbb.model.order.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Stores the details relevant to an order.
 * Some details have to be converted to their model representations before converting to a Order model type.
 */
public class OrderDescriptor {
    public static final String MESSAGE_MISSING_CLIENT_DETAILS = "Both client and client details cannot be found";

    private Index clientIndex;
    private GenericString clientName;
    private Phone clientPhone;
    private Address clientAddress;
    private GenericString recipeName;
    private RecipeIngredientList recipeIngredients;
    private Price price;
    private Deadline deadline;
    private Quantity quantity;

    public OrderDescriptor() {};

    /**
     * Constructs an {@code OrderDescriptor} using the details of an existing {@code OrderDescriptor}.
     *
     * @param toCopy Existing {@code OrderDescriptor}.
     */
    public OrderDescriptor(OrderDescriptor toCopy) {
        setClientIndex(toCopy.clientIndex);
        setClientName(toCopy.clientName);
        setClientPhone(toCopy.clientPhone);
        setClientAddress(toCopy.clientAddress);
        setRecipeName(toCopy.recipeName);
        setRecipeIngredients(toCopy.recipeIngredients);
        setPrice(toCopy.price);
        setDeadline(toCopy.deadline);
        setQuantity(toCopy.quantity);
    }

    public void setClientIndex(Index clientIndex) {
        this.clientIndex = clientIndex;
    }

    public Optional<Index> getClientIndex() {
        return Optional.ofNullable(clientIndex);
    }

    public void setClientName(GenericString clientName) {
        this.clientName = clientName;
    }

    public Optional<GenericString> getClientName() {
        return Optional.ofNullable(clientName);
    }

    public void setClientPhone(Phone clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Optional<Phone> getClientPhone() {
        return Optional.ofNullable(clientPhone);
    }

    public void setClientAddress(Address clientAddress) {
        this.clientAddress = clientAddress;
    }

    public Optional<Address> getClientAddress() {
        return Optional.ofNullable(clientAddress);
    }

    public void setRecipeName(GenericString recipeName) {
        this.recipeName = recipeName;
    }

    public Optional<GenericString> getRecipeName() {
        return Optional.ofNullable(recipeName);
    }

    public void setRecipeIngredients(RecipeIngredientList recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public Optional<RecipeIngredientList> getRecipeIngredients() {
        return Optional.ofNullable(recipeIngredients);
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Optional<Price> getPrice() {
        return Optional.ofNullable(price);
    }

    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    public Optional<Deadline> getDeadline() {
        return Optional.ofNullable(deadline);
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Optional<Quantity> getQuantity() {
        return Optional.ofNullable(quantity);
    }

    /**
     * Converts an Order Descriptor to an Order model type.
     *
     * @param model To get the client list.
     * @return {@code Order}
     * @throws CommandException if both the client and its details are missing.
     */
    public Order toModelType(Model model) throws CommandException {
        Optional<Client> client = getClientFromModel(model);

        try {
            GenericString clientName = getClientName().orElseGet(() -> client.get().getName());
            Phone clientPhone = getClientPhone().orElseGet(() -> client.get().getPhone());
            Address clientAddress = getClientAddress().orElseGet(() -> client.get().getAddress());
            Quantity quantity = getQuantity().orElse(new Quantity("1"));
            RecipeIngredientList recipeIngredientList =
                    getRecipeIngredients().orElse(new RecipeIngredientList(new ArrayList<>()));
            return new Order(clientName, clientPhone, clientAddress,
                    recipeName, recipeIngredientList, price, deadline, quantity);
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_MISSING_CLIENT_DETAILS);
        }
    }

    /**
     * Converts an Order Descriptor to an Order model type.
     * Missing fields are filled with an existing order.
     *
     * @param existingOrder An existing Order that is not null.
     * @return {@code Order}.
     */
    public Order toModelTypeFrom(Order existingOrder) {
        assert existingOrder != null;

        GenericString clientName = getClientName().orElse(existingOrder.getClientName());
        Phone clientPhone = getClientPhone().orElse(existingOrder.getClientPhone());
        Address clientAddress = getClientAddress().orElse(existingOrder.getClientAddress());
        GenericString recipeName = getRecipeName().orElse(existingOrder.getRecipeName());
        RecipeIngredientList recipeIngredientList = getRecipeIngredients().orElse(existingOrder.getRecipeIngredients());
        Price price = getPrice().orElse(existingOrder.getPrice());
        Deadline deadline = getDeadline().orElse(existingOrder.getDeadline());
        Quantity quantity = getQuantity().orElse(existingOrder.getQuantity());
        return new Order(clientName, clientPhone, clientAddress,
                recipeName, recipeIngredientList, price, deadline, quantity);
    }

    public Optional<Client> getClientFromModel(Model model) throws CommandException {
        List<Client> lastShownClientList = model.getFilteredClientList();

        if (getClientIndex().isPresent() && getClientIndex().get().getZeroBased() >= lastShownClientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Optional<Client> client = getClientIndex().isPresent()
                ? Optional.of(lastShownClientList.get(clientIndex.getZeroBased()))
                : Optional.empty();
        return client;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OrderDescriptor)) {
            return false;
        }

        // state check
        OrderDescriptor otherOrderDescriptor = (OrderDescriptor) other;

        return getClientIndex().equals(otherOrderDescriptor.getClientIndex())
                && getClientName().equals(otherOrderDescriptor.getClientName())
                && getClientPhone().equals(otherOrderDescriptor.getClientPhone())
                && getClientAddress().equals(otherOrderDescriptor.getClientAddress())
                && getRecipeName().equals(otherOrderDescriptor.getRecipeName())
                && getRecipeIngredients().equals(otherOrderDescriptor.getRecipeIngredients())
                && getPrice().equals(otherOrderDescriptor.getPrice())
                && getDeadline().equals(otherOrderDescriptor.getDeadline())
                && getQuantity().equals(otherOrderDescriptor.getQuantity());
    }
}
