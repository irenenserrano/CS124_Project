import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ContactList {
    private static int hashTableSize = 0;
    private static HashMap<String, Contact> contactList;

    public ContactList() {
        contactList = new HashMap<String, Contact>();
    }
    
    public void insert(String name, String number) // no duplicates
	{
		//create new contact every time you 
		//want to insert a person into the hash-map
		Contact object = new Contact();
		
		contactList.put(number, object );
		contactList.put(name, object );
	}
	
	public void delete(String nameORnumber)
	{//constant time
		//string is a number
		if(isNumeric(nameORnumber)==true)
		{//ex: 5103954770	
			//contains value return true or false
			if (contactList.containsValue(nameORnumber)==true)
			{//the number is in the list 
				
				contactList.remove(nameORnumber);
			}
			else
			{
				System.out.println("The number is not in the list.");
			}

		}
		//string is a name 
		if(isNumeric(nameORnumber)==false)
		{
			 
			if(contactList.containsKey(nameORnumber)==true)
			{
				//you need to remove it entirely
				contactList.remove(nameORnumber);
			}
		}
		else
		{
			System.out.print("This contact does not exist.");
		}	
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

