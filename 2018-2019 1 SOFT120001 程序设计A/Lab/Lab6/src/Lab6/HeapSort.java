package Test;

import java.util.Scanner;

public class HeapSort {
    private void heapAdjust(int[] list, int index, int length) {
        int temp = list[index];
        for(int i = index * 2; i <= length; i = i * 2)
        {
            if(i < length && list[i] < list[i + 1]) i++;
            if(temp >= list[i]) break;
            list[index] = list[i];
            index = i;
        }
        list[index] = temp;
        //下沉操作
    }

    private void heapSort(int[] list, int length) {
        for (int i = length; i >= 1; i--) {
            heapAdjust(list,i,length);
        }
        //建堆

        for (int i = length; i >= 1; i--) {
            int temp = list[i];
            list[i] = list[1];
            list[1] = temp;
            heapAdjust(list,1,i - 1);
        }
        //排序
    }

    public static void main(String[] args) {
        System.out.println("Input:");
        Scanner input = new Scanner(System.in);
        HeapSort heapSort = new HeapSort();
        int length = input.nextInt();
        int[] list = new int[length + 1]; // 数组下标从1开始
        for (int i = 1; i <= length; i++) {
            list[i] = input.nextInt();
        }
        heapSort.heapSort(list, length);
        System.out.println("\nOutput:");
        for (int i = 1; i <= length; i++) System.out.print(list[i] + " ");
    }
}
