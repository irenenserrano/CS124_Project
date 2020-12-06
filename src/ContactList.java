import java.util.HashMap;

public class ContactList {
    // big concept: we have a single table compromised of a set number of slots that will hold
    // individual LinkedLists
    // The LinkLists will be comprised of individual nodes that hold a Map with a designated
    // Key:Value pair
    // the single table will be our Contact List, and the individual LinkedLists will be a chain of
    // Contacts, that
    // hold Names(Key) and Numbers(Value)
    // | | | | | | | | | | - Contact List
    // 0 0 0 0 0 0 0 0 0 - The beginning of every LinkLists will have an empty node// EMPTY PLACEHOLDER IS OPTIONAL
    // - - C - C C - - - - all other subsequent node will be a Hash Node C
    // - - C - C - - - -
    // - - C - - - - - -
    // in order to figure out what position a newly made Contact should belong to, we will need a
    // hashValue function
    // in order to insert a newly made contact, we need to get the hashValue, determine if there is
    // an existing LL,
    // check the LL to see if there is already a Contact with the same info, and then insert
    // accordingly
    // in order to find a contact, we will need to retrieve the hashValue, using the same method
    // that creates the
    // hashValue, which will give us the Contact List index that the Contact is saved in. Then we
    // check each node
    // in the LL that is associated with the Contact List index and return the Contact
    // in order to remove a contact, we will need to retrieve the hashValue, using the same method
    // that creates the
    // hashValue, which will give us the Contact List index and then "erase" the node

    private static int HASH_TABLE_SIZE = 19;
    private static HashMap<String, Contact>[] HASH_TABLE;
    private static HashMap<String, Contact> CONTACT_LIST;
    //    private ContactNode startNode;

    public ContactList() {
        HASH_TABLE = new HashMap[HASH_TABLE_SIZE];
        CONTACT_LIST = new HashMap<String, Contact>();
    }

}

