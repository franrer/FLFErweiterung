package cabin.driverSection;

public class BatteryDisplay implements IDisplay {

    DriverSection driverSection;

    public BatteryDisplay(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    public DriverSection getDriverSection() {
        return driverSection;
    }

    public void setDriverSection(DriverSection driverSection) {
        this.driverSection = driverSection;
    }

    @Override
    public String show() {
        return driverSection.show(driverSection.getBatteryDisplay());
    }

}
