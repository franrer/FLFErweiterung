package complex2;

public class ActiveWith0PercentFoam implements ITurretState {
    public ActiveWith0PercentFoam(IntelligentJoystick joystick) {
        if(!joystick.getTurret().isActive()) {
            joystick.getTurret().onOff();
        }
        joystick.getTurret().setFoam(0);
    }

    @Override
    public void nextState(IntelligentJoystick joystick) {
        joystick.setState( new ActiveWith3PercentFoam(joystick));
    }
}
