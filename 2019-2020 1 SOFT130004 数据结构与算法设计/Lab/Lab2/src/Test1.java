package Lab_2;

public class Test1 {
    public static void main(String[] args) {
        DLNode tmp = null;
        System.out.println("Deque");
        MyDeque myDeque = new MyDeque(new DLNode(1));
        System.out.println("1: " + myDeque.toString()); // 1: 1
        myDeque.insertFirst(new DLNode(2));
        System.out.println("2: " + myDeque.toString()); // 2: 2 1
        myDeque.insertLast(new DLNode(3));
        System.out.println("3: " + myDeque.toString()); // 3: 2 1 3
        tmp = myDeque.removeFirst();
        System.out.println("remove first " + tmp.getElement()); // remove first 2
        System.out.println("4: " + myDeque.toString()); // 4: 1 3
        tmp = myDeque.removeLast();
        System.out.println("remove last " + tmp.getElement()); // remove last 3
        System.out.println("5: " + myDeque.toString()); // 5: 1
    }
}
