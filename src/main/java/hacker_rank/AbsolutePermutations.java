package hacker_rank;

import java.util.Scanner;

/**
 * Created by dspirov on 30/09/16.
 */
public class AbsolutePermutations {


    static class Holder {

        int[] numbers;

        Holder(int n) {
            this.numbers = new int[n];
        }


    }

    static void solve(int n, int k) {
        if(k == 0) {
            for (int i = 0; i < n; i++) {
                System.out.print((i + 1) + " ");
            }
            System.out.println();
            return;
        }
        Holder h = new Holder(n);
        if(n % (2 * k) != 0) {
            System.out.println(-1);
            return;
        } else {
            for (int i = 0; i < n / (2 * k); i++) {
                swap(h, i * 2 * k, k);
            }
            for (int i = 0; i < h.numbers.length; i++) {
                System.out.print(h.numbers[i] + " ");
            }
            System.out.println();
        }

    }

    static void swap(Holder h, int start, int k) {
        for (int i = start; i < start + k; i++) {
            h.numbers[i + k] = i + 1;
            h.numbers[i] = k + i + 1;
        }
    }

//
//    public static void main(String[] args) {
//        solve(2, 1);
//    }
//



}
