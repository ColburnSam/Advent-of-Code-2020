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
    }
}