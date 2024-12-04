
// PhonebookManager.java
import java.io.*;
import java.util.*;

public class PhonebookManager { // This class serves as the PhonebookManager class
    private PBNode head;

    public PhonebookManager() { 
        head = null;
    }

    public void addEntry(String name, String address, String city, String phoneNumber) { // Add an entry into the Phonebook
        PBNode newNode = new PBNode(name, address, city, phoneNumber);
        if (head == null) {
            head = newNode;
        } else {
            PBNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void sortByName() { // Sorts the phonebook by alphabetic order using names.
        if (head == null || head.next == null) {
            return;
        }

        PBNode current = head;
        PBNode next;
        boolean swapped;

        do {
            swapped = false;
            current = head;
            next = current.next;

            while (next != null) {
                String[] currentParts = current.name.split(" ");
                String[] nextParts = next.name.split(" ");
                String currentLastName = currentParts[currentParts.length - 1];
                String nextLastName = nextParts[nextParts.length - 1];

                if (currentLastName.compareToIgnoreCase(nextLastName) > 0) {
                    String tempName = current.name;
                    String tempAddress = current.address;
                    String tempCity = current.city;
                    String tempPhone = current.phoneNumber;

                    current.name = next.name;
                    current.address = next.address;
                    current.city = next.city;
                    current.phoneNumber = next.phoneNumber;

                    next.name = tempName;
                    next.address = tempAddress;
                    next.city = tempCity;
                    next.phoneNumber = tempPhone;

                    swapped = true;
                }
                current = next;
                next = next.next;
            }
        } while (swapped);
    }

    public void deleteEntryByLastName(String lastName) { // Deletes an entry using the last name of an entry.
        if (head == null) {
            System.out.println("Phonebook is empty.");
            return;
        }

        PBNode current = head;
        PBNode previous = null;

        while (current != null) {
            String[] nameParts = current.name.split(" ");
            String currentLastName = nameParts[nameParts.length - 1];

            if (currentLastName.equalsIgnoreCase(lastName)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                System.out.println("Entry deleted successfully.");
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Entry with last name '" + lastName + "' not found.");
    }

    public PBNode findEntryByPhoneNumber(String phoneNumber) { // Finds an entry using a Phone Number. 
        if (head == null) {
            System.out.println("Phonebook is empty.");
            return null;
        }

        PBNode current = head;
        while (current != null) {
            if (current.phoneNumber.equals(phoneNumber)) {
                return current;
            }
            current = current.next;
        }
        System.out.println("Entry with phone number '" + phoneNumber + "' not found.");
        return null;
    }

    public void viewPhonebook() { // To view the Phonebook.
        if (head == null) {
            System.out.println("Phonebook is empty.");
            return;
        }
        sortByName();
        PBNode current = head;
        while (current != null) {
            System.out.println("Name: " + current.name);
            System.out.println("Address: " + current.address);
            System.out.println("City: " + current.city);
            System.out.println("Phone Number: " + current.phoneNumber);
            System.out.println("---------------------");
            current = current.next;
        }
    }

    public void printPhonebookToFile(String filename) { // To print the Phonebook into a txt file.
        if (head == null) {
            System.out.println("Phonebook is empty.");
            return;
        }

        try (FileWriter writer = new FileWriter(filename)) {
            PBNode current = head;
            while (current != null) {
                writer.write("Name: " + current.name + "\n");
                writer.write("Address: " + current.address + "\n");
                writer.write("City: " + current.city + "\n");
                writer.write("Phone Number: " + current.phoneNumber + "\n");
                writer.write("---------------------\n");
                current = current.next;
            }
            System.out.println("Phonebook saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public void editEntry(String phoneNumber) { // To edit an entry in the phonebook using a phoneNumber.
        if (head == null) {
            System.out.println("Phonebook is empty.");
            return;
        }

        PBNode current = head;
        PBNode previous = null;

        while (current != null) {
            if (current.phoneNumber.equals(phoneNumber)) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new address: ");
                String newAddress = scanner.nextLine();
                System.out.print("Enter new city: ");
                String newCity = scanner.nextLine();
                System.out.print("Enter new phone number: ");
                String newPhoneNumber = scanner.nextLine();

                current.name = newName;
                current.address = newAddress;
                current.city = newCity;
                current.phoneNumber = newPhoneNumber;
                System.out.println("Entry updated successfully.");
                return;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("Entry with phone number '" + phoneNumber + "' not found.");
    }
    
}