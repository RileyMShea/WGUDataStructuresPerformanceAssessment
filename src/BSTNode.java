/**
 * Class Implementation of Binary Search Tree Node
 *
 * @param <T> generic Type T
 */
public class BSTNode<T extends Person> {
    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;

    private BSTNode<T> parent;


    /**
     * Default Constructor
     */
    public BSTNode() {
        this(null); // call next constructor
    }

    /**
     * constructor when just passed the data part of the node
     *
     * @param dataPortion data within the node
     */
    public BSTNode(T dataPortion) {
        this(dataPortion, null, null); // call next constructor
    }

    /**
     * Constructor when passed data left child node and right child node
     *
     * @param dataPortion object to hold in node
     * @param leftChild   node in the left child of this node
     * @param rightChild  node in right child of this node
     */
    public BSTNode(T dataPortion, BSTNode<T> leftChild,
                   BSTNode<T> rightChild) {
        this(dataPortion, leftChild, rightChild, null); // call next constructor
    }

    /**
     * Same as above but with parent node as well
     *
     * @param dataPortion object to hold in node
     * @param leftChild   node in the left child of this node
     * @param rightChild  node in right child of this node
     * @param parentNode  sets parent node link
     */
    public BSTNode(T dataPortion, BSTNode<T> leftChild,
                   BSTNode<T> rightChild, BSTNode<T> parentNode) {
        data = dataPortion;
        left = leftChild;
        right = rightChild;
        parent = parentNode;
    }

    /**
     * Getter for data
     *
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * Setter for Data
     *
     * @param newData object to set as new data
     */
    public void setData(T newData) {
        data = newData;
    }

    /**
     * Getter of left child node
     *
     * @return left child node
     */
    public BSTNode<T> getLeftChild() {
        return left;
    }

    /**
     * Setter for left Child node
     *
     * @param leftChild the node to set as left child
     */
    public void setLeftChild(BSTNode<T> leftChild) {
        left = leftChild;
    }

    /**
     * Determine if this node has a left child
     *
     * @return boolean of whether node has child
     */
    public boolean hasLeftChild() {
        return left != null;
    }

    /**
     * Getter for right Child
     *
     * @return the right child node
     */
    public BSTNode<T> getRightChild() {
        return right;
    }

    /**
     * Setter for the right child of this node
     *
     * @param rightChild the node to set as the right child of this node
     */
    public void setRightChild(BSTNode<T> rightChild) {
        right = rightChild;
    }

    /**
     * Check if this this node has a right child
     *
     * @return boolean node has right child?
     */
    public boolean hasRightChild() {
        return right != null;
    }

    /**
     * Check if this node is a leaf
     *
     * @return boolean isLeaf
     */
    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    /**
     * Public Getter for height
     *
     * @return private get height value
     */
    public int getHeight() {
        return getHeight(this); // call private getHeight
    }

    /**
     * Private getter for Height
     *
     * @param node Node to determine height of
     * @return the height of the node
     */
    private int getHeight(BSTNode<T> node) {
        int height = 0;
        if (node != null)
            height = 1 + Math.max(getHeight(node.left),
                    getHeight(node.right));
        return height;
    }

    /**
     * Getter for number of nodes
     *
     * @return the number of nodes
     */
    public int getNumberOfNodes() {
        int leftNumber = 0;
        int rightNumber = 0;

        if (left != null)
            leftNumber = left.getNumberOfNodes();

        if (right != null)
            rightNumber = right.getNumberOfNodes();

        return 1 + leftNumber + rightNumber;
    }

    public BSTNode<T> copy() {
        BSTNode<T> newRoot = new BSTNode<T>(data);
        if (left != null)
            newRoot.left = left.copy(newRoot);
        if (right != null)
            newRoot.right = right.copy(newRoot);

        newRoot.parent = parent;

        return newRoot;
    }

    public BSTNode<T> copy(BSTNode<T> p) {
        BSTNode<T> newRoot = new BSTNode<T>(data);
        if (left != null)
            newRoot.left = left.copy(newRoot);
        if (right != null)
            newRoot.right = right.copy(newRoot);

        newRoot.parent = p;
        return newRoot;
    }


    /**
     * getter for Parent node of this node
     *
     * @return parent node of this node
     */
    public BSTNode<T> getParent() {
        return parent;
    }

    /**
     * Setter for the parent of this node
     *
     * @param p the node to set as the parent
     */
    public void setParent(BSTNode<T> p) {
        parent = p;
    }

    /**
     * Determine if this node as a parent
     *
     * @return boolean hasParent
     */
    public boolean hasParent() {
        return parent != null;
    }


    /**
     * Determines if a particular person object exists in a Tree
     *
     * @param data the person to search for
     * @return true/false whether the person exists
     */
    public boolean search(T data) {
        if (data.compareTo(this.data) == 0)
            return true;
        else if (data.compareTo(this.data) < 0) {
            if (left == null)
                return false;
            else
                return left.search(data);
        } else if (data.compareTo(this.data) > 0) {
            if (right == null)
                return false;
            else
                return right.search(data);
        }
        return false;
    }

    /**
     * Will lookUp a particular Person in the Binary Search Tree by comparing data in the data portion of BSTNode
     *
     * @param data the Data to compare against the data in this node
     * @return the matching person object in the BST if found, otherwise null.
     */
    public Person lookUp(T data) {
        if (this.search(data)) {

            if (data.compareTo(this.data) == 0)
                return this.data;
            else if (data.compareTo(this.data) < 0) {
                if (left == null)
                    return null;
                else
                    return left.lookUp(data);
            } else if (data.compareTo(this.data) > 0) {
                if (right == null)
                    return null;
                else
                    return right.lookUp(data);
            }

        }
        return null;
    }


}
