package teil2.task03;

public class CellBattery extends SubCell implements IBattery {

    public CellBattery(int amountMainCells, int amountSubCells, int amountCells) {
        this.unitList = new MainCell[amountMainCells];
        for(int i=0;i<amountMainCells;i++){
            unitList[i]=new MainCell(amountSubCells,amountCells);
        }
    }

    @Override
    public void charge(int amount) {
        load(amount);
    }
    @Override
    public int emptySpace() {
        return maxCapacity()-getLoad();
    }
    @Override
    public int maxCapacity() {
       return getCapacity();
    }

    @Override
    public int occupiedSpace() {
        return getLoad();
    }
    @Override
    public void fillToMax() {
        charge(emptySpace());
    }
    @Override
    public int takeOut(int amount) {
        unLoad(amount);
        return amount;
    }


}
