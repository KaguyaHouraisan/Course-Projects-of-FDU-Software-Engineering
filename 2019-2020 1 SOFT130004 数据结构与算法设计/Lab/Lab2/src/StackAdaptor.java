package Lab_2;

public class StackAdaptor extends MyStack {
    private MyDeque myDeque;

    public StackAdaptor(MyDeque myDeque) {
        this.myDeque = myDeque;
    }

    public void push(DLNode node) {
        myDeque.insertLast(node);
    }

    public DLNode pop() {
        DLNode top = myDeque.last();
        myDeque.removeLast();
        return top;
    }

    public DLNode top() {
        return myDeque.last();
    }

    public int size() {
        return myDeque.size();
    }

    public boolean isEmpty() {
        return myDeque.isEmpty();
    }

    public String toString() {
        return myDeque.toString();
    }
}
