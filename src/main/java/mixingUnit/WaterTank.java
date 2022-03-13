package mixingUnit;

import teil2.task08.TankSensor;

public class WaterTank extends Tank {


    public WaterTank(int length, int width, int height) {
        super(length, width, height);
    }
    public WaterTank(int length, int width, int height, TankSensor tankSensor) {
        super(length, width, height,tankSensor);
    }

    public WaterTank() {
        super();
    }
}
