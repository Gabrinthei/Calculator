
/**
 * StackNode
 * 
 * @author James Sonntag
 * @version v1.0.0
 */
public class StackNode
{
    private StackNode next;
    private double data;

    /**
     * Constructor for objects of class StackNode
     */
    public StackNode(double c)
    {
        data = c;
    }

    public void setNext(StackNode node) {
        next = node;
    }
    
    public StackNode getNext() {
        return next;
    }
    
    public double getData() {
        return data;
    }
}

