package teil2.task07;

import ccu.CCU;
import teil2.task06.SwitchType;

public class SwitchOn implements ICommands {
    private final CCU unit;

    public SwitchOn(CCU unit) {
        this.unit = unit;
    }

    public void execute(SwitchType type) {
        switch (type) {
            case electroMotor -> unit.changeMotorState();
            case floorSprayNozzles -> unit.changeFloorSpraysNozzleState();
            default -> unit.changeLightState(type);
        }
    }
}