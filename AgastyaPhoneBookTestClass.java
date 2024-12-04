// Assignment 2 - Phone Book
// Agastya Terrenz Aquila Mamahit
// Date: 10/29/2024
// CS: 145
// ITERATION/VERSION: 2.3

// NOTE: MIGHT BE CRASH PRONE
// CREDIT: TO MY FRIEND FOR HELPING ME

import java.util.*;
public class AgastyaPhoneBookTestClass { // This assignment's main purpose is to function as a Phone Book.
    private static String input;        // To be able to add, modify, delete, print, and switch regional/city of an entry/contact into the Phone Book.
    private static char selection;
    private static boolean exit;

    public static void  main  (String [] args){ // The main method, to do a while loop so that the program runs until
                                                // the user decides to quit.
        do {
            selection = menu();
            menuSelection();
            System.out.println();
        } while (!exit);
    } // end main

    public static char menu() { // Acting as the menu of the entire program allowing the user to choose between different options.
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the Phonebook program!");
        System.out.println("This program will create a list of contacts for you once you've added entries onto it.");
        System.out.println("You can print the Phonebook into a .txt file once completed!");
        System.out.println("The commands for the Phonebook are below:");
        System.out.println();
        System.out.println("A to add a new entry to the Phonebook.");
        System.out.println("F to find an entry/contact in the Phonebook using their number.");
        System.out.println("M to modify an existing entry in the Phonebook.");
        System.out.println("D to delete an existing entry in the Phonebook using their last name.");
        System.out.println("V to view all the entries in Phonebook.");
        System.out.println("P to print all the entries in Phonebook into a .txt file.");
        System.out.println("Q to exit the program, all changes will be immediately printed and saved into a .txt file.");
        System.out.println();

        while (true) { // To prevent the user accidentally putting the wrong option.
            try {
            input = console.nextLine().trim();
            if (input.length() == 1) {
                selection = Character.toUpperCase(input.charAt(0));
                break;
            } else {
                System.out.println("Please enter a valid command.");
              }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
           }
    }
        return selection;
    } // end menu
    public static boolean menuSelection() { // To call methods so the program would run accordingly with the given option.
        PhonebookManager phonebook = new PhonebookManager();
        String phoneNumber;
        String lastName;
        Scanner scanner = new Scanner(System.in);
        String filename = "Phonebook";
        switch (selection) {
            case 'A':
            System.out.print("Enter First and Last Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter City: ");
            String city = scanner.nextLine();
            System.out.print("Enter Phone Number: ");
            phoneNumber = scanner.nextLine();
                phonebook.addEntry(name, address, city, phoneNumber);
                System.out.print("Contact/Entry Created!");
                System.out.println();
                exit = false;
                break;
            case 'V':
                phonebook.viewPhonebook();
                exit = false;
                break;
            case 'D':
                System.out.print("Enter Last Name to Delete: ");
                lastName = scanner.next();
                phonebook.deleteEntryByLastName(lastName);
                exit = false;
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
                exit = false;
                break;
            case 'M':
                System.out.print("Enter Phone Number of entry to Modify: ");
                phoneNumber = scanner.next();
                phonebook.editEntry(phoneNumber);
                break;
            case 'P':
                phonebook.printPhonebookToFile(filename);
                exit = false;
                break;
            case 'Q':
                phonebook.printPhonebookToFile(filename);
                exit = true;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

      return exit;
    } // end selection
}