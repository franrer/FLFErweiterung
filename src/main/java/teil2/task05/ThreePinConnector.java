package teil2.task05;

import driveUnit.electrical.BatteryManagement;

public class ThreePinConnector {
    protected Pin pin1;
    protected Pin pin2;
    protected Pin pin3;
    private BatteryManagement batteryManagement;

    public ThreePinConnector(Pin pin1, Pin pin2, Pin pin3, BatteryManagement batteryManagement) {
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.pin3 = pin3;
        this.batteryManagement=batteryManagement;
    }

    public Pin getPin1() {
        return pin1;
    }

    public Pin getPin2() {
        return pin2;
    }

    public Pin getPin3() {
        return pin3;
    }

    public BatteryManagement getBatteryManagement() {
        return batteryManagement;
    }

    public void load() {
        batteryManagement.load(pin1.getEnergyAmount());
        batteryManagement.load(pin2.getEnergyAmount());
        batteryManagement.load(pin3.getEnergyAmount());
    }
}
