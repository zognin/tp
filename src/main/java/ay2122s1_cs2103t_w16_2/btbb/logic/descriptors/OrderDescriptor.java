package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages;
import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil;
import ay2122s1_cs2103t_w16_2.btbb.exception.CommandException;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.model.order.CompletionStatus;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Deadline;
import ay2122s1_cs2103t_w16_2.btbb.model.order.Order;
import ay2122s1_cs2103t_w16_2.btbb.model.order.OrderPrice;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.Recipe;
import ay2122s1_cs2103t_w16_2.btbb.model.recipe.RecipeIngredientList;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.GenericString;
import ay2122s1_cs2103t_w16_2.btbb.model.shared.Quantity;

/**
 * Stores the details relevant to an order.
 * Some details have to be converted to their model representations before converting to a Order model type.
 */
public class OrderDescriptor {
    public static final String MESSAGE_MISSING_CLIENT_DETAILS = "If CLIENT_INDEX is not present, CLIENT_NAME, "
            + "CLIENT_PHONE and CLIENT_ADDRESS must be present.";
    public static final String MESSAGE_MISSING_RECIPE_DETAILS = "If RECIPE_INDEX is not present, RECIPE_NAME and "
            + "ORDER_PRICE must be present.";

    private Index clientIndex;
    private GenericString clientName;
    private Phone clientPhone;
    private Address clientAddress;
    private Index recipeIndex;
    private GenericString recipeName;
    private RecipeIngredientList recipeIngredients;
    private OrderPrice orderPrice;
    private Deadline deadline;
    private Quantity quantity;
    private CompletionStatus completionStatus;

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
        setRecipeIndex(toCopy.recipeIndex);
        setRecipeName(toCopy.recipeName);
        setRecipeIngredients(toCopy.recipeIngredients);
        setOrderPrice(toCopy.orderPrice);
        setDeadline(toCopy.deadline);
        setQuantity(toCopy.quantity);
        setCompletionStatus(toCopy.completionStatus);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(clientIndex, clientName, clientPhone, clientAddress, recipeIndex, recipeName,
                recipeIngredients, orderPrice, deadline, quantity);
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

    public void setRecipeIndex(Index recipeIndex) {
        this.recipeIndex = recipeIndex;
    }

    public Optional<Index> getRecipeIndex() {
        return Optional.ofNullable(recipeIndex);
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

    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Optional<OrderPrice> getOrderPrice() {
        return Optional.ofNullable(orderPrice);
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

    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    public Optional<CompletionStatus> getCompletionStatus() {
        return Optional.ofNullable(completionStatus);
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
        Optional<Recipe> recipe = getRecipeFromModel(model);
        GenericString clientName;
        Phone clientPhone;
        Address clientAddress;
        GenericString recipeName;
        RecipeIngredientList recipeIngredients;
        OrderPrice orderPrice;

        try {
            clientName = getClientName().orElseGet(() -> client.get().getName());
            clientPhone = getClientPhone().orElseGet(() -> client.get().getPhone());
            clientAddress = getClientAddress().orElseGet(() -> client.get().getAddress());
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_MISSING_CLIENT_DETAILS);
        }

        try {
            recipeName = getRecipeName().orElseGet(() -> recipe.get().getName());
            recipeIngredients = !getRecipeIngredients().get().isEmpty() || recipe.isEmpty()
                    ? getRecipeIngredients().get()
                    : recipe.get().getRecipeIngredients();
            orderPrice = getOrderPrice().orElseGet(() ->
                            recipe.get().getRecipePrice().multiplyRecipePriceByQuantity(quantity));
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_MISSING_RECIPE_DETAILS);
        }

        return new Order(clientName, clientPhone, clientAddress, recipeName, recipeIngredients, orderPrice,
                deadline, quantity, completionStatus);
    }

    /**
     * Converts an Order Descriptor to an Order model type.
     * Missing fields are filled with an existing order.
     *
     * @param model To get the client list.
     * @param existingOrder An existing order that is not null.
     * @return {@code Order}.
     * @throws CommandException If the client index provided is invalid.
     */
    public Order toModelTypeFrom(Model model, Order existingOrder) throws CommandException {
        assert existingOrder != null;

        Optional<Client> client = getClientFromModel(model);
        Optional<Recipe> recipe = getRecipeFromModel(model);
        boolean isClientPresent = client.isPresent();
        boolean isRecipePresent = recipe.isPresent();

        GenericString updatedClientName = getClientName().orElse(isClientPresent
                ? client.get().getName() : existingOrder.getClientName());
        Phone updatedClientPhone = getClientPhone().orElse(isClientPresent
                ? client.get().getPhone() : existingOrder.getClientPhone());
        Address updatedClientAddress = getClientAddress().orElse(isClientPresent
                ? client.get().getAddress() : existingOrder.getClientAddress());

        GenericString updatedRecipeName = getRecipeName().orElse(isRecipePresent
                ? recipe.get().getName() : existingOrder.getRecipeName());
        RecipeIngredientList updatedRecipeIngredientList = getRecipeIngredients()
                .orElse(isRecipePresent ? recipe.get().getRecipeIngredients() : existingOrder.getRecipeIngredients());

        Deadline updatedDeadline = getDeadline().orElse(existingOrder.getDeadline());
        Quantity updatedQuantity = getQuantity().orElse(existingOrder.getQuantity());
        OrderPrice updatedOrderPrice = getOrderPrice().orElse(isRecipePresent
                ? recipe.get().getRecipePrice().multiplyRecipePriceByQuantity(updatedQuantity)
                : existingOrder.getOrderPrice());
        CompletionStatus updatedCompletionStatus = getCompletionStatus().orElse(existingOrder.getCompletionStatus());

        return new Order(updatedClientName, updatedClientPhone, updatedClientAddress,
                updatedRecipeName, updatedRecipeIngredientList,
                updatedOrderPrice, updatedDeadline, updatedQuantity, updatedCompletionStatus);
    }

    private Optional<Client> getClientFromModel(Model model) throws CommandException {
        List<Client> lastShownClientList = model.getFilteredClientList();

        if (getClientIndex().isPresent() && getClientIndex().get().getZeroBased() >= lastShownClientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Optional<Client> client = getClientIndex().isPresent()
                ? Optional.of(lastShownClientList.get(clientIndex.getZeroBased()))
                : Optional.empty();
        return client;
    }

    private Optional<Recipe> getRecipeFromModel(Model model) throws CommandException {
        List<Recipe> lastShownRecipeList = model.getFilteredRecipeList();

        if (getRecipeIndex().isPresent() && getRecipeIndex().get().getZeroBased() >= lastShownRecipeList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Optional<Recipe> recipe = getRecipeIndex().isPresent()
                ? Optional.of(lastShownRecipeList.get(recipeIndex.getZeroBased()))
                : Optional.empty();
        return recipe;
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
                && getRecipeIndex().equals(otherOrderDescriptor.getRecipeIndex())
                && getRecipeName().equals(otherOrderDescriptor.getRecipeName())
                && getRecipeIngredients().equals(otherOrderDescriptor.getRecipeIngredients())
                && getOrderPrice().equals(otherOrderDescriptor.getOrderPrice())
                && getDeadline().equals(otherOrderDescriptor.getDeadline())
                && getQuantity().equals(otherOrderDescriptor.getQuantity())
                && getCompletionStatus().equals(otherOrderDescriptor.getCompletionStatus());
    }
}
