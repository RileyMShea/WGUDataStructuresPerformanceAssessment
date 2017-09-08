import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to implement online phone book using Hash Table Data Structure
 */
public class AddressBook {
    public static void main(String[] args) throws IOException {

        HashTable<LList> bucketHash = new HashTable<>(); // initialize a hashTable that has an array of size 13(0-to-12)

        try {
            runTestCases(bucketHash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to run through test cases
     *
     * @param bucketHash takes in a BucketHash Data Structure Object which holds the Person entries
     * @throws IOException in case of IO error
     */
    public static void runTestCases(HashTable bucketHash) throws IOException {
        String action, firstName, lastName, phoneNum, email, currentLine;
        Person insertPerson;
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
                        bucketHash.add(insertPerson);
                        System.out.println("-----------------------------------------------------------" +
                                "---------------------------------------------------------");
                        break;
                    case "Delete":
                        firstName = s.next();
                        lastName = s.next();
                        System.out.println("**Deleting " + firstName + " " + lastName + "**");
                        bucketHash.delete(firstName, lastName);
                        System.out.println("-----------------------------------------------------------" +
                                "---------------------------------------------------------");
                        break;
                    case "Lookup":
                        firstName = s.next();
                        lastName = s.next();
                        System.out.println("**Looking up " + firstName + " " + lastName + "**");
                        Person lookUpPerson = bucketHash.lookUp(firstName, lastName);

                        if (lookUpPerson != null)
                            lookUpPerson.printPerson();
                        else if (lookUpPerson == null)
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
