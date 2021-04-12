package Lab_2;

public class Test2 {
    public static void main(String[] args) {
        DLNode tmp = null;
        System.out.println("Stack");
        MyStack stack = new StackAdaptor(new MyDeque(new DLNode(1)));
        System.out.println("1: " + stack.toString()); // 1：1
        stack.push(new DLNode(2));
        System.out.println("2: " + stack.toString()); // 2：1 2
        tmp = stack.pop();
        System.out.println("pop " + tmp.getElement()); // pop 2
        System.out.println("3: " + stack.toString()); // 3: 1
        tmp = stack.pop();
        System.out.println("pop " + tmp.getElement()); // pop 1
        System.out.println("4: " + stack.toString()); // 4:
        stack.push(new DLNode(3));
        System.out.println("5: " + stack.toString()); // 5: 3
        System.out.println("Queue");
        MyQueue queue = new QueueAdaptor(new MyDeque(new DLNode(1)));
        System.out.println("1: " + queue.toString()); // 1 : 1
        tmp = queue.dequeue();
        System.out.println("dequeue " + tmp.getElement()); // dequeue 1
        System.out.println("2: " + queue.toString()); // 2:
        queue.enqueue(new DLNode(2));
        System.out.println("3: " + queue.toString()); // 3: 2
        queue.enqueue(new DLNode(3));
        System.out.println("4: " + queue.toString()); // 4: 2 3
        tmp = queue.dequeue();
        System.out.println("dequeue " + tmp.getElement()); //dequeue 2
        System.out.println("5: " + queue.toString()); // 5: 3
    }
}
