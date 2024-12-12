import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Day05 {
    public static int Part1(String text) {
        var split = text.split("\n\n");
        var rulesRaw = split[0].split("\n");
        var updatesRaw = split[1].split("\n");
        var sortedOrder = new ArrayList<Integer>();

        //build a nice rules list, and another list containing all integers that appear in the rules
        var rules = new ArrayList<int[]>();

        for (var rule : rulesRaw) {
            var x = rule.split("\\|");
            var left = Integer.parseInt(x[0]);
            var right = Integer.parseInt(x[1]);
            rules.add(new int[]{left, right});
            if (!sortedOrder.contains(left)) {
                sortedOrder.add(left);
            }
            if (!sortedOrder.contains(right)) {
                sortedOrder.add(right);
            }
        }

        var updates = new ArrayList<ArrayList<Integer>>();

        for (var update : updatesRaw) {
            var tempUpdateList = update.split(",");
            var newList = new ArrayList<Integer>();
            for (var updateItem : tempUpdateList) {
                newList.add(Integer.parseInt(updateItem));
            }
            updates.add(newList);
        }

        int sum = 0;

        for (var update : updates) {
            boolean fail = false;
            for (var rule : rules) {
                if (update.contains(rule[0]) && update.contains(rule[1]) && update.indexOf(rule[0]) > update.indexOf(rule[1])) {
                    fail = true;
                    break;
                }
            }
            if (!fail) {
                sum += update.get((update.size() - 1) / 2);
            }
        }

        return sum;
    }

	public static int Part2(String text) {
        var split = text.split("\n\n");
        var rulesRaw = split[0].split("\n");
        var updatesRaw = split[1].split("\n");

        //build a nice rules list, and another list containing all integers that appear in the rules
        var rulesMustBeBelow = new HashMap<Integer, ArrayList<Integer>>();
        var rulesMustBeAbove = new HashMap<Integer, ArrayList<Integer>>();

        for (var rule : rulesRaw) {
            var x = rule.split("\\|");
            var left = Integer.valueOf(x[0]);
            var right = Integer.valueOf(x[1]);
            if (rulesMustBeBelow.containsKey(left)) {
                rulesMustBeBelow.get(left).add(right);
            }
            else {
                rulesMustBeBelow.put(left, new ArrayList<>(Arrays.asList(right)));
            }
            
            if (rulesMustBeAbove.containsKey(right)) {
                rulesMustBeAbove.get(right).add(left);
            }
            else {
                rulesMustBeAbove.put(right, new ArrayList<>(Arrays.asList(left)));
            }
        }

        var updates = new ArrayList<ArrayList<Integer>>();

        for (var update : updatesRaw) {
            var tempUpdateList = update.split(",");
            var newList = new ArrayList<Integer>();
            for (var updateItem : tempUpdateList) {
                newList.add(Integer.parseInt(updateItem));
            }
            updates.add(newList);
        }

        int sum = 0;
        //Sort all the updates

        for (int i = 0; i < updates.size(); i++) {
            var update = updates.get(i);
            boolean sorted = false;
            boolean requiredSorting = false;
            while (!sorted) {
                sorted = true;
                for (int j = 0; j < update.size(); j++) {
                    var member = update.get(j);
                    var mustBeBelowList = rulesMustBeBelow.get(member);
                    var mustBeAboveList = rulesMustBeAbove.get(member);
                    var mustBeBelowIndices = new ArrayList<Integer>();
                    var mustBeAboveIndices = new ArrayList<Integer>();
    
                    if (mustBeBelowList != null){
                        for (var mustBeBelow : mustBeBelowList) {
                            if (update.indexOf(mustBeBelow) != -1){
                                mustBeBelowIndices.add(update.indexOf(mustBeBelow));
                            }
                        }
                    }
                    
                    if (mustBeAboveList != null) {
                        for (var mustBeAbove : mustBeAboveList) {
                            if (update.indexOf(mustBeAbove) != -1){
                                mustBeAboveIndices.add(update.indexOf(mustBeAbove));
                            }
                        }
                    }
    
                    Collections.sort(mustBeBelowIndices);
                    Collections.sort(mustBeAboveIndices);
    
                    if (mustBeBelowIndices.size() > 0 && (Integer)update.indexOf(member) > mustBeBelowIndices.get(0)) {
                        sorted = false;
                        requiredSorting = true;
                        update.remove(update.indexOf(member));
                        update.add(mustBeBelowIndices.get(0), member);
                        break;
                    }
                    else if (mustBeAboveIndices.size() > 0 && (Integer)update.indexOf(member) < mustBeAboveIndices.get(mustBeAboveIndices.size() - 1)) {
                        sorted = false;
                        requiredSorting = true;
                        update.remove(update.indexOf(member));
                        update.add(mustBeAboveIndices.get(mustBeAboveIndices.size() - 1), member);
                        break;
                    }
                }
            }
            if (requiredSorting) {
                sum += update.get((update.size() - 1) / 2);
            }
        }

        return sum;
	}
}
