import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class ContactList {
    private static int hashTableSize = 5; // initial size of table
    private static ContactNode[] hashTable; // hash table
    private static int numContacts;// contact counter
    private static int numContactNodes;

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
        numContactNodes = 0;
        for (int i = 0; i < hashTable.length; i++)
            hashTable[i] = null;
    }

    private int hashValue(String s) {
        int value = Math.abs(s.hashCode());
        int index = value % hashTable.length;
        return index;
    }

    // run time: O(1)
    public boolean insert(String name, String number) // no duplicates
    {
        // update numContacts
        numContacts++;
        int index;
        String str;
        // create a new contact, using both name and number as key
        Contact contact = new Contact(name, number);
        for (int i = 0; i < 2; i++) {
            if (i == 0)
                str = name;
            else
                str = number;
            // figure out where this cNode belongs in the hashTable
            index = hashValue(str);
            // create a new cNode
            ContactNode<String, Contact> cNode = new ContactNode<>(str, contact);
            // place into hashtable if empty or place at front of existing linked list
            if (hashTable[index] == null)
                hashTable[index] = cNode;
            else {
                // set the next of the new cNode to the next of the placeHolder
                cNode.next = hashTable[index];
                // place this new node to be the next node after placeHolder
                hashTable[index] = cNode;
            }
            // keep track of the total number of nodes
            numContactNodes++;
        }

        if ((double) numContactNodes / hashTable.length >= 0.7)
            rehash();

        return true;
    }

    private void rehash() {
        // helper objects
        ContactNode[] temp = hashTable;
        ContactNode<String, Contact> current;
        // reset global variable
        hashTableSize = nextPrime(hashTableSize);
        hashTable = new ContactNode[hashTableSize];
        numContacts = 0;
        numContactNodes = 0;
        // reinitialize hashTable
        for (int i = 0; i < hashTable.length; i++)
            hashTable[i] = null;
        // refill hashTable
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                current = temp[i];
                while (current != null) {
                    if (!isNumeric(current.key))
                        insert(current.contact.getName(), current.contact.getNumber());
                    current = current.next;
                }
            }
        }
    }

    public int nextPrime(int oldIndex) {
        BigInteger b = new BigInteger(String.valueOf(oldIndex * 2));
        int newIndex = Integer.parseInt(b.nextProbablePrime().toString());
        return newIndex;
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

    // run time: O(1)
    public int numContactNodes() {
        return numContactNodes;
    }

    // run time: O(1)
    public int numContacts() {
        return numContacts;
    }

    // run time: O(N log N)
    public ArrayList<Contact> printAllContacts() {
        ArrayList<Contact> sortedContacts = new ArrayList<>();
        ContactNode<String, Contact> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    if (!isNumeric(current.key))
                        sortedContacts.add(current.contact);
                    current = current.next;
                }
            }
        }

        Collections.sort(sortedContacts);
        for (int i = 0; i < sortedContacts.size(); i++)
            System.out.println(sortedContacts.get(i).toString());
        return sortedContacts;
    }

    // run time: O(N log N)
    public ArrayList<Contact> searchAllContacts(String target) {
        ArrayList<Contact> sortedContacts = new ArrayList<>();
        ContactNode<String, Contact> current;
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                current = hashTable[i];
                while (current != null) {
                    if (current.key.toLowerCase().contains(target.toLowerCase()))
                        sortedContacts.add(current.contact);
                    current = current.next;
                }
            }
        }

        if (sortedContacts.isEmpty())
            System.out.println("No contacts found");
        else {
            Collections.sort(sortedContacts);
            for (int i = 0; i < sortedContacts.size(); i++)
                System.out.println(sortedContacts.get(i).toString());
        }
        return sortedContacts;
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

