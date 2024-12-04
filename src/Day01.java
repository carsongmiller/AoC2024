import java.util.ArrayList;
import java.util.Collections;

public class Day01 {
    public static int Part1(String text) {
        //First, split the lines into two lists: left and right
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();
        var lines = text.split("\n");

        for (String line : lines) {
            var sp = line.split("   ");
            left.add(Integer.valueOf(sp[0]));
            right.add(Integer.valueOf(sp[1]));
        }

        Collections.sort(left);
        Collections.sort(right);
        int distance = 0;

        for (int i = 0; i < left.size(); i++) {
            distance += Math.abs(left.get(i) - right.get(i));
        }

        return distance;
    }

	public static int Part2(String text) {
        //First, split the lines into two lists: left and right
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();
        var lines = text.split("\n");

        for (String line : lines) {
            var sp = line.split("   ");
            left.add(Integer.valueOf(sp[0]));
            right.add(Integer.valueOf(sp[1]));
        }

        int similarity = 0;

        for (int i = 0; i < left.size(); i++) {
            int appearances = Collections.frequency(right, left.get(i));
            similarity += left.get(i) * appearances;
        }

        return similarity;
	}
}
