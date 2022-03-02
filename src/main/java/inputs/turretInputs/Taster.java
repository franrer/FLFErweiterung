package inputs.turretInputs;

import inputs.buttons.IButton;


public class Taster implements IButton {

    private final AbstractJoystick joystick;

    public Taster(AbstractJoystick joystick) {
        this.joystick = joystick;
    }

    @Override
    public void press() {
        joystick.buttonPress(this);

    }

    public AbstractJoystick getJoystick() {
        return joystick;
    }
}
