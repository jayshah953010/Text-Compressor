
public class PairNode {
	String value;
    int frequency;
    PairNode leftChild;
    PairNode nextSibling;
    PairNode prev;
    PairNode lchild;
    PairNode rchild;
 
    /* Constructor */
    public PairNode(String v,int x)
    {
    	value = v;
        frequency = x;
        leftChild = null;
        nextSibling = null;
        prev = null;
        lchild = null;
        rchild = null;
    }
    public PairNode(String v,int x,PairNode lc,PairNode rc)
    {
    	value = v;
        frequency = x;
        leftChild = null;
        nextSibling = null;
        prev = null;
        lchild = lc;
        rchild = rc;
    }
    public boolean isLeaf()
    {
    	return lchild == null && rchild == null;
    }

}
