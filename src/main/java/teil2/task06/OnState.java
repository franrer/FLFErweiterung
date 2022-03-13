package teil2.task06;

public class OnState implements IButtonState {

    @Override
    public void switchState(Switch toSwitch) {
        toSwitch.setState(new OffState());
    }
}