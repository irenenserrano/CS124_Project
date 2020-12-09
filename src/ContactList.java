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
    public boolean insert(String name, String number) // no duplicates
    {
        // create new contact every time you
        // want to insert a person into the hash-map
        Contact object = new Contact(name, number);

        contactList.put(number, object);
        contactList.put(name, object);

        hashTableSize++;
        return true;
    }

    // run time: O(1)
    public boolean delete(String nameORnumber) {
        if(contactList.containsKey(nameORnumber)) {// checking if item exists
            if (isNumeric(nameORnumber)) { // deleting other instance before search instance
                contactList.remove(contactList.get(nameORnumber).getName());
            }else {
                contactList.remove(contactList.get(nameORnumber).getNumber());
            }
            contactList.remove(nameORnumber); // deleting search instance
            hashTableSize--;
            return true;
        }

        return false;
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
            if (!isNumeric(s))
                System.out.println(contactList.get(s).toString());
        }
    }

    // run time: O(N log N)
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

