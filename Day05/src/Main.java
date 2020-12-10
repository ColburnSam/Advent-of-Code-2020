import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static int getID(String seat) {
        return Integer.parseInt(seat.replaceAll("[BR]", "1").replaceAll("[FL]", "0"), 2);
    }

    public static int findSeat(int n, int a_n, int S_n) {
        return (n / 2) * ((a_n - n + 1) + a_n) - S_n; // Arithmetic sum - actual sum = missing term
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Set<Integer> seatNumbers = new HashSet<>();
        int count = 0;
        int sum = 0;
        int max = 0;
        while (sc.hasNext()) {
            int curr = getID(sc.next().trim());
            seatNumbers.add(curr);
            count++;
            sum +=curr;
            if (max < curr)
                max = curr;
        }
        System.out.println("Part 1: \nThe highest seat number on a boarding pass is " + max);
        System.out.println("Part 2: \nYour seat number is " + findSeat(count + 1, max, sum)); // add you to count
    }
}
