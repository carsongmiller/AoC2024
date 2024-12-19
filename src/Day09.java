import java.util.ArrayList;


public class Day09 {
    public static long Part1(String text) {
        var resultMemory = new ArrayList<int[]>(); //[0] = ID, [1] = size
        text = text.strip();
        if (text.length() % 2 == 0) { //if the text's length is even, the last entry is just empty space.  Remove it
            text = text.substring(0, text.length() - 2);
        }

        var numFiles = (text.length() + 1) / 2;
        var currentTailID = numFiles - 1;

        
        for (int i = 0 ; i < text.length(); i++) {
            if (i % 2 == 0) {
                //this digit represents a file's size
                resultMemory.add(new int[]{i / 2, Character.getNumericValue(text.charAt(i))});
            }
            else {
                //this digit represents a free block's size
                var sizeToFill = Character.getNumericValue(text.charAt(i));
                while (sizeToFill > 0) {
                    var lastFileSize = Character.getNumericValue(text.charAt(text.length() - 1));
                    if (sizeToFill >= lastFileSize) {
                        resultMemory.add(new int[]{currentTailID, lastFileSize});
                        text = text.substring(0, text.length() - 2); //we've moved the entire last file, remove it from the text
                        currentTailID--;
                        sizeToFill -= lastFileSize;
                    }
                    else {
                        //the tail file is larger than the space we're trying to fill
                        resultMemory.add(new int[]{currentTailID, sizeToFill});
                        var tailFileNewSize = Character.getNumericValue(text.charAt(text.length() - 1)) - sizeToFill;
                        text = text.substring(0, text.length() - 1) + ((char) (tailFileNewSize + '0')); //change the end of the memory to show the tail file's new size
                        sizeToFill = 0;
                    }
                }
            }
        }

        //now sum up the results
        long sum = 0;
        int index = 0;
        for (var block : resultMemory) {
            for (int i = 0; i < block[1]; i++) {
                sum += index++ * block[0];
            }
        }

        return sum;
    }

	public static long Part2(String text) {

        text = text.strip();
        if (text.length() % 2 == 0) { //if the text's length is even, the last entry is just empty space.  Remove it
            text = text.substring(0, text.length() - 2);
        }

        var memorySize = 0;
        for (int i = 0; i < text.length(); i++) {
            memorySize += Character.getNumericValue(text.charAt(i));
        }

        var memory = new int[memorySize];

        int memPointer = 0;
        for (int i = 0; i < text.length(); i++) {
            var blockSize = Character.getNumericValue(text.charAt(i));
            for (int j = 0; j < blockSize; j++) {
                if (i % 2 == 0) {
                    memory[memPointer++] = i/2;
                }
                else {
                    memory[memPointer++] = -1;
                }
            }
        }

        int blockToMoveID = memory[memory.length - 1];
        int blockToMoveStartIndex = memory.length - 1;
        while (blockToMoveID > 0) {
            //find the chunk of memory that the tail belongs to
            int blockToMoveSize = 0;
            boolean blockEncountered = false;

            for (int i = blockToMoveStartIndex; i >= 0; i--) {
                if (memory[i] == blockToMoveID) {
                    blockToMoveSize++;
                    blockEncountered = true;
                }
                else if (blockEncountered) {
                    blockToMoveStartIndex = i + 1;
                    break;
                }
            }

            for (int i = 0; i < blockToMoveStartIndex; i++) {
                //walk from the front until you find a blank space
                if (memory[i] != -1) continue;

                //find the blank space's size
                int blankSpaceSize = 1;
                for (int j = i+1; j < memory.length; j++) {
                    if (memory[j] == -1) 
                        blankSpaceSize++;
                    else
                        break;
                }

                if (blankSpaceSize >= blockToMoveSize) {
                    for (int j = 0; j < blockToMoveSize; j++) {
                        memory[i + j] = blockToMoveID;
                        memory[blockToMoveStartIndex + j] = -1;
                    }
                    break;
                }
                else {
                    i += blankSpaceSize - 1; //skip to the end of the blank space before looking for the next one
                }
            }
            blockToMoveID--; //now try to move the next ID
        }

        //now sum up the results
        long sum = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] != -1)
                sum += i * memory[i];
        }

        return sum;
	}
}
