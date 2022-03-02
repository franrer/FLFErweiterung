package complex2;

public class InactiveTurret implements ITurretState {
    public InactiveTurret(IntelligentJoystick joystick) {
        if (joystick.getTurret().isActive()) {
            joystick.getTurret().onOff();
        }
    }

    @Override
    public void nextState(IntelligentJoystick joystick) {
        joystick.setState(new ActiveWith0PercentFoam(joystick));
    }
}
