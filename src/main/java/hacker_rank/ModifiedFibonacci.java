package hacker_rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by dspirov on 01/10/16.
 */
public class ModifiedFibonacci {

    static class FibKey {

        int t1;
        int t2;
        int n;

        public FibKey(int t1, int t2, int n) {
            this.t1 = t1;
            this.t2 = t2;
            this.n = n;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FibKey fibKey = (FibKey) o;

            if (t1 != fibKey.t1) return false;
            if (t2 != fibKey.t2) return false;
            return n == fibKey.n;

        }

        @Override
        public int hashCode() {
            int result = t1;
            result = 31 * result + t2;
            result = 31 * result + n;
            return result;
        }
    }

    static Map<FibKey, String> fibCache = new HashMap<>();

    static void modFibonacci(int t1, int t2, int n) {
        String[] fib = new String[30];
        fib[0] = t1 + "";
        fib[1] = t2 + "";

        for (int i = 2; i < n; i++) {
            FibKey key = new FibKey(t1, t2, i);
            String cacheHit = fibCache.get(key);
            if(cacheHit != null) {
                fib[i] = cacheHit;
            } else {
                fib[i] = plus(Arrays.asList(new String[] {fib[i - 2], multTrie(fib[i - 1], fib[i - 1])}));
                fibCache.put(key, fib[i]);
                System.out.println(fib[i].length() + " " + fib[i]);
                System.out.println("-------------");
            }
        }
        System.out.println(fib[n - 1]);
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

    static class Node {

        Map<Integer, Node> children;
        int val;
        String result;
        int count;

        Node(int x, String result) {
            children = new HashMap<>();
            this.val = x;
            this.result = result;
            count = 0;
        }

        void add(String val, String result) {
            if(val.length() == 0) {
                this.result = result;
                count++;
                return;
            }

           int x = val.charAt(val.length() - 1) - '0';
            Node c = children.get(x);
            if(c != null) {
            } else {
                c = new Node(x, result);
                children.put(x, c);
            }
            c.add(val.substring(0, val.length() - 1), result);
        }

        String find(String val) {
            if(val.length() == 0) {
                return result;
            }
            int x = val.charAt(val.length() - 1) - '0';
            Node c = children.get(x);
            if(c == null) {
                return null;
            } else {
                return c.find(val.substring(0, val.length() - 1));
            }
        }

    }

    static class Trie {

        Node root = new Node(0, "0");

        String find(String val) {
            return root.find(val);
        }

        void add(String val, String result) {
            root.add(val, result);
        }

    }



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

    static Map<Key, String> singleMap = new HashMap<>();

    static class Key {
        String s;
        int x;

        public Key(String s, int x) {
            this.s = s;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (x != key.x) return false;
            return !(s != null ? !s.equals(key.s) : key.s != null);

        }

        @Override
        public int hashCode() {
            int result = s != null ? s.hashCode() : 0;
            result = 31 * result + x;
            return result;
        }
    }

    static int chit = 0;
    static int cmiss = 0;

    static Map<String, String>  sMapOpt = new HashMap<>();


    static String singleMult(String b, int x) {
        String key = b + "-" + x;
        String test = sMapOpt.get(key);
        if(test != null) {
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

    static String plus(List<String> numbers) {
        //System.out.println("+ " + s1.length() + " , " + s2.length());



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
        //System.out.println(s + " "+ index + " " + length + " " + s.charAt(i));
        return s.charAt(i) - '0';
    }

    static String leadZero(String s, int length) {
        return zeroes(length - s.length()) + s;
    }


    static void test() {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            int a1 = r.nextInt(100);
            int m1 = a1 * a1;
            int m2 = Integer.parseInt(multTrie(a1 + "", a1 + ""));
            if(m1 != m2) {
                System.out.println(a1 + " " + a1 + " " + m1 + " " + m2);
            }

        }
    }

    static void buildTrie(Trie t, String s) {
        String curr = "";
        for (int i = s.length() - 1; i >= 0; i--) {
           curr = s.charAt(i) + curr;
            String r = t.find(curr);
            if(r == null) {
                t.add(curr, "");
                curr = "";
            }
        }
        System.out.println("test");
    }


    static String singleMultTrie(Trie t,String b, int x) {
        String test= t.find(x + "");
        if(test != null) {
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
            t.add(x + "", res.toString());
            return res.toString();
        }
    }

    static String multTrie(String s1, String s2) {
        Trie t = new Trie();
        StringBuilder res = new StringBuilder("0");
        List<String> all = new ArrayList<>();
        String curRes = "";
        String r = "";
        for (int i = s1.length() - 1; i >= 0; i--) {
            int x = s1.charAt(i) - '0';
            curRes = x + curRes;
            r = t.find(curRes);

            if(r == null) {
                //System.out.println("hit " + curRes.length());
                String tmp = "0";
                String tmp1 = singleMultTrie(t, s2, x) + zeroes(s1.length() - 1 - i);
                String fRes = tmp1;
                if(curRes.length() > 1) {
                    tmp = t.find(curRes.substring(1, curRes.length()));
                    fRes = plus(Arrays.asList(new String[]{tmp, tmp1}));
                }

                t.add(curRes, fRes);
                all.add(fRes);

                curRes = "";
            }
            if(i == 0 && r != null) {
                all.add(r + zeroes(s1.length() - i - 1));
            }
            //res = new StringBuilder(plus(curr, res.toString()));
        }


        return plus(all);
    }

    public static void main(String[] args) {
        //test();
        //System.out.println(plus(Arrays.asList(new String[] {"12678" , "345"})));
        //System.out.println(singleMult("705", 5));
        System.out.println(multTrie("4420666042894", "4420666042894") + " " + 11*11);
        //System.out.println(multTrie("", "1245") + " " + 1245*1245);
        //modFibonacci(2, 2, 12);
        //System.out.println(zeroes(1000));
        //System.out.println(getChar("1234", 4, 3));
        //String res = "1757329256675243923789908706392770784786675014466205478138412457861182748252445334276829689067184961772452672539182519776970018441545703728764943804716080333071977999357686452225749520438868072077370296752759336628819658626450119829460271307320771905966610444316632902665405778568692358421822346515297561688615826139900005148232393566635450910503069280558885776974441724647575506917705233033126440427943192933031295667270379802822678451677873476562878090351384756323752934266483704329494913901777077818454247115169238710238511897127940175269563609704933891722851527311586200728158589834781233640705178754148388193984881072020460897836844013424021398486231811026937788027205277318512367740285570214308422221218045052546982425948267923100208211877485247274063676584245211992309199093943770848228756115454877811574170055710239820307647236063102551089331022551145970246161572934923563788891140196611348289891836584407118366615429902787842414216705879909724842427311043658585786793985854710704388468694705447453361840222042872804082033605387304720820709511700612993894438037088258748378144829151759966134438587801239685060481287847041098860827156205266219988528606361248605731977393455336514841904126569917580552221615445440707944259657537316663633467775455687176419784308109862403681615390768827522514226515170779952877302260447965619385075559481038156202380824747910079952509468764879665277926444762783111170334169564290133775731116624417103626332473260854706855836733892239605334403060325713213158198436235481732934783824522986791671298451630521298146256913521370293366146027041883164004813408024083418453818496833678677989688286365578704053463003280506773290489025022245432658007749334170427594301173455454554896264010337133979533194378415979559205762371629124713048151826187726541330925435941196489866436262982965357963222011173783566578265154537046879084586204982283615439654020245205575911232340978345796075759501453619980609757761007900782285003697961396855030759830227588672764901783914185255175633028864246597254753650709637838179816634761041341829682036923022647593659655086714575074121846462444066620656618402622582078227970971263134102620785544089874901290476208776062274125753623695424837124309012889744671656878868185467040956278860994022654818422174510207086302834360129636807988365101221058491347089044016710776737644321609595303247578604224834566346064211080660255706747923865699460916177343980076864685242547557287739855935088339224039901526344160525839859926514809254800796830793171231839585826664965370120348354863229472374580496122154405365502748878825417073864742259611244291147536123360313428976309739321516651880073006457027777846740424352741783894796265299375110383046035061852428726146824308622371413505186282417715333922467949631807937584325292193182634981308081610405254624500919877439502317535268113977697410303229612126819956729229712994121103936461536859217040417282035407938130308810628152511428442032222000446056735945822485416186624739508353046274177392584177836344015741284307726408895221078195326053694598171404400753324481185152896441877182770004650733312662200926580429269618000917825486318259735458397201405179612629871143087629606057747806435987915590108963986570156087004776466078987255481357174321182";
        //Trie trie = new Trie();
        //buildTrie(trie, res);
    }
}
