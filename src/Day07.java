
import java.util.Arrays;


public class Day07 {
    public static long Part1(String text) {
        var lines = text.split("\n");
        long sum = 0;

        for (var equation : lines) {
            var temp = equation.split(": ");
            var solution = Long.parseLong(temp[0]);
            var operands = Arrays.stream(temp[1].split(" ")).mapToLong(Long::parseLong).toArray();

            for (int operatorList = 0; operatorList < Math.pow(2, operands.length - 1); operatorList++) {
                var result = operands[0];
                for (int i = 1; i < operands.length; i++) {
                    if (getNthBit(operatorList, i-1) == 0) { //addition
                        result += operands[i];
                    }
                    else if (getNthBit(operatorList, i-1) == 1) { //multiplication
                        result *= operands[i];
                    }
                }
                if (result == solution) {
                    sum += solution;
                    break;
                }
            }
        }

        return sum;
    }

    public static int getNthBit(int num, int n) {
        return (num >> n) & 1;
    }

    public static int getNthTernaryDigit(int number, int n) {
        return (number / (int) Math.pow(3, n)) % 3;
    }

	public static long Part2(String text) {
        var lines = text.split("\n");
        long sum = 0;

        for (var equation : lines) {
            var temp = equation.split(": ");
            var solution = Long.parseLong(temp[0]);
            var operands = Arrays.stream(temp[1].split(" ")).mapToLong(Long::parseLong).toArray();

            for (int operatorList = 0; operatorList < Math.pow(3, operands.length - 1); operatorList++) {
                var result = operands[0];
                for (int i = 1; i < operands.length; i++) {
                    if (getNthTernaryDigit(operatorList, i-1) == 0) { //addition
                        result += operands[i];
                    }
                    else if (getNthTernaryDigit(operatorList, i-1) == 1) { //multiplication
                        result *= operands[i];
                    }

                    else if (getNthTernaryDigit(operatorList, i-1) == 2) { //concatenation
                        result = Long.parseLong(String.valueOf(result) + String.valueOf(operands[i]));
                    }
                }
                if (result == solution) {
                    sum += solution;
                    break;
                }
            }
        }

        return sum;
	}
}
