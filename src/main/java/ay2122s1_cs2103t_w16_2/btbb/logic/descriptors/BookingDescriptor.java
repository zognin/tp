package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

public class BookingDescriptor {
    private Phone phone;

    public BookingDescriptor() {};

    public BookingDescriptor(BookingDescriptor toCopy) {
        setPhone(toCopy.phone);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    /**
     * Converts a Booking Descriptor to a Booking model type.
     *
     * @param model Model.
     * @return Booking.
     * @throws NotFoundException If a phone for the booking is not found.
     */
    public Booking toModelType(Model model) throws NotFoundException {
        requireAllNonNull(phone);
        Optional<Client> optionalClient = model.getClientByPhone(phone);
        if (!(optionalClient.isPresent())) {
            throw new NotFoundException(Phone.class.getName());
        }

        return new Booking(optionalClient.get());
    }
}
