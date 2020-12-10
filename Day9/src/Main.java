import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Scanner user = new Scanner(System.in);
        System.out.println("How many lines is the prefix?");
        ArrayList<Integer> prefix = getPrefix(sc, user.nextInt());
        Map<Integer, ArrayList<Integer>> getAddends = new HashMap<>();

        // Part 1
        boolean found = false;
        Integer badNum = null;
        while(sc.hasNextLine() && !found) {
            badNum = sc.nextInt();
            found = !isSum(prefix, badNum);
            prefix.remove(0); // removes element at index 0 (not an element with value 0)
            prefix.add(badNum);
        }
        System.out.println("Part 1: " + badNum);

        // Part 2
        sc = new Scanner(new File("input.txt"));
        ArrayList<Integer> addends = new ArrayList<>();
        int sum = 0;
        while (sum != badNum) {
            if (sum < badNum) {
                int next = sc.nextInt();
                sum += next;
                addends.add(next);
            } else if (sum > badNum) {
                sum -= addends.get(0);
                addends.remove(0);
            }
        }
        Collections.sort(addends);
        System.out.println("Part 2: " + (addends.get(0) + addends.get(addends.size() - 1)));
    }

    // Creates ArrayList from prefix of input file of given length
    public static ArrayList<Integer> getPrefix(Scanner sc, int size) {
        ArrayList<Integer> prefix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            prefix.add(sc.nextInt());
        }
        return prefix;
    }

    // Returns if the given integer can be expressed as a sum of 2 distinct elements
    // (i.e.: differently indexed) of the given ArrayList
    public static boolean isSum(ArrayList<Integer> prefix, int n) {
        for (int i = 0; i < prefix.size(); i++) {
            int curr = prefix.get(i);
            if (prefix.contains(n - curr) && (curr * 2 != n || prefix.indexOf(curr) != i))
                return true;
        }
        return false;
    }
}
