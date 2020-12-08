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
    public String find(String nameORnumber) {
        if (contactList.containsKey(nameORnumber))
            return contactList.get(nameORnumber).toString();
        return "Contact not found";
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
            if(!isNumeric(s))
                System.out.println(contactList.get(s).toString());
        }
    }

    // run time: O(N)
    public void searchAllContacts(String target) {
        ArrayList<String> sortedContacts = new ArrayList<>(contactList.keySet());
        Collections.sort(sortedContacts);
        for (String s : sortedContacts) {
            if (s.contains(target))
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

