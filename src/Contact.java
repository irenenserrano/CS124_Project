public class Contact implements Comparable<Contact> {
    // big concept: we have a single table compromised of a set number of slots that will hold individual LinkedLists
    // The LinkLists will be comprised of individual nodes that hold a Map with a designated Key:Value pair
    // the single table will be our Contact List, and the individual LinkedLists will be a chain of Contacts, that
    // hold Names(Key) and Numbers(Value)
    // |   |   |   |   |   |   |   |   |   |  - Contact List
    //   0   0   0   0   0   0   0   0   0    - The beginning of every LinkLists will have an empty node
    //   -   -   C   -   C   C   -   -   -    - all other subsequent node will be a Hash Node C
    //   -   -   C   -   C   -   -   -   -
    //   -   -   C   -   -   -   -   -   -
    // in order to figure out what position a newly made Contact should belong to, we will need a hashValue function
    // in order to insert a newly made contact, we need to get the hashValue, determine if there is an existing LL,
    //     check the LL to see if there is already a Contact with the same info, and then insert accordingly
    // in order to find a contact, we will need to retrieve the hashValue, using the same method that creates the
    //     hashValue, which will give us the Contact List index that the Contact is saved in. Then we check each node
    //     in the LL that is associated with the Contact List index and return the Contact
    // in order to remove a contact, we will need to retrieve the hashValue, using the same method that creates the
    //     hashValue, which will give us the Contact List index and then "erase" the node

    private String name;// this is the key
    private String number;// this is the value

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String modifyNumber(String number) {
        return String.valueOf(number).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");

    }

    @Override
    public String toString() {
        return "Name: " + name + "\nNumber: " + modifyNumber(this.number);
    }

    public int compareTo(Contact otherContact) {
        return name.compareTo(otherContact.getName()) + number.compareTo(otherContact.getNumber());
    }


}
