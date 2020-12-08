import java.util.*;

public class Rules {

    public Map<String, Map<String, Integer>> rulesMap; // maps bags to the their required contents
    public Map<String, ArrayList<String>> containerMap; // maps bags to its potential container types

    public Rules(Scanner rulesList) {
        rulesMap = new HashMap<>();
        containerMap = new HashMap<>();
        while (rulesList.hasNextLine()) {
            String[] rule = rulesList.nextLine().split("bags contain"); // {"name", "list of things it contains"}
            Map<String, Integer> contents = getRule(rule[1]);
            rulesMap.put(rule[0].trim(), contents);

            // populate containerMap
            for (String s : contents.keySet()) {
                if (!containerMap.containsKey(s))
                    containerMap.put(s, new ArrayList<>());
                containerMap.get(s).add(rule[0].trim());
            }
        }
    }

    public Map<String, Integer> getRule(String rule) {
        Map<String, Integer> requiredContents = new HashMap<>();
        if (rule.equals(" no other bags."))
            return requiredContents;

        String[] requirements = rule.split(",");
        for (int i = 0; i < requirements.length; i++) {
            Scanner sc = new Scanner(requirements[i]);
            int quantity = sc.nextInt();
            String adjective = sc.next();
            String color = sc.next();
            requiredContents.put((adjective + " " + color) , quantity);
        }
        return requiredContents;
    }

    // part 1
    public Set<String> getContainers(String bag) {
        Set<String> containers = new HashSet<>();
        if (!containerMap.containsKey(bag))
           return containers;

        for (String s : containerMap.get(bag)) {
            containers.add(s);
            containers.addAll(getContainers(s));
        }
        return containers;
    }

    // part 2
    public int countBagsInside(String bag) {
        int count = 0;
        Map<String, Integer> contents = rulesMap.get(bag);

        for (String key : contents.keySet()) {
            count += contents.get(key) * (1 + countBagsInside(key));
        }
        return count;
    }

}
