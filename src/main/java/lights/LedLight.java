package lights;

import ccu.CCU;

public class LedLight extends Light {

    private LED[] leds;


    public LedLight(int numOfLeds, CCU ccu, Position position, LightColor color, Side side, Type type) {
        super(ccu, position, color, side, type);
        if (numOfLeds == 1 || numOfLeds == 2 || numOfLeds == 4) {
            leds = new LED[numOfLeds];
            for (int i = 0; i < numOfLeds; i++) {
                leds[i] = new LED();
            }
        } else
            leds = null;
    }


}
