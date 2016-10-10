package hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static hacker_rank.BigNumbersUtils.*;

/**
 * Created by dspirov on 03/10/16.
 */
public class KaratsubaFibModified {


    static void modFibonacci(int t1, int t2, int n) {
        String[] fib = new String[30];
        fib[0] = t1 + "";
        fib[1] = t2 + "";

        for (int i = 2; i < n; i++) {
            String mult_ = mult(fib[i - 1], fib[i - 1]);
            //System.out.println(mult_);
            fib[i] = plus(Arrays.asList(new String[] {fib[i - 2], mult_}));
            System.out.println(fib[i].length() + " " +  fib[i]);
            //System.out.println(fib[i].length());
            System.out.println("----------");
            clearCache();
        }
        System.out.println(fib[n - 1]);
    }



    static String mult(String s1, String s2) {

        if(s2.length() < 200) {
            return BigNumbersUtils.mult(s1, s2);
        }

        try {
            //System.out.println(" mult " + s1 + " " + s2);
            if (s1.length() < 4) {
                return Integer.parseInt(s1) * Integer.parseInt(s2) + "";
            }

            int w = s1.length() / 2 ;
            if (s2.length() < w) {
                throw new IllegalStateException("cant find w");
            }

            String a0 = s1.substring(s1.length() - w, s1.length());
            String a1 = s1.substring(0, s1.length() - w);

            String b0 = s2.substring(s2.length() - w, s2.length());
            String b1 = s2.substring(0, s2.length() - w);

            String z0 = mult(a0, b0);
            String z2 = mult(a1, b1);
            //System.out.println("a0 a1 b0 b1 " + a0 + " " + a1 + " " + b0 + " " + b1);
            String z1 = minus(mult(plus(a1, a0), plus(b1, b0)), plus(z0, z2));

            String res_ = plus(plus(z2 + zeroes(2 * w), z1 + zeroes(w)), z0);
            System.out.println(" mult " + s1 + " " + s2 + " " + res_);
            return res_;
        }catch (Exception e) {
            System.out.println(e);
            throw e;
        }

    }

    static void testMinus() {
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            int a2 = r.nextInt(100000);
            int a1 = a2 + r.nextInt(100000);
            int m1 = a1 - a2;
            int m2 = Integer.parseInt(minus(a1 + "", a2 + ""));
            if(m1 != m2) {
                System.out.println(a1 + " " + a2 + " " + m1 + " " + m2);
            }

        }
    }


    static void testFib() {
        //testMinus();
        long t = System.currentTimeMillis();
        modFibonacci(2, 2, 10);
        System.out.println(System.currentTimeMillis() - t);
        System.out.println(chit + " " + cmiss + " " + (double) chit/(double) (chit + cmiss) );
        //System.out.println(minus("82712", "82712"));//55507
        //System.out.println(mult("23467", "23467"));//55507
    }


    static void testMult() {
        System.out.println(plus("2102538", KaratsubaFibModified.mult("4205291590042894", "4205291590042894")));
    }

    public static void main(String[] args) {
        testMult();
        ///testFib();
    }


}
