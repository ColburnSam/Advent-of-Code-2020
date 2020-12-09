import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import static java.lang.Integer.parseInt;

public class Program {

    private ArrayList<String> code;
    private int acc;

    public Program(Scanner sc) {
        code = new ArrayList<>();
        while (sc.hasNextLine()) {
            code.add(sc.nextLine());
        }
    }

    public int getLength() {
        return code.size();
    }

    public String getLine(int lineNumber) {
        return this.code.get(lineNumber);
    }


    // Runs the code from line 0 until it is about to enter a loop
    // Returns a set of integers representing visited lines (potentially including
    // a line past the end of the file if it was attempted)
    public int runCode() {
        this.acc = 0;
        int currLine = 0;
        Set<Integer> visited = new HashSet<>();
        while (!visited.contains(currLine) && currLine < code.size()) {
            visited.add(currLine);
            currLine = runLine(currLine);
        }
        return this.acc;
    }

    public int runLine(int currLine) {
        String[] line = code.get(currLine).split(" ");
        if (line[0].equals("acc"))
            this.acc += parseInt(line[1]);
        else if (line[0].equals("jmp"))
            currLine += parseInt(line[1]) - 1;
        return currLine + 1;
    }

    // Mutates the line at the given index from "nop" to "jmp" or vice versa
    public void mutateLine(int i) {
        String[] line = code.get(i).split(" ");
        if (line[0].equals("nop"))
            code.set(i, "jmp " + line[1]);
        else if (line[0].equals("jmp"))
            code.set(i, "nop " + line[1]);
    }

    public boolean fixCode(Set targets) {
        int currLine = 0;
        Set<Integer> visited = new HashSet<>();
        while (!visited.contains(currLine) && currLine < code.size()) {
            mutateLine(currLine);
            int newLine = runLine(currLine);
            if(targets.contains(newLine)) {
                return true;
            } else {
                mutateLine(currLine);
                currLine = runLine(currLine);
            }
        }
        return false;
    }
}
