package lights;

import ccu.CCU;

public class Light {

    private CCU ccu;
    private boolean isOn;
    private Position position;
    private LightColor color;
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


}
