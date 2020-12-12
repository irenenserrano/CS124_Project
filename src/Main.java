
public class Main {

	
	
	 public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			//create a map 
			ContactList firstList = new ContactList(); 
			
			//Contact newContact0 = new Contact();
			
			//we use its given functions for insert 
			firstList.insert("Jenn","510-134-085");
			firstList.insert("Kim","510-334-915");
			firstList.insert("Jim","510-931-835");
			firstList.insert("Rudi","510-114-485");
			firstList.insert("Hale","510-914-685");

			System.out.println("Size of list: ");
			System.out.println(firstList.size());
			
			
			System.out.println("Is Kim in the list?");
			System.out.println(firstList.find("Kim"));
			
			System.out.println("We want to know if the subscript ki is in the list? Is it? ");
			System.out.println(firstList.find("Ki"));
			System.out.println(" ");
						
			
			//testing the name Hale if in list and also deleting it and looking for Hale
			System.out.println("Is Hale in the list?");
			System.out.println(firstList.find("Hale"));
			System.out.println(" ");
			System.out.println("Deleting Hale:");
			firstList.delete("510-914-685");
			System.out.println(" ");
			System.out.println("Is Hale still in the list after deletion?");
			System.out.println(firstList.find("Hale"));
			
			



			

	}

}
