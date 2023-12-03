import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CubeConundrum {

    static String filename = "input.txt";
    static int part = 1;
    static ArrayList<String> gameData = new ArrayList<String>();
    static int maxRed = 12;
    static int maxGreen = 13;
    static int maxBlue = 14;
    static GameCounter counter;
    static boolean debug = true;

    public static void main(String [] args) {
        readGameData();
        counter = new GameCounter(gameData, maxRed, maxGreen, maxBlue, debug, part);
        counter.countGames();
        if(part  == 1 )
            System.out.println("Passing Games Sum is: " + counter.getGameCount());
        if(part  == 2 )
            System.out.println("Power Sum is: " + counter.getPowerSum());
    }

    public static void readGameData() { 
        try {
            File gameDataFile = new File(filename);
            Scanner myReader = new Scanner(gameDataFile);

            // Read in the entire file into the arraylist
            while (myReader.hasNextLine()) {
                String gameDataText = myReader.nextLine();
                if(gameDataText.length() > 0 ) {
                    gameData.add(gameDataText);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
