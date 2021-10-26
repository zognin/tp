package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;

public class ClientDescriptorTest {
    @Test
    public void toModelType_nullFields_throwsNullPointerException() {
        // All fields are null
        assertThrows(NullPointerException.class, () -> new ClientDescriptor().toModelType());

        // Name field is null
        ClientDescriptor nullNameDescriptor = new ClientDescriptorBuilder().build();
        nullNameDescriptor.setName(null);
        assertThrows(NullPointerException.class, nullNameDescriptor::toModelType);

        // Phone field is null
        ClientDescriptor nullPhoneDescriptor = new ClientDescriptorBuilder().build();
        nullPhoneDescriptor.setPhone(null);
        assertThrows(NullPointerException.class, nullPhoneDescriptor::toModelType);

        // Email field is null
        ClientDescriptor nullEmailDescriptor = new ClientDescriptorBuilder().build();
        nullEmailDescriptor.setEmail(null);
        assertThrows(NullPointerException.class, nullEmailDescriptor::toModelType);

        // Address field is null
        ClientDescriptor nullAddressDescriptor = new ClientDescriptorBuilder().build();
        nullAddressDescriptor.setAddress(null);
        assertThrows(NullPointerException.class, nullAddressDescriptor::toModelType);
    }

    @Test
    public void toModelType_validDescriptor_success() {
        Client expectedModelClient = new ClientBuilder().build();
        ClientDescriptor validClientDescriptor = new ClientDescriptorBuilder(expectedModelClient).build();
        assertEquals(expectedModelClient, validClientDescriptor.toModelType());
    }

    @Test
    public void toModelTypeFrom() {
        Client expectedModelClient = new ClientBuilder().build();

        // All fields in descriptor are non null
        Client anotherModelClient = new ClientBuilder().withName("Imposter").build();
        ClientDescriptor validClientDescriptor = new ClientDescriptorBuilder(expectedModelClient).build();
        assertEquals(expectedModelClient, validClientDescriptor.toModelTypeFrom(anotherModelClient));

        // Name field in descriptor is null
        validClientDescriptor = new ClientDescriptorBuilder(expectedModelClient).build();
        validClientDescriptor.setName(null);
        assertEquals(expectedModelClient, validClientDescriptor.toModelTypeFrom(expectedModelClient));

        // Phone field in descriptor is null
        validClientDescriptor = new ClientDescriptorBuilder(expectedModelClient).build();
        validClientDescriptor.setPhone(null);
        assertEquals(expectedModelClient, validClientDescriptor.toModelTypeFrom(expectedModelClient));

        // Email field in descriptor is null
        validClientDescriptor = new ClientDescriptorBuilder(expectedModelClient).build();
        validClientDescriptor.setEmail(null);
        assertEquals(expectedModelClient, validClientDescriptor.toModelTypeFrom(expectedModelClient));

        // Address field in descriptor is null
        validClientDescriptor = new ClientDescriptorBuilder(expectedModelClient).build();
        validClientDescriptor.setAddress(null);
        assertEquals(expectedModelClient, validClientDescriptor.toModelTypeFrom(expectedModelClient));
    }

    @Test
    public void equals() {
        // same values -> returns true
        ClientDescriptor descriptorWithSameValues = new ClientDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        ClientDescriptor editedAmy = new ClientDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new ClientDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new ClientDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new ClientDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
