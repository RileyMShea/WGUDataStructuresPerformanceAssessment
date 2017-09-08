/**
 * Class to Implement Binary Tree Data Structure
 *
 * @param <T> Generic Type T
 */
public class BinaryTree<T extends Person> extends BSTNode<T> {
    private BSTNode<T> root;

    /**
     * Default Constructor
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Check if binary Tree is Empty
     *
     * @return boolean of the check
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Getter for the root Node
     *
     * @return the root node
     */
    protected BSTNode<T> getRootNode() {
        return root;
    }

    /**
     * Setter for Root Node
     *
     * @param rootNode the node to set as root Node
     */
    protected void setRootNode(BSTNode<T> rootNode) {
        root = rootNode;
    }

    /**
     * Getter for the height
     *
     * @return
     */
    public int getHeight() {

        if (root == null)
            return 0;
        else
            return root.getHeight();
    }

    /**
     * Getter for the Number of Nodes in this Binary Tree
     *
     * @return number of nodes
     */
    public int getNumberOfNodes() {

        if (root == null)
            return 0;
        else
            return root.getNumberOfNodes();
    }
}
