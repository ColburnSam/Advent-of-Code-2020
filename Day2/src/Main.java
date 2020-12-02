import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File text = new File("Passwords.txt");
        Scanner sc = new Scanner(text);
        int count1 = 0;
        int count2 = 0;

        while (sc.hasNext()) {
            Password pw = new Password(sc.nextLine());
            if (pw.passwordIsValid(1))
                count1++;
            if (pw.passwordIsValid(2))
                count2++;
        }

        System.out.println("Part 1: " + count1);
        System.out.println("Part 2: " + count2);
    }
}
