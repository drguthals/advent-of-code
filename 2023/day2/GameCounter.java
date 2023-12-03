import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameCounter {
    ArrayList<String> gamesData;
    ArrayList<String> passingGames;
    int passingGamesSum;
    int part;
    String outputText;;
    boolean debug;
    int maxRed;
    int maxGreen;
    int maxBlue;
    int minRed;
    int minGreen;
    int minBlue;
    int powerSum;

    public GameCounter(ArrayList<String> gameData, int maxRed, int maxGreen, int maxBlue, boolean debug, int part) {
        this.gamesData = gameData;
        this.maxRed = maxRed;
        this.maxGreen = maxGreen;
        this.maxBlue = maxBlue;
        this.debug = debug;
        this.part = part;

        passingGames = new ArrayList<String>();
        outputText = "";
        passingGamesSum = 0;
        powerSum = 0;
    }

    public int getGameCount() {
        return passingGamesSum;
    }

    public int getPowerSum() {
        return powerSum;
    }

    public void countGames() {
        for( String gameData : gamesData) {
            int gameID = Integer.valueOf(gameData.substring(5).split(":")[0]);
            if(debug) outputText += ("Game " + gameID + ": ");
            boolean valid = true;

            String[] grabsData = gameData.split(":")[1].split(";");
            for( String grabData : grabsData ) {
                String [] grabByColor = grabData.split(",");
                int redGrab = 0;
                int greenGrab = 0;
                int blueGrab = 0;

                for( String grabColor : grabByColor ) {
                    if(grabColor.indexOf("red") > 0) {
                        redGrab = Integer.valueOf(grabColor.trim().split(" ")[0].trim());
                    } else if(grabColor.indexOf("green") > 0) {
                        greenGrab = Integer.valueOf(grabColor.trim().split(" ")[0].trim());
                    } else if(grabColor.indexOf("blue") > 0) {
                        blueGrab = Integer.valueOf(grabColor.trim().split(" ")[0].trim());
                    }
                }
                if(debug) outputText += (redGrab + "  red, " + greenGrab+ "  green, " + blueGrab+ "  blue;");
                
                if(part == 1) {
                    int [] colorCounts = {redGrab, greenGrab, blueGrab};
                    if(!verifyColorCounts(colorCounts)) {
                        valid = false;
                    }
                } else if(part == 2) {
                    if(redGrab > minRed) minRed = redGrab;
                    if(greenGrab > minGreen) minGreen = greenGrab;
                    if(blueGrab > minBlue) minBlue = blueGrab;
                }
            }
            if(debug) outputText += "\n";
            if((part == 1) && valid) passingGamesSum += gameID;
            if(part == 2) {
                if(debug) outputText += ("Red Min: " + minRed + ", Green Min: " + minGreen + ", Blue Min: " + minBlue + "\n");
                int power = (minRed * minGreen * minBlue);
                if(debug) outputText += ("Power: " + power + "\n");
                powerSum += power;
                if(debug) outputText += ("Power Sum So Far: " + powerSum + "\n");
                minRed = 0;
                minGreen = 0;
                minBlue = 0;
            }
            
        }
        if(debug) writeProcess(outputText);
    }

    public boolean verifyColorCounts(int [] colorCounts) {
        if(colorCounts[0] <= maxRed 
            && colorCounts[1] <= maxGreen
                && colorCounts[2] <= maxBlue) {
                    return true;
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
