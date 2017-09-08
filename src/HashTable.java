/**
 * Class implementation of HashTable Data structure
 *
 * @param <T> Generic type T
 */
public class HashTable<T extends Person> {
    private static final int DEFAULT_CAPACITY = 13;
    private final LList[] hashArray; // an array of linked lists


    /**
     * A constructor with no parameters will call the one parameter constructor with the default capacity
     */
    public HashTable() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * Used to initialize a hashTable with a capacity different than the default capacity
     *
     * @param capacity the size to initialize the HashTable array to
     */
    public HashTable(int capacity) {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        LList[] tempArray = new LList[capacity]; // initialize a tempArray containing LList Objects
        hashArray = tempArray; // hashArray Points to temp array
    }

    /**
     * Add's the person entry to the LinkList according to it's bucketIndex
     *
     * @param personEntry
     */
    public void add(Person personEntry) {
        int index = personEntry.getBucket(); // get the index bucket to put the array into

        // if the indexBucket is empty add a new LList Object into the array
        if (hashArray[index] == null)
            hashArray[index] = new LList<Person>();

        //add the person
        hashArray[index].add(personEntry);
        return;

    }

    /**
     * Given a Person Object, find and return the person object in the HashTable
     *
     * @param personEntry The Person to be found in the hashTable
     * @return Return a Matching person in the hashTable
     */
    public T retrieve(Person personEntry) {

        //The bucket where the person should be stored
        int index = personEntry.getBucket();

        T personData = null;

        if (hashArray[index] == null)
            return personData;
        else
            personData = (T) hashArray[index].getNodeHash(personEntry); //for the bucket x get the hashNode
        return personData;
    }

    /**
     * Delete a Person Object in the hashTable
     *
     * @param firstName First Name of Person to delete from the hashtable
     * @param lastName  last name of person to delete from the hash table
     */
    public void delete(String firstName, String lastName) {
        String fullName = firstName.concat(lastName);
        int nameHash = fullName.toUpperCase().hashCode();
        int compressedHash = nameHash % DEFAULT_CAPACITY;
        if (compressedHash < 0)
            compressedHash = compressedHash + DEFAULT_CAPACITY;
        hashArray[compressedHash].remove(nameHash);
    }

    /**
     * Looks up Person in the HashTable
     *
     * @param firstName first name of person to look up
     * @param lastName  last name of person to lookup
     * @return the person object that was looked up or null if no person was found
     */
    public Person lookUp(String firstName, String lastName) {
        String fullName = firstName.concat(lastName);
        int nameHash = fullName.toUpperCase().hashCode();
        int compressedHash = nameHash % DEFAULT_CAPACITY;
        if (compressedHash < 0)
            compressedHash = compressedHash + DEFAULT_CAPACITY;
        return hashArray[compressedHash].lookupHash(nameHash);
    }

}