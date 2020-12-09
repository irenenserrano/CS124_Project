import java.util.Objects;

public class ContactList {
    private static int hashTableSize = 19; // initial size of table
    private static ContactNode[] hashTable; // hash table
    private ContactNode head;
    private int numContacts = 0;// contact counter

    private class ContactNode {
        public String key;
        public Contact value;
        public ContactNode next;
    }

    public ContactList() {
        hashTable = new ContactNode[hashTableSize];
        // when creating a new hash table, the reference to a ContactNode should be null
    }

    private int hashValue(String s) {
        int value = Objects.hash(s.charAt(0));
        int index = value % hashTableSize;
        return index;
    }

    // run time: O(1)
    public boolean insert(String name, String number) // no duplicates
    {
        return true;
    }

    // run time: O(1)
    public boolean delete(String nameORnumber) {
        return false;
    }

    // run time: O(1)
    public String find(String nameORnumber) {

        return "Contact not found";
    }

    // run time: O(1)
    public int size() {
        return hashTableSize;
    }

    // run time: O(N log N)
    public void printAllContacts() {

    }

    // run time: O(N log N)
    public void searchAllContacts(String target) {

    }

    private static boolean isNumeric(String strNum) {
        try {
            long number = Long.parseLong(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }



}

