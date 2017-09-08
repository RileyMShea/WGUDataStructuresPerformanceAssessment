/**
 * Class for Person Data
 * @param <T> utilizes generic type T
 */
public class Person<T> implements Comparable<Person>{
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private int numBuckets = 13;

    /**
     * Default constructor
     */
    public Person(){};

    /**
     * Used to return the first and last name concatenated together and converted to uppercase for comparison or hash
     * generation
     * @return the Person's full name in all uppercase letters
     */
    public String fullNameToUpper(){
        String fullName = firstName.concat(lastName);
        return fullName.toUpperCase();
    }

    /**
     * Generate a Hashcode for Lookup Purposes and to determine which bucket to use in the HastTable
     * @return the hashcode for the Persoon based off their full name in uppercase
     */
    public int generateHash(){

        int nameHash = fullNameToUpper().hashCode();
        return nameHash;

    }

    /**
     * Generates and returns the bucket # of a Person object  by getting the hash and modding it by the number of
     * buckets.
     * @return bucket # of Person
     */
    public int getBucket(){
        int compressedHash = generateHash() % numBuckets;
        if(compressedHash<0)
            compressedHash = compressedHash + numBuckets; // maybe supposed to add numbbuckets instead of fullName?
        return compressedHash;

    }

    /**
     * Getter for Last Name
     * @return Last name of Person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for Last Name of Person
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for Email
     * @return
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Setter for Email
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Getter for Phone Number
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter For Phone Number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for First Name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Alternate Constructor when all data entries are parameters
     * @param firstName first name of person
     * @param lastName last name of person
     * @param phoneNumber phone number of person
     * @param emailAddress email address of person
     */
    public Person(String firstName, String lastName, String phoneNumber, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Alternate Constructor with just the first and last name as parameters.  Used for Lookups.
     * @param firstName first name of person
     * @param lastName last name of person
     */
    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = null;
        this.phoneNumber = null;
    }

    /**
     * Used to print this Person objects first and last name, phone number and email address
     */
    public void printPerson(){
        System.out.printf("%-20s %-20s %-15s %-25s %n",firstName,lastName,phoneNumber,emailAddress);
    }

    /**
     * Ovveride the compareTo method and compares the uppernase, concatenated full name strings with each other
     * @param o the person to compare this Person object to
     * @return the difference in the strings from a lexicographic comparison built into the String class
     */
    @Override
    public int compareTo(Person o) {
       return this.fullNameToUpper().compareTo(o.fullNameToUpper());

    }
    
}
