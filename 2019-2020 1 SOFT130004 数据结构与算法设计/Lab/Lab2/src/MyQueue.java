package Lab_2;

public abstract class MyQueue {
    //enqueue a node
    public abstract void enqueue(DLNode node);
    //dequeue a node
    public abstract DLNode dequeue();
    //return front node of queue
    public abstract DLNode front();
    //return number of nodes
    public abstract int size();
    //judge whether the queue is empty
    public abstract boolean isEmpty();
    //display the content of the stack
    public abstract String toString();
}
