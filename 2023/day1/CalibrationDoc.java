import java.io.File;  // Import the File class
import java.util.ArrayList;
import java.lang.Character;
import java.lang.String;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

/*
    Thanks W3Schools for the copy and paste of things I forgot
*/
public class CalibrationDoc {

    public String outputText = "";
    public boolean debug;
    ArrayList<String> calibrationData;

    public CalibrationDoc (ArrayList<String> calibrationData) {
        this.calibrationData = calibrationData;
    }

    public int determineCalibrationValues(boolean debug) {
        this.debug = debug;
        int calibrationValueSum = 0;
        for(String calibrationLine : calibrationData ) {
            if(debug) outputText += ("Calibration Line: " + calibrationLine + " *** ");
            String calibrationDigits = "";
            for(int index = 0; index < calibrationLine.length(); index++) {
                char calibrationChar = calibrationLine.charAt(index);
                if(Character.isDigit(calibrationLine.charAt(index))) {
                    calibrationDigits += calibrationChar;
                }
            }
            if(debug) outputText += ("Calibration Digits: " + calibrationDigits + " *** ");
            if(calibrationDigits.length() > 0){
                int calibrationValue = 0;
                char calibrationValue1 = calibrationDigits.charAt(0);
                char calibrationValue2 = calibrationDigits.charAt(calibrationDigits.length()-1);
                String calibrationValues = ("" + calibrationValue1 + calibrationValue2);
                calibrationValue = Integer.valueOf(calibrationValues);
                if(debug) outputText += ("Calibration Value: " + calibrationValue + "\n");

                calibrationValueSum += calibrationValue;
            }
        }

        if(debug) writeProcess(outputText);
        
        return calibrationValueSum;
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