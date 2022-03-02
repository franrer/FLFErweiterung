package cabin.driverSection;

import cabin.Cabin;
import cabin.Seat;
import ccu.CCU;
import inputs.buttons.IButton;
import inputs.driverInputs.Accelerator;
import inputs.driverInputs.Brake;
import inputs.driverInputs.IPedal;
import inputs.driverInputs.SteeringWheel;
import inputs.turretInputs.AbstractJoystick;
import inputs.turretInputs.RotaryKnob;
import turrets.AbstractTurretSection;

public class DriverSection extends AbstractTurretSection {


    private Cabin cabin;


    private Accelerator accelerator;
    private Brake brake;

    private BatteryDisplay batteryDisplay;
    private SpeedDisplay speedDisplay;

    private SteeringWheel steeringWheel;


    private Seat seat;


    public DriverSection(Cabin cabin) {
        this.cabin = cabin;
    }

    public DriverSection() {
        this.cabin = null;
        batteryDisplay = new BatteryDisplay(this);
        speedDisplay = new SpeedDisplay(this);
        seat = new Seat();
        accelerator = new Accelerator(this);
        brake = new Brake(this);
        steeringWheel = new SteeringWheel(this);
    }

    public void stepOn(IPedal pedal) {
        ccu.stepOn(pedal);
    }

    public String show(IDisplay display) {

        return ccu.show(display);

    }

    public void steer(int degree) {
        ccu.steer(degree);
    }

    public void buttonPress(IButton button) {
        ccu.buttonPress(button);
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public CCU getCcu() {
        return ccu;
    }

    public void setCcu(CCU ccu) {
        this.ccu = ccu;
    }

    public Accelerator getAccelerator() {
        return accelerator;
    }

    public Brake getBrake() {
        return brake;
    }

    public BatteryDisplay getBatteryDisplay() {
        return batteryDisplay;
    }

    public SpeedDisplay getSpeedDisplay() {
        return speedDisplay;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public RotaryKnob getRotaryKnob() {
        return rotaryKnob;
    }

    public void setRotaryKnob(RotaryKnob rotaryKnob) {
        this.rotaryKnob = rotaryKnob;
    }

    public Seat getSeat() {
        return seat;
    }

    public AbstractJoystick getJoystick() {
        return joystick;
    }

    public void setJoystick(AbstractJoystick joystick) {
        this.joystick = joystick;
    }


}
