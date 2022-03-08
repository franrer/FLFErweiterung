package driveUnit.electrical;
import teil2.task03.IBattery;

public class BatteryBox {
    private IBattery[][] batteries;

    public BatteryBox(IBattery[][] batteries) {
        this.batteries = batteries;
    }

    public IBattery[][] getBatteries() {
        return batteries;
    }
}
