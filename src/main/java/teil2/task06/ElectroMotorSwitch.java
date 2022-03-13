package teil2.task06;

import ccu.CCU;

public class ElectroMotorSwitch extends Switch {

    public ElectroMotorSwitch(CCU unit) {
        super(SwitchType.electroMotor, unit);
    }

    @Override
    public void buttonPress() {
        if (this.state instanceof OnState) {
            this.on.execute(this.switchTypeOperation);
        } else {
            this.off.execute(this.switchTypeOperation);
        }
    }

    @Override
    public void pressButton() {

    }
}