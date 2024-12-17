
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Day08 {
    public static int Part1(String text) {
        var lines = text.split("\n");
        var map = new HashMap<Character, ArrayList<int[]>>();
        //Make a hash map of the locations of all nodes in the map
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length(); col++) {
                if (lines[row].charAt(col) == '.') continue;

                if (!map.containsKey(lines[row].charAt(col))) {
                    map.put(lines[row].charAt(col), new ArrayList<int[]>());
                }
                map.get(lines[row].charAt(col)).add(new int[]{col, row});
            }
        }

        var width = lines[0].length();
        var height = lines.length;

        //now loop through each position in the map, and for each frequency, calculate the distance to each tower
        var set = new HashSet<Integer>();
        for (var key : map.keySet()) {
            var pointList = map.get(key);
            for (int i = 0; i < pointList.size(); i++) {
                for (int j = i + 1; j < pointList.size(); j++) {
                    if (j == i) continue;

                    var point1 = pointList.get(i);
                    var point2 = pointList.get(j);

                    var dx = point2[0] - point1[0];
                    var dy = point2[1] - point1[1];
                    var antiNode1 = new int[]{point1[0] - dx, point1[1] - dy};
                    var antiNode2 = new int[]{point2[0] + dx, point2[1] + dy};
                    
                    if (antiNode1[0] >= 0 && antiNode1[0] < width && antiNode1[1] >= 0 && antiNode1[1] < height){
                        set.add(antiNode1[1] * width + antiNode1[0]);
                    }
                    if (antiNode2[0] >= 0 && antiNode2[0] < width && antiNode2[1] >= 0 && antiNode2[1] < height){
                        set.add(antiNode2[1] * width + antiNode2[0]);
                    }
                }
            }
        }
        return set.size();
    }

	public static int Part2(String text) {
        var lines = text.split("\n");
        var map = new HashMap<Character, ArrayList<int[]>>();
        //Make a hash map of the locations of all nodes in the map
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length(); col++) {
                if (lines[row].charAt(col) == '.') continue;

                if (!map.containsKey(lines[row].charAt(col))) {
                    map.put(lines[row].charAt(col), new ArrayList<int[]>());
                }
                map.get(lines[row].charAt(col)).add(new int[]{col, row});
            }
        }

        var width = lines[0].length();
        var height = lines.length;

        //now loop through each position in the map, and for each frequency, calculate the distance to each tower
        var set = new HashSet<Integer>();
        for (var key : map.keySet()) {
            var pointList = map.get(key);
            for (int i = 0; i < pointList.size(); i++) {
                for (int j = i + 1; j < pointList.size(); j++) {
                    if (j == i) continue;

                    var point1 = pointList.get(i);
                    var point2 = pointList.get(j);

                    var riseRun = GetRiseAndRun(point2[0] - point1[0], point2[1] - point1[1]);

                    //walk one direction from point1
                    int stepSize = 0;
                    while (true) { 
                        var antiNode = new int[]{point1[0] - stepSize * riseRun[0], point1[1] - stepSize * riseRun[1]};
                        if (antiNode[0] >= 0 && antiNode[0] < width && antiNode[1] >= 0 && antiNode[1] < height){
                            set.add(antiNode[1] * width + antiNode[0]);
                        }
                        else {
                            break;
                        }
                        stepSize++;
                    }

                    //walk the other direction from point1
                    stepSize = 1;
                    while (true) { 
                        var antiNode = new int[]{point1[0] + stepSize * riseRun[0], point1[1] + stepSize * riseRun[1]};
                        if (antiNode[0] >= 0 && antiNode[0] < width && antiNode[1] >= 0 && antiNode[1] < height){
                            set.add(antiNode[1] * width + antiNode[0]);
                        }
                        else {
                            break;
                        }
                        stepSize++;
                    }
                }
            }
        }
        return set.size();
	}

    public static int[] GetRiseAndRun(int run, int rise) {
        return new int[]{run / gcd(run, rise), rise / gcd(run, rise)};
    }

    public static int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }
}
