package hacker_rank;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dspirov on 01/10/16.
 */
public class TwoPlussesTest {


    @Test
    public void testMark() {
        int n = 4;
        int m = 6;
        int[][] grid = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        int[][] res = TwoPlusses.mark(grid, n, m, 1, 2, 1);
        Assert.assertEquals(res[1][2], 1);
        Assert.assertEquals(res[1][3], 1);
    }

}