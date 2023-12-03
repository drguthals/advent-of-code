# Advent of Code 2023 Day 1: Trebuchet

## Table of Contents
- [Part 1](#part-1)
- [Part 2](#part-2)

## Part 1
### Files
- [Trebuchet.java](./day1/Trebuchet.java): Runs the program
- [TextNumbers.java](./day1/TextNumbers.java): Converts word versions of digits to digits
- [CalibrationDoc.java](./day1/CalibrationDoc.java): Computes the calibration data
- [input.txt](./day1/input.txt): The input data I received for Day 1
- [cleanedInput.txt](./day1/cleanedInput.txt): The same input data for Day 1 but with word digits converted to digits
- [output.txt](./day1/output.txt): Helpful output when debugging

### Global Variables
- `boolean debug`: Set to `true` in `Trebuchet.java` so that when run, output data is written to `output.txt`
- `int part`: Either `1` or `2` depending on which part of the Day 1 challenge you are looking to solve
- `String filename`; Set to `index.txt` for the input data I received for Day 1. 

### Compile and Run  
In `advent-of-code/2023/day1/` run:
```
javac *.java
java Trebuchet
```
### Answers  
Part 1: 55123  
Part 2: 55260  

## Part 2
### Files
- [CubeConundrum.java](./day2/CubeConundrum.java): Runs the program
- [GameCounter.java](./day2/GameCounter.java): Counts colors for each game
- [input.txt](./day2/input.txt): The input data I received for Day 2
- [output.txt](./day2/output.txt): Helpful output when debugging

### Global Variables
- `boolean debug`: Set to `true` in `CubeConundrum.java` so that when run, output data is written to `output.txt`
- `int part`: Either `1` or `2` depending on which part of the Day 2 challenge you are looking to solve
- `String filename`: Set to `index.txt` for the input data I received for Day 2. 

### Compile and Run  
In `advent-of-code/2023/day2/` run:
```
javac *.java
java CubeConundrum
```
### Answers  
Part 1: 2563  
Part 2: 70768  
