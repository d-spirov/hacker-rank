package hacker_rank;

/**
 * Created by dspirov on 07/10/16.
 */
public class BricksGame {

    static long max(long... x) {
        if(x[0] > x[1] && x[0] > x[2]) {
            return x[0];
        } else if(x[1] > x[0] && x[1] > x[2]) {
            return x[1];
        } else {
            return x[2];
        }
    }

    static long solve(long[] bricks) {
        long[][] sum = new long[bricks.length][3];
        for (int i = bricks.length - 1; i >= 0; i--) {
            sum[i][0] = bricks[i];
            sum[i][1] = bricks[i] + max(sum[i + 1]);
        }
        return -1;
    }

}
