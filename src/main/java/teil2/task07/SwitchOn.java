package teil2.task07;

import ccu.CCU;
import teil2.task06.SwitchType;

public class SwitchOn implements ICommands {
    private final CCU unit;

    public SwitchOn(CCU unit) {
        this.unit = unit;
    }

    //TODO: make the type distinction here and give the functions directly to the central unit
    @Override
    public void execute(SwitchType type) {
        switch (type) {
            case electroMotor -> unit.changeMotorState();
            case floorSprayNozzles -> unit.changeFloorNozzleSpraysState();
            default -> unit.changeLightState(type);
        }
    }
}