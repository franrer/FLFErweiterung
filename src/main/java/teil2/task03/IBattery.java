package teil2.task03;

public interface IBattery {
    void charge(int amount);

    int emptySpace();

    int maxCapacity();

    int occupiedSpace();

    void fillToMax();

    int takeOut(int amount);

}
