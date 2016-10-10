package hacker_rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dspirov on 01/10/16.
 */
public class LarrysArray {

    static class Robot {

        Map<Integer, Integer> pos = new HashMap<>();

        int[] array;

        Robot(int[] array) {
            this.array = array;
            for (int i = 0; i < array.length; i++) {
                pos.put(array[i], i + 1);
            }
        }

        int pos(int x) {
            return pos.get(x);
        }

        void rotate(int index) {
            int a = array[index - 1];
            int b = array[index];
            int c = array[index + 1];
            array[index - 1] = b;
            array[index] = c;
            array[index + 1] = a;
            pos.put(b, index );
            pos.put(c, index + 1);
            pos.put(a, index + 2);
        }
    }

    static void solve(int[] perm) {
        int n = perm.length;
        Robot r = new Robot(perm);
        for (int i = 0; i < n - 3; i++) {

            int p = r.pos(i + 1);
            if(p > i + 1) {
                for (int j = p; j > i + 2; j--) {
                    r.rotate(j - 2);
                }
                r.rotate(i + 1);
            }
        }
        boolean can = check(r.array[n - 3], r.array[n - 2], r.array[n - 1]);
        if(can) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }


    static boolean check(int a, int b, int c) {
        return (a < b && b < c)
                || ( b < c && c < a)
                || (c < a && a < b);
    }

    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }
//
//    public static void main(String[] args) {
//        //solve(new int[] {8, 7, 6, 5, 4, 3, 2, 1});
//        solve(new int[] {1,2,3,5,4});
//    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            solve(arr);
        }

    }


}
