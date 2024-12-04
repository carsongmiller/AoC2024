import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {
    public static int Part1(String text) {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("mul\\(\\d+,\\d+\\)").matcher(text);
        while (m.find()) {
            allMatches.add(m.group());
        }

        int sum = 0;
        for (String match : allMatches) {
            var split = match.split(",");
            int left = Integer.parseInt(split[0].substring(4));
            int right = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
            sum += left * right;
        }
        return sum;
    }

	public static int Part2(String text) {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)").matcher(text);
        while (m.find()) {
            allMatches.add(m.group());
        }

        int sum = 0;
        boolean active = true;
        for (String match : allMatches) {
            if (match.equals("do()")) {
                active = true;
                continue;
            }
            else if (match.equals("don't()")) {
                active = false;
                continue;
            }
            if (!active) continue;

            var split = match.split(",");
            int left = Integer.parseInt(split[0].substring(4));
            int right = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
            sum += left * right;
        }
        return sum;
	}
}
