import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class ContactList {
    private static int hashTableSize = 19; // initial size of table
    private static ContactNode[] hashTable; // hash table
    private static int numContacts;// contact counter

    private class ContactNode<String, Contact> {
        public String key;
        public Contact contact;
        public ContactNode<String, Contact> next;

        public ContactNode(String key, Contact contact) {
            this.key = key;
            this.contact = contact;
        }

        @Override
        public java.lang.String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(key).append("'s Info:\n").append(contact.toString());
            return sb.toString();
        }
    }

    public ContactList() {
        hashTable = new ContactNode[hashTableSize];
        numContacts = 0;
        for (int i = 0; i < hashTable.length; i++)
            hashTable[i] = null;
    }

    private int hashValue(String s) {
        int value = Objects.hash(s.charAt(0));
        int index = value % hashTableSize;
        return index;
    }

    public void checkHashTable() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null)
                System.out.println("Empty, as it should be");
            else
                System.out.println("Items found");
        }
    }
    public void printSpecificPosition(int index) {
        ContactNode<String, Contact> current = hashTable[index];
        while(current != null) {
            System.out.println(current.toString());
            current = current.next;
        }
    }

    // run time: O(1)
    public boolean insert(String name, String number) // no duplicates
    {
        // update numContacts
        numContacts++;
        // figure out where this cNode belongs in the hashTable
        int index = hashValue(name.toLowerCase() + number);
        // create a new contact, using both name and number as key
        String str;
        Contact contact = new Contact(name, number);
        System.out.println(contact);
        for (int i = 0; i < 2; i++) {
            if (i == 0)
                str = name;
            else
                str = number;
            // create a new cNode
            ContactNode<String, Contact>cNode = new ContactNode<>(str, contact);
            // place into hashtable if empty or place at front of existing linked list
            if (hashTable[index] == null)
                hashTable[index] = cNode;
            else {
                // set the next of the new cNode to the next of the placeHolder
                cNode.next = hashTable[index];
                // place this new node to be the next node after placeHolder
                hashTable[index] = cNode;
            }

        }
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
        HashMap<String, Contact> contacts = new HashMap<>();
        ContactNode<String, Contact> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    if(!isNumeric(current.key))
                        contacts.put(current.key, current.contact);
                    current = current.next;
                }
            }
        }
        ArrayList<String> sortedContacts = new ArrayList<>(contacts.keySet());
        Collections.sort(sortedContacts);
        for(String s : sortedContacts)
            System.out.println(contacts.get(s).toString());
    }

    // run time: O(N log N)
    public void searchAllContacts(String target) {
        HashMap<String, Contact> contacts = new HashMap<>();
        ContactNode<String, Contact> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    if(current.key.toLowerCase().contains(target.toLowerCase()))
                        contacts.put(current.key, current.contact);
                    current = current.next;
                }
            }
        }
        ArrayList<String> sortedContacts = new ArrayList<>(contacts.keySet());
        Collections.sort(sortedContacts);
        for(String s : sortedContacts)
            System.out.println(contacts.get(s).toString());
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

