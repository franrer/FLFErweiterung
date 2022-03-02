package complex2;

public class ActiveWith5PercentFoam implements ITurretState {
    public ActiveWith5PercentFoam(IntelligentJoystick joystick) {
        joystick.getTurret().setFoam(5);
    }

    @Override
    public void nextState(IntelligentJoystick joystick) {
        joystick.setState(new ActiveWith10PercentFoam(joystick));
    }
}
