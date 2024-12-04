import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class App {
    public static void main(String[] args) {
		var inputFilePath = "inputs\\01.txt";
		var text = GetLines(inputFilePath);
        System.out.println("Part 1: " + Part1(text));
		System.out.println("Part 2: " + Part2(text));
    }

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

    //Returns all lines of a given text file as a list of strings
    public static String GetLines(String filePath) {
        //Create a list that we'll store all of our lines in
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            //do nothing
        }
		return text;
    }
}