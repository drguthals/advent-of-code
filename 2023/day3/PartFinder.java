import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PartFinder {

    int partNumberSum;
    ArrayList<String> engineSchematic;
    boolean debug;
    int part;
    String outputText;
    Gears gears;

    public PartFinder(ArrayList<String> engineSchematic, boolean debug, int part) {
        partNumberSum = 0;
        this.engineSchematic = engineSchematic;
        this.debug = debug;
        this.part = part;
        outputText = "";
        if(part == 2) gears = new Gears(debug);
    }

    public int findPartNumbers() {
        // Loop through the schematic looking for numbers
        for(int index = 0; index < engineSchematic.size(); index++) {
            String engineSchematicLine = engineSchematic.get(index);
            findNumbers(index, engineSchematicLine);
        }

        if(debug) writeProcess(outputText);
        if(part == 1) return partNumberSum;
        if(part == 2) return gears.getGearSum();
        return -1;
    }

    public void findNumbers(int row, String engineSchematicLine) {
        String number = "";
        boolean partNumberFound = false;

        // Loop through the line looking for numbers
        for(int index = 0; index < engineSchematicLine.length();) {
            char character = engineSchematicLine.charAt(index);

            // If the current character is a digit, look around to find a symbol
            if( Character.isDigit(character)) {
                number += character;
                if(!partNumberFound) partNumberFound = checkForSymbol(row, index);
                index++;
                continue;
            } else if(!number.equals("") && partNumberFound) {
                int partNumber = Integer.valueOf(number);
                if(part == 1) {
                    if(debug) outputText += (number + " + ");
                    partNumberSum += partNumber;
                }
                else if(part ==2) {
                    if(debug) outputText += ("Part: " + partNumber + "\n");
                    gears.addPartToGear(partNumber);
                }
                partNumberFound = false;
                number = "";
            } else if(!number.equals("") && !partNumberFound) {
                number = "";
            }
            index++;
        }
        if(debug && part==1) outputText = outputText.substring(0, outputText.length()-2);
        if(debug && part==1) outputText += ("= " + partNumberSum + "\n");
    }

    public boolean checkForSymbol(int row, int column) {
        String previousRow = "";
        String currentRow = engineSchematic.get(row);
        String nextRow = "";
        char prevRowPrevChar = '.';
        char prevRowCurrChar = '.';
        char prevRowNextChar = '.';

        char currRowPrevChar = '.';
        char currRowNextChar = '.';

        char nextRowPrevChar = '.';
        char nextRowCurrChar = '.';
        char nextRowNextChar = '.';

        if( row > 0 ) {
            previousRow = engineSchematic.get(row - 1);
            prevRowCurrChar = previousRow.charAt(column);
            if((part == 1) && !Character.isDigit(prevRowCurrChar) && (prevRowCurrChar != '.')) return true;
            if((part == 2) && prevRowCurrChar == '*') {
                if(debug) outputText += ("("+(row-1)+", "+(column) + ")\n");
                return gears.addGear(row-1, column);
            }
            if(column > 0) {
                prevRowPrevChar = previousRow.charAt(column - 1);
                if((part == 1) && !Character.isDigit(prevRowPrevChar) && (prevRowPrevChar != '.')) return true;
                if((part == 2) && prevRowPrevChar == '*') {
                    if(debug) outputText += ("("+(row-1)+", "+(column-1) + ")\n");
                    return gears.addGear(row-1, column-1);
                } 
            }
            if(column < previousRow.length()-1) {
                prevRowNextChar = previousRow.charAt(column + 1);
                if((part == 1) && !Character.isDigit(prevRowNextChar) && (prevRowNextChar != '.')) return true;
                if((part == 2) && prevRowNextChar == '*') {
                    if(debug) outputText += ("("+(row-1)+", "+(column+1) + ")\n");
                    return gears.addGear(row-1, column+1);
                }
            }
        }

        if(column > 0) {
            currRowPrevChar = currentRow.charAt(column - 1);
            if((part == 1) && !Character.isDigit(currRowPrevChar) && (currRowPrevChar != '.')) return true;
            if((part == 2) && currRowPrevChar == '*') {
                if(debug) outputText += ("("+(row)+", "+(column-1) + ")\n");
                return gears.addGear(row, column-1);
            }
        }
        if(column < currentRow.length()-1) {
            currRowNextChar = currentRow.charAt(column + 1);
            if((part == 1) && !Character.isDigit(currRowNextChar) && (currRowNextChar != '.')) return true;
            if((part == 2) && currRowNextChar == '*') {
                if(debug) outputText += ("("+(row)+", "+(column+1) + ")\n");
                return gears.addGear(row, column+1);
            }
        }

        if(row < engineSchematic.size()-1) {
            nextRow = engineSchematic.get(row+1);
            nextRowCurrChar = nextRow.charAt(column);
            if((part == 1) && !Character.isDigit(nextRowCurrChar) && (nextRowCurrChar != '.')) return true;
            if((part == 2) && nextRowCurrChar == '*') {
                if(debug) outputText += ("("+(row+1)+", "+(column) + ")\n");
                return gears.addGear(row+1, column);
            }
            if(column > 0) {
                nextRowPrevChar = nextRow.charAt(column - 1);
                if((part == 1) && !Character.isDigit(nextRowPrevChar) && (nextRowPrevChar != '.')) return true;
                if((part == 2) && nextRowPrevChar == '*') {
                    if(debug) outputText += ("("+(row+1)+", "+(column-1) + ")\n");
                    return gears.addGear(row+1, column-1);
                }
            } 
            if(column < nextRow.length()-1) {
                nextRowNextChar = nextRow.charAt(column + 1);
                if((part == 1) && !Character.isDigit(nextRowNextChar) && (nextRowNextChar != '.')) return true;
                if((part == 2) && nextRowNextChar == '*') {
                    if(debug) outputText += ("("+(row+1)+", "+(column+1) + ")\n");
                    return gears.addGear(row+1, column+1);
                }
            }
        }

        return false;
    }
    
    public void writeProcess(String line) {
        try {
            File processDoc = new File("output.txt");
            FileWriter myWriter = new FileWriter(processDoc);
            myWriter.write(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
