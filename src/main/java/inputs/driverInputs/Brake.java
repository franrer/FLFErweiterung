package inputs.driverInputs;

import cabin.driverSection.DriverSection;

public class Brake implements IPedal {

    DriverSection driverSection;

    public Brake(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    public DriverSection getDriverSection() {
        return driverSection;
    }

    public void setDriverSection(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    @Override
    public void stepOn() {
        driverSection.stepOn(this);
    }

}
