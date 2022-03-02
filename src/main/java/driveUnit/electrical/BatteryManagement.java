package driveUnit.electrical;

public enum BatteryManagement {
    INSTANCE;

    private BatteryBox batterybox;

    public String load(int amount) {
        int toLoad = amount;
        Battery[][] batteries = batterybox.getBatteries();
        for (int x = batteries.length - 1; x >= 0; x--) {
            for (int y = batteries[x].length - 1; y >= 0; y--) {
                if (batteries[x][y].emptySpace() >= toLoad) {
                    batteries[x][y].charge(toLoad);
                    return "load successful" + amount + " units";
                } else {
                    batteries[x][y].fillToMax();
                    toLoad -= batteries[x][y].emptySpace();
                }
            }
        }
        return "fully loaded after" + (amount - toLoad) + "units";
    }

    public String use(int amount) {
        int toUse = amount;
        Battery[][] batteries = batterybox.getBatteries();
        for (Battery[] batteryLine : batteries) {
            for (Battery battery : batteryLine) {
                if (battery.occupiedSpace() >= toUse) {
                    battery.takeOut(toUse);
                    return amount + " units used";
                } else {
                    battery.takeOut(battery.occupiedSpace());
                    toUse -= battery.occupiedSpace();
                }
            }
        }
        return "Battery empty. Missing" + toUse + "units";
    }

    public int lookAmount() {
        int amount = 0;
        for (Battery[] batteryLine : batterybox.getBatteries()) {
            for (Battery b : batteryLine) {
                amount += b.occupiedSpace();
            }
        }

        return amount;
    }

    public BatteryBox getBatteryBox() {
        return batterybox;
    }

    public void setBatteryBox(BatteryBox batterybox) {
        this.batterybox = batterybox;
    }

    public int lookAmountInPercent() {
        int max = 0;
        for (Battery[] batteryLine : batterybox.getBatteries()) {
            for (Battery b : batteryLine) {
                max += b.maxCapacity();
            }
        }
        return ((max / lookAmount()) * 100);
    }
}
