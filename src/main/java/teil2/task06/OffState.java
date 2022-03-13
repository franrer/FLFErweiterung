package teil2.task06;

public class OffState implements IButtonState {

    @Override
    public void switchState(Switch toSwitch) {
        toSwitch.setState(new OnState());

    }
}