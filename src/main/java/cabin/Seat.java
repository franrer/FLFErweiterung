package cabin;

public class Seat {

    private boolean isOccupied;

    public Seat() {
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void sit() {
        isOccupied = true;
    }

    public void leave() {
        isOccupied = false;
    }

}
