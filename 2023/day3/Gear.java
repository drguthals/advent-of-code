import java.util.ArrayList;

public class Gear {

    int row;
    int column;
    int gearNumberProduct;
    int gearNumber1;
    int gearNumber2;
    boolean isGear;

    public Gear(int row, int column) {
        this.row = row;
        this.column = column;
        gearNumber1 = -1;
        gearNumber2 = -1;
        isGear = false;
        gearNumberProduct = 0;
    }

    public boolean isItThisGear(int row, int column) {
        if (this.row == row && this.column == column) return true;
        return false;
    }

    public void addGearNumbers(int gearNumber) {
        if(gearNumber1 < 0) gearNumber1 = gearNumber;
        else if(gearNumber2 < 0) {
            gearNumber2 = gearNumber;
            isGear = true;
        } else {
            isGear = false;
        }
    }

    public int getGearNumberProduct() {
        if(isGear) gearNumberProduct = gearNumber1 * gearNumber2;
        return gearNumberProduct;
    }
}
