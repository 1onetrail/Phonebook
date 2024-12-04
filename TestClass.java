import java.util.*;

public class TestClass { // To test the Phonebook with pre-made entries
    public static void main(String[] args) { // To enter pre-made entries into the phonebook to be then later used for testing each method.
        PhonebookManager phonebook = new PhonebookManager();
        String phoneNumber;
        String lastName;
        Scanner scanner = new Scanner(System.in);
        String filename = "Phonebook";
        phonebook.addEntry("John Doe", "123 Main St", "Bellingham", "5551234567");
        phonebook.addEntry("Jane Doe", "456 Submain Ave", "Seattle", "5552345678");
        phonebook.addEntry("Alice Malice", "420 Free Rd", "Bellingham", "5553456789");
        System.out.println("(V)iew, (M)odify, (P)rint, (F)ind, or (D)elete?");
        Scanner console = new Scanner(System.in);
        String input = console.nextLine().trim();
        char selection = Character.toUpperCase(input.charAt(0));
    switch (selection) {
        case 'V':
            phonebook.viewPhonebook();
            break;
        case 'D':
            System.out.print("Enter Last Name to Delete: ");
            lastName = scanner.next();
            phonebook.deleteEntryByLastName(lastName);
            break;
        case 'F':
            System.out.print("Enter Phone Number to Find: ");
            phoneNumber = scanner.next();
            PBNode foundEntry = phonebook.findEntryByPhoneNumber(phoneNumber);
            if (foundEntry != null) {
                System.out.println("Found Entry:");
                System.out.println("Name: " + foundEntry.name);
                System.out.println("Address: " + foundEntry.address);
                System.out.println("City: " + foundEntry.city);
                System.out.println("Phone Number: " + foundEntry.phoneNumber);
                System.out.println();
            }
            break;
        case 'M':
            System.out.print("Enter Phone Number of entry to Modify: ");
            phoneNumber = scanner.next();
            phonebook.editEntry(phoneNumber);
            break;
        case 'P':
            phonebook.printPhonebookToFile(filename);
            break;
        default:
            System.out.println("Invalid choice. Please try again.");
    }
}
}