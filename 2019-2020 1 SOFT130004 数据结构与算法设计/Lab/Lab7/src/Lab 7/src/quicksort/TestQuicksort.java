package quicksort;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class TestQuicksort {

	private static final Random RANDOM = new Random();
    private static final int SIZE = 10000;

    private static int[] unsorted = null;
    private static int[] sorted = null;
    private static int[] reverse = null;

    static {
        unsorted = new int[SIZE];
        int i = 0;
        while (i < unsorted.length) {
            int j = RANDOM.nextInt(unsorted.length * 10);
            unsorted[i++] = j;
        }

        sorted = new int[SIZE];
        for (i = 0; i < sorted.length; i++)
            sorted[i] = i;

        reverse = new int[SIZE];
        for (i = (reverse.length - 1); i >= 0; i--)
            reverse[i] = (SIZE - 1) - i;
    }

    @Test
    public void testQuicksort() {
    	int[] result = Quicksort.sort(unsorted.clone());
        assertTrue("Quicksort unsorted error. result="+print(result), check(result));
        result = Quicksort.sort(sorted.clone());
        assertTrue("Quicksort sorted error. result="+print(result), check(result));
        result = Quicksort.sort(reverse.clone());
        assertTrue("Quicksort reverse error. result="+print(result), check(result));
    }

    private static final boolean check(int[] array) {
        for (int i = 1; i<array.length; i++) {
            if (array[i-1]>array[i])
                return false;
        }
        return true;
    }

    private static final String print(int[] array) {
        return print(array, 0, array.length);
    }

    private static final String print(int[] array, int start, int length) {
        final int[] clone = array.clone();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<length; i++) {
            int e = clone[start+i];
            builder.append(e+" ");
        }
        return builder.toString();
    }
}
