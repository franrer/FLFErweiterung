package lights;

import ccu.CCU;
import com.google.common.eventbus.Subscribe;
import teil2.task02.*;

public class Light extends Subscriber {

    private CCU ccu;
    private boolean isOn;
    private Position position;
    protected LightColor color;
    private Side side;
    private Type type;

    public Light(CCU ccu, Position position, LightColor color, Side side, Type type) {
        this.ccu = ccu;
        this.isOn = false;
        this.position = position;
        this.color = color;
        this.side = side;
        this.type = type;
    }

    public CCU getCcu() {
        return ccu;
    }

    public void setCcu(CCU ccu) {
        this.ccu = ccu;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void onOff() {
        isOn = !isOn;
    }

    public Side getSide() {
        return side;
    }

    public Type getType() {
        return type;
    }

    public LightColor getColor() {
        return color;
    }

    @Subscribe
    public void receive(BlueLightsEvent event) {
        if (getType() == Type.BLUELIGHT) {
            onOff();
        }
    }

    @Subscribe
    public void receive(WarningLightEvent event) {
        if (getType() == Type.WARNINGLIGHT) {
            onOff();
        }
    }


    @Subscribe
    public void receive(SideLightsEvent event) {
        if (getType() == Type.SPOTLIGHT) {
            if (getSide() == Side.LEFT || getSide() == Side.RIGHT || getSide() == Side.SIDE)
                onOff();
        }
    }

    @Subscribe
    public void receive(RoofLightsEvent event) {
        if (getType() == Type.SPOTLIGHT) {
            if (getSide() == Side.ROOF)
                onOff();
        }
    }


    @Subscribe
    public void receive(FrontLightsEvent event) {
        if (getType() == Type.SPOTLIGHT) {
            if (getSide() == Side.FRONT)
                onOff();
        }
    }
}