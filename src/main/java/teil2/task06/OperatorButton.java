package teil2.task06;

import ccu.CCU;
import teil2.task07.SwitchOff;
import teil2.task07.SwitchOn;

public class OperatorButton {
    protected SwitchType switchTypeOperation;
    protected IButtonState state;
    protected SwitchOn on;
    protected SwitchOff off;

    public OperatorButton(SwitchType type, CCU unit) {
        this.switchTypeOperation = type;
        this.state = new OffState();
        this.on = new SwitchOn(unit);
        this.off = new SwitchOff(unit);

    }

    public void buttonPress() {
        this.state.switchState(this);
        if (this.state instanceof OnState) {
            this.on.execute(this.switchTypeOperation);
        } else {
            this.off.execute(this.switchTypeOperation);
        }
    }

    public SwitchType getSwitchTypeOperation() {
        return switchTypeOperation;
    }

    public void setState(IButtonState state) {
        this.state = state;
    }

    public IButtonState getState() {
        return state;
    }
}
