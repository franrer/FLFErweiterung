package inputs.switches;

import cabin.operatorSection.Controlpanel;
import lights.Type;

public class LightSwitch extends Switch {

    private Type type;

    public LightSwitch(Type type, Controlpanel controlpanel) {
        super(controlpanel);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void turnSwitch() {
        super.turnSwitch();
    }

}
