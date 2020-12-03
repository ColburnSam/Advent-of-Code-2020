import java.util.ArrayList;
import java.util.Scanner;

public class Hillside {

    private ArrayList<String> trees;
    private final char TREE = '#';

    public Hillside(Scanner sc) {
        trees = new ArrayList<String>();
        while (sc.hasNext()) {
            this.trees.add(sc.nextLine());
        }
    }

    // Given the slope (how many down and how many to the right we progress at a time) of decent,
    // returns the number of trees hit following that path
    public int getTreesHit(int right, int down) {
        int treesHit = 0;
        for (int i = 0; i * down < trees.size(); i++) {
            if (isTree(i * right, i * down)) {
                treesHit++;
            } else {
            }
        }
        return treesHit;
    }

    // Given the x and y value of a coordinate, returns whether or not there is a tree at that point
    public boolean isTree(int x, int y) {
        String row = trees.get(y);
        return row.charAt(x % row.length()) == TREE;
    }

}