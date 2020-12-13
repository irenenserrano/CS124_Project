import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class ContactListTest {
    private static ContactList contactList = new ContactList();

    @Test
    void size_equals_CorrectInitialSizes() {
        contactList.insert("Irene", "4152231231");
        assertEquals(5, contactList.size());
        assertEquals(2, contactList.numContactNodes());
        assertEquals(1, contactList.numContacts());
    }

    @ParameterizedTest
    @ArgumentsSource(insertContacts.class)
    void size_equals_UpdatedSizesAfterRehash(int expected, int actual) {
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(deleteContactsName.class)
    void size_equals_CorrectSizesAfterDeleteName(int expected, int actual) {
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(deleteContactsNumber.class)
    void size_equals_CorrectSizesAfterDeleteNumber(int expected, int actual) {
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(searchAllContacts.class)
    void searchAllContacts_equals_ActualReturnMeetsExpected(ArrayList<Contact> expected,
            ArrayList<Contact> actual) {
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
        assertEquals(expected.size(), actual.size());
    }

    @ParameterizedTest
    @ArgumentsSource(printAllContacts.class)
    void printAllContacts_equals_ReturnedContactsInOrder(ArrayList<Contact> expected,
            ArrayList<Contact> actual) {
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
        assertEquals(expected.size(), actual.size());

    }

    static class insertContacts implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            contactList.insert("Jen", "4151112233");
            return Stream.of(Arguments.of(11, contactList.size()),
                    Arguments.of(4, contactList.numContactNodes()),
                    Arguments.of(2, contactList.numContacts()));
        }
    }

    static class deleteContactsName implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            contactList.insert("Isabell", "5101234556");
            contactList.insert("Maria", "5105569987");
            contactList.delete("Irene");
            return Stream.of(Arguments.of(23, contactList.size()),
                    Arguments.of(6, contactList.numContactNodes()),
                    Arguments.of(3, contactList.numContacts()));
        }
    }

    static class deleteContactsNumber implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            contactList.delete("5101234556");
            return Stream.of(Arguments.of(23, contactList.size()),
                    Arguments.of(4, contactList.numContactNodes()),
                    Arguments.of(2, contactList.numContacts()));
        }
    }

    private static Contact makeContact(String name, String number) {
        Contact contact = new Contact(name, number);
        return contact;
    }

    private static ArrayList<Contact> makeContactList(Contact... contacts) {
        ArrayList<Contact> arrayList = new ArrayList<>();
        for (Contact c : contacts) {
            arrayList.add(c);
        }
        return arrayList;
    }

    static class searchAllContacts implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            // TODO Auto-generated method stub
            return Stream.of(
                    Arguments.of(makeContactList(makeContact("Irene", "4152231231"),
                            makeContact("Jen", "4151112233")),
                            contactList.searchAllContacts("415")),
                    Arguments.of(makeContactList(makeContact("Irene", "4152231231")),
                            contactList.searchAllContacts("Ire")),
                    Arguments.of(makeContactList(makeContact("Irene", "4152231231"),
                            makeContact("Jen", "4151112233")),
                            contactList.searchAllContacts("e")),
                    Arguments.of(makeContactList(), contactList.searchAllContacts("M")));
        }
    }

    static class printAllContacts implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            contactList.insert("Isabell", "5101234556");
            contactList.insert("Rey", "4157752376");
            return Stream.of(Arguments.of(makeContactList(makeContact("Irene", "4152231231"),
                    makeContact("Isabell", "5101234556"), makeContact("Jen", "4151112233"),
                    makeContact("Maria", "5105569987"), makeContact("Rey", "4157752376")),
                    contactList.printAllContacts()));
        }
    }
}
