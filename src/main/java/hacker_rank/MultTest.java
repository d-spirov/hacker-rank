package hacker_rank;

import java.util.Arrays;

/**
 * Created by dspirov on 05/10/16.
 */
public class MultTest {

    static void testMult() {
        String s1 = ModifiedFibonacci.mult("4205291590042894", "4205291590042894");
        String s2 = BigNumbersUtils.mult("4205291590042894", "4205291590042894");
        System.out.println(s1);
        System.out.println(s2);
    }

    static void testComp() {
        String s1 = BigNumbersUtils.plus("17684477357285491654924759895236", "2102538");
        String s2 = ModifiedFibonacci.plus(Arrays.asList("17684477357285491654924759895236", "2102538"));
        System.out.println(s1);
        System.out.println(s2);
    }

    public static void main(String[] args) {
        testComp();
    }

}
