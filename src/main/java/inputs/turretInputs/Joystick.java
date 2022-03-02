package inputs.turretInputs;

import inputs.buttons.IButton;
import lights.Position;
import turrets.AbstractTurretSection;
import turrets.turretsWithFoam.TurretWithFoam;

public class Joystick extends AbstractJoystick {

    private ButtonJoy buttonRight;
    private ButtonJoy buttonLeft;


    public Joystick(AbstractTurretSection section, TurretWithFoam turret) {
        this.section = section;
        this.turret = turret;
        buttonRight = new ButtonJoy(this, Position.RIGHT);
        buttonLeft = new ButtonJoy(this, Position.LEFT);
        taster = new Taster(this);
    }

    public void buttonPress(IButton button) {
        section.buttonPress(button, turret);
    }

    public ButtonJoy getButtonRight() {
        return buttonRight;
    }

    public ButtonJoy getButtonLeft() {
        return buttonLeft;
    }


}
