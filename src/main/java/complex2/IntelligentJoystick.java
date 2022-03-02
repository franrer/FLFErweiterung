package complex2;

import inputs.buttons.IButton;
import inputs.turretInputs.AbstractJoystick;
import inputs.turretInputs.Taster;
import turrets.AbstractTurretSection;
import turrets.turretsWithFoam.TurretWithFoam;

public class IntelligentJoystick extends AbstractJoystick {

    private AbstractTurretSection section;
    private IntelligentButton button;
    private ITurretState state;
    private ITurretState prevState;


    public IntelligentJoystick(AbstractTurretSection section, TurretWithFoam turret) {
        this.section = section;
        this.turret = turret;
        button = new IntelligentButton(this);
        taster = new Taster(this);
        state = new InactiveTurret(this);
    }


    public void buttonPress(IButton button) {
        if (button instanceof IntelligentButton) {
            state.nextState(this);
        }
        if (button instanceof Taster) {
            sprayState(button);
        }
    }

    public AbstractTurretSection getSection() {
        return section;
    }

    public IntelligentButton getButton() {
        return button;
    }


    public Taster getTaster() {
        return taster;
    }

    public TurretWithFoam getTurret() {
        return turret;
    }

    public void setState(ITurretState state) {
        this.state = state;
    }

    public ITurretState getPrevState() {
        return prevState;
    }

    public void sprayState(IButton button) {
        prevState = state;
        state = new SprayingTurret(this);
    }
}
