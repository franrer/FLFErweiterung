package turrets;

import ccu.CCU;
import inputs.buttons.IButton;
import inputs.turretInputs.AbstractJoystick;
import inputs.turretInputs.RotaryKnob;
import turrets.turretsWithFoam.TurretWithFoam;

public abstract class AbstractTurretSection {
    protected CCU ccu;
    protected RotaryKnob rotaryKnob;
    protected AbstractJoystick joystick;

    public void buttonPress(IButton button, TurretWithFoam turret) {
        ccu.buttonPress(button, turret);
    }


    public void turnLeft(TurretWithFoam turret) {
        ccu.turnLeft(turret);
    }


    public void turnRight(TurretWithFoam turret) {
        ccu.turnRight(turret);
    }
}
