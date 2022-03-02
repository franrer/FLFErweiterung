package inputs.turretInputs;

import inputs.buttons.IButton;
import turrets.AbstractTurretSection;
import turrets.turretsWithFoam.TurretWithFoam;

public abstract class AbstractJoystick {
    protected AbstractTurretSection section;
    protected TurretWithFoam turret;
    protected Taster taster;

    public abstract void buttonPress(IButton button);

    public TurretWithFoam getTurret() {
        return turret;
    }

    public AbstractTurretSection getSection() {
        return section;
    }

    public Taster getTaster() {
        return taster;
    }


}
