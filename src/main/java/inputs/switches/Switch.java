package inputs.switches;

import cabin.operatorSection.Controlpanel;
import teil2.task06.IButtonState;

public abstract class Switch {

    protected IButtonState state;
    protected Object switchTypeOperation;
    private boolean isOn;
    private Controlpanel controlpanel;

    public Switch(Controlpanel controlpanel) {
        this.isOn = false;
        this.controlpanel = controlpanel;
    }

    public Controlpanel getControlPanel() {
        return controlpanel;
    }

    public void turnSwitch() {
        isOn = !isOn;
        controlpanel.turnSwitch(this);
    }

    public boolean isOn() {
        return isOn;
    }
}
