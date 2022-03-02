package cabin.operatorSection;

import cabin.Cabin;
import cabin.Seat;
import ccu.CCU;
import inputs.buttons.IButton;
import inputs.switches.Switch;
import inputs.turretInputs.AbstractJoystick;
import inputs.turretInputs.RotaryKnob;
import turrets.AbstractTurretSection;

public class OperatorSection extends AbstractTurretSection {

    private Controlpanel controlpanel;
    private Seat seat;
    private AbstractJoystick joystick;
    private RotaryKnob rotaryKnob;
    private Cabin cabin;

    public OperatorSection(Controlpanel controlpanel, CCU ccu) {
        this.controlpanel = controlpanel;
        this.ccu = ccu;
        seat = new Seat();
    }

    public RotaryKnob getRotaryKnob() {
        return rotaryKnob;
    }

    public void setRotaryKnob(RotaryKnob rotaryKnob) {
        this.rotaryKnob = rotaryKnob;
    }

    public AbstractJoystick getJoystick() {
        return joystick;
    }

    public void setJoystick(AbstractJoystick joystick) {
        this.joystick = joystick;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public Controlpanel getControlpanel() {
        return controlpanel;
    }

    public void setControlpanel(Controlpanel controlpanel) {
        this.controlpanel = controlpanel;
    }

    public CCU getCcu() {
        return ccu;
    }

    public void setCcu(CCU ccu) {
        this.ccu = ccu;
    }

    public void turnSwitch(Switch s) {
        ccu.turnSwitch(s);
    }

    public void buttonPress(IButton button) {
        ccu.buttonPress(button);
    }

    public void passString(String s) {
        ccu.validateString(s);
    }

    public void lockUnlock() {
        cabin.lockUnlock();
    }
}
