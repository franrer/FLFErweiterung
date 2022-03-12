package teil2.task08;

import lights.LedLight;
import lights.LightColor;

public class TankLed implements ITankSensorListener {
    private LedLight led;

    public TankLed(LedLight led) {
        this.led = led;
    }

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
        led.setColor(LightColor.WHITE);
    }

    public LedLight getLed() {
        return led;
    }
}
