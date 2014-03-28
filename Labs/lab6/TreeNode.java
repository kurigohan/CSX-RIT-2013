
public class TreeNode
{

    private Object value = null;
    private TreeNode left = null;
    private TreeNode right = null;
    private TreeNode parent = null;

    /**
     * Construct a tree node with specified value, with 
     * null left and right subtrees.
     * @param      initValue      value stored in the node
     */
    
    public TreeNode( Object initValue )
    {
        value = initValue;
    }

    
    /**
     * Construct a tree node with specified value, left, and right
     * subtrees.
     * @param      initValue     value stored in the node
     * @param      initLeft        left subtree of the node
     * @param      initRight      right subtree of the node
     */
    
    public TreeNode( Object initValue, TreeNode initLeft, TreeNode initRight )
    {
        value = initValue; 
        left = initLeft; 
        right = initRight;
    }



    /**
     * Construct a tree node with specified value, left, and right
     * subtrees.
     * @param      initValue     value stored in the node
     * @param      initLeft        left subtree of the node
     * @param      initRight      right subtree of the node
     * @param      initParent    parent of the node
     */
    public TreeNode( Object initValue, TreeNode initLeft, TreeNode initRight, TreeNode initParent ) {
        value = initValue;
        left = initLeft;
        right = initRight;
        parent = initParent;
    }

    /**
     * @return this tree node's value
     */
    
    public Object getValue()
    {
        return value;
    }


    /**
     * @return a reference to the parent of this node
     */
    public TreeNode getParent() {
        return parent;
    }


    /**
     * @return a reference to the left subtree of this node
     */
    
    public TreeNode getLeft()
    {
        return left;
    }

    /**
     * @return a reference to the right subtree of this node
     */
    
    public TreeNode getRight()
    {
        return right;
    }


    /**
     * Sets the value of this tree node.
     * @param      newValue      new value stored in this node
     */
    public void setValue( Object newValue )
    {
        value = newValue;
    }


    /**
     * Sets the value of the left subtree of this node.
     * @param      newParent     new  parent of this node
     */
    public void setParent( TreeNode newParent ) {
        parent = newParent;
    }

    /**
     * Sets the value of the left subtree of this node.
     * @param      newLeft     new left subtree of this node
     */
    public void setLeft( TreeNode newLeft )
    {
        left = newLeft;
    }

    /**
     * Sets the value of the right subtree of this node.
     * @param     newRight      the new right subtree of this node
     */
    public void setRight( TreeNode newRight )
    {
        right = newRight;
    }


} 

