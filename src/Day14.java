
import java.io.File;
import java.util.ArrayList;


public class Day14 {
    public static long Part1(String text) {
        var lines = text.split("\n");
        var robots = new ArrayList<int[]>();
        for (var line : lines) {
            var newRobot = new int[4];
            var p = line.split(" ")[0].substring(2);
            var v = line.split(" ")[1].substring(2);
            newRobot[0] = Integer.parseInt(p.split(",")[0]);
            newRobot[1] = Integer.parseInt(p.split(",")[1]);
            newRobot[2] = Integer.parseInt(v.split(",")[0]);
            newRobot[3] = Integer.parseInt(v.split(",")[1]);
            robots.add(newRobot);
        }

        int steps = 100;
        int w = 101;
        int h = 103;

        var quadrantCounts = new long[]{0, 0, 0, 0};

        for (int step = 0; step < steps; step++) {
            for (var robot : robots) {
                robot[0] = (robot[0] + w + robot[2]) % w;
                robot[1] = (robot[1] + h + robot[3]) % h;
            }
        }

        for (var robot : robots) {
            if (robot[0] < w/2) {
                if (robot[1] < h/2) quadrantCounts[0]++;
                else if (robot[1] > h/2) quadrantCounts[1]++;
            }
            else if (robot[0] > w/2) {
                if (robot[1] < h/2) quadrantCounts[2]++;
                else if (robot[1] > h/2) quadrantCounts[3]++;
            }
        }

        return quadrantCounts[0] * quadrantCounts[1] * quadrantCounts[2] * quadrantCounts[3];
    }

	public static int Part2(String text) {
        var lines = text.split("\n");
        var robots = new ArrayList<int[]>();
        for (var line : lines) {
            var newRobot = new int[4];
            var p = line.split(" ")[0].substring(2);
            var v = line.split(" ")[1].substring(2);
            newRobot[0] = Integer.parseInt(p.split(",")[0]);
            newRobot[1] = Integer.parseInt(p.split(",")[1]);
            newRobot[2] = Integer.parseInt(v.split(",")[0]);
            newRobot[3] = Integer.parseInt(v.split(",")[1]);
            robots.add(newRobot);
        }

        int steps = 1000000;
        int w = 101;
        int h = 103;

        for (int step = 0; step < steps; step++) {
            for (var robot : robots) {
                robot[0] = (robot[0] + w + robot[2]) % w;
                robot[1] = (robot[1] + h + robot[3]) % h;
            }
            //after each step, see if there's a cluster of 3x3 cells that are all populated
            //if there are, we assume that's the tree and stop
            if (containsCluster(makeMap(robots, w, h), 3)) {
                printRobots(makeMap(robots, w, h), step);
                return step + 1;
            }
        }

        return 0;
	}

    public static void printRobots(int[][] map, int step) {
        System.out.println(step);

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                if (map[r][c] > 0) System.out.print(map[r][c]);
                else System.out.print('.');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[][] makeMap(ArrayList<int[]> robots, int w, int h) {
        var map = new int[h][w];
        for (var robot : robots) {
            map[robot[1]][robot[0]]++;
        }
        return map;
    }

    public static boolean containsCluster(int[][] map, int clusterSize){
        for (int r = 0; r < map.length - clusterSize; r++) {
            for (int c = 0; c < map[0].length - clusterSize; c++) {
                boolean containsCluster = true;
                for (int i = 0; i < clusterSize; i++) {
                    for (int j = 0; j < clusterSize; j++) {
                        if (map[r+i][c+j] == 0){
                            containsCluster = false;
                            break;
                        }
                    }
                    if (!containsCluster) break;
                }
                if (containsCluster) return true;
            }
        }
        return false;
    }
}
