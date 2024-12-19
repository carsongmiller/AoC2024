
import java.util.ArrayList;
import java.util.HashMap;


public class Day11 {
    public static long Part1(String text) {
        text = text.strip();
        var map = new HashMap<Long, Long>();
        for (var num : text.split(" ")) {
            var value = Long.parseLong(num);
            if (!map.containsKey(value)) {
                map.put(value, (long)1);
            }
            else {
                map.put(value, map.get(value) + 1);
            }
        }

        int numSteps = 25;
        for (int step = 0; step < numSteps; step++) {
            map = StepStones(map);
        }

        long sum = 0;
        for (var val : map.values()) {
            sum += val;
        }
        return sum;
    }

	public static long Part2(String text) {
        text = text.strip();
        var map = new HashMap<Long, Long>();
        for (var num : text.split(" ")) {
            var value = Long.parseLong(num);
            if (!map.containsKey(value)) {
                map.put(value, (long)1);
            }
            else {
                map.put(value, map.get(value) + 1);
            }
        }

        int numSteps = 75;
        for (int step = 0; step < numSteps; step++) {
            map = StepStones(map);
        }

        long sum = 0;
        for (var val : map.values()) {
            sum += val;
        }
        return sum;
	}

    public static HashMap<Long, Long> StepStones(HashMap<Long, Long> map) {
        var keys = map.keySet();
        var newMap = new HashMap<Long, Long>();
        for (var key : keys) {
            if (key == 0) {
                if (newMap.containsKey((long)1)) {
                    newMap.put((long)1, newMap.get((long)1) + map.get((long)0));
                }
                else {
                    newMap.put((long)1, map.get((long)0));
                }
            }
            else if (key.toString().length() % 2 == 0) {
                var str = key.toString();
                var left = Long.parseLong(str.substring(0, str.length()/2));
                var right = Long.parseLong(str.substring(str.length()/2));
                if (newMap.containsKey(left)) {
                    newMap.put(left, newMap.get(left) + map.get(key));
                }
                else {
                    newMap.put(left, map.get(key));
                }
                
                if (newMap.containsKey(right)) {
                    newMap.put(right, newMap.get(right) + map.get(key));
                }
                else {
                    newMap.put(right, map.get(key));
                }
            }
            else {
                var newVal = key * (long)2024;
                if (newMap.containsKey(newVal)) {
                    newMap.put(newVal, newMap.get(newVal) + map.get(key));
                }
                else {
                    newMap.put(newVal, map.get(key));
                }
            }
        }
        return newMap;
    }
}
