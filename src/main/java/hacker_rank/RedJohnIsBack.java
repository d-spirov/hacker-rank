package hacker_rank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by dspirov on 08/10/16.
 */
public class RedJohnIsBack {


    static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> primes = new ArrayList<>();
    static int max = 0;


    static int findPrimes(int n) {
        if(max > n) {
            int i = 0;
            while(primes.get(i) <= n) {

                i++;
            }
            if(i > 0 ) {
                return i;
            } else {
                return i;
            }
        } else {
            int res = primes.size();
            for (int i = Math.max(max + 1, 2); i <= n; i++) {
                if(isPrime(i)) {
                    primes.add(i);
                    res++;
                }
                max = i;
            }
            return res;
        }
    }

    static int solve(int n) {
        int x = solve_(n);
        return findPrimes(x);
    }

    static int solve_(int n) {
        if(n < 1) {
            return 0;
        } else if(n < 4) {
            return 1;
        } else if(n == 4) {
            return 2;
        } else  {
            int res = 0;
            int x1 = solve_(n - 1);
            int x2 = solve_(n - 4);
            if(x1 > 0) {
                res += x1;
            }
            if(x2 >0) {
                res += x2;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        //System.out.println(solve(23));
        //System.out.println(isPrime(4));
        System.out.println(findPrimes(5));
        System.out.println(findPrimes(5));
        System.out.println(findPrimes(4));
        System.out.println(findPrimes(25));
        System.out.println(findPrimes(1000000));
    }

}
