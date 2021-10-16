package ay2122s1_cs2103t_w16_2.btbb.model.client;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_FIRST;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_SECOND;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalIndexes.INDEX_THIRD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;
import javafx.collections.ObservableList;

public class UniqueClientListTest {
    private final UniqueClientList uniqueClientList = new UniqueClientList();

    @Test
    public void contains_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.contains(null));
    }

    @Test
    public void contains_clientNotInList_returnsFalse() {
        assertFalse(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        assertTrue(uniqueClientList.contains(ALICE));
    }

    @Test
    public void contains_clientWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(uniqueClientList.contains(editedAlice));
    }

    @Test
    public void add_validClient_listInSortedOrder() {
        uniqueClientList.add(BOB);
        uniqueClientList.add(ALICE);
        ObservableList<Client> clientList = uniqueClientList.asUnmodifiableObservableList();
        assertEquals(ALICE, clientList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(BOB, clientList.get(INDEX_SECOND.getZeroBased()));
    }

    @Test
    public void add_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.add(null));
    }

    @Test
    public void setClient_nullTargetClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(null, ALICE));
    }

    @Test
    public void setClient_nullEditedClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClient(ALICE, null));
    }

    @Test
    public void setClient_targetClientNotInList_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueClientList.setClient(ALICE, ALICE));
    }

    @Test
    public void setClient_editedClientIsSameClient_success() throws NotFoundException {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(ALICE);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasSameIdentity_success() throws NotFoundException {
        uniqueClientList.add(ALICE);
        Client editedAlice = new ClientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        uniqueClientList.setClient(ALICE, editedAlice);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(editedAlice);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasDifferentIdentity_success() throws NotFoundException {
        uniqueClientList.add(ALICE);
        uniqueClientList.setClient(ALICE, BOB);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClient_editedClientHasDifferentName_listInSortedOrder() throws NotFoundException {
        uniqueClientList.add(ALICE);
        uniqueClientList.add(BOB);
        uniqueClientList.add(CARL);
        uniqueClientList.setClient(CARL, AMY);
        ObservableList<Client> clientList = uniqueClientList.asUnmodifiableObservableList();
        assertEquals(ALICE, clientList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(AMY, clientList.get(INDEX_SECOND.getZeroBased()));
        assertEquals(BOB, clientList.get(INDEX_THIRD.getZeroBased()));
    }

    @Test
    public void remove_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.remove(null));
    }

    @Test
    public void remove_clientDoesNotExist_throwsNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueClientList.remove(ALICE));
    }

    @Test
    public void remove_existingClient_removesClient() throws NotFoundException {
        uniqueClientList.add(ALICE);
        uniqueClientList.remove(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullUniqueClientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((UniqueClientList) null));
    }

    @Test
    public void setClients_uniqueClientList_listInSortedOrder() {
        uniqueClientList.add(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(CARL);
        expectedUniqueClientList.add(BOB);
        uniqueClientList.setClients(expectedUniqueClientList);
        ObservableList<Client> clientObservableList = uniqueClientList.asUnmodifiableObservableList();
        assertEquals(BOB, clientObservableList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(CARL, clientObservableList.get(INDEX_SECOND.getZeroBased()));
    }

    @Test
    public void setClients_uniqueClientList_replacesOwnListWithProvidedUniqueClientList() {
        uniqueClientList.add(ALICE);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        uniqueClientList.setClients(expectedUniqueClientList);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void setClients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClientList.setClients((List<Client>) null));
    }

    @Test
    public void setClients_list_listInSortedOrder() {
        List<Client> clientList = List.of(BOB, ALICE, CARL);
        uniqueClientList.setClients(clientList);
        ObservableList<Client> clientObservableList = uniqueClientList.asUnmodifiableObservableList();
        assertEquals(ALICE, clientObservableList.get(INDEX_FIRST.getZeroBased()));
        assertEquals(BOB, clientObservableList.get(INDEX_SECOND.getZeroBased()));
        assertEquals(CARL, clientObservableList.get(INDEX_THIRD.getZeroBased()));
    }

    @Test
    public void setClients_list_replacesOwnListWithProvidedList() {
        uniqueClientList.add(ALICE);
        List<Client> clientList = Collections.singletonList(BOB);
        uniqueClientList.setClients(clientList);
        UniqueClientList expectedUniqueClientList = new UniqueClientList();
        expectedUniqueClientList.add(BOB);
        assertEquals(expectedUniqueClientList, uniqueClientList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueClientList.asUnmodifiableObservableList().remove(0));
    }
}
