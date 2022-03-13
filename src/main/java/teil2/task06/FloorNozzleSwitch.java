package teil2.task06;

import ccu.CCU;

public abstract class FloorNozzleSwitch extends Switch {

    FloorNozzleSwitch(SwitchType type, CCU unit) {
        super(type, unit);
    }


    @Override
    public void pressButton() {
        this.state.switchState(this);
        if(this.state instanceof OnState) {
            this.on.execute(this.switchTypeOperation);
        } else {
            this.off.execute(this.switchTypeOperation);
        }
    }
}