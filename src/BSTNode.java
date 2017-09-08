




public class BSTNode<T extends Person>
{
    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private BSTNode<T> parent;
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public BSTNode()
    {
        this(null); // call next constructor
    } // end default constructor

    public BSTNode(T dataPortion)
    {
        this(dataPortion, null, null); // call next constructor
    } // end constructor

    public BSTNode(T dataPortion, BSTNode<T> leftChild,
                   BSTNode<T> rightChild)
    {
//-		data = dataPortion;
//-		left = leftChild;
//-		right = rightChild;
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        this(dataPortion, leftChild, rightChild, null); // call next constructor
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end constructor


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public BSTNode(T dataPortion, BSTNode<T> leftChild,
                   BSTNode<T> rightChild, BSTNode<T> parentNode)
    {
        data = dataPortion;
        left = leftChild;
        right = rightChild;
        parent = parentNode;
    } // end constructor
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public T getData()
    {
        return (T)data;
    } // end getData

    public void setData(T newData)
    {
        data = (T)newData;
    } // end setData

    public BSTNode<T> getLeftChild()
    {
        return left;
    } // end getLeftChild

    public void setLeftChild(BSTNode<T>  leftChild)
    {
        left = (BSTNode<T>)leftChild;
    } // end setLeftChild

    public boolean hasLeftChild()
    {
        return left != null;
    } // end hasLeftChild

    public BSTNode<T>  getRightChild()
    {
        return right;
    } // end getRightChild

    public void setRightChild(BSTNode<T> rightChild)
    {
        right = (BSTNode<T>)rightChild;
    } // end setRightChild

    public boolean hasRightChild()
    {
        return right != null;
    } // end hasRightChild

    public boolean isLeaf()
    {
        return (left == null) && (right == null);
    } // end isLeaf

    public int getHeight()
    {
        return getHeight(this); // call private getHeight
    } // end getHeight

    private int getHeight(BSTNode<T> node)
    {
        int height = 0;
        if (node != null)
            height = 1 + Math.max(getHeight(node.left),
                    getHeight(node.right));
        return height;
    } // end getHeight

    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;

        if (left != null)
            leftNumber = left.getNumberOfNodes();

        if (right != null)
            rightNumber = right.getNumberOfNodes();

        return 1 + leftNumber + rightNumber;
    } // end getNumberOfNodes


    public BSTNode<T> copy()
    {
        BSTNode<T> newRoot = new BSTNode<T>(data);
        if (left != null)
//-            newRoot.left = (BSTNode<T>) left.copy();
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            newRoot.left = (BSTNode<T>) left.copy(newRoot);
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        if (right != null)
//-            newRoot.right = (BSTNode<T>) right.copy();
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            newRoot.right = (BSTNode<T>) right.copy(newRoot);
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        newRoot.parent = parent;
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        return newRoot;
    } // end copy


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public BSTNode<T> copy(BSTNode<T> p)
    {
        BSTNode<T> newRoot = new BSTNode<T>(data);
        if (left != null)
            newRoot.left = (BSTNode<T>) left.copy(newRoot);
        if (right != null)
            newRoot.right = (BSTNode<T>) right.copy(newRoot);

        newRoot.parent = p;
        return newRoot;
    } // end copy


    public BSTNode<T>  getParent()
    {
        return parent;
    } // end getParent

    public void setParent(BSTNode<T>  p)
    {
        parent = (BSTNode<T>) p;
    } // end setParent

    public boolean hasParent()
    {
        return parent != null;
    } // end hasParent
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Determines if a particular person object exists in a Tree
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

    public Person lookUp(T data) {
        if(this.search(data)) {

            if (data.compareTo(this.data) == 0)
                return (Person) this.data;
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



} // end BSTNode
