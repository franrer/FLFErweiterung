package teil2.task06;

public class OnState implements IButtonState {

    @Override
    public void switchState(OperatorButton toOperatorButton) {
        toOperatorButton.setState(new OffState());
    }
}