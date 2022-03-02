package inputs.turretInputs;

import inputs.buttons.IButton;
import lights.Position;

public class ButtonJoy implements IButton {

    private Joystick joystick;
    private Position position;

    public ButtonJoy(Joystick joystick, Position position) {
        this.joystick = joystick;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Joystick getJoystick() {
        return joystick;
    }


    @Override
    public void press() {
        joystick.buttonPress(this);
    }
}
