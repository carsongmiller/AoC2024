
public class Day02 {
    public static int Part1(String text) {
        var lines = text.split("\n");
        int safeCount = 0;
        for (var report : lines) {
            var levels = report.split(" ");
            int lastLevel = Integer.parseInt(levels[0]);
            var lastDiff = 0;
            boolean safe = true;
            for (int i = 1; i < levels.length; i++) {
                int diff = lastLevel - Integer.parseInt(levels[i]);
                if (Math.abs(diff) > 3 || diff == 0 || (lastDiff * diff < 0)) {
                    safe = false;
                    break;
                }
                lastDiff = diff;
                lastLevel = Integer.parseInt(levels[i]);
            }
            if (safe) safeCount++;
        }
        return safeCount;
    }

	public static int Part2(String text) {
        var lines = text.split("\n");
        int safeCount = 0;
        for (var report : lines) {
            var levels = report.split(" ");
            
            
            for (int skipLevel = -1; skipLevel < levels.length; skipLevel++) {
                boolean safe = true;
                int lastLevel = 1000;
                var lastDiff = 0;

                for (int i = 0; i < levels.length; i++) {
                    if (i == skipLevel) {
                        continue;
                    }

                    if (lastLevel == 1000) { //this is the first level we're checking
                        lastLevel = Integer.parseInt(levels[i]);
                        continue;
                    }

                    int diff = lastLevel - Integer.parseInt(levels[i]);
                    if (Math.abs(diff) > 3 || diff == 0 || lastDiff * diff < 0) {
                        safe = false;
                        break;
                    }
                    lastDiff = diff;
                    lastLevel = Integer.parseInt(levels[i]);
                }
                if (safe) {
                    safeCount++;
                    break;
                }
            }
        }
        return safeCount;
	}
}
