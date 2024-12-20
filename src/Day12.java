
import java.util.HashSet;


public class Day12 {
    public static int Part1(String text) {
        var countedList = new HashSet<Integer>();
        var map = text.split("\n");

        int cost = 0;

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++) {
                if (!countedList.contains(r * map[0].length() + c)){
                    //this is a new plant
                    var newRegionMembers = new HashSet<Integer>();
                    var result = FindRegion(map, r, c, newRegionMembers);
                    countedList.addAll(newRegionMembers);
                    cost += result[0] * result[1];
                }
            }
        }

        
        return cost;
    }

    //returns an int array of size 2 with [0] = perimiter of the region containing this plant, and [1] = area
    public static int[] FindRegion(String[] map, int r, int c, HashSet<Integer> members) {

        members.add(map[0].length() * r + c);
        var width = map[0].length();

        //look at this plant's neighbors
        //for each neighbor that isn't matching, add 1 to perimiter
        //if a neighbor is matching, increment area by 1 and call this function recursively
        var plantType = map[r].charAt(c);
        int thisPerimiter = 0;
        int thisArea = 1;

        //up
        if (r > 0 && map[r-1].charAt(c) == plantType && !members.contains((r-1) * width + c)) {
            var result = FindRegion(map, r-1, c, members);
            thisPerimiter += result[0];
            thisArea += result[1];
        }
        else if (r <= 0 || map[r-1].charAt(c) != plantType) {
            thisPerimiter++;
        }

        //down
        if (r < map.length - 1 && map[r+1].charAt(c) == plantType && !members.contains((r+1) * width + c)) {
            var result = FindRegion(map, r+1, c, members);
            thisPerimiter += result[0];
            thisArea += result[1];
        }
        else if (r >= map.length - 1 || map[r+1].charAt(c) != plantType) {
            thisPerimiter++;
        }

        //left
        if (c > 0 && map[r].charAt(c-1) == plantType && !members.contains(r * width + (c-1))) {
            var result = FindRegion(map, r, c-1, members);
            thisPerimiter += result[0];
            thisArea += result[1];
        }
        else if (c <= 0 || map[r].charAt(c-1) != plantType) {
            thisPerimiter++;
        }

        //right
        if (c < map[r].length() - 1 && map[r].charAt(c+1) == plantType && !members.contains(r * width + (c+1))) {
            var result = FindRegion(map, r, c+1, members);
            thisPerimiter += result[0];
            thisArea += result[1];
        }
        else if (c >= map[r].length() - 1 || map[r].charAt(c+1) != plantType) {
            thisPerimiter++;
        }

        return new int[]{thisPerimiter, thisArea};
    }

	public static int Part2(String text) {
        var countedList = new HashSet<Integer>();
        var map = text.split("\n");

        int cost = 0;

        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length(); c++) {
                if (!countedList.contains(r * map[0].length() + c)){
                    //this is a new plant
                    var newRegionMembers = new HashSet<Integer>();
                    var result = FindRegion(map, r, c, newRegionMembers);
                    countedList.addAll(newRegionMembers);
                    var corners = CountCorners(newRegionMembers, map[0].length());
                    cost += corners * result[1];
                }
            }
        }

        return cost;
	}

    public static int CountCorners(HashSet<Integer> members, int mapWidth) {
        int corners = 0;
        for (var member : members) {
            int up = member - mapWidth;
            int down = member + mapWidth;
            int left = member - 1;
            int right = member + 1;

            int upRight = up + 1;
            int downRight = down + 1;
            int downLeft = down - 1;
            int upleft = up - 1;

            //check for each convex corner
            if (!members.contains(up) && !members.contains(right)) corners++; //upper right
            if (!members.contains(right) && !members.contains(down)) corners++; //lower right
            if (!members.contains(down) && !members.contains(left)) corners++; //lower left
            if (!members.contains(left) && !members.contains(up)) corners++; //upper left

            //check for each concave corner
            if (members.contains(up) && members.contains(right) && !members.contains(upRight)) corners++; //upper right
            if (members.contains(right) && members.contains(down) && !members.contains(downRight)) corners++; //lower right
            if (members.contains(down) && members.contains(left) && !members.contains(downLeft)) corners++; //lower left
            if (members.contains(left) && members.contains(up) && !members.contains(upleft)) corners++; //upper left
        }
        return corners;
    }

    
}
