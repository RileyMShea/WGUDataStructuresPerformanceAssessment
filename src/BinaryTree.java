//+ 
//+ Final solution for Lab 19, should be placed in tree package  

import java.util.*;

/** An implementation of the ADT Binary Tree.
 *
 * This code is from Chapter 24 of
 * Data Structures and Abstractions with Java 3/e
 *      by Carrano
 *
 * Modified to use Java's built in Stack class
 * by Charles Hoot
 //- * @version 3.0
 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 * @version 3.1 (Modified for the Solution)
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
 */



//-public class BinaryTree<T> implements BSTNode<T>
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
public class BinaryTree<T extends Person> extends BSTNode<T>
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
{
    private BSTNode<T> root;
    public BinaryTree()
    {
        root = null;
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        resetAccess();
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end default constructor

    public BinaryTree(T rootData)
    {
        root = new BSTNode<T>(rootData);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        resetAccess();
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end constructor

    public BinaryTree(T rootData, BinaryTree<T> leftTree,
                      BinaryTree<T> rightTree)
    {
        privateSetTree(rootData, leftTree, rightTree);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        resetAccess();
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end constructor

    public void setTree(T rootData)
    {
        root = (BSTNode<T>) new BSTNode<T>(rootData);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        resetAccess();
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end setTree

    public void setTree(T rootData, BSTNode<T> leftTree,
                        BSTNode<T> rightTree)
    {
        privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        resetAccess();
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end setTree




    private void privateSetTree(T rootData,
                                BinaryTree<T> leftTree, BinaryTree<T> rightTree)
    {
        root = new BSTNode<T>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty())
        {
            root.setLeftChild(leftTree.root);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
            ((BSTNode<T>)root.getLeftChild()).setParent(root);
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        }

        if ((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
            {
                root.setRightChild(rightTree.root);
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
                ((BSTNode<T>)root.getRightChild()).setParent(root);
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            }
            else
                root.setRightChild(rightTree.root.copy());
        } // end if

        if ((leftTree != null) && (this != leftTree))
            leftTree.clear();

        if ((rightTree != null) && (this != rightTree))
            rightTree.clear();

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        resetAccess();
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end privateSetTree


    public Person<T> getRootData()
    {
        Person rootData = null;
        if (root != null)
            rootData = root.getData();
        return rootData;
    } // end getRootData

    public boolean isEmpty()
    {
        return root == null;
    } // end isEmpty

    public void clear()
    {
        root = null;
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
        resetAccess();
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    } // end clear

    protected void setRootData(T rootData)
    {
        root.setData(rootData);
    } // end setRootData

    protected void setRootNode(BSTNode<T> rootNode)
    {
        root = rootNode;
    } // end setRootNode

    protected BSTNode<T> getRootNode()
    {
        return root;
    } // end getRootNode

    public int getHeight()
    {
        // Modified from Carano to return 0 if the tree is empty
        if(root== null)
            return 0;
        else
            return root.getHeight();
    } // end getHeight

    public int getNumberOfNodes()
    {
        // Modified from Carano to return 0 if the tree is empty
        if(root== null)
            return 0;
        else
            return root.getNumberOfNodes();
    } // end getNumberOfNodes

    public void inorderTraverse()
    {
        inorderTraverse(root);
    } // end inorderTraverse


    /* This has been modified from Carrano's code to use recursion to get
     * the inorder traversal.  This is a particularly nice formulation because
     * pre and post orders can be done just by reording the lines of code.
     */
    private void inorderTraverse(BSTNode<T> node)
    {
        if (node != null)
        {
            inorderTraverse(node.getLeftChild());
            System.out.println(node.getData());
            inorderTraverse(node.getRightChild());
        } // end if
    } // end inorderTraverse


    private class InorderIterator implements Iterator<T>
    {
        private Stack<BSTNode<T>> nodeStack;
        private BSTNode<T> currentNode;

        public InorderIterator()
        {
            nodeStack = new Stack<BSTNode<T>>();
            currentNode = root;
        } // end default constructor

        public boolean hasNext()
        {
            return !nodeStack.isEmpty() || (currentNode != null);
        } // end hasNext

        public T next()
        {
            BSTNode<T> nextNode = null;

            // find leftmost node with no left child
            while (currentNode != null)
            {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            } // end while

            // get leftmost node, then move to its right subtree
            if (!nodeStack.isEmpty())
            {
                nextNode =  nodeStack.pop();
                assert nextNode != null; // since nodeStack was not empty
                // before the pop
                currentNode = nextNode.getRightChild();
            }
            else
                throw new NoSuchElementException();

            return nextNode.getData();
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove

    } // end InorderIterator

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private class PostorderIterator implements Iterator<T>
    {
        Stack<BSTNode<T>> nodeStack;
        private BSTNode<T> currentNode;

        public PostorderIterator()
        {
            nodeStack = new Stack<BSTNode<T>>();
            currentNode = (BSTNode<T>) root;
            if(root != null)
                goDeep();
        } // end default constructor

        public boolean hasNext()
        {
            return (currentNode != null);
        } // end hasNext

        public T next()
        {

            BSTNode<T> thisNode = (BSTNode<T>) currentNode;

            //System.out.println("next invoked at node" + currentNode);

            if(currentNode != null)
            {
                if(!currentNode.hasParent())
                    currentNode = null;         // at the root, so we are done
                else
                {
                    //System.out.println("move to parent");
                    currentNode =  (BSTNode<T>) currentNode.getParent();
                    if (cameFromLeft(currentNode, thisNode))
                    {
                        //System.out.println("came from left, try right");
                        // go right and deep if we can
                        if(currentNode.hasRightChild())
                        {
                            //System.out.println("went right");
                            currentNode = (BSTNode<T>) currentNode.getRightChild();
                            //System.out.println("going deep");
                            goDeep();
                        }
                    }
                }
            }
            else
                throw new NoSuchElementException();

            return thisNode.getData();


        } // end next

        // go as deep as one can
        private void goDeep()
        {
            while(currentNode.hasLeftChild() || currentNode.hasRightChild())
            {
                if(currentNode.hasLeftChild())
                    currentNode = (BSTNode<T>) currentNode.getLeftChild();
                else
                    currentNode = (BSTNode<T>) currentNode.getRightChild();
                //System.out.println("Go deep visiting " + currentNode);
            }
        }

        // find the relation between the parent and the child
        private boolean cameFromLeft(BSTNode<T> parent, BSTNode<T> child)
        {
            //System.out.println("Parent is " + parent);
            //System.out.println("check child is " + child);
            //if(parent.hasLeftChild())
            //    System.out.println("left child is " + parent.getLeftChild());
            //if(parent.hasRightChild())
            //    System.out.println("right child is " + parent.getRightChild());                
            return (parent.hasLeftChild() && child == parent.getLeftChild());
        }

        // find the relation between the parent and the child
        private boolean cameFromRight(BSTNode<T> parent, BSTNode<T> child)
        {
            return (parent.hasRightChild() && child == parent.getRightChild());
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove

    } // end PostorderIterator





    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    /* Create an inorder iterator
    * @return the iterator
    */
    public Iterator<T> getInorderIterator()
    {
        return new InorderIterator();
    }


    //-// Only the one iterator will be implemented by this code
    //+// Only inorder and postorder iterators are supported by this code
    public Iterator<T> getPreorderIterator()
    {
        throw new RuntimeException("Pre order iterators not yet supported by this class");
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    /* Create a postorder iterator
     * @return the iterator
     */
    public Iterator<T> getPostorderIterator()
    {
        return new PostorderIterator();
    }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//-    public Iterator<T> getPostorderIterator()
//-    {
//-        throw new RuntimeException("Post order iterators not yet supported by this class");
//-}


    public Iterator<T> getLevelOrderIterator()
    {
        throw new RuntimeException("Level order iterators not yet supported by this class");
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>            
    // Methods for BinaryTreeAccessInterface

    BSTNode<T> current = null;

    /** Task: Gets the data in the current node.
     * @return the data object in the current node */
    public T getCurrentData()
    {
        T result = null;

        if (current != null)
            result = current.getData();
        return result;
    }

    /** Task: Determines if the current node has a left child 
     * @return true if the left child is non-null
     * */
    public boolean canAdvanceToLeft()
    {
        return (current != null && current.hasLeftChild());
    }

    /** Task: Moves the current node to the left child of
     * the current node. */
    public void advanceToLeft()
    {
        if (current != null)
            current = (BSTNode<T>) current.getLeftChild();
    }

    /** Task: Determines if the current node has a right child 
     * @return true if the right child is non-null
     * */
    public boolean canAdvanceToRight()
    {
        return (current != null && current.hasRightChild());
    }

    /** Task: Moves the current node to the right child of
     * the current node. */
    public void advanceToRight()
    {
        if (current != null)
            current = (BSTNode<T>) current.getRightChild();
    }

    /** Task: Sets the current node to the root of the tree.*/
    public void resetAccess()
    {
        current = (BSTNode<T>) root;
    }


    /** Task: Determines if the current node has a parent
     * @return true if the parent is non-null
     * */
    public boolean canGoBackToParent()
    {
        return (current != null && current.hasParent());
    }

    /** Task: Moves the current node to the parent of
     * the current node. */
    public void goBackToParent()
    {
        if (current != null)
            current = (BSTNode<T>) current.getParent();
    }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

}
