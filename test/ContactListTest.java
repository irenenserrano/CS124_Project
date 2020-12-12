import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class ContactListTest<AnyType extends Comparable<AnyType>> {
    private static final ContactList contactList = new ContactList();

    @Test
    public void size_equals_CorrectInitialSizes() {
        contactList.insert("Irene", "4152231231");
        assertEquals(5, contactList.size());
        assertEquals(2, contactList.numContactNodes());
        assertEquals(1, contactList.numContacts());
    }

    @Test
    public void size_equals_UpdatedSizesAfterRehash() {
        contactList.insert("Jen", "4151112233");
        assertEquals(11, contactList.size());
        assertEquals(4, contactList.numContactNodes());
        assertEquals(2, contactList.numContacts());
    }


    @ParameterizedTest
    @ArgumentsSource(searchAllContacts_SizeAndValues.class)
    public void searchAllContacts_equals_ActualReturnMeetsExpected(AnyType expected,
            AnyType actual) {
        // refer to class to see test values
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ArgumentsSource(printAllContacts.class)
    public void printAllContacts_equals_ReturnedContactsinOrder(AnyType expected, AnyType actual) {
        contactList.insert("Isabell", "5101234556");
        contactList.insert("Maria", "5105569987");
        assertEquals(expected, actual);
        assertNotEquals(makeContactList(makeContact("Isabell", "5101234556"),
                makeContact("Jen", "4151112233"), makeContact("Maria", "5105569987"),
                makeContact("Irene", "4152231231")), contactList.printAllContacts());
    }

    @Test
    public void size_equals_CorrectSizesAfterDelete() {
        contactList.delete("Irene");
        assertEquals(11, contactList.size());
        assertEquals(6, contactList.numContactNodes());
        assertEquals(3, contactList.numContacts());
        
        contactList.insert("Evolet", "510-457-7760");
        assertEquals(11, contactList.size());
        assertEquals(8, contactList.numContactNodes());
        assertEquals(4, contactList.numContacts());
        
    }
    
    @Test
    public void size_equals_CorrectSizesAfterDeletePart2() {
        contactList.delete("4151112233");
        assertEquals(11, contactList.size());
        assertEquals(6, contactList.numContactNodes());
        assertEquals(3, contactList.numContacts());
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

    public static class searchAllContacts_SizeAndValues implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            // TODO Auto-generated method stub
            return Stream.of(Arguments.of(2, contactList.searchAllContacts("415").size()),
                    Arguments.of(1, contactList.searchAllContacts("Ire").size()),
                    Arguments.of(2, contactList.searchAllContacts("e").size()),
                    Arguments.of(0, contactList.searchAllContacts("M").size()),
                    Arguments.of(
                            makeContactList(makeContact("Irene", "4152231231"),
                                    makeContact("Jen", "4151112233")),
                            contactList.searchAllContacts("415")),
                    Arguments.of(makeContactList(makeContact("Irene", "4152231231")),
                            contactList.searchAllContacts("Ire")),
                    Arguments.of(
                            makeContactList(makeContact("Irene", "4152231231"),
                                    makeContact("Jen", "4151112233")),
                            contactList.searchAllContacts("e")),
                    Arguments.of(makeContactList(), contactList.searchAllContacts("M")),
                    Arguments.of(
                            makeContactList(makeContact("Irene", "4152231231"),
                                    makeContact("Jen", "4151112233")),
                            contactList.printAllContacts()));
        }
    }

    public static class printAllContacts implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext arg0)
                throws Exception {
            // TODO Auto-generated method stub
            return Stream.of(
                    Arguments.of(makeContactList(makeContact("Irene", "4152231231"),
                            makeContact("Isabell", "5101234556"),
                            makeContact("Jen", "4151112233"), makeContact("Maria", "5105569987")),
                            contactList.printAllContacts()),
                    Arguments.of(
                            makeContactList(makeContact("Irene", "4152231231"),
                                    makeContact("Isabell", "5101234556"),
                                    makeContact("Jen", "4151112233"),
                                    makeContact("Maria", "5105569987")).size(),
                            contactList.printAllContacts().size()));
        }
    }
}
