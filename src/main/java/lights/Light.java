package lights;

import ccu.CCU;
import teil2.task02.*;
import com.google.common.eventbus.Subscribe;

public class Light extends Subscriber {

    private CCU ccu;
    private static boolean isOn;
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

    public Light(Side side, int i) {
        super();
    }

    public Light(Position position) {
        super();
    }

    public Light(Type headlights, int i) {
        super();
    }

    public Light(Side side) {
        super();
    }

    public Light(Side side, Position position){ super();}

    public CCU getCcu() {
        return ccu;
    }

    public void setCcu(CCU ccu) {
        this.ccu = ccu;
    }

    public static boolean isOn() {
        return isOn;
    }

    public static void setOn(boolean on) {
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
        System.out.println("receive Bluelight");
        if (this.isOn()) {
            onOff();
        } else {
            isOn();
        }
    }

    @Subscribe
    public void receive(WarningLightEvent event) {
        if (isOn()) {
            onOff();
        } else {
            isOn();
        }
    }

    @Subscribe
    public void receive(SideLightsEvent event) {
        if (this.isOn()) {
            onOff();
        } else {
            isOn();
        }
    }

    @Subscribe
    public void receive(RoofLightsEvent event) {
        if (this.position == Position.TOP) {
            if (isOn()) {
                this.onOff();
            } else {
                this.isOn();
            }
        }

    }
    @Subscribe
    public void receive(FrontLightsEvent event) {
        if (this.side == Side.FRONT) {
            if (isOn()) {
                this.onOff();
            } else {
                this.isOn();
            }
        }

    }
}