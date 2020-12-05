import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static int getID(String seat) {
        seat = seat.replaceAll("[BR]", "1").replaceAll("[FL]", "0");
        int row = Integer.parseInt(seat.substring(0,7), 2);
        int col = Integer.parseInt(seat.substring(7), 2);
        return row * 8 + col;
    }

    public static int findSeat(int max, Set seatNumbers) {
        for (int i = max; i > 0; i--) {
            if(!seatNumbers.contains(i))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Set<Integer> seatNumbers = new HashSet<>();
        int max = 0;
        while (sc.hasNext()) {
            int curr = getID(sc.next().trim());
            seatNumbers.add(curr);
            if (max < curr)
                max = curr;
        }
        System.out.println("Part 1: \nThe highest seat number on a boarding pass is " + max);
        System.out.println("Part 2: \nYour seat number is " + findSeat(max, seatNumbers));
    }
}
