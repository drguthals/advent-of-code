import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.Character;
import java.lang.String;

public class TextNumbers {
    HashMap<String, Integer> textToNumbers;
    String line = "";
    ArrayList<Character> firstCharNumbers = new ArrayList<Character>();
    String newLine = "";

    public TextNumbers() {
        textToNumbers = new HashMap<String, Integer>();
        textToNumbers.put("zero", 0);
        textToNumbers.put("one", 1);
        textToNumbers.put("two", 2);
        textToNumbers.put("three", 3);
        textToNumbers.put("four", 4);
        textToNumbers.put("five", 5);
        textToNumbers.put("six", 6);
        textToNumbers.put("seven", 7);
        textToNumbers.put("eight", 8);
        textToNumbers.put("nine", 9);

        Character [] characters = {'z', 'o', 't', 'f', 's', 'e', 'n'};
        firstCharNumbers.addAll(Arrays.asList(characters));
    }

    public String findNumberWords(String input){
        line = input;
        newLine = "";
        int pointer = 0;
        while(pointer < line.length()) {
            char character  = line.charAt(pointer);
            int length = 0;
            if(firstCharNumbers.contains(character) && ((pointer+1) < line.length())) {
                length = getLength(pointer);
                if((length > 0) && ((pointer+length) < (line.length()+1))){
                    String textNumber = line.substring(pointer, (pointer+length));
                    if(textToNumbers.containsKey(textNumber)){
                        int digit = getDigit(textNumber);
                        newLine += digit;
                    } else {
                        newLine += character;
                    }
                } else {
                    newLine += character;
                }
            } 
            // if the character isn't the start of a number word, just add it to the new line
            else {
                newLine += character;
            }
            pointer++;
        }
        newLine+="\n";
        return newLine;
    }

    public int getDigit(String text) {
        switch(text) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
        }
        return 0;
    }

    public int getLength(int index) {
        char character = line.charAt(index);

        switch (character) {
            case 'z':
                return 4;
            case 'o':
                return 3;
            case 'e':
                return 5;
            case 'n':
                return 4;
            case 't':
            case 'f':
            case 's':
                char nextCharacter = line.charAt(index+1);
                String twoCharacters = "" + character + nextCharacter;
                return getLength(twoCharacters);
            default:
                return 0;
        }
    }

    public int getLength(String characters) {
        switch (characters) {
            case "tw":
                return 3;
            case "th":
                return 5;
            case "fo":
                return 4;
            case "fi":
                return 4;
            case "si":
                return 3;
            case "se":
                return 5;
            default:
                return 0;
        }
    }
}