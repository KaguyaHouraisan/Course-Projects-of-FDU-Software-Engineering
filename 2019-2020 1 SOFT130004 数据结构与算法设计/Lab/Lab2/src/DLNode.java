package Lab_2;

public class DLNode {
    private int element;
    private DLNode prev;
    private DLNode next;

    // create a DLNode
    DLNode(int element) {
        this.element = element;
        prev = null;
        next = null;
    }

    // get the value of the element
    public int getElement() {
        return element;
    }

    // set the value of the element
    public void setElement(int element) {
        this.element = element;
    }

    // get the previous DLNode
    public DLNode getPrev() {
        return prev;
    }

    // set the previous DLNode
    public void setPrev(DLNode prev) {
        this.prev = prev;
    }

    // get the next DLNode
    public DLNode getNext() {
        return next;
    }

    // set the next DLNode
    public void setNext(DLNode next) {
        this.next = next;
    }
}
