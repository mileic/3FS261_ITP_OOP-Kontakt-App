import java.util.Scanner; 

public class ContactList {
    public static void main(String[] args) {
        
    }

    public void newContact() {
        Scanner scan = new Scanner(System.in); 
        System.out.println("Enter name:"); 
        String newName = scan.nextLine(); 
        System.out.println("Enter surname:"); 
        String newSurname = scan.nextLine(); 
    }
}