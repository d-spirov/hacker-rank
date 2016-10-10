package hacker_rank;

/**
 * Created by dspirov on 07/10/16.
 */
public class CoinChange {

    static Integer solve(int[] coins, int n) {
        if(n == 0) {
            return 1;
        } else if(n < 0) {
            return null;
        }
        Integer cnt = 0;
        for (int i = 0; i < coins.length; i++) {
            Integer cur = solve(coins, n - coins[i]);
            if(cur != null) {
                cnt += cur;
            }
        }
        if(cnt == 0) {
            cnt = null;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[] {2, 5, 3, 6}, 10));
    }

}
