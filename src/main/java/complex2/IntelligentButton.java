package complex2;

import inputs.buttons.IButton;

public class IntelligentButton implements IButton {
    private IntelligentJoystick joystick;


    public IntelligentButton(IntelligentJoystick joystick) {
        this.joystick = joystick;
    }

    public IntelligentJoystick getJoystick() {
        return joystick;
    }


    @Override
    public void press() {
        joystick.buttonPress(this);
    }

}
