package mixingUnit;

import teil2.task08.TankSensor;

public class FoamTank extends Tank {
    public FoamTank(int length, int width, int height) {
        super(length, width, height);
    }

    public FoamTank(int length, int width, int height, TankSensor tankSensor) {
        super(length, width, height, tankSensor);
    }


}
