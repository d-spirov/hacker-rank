package hacker_rank;

import java.text.DecimalFormat;

/**
 * Created by dspirov on 29/09/16.
 */
public class PlusMinus {

    static DecimalFormat df = new DecimalFormat("#.00000");


    static void findPlusMinus(int[] input) {
        int pos = 0, neg = 0, zeroes = 0;
        for (int i = 0; i < input.length; i++) {
            int current = input[i];
            if(current == 0) {
                zeroes++;
            } else if(current < 0) {
                neg++;
            } else pos++;
        }
        System.out.println(df.format((double)pos / (double)input.length));
        System.out.println(df.format((double)neg / (double)input.length));

        System.out.println(df.format((double)zeroes / (double)input.length));

    }

    public static void main(String[] args) {
        findPlusMinus(new int[] {-4, 3, -9, 0, 4, 1});
    }

}
