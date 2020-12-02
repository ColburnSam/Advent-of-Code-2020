import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Set<Integer> expenses = new HashSet<>();
        File text = new File("ExpenseReport.txt");
        Scanner sc = new Scanner(text);

        while (sc.hasNextInt()){
            expenses.add(sc.nextInt());
        }

        Set<Integer> solutions1 = new HashSet<>();
        for (Integer i : expenses) {
            if (expenses.contains(2020 - i)) {
                solutions1.add(i);
            }
        }
        System.out.println("Part 1:" + solutions1);

        Set<Integer> solutions2 = new HashSet<>();
        for (Integer i : expenses) {
            for (Integer j : expenses) {
                if (expenses.contains(2020 - (i + j))) {
                    solutions2.add(i);
                }
            }
        }
        System.out.println("Part 2:" + solutions2);

    }
}

