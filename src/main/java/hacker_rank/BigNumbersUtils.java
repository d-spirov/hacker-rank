package hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by dspirov on 04/10/16.
 */
public class BigNumbersUtils {

    static Map<Integer, String> zCache = new HashMap<>();

    static int chit = 0;
    static int cmiss = 0;
    static Map<String, String> sMapOpt = new HashMap<>();

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

    static void clearCache() {
        sMapOpt.clear();
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

    static String plus(String a1, String a2) {
        return plus(Arrays.asList(new String[] {a1, a2}));
    }

    static int getChar(String s, int index, int length) {
        if(s.length() <= index) {
            return 0;
        }
        int i = s.length() - index - 1;
        //System.out.println(s + " "+ index + " " + length + " " + s.charAt(i));
        return s.charAt(i) - '0';
    }

    static String _minus_recursive(String a, String b) {
        String r = _minus_recursive(a, b, 0);
        //System.out.println("minus " + a + " " + b +  "  " + r);
        return r;
    }

    static String _minus_recursive(String a, String b, int prev) {
        System.out.println(a + " " + b);
        try {
            if (a.length() < 3 && b.length() < 3) {
                if (b.length() == 0) {
                    return Integer.valueOf(a) - prev + "";
                }
                int c = (Integer.valueOf(a) - Integer.valueOf(b)) - prev;
                return c + "";
            } else if (b.length() == 0) {
                if (prev == 0) {
                    return a;
                } else {
                    return _minus_recursive(a, "" + prev, 0);
                }
            } else {
                //System.out.println(a + " " + b);
                if (a.length() == 0) {
                    return "-" + b;
                }
                int a1 = a.charAt(a.length() - 1) - '0';
                int b1 = b.charAt(b.length() - 1) - '0';
                if (a1 - prev >= b1) {
                    return _minus_recursive(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1), 0) + (a1 - b1 - prev);
                } else {
                    return _minus_recursive(a.substring(0, a.length() - 1), b.substring(0, b.length() - 1), 1) + (10 + a1 - b1 - prev);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }

    }


    static String minus(String a, String b) {
        if(a.length() < b.length()) {
            throw new IllegalStateException("wrong arguments");
        }
        int prev = 0;
        StringBuilder sb = new StringBuilder(a.length());
        for (int i = 0; i < a.length() ; i++) {

            if(b.length() - i - 1 < 0) {
                if(prev == 0) {
                    sb.insert(0, a.substring(0, a.length() - i ));
                } else {
                    sb.insert(0, minus(a.substring(0, a.length() - i), prev + ""));
                }
                break;

            }

            int a1 = a.charAt(a.length() - i - 1) - '0';

            int b1 = b.charAt(b.length() - 1 - i) - '0';
            int cur = a1 - b1 - prev;
            if (cur >= 0) {
                sb.insert(0, cur);
                prev = 0;
            } else {
                sb.insert(0, 10 + a1 - b1 - prev);
                prev = 1;
            }

        }
        return sb.toString();
    }


    static void testMinus() {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            int a2 = r.nextInt(10000);
            int a1 = a2 + r.nextInt(1000);
            int m1 = a1 - a2;
            int m2 = Integer.parseInt(minus(a1 + "", a2 + ""));
            if(m1 != m2) {
                System.out.println(a1 + " " + a2 + " " + m1 + " " + m2);
            }

        }
    }

    static void testMult() {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            int a2 = r.nextInt(10000);
            int a1 = a2 + r.nextInt(1000);
            int m1 = a1 * a2;
            int m2 = Integer.parseInt(mult(a1 + "", a2 + ""));
            if(m1 != m2) {
                System.out.println(a1 + " " + a2 + " " + m1 + " " + m2);
            }

        }
    }

    public static void main(String[] args) {
        //System.out.println(minus("92", "7"));
        //testMinus();
        //System.out.println(plus("2102538", mult("4205291590042894", "4205291590042894")));
        testMult();

    }

}
