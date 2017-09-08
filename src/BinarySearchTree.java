import java.util.Iterator;


/**
 * Class for Binary Search Tree Object
 * @param <T> generic type T
 */
public class BinarySearchTree<T> extends BinaryTree {

    /**
     * Calls BinaryTreeConstructor
     */
    public BinarySearchTree()
    {
        super();
    }

    /**
     * Calls BinaryTree Constructor with a rootNode that contains the data portion for root Entry
     * @param rootEntry
     */
    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BSTNode((Person) rootEntry));
    }

    /**
     * Prevent setTree usage so that tree remains a binary search tree
     * @param rootData data from root node
     */
    public void setTree(T rootData) // disable setTree (see Segment 25.6)
    {
        throw new UnsupportedOperationException();
    } // end setTree

    /**
     * Prevent setTree usage so that tree remains a binary search tree
     * @param rootData data from root node
     * @param leftTree left subtree
     * @param rightTree right substree
     */
    public void setTree(T rootData, BinaryTree leftTree, BinaryTree rightTree)
    {
        throw new UnsupportedOperationException();
    } // end setTree

    /**
     * Adds newEntry to the nonempty subtree rooted at rootNode.
     * @param rootNode Node @ the root of the tree
     * @param newEntry Person to be added
     * @return Entry that was added
     */
    private Person addEntry(BSTNode rootNode, Person newEntry)
    {
        assert rootNode != null;
        Person result = null;
        int comparison = newEntry.compareTo(rootNode.getData());
        if (comparison == 0)
        {
            result = rootNode.getData();
            rootNode.setData(newEntry);
        }
        else if (comparison < 0)
        {
            if (rootNode.hasLeftChild())
                result = addEntry(rootNode.getLeftChild(), newEntry);
            else
                rootNode.setLeftChild(new BSTNode(newEntry));
        }
        else
        {
            assert comparison > 0;
            if (rootNode.hasRightChild())
                result = addEntry(rootNode.getRightChild(), newEntry);
            else
                rootNode.setRightChild(new BSTNode(newEntry));
        }
        return result;
    }

    /**
     * Calls addEntry to add a person object to a node
     * @param newEntry
     * @return Person that was added
     */
    public T add(Person newEntry)
    {
        T result = null;
        if (isEmpty())
            setRootNode(new BSTNode(newEntry));
        else
            result = (T) addEntry(getRootNode(), newEntry);
        return result;
    }


    /**
     * Removes a Person from the BST
     * @param entry the person to remove
     * @return the person that was removed
     */
    public T remove(Person entry)
    {
        ReturnObject oldEntry = new ReturnObject(null);
        BSTNode newRoot = removeEntry(getRootNode(), entry,oldEntry);
        setRootNode(newRoot);
        return oldEntry.getData();
    }

    /**
     * emoves an entry from the tree rooted at a given node.
     * @param rootNode a reference to the root of a tree.
     * @param entry the object to be removed.
     * @param oldEntry contains data field of entry that was removed from tree
     * @return root node of the resulting tree; if entry matches
     */
    private BSTNode removeEntry(BSTNode rootNode,
                                               Person entry, ReturnObject oldEntry)
    {
        if (rootNode != null)
        {
            Person rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if (comparison == 0) // entry == root entry
            {
                oldEntry.setData((T) rootData);
                rootNode = removeFromRoot(rootNode);
            }
            else if (comparison < 0) // entry < root entry
            {
                BSTNode leftChild = rootNode.getLeftChild();
                BSTNode subtreeRoot = removeEntry(leftChild,entry, oldEntry);
                rootNode.setLeftChild(subtreeRoot);
            }
            else // entry > root entry
            {
                BSTNode rightChild = rootNode.getRightChild();
                rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
            }
        }
        return rootNode;
    }

    /**
     * Removes the entry in a given root node of a subtree.
     * @param rootNode the root node of the subtree.
     * @return the root node of the revised subtree.
     */
    private BSTNode removeFromRoot(BSTNode rootNode)
    {
// Case 1: rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild())
        {
            // find node with largest entry in left subtree
            BSTNode leftSubtreeRoot = rootNode.getLeftChild();
            BSTNode largestNode = findLargest(leftSubtreeRoot);
            // replace entry in root
            rootNode.setData((Person) largestNode.getData());
            // remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        }
// Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild())
            rootNode = rootNode.getRightChild();
        else
            rootNode = rootNode.getLeftChild();
// Assertion: if rootNode was a leaf, it is now null
        return rootNode;
    }

    /**
     * Finds the node containing the largest entry in a given tree.
     * @param rootNode the root node of the tree.
     * @return node containing the largest entry in the tree.
     */
    private BSTNode findLargest(BSTNode rootNode)
    {
        if (rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;
    }

    /**
     * Removes the node containing the largest entry in a given tree.
     * @param rootNode the root node of the tree.
     * @return root node of the revised tree.
     */
    private BSTNode removeLargest(BSTNode rootNode)
    {
        if (rootNode.hasRightChild())
        {
            BSTNode rightChild = rootNode.getRightChild();
            BSTNode root = removeLargest(rightChild);
            rootNode.setRightChild(root);
        }
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    }

    /**
     * Determines if an person exists in the BST
     * @param desiredObject Person to search for
     * @return whether the person was found
     */
   public boolean bstSearch(Person desiredObject){
        if(this.getRootNode() != null)
        return this.getRootNode().search(desiredObject);
        else
            return false;
   }

    /**
     * Looks up a Person based on First Name and Last Name
     * @param desiredObject The Person to object that has the first/last name to lookup
     * @return the Person Object if it was found, otherwise return null
     */
    public Person bstLookup(Person desiredObject){
        if(this.getRootNode() != null)
            return this.getRootNode().lookUp(desiredObject);
        else
            return null;
    }

    /**
     * Stripped down Node class used to return data portion of a Node
     */
    private class ReturnObject{
        T data;

        /**
         * By default an Object will be null since it should return null if an entry can't be found in the tree
         */
        public ReturnObject(){
            data = null;
        }

        /**
         * Calls  ReturnObject() if gets passed a single T param
         * @param test any object or null
         */
        public ReturnObject(T test){
            super();
        }

        /**
         * Getter for data object
         * @return the data object
         */
        public T getData() {
            return data;
        }

        /**
         * Setter for data object
         * @param data the data field to be set
         */
        public void setData(T data) {
            this.data = data;
        }
    }

}
