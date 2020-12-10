import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Passport {

    //Fields
    String byr; // birth year
    String iyr; // issue year
    String eyr; // expiration year
    String hgt; // height
    String hcl; // hair color
    String ecl; // eye color
    String pid; // passport ID
    String cid; // country ID

    // Given a scanner with each line containing a String of data of the form "variable:value" seperated by " "
    // constructs a Passport with the given data until it a blank line or the end of the scanner is reached.
    // pops the blank line from the scanner.
    // Malformation input file will crash this
    public Passport (Scanner sc) throws NoSuchFieldException, IllegalAccessException {
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isEmpty())
                break;
            Scanner subScan = new Scanner(line);
            while (subScan.hasNext()) {
                String datum = subScan.next();
                Field field = getClass().getDeclaredField(datum.substring(0,datum.indexOf(':')));
                field.set(this, datum.substring(datum.indexOf(':')+1));
            }
        }
    }

    // Part 1 validation method
    // Returns if the passport has all required fields (i.e.: all but country ID).
    public boolean isValidPassport() {
        return byr != null && iyr != null && eyr != null && hgt != null &&
                hcl != null && ecl != null && pid != null;
    }

    // Part 2 validation method
    // Returns
    public boolean reqFieldsAreValid() {
        return isValidPassport() && byrIsValid() && iyrIsValid() && eyrIsValid() &&
                    hgtIsValid() &&hclIsValid() && eclIsValid() && pidIsValid() && cidIsValid();
    }

    public boolean byrIsValid() {
        int year = Integer.parseInt(this.byr);
        return 1920 <= year && year <= 2002;
    }

    public boolean iyrIsValid() {
        int year = Integer.parseInt(this.iyr);
        return 2010 <= year && year <= 2020;
    }

    public boolean eyrIsValid() {
        int year = Integer.parseInt(this.eyr);
        return 2020 <= year && year <= 2030;
    }

    public boolean hgtIsValid() {
        String height = this.hgt;
        if (height.length() < 4)
            return false;
        String units = height.substring(height.length()-2);
        int quantity = Integer.parseInt(height.substring(0, height.length()-2));
        return (units.equals("cm") && 150 <= quantity && quantity <= 193) ||
                (units.equals("in") && 59 <= quantity && quantity <= 76);
    }

    public boolean hclIsValid() {
        String patternBase16 = "^[0-9a-f]+$";
        return this.hcl.length() == 7 && this.hcl.charAt(0) == '#' &&
                this.hcl.substring(1).matches(patternBase16);
    }

    public boolean eclIsValid() {
        Set<String> eyeColors = new HashSet(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
        return eyeColors.contains(this.ecl);
    }

    public boolean pidIsValid() {
        String patternBase10 = "^[0-9a-f]+$";
        return this.pid.length() == 9 && this.pid.substring(1).matches(patternBase10);
    }

    public boolean cidIsValid() {
        return true;
    }

}
