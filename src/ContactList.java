import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import ContactList.ContactNode;

public class ContactList {
    private static int hashTableSize = 3; // initial size of table
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
        // reset global variables
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

 		String str = nameORnumber;
 		
 		ContactNode<String, Contact> current;
 		ContactNode<String, Contact> prev;

 		// calling the hashvalue(str) to get a possible value
 		int possibleIndex = hashValue(str);

 		// nothing in array empty
 		if (hashTable[possibleIndex] == null)// for possible linked list that we wont be able to look inside of until
 		{
 			return false;
 		} 
 		else {
 			
 			current = hashTable[possibleIndex];
 			
 			// look into hashtable if the value is any of the keys
 			
 			while (current != null) 
 			{
 				if (hashTable[possibleIndex].key == str) 
 				{
 					
 					
 			
 					//to look for other contact Node should come first
 					prev = current;
 					
 					//decrease numContacts in the LinkedList
 					numContacts--;
 					
 					//look for the other contactNode with same object
 					
 					if (isNumeric(str) == true)
 					{
 						//i would need to delete the string name with object next 
 						delete(current.contact.getName());
 						
 					}
 					
 					return true;
 				} 
 				
 				current = current.next;
 			}//endofwhile

 		}
 		// update numContacts;
 		numContacts--;

 		return false;
 	}

 	// run time: O(1)
 	public String find(String nameORnumber) {

 		String str = nameORnumber;
 		
 		ContactNode<String, Contact> current;
 		ContactNode<String, Contact> prev;
 		
 		// look for possible index?
 		int possibleIndex = hashValue(nameORnumber);
 		
 		// Isn't hashTable[possibleIndex].key an object not a string so how would this
 		// nothing in array empty
 		if (hashTable[possibleIndex] == null)// for possible linked list that we wont be able to look inside of until
 		{
 			
 			return  str + " is not in contact list";

 		} 
 		else {

 			current = hashTable[possibleIndex];

 			// look into hashtable if the value is any of the keys

 			while (current != null) 
 			{
 				if (hashTable[possibleIndex].key == str) 
 				{

 					return str + " is in contact list";

 				}
 				
 				current = current.next;
 				
 			}//endofwhileloop

 		}

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
    public void printAllContacts() {
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
    }

    // run time: O(N log N)
    public void searchAllContacts(String target) {
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

