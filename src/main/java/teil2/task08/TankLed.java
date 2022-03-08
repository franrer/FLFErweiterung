package teil2.task08;

import lights.LedLight;
import lights.LightColor;

public class TankLed implements ITankSensorListener{

    LedLight led;
    @Override
    public void lessThanHalf() {
        led.setOn(true);
        led.setColor(LightColor.YELLOW);
    }

    @Override
    public void lessThanQuarter() {
        led.setOn(true);
        led.setColor(LightColor.ORANGE);
    }

    @Override
    public void lessThanTenPercent() {
        led.setOn(true);
        led.setColor(LightColor.RED);
    }

    @Override
    public void moreThanHalf() {
        led.setOn(false);
    }
}
