package hacker_rank.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by dspirov on 09/10/16.
 */
public class EvenTree {

    static class Node {
        int val;
        List<Node> children;
        int forestCnt = 0;
        Node parent;

        public Node(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        void addChild(Node c) {
            children.add(c);
            c.parent = this;
        }

        void remove(Node n) {
            children.remove(n);
        }

    }

    static class Tree {

        Map<Integer, Node> nodes = new HashMap<>();
        Node root;


        Tree(int n) {
            root = new Node(1);
            nodes.put(1, root);
            for (int i = 2; i <= n; i++) {
                Node node = new Node(i);
                nodes.put(i, node);
            }
        }

        void add(int from, int to) {
            Node fromNode = nodes.get(to);
            fromNode.addChild(nodes.get(from));
        }



    }

    static List<Node> roots = new ArrayList<>();

    static int dfs(Node t) {
        if(t == null) {
            return 0;
        } else {
            if(t.children.size() == 0) {
                return 1;
            }
        }
        int cnt = 1;
        for(Node n: t.children) {
            cnt += dfs(n);
        }

        t.forestCnt = cnt;
        return cnt;
    }

    static void split(Tree t) {
        List<Node> nodes = new ArrayList<>(t.nodes.values());
        Collections.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.forestCnt - o2.forestCnt;
            }
        });
        roots.add(t.root);
        int cnt = 0;
        for(Node n: nodes) {
            if(n != t.root &&  n.forestCnt %2 == 0 && n.forestCnt > 0) {
                roots.add(n);
                n.parent.remove(n);
                n.parent.forestCnt -= n.forestCnt;
                cnt ++;
            }
        }
        System.out.println(cnt);
    }

    static void solve(Tree t) {
        dfs(t.root);
        split(t);
        //System.out.println(t.root);
    }


    static void test() {
        Tree t = new Tree(10);
        t.add(2, 1);
        t.add(3, 1);
        t.add(4, 3);
        t.add(5, 2);
        t.add(6, 1);
        t.add(7, 2);
        t.add(8, 6);
        t.add(9, 8);
        t.add(10, 8);
        solve(t);
    }

    public static void main(String[] args) {
        //test();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Tree t = new Tree(n);
        for (int i = 0; i < m; i++){
            int from = in.nextInt();
            int to = in.nextInt();
            t.add(from, to);
        }
        solve(t);
    }



}
