package com.Animesh.Comparators;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysMethods {
    public static void main(String[] args) {
        int[] arr = {-5, -4, -1, 5, 4, 1, 2, 3, 0, -2, 5};
        System.out.println(Arrays.toString(arr));
        int index = Arrays.binarySearch(arr, 0);
        System.out.println("The searched value is in index: " + index);
        int [] copy = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(copy));
        System.out.println(Arrays.equals(arr, copy));
        Arrays.fill(copy, 12);
        System.out.println(Arrays.toString(copy));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        Comparator<Integer> myComp = (a, b) -> Integer.compare(b, a);
    }
}
