import java.io.FileReader;
import java.util.*;

public class App {
    public static void main(String[] args) {
		String inputFilePath = "src\\01.txt";
		List<String> lines = GetLines(inputFilePath);
        System.out.println(Part1(lines));
		System.out.println(Part2(lines));
    }

    public static List<String> GetLines(String filePath) {
        //Create a list that we'll store all of our lines in
        List<String> lines = new ArrayList<>();
		try {
			//Create a scanner that will read all of the contents of the input file
			Scanner in = new Scanner(new FileReader(filePath));
			while(in.hasNext()) {
				//Take each line that the scanner reads and store it as a new item in our list
				lines.add(in.next());
			}
			//Close the input scanner
			in.close();
		} catch (Exception e) {
			//do nothing
		}

		return lines;
    }

    public static int Part1(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            char left = ' ';
            char right = ' ';
            //find the left most digit
            for (int i  = 0; i < line.length(); i++) {
                if (left == ' ' && Character.isDigit(line.charAt(i))) {
                    left = line.charAt(i);
                }
            }

            //find the right most digit
            for (int i  = line.length() - 1; i >= 0; i--) {
                if (right == ' ' && Character.isDigit(line.charAt(i))) {
                    right = line.charAt(i);
                }
            }

            sum += Integer.parseInt(Character.toString(left) + Character.toString(right));
        }
        return sum;
    }

	public static int Part2(List<String> lines) {
        return 0;
	}
}