import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File Map = new File("Map.txt");
        Scanner sc = new Scanner(Map);
        Hillside tobogganing = new Hillside(sc);

        System.out.println("Part 1:");
        System.out.println("Right 3, Down 1 : " + tobogganing.getTreesHit(3,1) + " trees hit");

        System.out.println("\nPart 2:");
        int answer = 1;
        answer *= hitTheSlopes(tobogganing,1,1);
        answer *= hitTheSlopes(tobogganing,3,1);
        answer *= hitTheSlopes(tobogganing,5,1);
        answer *= hitTheSlopes(tobogganing,7,1);
        answer *= hitTheSlopes(tobogganing,1,2);
        System.out.println("Final answer = " + answer);
    }

    // Hit the slopes my dude. Given a hillside and an angle of descent, returns the number of trees hit
    // and prints a message describing your toboggan ride.
    public static int hitTheSlopes (Hillside h, int right, int down) {
        int treesHit = h.getTreesHit(right, down);
        System.out.println("Right "+ right + ", Down " + down + " : " + treesHit + " trees hit");
        return treesHit;
    }
}