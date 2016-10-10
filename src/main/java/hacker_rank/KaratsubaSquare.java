package hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by dspirov on 02/10/16.
 */
public class KaratsubaSquare {


    static int chit = 0;
    static int cmiss = 0;

    static Map<String, String> sMapOpt = new HashMap<>();


    static Map<Integer, String> zCache = new HashMap<>();

    static String zeroes(int x) {
        String s = zCache.get(x);
        if(s != null) {
            return s;
        } else {
            StringBuilder res = new StringBuilder(x);
            for (int i = 0; i < x; i++) {
                res.append("0");
            }
            zCache.put(x, res.toString());
            return res.toString();
        }
    }


    static String mult(String s1, String s2) {
        StringBuilder res = new StringBuilder("0");
        List<String> all = new ArrayList<>();
        for (int i = s1.length() - 1; i >= 0; i--) {
            int x = s1.charAt(i) - '0';
            //String curr = singleMult(s2, x) + zeroes(s1.length() - 1 - i);
            StringBuilder curr = new StringBuilder(s2.length() + 1 + s1.length());
            curr.append(singleMult(s2, x)).append(zeroes(s1.length() - 1 - i));
            all.add(curr.toString());
            //res = new StringBuilder(plus(curr, res.toString()));
        }


        return plus(all);
    }

    static String singleMult(String b, int x) {
        //System.out.println(chit + " " + cmiss);
        String key = b + "-" + x;
        String test = sMapOpt.get(key);
        if(test != null) {
            //System.out.println("cache hit");
            chit++;
            return test;
        } else {
            cmiss++;
            StringBuilder res = new StringBuilder(b.length());
            int prev = 0;
            for (int i = b.length() - 1; i >= 0; i--) {
                int curr = (b.charAt(i) - '0');
                int interm = curr * x + prev;
                res.insert(0, interm % 10);
                prev = interm / 10;
            }
            if (prev > 0) {
                res.insert(0, prev);
            }
            sMapOpt.put(key, res.toString());
            return res.toString();
        }
    }

    static String plus(String a1, String a2) {
        return plus(Arrays.asList(new String[] {a1, a2}));
    }

    static String plus(List<String> numbers) {
        int prev = 0;
        int max = -1;
        for(String s: numbers) {
            if(s.length() > max) max = s.length();
        }
        StringBuilder res = new StringBuilder(max*10);
        for (int i = 0; i < max; i++) {
            int curr = 0;
            for (int j = 0; j < numbers.size(); j++) {
                curr += getChar(numbers.get(j), i, max);
            }
            curr += prev;
            res.insert(0, curr % 10);
            prev = curr / 10;
        }
        if (prev > 0) {
            res.insert(0, prev);
        }
        return res.toString();

    }

    static int getChar(String s, int index, int length) {
        if(s.length() <= index) {
            return 0;
        }
        int i = s.length() - index - 1;
        return s.charAt(i) - '0';
    }

    static String karatsuba(String a, String b) {
        if(a.length() < 5 && b.length() <  5) {
            return (Integer.valueOf(a) * Integer.valueOf(b)) + "";
        }
        int amid = a.length() / 2;
        String a1 = a.substring(0, amid);
        String a0 = a.substring(amid);
        String b1 = b.substring(0, amid);
        String b0 = b.substring(amid);

        String q0 = karatsuba(a0, b0);
        String q1 = karatsuba(plus(a0, a1), plus(b0, b1));
        String q2 = karatsuba(a1, b1);

        String p1 = minus(q1, plus(q0, q2));

        return plus(q0, plus(p1 + zeroes(amid), q2 + zeroes(amid * 2)));

    }

    static String minus(String a, String b) {
        return _minus(a, b, 0);
    }

    static String _minus(String a, String b, int prev) {
        if(a.length() < 6 && b.length() < 6) {
            return(Integer.valueOf(a) - Integer.valueOf(b)) + "";
        } else if(b.length() == 0) {
            return (a.charAt(0) - '0' - prev) + "";
        } else {
            System.out.println(a + " " + b);
            if(a.length() == 0) {
                return "-" + b;
            }
            int a1 = a.charAt(a.length() - 1) - '0';
            int b1 = b.charAt(b.length() - 1) - '0';
            if(a1 >= b1) {
                return _minus(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1), 0) + (a1 - b1);
            } else {
                return _minus(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1), 1) + (10 + a1 - b1);
            }
        }
    }


    static void testMinus() {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            int a1 = r.nextInt(100);
            int a2 = r.nextInt(10);
            int m1 = a1 - a2;
            int m2 = Integer.parseInt(minus(a1 + "", a2 + ""));
            if(m1 != m2) {
                System.out.println(a1 + " " + a2 + " " + m1 + " " + m2);
            }

        }
    }

    static void modFibonacci(int t1, int t2, int n) {
        String[] fib = new String[30];
        fib[0] = t1 + "";
        fib[1] = t2 + "";

        for (int i = 2; i < n; i++) {
            fib[i] = plus(Arrays.asList(new String[] {fib[i - 2], karatsuba(fib[i - 1], fib[i - 1])}));

                //System.out.println(fib[i].length() + " " + fib[i]);

        }
        System.out.println(fib[n - 1]);
    }


    public static void main(String[] args) {
        //System.out.println(minus("9", "1"));
        //testMinus();
        //System.out.println(karatsuba("1232323123123123", "12333242342342342"));
        modFibonacci(1, 2, 16);
    }


}
