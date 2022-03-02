package complex1;

import cabin.BusDoor;

public class CardReader implements ICardReader {

    BusDoor busDoor;

    public CardReader(BusDoor busDoor) {
        this.busDoor = busDoor;
    }

    @Override
    public void readString(IDCard idCard) {
        busDoor.passString(idCard.getRfidChip().getPassCode());
    }
}
