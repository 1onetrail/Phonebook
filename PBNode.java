// Phonebook Node
public class PBNode {
    String name;
    String address;
    String city;
    String phoneNumber;
    PBNode next;

    public PBNode(String name, String address, String city, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.next = null;
    }
}