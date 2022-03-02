package inputs.driverInputs;

import cabin.driverSection.DriverSection;

public class SteeringWheel {

    DriverSection driverSection;

    public SteeringWheel(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    public DriverSection getDriverSection() {
        return driverSection;
    }

    public void setDriverSection(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    public void steerLeft(int degree) {
        driverSection.steer(-degree);
    }

    public void steerRight(int degree) {
        driverSection.steer(degree);
    }

}
