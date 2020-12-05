import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException {
        File input = new File("PassportData.txt");
        Scanner sc = new Scanner(input);

        int validCount = 0;
        int validCount2 = 0;
        while (sc.hasNext()) {
            Passport curr = new Passport(sc);
            if (curr.isValidPassport())
                validCount++;
            if(curr.reqFieldsAreValid())
                validCount2++;
        }

        System.out.println("Part 1:");
        System .out.println("There are " + validCount + " valid passports.");

        System.out.println("\nPart 2:");
        System.out.println("There are " + validCount2 + " valid passports.");
    }
}
