package seedu.address.logic.descriptors;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class PersonDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;

    public PersonDescriptor() {
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public PersonDescriptor(PersonDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    /**
     * Converts a Person Descriptor to a Person model type.
     * All non null fields must be present before conversion.
     *
     * @return {@code Person}.
     */
    public Person toModelType() {
        requireAllNonNull(name, phone, email, address);
        return new Person(name, phone, email, address);
    }

    /**
     * Converts a Person Descriptor to a Person model type.
     * Missing fields are filled with an existing person.
     *
     * @param existingPerson An existing Person that is not null.
     * @return {@code Person}.
     */
    public Person toModelTypeFrom(Person existingPerson) {
        assert existingPerson != null;

        Name updatedName = getName().orElse(existingPerson.getName());
        Phone updatedPhone = getPhone().orElse(existingPerson.getPhone());
        Email updatedEmail = getEmail().orElse(existingPerson.getEmail());
        Address updatedAddress = getAddress().orElse(existingPerson.getAddress());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDescriptor)) {
            return false;
        }

        // state check
        PersonDescriptor e = (PersonDescriptor) other;

        return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress());
    }
}
