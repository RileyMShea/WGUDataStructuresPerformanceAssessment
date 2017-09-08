/**
 * Class implementation Linked List Data Structure
 *
 * @param <T> Generic type T
 */
public class LList<T extends Person> extends Person {
    private Node firstNode; // reference to first node
    private int numberOfEntries;

    /**
     * Default Constructor to create and initialize variables for new LList
     */
    public LList() {
        clear();
    }


    /**
     * Adds a new entry to the end of this list.
     * Entries currently in the list are unaffected.
     * The list's size is increased by 1.
     *
     * @param newEntry the object to be added as a new entry
     */
    public void add(T newEntry) {
        //create a new node with newEntry as a parameter
        Node newNode = new Node(newEntry); // correctly holding Person Object inside value field of node

        //check if the Linked List has any nodes
        //if it's empty set the firstNode to the newNode
        if (isEmpty())
            firstNode = newNode;
        else // add to end of non-empty list
        {
            Node lastNode = getNodeAt(numberOfEntries); // get the Node at the end of the list
            lastNode.setNextNode(newNode); // make last node reference new node
        }
        numberOfEntries++;
    }

    /**
     * Adds a new entry at a specified position within this list.
     * Entries originally at and above the specified position
     * are at the next higher position within the list.
     * The list's size is increased by 1.
     *
     * @param newPosition an integer that specifies the desired
     *                    position of the new entry
     * @param newEntry    the object to be added as a new entry
     * @return true if the addition is successful, or
     * false if newPosition < 1, or newPosition > getLength()+1
     */
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);
            if (newPosition == 1) // case 1
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else // case 2: list is not empty
            { // and newPosition > 1
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }
            numberOfEntries++;
        } else
            isSuccessful = false;
        return isSuccessful;
    }

    /**
     * Removes an Entry based on it's hash value
     *
     * @param givenHash
     * @return
     */
    public T remove(int givenHash) {
        T result = null; // return value
        assert !isEmpty();

        //
        if (numberOfEntries == 1 && firstNode.getKey() == givenHash) // case 1: remove first entry
        {
            result = firstNode.getData(); // save entry to be removed
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
        } else // case 2: not first entry
        {
            Node nodeBefore = firstNode;
            boolean found = false;

            //Node after nextNode isn't null and the nextNode doesn't match the the hashkey
            while (nodeBefore.getNextNode() != null && found == false) {

                if (nodeBefore.getNextNode().getKey() == givenHash) {

                    //set nodeBefore's next object to point the the node after the nodeToRemove
                    Node nodeToRemove = nodeBefore.getNextNode();
                    Node nodeAfter = nodeToRemove.getNextNode();
                    nodeBefore.setNextNode(nodeAfter);

                    result = nodeToRemove.getData();// save entry to be removed
                    found = true;
                    numberOfEntries--;
                } else
                    nodeBefore = nodeBefore.getNextNode();
            }
        }
        return result; // return the removed entry or null if deletion fails
    }


    /**
     * Used to find a Person Entry in the hashTable based on its hash
     *
     * @param givenHash the hashkey of the person that needs to be looked up
     * @return the Person object in the node that's been removed
     */
    public T lookupHash(int givenHash) {
        T result = null; // return value
        boolean found = false; //determine if entry with matching hash was found
        assert !isEmpty();
        if (numberOfEntries == 1 && firstNode.getKey() == givenHash) // case 1: only one entry
        {
            result = firstNode.getData(); // save entry to be removed
            found = true;
        } else // case 2: not first entry
        {
            // current node points to first node in Linked List
            Node currentNode = firstNode;


            //check if the current Node Key matches the Lookup HashKey
            found = currentNode.getKey() == givenHash;
            //While  currentNodeKey != lookupKey  AND
            // The next node is not null
            while (found == false && currentNode.getNextNode() != null) {

                //Move to next Node
                currentNode = currentNode.getNextNode();

                //check if current node matches given hash
                found = currentNode.getKey() == givenHash;


            }

//            boolean nodeMatchesHash = currentNode.getKey() == givenHash;
//            assert (nodeMatchesHash);
            if (found)
                result = currentNode.getData();
            else {
                System.out.println("NULL ENTRY");
                result = null;
            }
        }
        return result; // return located entry, or
// null if operation fails
    }


    /**
     * Replaces the entry at a given position in this list.
     *
     * @param givenPosition an integer that indicates the position of
     *                      the entry to be replaced
     * @param newEntry      the object that will replace the entry at the
     *                      position givenPosition
     * @return true if the replacement occurs, or false if either the
     * list is empty, givenPosition *< 1, or
     * givenPosition > getLength()
     */
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            Node desiredNode = getNodeAt(givenPosition);
            desiredNode.setData(newEntry);
        } else
            isSuccessful = false;
        return isSuccessful;
    }

    /**
     * Retrieves the entry at a given position in this list.
     *
     * @param givenPosition an integer that indicates the position of
     *                      the desired entry
     * @return a reference to the indicated entry or null, if either
     * the list is empty, givenPosition < 1, or
     * givenPosition > getLength()
     */
    public T getEntry(int givenPosition) {
        T result = null; // result to return
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            result = getNodeAt(givenPosition).getData();
        }
        return result;
    }

    public T getNodeHash(T anEntry) {
        int hash = anEntry.generateHash(); // set hash to the has for the entry
        boolean found = false;
        Node currentNode = firstNode;  //start currentNode @ firstNode
        int currentNodeHash = currentNode.getKey();

        while (!found && (currentNode != null)) {
            if (hash == currentNodeHash)
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }
        return currentNode.getData();
    }

    /**
     * Sees whether this list contains a given entry.
     *
     * @param anEntry the object that is the desired entry
     * @return true if the list contains anEntry, or false if not
     */
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }
        return found;
    }

    /**
     * Gets the length of this list.
     *
     * @return the integer number of entries currently in the list
     */
    public int getLength() {
        return numberOfEntries;
    }

    /**
     * Sees whether this list is empty.
     *
     * @return result if the list is empty true, or false if not empty
     */
    public boolean isEmpty() {
        boolean result;
        if (numberOfEntries == 0) // or getLength() == 0
        {
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        }
        return result;
    }


    /**
     * Retrieves all entries that are in this list in the order in which
     * they occur in the list.
     */
    public T[] toArray() {
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }


    public final void clear() // note the final method
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    // Returns a reference to the node at a given position.
    // Precondition: List is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition) {
        assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;
// traverse the chain to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
        assert currentNode != null;
        return currentNode;
    }

    private class Node // private inner class
    {
        private int key; // HashCode for the Node i.e 456345346
        private T value; // person object
        private Node next; // link to next node

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            value = dataPortion;
            key = dataPortion.generateHash();
            next = nextNode;
        }

        private int getKey() {
            return key;
        }

        private T getData() {
            return value;
        }

        private void setData(T newData) {
            value = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }
}