package teil2.task06;

import ccu.CCU;
import teil2.task07.SwitchOff;
import teil2.task07.SwitchOn;

public abstract class Switch {
    protected SwitchType switchTypeOperation;
    protected IButtonState state;
    protected SwitchOn on;
    protected SwitchOff off;

    Switch(SwitchType type, CCU unit) {
        this.switchTypeOperation = type;
        this.state = new OffState();
        this.on = new SwitchOn(unit);
        this.off = new SwitchOff(unit);

    }

    public abstract void buttonPress();

    public SwitchType getSwitchTypeOperation() {
        return switchTypeOperation;
    }

    public void setState(IButtonState state) {
        this.state = state;
    }

    public boolean getState() {
        return !(this.state instanceof OffState);
    }

    public abstract void pressButton();
}
