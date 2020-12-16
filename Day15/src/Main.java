import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Integer> previous = new HashMap<>(); // maps ints to last turn they were said
        int[] input = new int[]{ 0,13,1,16,6,17};
        //int[] input = new int[]{ 0, 13, 1, 16, 6, 17};
        for (int i = 1; i < input.length ; i++) {
            previous.put(input[i - 1], i);
        }
        int lastSaid = input[input.length - 1]; // last said, not in the map yet
        int say = -1;

        // updates the map for the last said number and "says" the next number.
        for (int i = input.length + 1; i <= 2020; i++) {

            if (!previous.keySet().contains(lastSaid)) {
                previous.put(lastSaid, i - 1);
                say = 0;
            } else {
                say = (i - 1) - previous.get(lastSaid);
                previous.replace(lastSaid, i - 1);
            }
            //System.out.println("Turn: " + i + " ; Say: " + say);
            lastSaid = say;
        }
        System.out.println("Part 1: " + say);
        // Part 2: replace 2020 with 30000000.

    }
}
