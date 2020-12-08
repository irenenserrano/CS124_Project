import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ContactList {
    private static int hashTableSize = 0;
    private static HashMap<String, Contact> contactList;

    public ContactList() {
        contactList = new HashMap<String, Contact>();
    }

    // run time: O(1)
    public boolean insert(String name, String number) {
        // create a new contact
        Contact contact = new Contact(name, number);
        // place contact
        contactList.put(name, contact);
        // update count
        hashTableSize++;

        return true;
    }

    // run time: O(N)
    public String find(String nameORnumber) {
        if (isNumeric(nameORnumber)) {
            for (String s : contactList.keySet()) {
                if (contactList.get(s).getNumber().equals(nameORnumber))
                    return contactList.get(s).toString();
            }
        } else {
            for (String s : contactList.keySet()) {
                if (s.equalsIgnoreCase(nameORnumber))
                    return contactList.get(s).toString();
            }
        }

        return "No contact found";
    }

    // run time: O(N)
    public boolean delete(String nameORnumber) {
        if (isNumeric(nameORnumber)) {
            for (String s : contactList.keySet()) {
                if (contactList.get(s).getNumber().equals(nameORnumber)) {
                    contactList.remove(s);
                    hashTableSize--;
                    return true;
                }
            }
        } else {
            for (String s : contactList.keySet()) {
                if (s.equalsIgnoreCase(nameORnumber)) {
                    contactList.remove(s);
                    hashTableSize--;
                    return true;
                }
            }
        }
        return false;
    }

    // run time: O(1)
    public int size() {
        return hashTableSize;
    }

    // run time: O(N log N)
    public void printAllContacts() {
        ArrayList<String> sortedContacts = new ArrayList<>(contactList.keySet());
        Collections.sort(sortedContacts);
        for (String s : sortedContacts) {
            System.out.println(contactList.get(s).toString());
        }
    }

    // run time: O(N)
    public void searchAllContacts(String target) {
        ArrayList<String> sortedContacts = new ArrayList<>(contactList.keySet());
        Collections.sort(sortedContacts);
        for (String s : sortedContacts) {
            if (s.equalsIgnoreCase(target))
                System.out.println(contactList.get(s).toString());
        }
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

