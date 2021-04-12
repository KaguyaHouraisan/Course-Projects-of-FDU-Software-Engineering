package Lab_2;

public abstract class MyStack {
    //push a node into stack
    public abstract void push(DLNode node);
    //pop a node from stack
    public abstract DLNode pop();
    //return top node
    public abstract DLNode top();
    //return number of nodes
    public abstract int size();
    //judge whether the stack is empty
    public abstract boolean isEmpty();
    //display the content of the stack
    public abstract String toString();
}

