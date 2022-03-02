package complex1;

public class IDCard {

    private RFIDChip rfidChip;

    public IDCard(RFIDChip rfidChip) {
        this.rfidChip = rfidChip;
    }

    public RFIDChip getRfidChip() {
        return rfidChip;
    }
}
