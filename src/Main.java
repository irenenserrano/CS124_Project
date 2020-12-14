
public class Main {

	 public static void main(String[] args) {
		 ContactList tester = new ContactList();
		 
		 System.out.println("Testing insert() and rehash()");
		 tester.insert("Irene","4152601231");
		 System.out.println("Hash Table Size: "+tester.size());
		 System.out.println("Number of Contacts: "+tester.numContacts());
		 System.out.println("Number of ContactNodes: "+tester.numContactNodes());
		 System.out.println();
		 
		 tester.insert("Jen","5103345667");
		 System.out.println("Hash Table Size: "+tester.size());
		 System.out.println("Number of Contacts: "+tester.numContacts());
		 System.out.println("Number of ContactNodes: "+tester.numContactNodes());
		 System.out.println();
		 
		 tester.insert("Maria", "5106678833");
		 tester.insert("Rey", "4154680663");
		 
		 System.out.println("Testing find(), searchAllContacts(), printAllContacts()");
		 System.out.println("Search for 'Irene'");
		 tester.find("Irene");
		 System.out.println();
		 System.out.println("Search for '5106678833'");
		 tester.find("5106678833");
		 System.out.println();
		 System.out.println("Search for 'Isabell'");
		 tester.find("Isabell");
		 System.out.println();
		 System.out.println("Search for '4151234578'");
		 tester.find("4151234578");
		 System.out.println();
		 System.out.println("Search All Contacts with Areacode '415'");
		 tester.searchAllContacts("415");
		 System.out.println();
		 System.out.println("Search All Contacts with Areacode '510'");
		 tester.searchAllContacts("510");
		 System.out.println();
		 System.out.println("Search All Contacts for 'Ma'");
		 tester.searchAllContacts("Ma");
		 System.out.println();
		 System.out.println("Search All Contacts with 'E' in their name");
		 tester.searchAllContacts("E");
		 System.out.println();
		 System.out.println("Print All Contacts");
		 tester.printAllContacts();
		 System.out.println();
		 
		 tester.insert("Isabell","4156879223");
		 tester.insert("John", "5108776234");
		 tester.insert("Doe", "5107883455");
		 
		 System.out.println("Testing delete() and printAllContacts()");
		 System.out.println("ContactList Starting Out: ");
		 tester.printAllContacts();
		 System.out.println("Hash Table Size: "+tester.size());
		 System.out.println("Number of Contacts: "+tester.numContacts());
		 System.out.println("Number of ContactNodes: "+tester.numContactNodes());
		 System.out.println();
		 
		 tester.delete("Isabell");
		 System.out.println("ContactList Updated: ");
		 tester.printAllContacts();
		 System.out.println("Hash Table Size: "+tester.size());
		 System.out.println("Number of Contacts: "+tester.numContacts());
		 System.out.println("Number of ContactNodes: "+tester.numContactNodes());
		 System.out.println();
		 
		 tester.delete("5108776234");
		 tester.delete("Doe");
		 System.out.println("ContactList Updated: ");
		 tester.printAllContacts();
		 System.out.println("Hash Table Size: "+tester.size());
		 System.out.println("Number of Contacts: "+tester.numContacts());
		 System.out.println("Number of ContactNodes: "+tester.numContactNodes());
		 System.out.println();
		 
		 tester.delete("5103345667");
		 System.out.println("ContactList Updated: ");
		 tester.printAllContacts();
		 System.out.println("Hash Table Size: "+tester.size());
		 System.out.println("Number of Contacts: "+tester.numContacts());
		 System.out.println("Number of ContactNodes: "+tester.numContactNodes());
		 System.out.println();
	}

}
