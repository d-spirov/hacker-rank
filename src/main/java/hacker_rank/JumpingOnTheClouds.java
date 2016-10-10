package hacker_rank;

/**
 * Created by dspirov on 30/09/16.
 */
public class JumpingOnTheClouds {

    static void jumping(int[] clouds) {
        int steps = 0;
        for (int i = 0; i < clouds.length; ) {
            if(i + 2 < clouds.length) {
                if (clouds[i + 2] == 1) {
                    steps++;
                    i++;
                } else {
                    steps++;
                    i += 2;
                }
            } else if(i == clouds.length -1){
                break;
            } else {
                steps++;
                break;
            }
            System.out.println(i);;
        }
        System.out.println(steps);
    }

    public static void main(String[] args) {
        jumping(new int[] {0,0,1,0,0,1,0});
    }

}
