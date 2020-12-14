import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Ferry f = new Ferry();
        f.setWayPoint(10, 1);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            char c = s.charAt(0);
            int i = parseInt(s.substring(1));
            f.moveWay(c, i);
            System.out.println("(x, y) = (" + f.getX() + ", " + f.getY() + ") with wayPoint " + f.printWay());
        }
        System.out.println("(x, y) = (" + f.getX() + ", " + f.getY() +")");
        System.out.println("Part 1: " + (Math.abs(f.getX()) + Math.abs(f.getY())));

    }
}

