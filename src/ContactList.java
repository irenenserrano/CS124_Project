import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ContactList {
    private static int HASH_TABLE_SIZE = 0;
    private static HashMap<String, Contact> CONTACT_LIST;

    public ContactList() {
        CONTACT_LIST = new HashMap<String, Contact>();
    }

    // run time: O(1)
    public boolean insert(String name, String number) {
        // create a new contact
        Contact contact = new Contact(name, number);
        // place contact
        CONTACT_LIST.put(name, contact);
        // update count
        HASH_TABLE_SIZE++;

        return true;
    }

    // run time: O(N)
    public String find(String nameORnumber) {
        if (isNumeric(nameORnumber)) {
            for (int i = 0; i < size(); i++) {
                if (CONTACT_LIST.get(i).getNumber().equals(nameORnumber))
                    return CONTACT_LIST.get(i).toString();
            }
        } else {
            for (String s : CONTACT_LIST.keySet()) {
                if (s.equalsIgnoreCase(nameORnumber))
                    return CONTACT_LIST.get(s).toString();
            }
        }

        return "No contact found";
    }

    // run time: O(N)
    public boolean delete(String nameORnumber) {
        if (isNumeric(nameORnumber)) {
            for (int i = 0; i < size(); i++) {
                if (CONTACT_LIST.get(i).getNumber().equals(nameORnumber)) {
                    CONTACT_LIST.remove(i);
                    HASH_TABLE_SIZE--;
                    return true;
                }
            }
        } else {
            for (String s : CONTACT_LIST.keySet()) {
                if (s.equalsIgnoreCase(nameORnumber)) {
                    CONTACT_LIST.remove(s);
                    HASH_TABLE_SIZE--;
                    return true;
                }
            }
        }
        return false;
    }

    // run time: O(1)
    public int size() {
        return HASH_TABLE_SIZE;
    }

    // run time: O(N log N)
    public void printAllContacts() {
        ArrayList<String> sortedContacts = new ArrayList<>(CONTACT_LIST.keySet());
        Collections.sort(sortedContacts);
        for (String s : sortedContacts) {
            CONTACT_LIST.get(s).toString();
        }
    }

    // run time: O(N)
    public void searchAllContacts(String target) {
        ArrayList<String> sortedContacts = new ArrayList<>(CONTACT_LIST.keySet());
        Collections.sort(sortedContacts);
        for (String s : sortedContacts) {
            if (s.equals(target))
                CONTACT_LIST.get(s).toString();
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

