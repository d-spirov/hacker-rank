package hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dspirov on 08/10/16.
 */
public class Equal {

    static int solve(int[] chocolates) {
        List<Integer> diff = new ArrayList<>();
        Arrays.sort(chocolates);
        int prev = chocolates[0];
        for (int i = 1; i < chocolates.length; i++) {
            if(chocolates[i] > prev) {
                diff.add(-1);
            }
        }
        return -1;

    }

}
