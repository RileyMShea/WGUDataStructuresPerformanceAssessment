import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 * Used to Test Binary Search Tree Implementation of the Address Book
 */
public class BSTAddressBook {
    public static void main(String[] args) throws IOException {

        BinarySearchTree addressSearchTree = new BinarySearchTree();

        runTestCases(addressSearchTree);

    }


    /**
     * Class to run through test cases of Binary Search Tree implementation of Address Book
     *
     * @param addressSearchTree the BinarySearch Tree to run
     * @throws IOException in case of IO error
     */
    private static void runTestCases(BinarySearchTree addressSearchTree) throws IOException {
        String action, firstName, lastName, phoneNum, email, currentLine;
        Person insertPerson, deletePerson, lookupPerson;
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("src/testCases.txt")));

            while (s.hasNextLine()) {
                action = s.next();

                switch (action) {
                    case "Insert":
                        firstName = s.next();
                        lastName = s.next();
                        phoneNum = s.next();
                        email = s.next();
                        insertPerson = new Person(firstName, lastName, phoneNum, email);
                        System.out.println("**Inserting " + firstName + " " + lastName + "**");
                        insertPerson.printPerson();
                        addressSearchTree.add(insertPerson);
                        System.out.println("-----------------------------------------------------------" +
                                "---------------------------------------------------------");
                        break;
                    case "Delete":
                        firstName = s.next();
                        lastName = s.next();
                        System.out.println("**Deleting " + firstName + " " + lastName + "**");
                        deletePerson = new Person(firstName, lastName);
                        addressSearchTree.remove(deletePerson);
                        System.out.println("-----------------------------------------------------------" +
                                "---------------------------------------------------------");
                        break;
                    case "Lookup":
                        firstName = s.next();
                        lastName = s.next();
                        System.out.println("**Looking up " + firstName + " " + lastName + "**");
                        lookupPerson = new Person(firstName, lastName);
                        Person foundPerson = addressSearchTree.bstLookup(lookupPerson);

                        if (foundPerson != null)
                            foundPerson.printPerson();
                        else if (foundPerson == null)
                            System.out.println(firstName + " " + lastName + " not found!");
                        System.out.println("-----------------------------------------------------------" +
                                "---------------------------------------------------------");
                        break;
                    default:
                        if (s.hasNextLine()) currentLine = s.nextLine();
                        break;
                }
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }


    }
}
