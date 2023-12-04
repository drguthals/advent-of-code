import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EngineRepair {
    
    static String filename = "input.txt";
    static boolean debug = true;
    static int part = 2;
    static PartFinder partFinder;
    static ArrayList<String> engineSchematic = new ArrayList<String>();

    public static void main(String [] args) {
        readEngineSchematicData();
        partFinder = new PartFinder(engineSchematic, debug, part);
        System.out.println(partFinder.findPartNumbers());
    }

    public static void readEngineSchematicData() { 
        try {
            File engineSchematicFile = new File(filename);
            Scanner myReader = new Scanner(engineSchematicFile);

            // Read in the entire file into the arraylist
            while (myReader.hasNextLine()) {
                String engineSchematicLine = myReader.nextLine();
                if(engineSchematicLine.length() > 0 ) {
                    engineSchematic.add(engineSchematicLine);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
