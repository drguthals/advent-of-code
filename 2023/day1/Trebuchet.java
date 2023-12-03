import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trebuchet {
    static ArrayList<String> calibrationData = new ArrayList<String>();
    static TextNumbers converter = new TextNumbers();
    static int part = 1;
    static boolean debug = true;
    static String filename = "input.txt";

    public static void main( String [] args) {
        readCalibrationData();
        if(debug) writeCleanedInput();

        if(calibrationData.size() > 0) {
            CalibrationDoc calibrationDoc = new CalibrationDoc(calibrationData);

            int calibrationValueSum = calibrationDoc.determineCalibrationValues(debug);

            System.out.println("The sum of the calibration values is: " + calibrationValueSum);
        } else {
            System.out.println("I'm sorry, but the Calidbration Document is empty.");
        }
    }

    public static void readCalibrationData() { 

        try {
            File calibrationText = new File(filename);
            Scanner myReader = new Scanner(calibrationText);

            // Read in the entire file into the arraylist
            while (myReader.hasNextLine()) {
                String calibrationLine = myReader.nextLine();
                if(calibrationLine.length() > 0 ) {
                    if(part == 1) {
                        calibrationData.add(calibrationLine);
                    } else {
                        String calibrationLineCleaned = "";
                        calibrationLineCleaned += converter.findNumberWords(calibrationLine);
                        if(!calibrationLineCleaned.equals(""))
                            calibrationData.add(calibrationLineCleaned);
                    }
                    
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeCleanedInput() {
        try {
            File cleanedInputDoc = new File("cleanedInput.txt");
            FileWriter myWriter = new FileWriter(cleanedInputDoc);
            String doc = "";
            for(String line : calibrationData) {
                doc += (line);
            }   
            myWriter.write(doc);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
    }
}
