package hacker_rank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Sorting1 {

    static int solve(long[] arr, long v) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == v) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int v = in.nextInt();

        int n = in.nextInt();
        long[] arr = new long[n];
        for(int j = 0; j < n; j++) {
            arr[j] = in.nextInt();
        }
        System.out.println(solve(arr, v));

    }
}