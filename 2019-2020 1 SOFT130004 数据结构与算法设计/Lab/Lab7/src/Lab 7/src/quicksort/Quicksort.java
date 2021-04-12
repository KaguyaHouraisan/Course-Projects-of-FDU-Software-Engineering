package quicksort;

import java.util.Random;

public class Quicksort {
    private Quicksort() { }

    // Your code here
    public static int[] sort(int[] unsorted) {
        int[] Stack = new int[unsorted.length];
        int oi = 0, oj = unsorted.length - 1;
        int top = -1;
        int pivot, temp;
        int pivotIndex, l, r;
        Stack[++top] = oi;  Stack[++top] = oj;
        while (top > 0) {
            int j = Stack[top--];  int i = Stack[top--];
            pivotIndex = (i + j) / 2;
            pivot = unsorted[pivotIndex];
            temp = unsorted[pivotIndex];
            unsorted[pivotIndex] = unsorted[j];
            unsorted[j] = temp;
            l = i-1;  r = j;
            do {
                while (unsorted[++l] < pivot);
                while ((r!=0) && (unsorted[--r] > pivot));
                temp = unsorted[l];
                unsorted[l] = unsorted[r];
                unsorted[r] = temp;
            } while (l < r);
            temp = unsorted[l];
            unsorted[l] = unsorted[r];
            unsorted[r] = temp;
            temp = unsorted[l];
            unsorted[l] = unsorted[j];
            unsorted[j] = temp;
            if ((l - i) > 5) {
                Stack[++top] = i;
                Stack[++top] = l - 1;
            }
            if ((j - l) > 5) {
                Stack[++top] = l + 1;
                Stack[++top] = j;
            }
        }
        int current;
        for (int i = 0; i < unsorted.length - 1; i++) {
            current = unsorted[i + 1];
            int preIdx = i;
            while (preIdx >= 0 && current < unsorted[preIdx]) {
                unsorted[preIdx + 1] = unsorted[preIdx];
                preIdx--;
            }
            unsorted[preIdx + 1] = current;
        }
        return unsorted;
    }
    
    // Your code here
    public static void main(String[] args) {
        int[] unsorted = new int[100000];
        Random RANDOM = new Random();
    	for (int M = 0; M < 31; M++) {
            int k = 0;
            while (k < unsorted.length) {
                int j = RANDOM.nextInt(unsorted.length * 10);
                unsorted[k++] = j;
            }
            long startTime =  System.nanoTime();
            int[] Stack = new int[unsorted.length];
            int oi = 0, oj = unsorted.length - 1;
            int top = -1;
            int pivot, temp;
            int pivotIndex, l, r;
            Stack[++top] = oi;  Stack[++top] = oj;
            while (top > 0) {
                int j = Stack[top--];  int i = Stack[top--];
                pivotIndex = (i + j) / 2;
                pivot = unsorted[pivotIndex];
                temp = unsorted[pivotIndex];
                unsorted[pivotIndex] = unsorted[j];
                unsorted[j] = temp;
                l = i-1;  r = j;
                do {
                    while (unsorted[++l] < pivot);
                    while ((r!=0) && (unsorted[--r] > pivot));
                    temp = unsorted[l];
                    unsorted[l] = unsorted[r];
                    unsorted[r] = temp;
                } while (l < r);
                temp = unsorted[l];
                unsorted[l] = unsorted[r];
                unsorted[r] = temp;
                temp = unsorted[l];
                unsorted[l] = unsorted[j];
                unsorted[j] = temp;
                if ((l - i) > M) {
                    Stack[++top] = i;
                    Stack[++top] = l - 1;
                }
                if ((j - l) > M) {
                    Stack[++top] = l + 1;
                    Stack[++top] = j;
                }
            }
            int current;
            for (int i = 0; i < unsorted.length - 1; i++) {
                current = unsorted[i + 1];
                int preIdx = i;
                while (preIdx >= 0 && current < unsorted[preIdx]) {
                    unsorted[preIdx + 1] = unsorted[preIdx];
                    preIdx--;
                }
                unsorted[preIdx + 1] = current;
            }
            long endTime = System.nanoTime();
            System.out.println("M = " + M + ": Time is " + ((double)endTime - startTime) / 1000000 + "ms.");
        }
    }
}
