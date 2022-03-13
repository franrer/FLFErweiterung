package teil2.task06;

public class OffState implements IButtonState {

    @Override
    public void switchState(OperatorButton toOperatorButton) {
        toOperatorButton.setState(new OnState());

    }
}