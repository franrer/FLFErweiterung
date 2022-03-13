package cabin;

import cabin.driverSection.DriverSection;
import cabin.operatorSection.OperatorSection;
import inputs.buttons.IButton;

public class Cabin {

    private DriverSection driverSection;
    private OperatorSection operatorSection;

    private BusDoor busDoorLeft;
    private BusDoor busDoorRight;


    public Cabin(DriverSection driverSection, OperatorSection operatorSection) {
        this.driverSection = driverSection;
        this.operatorSection = operatorSection;
        this.busDoorLeft = null;
        this.busDoorRight = null;
        Seat[] seats = new Seat[2];
        seats[0] = new Seat();
        seats[1] = new Seat();
    }

    public DriverSection getDriverSection() {
        return driverSection;
    }

    public void setDriverSection(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    public OperatorSection getOperatorSection() {
        return operatorSection;
    }

    public void setOperatorSection(OperatorSection operatorSection) {
        this.operatorSection = operatorSection;
    }

    public BusDoor getBusDoorLeft() {
        return busDoorLeft;
    }

    public void setBusDoorLeft(BusDoor busDoorLeft) {
        this.busDoorLeft = busDoorLeft;
    }

    public BusDoor getBusDoorRight() {
        return busDoorRight;
    }

    public void setBusDoorRight(BusDoor busDoorRight) {
        this.busDoorRight = busDoorRight;
    }

    public void buttonPress(IButton button) {
        driverSection.buttonPress(button);
    }

    public void passString(String s) {
        operatorSection.passString(s);
    }

    public void lockUnlock() {
        busDoorRight.lockUnlock();
        busDoorLeft.lockUnlock();
    }

    public cabin.driverSection.DriverSection getSeats() {
        return getSeats();
    }
}
