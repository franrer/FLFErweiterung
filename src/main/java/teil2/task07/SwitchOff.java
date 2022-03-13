package teil2.task07;

import ccu.CCU;
import teil2.task06.SwitchType;

public class SwitchOff implements ICommands {
    private final CCU unit;

    public SwitchOff(CCU centralUnit) {
        this.unit = centralUnit;
    }

    @Override
    public void execute(SwitchType type) {
        switch (type) {
            case electroMotor -> unit.changeMotorState();
            case floorSprayNozzles -> unit.changeFloorSpraysNozzleState();
            default -> unit.changeLightState(type);
        }
    }
}