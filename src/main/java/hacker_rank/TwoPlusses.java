package hacker_rank;

import java.util.Arrays;

/**
 * Created by dspirov on 01/10/16.
 */
public class TwoPlusses {

    static class Cross {
        int x, y, width;

        public Cross(int x, int y, int width) {
            this.x = x;
            this.y = y;
            this.width = width;
        }

        int area() {
            return calcArea(width);
        }
    }

    static int calcArea(int width) {
        return 1 + width * 4;
    }

    static int[][] transform(String[] grid, int n, int m) {
        int[][] res = new int[grid.length][grid[0].length()];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i].charAt(j) == 'B') {
                    res[i][j] = 1;
                } else {
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }

    static void solve(String[] input) {
        int max = 0;
        int n = input.length;
        int m = input[0].length();
        int[][] grid = transform(input, n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int w = findMaxWidth(grid, i, j, n, m);
                if(w >= 0) {
                    int[][] newGrid = mark(grid, n, m, i, j, w);
                    Cross c = findMax(newGrid, n, m);
                    if(c.area() * calcArea(w) > max) {
                        max = c.area() * calcArea(w);
                    }
                }
            }
        }
        System.out.println(max);
    }

    static int[][] mark(int[][] grid, int n, int m, int x, int y, int w) {
        int[][] res = copy(grid, n, m);
        for (int i = 0; i <= w; i++) {
            res[x - i][y] = 1;
            res[x + i][y] = 1;
            res[x][y + i] = 1;
            res[x][y - i] = 1;
            res[x][y] = 1;
        }
        return res;
    }

    static int[][] copy(int[][] input, int n, int m) {
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = input[i][j];
            }
        }
        return res;
    }

    static Cross findMax(int[][] grid, int n, int m) {
        Cross res = new Cross(0, 0, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int w = findMaxWidth(grid, i, j, n, m);
                if(calcArea(w) > res.area()) {
                    res = new Cross(i, j, w);
                }
            }
        }
        return res;
    }

    static int findMaxWidth(int[][] grid, int x, int y, int n, int m) {
        int res = -1;
        if(grid[x][y] == 1) {
            return res;
        } else {
            res = 0;
            for(int i = 1; i < Math.min(n, m) && checkNeighbours(grid, x, y, n, m, i); i++) {
                res ++;
            }
        }
        return res;
    }


    static boolean checkNeighbours(int[][] grid, int x, int y, int n, int m, int offset) {
        if(x - offset < 0) return false;
        if(y - offset < 0) return false;
        if(x + offset >= n) return false;
        if(y + offset >= m) return false;
        return grid[x - offset][y] == 0 &&
                grid[x + offset][y] == 0 &&
                grid[x][y + offset] == 0 &&
                grid[x][y - offset] == 0;
    }


    public static void main(String[] args) {
//        solve(new String[] {
//                "GGGGGG",
//                "GBBBGB",
//                "GGGGGG",
//                "GGBBGB",
//                "GGGGGG"
//
//        });

        solve(new String[] {
                "BGBBGB",
                "GGGGGG",
                "BGBBGB",
                "GGGGGG",
                "BGBBGB",
                "BGBBGB"
        });
    }



}
