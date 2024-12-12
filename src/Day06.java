import java.util.HashSet;

public class Day06 {
    public static int Part1(String text) {
        var lines = text.split("\n");
        var visited = new HashSet<>();
        var facing = 0; //0 = up, 1 = right, 2 = down, 3 = left
        var width = lines[0].length();

        int guardRow = 0;
        int guardCol = 0;
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length(); col++) {
                if (lines[row].charAt(col) == '^') {
                    guardRow = row;
                    guardCol = col;
                    lines[row] = lines[row].replace('^', '.');
                }
            }
        }

        visited.add(guardRow * width + guardCol);

        boolean escaped = false;
        while (!escaped) {
            //if the guard's on the edge of the map, break
            if (guardRow == 0 || guardRow == lines.length - 1 || guardCol == 0 || guardCol == lines[0].length() - 1) {
                break; //guard got out
            }

            //check what's in front of the guard and move or turn
            switch (facing) {
                case 0 -> {
                    if (lines[guardRow - 1].charAt(guardCol) == '.') {
                        guardRow--;
                        visited.add(guardRow * width + guardCol);
                    }
                    else {
                        facing = (facing + 1) % 4;
                    }
                }
                case 1 -> {
                    if (lines[guardRow].charAt(guardCol + 1) == '.') {
                        guardCol++;
                        visited.add(guardRow * width + guardCol);
                    }
                    else {
                        facing = (facing + 1) % 4;
                    }
                }
                case 2 -> {
                    if (lines[guardRow + 1].charAt(guardCol) == '.') {
                        guardRow++;
                        visited.add(guardRow * width + guardCol);
                    }
                    else {
                        facing = (facing + 1) % 4;
                    }
                }
                case 3 -> {
                    if (lines[guardRow].charAt(guardCol - 1) == '.') {
                        guardCol--;
                        visited.add(guardRow * width + guardCol);
                    }
                    else {
                        facing = (facing + 1) % 4;
                    }
                }
                default -> {
                }
            }
        }


        return visited.size();
    }

	public static int Part2(String text) {
        var lines = text.split("\n");
        var facing = 0; //0 = up, 1 = right, 2 = down, 3 = left
        var width = lines[0].length();

        int guardRowStart = 0;
        int guardColStart = 0;
        int loopCount = 0;

        
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[row].length(); col++) {
                if (lines[row].charAt(col) == '^') {
                    guardRowStart = row;
                    guardColStart = col;
                    lines[row] = lines[row].replace('^', '.');
                }
            }
        }

        for (int i = 0; i < lines.length * lines[0].length(); i++) {
            var visited = new HashSet<Integer>();
            int guardRow = guardRowStart;
            int guardCol = guardColStart;
            facing = 0;

            var insertCol = i % width;
            var insertRow = (i - (insertCol)) / width;
            if (lines[insertRow].charAt(insertCol) == '#') continue; //early exit, the loc is already a barrier

            visited.add(guardRowStart * width + guardColStart + (facing * width * lines.length));
            
            //try placing a barrier at each position and see if we reach a loop
            var sb = new StringBuilder(lines[insertRow]);
            sb.setCharAt(insertCol, '#');
            lines[insertRow] = sb.toString();
            
            boolean escaped = false;
            while (!escaped) {
                //if the guard's on the edge of the map, break
                
                if (guardRow == 0 || guardRow == lines.length - 1 || guardCol == 0 || guardCol == lines[0].length() - 1) {
                    escaped = true;
                    break; //guard got out
                }

                //check what's in front of the guard and move or turn
                switch (facing) {
                    case 0 -> {
                        if (lines[guardRow - 1].charAt(guardCol) == '.') {
                            guardRow--;
                        }
                        else {
                            facing = (facing + 1) % 4;
                        }
                    }
                    case 1 -> {
                        if (lines[guardRow].charAt(guardCol + 1) == '.') {
                            guardCol++;
                        }
                        else {
                            facing = (facing + 1) % 4;
                        }
                    }
                    case 2 -> {
                        if (lines[guardRow + 1].charAt(guardCol) == '.') {
                            guardRow++;
                        }
                        else {
                            facing = (facing + 1) % 4;
                        }
                    }
                    case 3 -> {
                        if (lines[guardRow].charAt(guardCol - 1) == '.') {
                            guardCol--;
                        }
                        else {
                            facing = (facing + 1) % 4;
                        }
                    }
                    default -> {
                        System.out.println("shouldn't be here");
                    }
                }

                if (visited.contains(guardRow * width + guardCol + (facing * width * lines.length))){
                    loopCount++;
                    break;
                }
                else {
                    visited.add(guardRow * width + guardCol + (facing * width * lines.length));
                }
            }

            //reset the maze to how it was before
            sb.setCharAt(insertCol, '.');
            lines[insertRow] = sb.toString();
        }

        return loopCount;
	}
}
