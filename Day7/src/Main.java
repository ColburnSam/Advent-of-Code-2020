import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("input.txt"));
        Rules r = new Rules(input);
        System.out.println("Bag types that can contain a shiny gold bag: " + r.getContainers("shiny gold").size());
        System.out.println("Number of bags inside of a shiny gold bag is: " + r.countBagsInside("shiny gold"));
    }
}
