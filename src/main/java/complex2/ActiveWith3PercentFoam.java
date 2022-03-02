package complex2;

public class ActiveWith3PercentFoam implements ITurretState {
    public ActiveWith3PercentFoam(IntelligentJoystick joystick) {
        joystick.getTurret().setFoam(3);
    }

    @Override
    public void nextState(IntelligentJoystick joystick) {
        joystick.setState(new ActiveWith5PercentFoam(joystick));
    }
}
