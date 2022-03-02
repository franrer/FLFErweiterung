package driveUnit.mechanical;

public class Axis {
    private Tire[] tires;
    private BrakeDisc[] brakeDisc;

    public Axis(Tire[] tires, BrakeDisc[] brakeDisc) {
        this.tires = tires;
        this.brakeDisc = brakeDisc;
    }

    public void brake() {
        for (BrakeDisc disc : brakeDisc) {
            disc.execute();
        }
    }

    public Tire[] getWheels() {
        return tires;
    }

    public BrakeDisc[] getBrakeDisc() {
        return brakeDisc;
    }
}
