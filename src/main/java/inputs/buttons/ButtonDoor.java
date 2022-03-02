package inputs.buttons;

import cabin.BusDoor;

public class ButtonDoor implements IButton {

    private BusDoor busDoor;

    public ButtonDoor(BusDoor busDoor) {
        this.busDoor = busDoor;
    }

    public BusDoor getBusDoor() {
        return busDoor;
    }

    @Override
    public void press() {
        busDoor.buttonPress(this);
    }
}
