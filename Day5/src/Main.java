import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final int MAX_ROW_NUMBER = 127;
    private static final int MAX_COL_NUMBER = 7;

    public static int getID(String seat) {
        int rowNumber = MAX_ROW_NUMBER;
        int colNumber = MAX_COL_NUMBER;

        for (int i = 0; i < 7; i++) {   // Rows
            if(seat.charAt(i) == 'F')
                rowNumber -= Math.pow(2, (6-i));
        }

        for (int i = 0; i < 3; i++) {   // Cols
            if(seat.charAt(i + 7) == 'L')
                colNumber -= Math.pow(2, (2-i));
        }

        return rowNumber * 8 + colNumber;
    }

    public static int findSeat(int max, Set seatNumbers) {
        for (int i = max; i > 0; i--) {
            if(!seatNumbers.contains(i))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File boardingPasses = new File("input.txt");
        Scanner sc = new Scanner(boardingPasses);
        Set<Integer> seatNumbers = new HashSet<>();
        int max = 0;

        while (sc.hasNext()) {
            int curr = getID(sc.next().trim());
            seatNumbers.add(curr);
            if (max < curr)
                max = curr;
        }

        System.out.println("Part 1:");
        System.out.println("The highest seat number on a boarding pass is " + max);
        System.out.println("\nPart 2:");
        System.out.println("Your seat number is " + findSeat(max, seatNumbers));
    }
}
