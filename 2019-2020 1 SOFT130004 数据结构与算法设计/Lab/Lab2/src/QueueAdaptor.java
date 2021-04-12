package Lab_2;

public class QueueAdaptor extends MyQueue {
    private MyDeque myDeque;

    public QueueAdaptor(MyDeque myDeque) {
        this.myDeque = myDeque;
    }

    public void enqueue(DLNode node) {
        myDeque.insertLast(node);
    }

    public DLNode dequeue() {
        return myDeque.removeFirst();
    }

    public DLNode front() {
        return myDeque.first();
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
