package driveUnit.electrical;

import teil2.task03.IBattery;

public enum BatteryManagement {
    INSTANCE;

    private BatteryBox batterybox;

    public String load(int amount) {
        int toLoad = amount;
        IBattery[][] batteries = batterybox.getBatteries();
        for (IBattery[] batteryLine : batteries) {
            for (IBattery battery : batteryLine) {
                if (battery.emptySpace() >= toLoad) {
                    battery.charge(toLoad);
                    return "load successful" + amount + " units";
                } else {
                    toLoad -= battery.emptySpace();
                    battery.fillToMax();
                }
            }
        }
        return "fully loaded after" + (amount - toLoad) + "units";
    }

    public String use(int amount) {
        int toUse = amount;
        IBattery[][] batteries = batterybox.getBatteries();
        for (IBattery[] batteryLine : batteries) {
            for (IBattery battery : batteryLine) {
                if (battery.occupiedSpace() >= toUse) {
                    battery.takeOut(toUse);
                    return amount + " units used";
                } else {
                    toUse -= battery.occupiedSpace();
                    battery.takeOut(battery.occupiedSpace());
                }
            }
        }
        return "Battery empty. Missing" + toUse + "units";
    }

    public int lookAmount() {
        int amount = 0;
        for (IBattery[] batteryLine : batterybox.getBatteries()) {
            for (IBattery b : batteryLine) {
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
        for (IBattery[] batteryLine : batterybox.getBatteries()) {
            for (IBattery b : batteryLine) {
                max += b.maxCapacity();
            }
        }
        return ((max / lookAmount()) * 100);
    }
}
