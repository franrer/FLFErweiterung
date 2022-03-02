package complex2;

public class SprayingTurret implements ITurretState {

    public SprayingTurret(IntelligentJoystick joystick) {
        joystick.getSection().buttonPress(joystick.getTaster(), joystick.getTurret());
        nextState(joystick);
    }

    @Override
    public void nextState(IntelligentJoystick joystick) {
        joystick.setState(joystick.getPrevState());
    }
}
