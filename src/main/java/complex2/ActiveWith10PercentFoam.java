package complex2;

public class ActiveWith10PercentFoam implements ITurretState {
    public ActiveWith10PercentFoam(IntelligentJoystick joystick) {
        joystick.getTurret().setFoam(10);
    }

    @Override
    public void nextState(IntelligentJoystick joystick) {
        joystick.setState(new InactiveTurret(joystick));
    }

}
