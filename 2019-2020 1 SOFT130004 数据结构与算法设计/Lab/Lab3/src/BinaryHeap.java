package Lab_3;

public class BinaryHeap {
    private int[] items;
    private int currentSize;
    private int capacity;

    //create a BinaryHeap
    public BinaryHeap(int capacity) {
        this.items = new int[capacity + 1];
        this.items[0] = 0;
        this.currentSize = 0;
        this.capacity = capacity;
    }

    public BinaryHeap(int[] items) {
        this.items = new int[capacity + 1];
        this.items[0] = 0;
        for (int i = 0; i < items.length; i++) {
            this.items[i + 1] = items[i];
        }
        this.currentSize = items.length;
        this.capacity = items.length;
    }

    //insert a element into the heap
    public void insert(int x) {
        if (currentSize >= capacity) {
            System.out.println("Heap is full. ");
        } else {
            int hole = ++currentSize;
            for (; hole > 1 && x < items[hole / 2]; hole /= 2)
                items[hole] = items[hole / 2];
            items[hole] = x;
        }
    }

    //return the minimum element
    public int findMin() throws Exception {
        return items[1];
    }

    //remove and return the minimum element
    public int deleteMin() throws Exception {
        if (isEmpty())
            return -1;
        int temp = findMin();
        items[1] = items[currentSize--];
        percolateDown(1);
        return temp;
    }

    //judge whether the heap is empty
    public boolean isEmpty() {
        return currentSize == 0;
    }

    //remove all elements
    public void makeEmpty() {
        currentSize = 0;
    }

    private void percolateDown (int hole){
        int child;
        int tmp = items[hole];
        for (; hole * 2 <= currentSize; hole = child){
            child = hole * 2;
            if (child != currentSize && items[child + 1] < items[child])
                child++;
            if (items[child] < tmp)
                items[hole] = items[child];
            else
                break;
        }
        items[hole] = tmp;
    }
}
