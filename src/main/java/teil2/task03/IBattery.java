package teil2.task03;

public interface IBattery {
    public void charge(int amount) ;


    public int emptySpace() ;


    public int maxCapacity();

    public int occupiedSpace();

    public void fillToMax();

    public int takeOut(int amount);

}
