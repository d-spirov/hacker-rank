package hacker_rank;

import java.util.Arrays;

/**
 * Created by dspirov on 08/10/16.
 */
public class Mandragora {

    static class State {
        long p;
        long s;

        public State(long p, long s) {
            this.p = p;
            this.s = s;
        }
    }

    static long solve(long[] mandragoras) {
        Arrays.sort(mandragoras);
        State[][] exp = new State[mandragoras.length][2];
        long p = 0;
        long s = 1;
        //0 = eats
        //1 = battles
        exp[0][0] = new State(p, s + 1);
        exp[0][1] = new State(p + s * mandragoras[0], s);
        for (int i = 1; i < mandragoras.length; i++) {
            State s0 = exp[i - 1][0];
            State s1 = exp[i - 1][1];
            exp[i][0] = new State(s0.p, s0.s + 1);
            exp[i][1] = new State(s0.p + s0.s * mandragoras[i], s1.s);
        }
        return -1;

    }

}
