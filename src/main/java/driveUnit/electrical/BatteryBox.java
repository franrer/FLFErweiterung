package driveUnit.electrical;

import teil2.task03.CellBattery;
import teil2.task03.IBattery;

public class BatteryBox {
    private IBattery[][] batteries;

    public BatteryBox(IBattery[][] batteries) {
        this.batteries = batteries;
    }

    public BatteryBox(Builder builder) {
        this.batteries = builder.batteries;
    }

    public IBattery[][] getBatteries() {
        return batteries;
    }

    public static class Builder {
        private int boxWidth;
        private int boxLength;
        private int batteryWidth;
        private int batteryHeight;
        private int batteryLength;
        IBattery[][] batteries;

        public Builder boxWidth(int boxWidth) {
            this.boxWidth = boxWidth;
            return this;
        }

        public Builder boxLength(int boxLength) {
            this.boxLength = boxLength;
            return this;
        }

        public Builder batteryWidth(int batteryWidth) {
            this.batteryWidth = batteryWidth;
            return this;
        }

        public Builder batteryHeight(int batteryHeight) {
            this.batteryHeight = batteryHeight;
            return this;
        }

        public Builder batteryLength(int batteryLength) {
            this.batteryLength = batteryLength;
            return this;
        }

        public BatteryBox buildCellBox() {
            batteries = new IBattery[boxWidth][boxLength];
            for (int x = 0; x < boxWidth; x++) {
                for (int y = 0; y < boxLength; y++) {
                    batteries[x][y] = new CellBattery(batteryWidth, batteryLength, batteryHeight);
                }
            }
            return new BatteryBox(batteries);
        }
    }
}
