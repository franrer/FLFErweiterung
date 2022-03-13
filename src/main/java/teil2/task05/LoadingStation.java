package teil2.task05;

import cabin.driverSection.BatteryDisplay;

public class LoadingStation implements IElectricLoader {
    private Pin pin;
    private IOnePinLoadable loadable;
    private BatteryDisplay batterystatus;

    public LoadingStation(Pin pin) {
        this.pin = pin;
    }

    public void setLoadable(IOnePinLoadable loadable) {
        this.loadable = loadable;
    }

    @Override
    public void load() {
        loadable.outsidePinConnect(pin);
    }

    public void setEnergyAmount(int energyAmount) {
        pin.setEnergyAmount(energyAmount);
    }

    public Pin getPin() {
        return pin;
    }

    public IOnePinLoadable getLoadable() {
        return loadable;
    }

    public Object getStatus() {
        return this.batterystatus;
    }
}
