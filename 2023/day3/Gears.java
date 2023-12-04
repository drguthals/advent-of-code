import java.util.ArrayList;

public class Gears {

    ArrayList<Gear> stars;
    boolean debug;
    Gear mostRecentGear;
    int gearProductSum;

    public Gears(boolean debug) {
        this.debug = debug;
        stars = new ArrayList<Gear>();
        mostRecentGear = new Gear(-1, -1);
        gearProductSum = 0;
    }

    public int getGearSum(){
        for( Gear gear : stars ){
            gearProductSum += (gear.getGearNumberProduct());
        }
        return gearProductSum;
    }

    public String getGearSumDebug() {
        String output = "";
        for( Gear gear : stars ){
            output += ("(" + gear.row + ", " + gear.column + "): ");
            output += (gear.gearNumber1 + " + " + gear.gearNumber2 + " = " + gear.getGearNumberProduct() + "\n");
        }
        return output;
    }
    
    public boolean addGear(int row, int column) {
        if(!hasGear(row, column)) {
            Gear gear = new Gear(row, column);
            mostRecentGear = gear;
            stars.add(gear);
        }
        return true;
    }

    public void addPartToGear(int partNumber) {
        mostRecentGear.addGearNumbers(partNumber);
    }

    public boolean hasGear(int row, int column) {
        boolean thisGear = false;
        for( Gear gear : stars ) {
            thisGear = gear.isItThisGear(row, column);
            mostRecentGear = gear;
            if(thisGear) break;
        }
        return thisGear;
    }
}
