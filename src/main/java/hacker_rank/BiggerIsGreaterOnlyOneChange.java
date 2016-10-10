package hacker_rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by dspirov on 30/09/16.
 */
public class BiggerIsGreaterOnlyOneChange {


    static void solve(String input) {
        int index = -1;
        for(int i = input.length() - 1; i >= 1; i--) {
           if(input.charAt(i) > input.charAt(i - 1)) {
               index = i - 1;
               break;
           }
        }
        if(index > -1) {
            char c = input.charAt(index);
            int indexSmallest = findSmallestBigger(c, input, index);
            if(indexSmallest > -1) {
                StringBuilder sb = new StringBuilder(input);
                sb.setCharAt(index, input.charAt(indexSmallest));
                sb.setCharAt(indexSmallest, input.charAt(index));
                String res = sb.toString().substring(0, index + 1) + sort(sb.toString().substring(index + 1));
                System.out.println(res);
                return;
            }
        }
        System.out.println("no answer");
    }

    static String sort(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    static int findSmallestBigger(char c, String input, int toIndex) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for(int i = input.length() - 1; i > toIndex; i--) {
            if(input.charAt(i) > c && input.charAt(i) - c < min) {
                min = input.charAt(i) - c;
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        String[] words = new String[t];
        in.nextLine();
        for (int i = 0; i < words.length; i++){
            words[i] = in.nextLine();
        }
        for(String w: words) {
            solve(w);
        }

    }

}
