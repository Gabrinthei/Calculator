
/**
 * MyStack
 * 
 * @author James Sonntag
 * @version v1.0.0
 */
public class MyStack
{
    // instance variables - replace the example below with your own
    private StackNode top;

    /**
     * Constructor for objects of class MyStack
     */
    public MyStack()
    {
        // initialise instance variables
        top = null;
    }

    public void push(StackNode node) {
        if (node != null) {
            StackNode oldTop = top;
            top = node;
            top.setNext(oldTop);
        }
    }
    
    public StackNode peek() {
        return top;
    }
    
    public StackNode pop() {
        StackNode curTop = top;
        if (top != null)
            top = top.getNext();
       return curTop;
    }
}
