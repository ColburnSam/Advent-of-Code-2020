import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private final static int ALL_ANSWERS_NO = 0;            // int form of 26 digit binary number of all 0s
    private final static int ALL_ANSWERS_YES = 67108863;    // int form of 26 digit binary number of all 1s

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        int totals[] = {0, 0};
        while (sc.hasNext()) {
            int curr[] = getNextGroup(sc);
            totals[0] += curr[0];
            totals[1] += curr[1];
        }
        System.out.println("Part 1: \nThe sum of questions answered \"yes\" by all groups is " + totals[0] + ".");
        System.out.println("Part 2: \nThe sum of questions answered \"yes\" by all groups is " + totals[1] + ".");
    }

    // Returns an int array where the first element is the given group's total as described in part 1
    // and the second element in the group's total as described in part 2.
    public static int[] getNextGroup(Scanner sc) {
        if (!sc.hasNext())
            return new int[]{0, 0};

        int groupAnswers[] = {ALL_ANSWERS_NO, ALL_ANSWERS_YES};
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isEmpty())
                return new int[] {count1s(groupAnswers[0]), count1s(groupAnswers[1])};
            int currAnswer = ALL_ANSWERS_NO;
            for (char ch : line.toCharArray()) {
                currAnswer += Math.pow(2, 'z' - ch);
            }
            groupAnswers[0] |= currAnswer;
            groupAnswers[1] &= currAnswer;
        }
        return new int[] {count1s(groupAnswers[0]), count1s(groupAnswers[1])};
    }

    // Returns the number of '1' digits in the binary expression of a given int.
    public static int count1s(int answers) {
        int count = 0;
        while (answers != 0) {
            count += answers % 2;
            answers /= 2;
        }
        return count;
    }
}
