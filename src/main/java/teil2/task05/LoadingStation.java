package teil2.task05;

public class LoadingStation implements IElectricLoader {
    Pin pin;
    IOnePinLoadable loadable;

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
        pin.energyAmount = energyAmount;
    }

    public Pin getPin() {
        return pin;
    }

    public IOnePinLoadable getLoadable() {
        return loadable;
    }
}
