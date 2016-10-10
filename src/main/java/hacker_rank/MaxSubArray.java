package hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dspirov on 07/10/16.
 */
public class MaxSubArray {

    static long pos(long[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] > 0) {
                sum += array[i];
            }
        }
        return sum;
    }

    static long solve(long[] array) {
        long max1 = Integer.MIN_VALUE;
        boolean pos = false;
        for (int i = 0; i < array.length; i++) {
            if(array[i] > 0) {
                pos = true;
                break;
            }
            if(max1 < array[i]) {
                max1 = array[i];
            }
        }
        if(!pos) {
            return max1;
        }

        long sum = 0;
        long prev = 0;
        int[] negativeIndex = new int[array.length];
        long[] negativeSum = new long[array.length];
        int index = 0;
        boolean calc = false;
        List<Integer> startPoints = new ArrayList<>(Arrays.asList(0));
        for (int i = 0; i < array.length; i++) {
            if(array[i] < 0 && prev > 0) {
                sum = 0;
                index = i;
                calc = true;
            }
            sum += array[i];
            if(sum > 0 && calc) {
                negativeIndex[index] = i + 1;
                negativeSum[index] = sum;
                calc = false;
            }


            prev = array[i];
            if(i > 0 && array[i] >=0 && array[i - 1] < 0) {
                startPoints.add(i);
            }
        }

        long max = Long.MIN_VALUE;
        for (Integer sp: startPoints) {
            sum = 0;
            for (int i = sp; i < array.length;) {
                if(array[i] < 0) {
                    if(negativeIndex[i] > 0) {
                        sum += negativeSum[i];
                        i = negativeIndex[i];
                        continue;
                    } else {
                        if(sum > max) {
                            max = sum;
                        }
                        sum = 0;
                    }
                } else {
                    sum += array[i];
                }
                i++;
            }
            if(sum > max) {
                max = sum;
            }

        }

        return max;
    }


    public static void main(String[] args) {
        long[] array = {1, 7, -1, -2, 0, 1, -1, 8};
        System.out.println(solve(array) + " " + pos(array));
        // Scanner in = new Scanner(System.in);
//        Scanner in = new Scanner(System.in);
//        int t = in.nextInt();
//        for (int i = 0; i < t; i++){
//            int n = in.nextInt();
//            long[] arr = new long[n];
//            for(int j = 0; j < n; j++) {
//                arr[j] = in.nextInt();
//            }
//            System.out.println(solve(arr) + " " + pos(arr));
//        }
    }

}
