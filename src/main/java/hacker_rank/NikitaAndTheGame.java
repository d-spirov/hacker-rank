package hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by dspirov on 07/10/16.
 */
public class NikitaAndTheGame {

    static int check0(long[] arr) {
        boolean zero = true;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] !=0 ) {
                zero = false;
            }
        }
        if(!zero) {
            return -1;
        } else {
            return arr.length - 1;
        }
    }

    static int solve(long[] array) {
        int k = check0(array);
        if(k > 0) {
            return k;
        }
        if(array.length == 1) {
            return 0;
        }
        List<Integer> indexes = new ArrayList<>();
        long[] sum_forward = new long[array.length];
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            sum_forward[i] = sum;
            if(sum < 0) {
                System.out.println("negative");
            }
        }
        sum = 0;
        for (int i = array.length - 1; i >= 1; i--) {
            sum += array[i];
            if(sum == sum_forward[i - 1] && array[i] != 0) {
                indexes.add(i);
            } else {
                if(sum > sum_forward[i - 1]) {
                    break;
                }
            }
        }
        //System.out.println(indexes.size());
        if(indexes.size() == 0) {
            return 0;
        } else {
            int max = Integer.MIN_VALUE;
            for (Integer i: indexes) {
                int left = solve(Arrays.copyOfRange(array, 0, i));
                int right = solve(Arrays.copyOfRange(array, i, array.length));
                int cur_max =  Math.max(left, right);
                if(cur_max  > max) {
                    max = cur_max;
                }
            }
            return max + 1;
        }

    }



    static void test() {
        int i1 = 1024 * 16;
        long[] a = new long[i1];
        Random r = new Random();
        for (int i = 0; i < i1; i++) {
            a[i] = 0;//r.nextInt(1000);
        }
        int i2 = 256;
        for (int i = 0; i < i2; i++) {
            if(i % 2 == 0) {
                a[i] = 2;
                a[i1 - 1 - i] = 2;
            }
        }
        //a[0] = 2;
        //a[1] = 2;
        System.out.println(solve(a));
    }

    public static void main(String[] args) {
        System.out.println(solve(new long[] {4, 4, 4, 4}));
        //test();
    }

}
