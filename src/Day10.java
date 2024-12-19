
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;


public class Day10 {
    public static int Part1(String text) {
        text = text.trim();
        var map = text.split("\n");
        int sum = 0;
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++ ) {
                if (Character.getNumericValue(map[r].charAt(c)) == 0) {
                    var peaks = new HashSet<Integer>();
                    ScoreTrailhead_Part1(map, r, c, new HashSet<>(), peaks);
                    sum += peaks.size();
                }
            }
        }
        return sum;
    }

    public static void ScoreTrailhead_Part1(String[] map, int r, int c, HashSet<Integer> visited, HashSet<Integer> peaks) {
        visited.add(r * map[0].length() + c);

        if (map[r].charAt(c) == '9') {
            peaks.add(r * map.length + c);
            return;
        }
        int currentHeight = Character.getNumericValue(map[r].charAt(c));

        if (r > 0 && Character.getNumericValue(map[r - 1].charAt(c)) == currentHeight + 1 && !visited.contains((r-1) * map.length + c)) {
            //explore up
            ScoreTrailhead_Part1(map, r - 1, c, visited, peaks);
        }
        if (c < map[0].length() - 1 && Character.getNumericValue(map[r].charAt(c + 1)) == currentHeight + 1 && !visited.contains(r * map.length + c + 1)) {
            //explore right
            ScoreTrailhead_Part1(map, r, c + 1, visited, peaks);
        }
        if (r < map.length - 1 && Character.getNumericValue(map[r + 1].charAt(c)) == currentHeight + 1 && !visited.contains((r+1) * map.length + c)) {
            //explore down
            ScoreTrailhead_Part1(map, r + 1, c, visited, peaks);
        }
        if (c > 0 && Character.getNumericValue(map[r].charAt(c - 1)) == currentHeight + 1 && !visited.contains(r * map.length + c - 1)) {
            //explore left
            ScoreTrailhead_Part1(map, r, c - 1, visited, peaks);
        }
        return;
    }

	public static int Part2(String text) {
        text = text.trim();
        var map = text.split("\n");
        int sum = 0;
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++ ) {
                if (Character.getNumericValue(map[r].charAt(c)) == 0) {
                    sum += ScoreTrailhead_Part2(map, r, c);
                }
            }
        }
        return sum;
	}

    public static int ScoreTrailhead_Part2(String[] map, int r, int c) {
        if (map[r].charAt(c) == '9') {
            return 1;
        }
        int currentHeight = Character.getNumericValue(map[r].charAt(c));

        int score = 0;

        if (r > 0 && Character.getNumericValue(map[r - 1].charAt(c)) == currentHeight + 1) {
            //explore up
            score += ScoreTrailhead_Part2(map, r - 1, c);
        }
        if (c < map[0].length() - 1 && Character.getNumericValue(map[r].charAt(c + 1)) == currentHeight + 1) {
            //explore right
            score += ScoreTrailhead_Part2(map, r, c + 1);
        }
        if (r < map.length - 1 && Character.getNumericValue(map[r + 1].charAt(c)) == currentHeight + 1) {
            //explore down
            score += ScoreTrailhead_Part2(map, r + 1, c);
        }
        if (c > 0 && Character.getNumericValue(map[r].charAt(c - 1)) == currentHeight + 1) {
            //explore left
            score += ScoreTrailhead_Part2(map, r, c - 1);
        }
        return score;
    }
}
