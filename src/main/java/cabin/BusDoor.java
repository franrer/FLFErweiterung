package cabin;

import complex1.CardReader;
import inputs.buttons.ButtonDoor;
import inputs.buttons.IButton;
import lights.Side;

public class BusDoor {

    private Side side;
    private ButtonDoor buttonDoor;
    private Cabin cabin;
    private boolean isOpen;
    private CardReader cardReader;
    private boolean isLocked;

    public BusDoor(Cabin cabin, Side side) {
        this.cabin = cabin;
        this.isOpen = false;
        this.isLocked = true;
        this.side = side;
        cardReader = new CardReader(this);
    }

    public Side getSide() {
        return side;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public CardReader getCardReader() {
        return cardReader;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void openClose() {
        if (!isLocked)
            isOpen = !isOpen;
    }

    public ButtonDoor getButtonDoor() {
        return buttonDoor;
    }

    public void setButtonDoor(ButtonDoor buttonDoor) {
        this.buttonDoor = buttonDoor;
    }

    public void buttonPress(IButton button) {
        cabin.buttonPress(button);
    }

    public void passString(String s) {
        cabin.passString(s);
    }

    public void lockUnlock() {
        isLocked = !isLocked;
    }
}
