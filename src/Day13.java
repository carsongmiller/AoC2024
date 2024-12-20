
import java.util.ArrayList;




public class Day13 {
    public static int Part1(String text) {
        var machinesRaw = text.split("\n\n");
        var machinesList = new ArrayList<Machine>();
        for (var machineDesc : machinesRaw) {
            var newMachine = new Machine(machineDesc, false);
            machinesList.add(newMachine);
        }

        int sum = 0;
        for (var machine : machinesList) {
            var steps = machine.getMinMoves();
            if (steps[0] < 100 && steps[1] < 100 && steps[0] == (int)steps[0] && steps[1] == (int)steps[1]){
                sum += steps[0] * 3 + steps[1];
            }
            
        }

        return sum;
    }
    

	public static long Part2(String text) {
        var machinesRaw = text.split("\n\n");
        var machinesList = new ArrayList<Machine>();
        for (var machineDesc : machinesRaw) {
            var newMachine = new Machine(machineDesc, true);
            machinesList.add(newMachine);
        }

        long sum = 0;
        for (var machine : machinesList) {
            var steps = machine.getMinMoves();
            if (steps[0] == (long)steps[0] && steps[1] == (long)steps[1]){
                sum += steps[0] * 3 + steps[1];
            }
        }

        return sum;
	}
}

class Machine {
    double xa;
    double ya;
    double xb;
    double yb;
    double xprize;
    double yprize;

    public Machine(String rawText, boolean part2) {
        var lines = rawText.split("\n");
        var aRaw = lines[0].split(": ")[1].split(", ");
        xa = Integer.parseInt(aRaw[0].substring(2));
        ya = Integer.parseInt(aRaw[1].substring(2));

        var bRaw = lines[1].split(": ")[1].split(", ");
        xb = Integer.parseInt(bRaw[0].substring(2));
        yb = Integer.parseInt(bRaw[1].substring(2));

        var prizeRaw = lines[2].split(": ")[1].split(", ");
        xprize = Integer.parseInt(prizeRaw[0].substring(2));
        yprize = Integer.parseInt(prizeRaw[1].substring(2));

        if (part2) {
            xprize += 10000000000000.0;
            yprize += 10000000000000.0;
        }
    }

    public double[] getMinMoves() {
        var B = ((yprize * xa) - (ya * xprize)) / ((yb * xa) - (ya * xb));
        var A = (xprize - (B * xb)) / xa;
        return new double[]{A, B};
    }
}