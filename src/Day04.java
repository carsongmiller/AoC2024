
public class Day04 {
    public static int Part1(String text) {
        var lines = text.split("\n");
        int matchCount = 0;
        for (int row = 0; row < lines.length; row++) {
            for (int col = 0; col < lines[0].length(); col++) {
                if (lines[row].charAt(col) == 'X') {
                    matchCount += CountMatchesPart1(row, col, lines);
                }
            }
        }
        return matchCount;
    }

    public static int CountMatchesPart1(int r, int c, String[] lines) {
        int matchCount = 0;
        String[] words = {"","","","","","","",""};

        if (r >= 3) {
            if (c >= 3){
                words[0] = "" + lines[r].charAt(c) + lines[r-1].charAt(c-1) + lines[r-2].charAt(c-2) + lines[r-3].charAt(c-3);
            }
            words[1] = "" + lines[r].charAt(c) + lines[r-1].charAt(c) + lines[r-2].charAt(c) + lines[r-3].charAt(c);
            if (c <= lines[0].length() - 4) {
                words[2] = "" + lines[r].charAt(c) + lines[r-1].charAt(c+1) + lines[r-2].charAt(c+2) + lines[r-3].charAt(c+3);
            }
        }

        if (c <= lines[0].length() - 4) {
            words[3] = "" + lines[r].charAt(c) + lines[r].charAt(c+1) + lines[r].charAt(c+2) + lines[r].charAt(c+3);
        }

        if (r <= lines.length - 4) {
            if (c <= lines[0].length() - 4) {
                words[4] = "" + lines[r].charAt(c) + lines[r+1].charAt(c+1) + lines[r+2].charAt(c+2) + lines[r+3].charAt(c+3);
            }
            words[5] = "" + lines[r].charAt(c) + lines[r+1].charAt(c) + lines[r+2].charAt(c) + lines[r+3].charAt(c);
            if (c >= 3){
                words[6] = "" + lines[r].charAt(c) + lines[r+1].charAt(c-1) + lines[r+2].charAt(c-2) + lines[r+3].charAt(c-3);
            }
        }

        if (c >= 3) {
            words[7] = "" + lines[r].charAt(c) + lines[r].charAt(c-1) + lines[r].charAt(c-2) + lines[r].charAt(c-3);
        }

        for (String word : words) {
            if (word.equals("XMAS")) {
                matchCount++;
            }
        }

        return matchCount;
    }

	public static int Part2(String text) {
        var lines = text.split("\n");
        int matchCount = 0;
        for (int row = 1; row < lines.length - 1; row++) {
            for (int col = 1; col < lines[0].length() - 1; col++) {
                if (lines[row].charAt(col) == 'A') {
                    if (CountMatchesPart2(row, col, lines)) {
                        matchCount++;
                    }
                }
            }
        }
        return matchCount;
	}

    public static boolean CountMatchesPart2(int r, int c, String[] lines) {
        int matchCount = 0;
        String word = "" + lines[r-1].charAt(c-1) + lines[r].charAt(c) + lines[r+1].charAt(c+1);
        String wordBackwards = new StringBuilder(word).reverse().toString();
        if (!word.equals("MAS") && !wordBackwards.equals("MAS")) {
            return false;
        }

        word = "" + lines[r-1].charAt(c+1) + lines[r].charAt(c) + lines[r+1].charAt(c-1);
        wordBackwards = new StringBuilder(word).reverse().toString();
        if (!word.equals("MAS") && !wordBackwards.equals("MAS")) {
            return false;
        }

        return true;
    }
}
