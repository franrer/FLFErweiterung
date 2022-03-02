package driveUnit.electrical;

public class BatteryBox {
    private Battery[][] batteries;

    public BatteryBox(Battery[][] batteries) {
        this.batteries = batteries;
    }

    public Battery[][] getBatteries() {
        return batteries;
    }
}
