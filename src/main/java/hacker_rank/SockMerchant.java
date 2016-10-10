package hacker_rank;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dspirov on 08/10/16.
 */
public class SockMerchant {

    static int solve(int[] arr) {
        Set<Integer> socks = new HashSet<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if(socks.contains(arr[i])) {
                socks.remove(arr[i]);
                res++;
            } else {
                socks.add(arr[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solve(new int[] {10, 20, 20, 10, 10, 30, 50, 10, 20}));
    }

}
