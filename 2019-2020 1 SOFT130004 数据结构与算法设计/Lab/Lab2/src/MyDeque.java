package Lab_2;

public class MyDeque {
    private DLNode first;

    //create a deque
    MyDeque(DLNode node) {
        first = node;
    }

    //insert a node at the beginning of deque
    public void insertFirst(DLNode node) {
        if(isEmpty()) {
            first = node;
        } else {
            DLNode temp = first;
            first = node;
            first.setNext(temp);
            temp.setPrev(first);
        }
    }

    //remove and return the first node
    public DLNode removeFirst() {
        if (isEmpty()) {
            return null;
        }else {
            DLNode temp = first;
            first = first.getNext();
            return temp;
        }
    }

    //insert a node at the end of deque
    public void insertLast(DLNode node) {
        if(isEmpty()) {
            first = node;
        }else {
            DLNode last = first;
            while(last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(node);
            node.setPrev(last);
        }
    }

    //remove and return the last node
    public DLNode removeLast() {
        if(isEmpty()) {
            return null;
        } else {
            if(first.getNext() == null) {
                DLNode temp = first;
                first = null;
                return temp;
            } else {
                DLNode last = first;
                while(last.getNext() != null) {
                    last = last.getNext();
                }
                DLNode temp = last;
                last = last.getPrev();
                last.setNext(null);
                temp.setPrev(null);
                return temp;
            }
        }
    }

    //return first node
    public DLNode first() {
        return first;
    }

    //return last node
    public DLNode last() {
        DLNode last = first;
        while(last.getNext()!=null) {
            last = last.getNext();
        }
        return last;
    }

    //return number of nodes
    public int size() {
        if(isEmpty()) {
            return 0;
        } else {
            int size = 1;
            DLNode last = first;
            while(last.getNext() != null) {
                last = last.getNext();
                size++;
            }
            return size;
        }
    }

    //judge whether the deque is empty
    public boolean isEmpty() {
        return first == null;
    }

    //display content of the deque
    public String toString() {
        String str = "";
        if(isEmpty()) {
            return str;
        }else {
            DLNode last = first;
            str = str.concat(String.valueOf(last.getElement()));
            str = str.concat(" ");
            while (last.getNext() != null) {
                last = last.getNext();
                str = str.concat(String.valueOf(last.getElement()));
                str = str.concat(" ");
            }
            return str;
        }
    }
}
