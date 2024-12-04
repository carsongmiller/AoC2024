import java.nio.file.Files;
import java.nio.file.Paths;

public class main {
    public static void main(String[] args) {
        //Change the following text file to reference different days
		var inputFilePath = "inputs\\02.txt";
		var text = GetLines(inputFilePath);
        
        //Change the day class in the following two lines to run different days
        System.out.println("Part 1: " + Day02.Part1(text));
		System.out.println("Part 2: " + Day02.Part2(text));
    }

    //Returns all lines of a given text file as a list of strings
    public static String GetLines(String filePath) {
        //Create a list that we'll store all of our lines in
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            //do nothing
        }
		return text;
    }
}